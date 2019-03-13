#!/usr/bin/env python
# -*- coding: utf-8 -*-

def trim( name, length):
    if len( name ) <= length:
        return name
    else:
        last_space = name.find(" ")
        if last_space == -1:
            return name[:35]
        else:
            return trim( name[:last_space], length)

def translate( name ):
    if name == 'Sang total':
        return 'Blood'
    else:
        return name


test_names = []
sample_type = []
name_results = []
guids = []

name_file = open('englishTestName.txt','r')
sample_type_file = open("sampleType.txt", 'r')
result_file = open("../localCode.sql", "w")
guid_file = open("guid.txt", "r")

for line in name_file:
    test_names.append(line.strip())
name_file.close()

for line in sample_type_file:
    sample_type.append(line.strip())
sample_type_file.close()

for line in guid_file:
    guids.append(line.strip())
guid_file.close()

updateString = "update clinlims.test set local_code='"

for row in range(0, len(test_names)):
    if test_names[row]:
        name_results.append(updateString)
        name_results.append(trim(test_names[row], 35) + "-" + translate(sample_type[row]) + "' where guid = '" + guids[row] + "';\n")


for line in name_results:
    result_file.write(line)

print "Done look for results in localCode.sql"

