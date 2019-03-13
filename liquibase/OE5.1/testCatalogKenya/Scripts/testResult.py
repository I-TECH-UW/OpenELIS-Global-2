#!/usr/bin/env python
# -*- coding: utf-8 -*-


def convert_type_to_symbole( type):
    if type == 'Numeric' or type == 'numeric' :
        return 'N'
    if type == 'Free Text':
        return 'R'
    if type == 'Select list' or 'Select List':
        return 'D'
    if type == 'multi' or type == 'Multiple Select':
        return 'M'

    return type

def esc_char(name):
    if "'" in name:
        return "$$" + name + "$$"
    else:
        return "'" + name + "'"

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

def get_split_names( name ):
    split_name_list = name.split("/")
    for i in range(0, len(split_name_list)):
        split_name_list[i] = split_name_list[i].strip()

    return split_name_list

def get_comma_split_names( name ):
    split_name_list = [name]

    if ',' in name:
        split_name_list = name.split(",")
    elif ';' in name:
        split_name_list = name.split(";")

    for i in range(0, len(split_name_list)):
        split_name_list[i] = split_name_list[i].strip()

    return split_name_list

def resolve_result_type(result_type, selection_name , qualifed_names ):
    if result_type == 'D' and selection_name in qualifed_names:
        return 'Q'
    
    return result_type 

test_names = []
sample_types = []
select = []
type = []
descriptions = []
qualified_options = []

name_file = open('testName.txt','r')
sample_type_file = open("sampleType.txt")
select_file = open("selectList.txt", 'r')
result_type_file = open("resultType.txt", 'r')
qualifier_file = open("QualifiedOptions.txt", 'r')


results = open("output/MassiveTestResults.sql", 'w')

for line in name_file:
    test_names.append(line.strip())
name_file.close()

for line in sample_type_file:
    sample_types.append(line.strip())
sample_type_file.close()

for line in select_file:
    select.append(line.strip())
select_file.close()

for line in result_type_file:
    type.append( line.strip())
result_type_file.close()

for line in qualifier_file:
    qualified_options.append( line.strip())
qualifier_file.close()


nextVal = " VALUES ( nextval( 'test_result_seq' ) "
order = 10

for row in range(0, len(test_names)):
    if len(test_names[row]) > 1: #it's a new entry

        result_type = convert_type_to_symbole(type[row])
      
        description = create_description(test_names[row], sample_types[row])
        if description not in descriptions:
            descriptions.append(description)
            
            if result_type == 'D' or result_type == 'M':
                split_selections = get_comma_split_names( select[row])
                for j in range(0, len(split_selections)):
                    dictionary_select = " ( select max(id) from clinlims.dictionary where dict_entry =" + esc_char(split_selections[j].strip()) + " ) "
                    results.write("INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)\n\t")
                    results.write( nextVal + ", ( select id from clinlims.test where description = " + description + " ) , '")
                    results.write( resolve_result_type(result_type, split_selections[j], qualified_options[row] ) + "' , " + dictionary_select + " , now() , " + str(order) + ");\n")
                    order += 10
            else:
                results.write("INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)\n\t")
                results.write( nextVal + ", ( select id from clinlims.test where description = " + description + " ) , '")
                results.write( result_type + "' , null , now() , " + str(order) + ");\n")
                order += 10


print "Done results in MassiveTestResults.sql"