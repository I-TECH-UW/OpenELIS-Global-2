#!/usr/bin/env python
# -*- coding: utf-8 -*-

import glob
import os
import platform
import sys
import string
import shutil
import stat
import time
import re
from time import gmtime, strftime
from random import Random

VERSION = "8.4"
OS_VERSION = ''
TEMPLATE_DIR = "./templates/"
WAR_DIR = "./warFiles/"
STAGING_DIR = "./stagingFiles/"
LIQUIBASE_DIR = "./liquibase/"
DB_DIR = "./databaseFiles/"
CROSSTAB_DIR = "./crosstab/"
ROLLBACK_DIR = "./rollback/"
LOG_DIR = "./log/"
FIX_DIR = "./fixes/"
APT_CACHE_DIR = "/var/cache/apt/archives/"
EXPECTED_CROSSTAB_FUNCTIONS = "3"
BIN_DIR = "./bin/"
CRON_FILE_DIR = "./cronFiles/"
CRON_INSTALL_DIR = "/etc/cron.d/"
CRON_FILE = "openElis"
POSTGRES_LIB_DIR = "/usr/lib/postgresql/8.3/lib/"
BACKUP_DIR = os.getenv("HOME") + "/openElisBackup/"
BACKUP_SCRIPT_NAME = "DatabaseBackup.pl"
LOG_FILE_NAME = "installer.log"
LANG_NAME = "en_US.UTF-8"
POSTGRES_ROLE_UPDATE_FILE_NAME = "updateDBPassword.sql"
APP_NAME = ""
REPORT_IMAGE_PATH = "/WEB-INF/reports/images/"
PREVIEW_IMAGE_PATH = "/images/"
PLUGIN_PATH = "/plugin"
# maps the application name to the liquibase context name
APP_CONTEX_MAP = {'CDI': 'CDIRetroCI', 'LNSP_Haiti': 'haitiLNSP', 'haiti': 'haiti', 'CI_LNSP': 'ciLNSP',
                  'CI_IPCI': 'CI_IPCI', 'CDI_RegLab': 'ci_regional', 'Kenya': 'Kenya'}
CLINLIMS_PWD = ''
ADMIN_PWD = ''
SITE_ID = ''
LOG_FILE = ''
PRINT_TO_CONSOLE = True
ACTION_TIME = ''
TOMCAT_DIR = ''
TOMCAT_BASE = '/usr/share/tomcat'
TOMCAT_VERSION = ''
TOMCAT_INSTALL_DIR = '/usr/share/'
NO_PROMPT = False
TOMCAT_INSTALLED_AS_SERVICE = False
TOMCAT_AUTO_DEPLOY = True

# note There is a fair amount of copying files, it should be re-written using shutil
def install_crosstab():
    log("Checking if crosstab is installed in Postgres", True)

    # this creates an empty file to write to
    result = open(STAGING_DIR + 'crosstabResult.txt', 'wr')
    result.close()
    os.chmod(STAGING_DIR + 'crosstabResult.txt', stat.S_IROTH | stat.S_IWOTH)

    cmd = cmd = 'su -c "psql clinlims -L ' + STAGING_DIR + 'crosstabResult.txt <  ' + CROSSTAB_DIR + 'crosstabCheck.sql" postgres > /dev/null'
    os.system(cmd)

    check_file = open(STAGING_DIR + 'crosstabResult.txt')

    for line in check_file:
        if line.find(EXPECTED_CROSSTAB_FUNCTIONS) != -1:
            log("Crosstabs are installed", True)
            return

    log("Installing crosstabs", True)

    # if tablefunc.so is not present in POSTGRES_LIB_DIR then copy it from CROSSTAB_DIR
    # the version in CROSSTAB_DIR is for 8.3 (assumes all new installs will be VM based)
    #if not os.path.exists(POSTGRES_LIB_DIR + "tablefunc.so") and "8.3" in POSTGRES_LIB_DIR:
    if not os.path.exists(POSTGRES_LIB_DIR + "tablefunc.so"):
        # copy the lib file to the lib directory
        shutil.copy(CROSSTAB_DIR + "tablefunc.so", POSTGRES_LIB_DIR)
        # run the installer
    cmd = cmd = 'su -c "psql clinlims <  ' + CROSSTAB_DIR + 'tablefunc.sql" postgres'
    os.system(cmd)


