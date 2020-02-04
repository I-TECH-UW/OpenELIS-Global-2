#!/usr/bin/env python
# -*- coding: utf-8 -*-

import getopt
import grp
import glob
import os
import subprocess
import platform
import sys
import string
import shutil
import stat
import time
import re
from time import gmtime, strftime
from random import Random

OE_VAR_DIR = "/var/lib/openelis-global/"
OE_CONFIG_DIR = "/etc/openelis-global/"

VERSION = ""
DOCKER_DIR = "./dockerImage/"
TEMPLATE_DIR = "./templates/"
STAGING_DIR = "./stagingFiles/"
DB_DIR = "./database/"
DB_INIT_DIR = "./databaseInitScripts/"
SCRIPTS_DIR = "./scripts/"
CROSSTAB_DIR = "./crosstab/"
ROLLBACK_DIR = "./rollback/"
LOG_DIR = "./log/"
FIX_DIR = "./fixes/"
APT_CACHE_DIR = "/var/cache/apt/archives/"
EXPECTED_CROSSTAB_FUNCTIONS = "3"
CRON_FILE_DIR = "./cronFiles/"
CRON_INSTALL_DIR = "/etc/cron.d/"
CRON_FILE = "openElis"
POSTGRES_LIB_DIR = "/usr/lib/postgresql/8.3/lib/"
BACKUP_DIR = OE_VAR_DIR + "backups/"
BACKUP_SCRIPT_NAME = "DatabaseBackup.pl"
LOG_FILE_NAME = "installer.log"
LANG_NAME = "en_US.UTF-8"
POSTGRES_ROLE_UPDATE_FILE_NAME = "updateDBPassword.sql"
APP_NAME = "OpenELIS-Global"
CLINLIMS_PWD = ''
ADMIN_PWD = ''
SITE_ID = ''
LOG_FILE = ''
PRINT_TO_CONSOLE = True
ACTION_TIME = ''
NO_PROMPT = False

DOCKER_OE_REPO_NAME = "openelisglobal"
DOCKER_OE_CONTAINER_NAME = "openelisglobal-webapp"
DOCKER_DB_CONTAINER_NAME = "openelisglobal-database"
DOCKER_DB_BACKUPS_DIR = "/backups/"  # path in docker container

SECRETS_MOUNT_POINT = OE_CONFIG_DIR + "secrets/"
DB_ENVIRONMENT_MOUNT_POINT = OE_CONFIG_DIR + "database/"
DB_BACKUPS_MOUNT_POINT = BACKUP_DIR  # path on host
DB_DATA_MOUNT_POINT = OE_VAR_DIR + "data/"  # path on host
DB_INIT_MOUNT_POINT = OE_VAR_DIR + DB_INIT_DIR
DB_FILES_MOUNT_POINT = OE_VAR_DIR + DB_DIR

#arguments set through command line
DOCKER_DB = False #DB installed through docker
MODE = "install"


def install():
    if not check_preconditions('install'):
        clean_exit()

    do_install()


def install_no_prompt():
    global NO_PROMPT
    NO_PROMPT = True
    install()


def update():
    if not check_preconditions('update'):
        log(APP_NAME + "is not an existing installation, can not update.\n", PRINT_TO_CONSOLE)
        clean_exit()

    do_update()

    # check if backup exists
    if not os.path.exists(BACKUP_DIR + BACKUP_SCRIPT_NAME):
        install_backup_task()


def uninstall():
    if not check_preconditions('uninstall'):
        log(APP_NAME + "is not an existing installation, can not update.\n", PRINT_TO_CONSOLE)
        clean_exit()

    do_uninstall()
    

