#!/usr/bin/env python
# -*- coding: utf-8 -*-

import os.path
import glob
import os
import sys
import string
import shutil
import stat
import time
from time import gmtime, strftime
from random import Random

type = []
panel = []
done_combos = []
type_file = open('sampleType.txt','r')
panel_file = open('panels.txt','r')
type_panel_results = open("typePanelResults.txt", 'w')

for line in type_file:
    type.append(line.strip())
type_file.close()

for line in panel_file:
    panel.append(line.strip())
panel_file.close()

for row in range(0, len(type)):
    if len(panel[row]) > 1:
            if (type[row]+panel[row]) not in done_combos:
                type_panel_results.write("INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES \n\t(nextval( 'sample_type_panel_seq') , ")
                type_panel_results.write("(select id from clinlims.type_of_sample where description = '" + type[row] + "' ) , ")
                type_panel_results.write("(select id from clinlims.panel where name = '" + panel[row] + "' ) );\n")
                done_combos.append(type[row]+panel[row])


type_panel_results.close()

print "Done look for results in typePanelResults.txt"