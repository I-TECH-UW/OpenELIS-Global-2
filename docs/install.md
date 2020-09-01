## Bare Metal Server Installation for OpenELIS Global 2.0 on Ubuntu 16.04 LTS

### Setup Ubuntu (16.04)

1. Boot Ubuntu from a CD. [Note: Use Ubuntu Server 16.04 LTS, or desktop if you want the GUI) [Download](http://releases.ubuntu.com/16.04/).
2. Select to download the updates in the background while installing. 
3. Select: Erase disk and install Ubuntu
4. Select the appropriate time zone 
5. Chose UI language 
6. Name the system: oeserver 
7. user itech 
8. set password and record it 
    * I suggest adding the ssh key for each support user to enable passwordless connection. 
9. Require a password on login
11. Select OpenSSH server during install *if running server version, if running desktop you will need to install it after*
	* this will allow you to ssh into this computer allowing copy/paste for Windows users through Putty, or connections via terminal on Mac and from the shell in LINUX

13. Finalize the ubuntu install

14. Reboot

**NOTE: I like to connect via ssh if I’m going to be using a lot of resources from my own computer here. This allows me to easily copy and paste commands below. For windows, the best utility I’ve found is Mobaxterm, as it incoperates a SCP client as well** [available for free download online](https://mobaxterm.mobatek.net/).
.

### Install Prerequisites for OpenELIS

1. Ensure that the system is connected to the internet properly, you can try to ping google DNS at 8.8.8.8

        ping 8.8.8.8

2. Open a command prompt and enter the following commands- this will install the needed services and install updates to the OS since the image was created. 
This updates the system from the sources in the sources list. It updates what new packages are available.

	    sudo apt-get update


        sudo apt-get upgrade

3. Install Python

        sudo apt-get install python
    
### Create and Load SSL Certificates

OpenELIS uses SSL certificates to securely communicate with other software or consolidated lab data servers. For a test or temporary instance, use a self-signed certificate, and for a production instance create a proper signed certifcate. **You must have a cert and key created and in the keystore and truststore for the installer to run**

#### Generate a .crt and .key file for the domain you want to use. 

The command below is for generating and using a self-signed certifcate. **Note: for FQDN use *.openelisci.org**


    sudo openssl req -x509 -nodes -days 365 -newkey rsa:2048 -keyout /etc/ssl/private/apache-selfsigned.key -out /etc/ssl/certs/apache-selfsigned.crt


#### Create keystore from key and cert 
Make the directories for the keystore

    sudo mkdir /etc/openelis-global/

make sure to record the password somewhere secure as you will need to enter it elsewhere)

    sudo openssl pkcs12 -inkey path/toyour/key -in path/to/your/cert -export -out /etc/openelis-global/keystore
    
enter an export password 
	
**Be sure to remember your keystore password, you will need it later**
	
For the self-signed certificate above, you would use:
	
    sudo openssl pkcs12 -inkey /etc/ssl/private/apache-selfsigned.key -in /etc/ssl/certs/apache-selfsigned.crt -export -out /etc/openelis-global/keystore

**Be sure to remember your keystore password, you will need it later **
	
#### Create truststore with OpenELIS-Global's cert (or a CA that signs OE certs)

1. using keytool (more reliable):
   
	    sudo apt-get install default-jre
   
        sudo keytool -import -alias oeCert -file path/to/your/cert -storetype pkcs12 -keystore /etc/openelis-global/truststore
	
	* set the truststore password 
	
	**Be sure to remember your truststore password, you will need it later **
	
	* when prompted if you want to trust the cert type `yes`
	
	For the self-signed certificate above, you would use:
	
        sudo keytool -import -alias oeCert -file /etc/ssl/certs/apache-selfsigned.crt -storetype pkcs12 -keystore /etc/openelis-global/truststore
	
	* set the truststore password 
	
	**Be sure to remember your truststore password, you will need it later **
	
	* when prompted if you want to trust the cert type `yes`
   
	
	
2. using openssl (less reliable, but doesn't require java):
  
        openssl pkcs12 -export -nokeys -in path/to/your/cert -out /etc/openelis-global/truststore

	For the self-signed certificate above, you would use:
	
	    openssl pkcs12 -export -nokeys -in /etc/ssl/certs/apache-selfsigned.crt -out /etc/openelis-global/truststore
    
### Install Postgresql
OpenELIS-Global is configured to be able to install a docker based version of Postgres, but this is generally not recommended for production databases
If you trust docker to provide your database, you can ignore this section

1. Install Postgresql

	    sudo apt install postgresql postgresql-contrib

2. Configure Postgresql

    Postgres gets configured automatically through the setup script. This might possibly interfere with other applications installed on the same server.

	
### Install OpenELIS Global

1. Install OpenELIS Global

    a. Download latest installer package: 

        curl -L -O https://url_for_the _file.tar.gz
 
    b. EG: for OE 2.0 Beta 1.2: 

        curl -L -O https://www.dropbox.com/s/rm0ugq857rrktc7/OpenELIS-Global_2.1.2.1_Installer.tar.gz
 
2. Unpack and enter the installer by running the following commands in Terminal, Mobaxterm, or Putty, replacing all in the { } with the appropriate values

        tar xzf {context_name}_{installer_version}_Installer.tar.gz
    
        cd {context_name}_{installer_version}_Installer
    
3. Optionally configure your install by editing setup.ini

	Find the section [DOCKER_VALUES] and set provide_database=True if you would like to use a Docker database

3. Run the install script in Terminal or Putty

        sudo python setup_OpenELIS.py

Wait while install procedure completes

4. Check if OpenELIS is running at http://{server_ip_address}:8080/OpenELIS-Global

Default user: admin
Default password: adminADMIN!

Configure the backup:

Follow the SOP at: [Backup Configuration](../backups)


