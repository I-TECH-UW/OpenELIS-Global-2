**OpenELIS 2.7 Upgrade**

**Background**

The goal of this process is to upgrade a typical OpenELIS installation to the current versions of OpenELIS and all supporting software. A complete backup and rollback strategy is beyond the scope of this document and is assumed to be completed prior to starting this process.

The order of the upgrades is important as there are interdependencies.

 

**Backup **(dev example)

sudo lsblk

sudo mkdir /media/newhd

sudo mount /dev/sdb2 /media/newhd, umount /dev/sdb2

df -H

dd if=/dev/sda2 of=/dev/sdb2 bs=256K conv=noerror,sync

dd if=/dev/sda of=/dev/sdb bs=256K conv=noerror,sync

 

**PostgreSQL**           	

Current Version       	Latest Version         	Show Version          	End of Life

9.5                           	14.5                         	postgres -V  	        	2021-01-01

 

docker image, docker image ls, docker pull postgres:14.5,

sudo docker exec -it openelisglobal-database bash,

which postgres

 

postgresql 9.5 -> 14.5

  9.5 db will be copied from the docker container and restored to a 9.5 db running Ubuntu 18 native.


    1.     Install Posgresql latest (14.5) using:


    sudo apt-get update


    sudo apt-get install postgresql postgresql-contrib


    2.     Initialize a 9.5 db and a 14.5 db using:


    sudo -upostgres /usr/lib/postgresql/9/bin/initdb -D /var/lib/postgresql/9/data


    sudo -upostgres /usr/lib/postgresql/14/bin/initdb -D /var/lib/postgresql/14/data


    3.     In /tmp start the native Ubuntu 9.5 Postgres server using:


    sudo -upostgres /usr/lib/postgresql/9/bin/pg_ctl -D /var/lib/postgresql/9/data -l logfile start


    4.     In openelisglobal-database container back up 9.5 db using:


    pg_dump -b -Upostgres -Fc  -f dump_9.bk


    5.     Exit container then copy backup file to /tmp using:


    docker cp &lt;container id>:dump_9.bk .


    6.     Restore restore db to Ubuntu native 9.5 using:


    pg_restore -Cvc -Upostgres -dpostgres dump_9.bk


    7.     Stop the Postgres 9.5 server using:


    sudo -upostgres /usr/lib/postgresql/9/bin/pg_ctl -D /var/lib/postgresql/9/data -l logfile stop


    8.     Check that pg_upgrade can upgrade the 9.5 db to the 14.5 using:


    time /usr/lib/postgresql/14/bin/pg_upgrade --old-bindir /usr/lib/postgresql/9/bin --new-bindir /usr/lib/postgresql/14/bin --old-datadir /var/lib/postgresql/9/data --new-datadir /var/lib/postgresql/14/data --link –check


    9.     Run the upgrade using the above command without the –check flag.


    10.  Start the native Ubuntu 14.5 server using:


    11.  sudo -upostgres /usr/lib/postgresql/14/bin/pg_ctl -D /var/lib/postgresql/14/data -l logfile start


    12.  Once you start the new server, consider running:


    sudo -upostgres /usr/lib/postgresql/14/bin/vacuumdb --all –analyze-in-stages


    13.  Backup native 14.5 database using:


    pg_dump -b -Upostgres -Fc  -f dump_14.bk


    14.  Edit docker-compose.yml to use postgresql 14.5


    image: postgres:9.5 to image: postgres:14.5


    15.  Start the database container using docker-compose.


    16.  Copy dump_14.bk to the container using docker cp.


    17.  In container restore db to container 14.5 using:


    pg_restore -Cvc -Upostgres -dpostgres dump_14.bk
