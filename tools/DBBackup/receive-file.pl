#!/usr/bin/perl -w                                                                                       

use strict;
use Data::Dumper;

#The CGI module only looks at TMPDIR which isn't normally set in Windows. Set it here.
$ENV{'TMPDIR'} = $ENV{'TMP'};
#The CGI module must be loaded after this change so we can not use 'use'.
require CGI;
import CGI;

my $uploadDirectory = '/var/www/paul-test/files/';

my $query = new CGI;
print $query->header;

#Save the uploaded files to disk.
#foreach my $fileParameterName ('dataFileName','reportFileName','errorFileName') {
foreach my $fileParameterName ($query->param) {
    my $uploadedFileHandle = $query->upload($fileParameterName);
    my $uploadedFileName = $query->param($fileParameterName);

    if (defined $uploadedFileHandle) {
	my $localFileHandle;
	my $localFileName = $uploadDirectory . $uploadedFileName;
	open($localFileHandle, '>', $localFileName);
	binmode($localFileHandle);
	if (! defined $localFileHandle) {
	    print "Can not open target file $localFileName.\n";
	    exit;
	}
	
	while (my $line = <$uploadedFileHandle>) {
	    print $localFileHandle $line;
	}

	close($localFileHandle);
    } else {
	#print "Can not open uploaded file. ($fileParameterName)\n";
	#exit;
	next;
    }
}

#Write a file containing any other parameters that were sent with the files.
{
    my %parameterHash;
    map {$parameterHash{$_} = $query->param($_) . ''} $query->param;
    # the . '' thing is to make file references strings rather then filehandlers

#    my $metaFileName = $uploadDirectory . $parameterHash{dataFileName} . '-meta.txt';
    my $metaFileName = $uploadDirectory . 'last-params.txt';
    my $metaFileHandle;
    open($metaFileHandle, '>', $metaFileName);
    if (! defined $metaFileHandle) {
	print "Can not open meta data file.\n";
	exit;
    }

    local $Data::Dumper::Indent = 0;
    print $metaFileHandle (Data::Dumper->Dump([\%parameterHash], ['parameterHash']) . "\n");

    close($metaFileHandle);
}

#If we make it this far everything went ok. So send back a 'success' message.
print 'success';
