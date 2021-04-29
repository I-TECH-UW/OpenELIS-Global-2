#!/usr/bin/env python
# -*- coding: utf-8 -*-

import getopt
import grp
import glob
import os
import subprocess
import sys
import string
import shutil
import stat
import time
import re
from time import gmtime, strftime
import random
import ConfigParser
from string import letters
from getpass import getpass

#About
VERSION = ""
APP_NAME = "OpenELIS-Global"
LANG_NAME = "en_US.UTF-8"

#Installer directories' names
INSTALLER_CROSSTAB_DIR = "./crosstab/"
INSTALLER_DB_INIT_DIR = "./initDB/"
INSTALLER_DOCKER_DIR = "./dockerImage/"
INSTALLER_LOG_DIR = "./log/"
INSTALLER_ROLLBACK_DIR = "./rollback/"
INSTALLER_SCRIPTS_DIR = "./scripts/"
INSTALLER_STAGING_DIR = "./stagingFiles/"
INSTALLER_TEMPLATE_DIR = "./templates/"

#File Names
BACKUP_SCRIPT_NAME = "DatabaseBackup.pl"
CRON_FILE_NAME = "openElis"
LOG_FILE_NAME = "installer.log"
POSTGRES_ROLE_UPDATE_FILE_NAME = "updateDBPassword.sql"
SETUP_CONFIG_FILE_NAME = "setup.ini"
CLIENT_FACING_KEYSTORE = "client_facing_keystore"
KEYSTORE = "keystore"
TRUSTSTORE = "truststore"

#install directories
OE_VAR_DIR = "/var/lib/openelis-global/"
OE_ETC_DIR = "/etc/openelis-global/"
#Install sub-directories (if running in docker, these directories will be mounted by the docker container)
DB_BACKUPS_DIR = OE_VAR_DIR + "backups/"  
DB_DATA_DIR = OE_VAR_DIR + "data/"  
DB_ENVIRONMENT_DIR = OE_VAR_DIR + "database/env/"
DB_INIT_DIR = OE_VAR_DIR + "initDB/"
SECRETS_DIR = OE_VAR_DIR + "secrets/"
PLUGINS_DIR = OE_VAR_DIR + "plugins/"
CONFIG_DIR = OE_VAR_DIR + "config/"
LOGS_DIR = OE_VAR_DIR + "logs/"
TOMCAT_LOGS_DIR = OE_VAR_DIR + "tomcatLogs/"
CRON_INSTALL_DIR = "/etc/cron.d/"

#full file paths
CLIENT_FACING_KEYSTORE_PATH = OE_ETC_DIR + CLIENT_FACING_KEYSTORE
KEYSTORE_PATH = OE_ETC_DIR + KEYSTORE
TRUSTSTORE_PATH = OE_ETC_DIR + TRUSTSTORE

#database variables
DB_HOST="localhost"
DB_HOST_FOR_DOCKER_SERVICES="172.17.0.1" #docker containers can't point to 'localhost' without a loopback
DB_PORT="5432"

#Docker variables
DOCKER_OE_REPO_NAME = "openelisglobal" #must match docker image name (not container name)
DOCKER_OE_CONTAINER_NAME = "openelisglobal-webapp" 
DOCKER_FHIR_API_CONTAINER_NAME = "external-fhir-api"
DOCKER_DB_CONTAINER_NAME = "openelisglobal-database" 
DOCKER_DB_BACKUPS_DIR = "/backups/"  # path in docker container
DOCKER_DB_HOST_PORT = "5432"

#Behaviour variables
DOCKER_DB = False 
LOCAL_DB = True
PRINT_TO_CONSOLE = True
MODE = "update-install"

POSTGRES_LIB_DIR = "/usr/lib/postgresql/12/lib/"
POSTGRES_MAIN_DIR = "/etc/postgresql/12/main/"
EXPECTED_CROSSTAB_FUNCTIONS = "3"

#Generated values
CLINLIMS_PWD = ''
ADMIN_PWD = ''
ACTION_TIME = ''

#Get from user values
SITE_ID = ''
KEYSTORE_PWD = ''
TRUSTSTORE_PWD = ''
ENCRYPTION_KEY = ''
LOCAL_FHIR_SERVER_ADDRESS = 'https://fhir.openelis.org:8443/fhir/'
REMOTE_FHIR_SOURCE = 'https://isanteplusdemo.com/openmrs/ws/fhir2/'
REMOTE_FHIR_SOURCE_UPDATE_STATUS = "false"
CONSOLIDATED_SERVER_ADDRESS = 'https://hub.openelisci.org:8444/fhir'
TIMEZONE = ''

#Stateful objects
LOG_FILE = ''


def write_help():
    print """
setup_OpenELIS.py <options>
    This script must be run as sudo or else it will fail due to permission problems.

    <options>
        -m --mode <mode>        -   chose what mode you want to run in, default is install
        <mode>
            update-install      - Installs OpenELIS or updates if already installed (default option)
            
            install             - Installs OpenELIS.  Assumes that there is not a partial install
            
            installBackup       - Installs just the backup.  Will overwrite any existing backup
            
            update              - Updates OpenElis.  Checks to insure that the instance being updated is the same as the installed
            
            uninstall           - Removes OpenELIS from the system optionally including the database. Make sure you have the clinlims password written down someplace
            
            recover             - Will try to recover the system if somebody has tried to fix the system manually.  It will reset the database password
            
        -v --version            -   run in version mode
        
        -h --help               -   print help
        """


#this method is the starting point of the script (called at bottom of page)
def main(argv):   
    global MODE, DOCKER_DB     
    os.putenv("LANG", LANG_NAME)
    check_on_writable_system()
    open_log_file()
    
    try:
        opts, args = getopt.getopt(argv,"m:vh",["mode=", "version", "help"])
    except getopt.GetoptError:
        write_help()
        sys.exit(2)
            
    for opt, arg in opts:
        if opt in ("-m","--mode"):
            MODE = arg
        elif opt in ("-v","--version"):
            write_version()
            return
        elif opt in ("-h","--help"):
            write_help()()
            return
          
    get_app_details()
    write_version()
    
    write_setup_properties_file()
    read_setup_properties_file()
    ensure_dir_exists(INSTALLER_STAGING_DIR)
    ensure_dir_exists(INSTALLER_ROLLBACK_DIR)
        
    #default mode
    if MODE == "update-install":
        if check_preconditions('install'):
            MODE = "install"
        elif check_preconditions('update'):
            MODE = "update"
        
    if MODE == "install":
        log("install " + strftime("%a, %d %b %Y %H:%M:%S", gmtime()), not PRINT_TO_CONSOLE)
        install()
    
    elif MODE == "installCrossTabs":
        log("installCrossTabs " + strftime("%a, %d %b %Y %H:%M:%S", gmtime()), not PRINT_TO_CONSOLE)
        install_crosstab()
    
    elif MODE == "uninstall":
        log("uninstall " + strftime("%a, %d %b %Y %H:%M:%S", gmtime()), not PRINT_TO_CONSOLE)
        print "This will uninstall OpenELIS from this machine including **ALL** data from database"
        remove = raw_input("Do you want to continue with the uninstall? y/n: ")
        if remove.lower() == 'y':
            uninstall()
    
    elif MODE == "update":
        log("update " + strftime("%a, %d %b %Y %H:%M:%S", gmtime()), not PRINT_TO_CONSOLE)
        update()
    
    elif MODE == "installBackup":
        log("installBackup " + strftime("%a, %d %b %Y %H:%M:%S", gmtime()), not PRINT_TO_CONSOLE)
        find_password()
        install_backup_task()
    
    elif MODE == "recover":
        log("recover " + strftime("%a, %d %b %Y %H:%M:%S", gmtime()), not PRINT_TO_CONSOLE)
        recover_database()
        
    else: # if all else fails give help
        write_help()
        
    # remove files that potentially contain passwords
    ensure_dir_not_exists(INSTALLER_STAGING_DIR)
    log("done", PRINT_TO_CONSOLE)
    clean_exit()



