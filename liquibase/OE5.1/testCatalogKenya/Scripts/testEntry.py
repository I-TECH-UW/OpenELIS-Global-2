#!/usr/bin/env python
# -*- coding: utf-8 -*-


test_names = []
test_sections = []
sample_types = []
descriptions = []
print_names = []
old_sort = []
uom = []
loinc_codes = []
sort_order = []

name_file = open('testName.txt','r')
test_section_file = open("testSections.txt",'r')
sample_type_file = open("sampleType.txt")
uom_file = open("newUOM.txt", 'r')
print_name_file = open("printName.txt")
loinc_file = open("loincCodes.txt")
results = open("output/testResults.txt", 'w')
sort_order_file = open("testOrder.txt")

def convert_to_existing_name( name ):


    if name == 'Hemato-immunologie' or name == 'H�mato-immunologie':
        return 'Hemto-Immunology'
    elif name == 'Biochimie':
        return 'Biochemistry'
    elif name == 'H�matologie' or name == 'Hematologie':
        return 'Hematology'
    elif name == 'Immunologie':
        return 'Immunology'
    elif name == 'Immunologie-Serologie' or name == 'Serology':
        return 'Serology-Immunology'
    elif name == 'Bacteriologie':
        return 'Bacteria'
    return name


def esc_char(name):
    if "'" in name:
        return "$$" + name + "$$"
    else:
        return "'" + name + "'"


def use_uom( uom ):
    return  len(uom) > 0 and uom != 'n/a'

def remove_test_name_markup( test_name):
    if '*' in test_name:
        test_name = test_name.split(')')[1].strip()

    return test_name

def create_description( test_name, sample_type):
    test_name = remove_test_name_markup(test_name)

    if ',' in sample_type:
        sample_type = ''
    else:
        sample_type =  '(' + sample_type + ')'

    return esc_char(test_name + sample_type )

for line in name_file:
    test_names.append(line.strip())

for line in print_name_file:
    if '?' in line:
        line = ''
    print_names.append(line.strip())
print_name_file.close()

for line in test_section_file:
    test_sections.append(line.strip())

for line in sample_type_file:
    sample_types.append(line.strip())
    
name_file.close()
test_section_file.close()

for line in uom_file:
    uom.append(line.strip())
uom_file.close()

for line in loinc_file:
    loinc_codes.append(line.strip())
loinc_file.close()

base_sort_order = 600
last_order_value = base_sort_order
for line in sort_order_file:
    if len(line) > 0:
        value = int(line.strip()) * 10
        sort_order.append( base_sort_order + value)
        last_order_value = base_sort_order + value
    else:
        last_order_value += 10
        sort_order.append( last_order_value )

sql_head = "INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc, orderable )\n\t"
results.write("The following should go in Tests.sql Note\n")


for row in range(0, len(test_names)):
    if len(test_names[row]) > 1:
        description =  create_description(test_names[row], sample_types[row])
        if description not in descriptions:
                    descriptions.append(description)
                    orderable = 'true'
                    if '*' in test_names[row]:
                        orderable = 'false'
                    results.write( sql_head)
                    results.write("VALUES ( nextval( 'test_seq' ) ," )
                    if use_uom(uom[row]):
                        results.write(" ( select id from clinlims.unit_of_measure where name='" + uom[row] + "') , ")
                    else:
                        results.write(" null , ")
                    results.write( description + " , " + esc_char(print_names[row]) + " , 'Y' , 'N' , now() , ")
                    results.write("(select id from clinlims.test_section where name = '" + convert_to_existing_name(test_sections[row]) + "' ) ,")
                    results.write( esc_char( remove_test_name_markup(test_names[row])[:20]) + " ," + str(sort_order[row]) + " , " + esc_char(remove_test_name_markup(test_names[row])) + " , " + esc_char(loinc_codes[row] ) + ", " + orderable + ");\n")

results.close()

print "Done look for results in testResults.txt"