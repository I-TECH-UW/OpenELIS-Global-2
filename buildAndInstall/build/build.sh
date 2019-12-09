#!/bin/bash
PROGNAME=$0

usage() {
  cat << EOF >&2
Usage: $PROGNAME [-b <branch>] [-d]

         -d: build docker images on machine
EOF
  exit 1
}

branch=master dockerBuild=false
while getopts :dm opt; do
  case $opt in
    (d) dockerBuild=true;;
    (*) usage
  esac
done

#build and package application
if [ $dockerBuild == true ]
then
	#build the war, and create the docker image
	mvn clean package dockerfile:build
else
	mvn clean package
fi
	
if [ $? != 0 ]
then
    echo
    echo build failed
    exit $?
fi