#---------------------------------------------------------------------
#             INSTALL
#---------------------------------------------------------------------
def install():
    if not check_preconditions('install'):
        print "Please either do an uninstall or update first"
        clean_exit()
    do_install()
        
    
def do_install():

    log("installing " + APP_NAME, PRINT_TO_CONSOLE)
    
    generate_passwords()
    
    get_install_user_values()
    
    get_stored_user_values()
    
    get_non_stored_user_values()

    install_docker()
    
    install_files_from_templates()
    
    install_site_info_config_file()
    
    install_db()
    
    preserve_database_user_password()

    install_crosstab()

    load_docker_image()
    
    ensure_dir_exists(PLUGINS_DIR)
    
    ensure_dir_exists(LOGS_DIR)
    os.chmod(LOGS_DIR, 0777) 
    os.chown(LOGS_DIR, 8443, 8443)  
    ensure_dir_exists(TOMCAT_LOGS_DIR)
    os.chmod(TOMCAT_LOGS_DIR, 0777) 
    os.chown(TOMCAT_LOGS_DIR, 8443, 8443)  
    

    start_docker_containers()


def install_files_from_templates():
    log("installing files from templates", PRINT_TO_CONSOLE)
    create_docker_compose_file()
    create_properties_files()
    create_server_xml_files()
    install_backup_task()
    install_permissions_file()
    if DOCKER_DB:
        install_environment_file()


def create_docker_compose_file():
    global DB_HOST_FOR_DOCKER_SERVICES
    template_file = open(INSTALLER_TEMPLATE_DIR + "docker-compose.yml", "r")
    output_file = open("docker-compose.yml", "w")
    
    if DOCKER_DB:
        DB_HOST_FOR_DOCKER_SERVICES = "db.openelis.org" #use docker network to connect by service name
    if DB_HOST == "localhost" or DB_HOST == "127.0.0.1":
        DB_HOST_FOR_DOCKER_SERVICES = get_docker_host_ip() #get docker host loopback
    
    for line in template_file:
        #set docker db attributes
        if DOCKER_DB:
            if line.find("#db") >= 0:
                line = line.replace("#db", "")
            if line.find("[% db_env_dir %]")  >= 0:
                line = line.replace("[% db_env_dir %]", DB_ENVIRONMENT_DIR)  
            if line.find("[% db_data_dir %]")  >= 0:
                line = line.replace("[% db_data_dir %]", DB_DATA_DIR)  
            if line.find("[% db_init_dir %]")  >= 0:
                line = line.replace("[% db_init_dir %]", DB_INIT_DIR)  
            if line.find("[% docker_backups_dir %]")  >= 0:
                line = line.replace("[% docker_backups_dir %]", DOCKER_DB_BACKUPS_DIR)  
            if line.find("[% db_backups_dir %]")  >= 0:
                line = line.replace("[% db_backups_dir %]", DB_BACKUPS_DIR)
            if line.find("[% db_host_port %]")  >= 0:
                line = line.replace("[% db_host_port %]", DOCKER_DB_HOST_PORT)
            if line.find("[% db_name %]")  >= 0:
                line = line.replace("[% db_name %]", DOCKER_DB_CONTAINER_NAME )
        
        #common attributes
        if line.find("[% db_host %]")  >= 0:
            line = line.replace("[% db_host %]", DB_HOST_FOR_DOCKER_SERVICES) 
        if line.find("[% db_port %]")  >= 0:
            line = line.replace("[% db_port %]", DB_PORT) 
        if line.find("[% secrets_dir %]")  >= 0:
            line = line.replace("[% secrets_dir %]", SECRETS_DIR)  
        if line.find("[% plugins_dir %]")  >= 0:
            line = line.replace("[% plugins_dir %]", PLUGINS_DIR)
        if line.find("[% logs_dir %]")  >= 0:
            line = line.replace("[% logs_dir %]", LOGS_DIR)
        if line.find("[% tomcat_logs_dir %]")  >= 0:
            line = line.replace("[% tomcat_logs_dir %]", TOMCAT_LOGS_DIR)
        if line.find("[% etc_dir %]")  >= 0:
            line = line.replace("[% etc_dir %]", OE_ETC_DIR )
        if line.find("[% oe_name %]")  >= 0:
            line = line.replace("[% oe_name %]", DOCKER_OE_CONTAINER_NAME )
        if line.find("[% fhir_api_name %]")  >= 0:
            line = line.replace("[% fhir_api_name %]", DOCKER_FHIR_API_CONTAINER_NAME )
        if line.find("[% timezone %]")  >= 0:
            line = line.replace("[% timezone %]", TIMEZONE )
        if line.find("[% truststore_password %]")  >= 0:
            line = line.replace("[% truststore_password %]", TRUSTSTORE_PWD )
        if line.find("[% keystore_password %]")  >= 0:
            line = line.replace("[% keystore_password %]", KEYSTORE_PWD )
        
        output_file.write(line)

    template_file.close()
    output_file.close()
    
    