def do_install():

    log("installing " + APP_NAME, PRINT_TO_CONSOLE)

    create_postgres_password()

    config_files_for_postgres()

    # prepare any site-specific information
    config_site_information()

    # add database users and install db
    log("installing database", PRINT_TO_CONSOLE)
    install_db()
    
    preserve_db_password()

    install_crosstab()

    log("installing docker", PRINT_TO_CONSOLE)
    install_docker()

    log("loading docker", PRINT_TO_CONSOLE)
    load_docker_image()

    log("starting docker containers", PRINT_TO_CONSOLE)
    start_docker_container()

    install_backup_task()

    # remove the files with passwords
    staging_files = glob.glob(STAGING_DIR + '*')
    for file in staging_files:
        cmd = "rm " + file
        os.system(cmd)


def install_db():
    if DOCKER_DB:
        ensure_dir_not_exists(DB_INIT_MOUNT_POINT)
        ensure_dir_not_exists(DB_FILES_MOUNT_POINT)
        
        shutil.copytree(DB_INIT_DIR, DB_INIT_MOUNT_POINT)
        os.chown(DB_INIT_MOUNT_POINT + '1-pgsqlPermissions.sql', 0, grp.getgrnam('docker')[2])
        shutil.copytree(DB_DIR, DB_FILES_MOUNT_POINT)
    else:
        cmd = 'su -c "createdb  clinlims" postgres'
        os.system(cmd)
        cmd = 'su -c "psql  <  ' + DB_INIT_DIR + '1-pgsqlPermissions.sql" postgres'
        os.system(cmd)
        cmd = 'su -c "psql clinlims  < ' + DB_INIT_DIR + 'OpenELIS-Global.sql" postgres'
        os.system(cmd)
        cmd = 'su -c "psql clinlims <  ' + DB_INIT_DIR + 'siteInfo.sql" postgres'
        os.system(cmd)


def install_backup_task():
    global SITE_ID
    if NO_PROMPT:
        pass
    else:
        if os.path.exists(BACKUP_DIR + BACKUP_SCRIPT_NAME):
            over_ride = raw_input("The backup script is already installed. Do you want to overwrite it? y/n ")
            if not over_ride.lower() == "y":
                return
    
    BACKUP_SCRIPT_TEMPLATE = TEMPLATE_DIR + BACKUP_SCRIPT_NAME 
    # make sure the template files for the backup exits
    if not os.path.exists(BACKUP_SCRIPT_TEMPLATE):
        log("Not installing backup. Script missing", PRINT_TO_CONSOLE)
        return
    else:
        log("Installing backup", True)

    # make sure curl is installed
    log("Checking for curl", True)
    if os.system('which curl') == 256:
        log('curl is not installed. http://curl.linux-mirror.org/', PRINT_TO_CONSOLE)
        return
    else:
        log("Curl found, continuing with backup installation", PRINT_TO_CONSOLE)
    if not NO_PROMPT:
        if len(SITE_ID) < 1:
            SITE_ID = raw_input("site number or identification for this lab: ")
            while len(SITE_ID) < 1:
                SITE_ID = raw_input(
                    "the site number is required to install the backup.  If you want to stop installing the backup use a site number of 'q': ")
                if SITE_ID.lower() == 'q':
                    return


      
    
    template_file = open(BACKUP_SCRIPT_TEMPLATE, "r")
    staging_file = open(STAGING_DIR + BACKUP_SCRIPT_NAME, "w")

    for line in template_file:
        if line.find("[% installName %]") > 0:
            line = line.replace("[% installName %]", APP_NAME)
        if line.find("[% siteId %]") > 0:
            line = line.replace("[% siteId %]", SITE_ID)
        #if line.find("[% postgres_password %]") > 0:
        #    line = line.replace("[% postgres_password %]", CLINLIMS_PWD)
        if line.find("[% db_install_type %]") > 0:
            if DOCKER_DB:
                line = line.replace("[% db_install_type %]", "docker")
            else:
                line = line.replace("[% db_install_type %]", "host")

        staging_file.write(line)

    template_file.close()
    staging_file.close()

    if not os.path.exists(BACKUP_DIR):
        os.makedirs(BACKUP_DIR, 0640)
    if not os.path.exists(BACKUP_DIR + "daily"):
        os.makedirs(BACKUP_DIR + "daily", 0640)
    if not os.path.exists(BACKUP_DIR + "cumulative"):
        os.makedirs(BACKUP_DIR + "cumulative", 0640)
    if not os.path.exists(BACKUP_DIR + "transmissionQueue"):
        os.makedirs(BACKUP_DIR + "transmissionQueue", 0640)

    shutil.copy(STAGING_DIR + BACKUP_SCRIPT_NAME, BACKUP_DIR + BACKUP_SCRIPT_NAME)
    os.chmod(BACKUP_DIR + BACKUP_SCRIPT_NAME, 0744)

    shutil.copy(CRON_FILE_DIR + CRON_FILE, CRON_INSTALL_DIR)

    return


