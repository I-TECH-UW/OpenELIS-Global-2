#!/usr/bin/env python
# -*- coding: utf-8 -*-

def convert_to_existing_name( name ):

    if name == 'Hemato-Immunologie':
        return 'Hematology'
    elif name == 'Biochimie':
        return 'Biochemistry'
    elif name == 'Mycologie':
        return 'Mycobacteriology'
    elif name == 'Parasitologie':
        return 'Parasitology'
    elif name == 'Immuno-virologie':
        return 'Immunology'
    elif name == 'Immuno-Virologie':
        return 'Immunology'
    elif name == 'CDV':
        return 'VCT'
    elif name == 'Bacteriologie':
        return 'Bacteria'
    elif name == 'Serologie- Virologie':
        return 'Virologie'
    elif name == 'Biologie Moleculaire':
        return 'Biologie Moleculaire'
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