def create_properties_files():
    ensure_dir_exists(SECRETS_DIR)
    template_file = open(INSTALLER_TEMPLATE_DIR + "common.properties", "r")
    output_file = open(SECRETS_DIR + "common.properties", "w")

    for line in template_file:
        #common attributes
        if line.find("[% db_host %]")  >= 0:
            line = line.replace("[% db_host %]", DB_HOST_FOR_DOCKER_SERVICES) 
        if line.find("[% db_port %]")  >= 0:
            line = line.replace("[% db_port %]", DB_PORT) 
        if line.find("[% db_password %]")  >= 0:
            line = line.replace("[% db_password %]", CLINLIMS_PWD)
        if line.find("[% truststore_password %]")  >= 0:
            line = line.replace("[% truststore_password %]", TRUSTSTORE_PWD)
        if line.find("[% keystore_password %]")  >= 0:
            line = line.replace("[% keystore_password %]", KEYSTORE_PWD)
        if line.find("[% encryption_key %]")  >= 0:
            line = line.replace("[% encryption_key %]", ENCRYPTION_KEY) 
        if line.find("[% local_fhir_server_address %]")  >= 0:
            line = line.replace("[% local_fhir_server_address %]", LOCAL_FHIR_SERVER_ADDRESS) 
        if line.find("[% remote_fhir_server_address %]")  >= 0:
            line = line.replace("[% remote_fhir_server_address %]", REMOTE_FHIR_SOURCE) 
        if line.find("[% remote_source_update_status %]")  >= 0:
            line = line.replace("[% remote_source_update_status %]", REMOTE_FHIR_SOURCE_UPDATE_STATUS) 
        if line.find("[% consolidated_server_address %]")  >= 0:
            line = line.replace("[% consolidated_server_address %]", CONSOLIDATED_SERVER_ADDRESS) 

        output_file.write(line)

    template_file.close()
    output_file.close()
    os.chmod(SECRETS_DIR + "common.properties", 0640)   
    os.chown(SECRETS_DIR + 'common.properties', 8443, 8443) 
    
    if not os.path.exists(SECRETS_DIR + "extra.properties"):
        open(SECRETS_DIR + "extra.properties", "w").close()
    os.chmod(SECRETS_DIR + "extra.properties", 0640)   
    os.chown(SECRETS_DIR + 'extra.properties', 8443, 8443) 
    
    template_file = open(INSTALLER_TEMPLATE_DIR + "hapi_application.yaml", "r")
    output_file = open(SECRETS_DIR + "hapi_application.yaml", "w")

    for line in template_file:
        #common attributes
        if line.find("[% db_host %]")  >= 0:
            line = line.replace("[% db_host %]", DB_HOST_FOR_DOCKER_SERVICES) 
        if line.find("[% db_port %]")  >= 0:
            line = line.replace("[% db_port %]", DB_PORT) 
        if line.find("[% db_password %]")  >= 0:
            line = line.replace("[% db_password %]", CLINLIMS_PWD) 
        if line.find("[% truststore_password %]")  >= 0:
            line = line.replace("[% truststore_password %]", TRUSTSTORE_PWD)
        if line.find("[% keystore_password %]")  >= 0:
            line = line.replace("[% keystore_password %]", KEYSTORE_PWD) 
        if line.find("[% local_fhir_server_address %]")  >= 0:
            line = line.replace("[% local_fhir_server_address %]", LOCAL_FHIR_SERVER_ADDRESS) 
        
        output_file.write(line)

    template_file.close()
    output_file.close()
    os.chmod(SECRETS_DIR + "hapi_application.yaml", 0640)  
    os.chown(SECRETS_DIR + 'hapi_application.yaml', 8443, 8443)   
    

def create_server_xml_files():
    ensure_dir_exists(SECRETS_DIR)
    template_file = open(INSTALLER_TEMPLATE_DIR + "oe_server.xml", "r")
    output_file = open(OE_ETC_DIR + "oe_server.xml", "w")

    for line in template_file:
        if line.find("[% truststore_password %]")  >= 0:
            line = line.replace("[% truststore_password %]", TRUSTSTORE_PWD)
        if line.find("[% keystore_password %]")  >= 0:
            line = line.replace("[% keystore_password %]", KEYSTORE_PWD) 
        
        output_file.write(line)

    template_file.close()
    output_file.close()
    os.chmod(OE_ETC_DIR + "oe_server.xml", 0640) 
    os.chown(OE_ETC_DIR + 'oe_server.xml', 8443, 8443)  
    
    template_file = open(INSTALLER_TEMPLATE_DIR + "hapi_server.xml", "r")
    output_file = open(OE_ETC_DIR + "hapi_server.xml", "w")

    for line in template_file:
        if line.find("[% truststore_password %]")  >= 0:
            line = line.replace("[% truststore_password %]", TRUSTSTORE_PWD)
        if line.find("[% keystore_password %]")  >= 0:
            line = line.replace("[% keystore_password %]", KEYSTORE_PWD) 
        
        output_file.write(line)

    template_file.close()
    output_file.close()
    os.chmod(OE_ETC_DIR + "hapi_server.xml", 0640) 
    os.chown(OE_ETC_DIR + 'hapi_server.xml', 8443, 8443)      
    

def install_backup_task():
    install_backup_script()
    install_cron_file()
        
        
def install_backup_script():
    if os.path.exists(DB_BACKUPS_DIR + BACKUP_SCRIPT_NAME):
        over_ride = raw_input("The backup script is already installed. Do you want to overwrite it? y/n ")
        if not over_ride.lower() == "y":
            return
    
    backup_script_template = INSTALLER_TEMPLATE_DIR + BACKUP_SCRIPT_NAME 
    # make sure the template files for the backup exits
    if not os.path.exists(backup_script_template):
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
      
    #create DatabaseBackup.pl from template
    ensure_dir_exists(DB_BACKUPS_DIR)
    template_file = open(backup_script_template, "r")
    staging_file = open(INSTALLER_STAGING_DIR + BACKUP_SCRIPT_NAME, "w")

    for line in template_file:
        if line.find("[% installName %]") >= 0:
            line = line.replace("[% installName %]", APP_NAME)
        if line.find("[% siteId %]") >= 0:
            line = line.replace("[% siteId %]", SITE_ID)
        #if line.find("[% postgres_password %]") >= 0:
        #    line = line.replace("[% postgres_password %]", CLINLIMS_PWD)
        if line.find("[% db_install_type %]") >= 0:
            if DOCKER_DB:
                line = line.replace("[% db_install_type %]", "docker")
            elif LOCAL_DB:
                line = line.replace("[% db_install_type %]", "host")
            else:
                line = line.replace("[% db_install_type %]", "remote")
        if line.find("[% db_backups_dir %]") >= 0:
            line = line.replace("[% db_backups_dir %]", DB_BACKUPS_DIR)
        if line.find("[% docker_backups_dir %]") >= 0:
            line = line.replace("[% docker_backups_dir %]", DOCKER_DB_BACKUPS_DIR)
        if line.find("[% secrets_dir %]") >= 0:
            line = line.replace("[% secrets_dir %]", SECRETS_DIR)

        staging_file.write(line)

    template_file.close()
    staging_file.close()

    if not os.path.exists(DB_BACKUPS_DIR):
        os.makedirs(DB_BACKUPS_DIR, 0640)
    if not os.path.exists(DB_BACKUPS_DIR + "daily"):
        os.makedirs(DB_BACKUPS_DIR + "daily", 0640)
    if not os.path.exists(DB_BACKUPS_DIR + "cumulative"):
        os.makedirs(DB_BACKUPS_DIR + "cumulative", 0640)
    if not os.path.exists(DB_BACKUPS_DIR + "transmissionQueue"):
        os.makedirs(DB_BACKUPS_DIR + "transmissionQueue", 0640)

    shutil.copy(INSTALLER_STAGING_DIR + BACKUP_SCRIPT_NAME, DB_BACKUPS_DIR + BACKUP_SCRIPT_NAME)
    os.chmod(DB_BACKUPS_DIR + BACKUP_SCRIPT_NAME, 0744)    


