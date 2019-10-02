#!/bin/bash

#get location of this script
SOURCE="${BASH_SOURCE[0]}"
while [ -h "$SOURCE" ]; do # resolve $SOURCE until the file is no longer a symlink
  scriptDir="$( cd -P "$( dirname "$SOURCE" )" >/dev/null 2>&1 && pwd )"
  SOURCE="$(readlink "$SOURCE")"
  [[ $SOURCE != /* ]] && SOURCE="$DIR/$SOURCE" # if $SOURCE was a relative symlink, we need to resolve it relative to the path where the symlink file was located
done
scriptDir="$( cd -P "$( dirname "$SOURCE" )" >/dev/null 2>&1 && pwd )"

#
# This file exists ensures docker is installed on the target machine

if [ -x "$(command -v docker)" ]; then
    echo "docker already installed. Continuing"
else
    echo "Installing docker"
    bash ${scriptDir}/get-docker.sh
fi

if [ -x "$(command -v docker-compose)" ]; then
    echo "docker-compose already installed. Continuing"
else
    echo "Installing docker-compose"
    cp ${scriptDir}/../bin/docker-compose /usr/local/bin/docker-compose
    chmod +x /usr/local/bin/docker-compose
fi