def check_preconditions():
    """Checks that the correct versions of tomcat, postgres and jre are installed.  Note that the jre check is not very robust"""
    global TOMCAT_VERSION
    global TOMCAT_DIR
    global POSTGRES_LIB_DIR

    log("Checking for Tomcat 7.0 or later installation", PRINT_TO_CONSOLE)
    TOMCAT_DIR = get_tomcat_directory()
    TOMCAT_VERSION = TOMCAT_DIR.strip(TOMCAT_INSTALL_DIR)
    log("Tomcat found version = " + TOMCAT_VERSION, PRINT_TO_CONSOLE)

    log("Checking for Postgres 8.3 or later installation", PRINT_TO_CONSOLE)
    if not check_postgres_preconditions():
        return False

    log("Checking for correcting for correct java runtime", PRINT_TO_CONSOLE)

    # do rough check for debian what kind of configuration to do. This is the not a robust part
    jvm_six_path = "/usr/lib/jvm/java-6-sun/jre/bin/"

    if os.path.exists(jvm_six_path):
        update_alternative_path = "/usr/sbin/update-alternatives"
        if os.path.exists(update_alternative_path):
            set_java_path = update_alternative_path + " --set java " + jvm_six_path + "java"
            set_key_path = update_alternative_path + " --set keytool " + jvm_six_path + "keytool"

            os.system(set_java_path)
            os.system(set_key_path)

    return True


def current_installation():
    name = find_installed_name()
    return os.path.exists(TOMCAT_DIR + '/conf/Catalina/localhost/' + name + '.xml')


def get_tomcat_directory():
    names = glob.glob(TOMCAT_BASE + '[0-9].[0-9]')
    if names:
        version = names[0].strip(TOMCAT_BASE)
        split_version = version.split('.')
        major = int(split_version[0])
        minor = int(split_version[1])
        # check for version 7.0 or greater
        if major == 8 and minor == 0:
            log("Found " + names[0], PRINT_TO_CONSOLE)
            log("Deprecation waning: Tomcat version 8.0 is EoL", PRINT_TO_CONSOLE)
            return names[0]
        elif major >= 7 :
            log("Found " + names[0], PRINT_TO_CONSOLE)
            return names[0]
        else:
            log("Found " + names[0], PRINT_TO_CONSOLE)
            log("Tomcat must be version 7.0 or later\n", PRINT_TO_CONSOLE)
            return None
    names = glob.glob(TOMCAT_BASE + '[0-9]')
    if names:
        version = names[0].strip(TOMCAT_BASE)
        major = int(version)
        if major >= 7:
            log("Found " + names[0], PRINT_TO_CONSOLE)
            return names[0]
        else:
            log("Found " + names[0], PRINT_TO_CONSOLE)
            log("Tomcat must be version 7.0 or later\n", PRINT_TO_CONSOLE)
            return None
    return None
    
    
def get_tomcat_install_info():
    global TOMCAT_INSTALLED_AS_SERVICE
    global TOMCAT_AUTO_DEPLOY
    if os.path.isfile(TOMCAT_DIR + '/installInfo'):
        with open(TOMCAT_DIR + '/installInfo', 'r') as info_file:
            for line in info_file:
                if line.contains('init.d'):
                    TOMCAT_INSTALLED_AS_SERVICE = str.lower(line).contains('true')
                if line.contains('autoDeploy'):
                    TOMCAT_AUTO_DEPLOY = str.lower(line).contains('true')
    else:
        TOMCAT_INSTALLED_AS_SERVICE = True
        TOMCAT_AUTO_DEPLOY = True


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

    output_file = open(STAGING_DIR + 'pgsqlPermissions.sql', 'w')

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


def get_file_name(file):
    filename_parts = file.split('/')
    return filename_parts[len(filename_parts) - 1]


def find_installation_name():
    global APP_NAME

    war_files = glob.glob(WAR_DIR + '*.war')

    for file in war_files:
        filename = get_file_name(file)

        if re.match('.*OpenElis.war', filename):
            APP_NAME = filename.split('.')[0]
            return True

    return False


def find_installed_name():
    global TOMCAT_DIR
    global TOMCAT_VERSION
    TOMCAT_DIR = get_tomcat_directory()
    TOMCAT_VERSION = TOMCAT_DIR.strip(TOMCAT_INSTALL_DIR)
    war_files = glob.glob(TOMCAT_DIR + '/webapps/*.war')

    for file in war_files:
        filename = get_file_name(file)

        if re.match('.*OpenElis.war', filename):
            return filename.split('.')[0]

    return ''


