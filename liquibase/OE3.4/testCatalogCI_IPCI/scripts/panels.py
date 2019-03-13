#!/usr/bin/env python
# -*- coding: utf-8 -*-

panels = []

used = ['']
panel_file = open('panels.txt','r')

for line in panel_file:
    if len(line) > 1:
        panels.append(line.strip())
panel_file.close()

def esc_name(name):
    if "'" in name:
        return "$$" + name.strip() + "$$"
    else:
        return "'" + name.strip() + "'"

sql_insert = "INSERT INTO clinlims.panel( id, name, description, lastupdated, display_key, sort_order) VALUES\n\t(nextval( 'panel_seq' ) ,"
count = 10
panel_results = open("output/panelResults.sql", 'w')
for row in range(0, len(panels)):
        panel_name = panels[row]
        if panel_name not in used and 'n/a' not in panel_name:
            used.append(panel_name)
            panel_results.write(sql_insert)
            panel_results.write(" '" + panel_name + "' , " + esc_name(panel_name) + " , now() ,'panel.name." + panel_name.split()[0] + "' ," + str(count) + ");\n")
            count += 10

print "Done Look for the results in panelResults.sql"

