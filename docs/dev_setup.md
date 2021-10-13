

## Developer Setup


## Install and Update Ubuntu (cur ver 20.04)



1. Boot to a live version of Ubuntu on a cd or a usb key
2. Install Ubuntu on the target hard drive 
3. Update ubuntu once it is finished installing
    1. sudo apt update
    2. sudo apt upgrade
    3. sudo apt full-upgrade
    4. sudo apt autoremove


## Install Java (cur ver11)

Detailed instructions found [here](https://www.digitalocean.com/community/tutorials/how-to-install-java-with-apt-on-ubuntu-18-04)



1. sudo apt install default-jdk
2. java -version
3. javac -version


## Install Docker 

Detailed instructions found [here](https://www.digitalocean.com/community/tutorials/how-to-install-and-use-docker-on-ubuntu-16-04)



1. curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
2. sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
3. sudo apt-get update
4. apt-cache policy docker-ce
5. sudo apt-get install -y docker-ce
6. sudo systemctl status docker


## Setup Docker (Optional)

Detailed instructions found [here](https://www.digitalocean.com/community/tutorials/how-to-install-and-use-docker-on-ubuntu-16-04). This will enable you to call docker without calling sudo.



1. sudo usermod -aG docker ${USER} 
2. su - ${USER}
3. id -nG


## Install Docker-Compose

Detailed instructions found [here](https://www.digitalocean.com/community/tutorials/how-to-install-docker-compose-on-ubuntu-18-04)



1. sudo curl -L https://github.com/docker/compose/releases/download/1.21.2/docker-compose-`uname -s`-`uname -m` -o /usr/local/bin/docker-compose
2. sudo chmod +x /usr/local/bin/docker-compose
3. docker-compose --version


## Install Maven

Detailed instructions [here](https://linuxize.com/post/how-to-install-apache-maven-on-ubuntu-18-04/)



1. sudo apt install maven
2. mvn -version


## Install Eclipse



1. Navigate to Eclipse download website (https://www.eclipse.org/downloads/)
2. Download the installer
3. Run the installer, choosing the Java ee version of eclipse
4. Create desktop icon for eclipse
    1. nano ~/.local/share/applications/.desktop
    2. paste into the file the following, replacing the path to your eclipse

            [Desktop Entry]


            Comment=Eclipse


            Terminal=false


            Name=Eclipse


            Exec=/path/to/your/version/eclipse/eclipse


            Type=Application


            Icon=/path/to/your/version/eclipse/icon.xpm


            StartupWMClass=Eclipse

    3. save the file
    4. chmod a+x ~/.local/share/applications/.desktop


## Install Chrome



1. Download Chrome
2. Run Chrome installer 
3. Make chrome your default browser


## Download OpenELIS-Global-2



1. Fork the repo at [https://github.com/I-TECH-UW/OpenELIS-Global-2](https://github.com/I-TECH-UW/OpenELIS-Global-2) 
2. cd /path/to/eclipse/workspace
3. git clone [git@github.com](mailto:git@github.com):{Your_Github_Account}/OpenELIS-Global-2.git
4. Import into Eclipse
    1. File > Import > Projects from Folder or Archive
    2. ensure OpenELIS-Global-2 is selected 
    3. ensure detect and configure project natures is selected
    4. finish


## Test If OpenELIS-Global-2 Deploys



1. Open terminal to the OpenELIS-Global-2 directory
2. mvn clean package dockerfile:build
3. docker-compose up
4. Access the application at [http://localhost:8080/OpenELIS-Global](http://localhost:8080/OpenELIS-Global)
5. Might need to dismiss a security warning


## Optionally Install Eclipse Plugins



1. Jaspersoft Reports
2. eGit


## Setup Tomcat in Eclipse


### Configure Eclipse to use Currently Supported Java



1. In terminal 
    1. sudo apt install openjdk-8-jre-headless
2. In eclipse
    2. Window > Preferences > Java > Installed JREs > Add > Next
    3. Specify your java 8 JRE directory (normally /usr/lib/jvm/java-8-openjdk-amd64)
    4. Finish
    5. Select java-8 and hit Apply


### Setup Tomcat Server



1. [Download tomcat](https://tomcat.apache.org/download-90.cgi) as tar.gz (cur ver 9)
2. Extract into to your eclipse workspace directory (or wherever you want, but remember the location)
3. Select servers tab in eclipse
4. Right click in the servers tab, and select new > server
5. Find tomcat 9 in the list, click it, and select next
6. Select your tomcat installation directory (wherever you extracted it in step 2)
7. Click next
8. Add Open-Elis-Global-2 to the server and click finish


### Configure Tomcat server



1. Double click on the tomcat 9.0 server in the servers tab
2. Click ‘Open launch configuration’
3. Navigate to the Arguments tab
4. Add the following 3 arguments to the VM arguments section, substituting whatever your local arguments are for your database
    1. -Ddatasource.password=clinlims
    2. -Ddatasource.username=clinlims
    3. -Ddatasource.url=jdbc:postgresql://localhost:15432/clinlims?currentSchema=clinlims
5. click OK to finish


### Configure Tomcat to Support ssl



1. Expand the Servers folder in the projects tab
2. Expand the server that runs OE
3. Copy the contents of OpenELIS-Global-2/dev/tomcat/server.xml into Tomcat v9.0 Server at localhost-config/server.xml
4. Edit Tomcat v9.0 Server at localhost-config/server.xml, substituting your paths
    1. replace &lt;Server port=”-1” shutdown=”SHUTDOWN”> with &lt;Server port=”8005” shutdown=”SHUTDOWN”>
    2. replace keystoreFile=”/run/secrets/keystore” with keystoreFile=”/path/to/openelis_global_2/dev/https/oe_selfsigned.keystore”
    3. replace truststoreFile="/run/secrets/truststore" with truststoreFile="/path/to/openelis_global_2/dev/https/oe_selfsigned.truststore"
    4. save


### Run Database in Docker



1. Comment out the oe.openelisci.org service in docker-compose.yml
2. docker-compose up -d


### Start Tomcat in Eclipse



1. Make sure your project builds
2. Click start server in normal, or debug mode