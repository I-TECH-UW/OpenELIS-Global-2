# OpenELIS Global - Implementation Notes

## Assessments




## Equipments needs
we are working on the deployment of an application using network configuration. 
The main application is installed on a computer (server). 
several client workstations will be able to access to this server via a network to be configured.

	* * server minimal configuration
	
	`my Processor:	Core i3
	`my RAM:	4 Go
	`my Hard Disk:	500 Go
	`my External Hard Disk:	500 Go
	`my Ethernet Output available or provide wifi key
	`my UPS: 1000 /1500 VA

	* * workstation minimal configuration
	
	`my Processor:	Dual core
	`my RAM:	4 Go
	`my Hard Disk:	500 Go
	`my External Hard Disk:	500 Go
	`my Ethernet Output available or provide wifi key
	`my UPS: 650 VA
	
	* * Network Configuration
	
	`my Wifi Router
	`my provide wifi key or ethernt cable



## Server and workstations Configurations



## Training




## Installation




## Assistance



##Setting up for Eclipse


Creation Steps

1. Create a new Java project. Accept all of the defaults. The name of
   the project should match the name of the analyzer.
2. Open up the properties for the project
3. Select the Java build path (left hand side of the dialog)
4. Select projects tab and add (right hand side of the dialog)
   openElisGlobal 1

.. _`https://github.com/openelisglobal/openelisglobal-plugins`: https://github.com/openelisglobal/openelisglobal-plugins
.. _SourceTree: http://www.sourcetreeapp.com/


# How to add Analyzer on the main OpenELIS project?
1. The plugins must be compile on a jar file
2. the jar file must be copy on the folder: -- to update --
3. the main project will be recompile including the jar file, the plugin is proper upload when its available in the menu.
4. login to OpenELIS -> Results -> from Analyzer -> Analyzer name.



# How are analyzer results imported to OpenELIS?

The model being used is that the analyzer will write a results file to the file system and a perl script will monitor the directory where the results files are written. That directory is referred to as the staging directory.  When a new file is detected it is copied to another directory called the transmissionQueue.  The script will then send the file to a service in OpenELIS and as soon as it is successfully sent it will be deleted from the transmissionQueue.

Some points to keep in mind:

1. The file is copied from the staging directory to the transmissionQueue.  Management of the staging directory is not part of the model

1. The script depends on the timestamp of the files in the staging directory to insure that they are not moved to the transmissionQueue more than once.  There are some strangely named files in the transmissionQueue that track the timestamp, do not delete them or all of the files from the staging directory will be resent.

1. The file in the transmissionQueue folder is deleted after successful transmission.

1. During development it is easiest to copy (not move) the test file to the transmissionQueue directory rather than updating the timestamp and adding it to the staging directory.

The perl file is in openelisgloble-core\tools\AnalyzerSideDataImport\FlatFileTransport.pl .
Note that it exist to type of export file , one for windows environment and another for linux. 
the main difference is how we mention the folder path in the perl script.

Depending on where perl is installed on your system the script will be invoked with something similar to: `c:\Perl\bin\perl.exe FlatFileTransport.pl`

There are four configurable values in the file:
   *  *The location of curl*  
   Curl is the application that actually sends the file to OpenELIS.  In the perl script it is assumed that it is installed in the root of the C drive. `my $curlExe = 'C:\curl\curl.exe';`  If it differs on the host machine this should be edited

   *  *The target application*  
   In the following case the application is haitiOpenElis on the local host.
`my $upLoadtargetURL = 'https://localhost:8443/[openelisURL]/importAnalyzer';`

   *  *The user name and password.*  
   This user name and password must have been added as a user to the application.  The user should be added with no roles.
`my $upLoadUserName = '';
my $upLoadPassword = '';`


   *  *The location of the staging directory.*  
   The directory to which the analyzer is writing it's output files.  In the perl script is an example of a single script supporting two analyzers but one of them has been commented out.  In this example the name of the directory is a directory relative to the perl script named staging
`my $stagingDir1 = ".\\staging";` -- for windows

 `my $stagingDir1 = ".//staging";` -- for linux

    * In this example the directory has been mapped from one machine to the machine the perl script is on to a drive named Y
'my $stagingDir2 = "Y:";`

##Troubleshooting:

Perl and curl give good error messages so most problems in the script will be obvious.  However sometimes the server returns errors which are not as obvious.

If you get an HTML response it is one of two things.

1.  A response from Tomcat that it could not find the resources.  This is usually about three lines of HTML and buried in it is the missing resource message.  That indicates that the url is not correct
    
1. A response from openELIS than an exception has been thrown.  This is the HTML for the gray screen of death and is usually dozens of lines wrong.  This indicates that there was an error in the code and you should look for the stack trace in the console or in the logs

Another common message is that the user name or password was not correct.  Revisit the section above about setting them correctly 
