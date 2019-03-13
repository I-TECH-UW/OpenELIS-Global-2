#!/usr/bin/env python
# -*- coding: utf-8 -*-

import os.path
import glob
import os
import re
import sys
import string
import shutil
import stat
import time
from time import gmtime, strftime
from random import Random

old = []

old_file = open("currentUOM.txt")
new_file = open("newUOM.txt")
result = open("MassiveUOM.sql",'w')

for line in old_file:
    old.append(line.strip())
old_file.close()

for line in new_file:
    if len(line) > 1:
        values = line.split(',')

        for value in values:
            if value.strip() not in old:
                old.append(value.strip())
                result.write("INSERT INTO unit_of_measure( id, name , description, lastupdated) \n\t")
                result.write("VALUES ( nextval( 'unit_of_measure_seq' ) , '" + value.strip() + "' , '" + value.strip() + "' , now());\n")

result.close()

print "Done check MassiveUOM.sql for values"
