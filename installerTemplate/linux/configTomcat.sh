#!/bin/sh -vx

#Move a config file owned by a Debian package out of the way.
divert () {
    targetFile=$1
    if [ ! `dpkg-divert --list $targetFile` ]
    then
        dpkg-divert --rename --add $targetFile
    fi
    #Version of dpkg before 1.15 have a problem that cause *.distrib.dpkg-new to be created when they aren't needed. Fix that here.
    if [ -e $targetFile.distrib.dpkg-new ]
    then
        mv -f $targetFile.distrib.dpkg-new $targetFile.distrib
    fi
}


tomcatDefault=/etc/default/tomcat5.5
tomcatRun=/etc/init.d/tomcat5.5
divert $tomcatDefault
cp -f $tomcatDefault.distrib $tomcatDefault
sed -i -re "s/#?TOMCAT5_SECURITY=.*/TOMCAT5_SECURITY=no/" $tomcatDefault
#this SHOULD NOT be needed but there have been installations where the default has not been honored
sed -i -re "s/#?TOMCAT5_SECURITY=.*/TOMCAT5_SECURITY=no/" $tomcatRun

echo -n "
if [ -d \$JAVA_HOME/jre/lib/i386 ]; then
  # Workaround for Sun JVM bug
  export LD_LIBRARY_PATH=\$JAVA_HOME/jre/lib/i386/
fi

" >> $tomcatDefault

invoke-rc.d tomcat5.5 restart &>/dev/null &
