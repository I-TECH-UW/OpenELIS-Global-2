## OpenELIS Global Database backups

### Background
Good practices for database backups include backing up often and to a location separate from the host machine.  To support these practices the OpenELIS installer creates the structure for doing backups and the cron job for the doing a daily backup.  The installed backup is not suitable for all installations and this document provides guidance for having a suitable solution.
### Assumptions
1. The person setting up the backup is familiar with basic Linux commands, has root access to the server, and knows how to find information to fill in their gaps of knowledge.
1. An sftp site has been identified to receive the backup files, there is a USB hard disk drive, or both. 
1. If sending to an offsite backup, the server has a connection to the internet.
### Basic backup processes description

* A cron job will run the backup script, “DatabaseBackup.pl”

* The script will do the following:

* Do a dump of the OpenELIS database and compress it in a file.

* Copy that backup to a sub-directory named “daily”.  That directory will always have the latest copy of the backup

* Copy the backup to a sub-directory named “cumulative”.  That directory will have copies of the last 30 days of the backups

* Copy the backup to a sub-directory named “transmissionQueue”.  That directory will have copies of backups waiting to be sent off-site.  

* Send the files in transmissionQueue to an ftp site and/or external drive.  As soon as they are successfully sent they will be removed from transmissionQueue.

### Customizing the script

The installed script can be found at /var/lib/openelis-global/backups/DatabaseBackup.pl by default after installation.  The installed script can be edited in place or replaced entirely. The person configuring the backup can decide which method they prefer. The script has a few important variables such as the postgres password file, siteID, etc. Pay attention to the below lines:

* Line 71: The db install type is how your database is installed onto your machine. By default this is `docker`, but can also be `host` if the database is running natively on your server. Replace [% db_install_type %] with your install type if it is not already filled in.
* Line 72: The secrets directory path where passwords are kept. By default this is `/var/lib/openelis-global/secrets/` . Replace [% secrets_dir %] with your directory location if it is not already filled in.
* Line 76: The name of the site.  Replace [% siteId %] if it is not already filled in.  This will be used to create the backup file name and will be the prefix to the file.
* Line 77: The destination for the file.  Replace “192.168.1.1/EFI/backup/”.  The IP address is the IP address of the sftp server and the rest is the path on the server which will receive the files.  The person doing this installation should make sure that path exists.  Make sure that the trailing ‘/’ is part of the path. If you are sending it off site PLEASE MAKE SURE YOU USE SFTP! Remove the ‘#’ at the start of the line.
* Line 78: The user name of the sftp account.  Replace “ftpuser” and remove the ‘#’
* Line 79: The password of the sftp account. Replace “12345678” and remove the ‘#’
* Line 85: Where the backups are stored in the docker container. By default this is `/backups/`. Replace [% docker_backups_dir %] if it is not already filled in.
* Line 88 and 90: These lines contain commands to run the backup. Line 88 is for docker DBs while line 90 is for natively run postgres databases. These should not have to be changed but will be reviewed here.  There are three places where clinlims is used in each line, which can be confusing.
–U clinlims This is the user name for the account
–n \”clinlims\” This is the schema name.
Trailing clinlims.  This is the name of the database.
* Line 93: The directory where backups are kept. By default this is `/var/lib/openelis-global/backups/` . Replace [% db_backups_dir %] with the directory to your database backups if it is not already filled in.
* Line 94: The name of the installation. Replace [% installName %] to change the baseFileName used if it is not already filled in.
* Line 95:  If a device is mounted to the machine then another backup can be done to that device.  If the device has been removed then it will fail quietly.  Note that if the device is not available and is then made available then the missed backups will not be done. This line should look like “/Media/USB0/Backup”
* Line 123: The command to send backups off site. Remove the ‘#’ if you want to activate this functionality, otherwise only local backups are supported.

#### Method 1: inline editing
* On your computer, open /var/lib/openelis-global/backups/DatabaseBackup.pl with a command line text editor. I suggest using a command line editor like nano to edit the file in place as you will have to manually fill in fewer variables. Run:

``sudo nano -c /var/lib/openelis-global/backups/DatabaseBackup.pl`` 

* Edit lines 77, 78, 79, 95, 123 for your purposes, as noted above

* Save the file. 

#### Method 2: replace file 
* Open a copy of DatabaseBackup.pl with any text editor you have access to.

* Edit lines 71, 72, 76, 77, 78, 79, 85, 93, 94, 95, and 123 for your purposes, as noted above to match your server’s configuration

* Save your updated file

* Place your updated file in your server’s home directory

* Copy your file contents into /var/lib/openelis-global/backups/DatabaseBackup.pl. 

* Run on server:
	
``sudo cat ~/DatabaseBackup.pl > /var/lib/openelis-global/backups/DatabaseBackup.pl``
#### Testing

The script is a perl script and by tested by running

``sudo perl /var/lib/openelis-global/backups/DatabaseBackup.pl``

The end result will be a message from the curl application indicating that it has sent the file to the ftp server and seeing a new file on the ftp server, if there is an error, it indicates that something is not properly configured. 
