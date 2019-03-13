#!/usr/bin/env python
# -*- coding: utf-8 -*-

panel_file = open('panels.txt','r')
name_file = open('testName.txt','r')
sample_type_file = open("sampleType.txt")
test_panel_results = open("testPanelResults.txt", 'w')
panel = []
type = []
test_names = []

def get_split_names( name ):
    split_name_list = name.split("/")
    for i in range(0, len(split_name_list)):
        split_name_list[i] = split_name_list[i].strip()

    return split_name_list


def esc_char(name):
    if "'" in name:
        return "$$" + name + "$$"
    else:
        return "'" + name + "'"


for line in panel_file:
    panel.append(line.strip())
panel_file.close()

for line in sample_type_file:
    type.append(line.strip())
sample_type_file.close()

for line in name_file:
    test_names.append(line.strip())
name_file.close()

test_panel_results.write("Below should be pasted to MassiveTestPanel.csv\n\n")

for row in range(0, len(test_names)):
    if len(panel[row]) > 1:
            test_description = esc_char(test_names[row] + "(" + type[row] + ")")
            test_panel_results.write("nextval( 'panel_item_seq' ) , (select id from panel where name = '" + panel[row] + "')")
            test_panel_results.write(" , (select id from test where description = " + test_description + ") , null , now() \n")

                
test_panel_results.close()

print "Done look for results in testPanelResults.txt"