#!/usr/bin/perl -w
use File::Copy;
use File::stat;
use File::Basename;
use Cwd;

sub getTimeStamp {
	( $sec, $min, $hour, $mday, $mon, $year, $wday, $yday, $isdst ) =  localtime time ;
	my $fullYear  = $year + 1900;
	my $fullMonth = $mon + 1;

	return  "_" . $mday . "_" . $fullMonth . "_" . $fullYear . "_" . $hour . "_" . $min;
}

sub deleteOverAgedBackups {
	my $maxTimeSpan    = shift;
	my $cumulativeDir = shift;

	chdir $cumulativeDir;

	@$files = <*>;
	foreach my $file (@$files) {
		if ( time - $maxTimeSpan > stat($file)->mtime ) {
			unlink($file);
		}
	}
}

sub sendOffsite{
	my $queueDir = shift;
	my $upLoadtargetURL    = shift;
	my $upLoadUserName = shift;
	my $upLoadPassword = shift;
	
	my $maxRetryCount = 1;
	my $curlExe = 'curl';

	chdir "$queueDir";

	my @files = <$queueDir/*.backup.gz>; 

	foreach $file (@files) {
	    my $command = $curlExe . ' -T ' . $file . ' --user ' .$upLoadUserName . ':' . $upLoadPassword . ' ' . $upLoadtargetURL . basename($file);
        #print basename($file) . "\n";
  		my $retryCount = 0;
   		my $sendSuccess = 0; #false
	    
           
   		while ($retryCount < $maxRetryCount) {
   			my $curlReturn = `$command`;
        	my $returnStatus = $?;
			
        	if (($returnStatus != 0) ) {
        		print "Curl had an error. Curl said \n$curlReturn\n"
        				. "Return status $returnStatus\n";
        		$retryCount = $retryCount + 1;
        	} else {
        		$sendSuccess = 1; #true
        		last;
        	}
        	
        	sleep 7;
		}
           
		if ($sendSuccess) {
    		#remove file from system
    		unlink( $file );
    	}
	}           
}
my $db_install_type  = '[% db_install_type %]';
my $postgres_pwd_filepath = '/etc/openelis-global/secrets/OE_DB_USER_PASSWORD';
open my $fh, '<', $postgres_pwd_filepath or die "Can't open file $!";
read $fh, my $postgres_pwd, -s $fh;
my $keepFileDays  = 30;
my $siteId = '[% siteId %]';
#my $upLoadtargetURL = 'sftp://172.26.224.55/EFI/backup';
#my $upLoadUserName = 'ftpuser';
#my $upLoadPassword = '12345678';


my $snapShotFileBase     = 'lastSnapshot_' . $siteId; 
my $snapShotFileName     = $snapShotFileBase . '.backup'; 
my $snapShotFileNameZipped     = $snapShotFileName . '.gz'; 
my $databaseBackupMountPoint			 = '/var/lib/openelis-global/backups';
my $databaseDockerBackupDir	 			 = '/backups';
my $databaseDockerImageName				 = 'openelisglobal-database';
#for backups using docker image
my $docker_cmd = 'docker exec ' . $databaseDockerImageName . ' /usr/bin/pg_dump -U clinlims -f "' . $databaseDockerBackupDir . '/' . $snapShotFileName . '" -n \"clinlims\" clinlims';
#for backups using postgres running on the host
my $host_cmd = 'pg_dump -h localhost  -U clinlims -f "' . $snapShotFileName . '" -n \"clinlims\" clinlims'; 
my $zipCmd = 'gzip -f ' .  $snapShotFileName;
#my $backBaseDir          = cwd();
my $backBaseDir          = $databaseBackupMountPoint;
my $baseFileName         = '[% installName %]';
my $mountedBackup        = "/media/My\ Passport/backup";
my $dailyDir             = "$backBaseDir/daily";
my $cumulativeDir        = "$backBaseDir/cumulative";
my $queueDir             = "$backBaseDir/transmissionQueue";
my $timeStamp            = getTimeStamp();
my $todaysCummlativeFile = "$siteId$baseFileName$timeStamp.backup.gz";
my $maxTimeSpan = 60 * 60 * 24 * $keepFileDays;


$ENV{'PGPASSWORD'} = $postgres_pwd;

chdir "$dailyDir";
if ( $db_install_type eq "docker" ) {
	my $response = system("$docker_cmd")  and warn "Error while running: $! \n";
	copy( "$databaseBackupMountPoint/$snapShotFileName", "$dailyDir" ) or die "File cannot be copied.";
} else {
	my $response = system("$host_cmd")  and warn "Error while running: $! \n";
}
system("$zipCmd")  and warn "Error while running: $! \n";

copy( $snapShotFileNameZipped, "$cumulativeDir/$todaysCummlativeFile" ) or die "File cannot be copied.";
copy( $snapShotFileNameZipped, "$queueDir/$todaysCummlativeFile" ) or die "File cannot be copied.";
if (-d $mountedBackup) {
    copy( $snapShotFileNameZipped, "$mountedBackup/$todaysCummlativeFile" ) or die "File cannot be copied.";
}

deleteOverAgedBackups ($maxTimeSpan, $cumulativeDir);

#sendOffsite($queueDir, $upLoadtargetURL, $upLoadUserName, $upLoadPassword) or die "File cannot be copied on FTP server.";

   









