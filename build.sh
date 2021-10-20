#!/bin/bash
PROGNAME=$0
CALL_DIR=$PWD

INSTALLER_CREATION_DIR="OEInstaller"
STAGING_DIR="OEInstaller_stagingDir"

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
  BUILD_SCRIPT_DIR="$( cd -P "$( dirname "$SOURCE" )" >/dev/null 2>&1 && pwd )"
  SOURCE="$(readlink "$SOURCE")"
  [[ $SOURCE != /* ]] && SOURCE="$DIR/$SOURCE" # if $SOURCE was a relative symlink, we need to resolve it relative to the path where the symlink file was located
done
BUILD_SCRIPT_DIR="$( cd -P "$( dirname "$SOURCE" )" >/dev/null 2>&1 && pwd )"
INSTALL_DIR="${BUILD_SCRIPT_DIR}/install"

#and other important locations
PROJECT_DIR="${BUILD_SCRIPT_DIR}"
JPA_SERVER_DIR="${PROJECT_DIR}/hapi-fhir-jpaserver-starter/"
DATA_EXPORT_DIR="${PROJECT_DIR}/dataexport/"
#CONSOLIDATED_SERVER_DIR="${PROJECT_DIR}/Consolidated-Server/"

if [ $createInstaller == true ]
then
	cd ${CALL_DIR}
	
	if [ -d "${INSTALLER_CREATION_DIR}" ]
	then
		while true; do
		    read -p "Installer directory has been detected, replace it? [Y]es [N]o: " yn
		    case $yn in
		        [Yy][Ee][Ss]|[Yy] ) break;;
		        [Nn][Oo]|[Nn] ) exit;;
		        * ) echo "Please answer yes or no.";;
		    esac
		done
		rm -r ${INSTALLER_CREATION_DIR}
	fi
fi

cd ${PROJECT_DIR}
echo Will build from $branch
#cd source/openelisglobal-core
#git checkout -- app/src/build.properties
git checkout -B $branch
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
git submodule update --recursive

#git rev-list HEAD | tac | nl | tail -n 1 | sed 's/\t/ hash-/g'  |sed 's/\s\{2,\}/revision-/g' > ../../version.txt 
#cd ../..
#sed '2!d' source/openelisglobal-core/app/src/build.properties  > build.txt
cd ${CALL_DIR}

echo "creating docker images"
#create jpaserver docker image
#bash ${INSTALL_DIR}/buildProject.sh -dl ${JPA_SERVER_DIR}
#create dataexport jar so it can be used in OpenELIS
#bash ${INSTALL_DIR}/buildProject.sh -l ${DATA_EXPORT_DIR}
#create data import docker image
#bash ${INSTALL_DIR}/buildProject.sh -dl ${CONSOLIDATED_SERVER_DIR}
#create the docker image 
bash ${INSTALL_DIR}/buildProject.sh -dl ${PROJECT_DIR} -t openelisglobal

createLinuxInstaller() {
	context=$1
	backupFile=$2
	installerName="${context}_${projectVersion}_Installer"
	
	echo "creating installer for context ${context}"
	mkdir -p ${INSTALLER_CREATION_DIR}/linux/${installerName}
	cp -r ${INSTALL_DIR}/installerTemplate/linux/* ${INSTALLER_CREATION_DIR}/linux/${installerName}
	cp OpenELIS-Global_DockerImage.tar.gz ${INSTALLER_CREATION_DIR}/linux/${installerName}/dockerImage/${context}-${projectVersion}.tar.gz
	cp Postgres_DockerImage.tar.gz ${INSTALLER_CREATION_DIR}/linux/${installerName}/dockerImage/Postgres_DockerImage.tar.gz
	cp JPAServer_DockerImage.tar.gz ${INSTALLER_CREATION_DIR}/linux/${installerName}/dockerImage/JPAServer_DockerImage.tar.gz
	cp AutoHeal_DockerImage.tar.gz ${INSTALLER_CREATION_DIR}/linux/${installerName}/dockerImage/AutoHeal_DockerImage.tar.gz
#	cp DataImporter_DockerImage.tar.gz ${INSTALLER_CREATION_DIR}/linux/${installerName}/dockerImage/DataImporter_DockerImage.tar.gz
#	cp DataSubscriber_DockerImage.tar.gz ${INSTALLER_CREATION_DIR}/linux/${installerName}/dockerImage/DataSubscriber_DockerImage.tar.gz
#	cp ${PROJECT_DIR}/tools/DBBackup/installerTemplates/${backupFile} ${INSTALLER_CREATION_DIR}/linux/${context}/templates/DatabaseBackup.pl
#	cp ${PROJECT_DIR}/database/baseDatabase/OpenELIS-Global.sql ${INSTALLER_CREATION_DIR}/linux/${installerName}/database/baseDatabase/databaseInstall.sql
	
	cp ${STAGING_DIR}/get-docker.sh ${INSTALLER_CREATION_DIR}/linux/${installerName}/scripts/
	chmod +x ${INSTALLER_CREATION_DIR}/linux/${installerName}/scripts/*.sh
	
	cp ${STAGING_DIR}/docker-compose ${INSTALLER_CREATION_DIR}/linux/${installerName}/bin/docker-compose
	cd ${INSTALLER_CREATION_DIR}/linux
	tar -cf ${installerName}.tar ${installerName}
	gzip ${installerName}.tar 
	cd ${CALL_DIR}
}

if [ $createInstaller == true ]
then
	cd ${PROJECT_DIR}
	#get useful info from the maven project
	output=$({ echo 'ARTIFACT_ID=${project.artifactId}';\
	    echo 'PROJECT_VERSION=${project.version}'; } \
	  | mvn help:evaluate --non-recursive )
	artifactId=$(echo "$output" | grep '^ARTIFACT_ID' | cut -d = -f 2)
	projectVersion=$(echo "$output" | grep '^PROJECT_VERSION' | cut -d = -f 2)
	cd ${CALL_DIR}
	
	echo "saving docker image as OpenELIS-Global_DockerImage.tar.gz"
	docker save openelisglobal:latest | gzip > OpenELIS-Global_DockerImage.tar.gz
	echo "saving Postgres docker image"
	docker pull postgres:9.5
	docker save postgres:9.5 | gzip > Postgres_DockerImage.tar.gz
	echo "saving JPA Server docker image"
	docker pull hapiproject/hapi:v5.4.1
	docker save hapiproject/hapi:v5.4.1 | gzip > JPAServer_DockerImage.tar.gz
	echo "saving Autoheal docker image"
	docker pull willfarrell/autoheal:1.2.0
	docker save willfarrell/autoheal:1.2.0 | gzip > AutoHeal_DockerImage.tar.gz
	
	
#	docker save hapi-fhir-jpaserver-starter:latest | gzip > JPAServer_DockerImage.tar.gz
#	echo "saving Data Importer docker image"
#	docker save dataimport-webapp:latest | gzip > DataImporter_DockerImage.tar.gz
#	echo "saving Data Subscriber docker image"
#	docker save datasubscriber-webapp:latest | gzip > DataSubscriber_DockerImage.tar.gz
	
	mkdir ${STAGING_DIR}
	echo "downloading scripts for docker installation and docker-compose installation"
	curl -fsSL https://get.docker.com -o ${STAGING_DIR}/get-docker.sh
	curl -L https://github.com/docker/compose/releases/download/1.21.2/docker-compose-`uname -s`-`uname -m` -o ${STAGING_DIR}/docker-compose
	
	createLinuxInstaller OpenELIS-Global OffSiteBackupLinux.pl 

	
	rm OpenELIS-Global_DockerImage*.tar.gz
	rm Postgres_DockerImage.tar.gz
	rm JPAServer_DockerImage.tar.gz
	rm AutoHeal_DockerImage.tar.gz
#	rm DataSubscriber_DockerImage.tar.gz
#	rm DataImporter_DockerImage.tar.gz
	rm -r ${STAGING_DIR}
	
fi
