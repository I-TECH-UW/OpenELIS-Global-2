#!/bin/bash
PROGNAME=$0

#get location of this script
SOURCE="${BASH_SOURCE[0]}"
while [ -h "$SOURCE" ]; do # resolve $SOURCE until the file is no longer a symlink
  buildDir="$( cd -P "$( dirname "$SOURCE" )" >/dev/null 2>&1 && pwd )"
  SOURCE="$(readlink "$SOURCE")"
  [[ $SOURCE != /* ]] && SOURCE="$DIR/$SOURCE" # if $SOURCE was a relative symlink, we need to resolve it relative to the path where the symlink file was located
done
buildDir="$( cd -P "$( dirname "$SOURCE" )" >/dev/null 2>&1 && pwd )"

#and other important locations
projectDir="${buildDir}/.."
resourcesDir="${projectDir}/src/main/resources"

echo "Please enter a default password that can be used to login into the default admin account that is created on install"
echo "This password will be stored in the created project war as a hash. "
echo "This is technically secure, but it is recommended to change the password on installation"

htpasswd -cBC 12 ${resourcesDir}/adminPassword.txt admin 
result=$?
while [ $result != 0 ]; do
  if [ $result == 3 ]
  then 
    echo "passwords did not match"
  else 
    echo "an error occured creating the password"
  fi
  while true; do
    read -p "try again? [Y]es [N]o: " yn
    case $yn in
      [Yy][Ee][Ss]|[Yy] ) break;;
      [Nn][Oo]|[Nn] ) exit;;
        * ) echo "Please answer yes or no.";;
    esac
  done
  htpasswd -cBC 12 ${resourcesDir}/adminPassword.txt admin 
  result=$?
done
	