def install_cron_file():
    #create backup cron job from template
    template_file = open(INSTALLER_TEMPLATE_DIR + CRON_FILE_NAME, "r")
    staging_file = open(INSTALLER_STAGING_DIR + CRON_FILE_NAME, "w")

    for line in template_file:
        if line.find("[% backups_dir %]") >= 0:
            line = line.replace("[% backups_dir %]", DB_BACKUPS_DIR)
        staging_file.write(line)

    template_file.close()
    staging_file.close()
    shutil.copy(INSTALLER_STAGING_DIR + CRON_FILE_NAME, CRON_INSTALL_DIR)
    

def install_permissions_file():
    # set values for database users
    pg_permissions = open(INSTALLER_TEMPLATE_DIR + 'pgsql-permissions.sql')
    output_file = open(INSTALLER_DB_INIT_DIR + '1-pgsqlPermissions.sql', 'w')
    for line in pg_permissions:
        if line.find('itechappPassword') >= 0:
            line = line.replace('[% itechappPassword %]', CLINLIMS_PWD)
            output_file.write(line)
        elif line.find('adminPassword') >= 0:
            line = line.replace('[% adminPassword %]', ADMIN_PWD)
            output_file.write(line)
        else:
            output_file.write(line)
    output_file.close()
    pg_permissions.close() 
    os.chmod(INSTALLER_DB_INIT_DIR + '1-pgsqlPermissions.sql', 0640)  
    
    
def install_environment_file():
    ensure_dir_exists(DB_ENVIRONMENT_DIR)
    # set values for database admin user if installing with docker
    database_environment = open(INSTALLER_TEMPLATE_DIR + 'dockerDB/database.env')
    output_file = open(DB_ENVIRONMENT_DIR + 'database.env', 'w')
    for line in database_environment:
        if line.find('adminPassword') >= 0:
            line = line.replace('[% adminPassword %]', ADMIN_PWD)
            output_file.write(line)
        else:
            output_file.write(line)
    output_file.close()
    database_environment.close()
    os.chmod(DB_ENVIRONMENT_DIR + 'database.env', 0640) 
        
        
def install_site_info_config_file():
    log("installing site info config", PRINT_TO_CONSOLE)
    ensure_dir_exists(INSTALLER_DB_INIT_DIR)
    site_file = open(INSTALLER_DB_INIT_DIR + 'siteInfo.sql', 'w')
    persist_site_information(site_file, 'siteNumber', 'The site number of the this lab', SITE_ID)
    persist_site_information(site_file, 'Accession number prefix',
                            'Prefix for SITEYEARNUM format.  Can not be changed if there are samples', SITE_ID)
    site_file.close()   


def install_docker():
    cmd = 'sudo ' + INSTALLER_SCRIPTS_DIR + 'setupDocker.sh'
    os.system(cmd)
    
    cmd = 'sudo ' + INSTALLER_SCRIPTS_DIR + 'setupTomcatDocker.sh'
    os.system(cmd)
    

def install_db():
    log("installing database", PRINT_TO_CONSOLE)
    
    if DOCKER_DB:
        ensure_dir_not_exists(DB_INIT_DIR)
        #every .sql and .sh in db_init_dir will be auto run by docker on install
        shutil.copytree(INSTALLER_DB_INIT_DIR, DB_INIT_DIR)
        #make sure docker can read this file to run it
        os.chown(DB_INIT_DIR + '1-pgsqlPermissions.sql', 0, 0)
        #TODO not the best for security. consider revisiting
        os.chmod(DB_INIT_DIR + '1-pgsqlPermissions.sql', 0644)  
    elif LOCAL_DB:
        #configure the postgres installation to make sure it can be connected to from the docker container
        cmd = 'sudo ' + INSTALLER_SCRIPTS_DIR + 'configureHostPostgres.sh ' + POSTGRES_MAIN_DIR
        os.system(cmd)
        
        cmd = 'su -c "createdb  clinlims" postgres'
        os.system(cmd)
        #make sure postgres can read this file to run it
        os.chown(INSTALLER_DB_INIT_DIR + '1-pgsqlPermissions.sql', 0, grp.getgrnam('postgres')[2])
        cmd = 'su -c "psql  <  ' + INSTALLER_DB_INIT_DIR + '1-pgsqlPermissions.sql" postgres'
        os.system(cmd)
        cmd = 'su -c "psql clinlims  < ' + INSTALLER_DB_INIT_DIR + 'OpenELIS-Global.sql" postgres'
        os.system(cmd)
        cmd = 'su -c "psql clinlims <  ' + INSTALLER_DB_INIT_DIR + 'siteInfo.sql" postgres'
        os.system(cmd)
    else:
        log("can't install database remotely". PRINT_TO_CONSOLE)
        log("please follow instructions for setting up a remote database". PRINT_TO_CONSOLE)
        log("if you have already done so, please ignore this". PRINT_TO_CONSOLE)


def preserve_database_user_password():
    ensure_dir_exists(SECRETS_DIR)
    db_user_password_file = open(SECRETS_DIR + 'datasource.password', 'w')
    db_user_password_file.write(CLINLIMS_PWD)
    db_user_password_file.close()

    # own directory by tomcat user
    os.chown(SECRETS_DIR + 'datasource.password', 8443, 8443)
    os.chmod(SECRETS_DIR + 'datasource.password', 0640)
    
    
# note There is a fair amount of copying files, it should be re-written using shutil
def install_crosstab():
    if LOCAL_DB:
        log("Checking if crosstab is installed in Postgres", True)
    
        # this creates an empty file to write to
        result = open(INSTALLER_STAGING_DIR + 'crosstabResult.txt', 'wr')
        result.close()
        os.chmod(INSTALLER_STAGING_DIR + 'crosstabResult.txt', stat.S_IROTH | stat.S_IWOTH)
    
        cmd = cmd = 'sudo -u postgres psql -d clinlims -L ' + INSTALLER_STAGING_DIR + 'crosstabResult.txt  -f' + INSTALLER_CROSSTAB_DIR + 'crosstabCheck.sql'
        os.system(cmd)
    
        check_file = open(INSTALLER_STAGING_DIR + 'crosstabResult.txt')
    
        for line in check_file:
            if line.find(EXPECTED_CROSSTAB_FUNCTIONS) != -1:
                log("Crosstabs are installed", True)
                return
    
        log("Installing crosstabs", True)
    
        # if tablefunc.so is not present in POSTGRES_LIB_DIR then copy it from INSTALLER_CROSSTAB_DIR
        # the version in INSTALLER_CROSSTAB_DIR is for 8.3 (assumes all new installs will be VM based)
        # if not os.path.exists(POSTGRES_LIB_DIR + "tablefunc.so") and "8.3" in POSTGRES_LIB_DIR:
        if not os.path.exists(POSTGRES_LIB_DIR + "tablefunc.so"):
            # copy the lib file to the lib directory
            shutil.copy(INSTALLER_CROSSTAB_DIR + "tablefunc.so", POSTGRES_LIB_DIR)
            # run the installer
        cmd = cmd = 'sudo -u postgres psql -d clinlims -f ' + INSTALLER_CROSSTAB_DIR + 'tablefunc.sql'
        os.system(cmd)
        

