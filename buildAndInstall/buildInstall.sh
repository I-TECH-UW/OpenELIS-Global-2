#!/bin/bash
PROGNAME=$0
callDirectory=$PWD

installerDir="OEInstaller"
stagingDir="OEInstaller_stagingDir"

usage() {
  cat << EOF >&2
Usage: $PROGNAME [-b <branch>] [-l] [-i]

-b <branch>: git branch to build from
         -i: create installer
EOF
  exit 1
}

branch=master 
createInstaller=false
while getopts :b:i opt; do
  case $opt in
    (b) branch=$OPTARG;;
    (i) createInstaller=true;;
    (*) usage
  esac
done
shift "$((OPTIND - 1))"

#get location of this script
SOURCE="${BASH_SOURCE[0]}"
while [ -h "$SOURCE" ]; do # resolve $SOURCE until the file is no longer a symlink
  buildInstallDir="$( cd -P "$( dirname "$SOURCE" )" >/dev/null 2>&1 && pwd )"
  SOURCE="$(readlink "$SOURCE")"
  [[ $SOURCE != /* ]] && SOURCE="$DIR/$SOURCE" # if $SOURCE was a relative symlink, we need to resolve it relative to the path where the symlink file was located
done
buildInstallDir="$( cd -P "$( dirname "$SOURCE" )" >/dev/null 2>&1 && pwd )"

#and other important locations
projectDir="${buildInstallDir}/.."

cd ${projectDir}
echo Will build from $branch
#cd source/openelisglobal-core
#git checkout -- app/src/build.properties
git checkout $branch
if [ $? != 0 ]
then
    echo
    echo "branch is not local will try to create"
    echo 
    
    git checkout -b $branch origin/$branch

    if [ $? != 0 ]
	then
	echo
	echo "$branch not found in main repository. Check name"
	exit 1 
    fi
fi
git pull origin $branch

#git rev-list HEAD | tac | nl | tail -n 1 | sed 's/\t/ hash-/g'  |sed 's/\s\{2,\}/revision-/g' > ../../version.txt 
#cd ../..
#sed '2!d' source/openelisglobal-core/app/src/build.properties  > build.txt
cd ${callDirectory}

bash ${buildInstallDir}/build/createDefaultPassword.sh

echo "creating docker image"
#setup linux for tomcat docker to work
bash ${buildInstallDir}/install/linux/setupTomcatDocker.sh
	
#create the docker image
bash ${buildInstallDir}/build/build.sh -d
cd ${projectDir}
#run the docker image
echo "starting up docker"
docker-compose up -d
cd ${callDirectory}

createLinuxInstaller() {
	context=$1
	backupFile=$2
	
	echo "creating installer for context ${context}"
	mkdir -p ${installerDir}/linux/${context}
	cp -r ${buildInstallDir}/installerTemplate/linux/* ${installerDir}/linux/${context}
	cp OpenELIS-Global_DockerImage.tar.gz ${installerDir}/linux/${context}/dockerImage/${context}-${projectVersion}.tar.gz
	cp ${projectDir}/tools/DBBackup/installerTemplates/${backupFile} ${installerDir}/linux/${context}/templates/DatabaseBackup.pl
	cp ${projectDir}/tools/baseDatabases/${context}.backup ${installerDir}/linux/${context}/databaseFiles/databaseInstall.backup
	cp ${buildInstallDir}/install/linux/* ${installerDir}/linux/${context}/scripts/
	
	cp ${stagingDir}/get-docker.sh ${installerDir}/linux/${context}/scripts/
	chmod +x ${installerDir}/linux/${context}/scripts/*.sh
	
	cp ${stagingDir}/docker-compose ${installerDir}/linux/${context}/bin/docker-compose
	cd ${installerDir}/linux
	tar -cf ${context}_${projectVersion}_Installer.tar ${context}
	gzip ${context}_${projectVersion}_Installer.tar 
	cd ${callDirectory}
}

if [ $createInstaller == true ]
then
	cd ${projectDir}
	#get useful info from the maven project
	output=$({ echo 'ARTIFACT_ID=${project.artifactId}';\
	    echo 'PROJECT_VERSION=${project.version}'; } \
	  | mvn help:evaluate --non-recursive )
	artifactId=$(echo "$output" | grep '^ARTIFACT_ID' | cut -d = -f 2)
	projectVersion=$(echo "$output" | grep '^PROJECT_VERSION' | cut -d = -f 2)
	cd ${callDirectory}
	
	echo "saving docker image as OpenELIS-Global_DockerImage.tar.gz"
	docker save ${artifactId}:latest | gzip > OpenELIS-Global_DockerImage.tar.gz
	
	if [ -d "${installerDir}" ]
	then
		while true; do
		    read -p "Installer directory has been detected, replace it? [Y]es [N]o: " yn
		    case $yn in
		        [Yy][Ee][Ss]|[Yy] ) break;;
		        [Nn][Oo]|[Nn] ) exit;;
		        * ) echo "Please answer yes or no.";;
		    esac
		done
	fi
	rm -r ${installerDir}
	mkdir -p ${installerDir}
	
	mkdir ${stagingDir}
	curl -fsSL https://get.docker.com -o ${stagingDir}/get-docker.sh
	curl -L https://github.com/docker/compose/releases/download/1.21.2/docker-compose-`uname -s`-`uname -m` -o ${stagingDir}/docker-compose
	
	createLinuxInstaller OpenELIS-Global OffSiteBackupLinux.pl 

	
	rm OpenELIS-Global_DockerImage*.tar.gz
	rm -r ${stagingDir}
	
fi