def config_files_for_tomcat():
    openelis_template_file = open(TEMPLATE_DIR + 'openelis.xml')

    openelis_config_file = open(STAGING_DIR + APP_NAME + '.xml', 'w')

    for line in openelis_template_file:
        if line.find('itechappPassword') > 0:
            line = line.replace('[% itechappPassword %]', CLINLIMS_PWD)
        elif line.find('appname') > 0:
            openelis_line = line.replace('[% appname %]', APP_NAME)
            openelis_config_file.write(openelis_line)
            continue

        openelis_config_file.write(line)

    openelis_config_file.close()
    openelis_template_file.close()


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
        site_file = open(STAGING_DIR + 'siteInfo.sql', 'w')
        SITE_ID = raw_input("site number for this lab (5 character): ")
        persist_site_information(site_file, 'siteNumber', 'The site number of the this lab', SITE_ID)
        persist_site_information(site_file, 'Accession number prefix',
                                 'Prefix for SITEYEARNUM format.  Can not be changed if there are samples', SITE_ID)
        site_file.close()


def check_postgres_preconditions():
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


def install_db():
    cmd = 'su -c "psql  <  ' + STAGING_DIR + 'pgsqlPermissions.sql" postgres'
    os.system(cmd)
    cmd = 'su -c "psql clinlims  < ' + DB_DIR + 'databaseInstall.backup" postgres'
    os.system(cmd)
    cmd = 'su -c "psql clinlims <  ' + STAGING_DIR + 'siteInfo.sql" postgres'
    os.system(cmd)


def install_backup():
    global SITE_ID
    if NO_PROMPT:
        pass
    else:
        if os.path.exists(BACKUP_DIR + BACKUP_SCRIPT_NAME):
            over_ride = raw_input("The backup script is already installed. Do you want to overwrite it? y/n ")
            if not over_ride.lower() == "y":
                return

    # make sure the template files for the backup exits
    if not os.path.exists(TEMPLATE_DIR + BACKUP_SCRIPT_NAME):
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
    if APP_NAME == 'haitiOpenElis' or not NO_PROMPT:
        if len(SITE_ID) < 1:
            SITE_ID = raw_input("site number or identification for this lab: ")
            while len(SITE_ID) < 1:
                SITE_ID = raw_input(
                    "the site number is required to install the backup.  If you want to stop installing the backup use a site number of 'q': ")
                if SITE_ID.lower() == 'q':
                    return

    template_file = open(TEMPLATE_DIR + BACKUP_SCRIPT_NAME, "r")
    staging_file = open(STAGING_DIR + BACKUP_SCRIPT_NAME, "w")

    for line in template_file:
        if line.find("[% installName %]") > 0:
            line = line.replace("[% installName %]", APP_NAME)
        if line.find("[% siteId %]") > 0:
            line = line.replace("[% siteId %]", SITE_ID)
        if line.find("[% postgres_password %]") > 0:
            line = line.replace("[% postgres_password %]", CLINLIMS_PWD)

        staging_file.write(line)

    template_file.close()
    staging_file.close()

    if not os.path.exists(BACKUP_DIR):
        os.makedirs(BACKUP_DIR, 0755)
    if not os.path.exists(BACKUP_DIR + "daily"):
        os.makedirs(BACKUP_DIR + "daily", 0755)
    if not os.path.exists(BACKUP_DIR + "cumulative"):
        os.makedirs(BACKUP_DIR + "cumulative", 0755)
    if not os.path.exists(BACKUP_DIR + "transmissionQueue"):
        os.makedirs(BACKUP_DIR + "transmissionQueue", 0755)

    shutil.copy(STAGING_DIR + BACKUP_SCRIPT_NAME, BACKUP_DIR + BACKUP_SCRIPT_NAME)
    os.chmod(BACKUP_DIR + BACKUP_SCRIPT_NAME, 0777)

    shutil.copy(CRON_FILE_DIR + CRON_FILE, CRON_INSTALL_DIR)

    return


