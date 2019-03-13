The AnaylzzerFileBroker is configured through a combination of command line arguments and a configuration file.  The configuration file is in XML format.
A sample is provided and then sample will be annotated. 

---command line ---------

java -jar AnalyzerFileBroker [options...] arguments...

  Example: java -jar AnalyzerFileBroker -accountName analyzerUser -password secret -configPath .\BrokerConfiguration.xml -lang EN  -url http://211.118.16.04:8080/importAnalyzer
  
 -accountName (-n) String : Credential name for OpenELIS service
 -configPath File         : The path to configuration file may be either
                            absolute or relative (default: .\BrokerConfiguration
                            .xml)
 -console (-c)            : Should the log also be output to the console.
                            (default: true)
 -help (-h)               : Prints the help to the console (default: false)
 -lang (-l) [EN | FR]     : Language used in logs and console. (default: EN)
 -log String              : Path and name for log
 -logBacklog (-b) Numeric : Time in days that the log should be kept (default:
                            30)
 -password (-pwd) String  : Credential password for OpenELIS service
 -period (-p) Numeric     : The time period, in seconds, between attempts to
                            read the analyzer directory.  May be overridden in
                            the configuration file (default: 5)
 -pre5__3                 : Errors are handled slightly different for OpenElis
                            5.2 and older.  If the installed instance is 5.3 or
                            older then this flag should be set (default: false)
 -printConfig (-config)   : Prints a printConfig configuration file to be
                            modified and used by user (default: false)
 -test (-t)               : Tests the url, name and password and the analyzer
                            directories to make sure they exist and are correct
                            (default: false)
 -url (-u) String         : The full url for openELIS


--sample -----------------

<?xml version="1.0" encoding="ISO-8859-1"?>

<AnalyzerFileBroker version="1.0">
    <global>
        <locale value="EN"/>
        <period value="5" />
        <connection url="http://localhost:8080/importAnalyzer" name="analyzer" password="ied1poh2Ku!"/>
        <log path="" backlog="30"/>
        <pre53 value="true" />
    </global>
    <analyzers>
        <analyzer name = "FasCalibur" sourceDir="D:\analyzerSource\FasCalibur"  />
		<analyzer name = "SysmeXT.xml" sourceDir="D:\analyzerSource\SysmeXT" period="10"  />
    </analyzers>
</AnalyzerFileBroker>

--- annotation --------------

<?xml version="1.0" encoding="ISO-8859-1"?>  -- required for all xml formated files

<AnalyzerFileBroker version="1.0">  -- head to define configuration.  As of now the version should always be 1.0
    <global>  -- head to define global values for the AnalyzerFileBroker
        <locale value="EN"/>  -- Language used in logs and information written to the console.  May be either EN or FR.  The default is EN
        <period value="5" />  -- How often, in seconds, AnalyzerFileBroker should check to see if a new file has been written to the source directory by the analyzer. 
												The application will try and send all unsent files to OpenELIS after a new file has been found.  The default is 5
												This value can be different for each analyzer is then specified in the analyzer section
        <connection url="http://localhost:8080/importAnalyzer" name="analyzer" password="secret"/>  -- Required. url is the url of OpenELIS. 
																																					name is login name for OpenELIS service.  This is a user configured in OpenELIS with NO roles
																																					password is the password for the account											
        <log path="" backlog="30"/>  Allows logging to be written to a file.
						path The absolute or relative path to the directory for the log files
						backlog The number of days the log files should be kept.  Default is 30.  Currently logging to a file has not been implemented so until then this value is ignored
        <pre53 value="true" /> To verify that the url, account name and the password are correct a file is sent to OpenELIS but OpenELIS does not know what to do with that file
and responds with an error.  Setting this flag to true will allow the AnalyzerFileBroker to ignore that error		
    </global>  --end of global section
    <analyzers>  -- head of analyzer section  There may be one or more analyzers
        <analyzer name = "FasCalibur"  prefix=" FasCalibur"  sourceDir="D:\analyzerSource\FasCalibur"  /> configuration for this analyzer
			name -- required Name of the analyzer.  Will be used in the log file and information written to the console
			prefix -- optional The value will be prefixed to the name of the file sent to OpenELIS.  After OpenELIS is upgraded it will be able to use the name of the file to detirmine which analyzer sent the file.
			sourceDir -- required The directory that the analyzer writes it's results.  
		<analyzer name = "SysmeXT.xml" sourceDir="D:\analyzerSource\SysmeXT" period="10"  />  This analyzer has the optional value of how often AnalyzerFileBroker checks for new files from the analyzer.  This value
		             overwrites the global vale or the value specified on the command line.  Each analyzer may have a different value.
    </analyzers>
</AnalyzerFileBroker>

 