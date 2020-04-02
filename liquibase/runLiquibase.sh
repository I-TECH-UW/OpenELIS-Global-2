#!/bin/bash

echo "updating DB $2 with $1 as context"
if [ -z $3 ]
then
	java -jar -Dfile.encoding=utf-8 ./lib/liquibase-1.9.5.jar --defaultsFile=./liquibase.properties  --contexts=$1 --url=jdbc:postgresql://localhost:5432/$2 update
else
	java -jar -Dfile.encoding=utf-8 ./lib/liquibase-1.9.5.jar --defaultsFile=./liquibase.properties  --contexts=$1 --url=jdbc:postgresql://localhost:5432/$2 --password=$3 update
fi
