

# **Migrate with pg_upgrade**

Much faster method of upgrading postgres dbs

If you are running these commands on a remote server, it is recommended to use a recoverable script session. For example, running `screen`. If you disconnect, just reconnect and run `screen -x` to recover active session.


## **Migrating OE 9.5 database to 14.4 database in dockerized environments**


    1.     stop containers so no changes happen while migration is occurring


        a)     sudo docker stop autoheal-oe external-fhir-api openelisglobal-webapp openelisglobal-database


    2.     remove db container so auto restart doesn’t occur


        a)     sudo docker rm openelisglobal-database


    3.     create folders for first step db migration to take place


        a)     sudo mkdir /var/lib/openelis-global/db


        b)    sudo mkdir /var/lib/openelis-global/db/9.5


        c)     sudo mkdir /var/lib/openelis-global/db/9.6


        d)    sudo mkdir /var/lib/openelis-global/db/9.6/data


    4.     copy current db to location (copy will preserve the old data so that we can more easily revert if something goes wrong)


        a)     sudo cp -r /var/lib/openelis-global/data /var/lib/openelis-global/db/9.5/data


    5.     run the 9.5 to 9.6 migration


        a)     sudo docker run -it --rm -v /var/lib/openelis-global/db/:/var/lib/postgresql/  tianon/postgres-upgrade:9.5-to-9.6 --link


    6.     create folders for second step migration to take place


        a)     sudo mkdir /var/lib/openelis-global/db/14


        b)    sudo mkdir /var/lib/openelis-global/db/14/data


    7.     run the 9.6 to 14 conversion


        a)     sudo docker run -it --rm -v /var/lib/openelis-global/db/:/var/lib/postgresql/  tianon/postgres-upgrade:9.6-to-14 --link


    8.     replace old db with new db


        a)     sudo mv /var/lib/openelis-global/data /var/lib/openelis-global/data2


        b)    sudo mv /var/lib/openelis-global/db/14/data /var/lib/openelis-global/data


    9.     run the setup script for the new version with updated db, ignoring db backup couldn’t occur step


        a)     sudo setup_OpenELIS.py


    10.  ensure systems start up and that data is present


    11.  optionally delete old db


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
