#! /usr/bin/python

from optparse import OptionParser
from pyPgSQL import PgSQL
from decimal import *

__author__ = "paulsc"
__date__ = "$Apr 20, 2011 1:29:05 PM$"

conn = ""
cursor = ""
test_fields = "id, method_id, uom_id, description, loinc, reporting_description, sticker_req_flag, is_active, active_begin, active_end, is_reportable, time_holding, time_wait, time_ta_average, time_ta_warning, time_ta_max, label_qty, lastupdated, label_id, test_trailer_id, test_section_id, scriptlet_id, test_format_id, local_abbrev, sort_order, name, display_key"
test_sample_type_map = dict()
type_id_to_name_map = dict()

prefix_to_id_map = dict()

def connect( password, database):
    global conn
    global cursor
    conn = PgSQL.connect(host="localhost", user="clinlims", database=database, password=password)
    cursor = conn.cursor()

def update_database( password, database):
    connect(password, database)

    get_tests_with_common_prefixs()
    #list_prefixs()
    collapse_associations()
    conn.commit()
    cursor.close()
    conn.close()


def get_tests_with_common_prefixs():
    global prefix_to_id_map
    cursor.execute("select id, description from clinlims.test")
    rows = cursor.fetchall()

    tmpMap = dict()

    for row in rows:
        id, name = row

        prefix = get_name_prefix( name )

        if  prefix in tmpMap:
            collapsing_ids = tmpMap[prefix]
        else:
            collapsing_ids = []
            tmpMap[prefix] = collapsing_ids

        collapsing_ids.append( id )

    for key in tmpMap.keys():
        if len(tmpMap[key]) > 1:
            prefix_to_id_map[key] = tmpMap[key]

def get_name_prefix( name ):
    #special case Cell Epitheliales
    if name.find("Cell Epith") != -1:
        return "Cell Epith"

    return name.split("(", 1)[0].rstrip()

def list_prefixs():
    for key in prefix_to_id_map:
        print key

def collapse_associations():
    for key in prefix_to_id_map:
        associated_test_ids = prefix_to_id_map[key]

        focal_test_id = ""

        consolidate = 1
        
        if special_case(key):
            print "Special casing " + key
            focal_test_id = get_special_case_focal_id_for( key, associated_test_ids )
            associated_test_ids.remove( focal_test_id )
        else:    
            focal_test_id = associated_test_ids.pop()

            if test_results_differ(key, focal_test_id, associated_test_ids ):
                print "\tReference test id: " + str(focal_test_id) + " comparing test ids "  + list_to_comma_separated_string( associated_test_ids )
                consolidate = False

            if result_limits_differ(key, focal_test_id, associated_test_ids ):
                print "\tReference test id: " + str(focal_test_id) + " comparing test ids " + list_to_comma_separated_string( associated_test_ids )
                consolidate = False

            if test_reflexes_differ(key, focal_test_id, associated_test_ids ):
                print "\tReference test id: " + str(focal_test_id) + " comparing test ids " + list_to_comma_separated_string( associated_test_ids )
                consolidate = False

            if test_analyte_differ(key, focal_test_id, associated_test_ids):
                print "\tReference test id: " + str(focal_test_id) + " comparing test ids " + list_to_comma_separated_string( associated_test_ids )
                consolidate = False


        if not consolidate:
            continue

        print "Consolidating " + key
        idString = list_to_comma_separated_string( associated_test_ids)

        focal_test_id = str( focal_test_id )
    
        cursor.execute("delete from clinlims.result_limits where test_id in ( " + idString + ")")
        cursor.execute("delete from clinlims.test_reflex where test_id in (  " + idString + " )")
        cursor.execute("delete from clinlims.test_analyte where test_id in (  " + idString + " )")
        update_test_result_id( idString, focal_test_id, key)
        cursor.execute("delete from clinlims.test_result where test_id in (  " + idString + " )")
        cursor.execute("update clinlims.sampletype_test set test_id = %s where test_id in (  " + idString + " )", (focal_test_id ))
        cursor.execute("update clinlims.analysis set test_id = %s where test_id in (  " + idString + " )", (focal_test_id))
        cursor.execute("update clinlims.referral_result set test_id = %s where test_id in (  " + idString + " )", (focal_test_id))
        cursor.execute("delete from clinlims.test where id in (  " + idString + " )")

        rename = rename_test(focal_test_id )

        if rename.find("Culture") != -1:
            update_results_table( focal_test_id )


