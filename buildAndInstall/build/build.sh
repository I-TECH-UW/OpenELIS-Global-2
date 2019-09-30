#!/bin/bash
PROGNAME=$0

usage() {
  cat << EOF >&2
Usage: $PROGNAME [-b <branch>] [-d]

-b <branch>: git branch to build from
         -d: build docker images on machine
EOF
  exit 1
}

branch=master dockerBuild=false
while getopts :b:dm opt; do
  case $opt in
    (b) branch=$OPTARG;;
    (d) dockerBuild=true;;
    (*) usage
  esac
done

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