def update_with_liquibase():
    global LOG_FILE
    context = False

    app_names = APP_CONTEX_MAP.keys()

    for app in app_names:
        if APP_NAME.find(app) == 0:
            context = APP_CONTEX_MAP[app]
            break

    context_arg = ''
    if context:
        context_arg = "--contexts=" + context

    log("Updating database\n", True)
    LOG_FILE.flush()
    cmd = "java -jar  ./lib/liquibase-1.9.5.jar --defaultsFile=liquibaseInstall.properties " + context_arg + \
          " --password=" + CLINLIMS_PWD + " --username=clinlims  update >> ../" + LOG_DIR + LOG_FILE_NAME + " 2>&" + str(
        LOG_FILE.fileno())

    print "The following command may take a few minutes to complete.  Please take a short break until it finishes"
    print cmd

    current_dir = os.getcwd()
    os.chdir(LIQUIBASE_DIR)
    result = os.system(cmd)

    if result > 0:
        log("Error running database update", PRINT_TO_CONSOLE)
        log("Re running with diagnostics", PRINT_TO_CONSOLE)
        log("If the issue is reported as an authentication problem run the installer with the '-recover' option", PRINT_TO_CONSOLE)
        print "For further information please check the log file " + LOG_DIR + LOG_FILE_NAME
        LOG_FILE.flush()
        cmd = "java -jar  ./lib/liquibase-1.9.5.jar --defaultsFile=liquibaseInstall.properties " + context_arg + \
              " --password=" + CLINLIMS_PWD + " --username=clinlims --logLevel=fine update  >> ../" + LOG_DIR + LOG_FILE_NAME + " 2>&" + str(
            LOG_FILE.fileno())
        result = os.system(cmd)

        if result > 0:
            os.chdir(current_dir)
            rollback()
            clean_exit()

    os.chdir(current_dir)


def install_no_prompt():
    global NO_PROMPT
    NO_PROMPT = True
    set_environment_variables()
    install()


def update_postgres_role():
    template_file = open(TEMPLATE_DIR + POSTGRES_ROLE_UPDATE_FILE_NAME, "r")
    staging_file = open(STAGING_DIR + POSTGRES_ROLE_UPDATE_FILE_NAME, "w")

    for line in template_file:
        if line.find("[% postgres_password %]") > 0:
            line = line.replace("[% postgres_password %]", CLINLIMS_PWD)

    staging_file.write(line)

    template_file.close()
    staging_file.close()

    cmd = 'su -c "psql  <  ' + STAGING_DIR + POSTGRES_ROLE_UPDATE_FILE_NAME +  '" postgres > /dev/null'
    os.system(cmd)


def recover_database():

    over_ride = raw_input("This will reset the database password and may install a new war file.  Do you want to keep going? y/n ")
    if not over_ride.lower() == "y":
        return

    find_installed_name()
    find_installation_name()

    stop_tomcat()

    filename = APP_NAME + '.war'
    if os.path.exists(TOMCAT_DIR + '/webapps/' + filename):
        log("Using existing war file " + filename, PRINT_TO_CONSOLE)
    else:
        copy_war_file_to_tomcat()
        log("Installing " + filename + " from installer", PRINT_TO_CONSOLE)

    create_postgres_password()
    config_files_for_tomcat()
    copy_tomcat_config_file()
    update_postgres_role()
    log("Updated postgres password in database and config file.", PRINT_TO_CONSOLE)
    start_tomcat()

    
def set_environment_variables():
    os.environ["LC_ALL"] = ""
    os.environ["LC_CTYPE"] = ""


def install():
    set_environment_variables()
    name = find_installed_name()
    if len(name) > 0:
        log("\nThere is a currently installed version of OpenElis: " + name, PRINT_TO_CONSOLE)
        print "Please either do an uninstall or update first"
        write_help()
        clean_exit()

    if not check_preconditions():
        clean_exit()

    do_install()


def stop_tomcat():
    try_count = 1
    if TOMCAT_INSTALLED_AS_SERVICE:
        log('/etc/init.d/' + TOMCAT_VERSION + ' stop', PRINT_TO_CONSOLE)
        cmd = '/etc/init.d/' + TOMCAT_VERSION + ' stop'
        os.system(cmd)
    else:
        cmd = 'su -c "' + TOMCAT_DIR + '/bin/shutdown.sh" tomcat'
        os.system(cmd)


def start_tomcat():
    #TO DO move to init.d script
    if TOMCAT_INSTALLED_AS_SERVICE:
        cmd = '/etc/init.d/' + TOMCAT_VERSION + ' start'
        os.system(cmd)
    else:
        cmd = 'su -c "' + TOMCAT_DIR + '/bin/startup.sh" tomcat'
        os.system(cmd)