# note There is a fair amount of copying files, it should be re-written using shutil
def install_crosstab():
    if not DOCKER_DB:
        log("Checking if crosstab is installed in Postgres", True)
    
        # this creates an empty file to write to
        result = open(STAGING_DIR + 'crosstabResult.txt', 'wr')
        result.close()
        os.chmod(STAGING_DIR + 'crosstabResult.txt', stat.S_IROTH | stat.S_IWOTH)
    
        cmd = cmd = 'sudo -u postgres psql -d clinlims -L ' + STAGING_DIR + 'crosstabResult.txt  -f' + CROSSTAB_DIR + 'crosstabCheck.sql'
        os.system(cmd)
    
        check_file = open(STAGING_DIR + 'crosstabResult.txt')
    
        for line in check_file:
            if line.find(EXPECTED_CROSSTAB_FUNCTIONS) != -1:
                log("Crosstabs are installed", True)
                return
    
        log("Installing crosstabs", True)
    
        # if tablefunc.so is not present in POSTGRES_LIB_DIR then copy it from CROSSTAB_DIR
        # the version in CROSSTAB_DIR is for 8.3 (assumes all new installs will be VM based)
        # if not os.path.exists(POSTGRES_LIB_DIR + "tablefunc.so") and "8.3" in POSTGRES_LIB_DIR:
        if not os.path.exists(POSTGRES_LIB_DIR + "tablefunc.so"):
            # copy the lib file to the lib directory
            shutil.copy(CROSSTAB_DIR + "tablefunc.so", POSTGRES_LIB_DIR)
            # run the installer
        cmd = cmd = 'sudo -u postgres psql -d clinlims -f ' + CROSSTAB_DIR + 'tablefunc.sql'
        os.system(cmd)


def install_docker():
    cmd = 'sudo ' + SCRIPTS_DIR + 'setupDocker.sh'
    os.system(cmd)
    setup_tomcat_docker()


def setup_tomcat_docker():
    cmd = 'sudo ' + SCRIPTS_DIR + 'setupTomcatDocker.sh'
    os.system(cmd)


def preserve_db_password():
    db_user_password_file = open(SECRETS_MOUNT_POINT + 'OE_DB_USER_PASSWORD', 'w')
    db_user_password_file.write(CLINLIMS_PWD)
    db_user_password_file.close()

    # own directory by tomcat user
    os.chown(SECRETS_MOUNT_POINT + 'OE_DB_USER_PASSWORD', 8443, 8443)
    os.chmod(SECRETS_MOUNT_POINT + 'OE_DB_USER_PASSWORD', 0640)


def do_update():
    log("Updating " + APP_NAME, PRINT_TO_CONSOLE)

    if not find_password():
        log("Unable to find password from secrets file. Exiting", PRINT_TO_CONSOLE)
        return

    backup_db()

    load_docker_image()

    start_docker_container()

    install_backup_task()

    time.sleep(10)

    log("Finished updating " + APP_NAME, PRINT_TO_CONSOLE)


