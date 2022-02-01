#!/bin/bash
PROGNAME=$0

usage() {
  cat << EOF >&2
Usage: $PROGNAME [-d] [-l]
         -d: build docker images on machine
         -l = <project-dir>: location of the project to build
         -t = <tag>: tag the docker image
EOF
  exit 1
}

dockerBuild=false DIR="./" TAG=""
while getopts :dl:t: opt; do
  case $opt in
    (d) dockerBuild=true;;
    (l) DIR=${OPTARG};;
    (t) TAG=${OPTARG};;
    (*) usage
  esac
done

if [ -d "$DIR" ]; then 
	cd ${DIR}
	echo "building in ${DIR}"
else
	echo "could not find directory '${DIR}' to build"
	exit
fi
#build and package application
if [ $dockerBuild == true ]
then
	if [ -z "${TAG}" ]
	then
		docker build . 
	else
		docker build -t ${TAG} . 
	fi
else
	mvn clean install -DskipTests
fi
	
if [ $? != 0 ]
then
    echo
    echo build failed
    exit $?
fi