def restart_tomcat():
    #TO DO move to init.d script
    if TOMCAT_INSTALLED_AS_SERVICE:
        cmd = '/etc/init.d/' + TOMCAT_VERSION + ' restart'
        os.system(cmd)
    else:
        cmd = 'su -c "' + TOMCAT_DIR + '/bin/shutdown.sh" tomcat'
        os.system(cmd)
        time.sleep(5)
        cmd = 'su -c "' + TOMCAT_DIR + '/bin/startup.sh" tomcat'
        os.system(cmd)


def copy_war_file_to_tomcat():
    if TOMCAT_AUTO_DEPLOY:
        cmd = 'cp ' + WAR_DIR + APP_NAME + '.war ' + TOMCAT_DIR + '/webapps/'
        log(cmd, PRINT_TO_CONSOLE)
        os.system(cmd)
    else:
        cmd = 'cp ' + WAR_DIR + APP_NAME + '.war ' + TOMCAT_DIR + '/webapps/OpenELIS.war'
        log(cmd, PRINT_TO_CONSOLE)
        os.system(cmd)


def backup_war_file():
    log("Backing up application to " + ROLLBACK_DIR + get_action_time() + '/' + APP_NAME + '.war ', PRINT_TO_CONSOLE)
    cmd = 'cp ' + TOMCAT_DIR + '/webapps/' + APP_NAME + '.war ' + ROLLBACK_DIR + get_action_time() + '/' + APP_NAME + '.war'
    os.system(cmd)


def backup_plugins():
    if os.path.isdir(TOMCAT_DIR + '/webapps/' + APP_NAME + PLUGIN_PATH):
        log("Backing up plugins to " + ROLLBACK_DIR + get_action_time() + PLUGIN_PATH, PRINT_TO_CONSOLE)
        shutil.copytree(TOMCAT_DIR + '/webapps/' + APP_NAME + PLUGIN_PATH, ROLLBACK_DIR + get_action_time() + '/plugin')
    else:
        log("This version of openELIS does not support plugins, can not backup", PRINT_TO_CONSOLE)


def restore_plugins():
    if os.path.isdir(ROLLBACK_DIR + get_action_time() + '/plugin/') and len(
            os.listdir(ROLLBACK_DIR + get_action_time() + '/plugin/')) > 1:
        log("Restoring " + ROLLBACK_DIR + get_action_time() + PLUGIN_PATH, PRINT_TO_CONSOLE)
        cmd = 'cp -r ' + ROLLBACK_DIR + get_action_time() + '/plugin/* ' + TOMCAT_DIR + '/webapps/' + APP_NAME + PLUGIN_PATH
        os.system(cmd)
        log("Restarting tomcat to install plugins into OpenElis", PRINT_TO_CONSOLE)
        return True
    else:
        log("No plugins to restore", PRINT_TO_CONSOLE)
        return False


def delete_war_file_from_tomcat(name):
    war_path = TOMCAT_DIR + '/webapps/' + name + '.war'
    os.remove(war_path)


def delete_app_directory_from_tomcat(name):
    cmd = 'rm -r ' + TOMCAT_DIR + '/webapps/' + name + '/'
    os.system(cmd)


def delete_work_directory_from_tomcat(name):
    cmd = 'rm -r ' + TOMCAT_DIR + '/work/Catalina/localhost/' + name + '/'
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


def do_install():
    find_installation_name()

    log("installing " + APP_NAME, PRINT_TO_CONSOLE)

    create_postgres_password()
    config_files_for_postgres()

    # prepare any site-specific information
    print APP_NAME
    if APP_NAME == 'haitiOpenElis' or APP_NAME == 'CDI_RegLabOpenElis':
        config_site_information()

    # add database users and install db
    install_db()

    # set values for tomcat
    config_files_for_tomcat()

    # run liquibase for any last minute updates
    update_with_liquibase()

    stop_tomcat()

    # copy postgres jar file 
    split_version = OS_VERSION[1].split('.')
    major = int(split_version[0])
    minor = int(split_version[1])
    if major < 16:
        postgres_file = glob.glob(BIN_DIR + 'postgresql*3*')
    if major >= 16:
        postgres_file = glob.glob(BIN_DIR + 'postgresql*4*')
    cmd = 'cp ' + postgres_file[0] + ' ' + TOMCAT_DIR + '/lib/'
    os.system(cmd)

    copy_tomcat_config_file()

    copy_war_file_to_tomcat()

    start_tomcat()

    install_backup()

    # remove the files with passwords
    staging_files = glob.glob(STAGING_DIR + '*')
    for file in staging_files:
        cmd = "rm " + file
        os.system(cmd)


