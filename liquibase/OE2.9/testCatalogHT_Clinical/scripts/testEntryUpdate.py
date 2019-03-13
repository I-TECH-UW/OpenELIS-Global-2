#!/usr/bin/env python
# -*- coding: utf-8 -*-


test_names = []
test_sections = []
sample_types = []
descriptions = []
current_descriptions = []
print_names = []
old_sort = []
uom = []
hand_edit = ['PaCO2','HCO3']
name_file = open('testName.txt','r')
test_section_file = open("testSections.txt",'r')
sample_type_file = open("sampleType.txt")
uom_file = open("newUOM.txt", 'r')
print_name_file = open("printName.txt")
current_description_file = open("currentTestDescription.txt")
results = open("output/testResults.txt", 'w')

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
    return name


def esc_char(name):
    if "'" in name:
        return "$$" + name + "$$"
    else:
        return "'" + name + "'"


def use_uom( uom ):
    return  len(uom) > 0 and uom != 'n/a'

for line in name_file:
    test_names.append(line.strip())
name_file.close()

for line in print_name_file:
    print_names.append(line.strip())
print_name_file.close()

for line in test_section_file:
    test_sections.append(line.strip())
test_section_file.close()

for line in sample_type_file:
    sample_types.append(line.strip())
sample_type_file.close()    

for line in uom_file:
    uom.append(line.strip())
uom_file.close()

for line in current_description_file:
    current_descriptions.append(line.strip())
current_description_file.close()


sql_head = "INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )\n\t"
update_head = "Update test SET "

sort_count = 10
for row in range(0, len(test_names)):
    if len(test_names[row]) > 1:
        raw_description = test_names[row] + "(" + sample_types[row] + ")"
        description = esc_char( raw_description)
        if description not in descriptions:
                    descriptions.append(description)
                    if raw_description in current_descriptions:
                        results.write( update_head )
                        results.write( "uom_id=" )
                        if use_uom(uom[row]):
                            results.write(" ( select id from clinlims.unit_of_measure where name='" + uom[row] + "') , ")
                        else:
                            results.write(" null , ")
                        results.write( "reporting_description="  + esc_char(print_names[row]) + ",is_active='Y', lastupdated=now(),")
                        results.write("test_section_id=(select id from clinlims.test_section where name = '" + convert_to_existing_name(test_sections[row]) + "' ) ,")
                        results.write("sort_order=" + str(sort_count) + " \n\t" )
                        results.write("WHERE description = " + description + ";\n")
                    else:    
                        results.write( sql_head)
                        results.write("VALUES ( nextval( 'test_seq' ) ," )
                        if use_uom(uom[row]):
                            results.write(" ( select id from clinlims.unit_of_measure where name='" + uom[row] + "') , ")
                        else:
                            results.write(" null , ")
                        results.write( description + " , " + esc_char(print_names[row]) + " , 'Y' , 'N' , now() , ")
                        results.write("(select id from clinlims.test_section where name = '" + convert_to_existing_name(test_sections[row]) + "' ) ,")
                        results.write( esc_char(test_names[row][:20]) + " ," + str(sort_count) + " , " + esc_char(test_names[row]) + " );\n")
                    sort_count += 10

results.close()

print "Done look for results in testResults.txt"