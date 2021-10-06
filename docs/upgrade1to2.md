# OpenELIS-Global 1 to 2 Conversion


# Notes:


## Choosing a Path

Path 1 is going to be the easiest and most reliable method of upgrading from version 1 to 2, but requires having a spare machine to place OpenELIS-Global 2 on.

Path 2 is a less reliable way to move from version 1 to 2, but can be done without using a spare machine.

Path 3 is the least reliable method, and most likely to result in an unstable installation, but it can be done fully in place without any outside equipment, and will maintain any other configurations you have applied to your server running OpenELIS-Global 1.


## Backups

Before attempting any upgrade paths, be sure you have accessible backups of your currently running version of OpenELIS-Global 1 available on another drive/server. 

For path 1 this involves checking to make sure your backups do not only exist on the server running OpenELIS-Global 1

If you are doing path 2 or 3, it would also be good to have a backup of your entire system, not just your database.


## Testing New Installation



*   Ensure you can log in (check more than just admin account)
*   Ensure old data can be properly found (ex. Search for an old patient)


## Password Migration

As part of the security upgrade process, passwords were moved from being stored as an encrypted version to being stored as cryptographically salt-hashed version. This migration was implemented to happen automatically in later versions of OpenELIS-Global 1, but will not be present in OpenELIS-Global 2. Therefore it is possible that users who have not logged in recently, or installations not running a recent version of OpenELIS-Global 1 will not be able to log in once they are migrated to OpenELIS-Global 2. To migrate passwords, please follow the instructions [here](https://docs.google.com/document/d/1ZfFanwLskT9A0i5GC92PVF-5McWXttBITBi1Jbn3_yU/edit?usp=sharing). This process can be done before dumping the old database, or during the installation process.


## Liquibase

Liquibase often complains about checksum errors. We hope to be able to get rid of this by starting fresh with liquibase, but if you encounter checksum errors and need to run it still, you can do so be clearing the md5Sum value in the databasechangelog table for the file/change set that is having an issue. For a more permanent solution, you can add &lt;validCheckSum>{new checksum value}&lt;/validCheckSum> to the changeset that is complaining (before the &lt;comment> tag) and it should go away.




# Path 1: Migrate Server to New Machine

**ENSURE YOU HAVE BACKED UP YOUR DATABASE FIRST**


## Migrate Passwords (If Unmigrated) on Old Server



1. Follow password migration instructions
    1. Currently located [here](password.md)

## Run Liquibase on Old Server



1. Download the [old liquibase files](https://github.com/I-TECH-UW/Liquibase-Outdated/archive/master.tar.gz) and unpack it
    1. wget https://github.com/I-TECH-UW/Liquibase-Outdated/archive/master.tar.gz
    2. tar -xvzf master.tar.gz
    3. cd Liquibase-Outdated-master
2. Run the liquibase command
    4. java -jar -Dfile.encoding=utf-8 ./lib/liquibase-1.9.5.jar --defaultsFile=./liquibase.properties  --contexts=ci_regional --url=jdbc:postgresql://localhost:5432/clinlims update


## Dump Database on Old Server



1. Run the following command 
    1. sudo -u postgres pg_dump > OpenELIS-backup.sql


## Install OpenELIS-Global 2 on New Server



1. Follow normal install instructions for OpenELIS-Global 2
    1. Currently located in the make the docs


## Pause Docker container on New Server



1. Get docker container id
    2. sudo docker container ls
    3. Find openelisglobal-web container and make a note of the id
2. Pause running docker container
    4. sudo docker kill {container_id}


## Restore Database Dump on New Server



1. Copy OpenELIS-backup.sql from old server onto new server
2. Recreate the database and import the dump
    1. dropdb clinlims
    2. createdb clinlims
    3. psql -f /OpenELIS-backup.sql clinlims
    4. psql
        1. ALTER DATABASE clinlims OWNER TO clinlims;
        2. \q


## Update Installation on New Server



1. Run upgrade script from OE2 Installer directory
    1. sudo python setup_OpenELIS.py -update


# Path 2: Migrate Server Using External Storage

**ENSURE YOU HAVE BACKED UP YOUR DATABASE FIRST**


## Migrate Passwords (If Unmigrated)



1. Follow password migration instructions
    1. Currently located [here](password.md)


## Run Liquibase



1. Download the [old liquibase files](https://github.com/I-TECH-UW/Liquibase-Outdated/archive/master.tar.gz) and unpack it
    1. wget https://github.com/I-TECH-UW/Liquibase-Outdated/archive/master.tar.gz
    2. tar -xvzf master.tar.gz
    3. cd Liquibase-Outdated-master
2. Run the liquibase command
    4. java -jar -Dfile.encoding=utf-8 ./lib/liquibase-1.9.5.jar --defaultsFile=./liquibase.properties  --contexts=ci_regional --url=jdbc:postgresql://localhost:5432/clinlims update


## Dump Database 



1. Run the following command 
    1. sudo -u postgres pg_dump > OpenELIS-backup.sql
2. Copy OpenELIS-backup.sql from onto external storage (like a flash drive. After you are done with the upgrade you should SECURELY delete this backup as it contains sensitive patient info. This means deleting and overwriting the data)


## Install OpenELIS-Global 2 on the Server



1. Follow normal install instructions for OpenELIS-Global 2 on the computer
    1. Currently located [here](install.md)


## Pause Docker Container



1. Get docker container id
    1. sudo docker container ls
    2. Find openelis-global container and make a note of the id
2. Pause running docker container
    3. sudo docker pause CONTAINER {container_id}


## Restore Database Dump 



1. Copy OpenELIS-backup.sql 
2. Copy OpenELIS-backup.sql from new server into docker image
    1. sudo docker container ls
    2. Find openelisglobal-database and make a note of the id
    3. sudo docker cp OpenELIS-backup.sql {container_id}:/OpenELIS-backup.sql
3. Enter the docker image and login as postgres
    4. sudo docker exec -it  {container_id} /bin/bash
    5. su postgres
4. Recreate the database and import the dump
    6. dropdb clinlims
    7. createdb clinlims
    8. psql -f /OpenELIS-backup.sql clinlims
    9. psql
        1. ALTER DATABASE clinlims OWNER TO clinlims;
        2. \q
5. Exit out of sessions
    10. exit
    11. exit


## Update Installation



1. Run upgrade script from OE2 Installer directory
    1. sudo python setup_OpenELIS.py -update


# Path 3: Update Server in Place

**ENSURE YOU HAVE BACKED UP YOUR DATABASE FIRST**


## Uninstall Tomcat from Server


## Migrate Passwords (If Unmigrated)



1. Follow password migration instructions
    1. Currently located [here](password.md)


## Run Liquibase



1. Download the [old liquibase files](https://github.com/I-TECH-UW/Liquibase-Outdated/archive/master.tar.gz) and unpack it
    1. wget https://github.com/I-TECH-UW/Liquibase-Outdated/archive/master.tar.gz
    2. tar -xvzf master.tar.gz
    3. cd Liquibase-Outdated-master
2. Run the liquibase command
    4. java -jar -Dfile.encoding=utf-8 ./lib/liquibase-1.9.5.jar --defaultsFile=./liquibase.properties  --contexts=ci_regional --url=jdbc:postgresql://localhost:5432/clinlims update


## Backup Postgres



1. Run the following command 
    1. sudo -u postgres pg_dump > OpenELIS-backup.sql


## Upgrade Ubuntu to Target Version



1. Update packages
    1. sudo apt-get update
    2. sudo apt-get upgrade
2. Update current distribution
    3. sudo apt-get dist-upgrade
3. Upgrade to next version
    4. sudo apt-get install update-manager-core
    5. sudo do-release-upgrade
    6. Will run for a while...
4. Check if running target version
    7. lsb_release -a
5. If version does not match target version, repeat from step 1


## Restore Database Dump 



1. Copy OpenELIS-backup.sql
2. Copy OpenELIS-backup.sql from new server into docker image
    1. sudo docker container ls
    2. Find openelisglobal-database and make a note of the id
    3. sudo docker cp OpenELIS-backup.sql {container_id}:/OpenELIS-backup.sql
3. Enter the docker image and login as postgres
    4. sudo docker exec -it  {container_id} /bin/bash
    5. su postgres
4. Recreate the database and import the dump
    6. dropdb clinlims
    7. createdb clinlims
    8. psql -f /OpenELIS-backup.sql clinlims
    9. psql
        1. ALTER DATABASE clinlims OWNER TO clinlims;
        2. \q
5. Exit out of sessions
    10. exit
    11. exit


## Update Installation



1. Run upgrade script from OE2 Installer directory
    1. sudo python setup_OpenELIS.py -update