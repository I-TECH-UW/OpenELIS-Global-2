#!/usr/bin/env python
# -*- coding: utf-8 -*-


existing_types = []
sample_types = []

sample_type_file = open("sampleType.txt")
existing_types_file = open("currentSampleTypes.txt")
results = open("output/sampleTypeOutput.txt", 'w')


def write_massive_type_of_sample():
    results.write("\nPaste following in SampleType.sql\n\n")

    sql_insert = "INSERT INTO type_of_sample( id, description, domain, lastupdated, local_abbrev, display_key, is_active )\n\tVALUES ("
    for line in sample_types:
        if line not in existing_types and len(line) > 1:
            results.write(sql_insert)
            results.write( " nextval( 'type_of_sample_seq' ) , '" + line + "','H', now() , '" + line[:10] + "', 'sample.type." + line.split()[0] + "', 'Y');\n" );

def write_sample_type_order():
    results.write("\nPaste following in TypeOrder.sql\n\n")
    order = 10
    for line in sample_types:
        results.write("update clinlims.type_of_sample set sort_order=" + str(order) + " where description ILIKE '" + line + "';\n")
        order = order + 10

def write_inactive_list():
    results.write('\nPaste following in inactivateSampleTypes.sql in the set inactive list\n\n')
    results.write("update clinlims.type_of_sample set is_active=\'N\' where name in (")
    for line in existing_types:
        if line not in sample_types:
            results.write('\'' + line + '\', ')
        
    results.write(');')


for line in sample_type_file:
    if len(line) > 1:
        if line.strip() not in sample_types:
            if ',' in line:
                if 'Variable' not in sample_types:
                    sample_types.append('Variable')
            else:
                sample_types.append(line.strip())



for line in existing_types_file:
    if len(line) > 0:
        existing_types.append(line.strip())

existing_types_file.close()

write_massive_type_of_sample()
write_sample_type_order()
write_inactive_list()

print "Done check file sampleTypeOutput.txt"
results.close();