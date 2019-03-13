
#!/usr/bin/env python
# -*- coding: utf-8 -*-

order_kind = []
test_name = []
sample_type = []
handled_descriptions = ['']

def get_db_order_type( type ):
    if type == 'Bilan initial':
        return 'HIV_firstVisit'
    if type == 'Bilan de suivi':
        return 'HIV_followupVisit'
    if type == 'Bilan initial, Bilan de suivi':
        return 'HIV_firstVisit,HIV_followupVisit'
    return ""

def esc_char(name):
    if "'" in name:
        return "$$" + name + "$$"
    else:
        return "'" + name + "'"
    
test_file = open("testName.txt")
sample_file = open("sampleType.txt")
order_file = open("orderType.txt")
result = open("output/orderTypeResult.sql",'w')

for line in test_file:
    test_name.append(line.strip())
test_file.close()

for line in sample_file:
    sample_type.append(line.strip())
sample_file.close()

for line in order_file:
    order_kind.append(line.strip())
order_file.close()

if len( order_kind ) == 0:
    print 'No results for order type'
    exit()

for row in range(0, len(test_name)):
    if len(test_name[row]) > 1:
        description = esc_char(test_name[row] + "(" + sample_type[row] + ")")
        if description.strip() not in handled_descriptions:
            order_types = order_kind[row].split(",")
            for type_index in range(0, len(order_types)):
                handled_descriptions.append( description.strip())
                result.write("INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) \n\t")
                result.write("VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = '" + get_db_order_type(order_types[type_index].strip()) + "')," )
                result.write( "( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = " + description + "),'DISPLAY');\n")

result.close()

print "Done check orderTypeResult.sql for values"
