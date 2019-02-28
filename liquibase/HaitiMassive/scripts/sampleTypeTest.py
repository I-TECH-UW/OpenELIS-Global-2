#!/usr/bin/env python
# -*- coding: utf-8 -*-

import os.path
import glob
import os
import sys
import string
import shutil
import stat
import time
from time import gmtime, strftime
from random import Random

existing_types = [ "Sang",
"Plasma",
"Serum",
"Urine",
"Selles",
"Crachat",
"Secretion Urethrale",
"Secretion Vaginale",
"Pus",
"LCR",
"Liquide Ascite",
"Liquide Synovial",
"Liquide Amniotique",
"Liquide Pleural",
"Serum/Urine concentre du matin",
"Culot Urinaire",
"Secretions genito-urinaire",
"Aspiration nasopharyngée",
"Ecouvillonage nasal",
"Ecouvillonage nosapharyngé",
"Sécrétion de la gorge",
"DBS",
"Prélèvement rhinopharyngé"
]

def get_split_names( name ):
    split_name_list = name.split("/")
    for i in range(0, len(split_name_list)):
        split_name_list[i] = split_name_list[i].strip()

    return split_name_list



type = []
test_names = []
current = []
descriptions = []

sample_type_file = open("sampleType.txt")
current_tests_file = open('testNameCurrent.txt','r')
name_file = open('testNameDesired.txt','r')
results = open("sampleTypeTestResults.txt", 'w')

def esc_char(name):
    if "'" in name:
        return "$$" + name + "$$"
    else:
        return "'" + name + "'"

for line in sample_type_file:
    type.append(line.strip())
sample_type_file.close()

for line in name_file:
    test_names.append(line.strip())
name_file.close()

for line in current_tests_file:
    current.append( line.strip())
current_tests_file.close()

for row in range(0, len(test_names)):
    if len(test_names[row]) > 1:
                description = test_names[row] + "(" + type[row] + ")"
                test_select = " (select id from test where description = " + esc_char(description) + " ) "
                sample_select = "  (select id from type_of_sample where description = '" + type[row]+ "') "
                if description not in descriptions:
                    results.write("DELETE from clinlims.sampletype_test where test_id = " + test_select + " and sample_type_id =" + sample_select + ";\n" )
                    results.write("INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES \n")
                    results.write("\t(nextval( 'sample_type_test_seq' ) ," + test_select + " ,  " + sample_select + " );\n")
                    descriptions.append(description)


print "Done check sampleTypeTestResults.txt for results"