def special_case( prefix ):
    return prefix.find("Bact") != -1 or \
           prefix.find("Chlore") != -1 or \
           prefix.find("Vitesse") != -1 or \
           prefix.find("VIH Elisa") != -1 or \
           prefix.find("Malaria") != -1 or \
           prefix.find("Mantoux") != -1 or \
           prefix.find("VIH Western Blot") != -1 or \
           prefix.find("Cell Epith") != -1 or \
           prefix.find("Culture") != -1 or \
           prefix.find("VIH Test Rapide") != -1


def get_special_case_focal_id_for( prefix, test_id_list ):
    if prefix.find("Bact") != -1:
        return find_id_for_type( "Urine", test_id_list )
    elif prefix.find("Chlore") != -1:
        return find_id_for_type( "Serum", test_id_list  )
    elif prefix.find("Vitesse") != -1:
        return find_id_for_type( "Sang", test_id_list  )
    elif prefix.find("VIH Elisa") != -1:
        return test_id_list[0]
    elif prefix.find("Cell Epith") != -1:
        return find_correct_cell_epith( test_id_list)
    elif prefix.find("Malaria") != -1:
        return find_correct_malaria( test_id_list)
    elif prefix.find("Mantoux") != -1:
        return find_correct_matoux( test_id_list)
    elif prefix.find("VIH Western Blot") != -1:
        return find_id_for_type( "DBS", test_id_list  )
    elif prefix.find("VIH Test Rapide") != -1:
        return find_id_for_type( "DBS", test_id_list  )
    elif prefix.find("Culture") != -1:
        return find_correct_culture( test_id_list)

    return ""

def find_id_for_type( name, test_id_list ):
    for id in test_id_list:
        cursor.execute("select description from clinlims.test where id = %s", ( str(id)))
        found_name = cursor.fetchone()[0]
        if found_name.find( name ) != -1:
            print "\tCollapsing to " + found_name
            return id

    return ""

def update_test_result_id( idString, focal_test_id, key):
    cursor.execute("select id, result_type, value from clinlims.result " +
                   " where test_result_id is not NULL and " +
                   " analysis_id in ( select id from clinlims.analysis where test_id in (" + idString +"))")

    focal_result_type_A_or_N = False
    resultList = cursor.fetchall()

    for result in resultList:
        id, type, value = result;
        if type == 'A' or type == 'N':
            if not focal_result_type_A_or_N:
                cursor.execute("select id from clinlims.test_result where test_id = %s", (str(focal_test_id)))
                focal_result_type_A_or_N = cursor.fetchone()[0]

            cursor.execute( "update clinlims.result set test_result_id = %s where id = %s", (str(focal_result_type_A_or_N), str(id)))
        elif key == "Culture":
            cursor.execute("select id from clinlims.test_result where test_id = %s", (str(focal_test_id)) )
            test_result_id = cursor.fetchone()[0]
            #note this will result in the result type differing from the test_result result_type but that will be corrected latter
            cursor.execute( "update clinlims.result set test_result_id = %s, value = (select value from clinlims.test_result tr where tr.id = %s) " +
                            "where id = %s", (str(test_result_id), str(test_result_id), str(id)))
        elif type == 'D' or type == 'M':
            cursor.execute( "select dict_entry from dictionary where id = %s", (str(value)))
            result_dictionary_value = cursor.fetchone()[0]
            cursor.execute("select tr.id from clinlims.test_result tr " +
                           " join clinlims.dictionary d on d.id = CAST( tr.value as numeric) " + 
                           " where test_id = %s and d.dict_entry = %s", (str(focal_test_id), result_dictionary_value) )
            #print "focal_id = " + str(focal_test_id) + " moving id = " + str(id) + " value = " + str(value) + " dictionary = " + result_dictionary_value
            test_result_id = cursor.fetchone()
            if not test_result_id and key == "VIH Test Rapide":
                 cursor.execute("select tr.id from clinlims.test_result tr " +
                           " join clinlims.dictionary d on d.id = CAST( tr.value as numeric) " +
                           " where test_id = " + str(focal_test_id) + " and d.dict_entry like 'Ind%'" )
                 test_result_id = cursor.fetchone()
                 
            cursor.execute( "update clinlims.result set test_result_id = %s, value = (select value from clinlims.test_result tr where tr.id = %s) " +
                            "  where id = %s", (str(test_result_id[0]), str(test_result_id[0]), str(id)))


def rename_test( test_id):
    cursor.execute("select description from clinlims.test where id = %s", (test_id))
    full_name = cursor.fetchone()[0]
    name = full_name.split("(")[0].rstrip()

    print "\tNaming " + name
    cursor.execute("update clinlims.test set description = %s, name= %s where id = %s", (name, name, str(test_id)))
    return name