def do_uninstall():
    if db_installed('clinlims'):
        backup_db()
        log("removing " + APP_NAME, PRINT_TO_CONSOLE)
        delete_docker_images()
        do_delete_backup = raw_input("Do you want to remove backupfiles from this machines y/n ")
        if do_delete_backup.lower() == 'y':
            delete_backup()

        delete_database()

    delete_backup_script()
    
    delete_program_files()


def delete_docker_images():
    if DOCKER_DB:
        log("stopping database docker image...", PRINT_TO_CONSOLE)
        cmd = 'docker rm $(docker stop $(docker ps -a -q --filter="name=' + DOCKER_DB_CONTAINER_NAME + '" --format="{{.ID}}"))'
        os.system(cmd)
    log("removing openelis docker image...", PRINT_TO_CONSOLE)
    cmd = 'docker rm $(docker stop $(docker ps -a -q --filter="name=' + DOCKER_OE_CONTAINER_NAME + '" --format="{{.ID}}"))'
    os.system(cmd)
    cmd = 'docker rmi $(docker images \'' + DOCKER_OE_REPO_NAME + '\' -q)'
    os.system(cmd)


def delete_backup():
    if os.path.exists(BACKUP_DIR):
        log("removing backup", PRINT_TO_CONSOLE)
        shutil.rmtree(BACKUP_DIR)

    if os.path.exists(CRON_INSTALL_DIR + CRON_FILE):
        log("removing crontab", PRINT_TO_CONSOLE)
        os.remove(CRON_INSTALL_DIR + CRON_FILE)


def delete_backup_script():
    if os.path.exists(BACKUP_DIR + BACKUP_SCRIPT_NAME):
        os.remove(BACKUP_DIR + BACKUP_SCRIPT_NAME)
        
        
def delete_program_files():
    if os.path.exists(OE_VAR_DIR):
        shutil.rmtree(OE_VAR_DIR)
    if os.path.exists(OE_CONFIG_DIR):
        shutil.rmtree(OE_CONFIG_DIR)


def delete_database():
    if DOCKER_DB:
        if os.path.exists(DB_DATA_MOUNT_POINT):
            log("removing database data", PRINT_TO_CONSOLE)
            shutil.rmtree(DB_DATA_MOUNT_POINT)
    else:
        log("Dropping clinlims database and OpenELIS database roles", PRINT_TO_CONSOLE)
        os.system('su -c "dropdb -e clinlims" postgres')
        os.system('su -c "dropuser -e clinlims" postgres')
        os.system('su -c "dropuser -e admin" postgres')


def create_postgres_password():
    global CLINLIMS_PWD
    CLINLIMS_PWD = ''.join(Random().sample(string.letters, 12))


def config_files_for_postgres():
    global ADMIN_PWD

    if NO_PROMPT:
        pass
    else:
        ADMIN_PWD = ''.join(Random().sample(string.letters, 12))
        print "This is the postgres admin password.  Please record it in a safe and private place."
        print "It will not be able to be recovered once this script is finished\n"
        print ADMIN_PWD
        print raw_input("\npress any key once you have recorded it")
        os.system('clear')

    # set values for database users
    pg_permissions = open(TEMPLATE_DIR + 'pgsql-permissions.sql')
    output_file = open(DB_INIT_DIR + '1-pgsqlPermissions.sql', 'w')
    for line in pg_permissions:
        if line.find('itechappPassword') > 0:
            line = line.replace('[% itechappPassword %]', CLINLIMS_PWD)
            output_file.write(line)
        elif line.find('adminPassword') > 0:
            line = line.replace('[% adminPassword %]', ADMIN_PWD)
            output_file.write(line)
        else:
            output_file.write(line)
    output_file.close()
    pg_permissions.close() 
    os.chmod(DB_INIT_DIR + '1-pgsqlPermissions.sql', 0640)

    # set values for database admin user if installing with docker
    if DOCKER_DB:
        database_environment = open(TEMPLATE_DIR + 'dockerDB/database.env')
        output_file = open(DB_ENVIRONMENT_MOUNT_POINT + 'database.env', 'w')
        for line in database_environment:
            if line.find('adminPassword') > 0:
                line = line.replace('[% adminPassword %]', ADMIN_PWD)
                output_file.write(line)
            else:
                output_file.write(line)
        output_file.close()
        database_environment.close()
        os.chmod(DB_ENVIRONMENT_MOUNT_POINT + 'database.env', 0640)