def create_postgres_password():
    global CLINLIMS_PWD
    CLINLIMS_PWD = ''.join(Random().sample(string.letters, 12))


def copy_tomcat_config_file():
    cmd = 'cp ' + STAGING_DIR + APP_NAME + '.xml ' + TOMCAT_DIR + '/conf/Catalina/localhost/'
    os.system(cmd)
    cmd = 'chown tomcat:tomcat ' + TOMCAT_DIR + '/conf/Catalina/localhost/' + APP_NAME + '.xml'
    os.system(cmd)


def do_uninstall():
    global APP_NAME

    log("checking for current installation", PRINT_TO_CONSOLE)

    installed_name = find_installed_name()

    if len(installed_name) == 0:
        log("Unable to find installed OpenELIS in tomcat", PRINT_TO_CONSOLE)
        print "The database can **NOT** be backed up\n"
        remove_db = raw_input("Do you want to remove the database? y/n")
        if remove_db.lower() == 'y':
            log("User selected to remove database", not PRINT_TO_CONSOLE)
            delete_database()
        return

    if not find_installation_name():
        log('Unable to find name of application in installer, exiting', PRINT_TO_CONSOLE)
        return

    if not APP_NAME == installed_name:
        log('Installed name: ' + installed_name + " does not match name in installer: " + APP_NAME, PRINT_TO_CONSOLE)
        remove = raw_input("Do you want to remove " + installed_name + "? y/n")
        if not (remove.lower() == 'y'):
            return
    else:
        remove = raw_input("Do you want to remove " + APP_NAME + " from tomcat? y/n ")
        if not remove.lower() == 'y':
            return

    APP_NAME = installed_name

    stop_tomcat()

    backup_db()

    backup_war_file()

    backup_config_file()

    log("removing " + APP_NAME, PRINT_TO_CONSOLE)

    config_file = TOMCAT_DIR + '/conf/Catalina/localhost/' + APP_NAME + '.xml'
    if not os.path.isfile(config_file):
        log("unable to find config file, continuing", not PRINT_TO_CONSOLE)
    else:
        os.remove(config_file)

    delete_war_file_from_tomcat(APP_NAME)

    delete_app_directory_from_tomcat(APP_NAME)

    do_delete_backup = raw_input("Do you want to remove backupfiles from this machines y/n ")
    if do_delete_backup.lower() == 'y':
        delete_backup()

    delete_backup_script()

    delete_database()

    start_tomcat()


def delete_database():
    log("Dropping clinlims database and OpenELIS database roles", PRINT_TO_CONSOLE)

    os.system('su -c "dropdb -e clinlims" postgres')
    os.system('su -c "dropuser -e clinlims" postgres')
    os.system('su -c "dropuser -e admin" postgres')


def check_update_preconditions():
    if not check_postgres_preconditions():
        return False

    global TOMCAT_DIR
    global TOMCAT_VERSION
    TOMCAT_DIR = get_tomcat_directory()
    TOMCAT_VERSION = TOMCAT_DIR.strip(TOMCAT_INSTALL_DIR)

    # get name of existing war files
    webapps = glob.glob(TOMCAT_DIR + '/webapps/*.war')

    # get name of new war file
    find_installation_name()

    matching_war = APP_NAME + ".war"
    # return their compare value
    for file in webapps:
        if matching_war == get_file_name(file):
            return True

    return False


def find_password(app_name):
    global CLINLIMS_PWD

    config_file = open(TOMCAT_DIR + '/conf/Catalina/localhost/' + app_name + '.xml')

    for line in config_file:
        password_index = line.find("password")
        if password_index > 0:
            password_values = line[password_index:].split("\"")
            CLINLIMS_PWD = password_values[1]
            return True


