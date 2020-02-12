#!/bin/bash
PROGNAME=$0
callDirectory=$PWD

installerCreationDir="OEInstaller"
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

createLinuxInstaller() {
	context=$1
	backupFile=$2
	installerName="${context}_${projectVersion}_Installer"
	
	echo "creating installer for context ${context}"
	mkdir -p ${installerCreationDir}/linux/${installerName}
	cp -r ${buildInstallDir}/installerTemplate/linux/* ${installerCreationDir}/linux/${installerName}
	cp OpenELIS-Global_DockerImage.tar.gz ${installerCreationDir}/linux/${installerName}/dockerImage/${context}-${projectVersion}.tar.gz
	cp Postgres_DockerImage.tar.gz ${installerCreationDir}/linux/${installerName}/dockerImage/Postgres_DockerImage.tar.gz
#	cp ${projectDir}/tools/DBBackup/installerTemplates/${backupFile} ${installerCreationDir}/linux/${context}/templates/DatabaseBackup.pl
#	cp ${projectDir}/database/baseDatabase/OpenELIS-Global.sql ${installerCreationDir}/linux/${installerName}/database/baseDatabase/databaseInstall.sql
	cp ${buildInstallDir}/install/linux/* ${installerCreationDir}/linux/${installerName}/scripts/
	
	cp ${stagingDir}/get-docker.sh ${installerCreationDir}/linux/${installerName}/scripts/
	chmod +x ${installerCreationDir}/linux/${installerName}/scripts/*.sh
	
	cp ${stagingDir}/docker-compose ${installerCreationDir}/linux/${installerName}/bin/docker-compose
	cd ${installerCreationDir}/linux
	tar -cf ${installerName}.tar ${installerName}
	gzip ${installerName}.tar 
	cd ${callDirectory}
}

if [ $createInstaller == true ]
then
	cd ${projectDir}
	
	if [ -d "${installerCreationDir}" ]
	then
		while true; do
		    read -p "Installer directory has been detected, replace it? [Y]es [N]o: " yn
		    case $yn in
		        [Yy][Ee][Ss]|[Yy] ) break;;
		        [Nn][Oo]|[Nn] ) exit;;
		        * ) echo "Please answer yes or no.";;
		    esac
		done
		rm -r ${installerCreationDir}
	fi
	mkdir -p ${installerCreationDir}
	
	#get useful info from the maven project
	output=$({ echo 'ARTIFACT_ID=${project.artifactId}';\
	    echo 'PROJECT_VERSION=${project.version}'; } \
	  | mvn help:evaluate --non-recursive )
	artifactId=$(echo "$output" | grep '^ARTIFACT_ID' | cut -d = -f 2)
	projectVersion=$(echo "$output" | grep '^PROJECT_VERSION' | cut -d = -f 2)
	cd ${callDirectory}
	
	echo "saving docker image as OpenELIS-Global_DockerImage.tar.gz"
	docker save ${artifactId}:latest | gzip > OpenELIS-Global_DockerImage.tar.gz
	docker save postgres:9.5 | gzip > Postgres_DockerImage.tar.gz
	
	mkdir ${stagingDir}
	curl -fsSL https://get.docker.com -o ${stagingDir}/get-docker.sh
	curl -L https://github.com/docker/compose/releases/download/1.21.2/docker-compose-`uname -s`-`uname -m` -o ${stagingDir}/docker-compose
	
	createLinuxInstaller OpenELIS-Global OffSiteBackupLinux.pl 

	
	rm OpenELIS-Global_DockerImage*.tar.gz
	rm Postgres_DockerImage.tar.gz
	rm -r ${stagingDir}
	
fi

