#!/usr/bin/env python
# -*- coding: utf-8 -*-


test_names = []
guids = []
populateNames = []
name_results = []

name_file = open('englishTestName.txt','r')
sample_type_file = open("sampleType.txt")
guid_file = open("guid.txt")
result_file = open("../populateNames.sql", "w")

for line in name_file:
    test_names.append(line.strip())
name_file.close()

for line in guid_file:
    guids.append(line.strip())
guid_file.close()

insertString = "INSERT INTO localization(  id, description, english, french, lastupdated)\n"
updateNameString = "update clinlims.test set name_localization_id = lastval() where guid ='"
updateReportingString = "update clinlims.test set reporting_name_localization_id = lastval() where guid ='"




for row in range(0, len(guids)):
    if guids[row]:
        english_name = ("'" + test_names[row] + "'" ) if test_names[row] else "(select name from clinlims.test where guid = '" + guids[row] + "' )"
        name_results.append(insertString)
        name_results.append("\tVALUES ( nextval('localization_seq'), 'test name', " + english_name + ", (select name from clinlims.test where guid = '" + guids[row] + "' ), now());\n")
        name_results.append(updateNameString + guids[row] + "';\n")

        english_reporting = ("'" + test_names[row] + "'" ) if test_names[row] else "(select reporting_description from clinlims.test where guid = '" + guids[row] + "' )"
        name_results.append(insertString)
        name_results.append("\tVALUES ( nextval('localization_seq'), 'test report name', " + english_reporting + ", (select reporting_description from clinlims.test where guid = '" + guids[row] + "' ), now());\n")
        name_results.append(updateReportingString + guids[row] + "';\n")

for line in name_results:
    result_file.write(line)

print "Done look for results in populateNames.sql"

