#!/usr/bin/env python
# -*- coding: utf-8 -*-

def convert_to_existing_name( name ):

    if name == 'Hemato-immunologie' or name == 'Hémato-immunologie':
        return 'Hemto-Immunology'
    elif name == 'Biochimie':
        return 'Biochemistry'
    elif name == 'Hématologie':
        return 'Hematology'
    elif name == 'Immunologie':
        return 'Immunology'
    elif name == 'Immunologie-Serologie':
        return 'Serology-Immunology'
    return name

section_file = open( "testSections.txt")
results = open("MassiveSectionOrder.sql", 'w')
handled_sections = []

order = 10

for line in section_file:
    if len(line) > 1:
        if line not in handled_sections:
            handled_sections.append(line)
            results.write( "update clinlims.test_section set sort_order=" + str(order) + " where name = '" + convert_to_existing_name( line.strip() ) + "';\n" )
            order = order + 10

print "Done check MassiveSectionOrder.sql"