
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


def get_split_names( name ):
    split_name_list = name.split("/")
    for i in range(0, len(split_name_list)):
        split_name_list[i] = split_name_list[i].strip()

    return split_name_list


def esc_char(name):
    if "'" in name:
        return "$$" + name + "$$"
    else:
        return "'" + name + "'"


test_names = []
sample_types = []
current = []
type = []
result_type = []
norm_min = []
norm_max = []
valid_min = []
valid_max = []
dict_normal = []
dict_normal_results =[]
descriptions = []

current_tests_file = open('testNameCurrent.txt','r')
name_file = open('testNameDesired.txt','r')
sample_type_file = open("sampleType.txt")
result_type_file = open("resultType.txt", 'r')
norm_min_file = open("minNormal.txt", 'r')
norm_max_file = open("maxNormal.txt", 'r')
valid_min_file = open("minValid.txt", 'r')
valid_max_file = open("maxValid.txt", 'r')
dict_normal_file = open("dictNormal.txt", 'r')
result = open("resultLimitResults.txt", 'w')

for line in name_file:
    test_names.append(line.strip())
name_file.close()

for line in current_tests_file:
    current.append( line.strip())
current_tests_file.close()

for line in sample_type_file:
    sample_types.append(line.strip())
name_file.close()

for line in result_type_file:
    result_type.append(line.strip())

for line in norm_min_file:
    norm_min.append( line.strip())
norm_min_file.close()

for line in norm_max_file:
    norm_max.append( line.strip())
norm_max_file.close()

for line in valid_min_file:
    valid_min.append( line.strip())
valid_min_file.close()

for line in valid_max_file:
    valid_max.append( line.strip())
valid_max_file.close()

for line in dict_normal_file:
    dict_normal.append( line.strip())
dict_normal_file.close()

insert_head = "INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) \n\t"
insert_dict_head = "INSERT INTO result_limits(  id, test_id, test_result_type_id, lastupdated, normal_dictionary_id) \n\t"
next_val = " VALUES ( nextval( 'result_limits_seq' ) "
select_type_ages = " (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' "
select_dict_type = " (select id from clinlims.type_of_test_result where test_result_type = 'D' ) "

result.write("Copy the following into MassiveResultLimits.sql\n")

for row in range(0, len(test_names)):
    if len(current[row]) < 1 and result_type[row] == 'Numeric':  #is this a new test with numeric results
            description = esc_char(test_names[row] + "(" + sample_types[row] + ")")
            if description not in descriptions:
                split_min_limit = get_split_names(norm_min[row])
                split_max_limit = get_split_names(norm_max[row])
                print row
                for i in range(0, len(split_min_limit)):
                    descriptions.append(description)
                    result.write( insert_head )
                    result.write( next_val + ", ( select id from clinlims.test where description = " + description + " ) , \n\t\t\t" + select_type_ages + ", " )
                    result.write( "'gender' ," + split_min_limit[i] + "," + split_max_limit[i] + "," + valid_min[row] + "," + valid_max[row] + ", now() );\n")
    elif len(current[row]) < 1 and result_type[row] == 'Select List' and len(dict_normal[row]) > 1:  #is this a new test with dictionary
           description = esc_char(test_names[row] + "(" + sample_types[row] + ")")
           if description not in descriptions:
               descriptions.append(description)
               dict_normal_results.append( insert_dict_head )
               dict_normal_results.append( next_val + ", ( select id from clinlims.test where description = " + description + " ) ," + select_dict_type + ", " )
               dict_normal_results.append( " now() , (select max(id) from clinlims.dictionary where dict_entry = '" + dict_normal[row] + "' ) );\n")

result.write("\n\nCopy the following into MassiveDictResultLimits.sql\n")

for line in dict_normal_results:
    result.write(line)
    
print "Done look for results in resultLimitResult.txt"