#!/usr/bin/env python
# -*- coding: utf-8 -*-

section = []
used = ['']

section_file = open( "input_files/testSections.txt")
results = open("output_files/testSection.sql", 'w')


for line in section_file:
    if len(line) > 1:
        section.append(line.strip(","))
section_file.close()


sql_insert = "INSERT INTO clinlims.test_section( id, name, description, org_id, is_external, lastupdated, display_key, sort_order, is_active) VALUES\n\t("
count = 100
section_results = open("output_files/testSection.sql", 'w')
for row in range(0, len(section)):
        section_name = section[row]
        if section_name not in used and 'n/a' not in section_name:
            used.append(section_name)
            section_results.write(sql_insert)
            section_results.write("nextval('clinlims.test_section_seq'), '" + section_name.strip() + "' , '" + section_name.strip() + "', 13704, 'N', now() ,'test.section." + section_name.split()[0].strip() + "' ," + str(count) + ", 'Y');\n")
	    count += 10

print "Done Look for the results in testSection.sql"