def check_preconditions(goal):
    """Checks if server is in right state for goal"""
    global POSTGRES_LIB_DIR

    if not DOCKER_DB:
        log("Checking for Postgres 8.3 or later installation", PRINT_TO_CONSOLE)
        if not check_postgres_preconditions():
            return False
        
    if goal == 'install':
        if (db_installed('clinlims')):
            log("\nThere is a currently installed version of OpenElis", PRINT_TO_CONSOLE)
            print "Please either do an uninstall or update first"
            write_help()
            return False
    elif goal == 'update':
        if (not db_installed('clinlims')):
            log("\nThere is no currently installed version of OpenElis", PRINT_TO_CONSOLE)
            print "Please either do an install first"
            write_help()
            return False
    elif goal == 'uninstall':
        if (not db_installed('clinlims')):
            log("\nThere is no currently installed version of OpenElis", PRINT_TO_CONSOLE)
            print "Nothing to uninstall"
            write_help()
            return False

    return True


def check_postgres_preconditions():
    global POSTGRES_LIB_DIR
    log("Checking for Postgres 8.3 or later installation", PRINT_TO_CONSOLE)
    os.system('psql --version > tmp')
    tmp_file = open('tmp')
    first_line = tmp_file.readline()
    tmp_file.close()

    if len(first_line) < 1:
        log("Postgress not installed.  Please install postgres8.3 or later", PRINT_TO_CONSOLE)
        return False

    split_line = first_line.split()
    version = split_line[2]
    split_version = version.split('.')

    valid = False

    if split_version[0].isdigit() and split_version[1].isdigit():
        major = int(split_version[0])
        minor = int(split_version[1])

        valid = major > 8 or (major == 8 and minor >= 3)

    if valid:
        log("Postgres" + str(major) + "." + str(minor) + " found!\n", PRINT_TO_CONSOLE)
        POSTGRES_LIB_DIR = "/usr/lib/postgresql/" + str(major) + "." + str(minor) + "/lib/"
        return True
    else:
        log("Postgres must be 8.3 or later\n", PRINT_TO_CONSOLE)
        return False
        
    
def db_installed(db_name):
    if DOCKER_DB:
        return os.path.isdir(DB_DATA_MOUNT_POINT)
    else:
        cmd = 'sudo -u postgres psql -c "SELECT datname FROM pg_catalog.pg_database WHERE lower(datname) = lower(\'' + db_name + '\');"'
        result = subprocess.check_output(cmd, shell=True)
        return db_name in result


def config_site_information():
    global SITE_ID

    # Get site specific information
    print """
    Some installations require configuuration.  
        You will be asked for specific information which may or may not be needed for this installation.
        If you do not know if it is needed or you do not know the correct value it may be left blank.
        You can set the values after the installation is complete.
    """
    if NO_PROMPT:
        pass
    else:
        site_file = open(DB_INIT_DIR + 'siteInfo.sql', 'w')
        SITE_ID = raw_input("site number for this lab (5 character): ")
        persist_site_information(site_file, 'siteNumber', 'The site number of the this lab', SITE_ID)
        persist_site_information(site_file, 'Accession number prefix',
                                 'Prefix for SITEYEARNUM format.  Can not be changed if there are samples', SITE_ID)
        site_file.close()