def apply_separate_test_fix():
    if not os.path.exists(FIX_DIR + "collapseTests/packages/python-pgsql_2.5.1-2+b2_i386.deb"):
        log("Note: Not applying separateTestFix.", PRINT_TO_CONSOLE)
        return

    matches = glob.glob(os.path.join(APT_CACHE_DIR, "python-pgsql*"))

    # The only systems this is being applied to are those where the module has not
    # yet been installed so if it is installed it is assumed that the fix has been run already
    if len(matches) > 0:
        log("Separate Test Fix already run", PRINT_TO_CONSOLE)
        return

    log("Apply Separate Test Fix", PRINT_TO_CONSOLE)

    packages = glob.glob(FIX_DIR + "collapseTests/packages/*")

    for package in packages:
        shutil.copy(package, APT_CACHE_DIR)

    cmd = "dpkg -i " + FIX_DIR + "collapseTests/packages/*"
    os.system(cmd)

    cmd = "python " + FIX_DIR + "collapseTests/testReconfiguration_Linux.py -p " + CLINLIMS_PWD + " -d clinlims"
    os.system(cmd)

    log("Fix applied", PRINT_TO_CONSOLE)


def get_action_time():
    global ACTION_TIME

    if ACTION_TIME == '':
        ACTION_TIME = strftime("%Y_%m_%d-%H_%M_%S", time.localtime())
        cmd = 'mkdir ' + ROLLBACK_DIR + '/' + ACTION_TIME
        os.system(cmd)

    return ACTION_TIME


def backup_db():
    backup_name = get_action_time() + '/openElis.backup'
    find_password(find_installed_name())
    log("backing up database to " + ROLLBACK_DIR + backup_name, PRINT_TO_CONSOLE)
    os.system(
        "PGPASSWORD=\"" + CLINLIMS_PWD + "\";export PGPASSWORD; su -c  'pg_dump -h localhost -U clinlims clinlims > " + ROLLBACK_DIR + backup_name + "'")


def backup_config_file():
    app_name = find_installed_name()
    backup_name = get_action_time() + '/' + app_name + '.xml'

    log("Backing up configuration to " + ROLLBACK_DIR + backup_name, PRINT_TO_CONSOLE)
    cmd = 'cp ' + TOMCAT_DIR + '/conf/Catalina/localhost/' + app_name + ".xml " + ROLLBACK_DIR + backup_name
    os.system(cmd)


def do_update():
    log("Updating " + APP_NAME, PRINT_TO_CONSOLE)

    if not find_password(APP_NAME):
        log("Unable to find password from configuration file. Exiting", PRINT_TO_CONSOLE)
        return

    stop_tomcat()

    backup_db()

    backup_war_file()

    backup_config_file()

    backup_plugins()

    delete_app_directory_from_tomcat(APP_NAME)

    delete_work_directory_from_tomcat(APP_NAME)

    copy_war_file_to_tomcat()

    update_with_liquibase()

    # apply_separate_test_fix() -- no longer needed 2.4 or later

    install_backup()

    start_tomcat()

    time.sleep(10)

    if restore_plugins():
        restart_tomcat()

    log("Finished updating " + APP_NAME, PRINT_TO_CONSOLE)


def update():
    if not check_update_preconditions():
        log(APP_NAME + "is not an existing installation, can not update.\n", PRINT_TO_CONSOLE)
        webapps = glob.glob(TOMCAT_DIR + '/webapps/*.war')
        clean_exit()

    do_update()

    # check if backup exists
    if not os.path.exists(BACKUP_DIR + BACKUP_SCRIPT_NAME):
        install_backup()


def ensure_dir_exists(dir):
    if not os.path.exists(dir):
        os.mkdir(dir)

        
def setup_tomcatInstallInfo():
    if os.path.isfile(CATALINA_HOME + '/installInfo'):
        with open(CATALINA_HOME + '/installInfo', 'r') as info_file:
            for line in info_file:
                if 'init.d' in line:
                    TOMCAT_INSTALLED_AS_SERVICE = '=true' in str.lower(line)
                if 'autoDeploy' in line:
                    TOMCAT_AUTO_DEPLOY = '=true' in str.lower(line)

                    
def rollback():
    log("\nRolling back to previous installation", PRINT_TO_CONSOLE)

    if not os.path.exists(ROLLBACK_DIR + get_action_time() + '/' + APP_NAME + '.war'):
        if os.path.exists(TOMCAT_DIR + '/webapps/' + APP_NAME + '.war'):
            log("Application still installed, nothing more to do.", PRINT_TO_CONSOLE)
        else:
            log("Unable to restore application.  File missing " + ROLLBACK_DIR + APP_NAME + '.war', PRINT_TO_CONSOLE)
    else:
        cmd = 'cp ' + ROLLBACK_DIR + APP_NAME + '.war ' + TOMCAT_DIR + '/webapps/'
        os.system(cmd)
        log("\nRollback finished", PRINT_TO_CONSOLE)

    log("Please report errors to http://groups.google.com/group/openelisglobal", PRINT_TO_CONSOLE)
    log("or https://openelis.cirg.washington.edu/OpenElisInfo/#contact \n", PRINT_TO_CONSOLE)

    start_tomcat()