#---------------------------------------------------------------------
#             UPDATE
#---------------------------------------------------------------------
def update():
    if not check_preconditions('update'):
        log(APP_NAME + " is not an existing installation, can not update.\n", PRINT_TO_CONSOLE)
        print "Please either do an install first"
        clean_exit()

    do_update()


def do_update():
    log("Updating " + APP_NAME, PRINT_TO_CONSOLE)

    if not find_password():
        log("Unable to find password from secrets file. Exiting", PRINT_TO_CONSOLE)
        return

    backup_db()
    
    uninstall_docker_images()
    
    clean_docker_objects()

    load_docker_image()
    
    ensure_dir_exists(PLUGINS_DIR)
    
    ensure_dir_exists(LOGS_DIR)
    os.chmod(LOGS_DIR, 0777) 
    os.chown(LOGS_DIR, 8443, 8443)
    ensure_dir_exists(TOMCAT_LOGS_DIR)
    os.chmod(TOMCAT_LOGS_DIR, 0777) 
    os.chown(TOMCAT_LOGS_DIR, 8443, 8443)  
    
    get_stored_user_values()
    
    get_non_stored_user_values()
    
    create_docker_compose_file()
    
    create_properties_files()
    
    create_server_xml_files()

    start_docker_containers()

    install_backup_task()

    time.sleep(10)

    log("Finished updating " + APP_NAME, PRINT_TO_CONSOLE)



#---------------------------------------------------------------------
#             UNINSTALL
#---------------------------------------------------------------------
def uninstall():
    if not check_preconditions('uninstall'):
        log(APP_NAME + " is not an existing installation, can not uninstall.\n", PRINT_TO_CONSOLE)
        print "Nothing to uninstall"
        clean_exit()

    do_uninstall()


def do_uninstall():
    log("removing " + APP_NAME, PRINT_TO_CONSOLE)
    backup_db()
    
    uninstall_docker_images()
    
    delete_database()
    
    uninstall_backup_task()
    
    do_uninstall_backups = raw_input("Do you want to remove backupfiles from this machines y/n ")
    if do_uninstall_backups.lower() == 'y':
        uninstall_backups()
        
    uninstall_program_files()


def delete_database():
    log("removing database " + APP_NAME, PRINT_TO_CONSOLE)
    if DOCKER_DB:
        if os.path.exists(DB_DATA_DIR):
            log("removing database data ", PRINT_TO_CONSOLE)
            shutil.rmtree(DB_DATA_DIR)
    elif LOCAL_DB:
        log("Dropping clinlims database and OpenELIS database roles", PRINT_TO_CONSOLE)
        os.system('su -c "dropdb -e clinlims" postgres')
        os.system('su -c "dropuser -e clinlims" postgres')
        os.system('su -c "dropuser -e admin" postgres')
    else:
        log("please manually remove remote database". PRINT_TO_CONSOLE)


def uninstall_docker_images():
    log("removing docker image " + APP_NAME, PRINT_TO_CONSOLE)
    if DOCKER_DB:
        log("stopping database docker image...", PRINT_TO_CONSOLE)
        cmd = 'docker rm $(docker stop $(docker ps -a -q --filter="name=' + DOCKER_DB_CONTAINER_NAME + '" --format="{{.ID}}"))'
        os.system(cmd)
        
    log("removing openelis docker image...", PRINT_TO_CONSOLE)
    cmd = 'docker rm $(docker stop $(docker ps -a -q --filter="name=' + DOCKER_OE_CONTAINER_NAME + '" --format="{{.ID}}"))'
    os.system(cmd)
    cmd = 'docker rmi $(docker images \'' + DOCKER_OE_REPO_NAME + '\' -q)'
    os.system(cmd)
    
    log("removing fhir api image...", PRINT_TO_CONSOLE)
    cmd = 'docker rm $(docker stop $(docker ps -a -q --filter="name=' + DOCKER_FHIR_API_CONTAINER_NAME + '" --format="{{.ID}}"))'
    os.system(cmd)
    

def uninstall_backup_task():
    log("removing backup task " + APP_NAME, PRINT_TO_CONSOLE)
    if os.path.exists(DB_BACKUPS_DIR + BACKUP_SCRIPT_NAME):
        os.remove(DB_BACKUPS_DIR + BACKUP_SCRIPT_NAME)

    if os.path.exists(CRON_INSTALL_DIR + CRON_FILE_NAME):
        log("removing crontab", PRINT_TO_CONSOLE)
        os.remove(CRON_INSTALL_DIR + CRON_FILE_NAME)


def uninstall_backups():
    if os.path.exists(DB_BACKUPS_DIR):
        log("removing backup", PRINT_TO_CONSOLE)
        shutil.rmtree(DB_BACKUPS_DIR)
        
        
def uninstall_program_files():
    log("cleaning up various program files", PRINT_TO_CONSOLE)
    if os.path.exists(OE_ETC_DIR):
        shutil.rmtree(OE_ETC_DIR)
    if os.path.exists(DB_ENVIRONMENT_DIR):
        shutil.rmtree(DB_ENVIRONMENT_DIR)
    if os.path.exists(DB_INIT_DIR):
        shutil.rmtree(DB_INIT_DIR)
    if os.path.exists(SECRETS_DIR):
        shutil.rmtree(SECRETS_DIR)



#---------------------------------------------------------------------
#             RECOVER 
#---------------------------------------------------------------------
def recover_database():
    over_ride = raw_input("This will reset the database password.  Do you want to keep going? y/n ")
    if not over_ride.lower() == "y":
        return

    generate_database_user_password()
    preserve_database_user_password()
    update_database_user_role()
    log("Updated postgres password in database and config file.", PRINT_TO_CONSOLE)


