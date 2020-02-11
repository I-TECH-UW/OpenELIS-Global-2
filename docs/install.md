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
    a. I suggest adding the ssh key for each support user to enable passwordless connection. 
9. Require a password on login
10. Create a 1GB partition mounted at /web
11. Select OpenSSH server during install *if running server version*

a    .this will allow you to ssh into this computer allowing copy/paste for Windows users through Putty, or connections via terminal on Mac and from the shell in LINUX

13. Finalize the ubuntu install

14. Reboot

**NOTE: I like to connect via ssh if I’m going to be using a lot of resources from my own computer here. This allows me to easily copy and paste commands below. For windows, the best utility I’ve found is Mobaxterm, as it incoperates a SCP client as well** [available for free download online](https://mobaxterm.mobatek.net/).
.

### Install Prerequisites for OpenELIS

1. Ensure that the system is connected to the internet properly, you can try to ping google DNS at 8.8.8.8

    ``ping 8.8.8.8``

2. Open a command prompt and enter the following commands- this will install the needed services and install updates to the OS since the image was created. 
This updates the system from the sources in the sources list. It updates what new packages are available.

    ``sudo apt-get update``


    ``sudo apt-get upgrade``

3. Install Python

    ``sudo apt-get update``

    ``sudo apt-get upgrade``

    ``sudo apt-get install python``
    
### Create and Load SSL Certificates

1. Generate a signed .crt and .key for individual installation

2. Place files in server as:

    ``/etc/tomcat/ssl/certs/tomcat_cert.crt``

    ``/etc/tomcat/ssl/private/tomcat_cert.key``
    
### Install Postgresql
OpenELIS-Global is configured to be able to install a docker based version of Postgres, but this is generally not recommended for production databases
If you trust docker to provide your database, you can ignore this section

1. Install Postgresql

	``sudo apt update``
	
	``sudo apt install postgresql postgresql-contrib``

	
### Install OpenELIS Global

1. Install OpenELIS Global

    a. Download latest installer package: 

    ``curl -L -O https://url_for_the _file.tar.gz``
 
    b. EG: for OE 2.0 Beta 1: 

    ``curl -L -O https://www.dropbox.com/s/2z04rng0tt7txbg/CDI_RegLabOpenElis_0.0.1-Beta_Installer.tar.gz?dl=0``
 
2. Unpack and enter the installer by running the following commands in Terminal, Mobaxterm, or Putty, replacing all in the { } with the appropriate values

    ``tar xzf {context_name}_{installer_version}_Installer.tar.gz``
    
    ``cd {context_name}_{installer_version}_Installer``
    
3. Optionally configure your install by editing setup.ini

	Find the section [DOCKER_VALUES] and set provide_database=True if you would like to use a Docker database

3. Run the install script in Terminal or Putty

     ``sudo python setup_OpenELIS.py ``

Wait while install procedure completes

4. Check if OpenELIS is running at http://{server_ip_address}:8080/OpenELIS-Global

Configure the backup:

Follow the SOP at: 
https://docs.google.com/document/d/1HNGaeUdFIe6n_bd7Sz1q9lpMmAyymbb1H8_8GjTVYfc/edit