def find_correct_cell_epith( test_list_id ):
    cursor.execute("select id from clinlims.test where description like '%(%' and id in ( " + list_to_comma_separated_string(test_list_id) + ")")
    return cursor.fetchone()[0]

def find_correct_culture( test_list_id ):
    cursor.execute("select test_id from clinlims.test_result where tst_rslt_type = 'A' and test_id in ( " + list_to_comma_separated_string(test_list_id) + ")")
    return cursor.fetchone()[0]

def find_correct_malaria( test_id_list ):
    for id in test_id_list:
        cursor.execute("select description from clinlims.test where id = %s", ( str(id)))
        found_name = str(cursor.fetchone()[0]).strip()
        if found_name == "Malaria":
            print "\tCollapsing to " + found_name
            return id

    return ""

def find_correct_matoux( test_id_list ):
    for id in test_id_list:
        cursor.execute("select description from clinlims.test where id = %s", ( str(id)))
        found_name = cursor.fetchone()[0]
        if found_name.find( "Invalid" ) == -1:
            print "\tCollapsing to " + found_name
            return id

    return ""

def update_results_table( focal_test_id ):
    cursor.execute("update clinlims.result " +
                   "set value = (select dict_entry from clinlims.dictionary d where d.id = CAST(value as numeric)), result_type = 'A' " +
                   "where analysis_id in (select id from clinlims.analysis a where a.test_id = %s ) and result_type = 'D' " , focal_test_id)

def test_results_differ(prefix, focal_test_id, associated_test_ids ):
    cursor.execute("select tst_rslt_type, value from clinlims.test_result where test_id = %s", (str(focal_test_id)))

    focal_results = cursor.fetchall()

    if len( focal_results ):
        focal_list = []
        for result in focal_results:
            type, value = result
            if not value:
                value = "none"
            focal_list.append(type + value)

        number_results = len( focal_results)
        for id in associated_test_ids:
            cursor.execute("select tst_rslt_type, value from clinlims.test_result where test_id = %s", (str(id)))
            id_results = cursor.fetchall()

            if len( id_results ) != number_results:
                print "Not consolidating " + prefix + " because because some tests have " + str(number_results) + " test results and others have " + str(len(id_results))
                return 1

            for result in id_results:
                type, value = result
                if not value:
                     value = "none"
                target = type + value
                if not target in focal_list:
                    print "Not consolidating " + prefix + " because because some tests do not have the same result_type as others"
                    return 1
    else:
        for id in associated_test_ids:
            cursor.execute("select tst_rslt_type, value from clinlims.test_result where test_id = %s", (str(id)))
            id_results = cursor.fetchall()
            if len( id_results ) != 0:
                print "Not consolidating " + prefix + " because because some tests have no test results and others have " + str(len(id_results))
                return 1

    return False

def result_limits_differ(prefix, focal_test_id, associated_test_ids ):
    cursor.execute("select CAST(test_result_type_id as text), CAST(min_age as text), CAST(max_age as text), gender, CAST(low_normal as text),CAST(high_normal as text), CAST(low_valid as text), CAST(high_valid as text) from clinlims.result_limits where test_id = %s", (str(focal_test_id)))

    focal_limits = cursor.fetchall()

    if len( focal_limits ):
        focal_list = []
        for limit in focal_limits:
            focal_list.append("".join(limit))

        number_limits = len( focal_limits)
        for id in associated_test_ids:
            cursor.execute("select CAST(test_result_type_id as text), CAST(min_age as text), CAST(max_age as text), gender, CAST(low_normal as text),CAST(high_normal as text), CAST(low_valid as text), CAST(high_valid as text) from clinlims.result_limits where test_id = %s", (str(id)))
            id_limits = cursor.fetchall()

            if len( id_limits ) != number_limits:
                print "Not consolidating " + prefix + " because because some tests have " + str(number_limits) + " test limits and others have " + str(len(id_limits))
                return 1

            for limit in id_limits:
                if not "".join(limit) in focal_list:
                    print "Not consolidating " + prefix + " because because some tests do not have the same result limits as others"
                    return 1
    else:
        for id in associated_test_ids:
            cursor.execute("select test_result_type_id, min_age, max_age, gender, low_normal,high_normal, low_valid, high_valid from clinlims.result_limits where test_id = %s", (str(id)))
            id_limits = cursor.fetchall()
            if len( id_limits ) != 0:
                print "Not consolidating " + prefix + " because because some tests have no result limits and others have " + str(len(id_limits))
                return 1

    return False