def update_database_user_role():
    template_file = open(INSTALLER_TEMPLATE_DIR + POSTGRES_ROLE_UPDATE_FILE_NAME, "r")
    staging_file = open(INSTALLER_STAGING_DIR + POSTGRES_ROLE_UPDATE_FILE_NAME, "w")

    for line in template_file:
        if line.find("[% postgres_password %]") >= 0:
            line = line.replace("[% postgres_password %]", CLINLIMS_PWD)

    staging_file.write(line)

    template_file.close()
    staging_file.close()
    #in case staging isn't deleted, make sure we're not storing password without protection
    os.chmod(INSTALLER_STAGING_DIR + POSTGRES_ROLE_UPDATE_FILE_NAME, 0640)
    if DOCKER_DB:
        cmd = 'docker exec ' + DOCKER_DB_CONTAINER_NAME + ' psql -f ' + INSTALLER_STAGING_DIR + POSTGRES_ROLE_UPDATE_FILE_NAME
        os.system(cmd)
    elif LOCAL_DB:
        cmd = 'su -c "psql  <  ' + INSTALLER_STAGING_DIR + POSTGRES_ROLE_UPDATE_FILE_NAME + '" postgres > /dev/null'
        os.system(cmd)
    else:
        log("cannot update remote databases users". PRINT_TO_CONSOLE)
        
        
        
#---------------------------------------------------------------------
#             GET/SET SETUP PROPERTIES
#---------------------------------------------------------------------
def read_setup_properties_file():
    global DB_BACKUPS_DIR, SECRETS_DIR, PLUGINS_DIR
    global DB_DATA_DIR, DB_ENVIRONMENT_DIR, DB_INIT_DIR, DOCKER_DB, DOCKER_DB_BACKUPS_DIR, DOCKER_DB_HOST_PORT
    global DB_HOST, DB_PORT
    global LOCAL_DB
    
    config = ConfigParser.ConfigParser() 
    config.read(OE_ETC_DIR + SETUP_CONFIG_FILE_NAME)
    
    install_dirs_info = "INSTALL_DIRS"
    DB_BACKUPS_DIR = ensure_dir_string(config.get(install_dirs_info, 'backup_dir'))
    SECRETS_DIR = ensure_dir_string(config.get(install_dirs_info,'secrets_dir'))
    PLUGINS_DIR = ensure_dir_string(config.get(install_dirs_info,'plugins_dir'))
    
    database_info = "DATABASE_CONNECTION"
    DB_HOST = config.get(database_info, "host") # unused if docker_db
    DB_PORT = config.get(database_info, "port") # unused if docker_db
    
    docker_db_info = "DOCKER_DB_VALUES"
    DOCKER_DB = is_true_string(config.get(docker_db_info, 'provide_database'))
    if DOCKER_DB:
        DB_HOST = "docker"
        DB_PORT = "5432" 
    DOCKER_DB_HOST_PORT = config.get(docker_db_info,'host_port')
    DOCKER_DB_BACKUPS_DIR = ensure_dir_string(config.get(docker_db_info,'backups_dir'))
    DB_DATA_DIR = ensure_dir_string(config.get(docker_db_info,'host_data_dir'))
    DB_ENVIRONMENT_DIR = ensure_dir_string(config.get(docker_db_info,'host_env_dir'))
    DB_INIT_DIR = ensure_dir_string(config.get(docker_db_info,'host_init_dir'))
    
    #if localhost database, get the docker host ip address so services can connect to db on host
    if DB_HOST == "localhost" or DB_HOST == "127.0.0.1":
        LOCAL_DB = True
    else:
        LOCAL_DB = False
    
    
def write_setup_properties_file():
    ensure_dir_exists(OE_ETC_DIR)
    if os.path.exists(OE_ETC_DIR + SETUP_CONFIG_FILE_NAME):
        log(OE_ETC_DIR + SETUP_CONFIG_FILE_NAME + " already exists, using already installed version", PRINT_TO_CONSOLE)
        return
    
    shutil.copy(SETUP_CONFIG_FILE_NAME, OE_ETC_DIR + SETUP_CONFIG_FILE_NAME)

    
def get_app_details():
    global APP_NAME, VERSION

    docker_files = glob.glob(INSTALLER_DOCKER_DIR + '*.tar.gz')
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
        config_file = open(SECRETS_DIR + 'datasource.password')

        for line in config_file:
            CLINLIMS_PWD = line
            return True
    except IOError:
        return False


def get_action_time():
    global ACTION_TIME

    if ACTION_TIME == '':
        ACTION_TIME = strftime("%Y_%m_%d-%H_%M_%S", time.localtime())
        cmd = 'mkdir ' + INSTALLER_ROLLBACK_DIR + '/' + ACTION_TIME
        os.system(cmd)

    return ACTION_TIME


def get_non_stored_user_values():
    get_keystore_password()
    get_truststore_password()
    get_encryption_key()
    get_server_addresses()
    

def get_install_user_values():
    get_site_id()


def get_stored_user_values():
    ensure_dir_exists(CONFIG_DIR)
    os.chmod(CONFIG_DIR, 0777) 
    get_set_timezone()


def get_set_timezone():
    if (not is_timezone_set()):
        set_timezone()
    get_timezone()


def get_keystore_password():
    global KEYSTORE_PWD
    print "keystore location: " + KEYSTORE_PATH
    KEYSTORE_PWD = getpass("keystore password: ")
    cmd = "openssl pkcs12 -info -in " + KEYSTORE_PATH + " -nokeys -passin pass:" + KEYSTORE_PWD
    status = os.system(cmd)
    if not status == 0:
        print "password for the keystore is incorrect. Please try again"
        get_keystore_password()


def  get_truststore_password():
    global TRUSTSTORE_PWD
    print "truststore location: " + TRUSTSTORE_PATH
    TRUSTSTORE_PWD = getpass("truststore password: ")
    cmd = "openssl pkcs12 -info -in " + TRUSTSTORE_PATH + " -nokeys -passin pass:" + TRUSTSTORE_PWD
    status = os.system(cmd)
    if not status == 0:
        print "password for the truststore is incorrect. Please try again"
        get_truststore_password()
    
        
def get_site_id():
    global SITE_ID

    # Get site specific information
    print """
    Some installations require configuration.  
        You will be asked for specific information which may or may not be needed for this installation.
        If you do not know if it is needed or you do not know the correct value it may be left blank.
        You can set the values after the installation is complete.
    """
    SITE_ID = raw_input("site number for this lab (5 character): ")
        
        
def get_encryption_key():
    global ENCRYPTION_KEY

    print """
    Enter an encryption key that will be used to encrypt sensitive data.
    This value must stay the same between installations or the program will lose all encrypted data.
    Record this value somewhere secure.
    """
    ENCRYPTION_KEY = getpass("encryption key: ")
    confirm_encryption_key = getpass("confirm encryption key: ")
    while (not confirm_encryption_key == ENCRYPTION_KEY):
        print "encryption key did not match. Please re-enter the encryption key"
        ENCRYPTION_KEY = getpass("encryption key: ")
        confirm_encryption_key = getpass("confirm encryption key: ")
        
        
