#!/usr/bin/env python
# -*- coding: utf-8 -*-


existing_types = []

def get_split_names( name ):
    split_name_list = name.split("/")
    for i in range(0, len(split_name_list)):
        split_name_list[i] = split_name_list[i].strip()

    return split_name_list



type = []
test_names = []
descriptions = []

sample_type_file = open("sampleType.txt")
name_file = open('testName.txt','r')
existing_types_file = open("currentSampleTypes.txt")
results = open("output/sampleTypeTestResults.txt", 'w')

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

    return test_name + sample_type

def clean_sample_type( sample_type ):
    if ',' in sample_type:
        sample_type = 'Variable'

    return sample_type

for line in sample_type_file:
    type.append(line.strip())

sample_type_file.close()

for line in name_file:
    test_names.append(line.strip())
name_file.close()


for line in existing_types_file:
    if len(line) > 0:
        existing_types.append(line.strip())

existing_types_file.close()
for row in range(0, len(test_names)):
    if len(test_names[row]) > 1:
                description = create_description(test_names[row], type[row] )
                test_select = " (select id from test where description = " + esc_char(description) + " ) "
                sample_select = "  (select id from type_of_sample where description = '" + clean_sample_type(type[row]) + "') "
                if description not in descriptions:
                    results.write("DELETE from clinlims.sampletype_test where test_id = " + test_select + " and sample_type_id =" + sample_select + ";\n" )
                    results.write("INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES \n")
                    results.write("\t(nextval( 'sample_type_test_seq' ) ," + test_select + " ,  " + sample_select + " );\n")
                    descriptions.append(description)


print "Done check sampleTypeTestResults.txt for results"