def test_reflexes_differ(prefix, focal_test_id, associated_test_ids ):
    cursor.execute("select CAST(tst_rslt_id as text), CAST(test_analyte_id as text), CAST(coalesce(add_test_id, 1 ) as text),CAST(coalesce(sibling_reflex, 1 ) as text),CAST(coalesce(scriptlet_id, 1 ) as text) from clinlims.test_reflex where test_id = %s", (str(focal_test_id)))

    focal_reflexes = cursor.fetchall()

    if len( focal_reflexes ):
        focal_list = []
        for reflex in focal_reflexes:
            print reflex
            focal_list.append("".join(reflex))


        number_reflexes = len( focal_reflexes)
        for id in associated_test_ids:
            cursor.execute("select CAST(tst_rslt_id as text), CAST(test_analyte_id as text), CAST(coalesce(add_test_id, 1 ) as text),CAST(coalesce(sibling_reflex, 1 ) as text),CAST(coalesce(scriptlet_id, 1 ) as text) from clinlims.test_reflex where test_id = %s", (str(id)))
            id_reflexes = cursor.fetchall()

            if len( id_reflexes ) != number_reflexes:
                print "Not consolidating " + prefix + " because because some tests have " + str(number_reflexes) + " test reflexes and others have " + str(len(id_reflexes))
                return 1

            for reflex in id_reflexes:
                if not "".join(reflex) in focal_list:
                    print "Not consolidating " + prefix + " because because some tests do not have the same test_reflexes as others"
                    return 1
    else:
        for id in associated_test_ids:
            cursor.execute("select CAST(tst_rslt_id as text), CAST(test_analyte_id as text), CAST(coalesce(add_test_id, 1 ) as text),CAST(coalesce(sibling_reflex, 1 ) as text),CAST(coalesce(scriptlet_id, 1 ) as text) from clinlims.test_reflex where test_id = %s", (str(id)))
            id_reflexes = cursor.fetchall()
            if len( id_reflexes ) != 0:
                print "Not consolidating " + prefix + " because because some tests have no test reflexes and others have " + str(len(id_reflexes))
                return 1

    return False

def test_analyte_differ(prefix, focal_test_id, associated_test_ids ):
    cursor.execute("select CAST(analyte_id as text), CAST(result_group as text), CAST(result_group as text), COALESCE(testalyt_type, ' ') from clinlims.test_analyte where test_id = %s", (str(focal_test_id)))

    focal_analytes = cursor.fetchall()

    if len( focal_analytes ):
        focal_list = []
        for test_analyte in focal_analytes:
            focal_list.append("".join(test_analyte))

        number_analytes = len( focal_analytes)
        for id in associated_test_ids:
            cursor.execute("select CAST(analyte_id as text), CAST(result_group as text), CAST(result_group as text), COALESCE(testalyt_type, ' ' ) from clinlims.test_analyte where test_id = %s", (str(id)))
            id_analytes = cursor.fetchall()

            if len( id_analytes ) != number_analytes:
                print "Not consolidating " + prefix + " because because some tests have " + str(number_analytes) + " test analytes and others have " + str(len(id_analytes))
                return 1

            for test_analyte in id_analytes:
                if not "".join(test_analyte) in focal_list:
                    print "Not consolidating " + prefix + " because because some tests do not have the same test_analytes as others"
    else:
        for id in associated_test_ids:
            cursor.execute("select CAST(analyte_id as text), CAST(result_group as text), CAST(result_group as text), COALESCE(testalyt_type, ' ' ) from clinlims.test_analyte where test_id = %s", (str(id)))
            id_analytes = cursor.fetchall()
            if len( id_analytes ) != 0:
                print "Not consolidating " + prefix + " because because some tests have no test analytes and others have " + str(len(id_analytes))
                return 1

    return False

def list_to_comma_separated_string( list ):
    new_string = ""
    
    use_separator = False
    for id in list:
        if not id:
            continue
        if use_separator:
            new_string += ", "
        new_string += str(id)
        use_separator = 1

    return new_string

if __name__ == "__main__":

    usage = "usage: %prog [options]"
    parser = OptionParser(usage)
    parser.add_option("-d", "--database", dest="database", type="string",
                  help="database to which the change should be applied")
    parser.add_option("-p", "--password", dest="password", type="string",
                  help="The password for the database, assumes user clinlims")

    (options, args) = parser.parse_args()

    if not options.database or not options.password:
        parser.error("Both password and database are required Use -h for help")

    update_database( options.password, options.database )