def get_server_addresses():
    global LOCAL_FHIR_SERVER_ADDRESS, REMOTE_FHIR_SOURCE, CONSOLIDATED_SERVER_ADDRESS, REMOTE_FHIR_SOURCE_UPDATE_STATUS
#    should be no longer neccessary since we use *.openelis.org in all our backend certs
#    print """
#    Enter the full server path to the local fhir store 
#    (most likely the address of this server on port 8444)
#    """
#    fhir_server_address = raw_input("local fhir store path (default  " + LOCAL_FHIR_SERVER_ADDRESS + ") : ")
#    if fhir_server_address:
#        if not fhir_server_address.startswith("https://"):
#            LOCAL_FHIR_SERVER_ADDRESS = "https://" + fhir_server_address
#        else:
#            LOCAL_FHIR_SERVER_ADDRESS = fhir_server_address
    
    print """
    Enter the full server path to the remote fhir instance you'd like to poll for Fhir Tasks (eg. OpenMRS) . 
    Leave blank to disable polling a remote instance
    """
    REMOTE_FHIR_SOURCE = raw_input("Remote Fhir Address: ")
    if REMOTE_FHIR_SOURCE:
        if not REMOTE_FHIR_SOURCE.startswith("https://"):
            REMOTE_FHIR_SOURCE = "https://" + REMOTE_FHIR_SOURCE
    
    if REMOTE_FHIR_SOURCE:
        while True: 
            statusResponse = raw_input("Should OpenELIS update the status of the remote fhir source? [Y]es [N]o: ")
            updateStatus = statusResponse[0].lower() 
            if statusResponse == '' or not updateStatus in ['y','n']: 
                print('Please answer with yes or no!') 
            else:
                if updateStatus == 'y':
                    REMOTE_FHIR_SOURCE_UPDATE_STATUS = "true"
                break 
            
    print """
    Enter the full server path to the consolidated server to send data to. 
    Leave blank to disable sending data to the Consolidated server
    """
    CONSOLIDATED_SERVER_ADDRESS = raw_input("Consolidated server address: ")
    if CONSOLIDATED_SERVER_ADDRESS:
        if not CONSOLIDATED_SERVER_ADDRESS.startswith("https://"):
            CONSOLIDATED_SERVER_ADDRESS = "https://" + CONSOLIDATED_SERVER_ADDRESS
    

def is_timezone_set():
    return os.path.isfile(CONFIG_DIR + 'TZ')
        
        
def get_timezone():
    global TIMEZONE
    tz_file = open(CONFIG_DIR + 'TZ')
    TIMEZONE = tz_file.readline()

    
def set_timezone():
    cmd = "tzselect >" + CONFIG_DIR + 'TZ'
    os.system(cmd)
        
        
                
#---------------------------------------------------------------------
#             PASSWORD GENERATION
#---------------------------------------------------------------------
def generate_passwords():
    generate_database_user_password()
    generate_database_admin_password()
    
    
def generate_database_user_password():
    global CLINLIMS_PWD
    CLINLIMS_PWD = ''.join(random.SystemRandom().choice(string.letters + string.digits) for _ in range(12))

    
def generate_database_admin_password():
    global ADMIN_PWD
    ADMIN_PWD = ''.join(random.SystemRandom().choice(string.letters + string.digits) for _ in range(12))
    print "This is the postgres admin password.  Please record it in a safe and private place."
    print "It will not be able to be recovered once this script is finished\n"
    print ADMIN_PWD
    print raw_input("\npress any key once you have recorded it")
    os.system('clear')
        
        
        
#---------------------------------------------------------------------
#             STATE CHECKING
#---------------------------------------------------------------------
def check_preconditions(goal):
    """Checks if server is in right state for goal"""
    global POSTGRES_LIB_DIR

    if LOCAL_DB:
        log("Checking for Postgres 8.3 or later installation", PRINT_TO_CONSOLE)
        if not check_postgres_preconditions():
            return False
        
    if goal == 'install':
        if (db_installed('clinlims')):
            log("\nThere is a currently installed version of OpenElis", PRINT_TO_CONSOLE)
            return False
        if (not os.path.isfile(KEYSTORE_PATH) or not os.access(KEYSTORE_PATH, os.R_OK)):
            log("\ncould not find a readable keystore. Check that file exists and is readable by the current user", PRINT_TO_CONSOLE)
            log("\nkeystore should exist at: " + KEYSTORE_PATH, PRINT_TO_CONSOLE)
            return False
        if (not os.path.isfile(CLIENT_FACING_KEYSTORE_PATH) or not os.access(CLIENT_FACING_KEYSTORE_PATH, os.R_OK)):
            log("\ncould not find a readable client facing keystore. Check that file exists and is readable by the current user", PRINT_TO_CONSOLE)
            log("\nkeystore should exist at: " + CLIENT_FACING_KEYSTORE_PATH, PRINT_TO_CONSOLE)
            return False
        if (not os.path.isfile(TRUSTSTORE_PATH) or not os.access(TRUSTSTORE_PATH, os.R_OK)):
            log("\ncould not find a readable trsutstore. Check that file exists and is readable by the current user", PRINT_TO_CONSOLE)
            log("\ntruststore should exist at: " + TRUSTSTORE_PATH, PRINT_TO_CONSOLE)
            return False
        
    elif goal == 'update':
        if (not db_installed('clinlims')):
            log("\nThere is no currently installed version of OpenElis", PRINT_TO_CONSOLE)
            return False
        if (not os.path.isfile(KEYSTORE_PATH) or not os.access(KEYSTORE_PATH, os.R_OK)):
            log("\ncould not find a readable keystore. Check that file exists and is readable by the current user", PRINT_TO_CONSOLE)
            log("\nkeystore should exist at: " + KEYSTORE_PATH, PRINT_TO_CONSOLE)
            return False
        if (not os.path.isfile(CLIENT_FACING_KEYSTORE_PATH) or not os.access(CLIENT_FACING_KEYSTORE_PATH, os.R_OK)):
            log("\ncould not find a readable client facing keystore. Check that file exists and is readable by the current user", PRINT_TO_CONSOLE)
            log("\nkeystore should exist at: " + CLIENT_FACING_KEYSTORE_PATH, PRINT_TO_CONSOLE)
            return False
        if (not os.path.isfile(TRUSTSTORE_PATH) or not os.access(TRUSTSTORE_PATH, os.R_OK)):
            log("\ncould not find a readable trsutstore. Check that file exists and is readable by the current user", PRINT_TO_CONSOLE)
            log("\ntruststore should exist at: " + TRUSTSTORE_PATH, PRINT_TO_CONSOLE)
            return False
        
    elif goal == 'uninstall':
        if (not db_installed('clinlims')):
            log("\nThere is no currently installed version of OpenElis", PRINT_TO_CONSOLE)
            return False

    return True
        
    
