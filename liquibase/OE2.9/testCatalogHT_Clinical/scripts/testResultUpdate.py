#!/usr/bin/env python
# -*- coding: utf-8 -*-
from compiler.pyassem import RAW


def convert_type_to_symbole( type):
    if type == 'Numeric' or type == 'numeric' :
        return 'N'
    if 'Free Text' in type:
        return 'R'
    if type == 'Select list' or type == "Select List":
        return 'D'
    if type == 'multi' or type == 'Multi Select':
        return 'M'

    return type

def esc_char(name):
    if "'" in name:
        return "$$" + name + "$$"
    else:
        return "'" + name + "'"

def get_split_names( name ):
    split_name_list = name.split("/")
    for i in range(0, len(split_name_list)):
        split_name_list[i] = split_name_list[i].strip()

    return split_name_list

def get_comma_split_names( name ):
    split_name_list = [name]

    if ',' in name:
        split_name_list = name.split(",")
    elif ';' in name:
        split_name_list = name.split(";")

    for i in range(0, len(split_name_list)):
        split_name_list[i] = split_name_list[i].strip()

    return split_name_list

test_names = []
sample_types = []
select = []
type = []
current_descriptions = []
descriptions = []

name_file = open('testName.txt','r')
sample_type_file = open("sampleType.txt")
select_file = open("selectList.txt", 'r')
result_type_file = open("resultType.txt", 'r')
current_description_file = open("currentTestDescription.txt")

results = open("output/testResults.sql", 'w')
hand_results = open("output/handTestResults.txt", 'w')



for line in name_file:
    test_names.append(line.strip())
name_file.close()

for line in sample_type_file:
    sample_types.append(line.strip())
sample_type_file.close()

for line in select_file:
    select.append(line.strip())
select_file.close()

for line in result_type_file:
    type.append( line.strip())
result_type_file.close()

for line in current_description_file:
    current_descriptions.append(line.strip())
current_description_file.close()

nextVal = " VALUES ( nextval( 'test_result_seq' ) "
order = 10
for row in range(0, len(test_names)):
    if len(test_names[row]) > 1: #it's a new entry

        result_type = convert_type_to_symbole(type[row])
        raw_description = test_names[row] + "(" + sample_types[row] + ")"
        description = esc_char(raw_description)
        if description not in descriptions:
            descriptions.append(description)
            if result_type == 'D' or result_type == 'M':
                if raw_description in current_descriptions:
                    split_selections = get_comma_split_names( select[row])
                    for j in range(0, len(split_selections)):
                        hand_results.write(raw_description + ";" + result_type +  ";" + split_selections[j].strip() + "  order: " + str(order) + "\n" )
                    order += 10
                else:    
                    split_selections = get_comma_split_names( select[row])
                    for j in range(0, len(split_selections)):
                        dictionary_select = " ( select max(id) from clinlims.dictionary where dict_entry =" + esc_char(split_selections[j].strip()) + " ) "
                        results.write("INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)\n\t")
                        results.write( nextVal + ", ( select id from clinlims.test where description = " + description + " ) , '")
                        results.write( result_type + "' , " + dictionary_select + " , now() , " + str(order) + ");\n")
                        order += 10
            else:
                if raw_description in current_descriptions:
                   results.write("UPDATE clinlims.test_result SET tst_rslt_type='" + result_type + "', sort_order=" + str(order) + "\n\t" )
                   results.write( "WHERE test_id= ( select id from clinlims.test where description = " + description + " );\n" ) 
                else:
                    results.write("INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)\n\t")
                    results.write( nextVal + ", ( select id from clinlims.test where description = " + description + " ) , '")
                    results.write( result_type + "' , null , now() , " + str(order) + ");\n")
                order += 10

print "Done results in testResults.sql"