def persist_site_information(file, name, description, value):
    DELETE_PRE = 'DELETE from clinlims.site_information where name = \''
    DELETE_POST = '\';\n'

    INSERT_PRE = 'INSERT INTO clinlims.site_information( id, lastupdated, "name", description, "value") VALUES ( nextval(\'clinlims.site_information_seq\'), now(), \''
    INSERT_POST = ' );\n\n'

    delete_line = DELETE_PRE + name + DELETE_POST

    insert_value = 'null'
    if len(value) > 0:
        insert_value = '\'' + value + '\''
    insert_line = INSERT_PRE + name + '\', \'' + description + '\', ' + insert_value + INSERT_POST

    file.write(delete_line)
    file.write(insert_line)


def update_postgres_role():
    template_file = open(TEMPLATE_DIR + POSTGRES_ROLE_UPDATE_FILE_NAME, "r")
    staging_file = open(STAGING_DIR + POSTGRES_ROLE_UPDATE_FILE_NAME, "w")

    for line in template_file:
        if line.find("[% postgres_password %]") > 0:
            line = line.replace("[% postgres_password %]", CLINLIMS_PWD)

    staging_file.write(line)

    template_file.close()
    staging_file.close()
    #in case staging isn't deleted, make sure we're not storing password without protection
    os.chmod(STAGING_DIR + POSTGRES_ROLE_UPDATE_FILE_NAME, 0640)
    if DOCKER_DB:
        cmd = 'docker exec ' + DOCKER_DB_CONTAINER_NAME + ' psql -f ' + STAGING_DIR + POSTGRES_ROLE_UPDATE_FILE_NAME
        os.system(cmd)
    else:
        cmd = 'su -c "psql  <  ' + STAGING_DIR + POSTGRES_ROLE_UPDATE_FILE_NAME + '" postgres > /dev/null'
        os.system(cmd)


def recover_database():
    over_ride = raw_input("This will reset the database password.  Do you want to keep going? y/n ")
    if not over_ride.lower() == "y":
        return

    create_postgres_password()
    preserve_db_password()
    update_postgres_role()
    log("Updated postgres password in database and config file.", PRINT_TO_CONSOLE)


def load_docker_image():
    cmd = 'sudo docker load < ' + DOCKER_DIR + APP_NAME + '-' + VERSION + '.tar.gz'
    os.system(cmd)
    

def start_docker_container():
    get_docker_compose()
    cmd = 'sudo docker-compose up -d '
    os.system(cmd)


def get_docker_compose():
    if DOCKER_DB:
        shutil.copy(TEMPLATE_DIR + "dockerDB/docker-compose.yml", "docker-compose.yml")
    else:
        shutil.copy(TEMPLATE_DIR + "docker-compose.yml", "docker-compose.yml")
        
        
# def apply_separate_test_fix():
#     if not os.path.exists(FIX_DIR + "collapseTests/packages/python-pgsql_2.5.1-2+b2_i386.deb"):
#         log("Note: Not applying separateTestFix.", PRINT_TO_CONSOLE)
#         return

#     matches = glob.glob(os.path.join(APT_CACHE_DIR, "python-pgsql*"))

#     # The only systems this is being applied to are those where the module has not
#     # yet been installed so if it is installed it is assumed that the fix has been run already
#     if len(matches) > 0:
#         log("Separate Test Fix already run", PRINT_TO_CONSOLE)
#         return

#     log("Apply Separate Test Fix", PRINT_TO_CONSOLE)

#     packages = glob.glob(FIX_DIR + "collapseTests/packages/*")

#     for package in packages:
#         shutil.copy(package, APT_CACHE_DIR)

#     cmd = "dpkg -i " + FIX_DIR + "collapseTests/packages/*"
#     os.system(cmd)

#     cmd = "python " + FIX_DIR + "collapseTests/testReconfiguration_Linux.py -p " + CLINLIMS_PWD + " -d clinlims"
#     os.system(cmd)

#     log("Fix applied", PRINT_TO_CONSOLE)


