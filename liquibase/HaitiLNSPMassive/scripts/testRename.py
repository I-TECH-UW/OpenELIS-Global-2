#!/usr/bin/env python
# -*- coding: utf-8 -*-



desired = []
current = []
type = []
derived_new = []
descriptions = []

desired_tests_file = open('testNameDesired.txt','r')
current_tests_file = open('testNameCurrent.txt','r')
sample_type_file = open("sampleType.txt")
test_rename_results = open("testRenameResults.txt", 'w')

for line in desired_tests_file:
    desired.append(line.strip())
desired_tests_file.close()

for line in current_tests_file:
    current.append( line.strip())
current_tests_file.close()

for line in sample_type_file:
    type.append(line.strip())
sample_type_file.close()

test_rename_results.write("This should go in MassiveTestRename.sql. \n\n")

for row in range(0, len(desired)):
    if len(current[row]) > 1:
            description = desired[row] + "(" + type[row] + ")"
            if description not in descriptions:
                descriptions.append(description)

                if "'" in desired[row]:
                   test_rename_results.write("------")
                test_rename_results.write("update clinlims.test set description = '" + description + "',local_abbrev = '" + desired[row][:20] + "',name = '" + desired[row] + "' where description = '"   + current[row] + "';\n")

test_rename_results.close()

print "Done look for results in testRenameResults.txt"

