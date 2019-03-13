#!/usr/bin/env python
# -*- coding: utf-8 -*-

def esc_char(name):
    if "'" in name:
        return "$$" + name + "$$"
    else:
        return "'" + name + "'"

test_names = []
sample_types = []
guids = []
descriptions = []
update_guid_results = []


name_file = open('testName.txt','r')
sample_type_file = open("sampleType.txt")
guid_file = open("guid.txt")
result_file = open("../addGuid.sql", "w")

for line in name_file:
    test_names.append(line.strip())
name_file.close()

for line in sample_type_file:
    sample_types.append(line.strip())
name_file.close()

for line in guid_file:
    guids.append(line.strip())
guid_file.close()

for row in range(0, len(test_names)):
    if test_names[row]:
        description = esc_char(test_names[row] + "(" + sample_types[row] + ")")
        if description not in descriptions:
            descriptions.append(description)
            update_guid_results.append( "update clinlims.test set GUID='" + guids[row] + "' where description = " + description + ";\n" )
        else:
            print "duplicate row " + str(row) + " :" + description + " : guid " + guids[row]

for line in update_guid_results:
    result_file.write(line)

print "Done look for results in quidResult.sql"