def backup_db():
    action_time = get_action_time()
    backup_name = action_time + '/openElis.backup'
    
    if find_password():
        log("backing up database to " + ROLLBACK_DIR + backup_name, PRINT_TO_CONSOLE)
        if DOCKER_DB:
            ensure_dir_exists(DB_BACKUPS_MOUNT_POINT + action_time)
            os.system(
                'docker exec ' + DOCKER_DB_CONTAINER_NAME + ' /usr/bin/pg_dump -U clinlims -f "' 
                + DOCKER_DB_BACKUPS_DIR + backup_name + '" clinlims')
            if os.path.exists(DB_BACKUPS_MOUNT_POINT + backup_name):
                shutil.move(DB_BACKUPS_MOUNT_POINT + backup_name, ROLLBACK_DIR + backup_name)
            else:
                over_ride = raw_input("Database could not be backed up properly. Do you want to continue without a proper backup? y/n ")
                if not over_ride.lower() == "y":
                    clean_exit()  
        else:
            os.system(
                "PGPASSWORD=\"" + CLINLIMS_PWD + "\";export PGPASSWORD; su -c  'pg_dump -h localhost -U clinlims clinlims > " + ROLLBACK_DIR + backup_name + "'")
    else:
        log("can't back up database, missing password file ", PRINT_TO_CONSOLE)


def get_app_details():
    global APP_NAME, VERSION

    docker_files = glob.glob(DOCKER_DIR + '*.tar.gz')
    for file in docker_files:
        filename = get_file_name(file)
        if re.match('.*OpenELIS-Global-.*.tar.gz', filename):
            APP_NAME = ""
            filename_parts = filename.split('-')
            i = 0
            filename_parts_size = len(filename_parts)
            # get everything before final '-' (version delimiter) for APP_NAME
            while i < filename_parts_size - 1:
                APP_NAME = APP_NAME + filename_parts[i] + "-"
                i += 1                                   
            APP_NAME = APP_NAME[:-1]                 
            
            VERSION = filename_parts[len(filename_parts) - 1][:-7]  # strip .tar.gz off
            log("app name is: " + APP_NAME, PRINT_TO_CONSOLE)
            log("Version is: " + VERSION, PRINT_TO_CONSOLE)
            return


def find_password():
    global CLINLIMS_PWD
    try:
        config_file = open(SECRETS_MOUNT_POINT + 'OE_DB_USER_PASSWORD')

        for line in config_file:
            CLINLIMS_PWD = line
            return True
    except IOError:
        return False


def get_action_time():
    global ACTION_TIME

    if ACTION_TIME == '':
        ACTION_TIME = strftime("%Y_%m_%d-%H_%M_%S", time.localtime())
        cmd = 'mkdir ' + ROLLBACK_DIR + '/' + ACTION_TIME
        os.system(cmd)

    return ACTION_TIME


def ensure_dir_exists(dir):
    if not os.path.exists(dir):
        os.makedirs(dir)
        
def ensure_dir_not_exists(dir):
    if os.path.exists(dir):
        shutil.rmtree(dir)


def get_file_name(file):
    filename_parts = file.split('/')
    return filename_parts[len(filename_parts) - 1]

        
def check_on_writable_system():
    if not os.access('./', os.W_OK | os.X_OK):
        print("Unable to write to file system.  Please copy installer to hard disk")
        print("Exiting")
        exit()


def write_help():
    print """
setup_OpenELIS.py <options>
    This script must be run as sudo or else it will fail due to permission problems.

    <options>
        -d --dockerDB           -   use a docker database instead of a separate Postgres DB
        
        -m --mode <mode>        -   chose what mode you want to run in, default is install
        <mode>
            install             - Installs OpenELIS.  Assumes that there is not a partial install
            
            installNoPrompt     - Installs OpenELIS without prompting.  Assumes that there is not a partial install
            
            installBackup       - Installs just the backup.  Will overwrite any existing backup
            
            update              - Updates OpenElis.  Checks to insure that the instance being updated is the same as the installed
            
            uninstall           - Removes OpenELIS from the system optionally including the database. Make sure you have the clinlims password written down someplace
            
            recover             - Will try to recover the system if somebody has tried to fix the system manually.  It will re-install the war file and reset the database password
            
            version             - The version number of this installer
            
            help                - This screen
            
        -v --version            -   run in version mode
        
        -h --help               -   print help
        """


