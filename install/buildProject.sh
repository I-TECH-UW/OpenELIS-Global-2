#!/bin/bash
PROGNAME=$0

usage() {
  cat << EOF >&2
Usage: $PROGNAME [-d] [-l]
         -d: build docker images on machine
         -l = <project-dir>: location of the project to build
EOF
  exit 1
}

dockerBuild=false DIR="./"
while getopts :dl: opt; do
  case $opt in
    (d) dockerBuild=true;;
    (l) DIR=${OPTARG};;
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
	#build the war, and create the docker image
	mvn clean install dockerfile:build -DskipTests
else
	mvn clean install -DskipTests
fi
	
if [ $? != 0 ]
then
    echo
    echo build failed
    exit $?
fi