def db_installed(db_name):
    if DOCKER_DB:
        return os.path.isdir(DB_DATA_DIR)
    elif LOCAL_DB:
        cmd = 'sudo -u postgres psql -c "SELECT datname FROM pg_catalog.pg_database WHERE lower(datname) = lower(\'' + db_name + '\');"'
        result = subprocess.check_output(cmd, shell=True)
        return db_name in result
    else:
        log("cannot check if remote database is installed. proceeding", PRINT_TO_CONSOLE)
        return True


def check_postgres_preconditions():
    global POSTGRES_LIB_DIR, POSTGRES_MAIN_DIR
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
        if os.path.isdir("/usr/lib/postgresql/" + str(major) + "." + str(minor) + "/lib/"):
            POSTGRES_LIB_DIR = "/usr/lib/postgresql/" + str(major) + "." + str(minor) + "/lib/"
        elif os.path.isdir("/usr/lib/postgresql/" + str(major) + "/lib/"):
            POSTGRES_LIB_DIR = "/usr/lib/postgresql/" + str(major) + "/lib/"
        else:
            log("Could not find postgres installation folders\n", PRINT_TO_CONSOLE)
            return False
        if os.path.isdir("/etc/postgresql/" + str(major) + "." + str(minor) + "/main/"):
            POSTGRES_MAIN_DIR = "/etc/postgresql/" + str(major) + "." + str(minor) + "/main/"
        elif os.path.isdir("/etc/postgresql/" + str(major) + "/main/"):
            POSTGRES_MAIN_DIR = "/etc/postgresql/" + str(major) + "/main/"
        else:
            log("Could not find postgres installation folders\n", PRINT_TO_CONSOLE)
            return False
        return True
    else:
        log("Postgres must be 8.3 or later\n", PRINT_TO_CONSOLE)
        return False



#---------------------------------------------------------------------
#             DOCKER FUCNTIONS
#---------------------------------------------------------------------
def load_docker_image():
    log("loading openelis-global docker image", PRINT_TO_CONSOLE)
    cmd = 'sudo docker load < ' + INSTALLER_DOCKER_DIR + APP_NAME + '-' + VERSION + '.tar.gz'
    os.system(cmd)
    
    log("loading jpa-server docker image", PRINT_TO_CONSOLE)
    cmd = 'sudo docker load < ' + INSTALLER_DOCKER_DIR + 'JPAServer_DockerImage.tar.gz'
    os.system(cmd)
    
#     log("loading dataimport-webapp docker image", PRINT_TO_CONSOLE)
#     cmd = 'sudo docker load < ' + INSTALLER_DOCKER_DIR + 'DataImporter_DockerImage.tar.gz'
#     os.system(cmd)
    
#    log("loading datasubscriber-webapp docker image", PRINT_TO_CONSOLE)
#    cmd = 'sudo docker load < ' + INSTALLER_DOCKER_DIR + 'DataSubscriber_DockerImage.tar.gz'
#    os.system(cmd)

    if DOCKER_DB:
        log("loading postgres docker image", PRINT_TO_CONSOLE)
        cmd = 'sudo docker load < ' + INSTALLER_DOCKER_DIR + 'Postgres_DockerImage.tar.gz'
        os.system(cmd)
    

def start_docker_containers():
    log("starting docker containers", PRINT_TO_CONSOLE)
    cmd = 'sudo docker-compose up -d '
    os.system(cmd)


def clean_docker_objects():
    log("cleaning docker network", PRINT_TO_CONSOLE)
    cmd = 'sudo docker network prune'
    os.system(cmd)
    
    log("cleaning docker images", PRINT_TO_CONSOLE)
    cmd = 'sudo docker image prune'
    os.system(cmd)
    
    
def get_docker_host_ip():
    cmd = "ip -4 addr show docker0 | grep -Po 'inet \K[\d.]+'"
    return subprocess.check_output(cmd, shell=True).strip()



#---------------------------------------------------------------------
#             ASSORTED UTILITIES
#---------------------------------------------------------------------
def is_true_string(compare_string):
    if "true" == compare_string.lower() or "t" == compare_string.lower():
        return True
    else:
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


def backup_db():
    action_time = get_action_time()
    backup_name = action_time + '/openElis.backup'
    
    if find_password():
        log("backing up database to " + INSTALLER_ROLLBACK_DIR + backup_name, PRINT_TO_CONSOLE)
        if DOCKER_DB:
            ensure_dir_exists(DB_BACKUPS_DIR + action_time)
            os.system(
                'docker exec ' + DOCKER_DB_CONTAINER_NAME + ' /usr/bin/pg_dump -U clinlims -f "' 
                + DOCKER_DB_BACKUPS_DIR + backup_name + '" clinlims')
            if os.path.exists(DB_BACKUPS_DIR + backup_name):
                shutil.move(DB_BACKUPS_DIR + backup_name, INSTALLER_ROLLBACK_DIR + backup_name)
            else:
                over_ride = raw_input("Database could not be backed up properly. Do you want to continue without a proper backup? y/n ")
                if not over_ride.lower() == "y":
                    clean_exit()  
        elif LOCAL_DB:
            os.system(
                "PGPASSWORD=\"" + CLINLIMS_PWD + "\";export PGPASSWORD; su -c  'pg_dump -h localhost -U clinlims clinlims > " + INSTALLER_ROLLBACK_DIR + backup_name + "'")
        else:
            log("can't backup remote databases. proceeding". PRINT_TO_CONSOLE)
    else:
        log("can't back up database, missing password file ", PRINT_TO_CONSOLE)
    
    
def ensure_dir_exists(dir):
    if not os.path.exists(dir):
        os.makedirs(dir)
        
        
def ensure_dir_not_exists(dir):
    if os.path.exists(dir):
        shutil.rmtree(dir)


def get_file_name(file):
    filename_parts = file.split('/')
    return filename_parts[len(filename_parts) - 1]
 
 
def ensure_dir_string(dir_string):
    if not dir_string.endswith('/'):
        return dir_string + '/'
    return dir_string

        
def check_on_writable_system():
    if not os.access('./', os.W_OK | os.X_OK):
        print("Unable to write to file system.  Please copy installer to hard disk")
        print("Exiting")
        exit()


def write_version():
    log("\n", PRINT_TO_CONSOLE)
    log("------------------------------------", not PRINT_TO_CONSOLE)
    log("OpenELIS installer Version " + VERSION, PRINT_TO_CONSOLE)


def open_log_file():
    global LOG_FILE
    ensure_dir_exists(INSTALLER_LOG_DIR)
    LOG_FILE = open(INSTALLER_LOG_DIR + LOG_FILE_NAME, 'a')


def log(message, to_console):
    LOG_FILE.write(message + "\n")
    if to_console:
        print message
        
        
def clean_exit():
    global LOG_FILE
    LOG_FILE.close()
    exit()
        
    
# call the main function
if __name__ == "__main__":
   main(sys.argv[1:])