def write_version():
    log("\n", PRINT_TO_CONSOLE)
    log("------------------------------------", not PRINT_TO_CONSOLE)
    log("OpenELIS installer Version " + VERSION, PRINT_TO_CONSOLE)


def open_log_file():
    global LOG_FILE
    LOG_FILE = open(LOG_DIR + LOG_FILE_NAME, 'a')


def clean_exit():
    global LOG_FILE
    LOG_FILE.close()
    exit()


def log(message, to_console):
    LOG_FILE.write(message + "\n")
    if to_console:
        print message

def main(argv):   
    global MODE, DOCKER_DB     
    os.putenv("LANG", LANG_NAME)
    
    try:
        opts, args = getopt.getopt(argv,"dm:vh",["dockerDB","mode=", "version", "help"])
    except getopt.GetoptError:
        write_help()
        sys.exit(2)
            
    for opt, arg in opts:
        if opt in ("-d", "--dockerDB"):
            DOCKER_DB = True
        elif opt in ("-m","--mode"):
            MODE = arg
        elif opt in ("-v","--version"):
            MODE = "version"
        elif opt in ("-h","--help"):
            MODE = "help"
    
    check_on_writable_system()
    
    ensure_dir_exists(STAGING_DIR)
    ensure_dir_exists(LOG_DIR)
    ensure_dir_exists(ROLLBACK_DIR)
    ensure_dir_exists(OE_VAR_DIR)
    ensure_dir_exists(SECRETS_MOUNT_POINT)
    ensure_dir_exists(DB_ENVIRONMENT_MOUNT_POINT)
    open_log_file()
    get_app_details()
    write_version()
    
    if MODE == "installCrossTabs":
        log("installCrossTabs " + strftime("%a, %d %b %Y %H:%M:%S", gmtime()), not PRINT_TO_CONSOLE)
        install_crosstab()
        clean_exit()
    
    if MODE == "uninstall":
        log("uninstall " + strftime("%a, %d %b %Y %H:%M:%S", gmtime()), not PRINT_TO_CONSOLE)
        print "This will uninstall OpenELIS from this machine including **ALL** data from database"
        remove = raw_input("Do you want to continue with the uninstall? y/n: ")
        if remove.lower() == 'y':
            uninstall()
            clean_exit()
    
    if MODE == "update":
        log("update " + strftime("%a, %d %b %Y %H:%M:%S", gmtime()), not PRINT_TO_CONSOLE)
        update()
        clean_exit()
    
    if MODE == "installBackup":
        log("installBackup " + strftime("%a, %d %b %Y %H:%M:%S", gmtime()), not PRINT_TO_CONSOLE)
        find_password()
        install_backup_task()
        clean_exit()
    
    if MODE == "install":
        log("install " + strftime("%a, %d %b %Y %H:%M:%S", gmtime()), not PRINT_TO_CONSOLE)
        install()
        clean_exit()
    
    if MODE == "installNoPrompt":
        log("installNoPrompt " + strftime("%a, %d %b %Y %H:%M:%S", gmtime()), not PRINT_TO_CONSOLE)
        install_no_prompt()
        clean_exit()
    
    if MODE == "recover":
        log("recover " + strftime("%a, %d %b %Y %H:%M:%S", gmtime()), not PRINT_TO_CONSOLE)
        recover_database()
        clean_exit()
    
    if MODE == "version":
        # version already written
        clean_exit()
    
    # if all else fails give help
    write_help()
    
if __name__ == "__main__":
   main(sys.argv[1:])
