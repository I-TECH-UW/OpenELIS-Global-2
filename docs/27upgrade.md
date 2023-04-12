

# **Migrate with pg_upgrade**

Much faster method of upgrading postgres dbs

If you are running these commands on a remote server, it is recommended to use a recoverable script session. For example, running `screen`. If you disconnect, just reconnect and run `screen -x` to recover active session.

## NOTES: 

    1.     It is paramount that data backups are up to date and recovery is tested before attempting a database migration. 
    
    2.     This can be a very memory intensive process. It is recommended to increase memory on the server that this is running on to 128 GB or more. This can be accomplished without too much of a performance hit by adding an SSD as a swap drive.
    
    3.     This process doesn't delete the old database files until the new database is up, running, and tested. Ensure that OE is running properly and the data is there BEFORE deleting the old machine.
    
    4.     Because of point number 3, you will require room on the server for a copy of all the database files located at `/var/lib/openelis-global/data`
    
## **Migrating OE 9.5 database to 14.4 database in dockerized environments**


    1.     stop containers so no changes happen while migration is occurring


        a)     sudo docker stop autoheal-oe external-fhir-api openelisglobal-webapp openelisglobal-database


    2.     remove db container so auto restart doesn’t occur


        a)     sudo docker rm openelisglobal-database


    3.     create folders for first step db migration to take place (this can be done on a separate machine with docker installed if the main server lacks resources to run the upgrade).


        a)     sudo mkdir /var/lib/openelis-global/db


        b)     sudo mkdir /var/lib/openelis-global/db/9.5


        c)     sudo mkdir /var/lib/openelis-global/db/14


        d)     sudo mkdir /var/lib/openelis-global/db/14/data


    4.     copy current db to the upgrade location (copy will preserve the old data so that we can more easily revert if something goes wrong)
    
    4.1    If you are performing the upgrade on the machine where the database is installed:

        a)     sudo cp -r /var/lib/openelis-global/data /var/lib/openelis-global/db/9.5/data
        
    4.2 If you are performing the upgrade on another machine where the database is not installed:

        a)     sudo tar cf /var/lib/openelis-global/data.tar.gz -C /var/lib/openelis-global/ data

        a)     sudo scp /var/lib/openelis-global/data.tar.gz username@destination:/var/lib/openelis-global/db/9.5/data.tar.gz

        a)     ssh username@destination
        
        a)     tar xzf /var/lib/openelis-global/db/9.5/data.tar.gz -C /var/lib/openelis-global/db/9.5/

    5.     run the 9.5 to 14 migration


        a)     sudo docker pull ctsteele/postgres-migration:9.5-14
        
        b)     sudo docker run -it --rm -v /var/lib/openelis-global/db/:/var/lib/postgresql/  ctsteele/postgres-migration:9.5-14 --link


    8.     replace old db with new db


        a)     sudo mv /var/lib/openelis-global/data /var/lib/openelis-global/data2


        b)     sudo mv /var/lib/openelis-global/db/14/data /var/lib/openelis-global/data


    9.  ensure file permissions and db access permissions are correct 
    

        a)     sudo chown -R tomcat2:tomcat2 /var/lib/openelis-global/data


        b)     edit `/var/lib/openelis-global/data/pg_hba.conf` to include all the same entries that are in `/var/lib/openelis-global/data2/pg_hba.conf`
    
    
    
    10.     run the setup script for the new version with updated db, ignoring db backup couldn’t occur step


        a)     sudo setup_OpenELIS.py


    11.  ensure systems start up and that data is present


    12.  optionally delete old db (or move to a secure backup server)


        a)     sudo rm /var/lib/openelis-global/db /var/lib/openelis-global/data2


## **Migrating OE 9.5 database to 14.4 database in non dockerized environment into dockerized environment (untested)**


    1)    stop containers so no changes happen while migration is occurring


        a)     sudo docker stop autoheal-oe external-fhir-api openelisglobal-webapp


    2)    stop postgres instance


        a)     sudo -upostgres /usr/lib/postgresql/9/bin/pg_ctl -D /var/lib/postgresql/9/data -l logfile stop


    3)    check that upgrade can occur


        a)     time /usr/lib/postgresql/14/bin/pg_upgrade --old-bindir /usr/lib/postgresql/9/bin --new-bindir /usr/lib/postgresql/14/bin --old-datadir /var/lib/postgresql/9/data --new-datadir /var/lib/openelis-global/data --link --check


    4)    run the upgrade


        a)     time /usr/lib/postgresql/14/bin/pg_upgrade --old-bindir /usr/lib/postgresql/9/bin --new-bindir /usr/lib/postgresql/14/bin --old-datadir /var/lib/postgresql/9/data --new-datadir /var/lib/openelis-global/data --link


    5)    run the setup script for the new version with updated db, ignoring db backup couldn’t occur step


        a)     sudo setup_OpenELIS.py


    6)    ensure systems start up and that data is present


# **Migrate with pg_dump**

This approach is mentioned as being the preferred option in postgres docs, but is VERY slow when restoring BLOBs


## **Migrating OE 9.5 database to 14.4 database in dockerized environments**

 


    1.     Run  the following commands to create the backup for restoring into OE 14, and the backup for 9.5 in case something goes wrong


        a)     sudo docker exec openelisglobal-database pg_dump -j 8 -d clinlims --verbose -U admin -F c -f /backups/95db.backup


        b)    sudo docker kill openelisglobal-database && sudo mv /var/lib/openelis-global/data  /var/lib/openelis-global/data2


    2.     install new OE with setup script - ignore db missing when prompted


    3.     Run the following to pause non-db connections and restore the backup before bringing the containers up again (must run in OE installer directory). Restoring a db can take a long time for large dbs. To avoid a long downtime, this can be done in a separate container


        a)     sudo docker kill external-fhir-api openelisglobal-webapp && sudo docker rm external-fhir-api openelisglobal-webapp


        b)    sudo docker exec openelisglobal-database pg_restore -j 8 -d clinlims -U postgres -v -Fc -c /backups/95db.backup


        c)     sudo docker-compose up -d


    4.     confirm that OpenELIS is working and that the data is there by accessing the front end


    5.     optionally, delete old data


        a)     rm /var/lib/openelis-global/data2


        b)    rm /var/lib/openelis-global/backups/95db.backup


## **Migrating OE 9.5 database to 14.4 database in non dockerized environment into dockerized environment (untested)**

 


    1.     Run  the following commands to create the backup for restoring into OE 14


        a)     pg_dump -d clinlims -h localhost -p 5432 –verbose -U clinlims -F c -f /var/lib/openelis-global/backups/95db.backup


        b)    [enter password for clinlims]


    2.     Modify setup.ini to have docker db on port other than 5432 and use docker db


        a)     sudo vi /etc/openelis-global/setup.ini


    3.     install new OE with setup script


    4.     Run the following to pause non-db connections and restore the backup before bringing the containers up again (must run in OE installer directory)


        a)     sudo docker kill external-fhir-api openelisglobal-webapp && sudo docker rm external-fhir-api openelisglobal-webapp


        b)    sudo docker exec openelisglobal-database  pg_restore -d clinlims -U postgres -v -Fc -c /backups/95db.backup


        c)     sudo docker-compose up -d


    5.     confirm that OpenELIS is working and that the data is there by accessing the front end


    6.     optionally, delete old data


        a)     sudo rm /var/lib/openelis-global/backups/95db.backup


        b)    uninstall native postgres if you want to move the docker  db onto port 5432