def check_on_writable_system():
    if not os.access('./', os.W_OK | os.X_OK):
        print("Unable to write to file system.  Please copy installer to hard disk")
        print("Exiting")
        exit()


def write_help():
    print """
setup_OpenELIS.py <options>
    This script must be run as sudo or else it will fail due to permission problems.

-install  - Installs OpenELIS.  Assumes that there is not a partial install

-installNoPrompt  - Installs OpenELIS without prompting.  Assumes that there is not a partial install

-installBackup - Installs just the backup.  Will overwrite any existing backup

-installCrossTabs - Install just the crosstab functionality into Postgress to allow export to work

-update - Updates OpenElis.  Checks to insure that the instance being updated is the same as the installed

-uninstall - Removes OpenELIS from the system optionally including the database. Make sure you have the clinlims password written down someplace

-recover - Will try to recover the system if somebody has tried to fix the system manually.  It will re-install the war file and reset the database password

-version - The version number of this installer

-help - This screen
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

        
def get_os_tomcat_dir():
    global TOMCAT_BASE
    global TOMCAT_INSTALL_DIR
    global OS_VERSION
    OS_VERSION = platform.linux_distribution()
    print("Release detected: " + OS_VERSION[1])
    split_version = OS_VERSION[1].split('.')
    major = int(split_version[0])
    minor = int(split_version[1])

    if major < 16:
        TOMCAT_BASE = "/var/lib/tomcat"
        TOMCAT_INSTALL_DIR = "/var/lib/"
    if major >= 16:
        TOMCAT_BASE = "/usr/share/tomcat"
        TOMCAT_INSTALL_DIR = "/usr/share/"
# Main entry point


if len(sys.argv) > 1:

    os.putenv("LANG", LANG_NAME)

    check_on_writable_system()

    ensure_dir_exists(STAGING_DIR)
    ensure_dir_exists(LOG_DIR)
    ensure_dir_exists(ROLLBACK_DIR)
    open_log_file()
    write_version()
    get_os_tomcat_dir()
    get_tomcat_install_info()

    arg = sys.argv[1]

    if arg == "-installCrossTabs":
        log("installCrossTabs " + strftime("%a, %d %b %Y %H:%M:%S", gmtime()), not PRINT_TO_CONSOLE)
        install_crosstab()
        clean_exit()

    if arg == "-uninstall":
        log("uninstall " + strftime("%a, %d %b %Y %H:%M:%S", gmtime()), not PRINT_TO_CONSOLE)
        print "This will uninstall OpenELIS from this machine including **ALL** data from database"
        remove = raw_input("Do you want to continue with the uninstall? y/n: ")
        if remove.lower() == 'y':
            do_uninstall()
        clean_exit()

    if arg == "-update":
        log("update " + strftime("%a, %d %b %Y %H:%M:%S", gmtime()), not PRINT_TO_CONSOLE)
        update()
        install_crosstab()
        # find_installation_name()
        # find_password()
        # apply_separate_test_fix()
        clean_exit()

    if arg == "-installBackup":
        log("installBackup " + strftime("%a, %d %b %Y %H:%M:%S", gmtime()), not PRINT_TO_CONSOLE)
        find_installation_name()
        find_password(APP_NAME)
        install_backup()
        clean_exit()

    if arg == "-install":
        log("install " + strftime("%a, %d %b %Y %H:%M:%S", gmtime()), not PRINT_TO_CONSOLE)
        install()
        install_crosstab()
        clean_exit()

    if arg == "-installNoPrompt":
        log("installNoPrompt " + strftime("%a, %d %b %Y %H:%M:%S", gmtime()), not PRINT_TO_CONSOLE)
        install_no_prompt()
        install_crosstab()
        clean_exit()

    if arg == "-recover":
        log("recover " + strftime("%a, %d %b %Y %H:%M:%S", gmtime()), not PRINT_TO_CONSOLE)
        recover_database()
        clean_exit()

    if arg == "-version":
        # version already written
        clean_exit()

    # if all else fails give help
    write_help()
    clean_exit()

# default is help
write_help()
