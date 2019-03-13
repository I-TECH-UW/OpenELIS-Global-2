#!/usr/bin/env python
# -*- coding: utf-8 -*-

localization = []
test_localization = []

used = ['']
local_file = open('input_files\\testName.txt','r')
test_local_file = open('input_files\\testLocalization.txt','r')

for line in local_file:
    if len(line) > 1:
        localization.append(line.strip())
local_file.close()

for line in test_local_file:
    if len(line) > 1:
        test_localization.append(line.strip())
test_local_file.close()
def esc_name(name):
    if "'" in name:
        return name.replace("'", "''")
    else:
        return name

sql_insert = "INSERT INTO clinlims.localization( id, description, english, french, lastupdated) VALUES\n\t(nextval( 'clinlims.localization_seq' ) ,"

local_results = open("output_files\\localization.sql", 'w')
for row in range(0, len(localization)):
        local_name = localization[row]
        if local_name not in test_localization and 'n/a' not in local_name:
	    test_localization.append(local_name)
            local_results.write(sql_insert)
            local_results.write(" 'test name' , '" + esc_name(local_name.strip()) + "' , '" + esc_name(local_name.strip()) + "', now());\n\t")
	    local_results.write("INSERT INTO clinlims.localization( id, description, english, french, lastupdated) VALUES\n\t(nextval( 'clinlims.localization_seq' ) ,")
	    local_results.write(" 'test report name' , '" + esc_name(local_name.strip()) + "' , '" + esc_name(local_name.strip()) + "', now());\n")

print ("Done Look for the results in localization.sql")

