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


english_test_names = []
french_test_names = []
sample_types = []
name_results = []
guids = []

english_name_file = open('englishTestName.txt','r')
french_name_file = open('testName.txt', 'r')
sample_type_file = open("sampleType.txt", 'r')
result_file = open("../localCode.sql", "w")
guid_file = open("guid.txt", "r")

for line in english_name_file:
    english_test_names.append(line.strip())
english_name_file.close()

for line in french_name_file:
    french_test_names.append(line.strip())
french_name_file.close()

for line in sample_type_file:
    sample_types.append(line.strip())
sample_type_file.close()

for line in guid_file:
    guids.append(line.strip())
guid_file.close()

updateString = "update clinlims.test set local_code='"

for row in range(0, len(guids)):
    if guids[row]:
        test_name = english_test_names[row] if english_test_names[row] else french_test_names[row]
        sample_type = sample_types[row] if sample_types[row] else "variable"
        name_results.append(updateString)
        name_results.append(trim(test_name, 35) + "-" + translate(sample_type) + "' where guid = '" + guids[row] + "';\n")


for line in name_results:
    result_file.write(line)

print "Done look for results in localCode.sql"

