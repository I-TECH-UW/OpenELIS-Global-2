#!python

import os
import sys
import getopt

genOrgName = "./genOrgFile.csv"
genTypeName = "./genOrgTypeFile.csv"

findOrgType = ""
orgOutFile = ""
orgTypeOutFile = ""

def usage():
    print "organizationCSVGen.py -- Generates csv files for use by liquibase for adding organizations and organization_organazation_types to database"
    print "\tThe csv file with the organizations is " + genOrgName + " Previous contents will be overwritten"
    print "\tThe csv file with the organization_organization_type is " + genTypeName + " Previous contents will be overwritten\n"
    print "\t--help or -h This message\n"
    print "\t--inputFile= or -i Required. The name of the input file with a list of the organizations\n"
    print "\t--orgType= or -t Required. The name (short_name) of the organization_type\n"
    print "\t example organizationCSVGen.py --inputFile=orgs.txt --orgType=referralLab or organizationCSVGen.py -i orgs.txt -t referralLab "

def exampleLiquibase():
    print "Example liquibase change set entries\n"
    print "<changeSet author=\"paulsc\" id=\"14\" context=\"haitiLNSP\">"
    print "\t<comment>Adding organizations for referrals</comment>"
    print "\t<loadData file=\"Haiti_CSV/organization.csv\" schemaName=\"clinlims\" tableName=\"organization\" >"
    print "\t\t<column name=\"id\" header=\"id\" type=\"NUMERIC\" />"
    print "\t\t<column name=\"name\" header=\"name\"  type=\"STRING\" />"
    print "\t\t<column name=\"lastupdated\" header=\"lastupdated\" type=\"DATE\" />"
    print "\t\t<column name=\"is_active\" header=\"active\" type=\"STRING\" />"
    print "\t</loadData>"
    print "\t<loadData file=\"Haiti_CSV/orgOrgType.csv\" schemaName=\"clinlims\" tableName=\"organization_organization_type\" >"
    print "\t\t<column name=\"org_id\" header=\"org_id\" type=\"NUMERIC\" />"
    print "\t\t<column name=\"org_type_id\" header=\"org_type_id\"  type=\"NUMERIC\" />"
    print "\t</loadData>"
    print "</changeSet>"    

def createOrgTypeSelection( referral ):
    global findOrgType
    findOrgType = " ( select id from organization_type ot where ot.short_name='" + referral + "' ) "

def openOutputFiles():
    global orgOutFile, orgTypeOutFile
    orgOutFile = open( genOrgName, "w")
    orgTypeOutFile = open( genTypeName, "w")
    
def closeFiles():
    global orgOutFile, orgTypeOutFile
    orgOutFile.close()
    orgTypeOutFile.close()

def writeHeaders():
    global orgOutFile, orgTypeOutFile
    orgOutFile.write("id,name,last,active\n")
    orgTypeOutFile.write("org_id,org_type_id\n")
    
def writeOrgLine( line ):
    global findOrgType
    orgOutFile.write(" nextval( 'organization_seq' ) ," + line.strip() + ", now() ,Y\n")
    orgTypeOutFile.write( findOrgType + ", ( select id from organization o where o.name='" + line.strip() + "') \n" )

def main(argv):   
    try:                                
        opts, args = getopt.getopt(argv, "hi:t:", ["help", "inputFile=", "orgType="]) 
    except getopt.GetoptError:           
        usage()                          
        sys.exit(2)        

    for opt, arg in opts:                
        if opt in ("-h", "--help"):      
            usage()                     
            sys.exit()                           
        elif opt in ("-i", "--inputFile"): 
            inputFile = arg
        elif opt in ("-t", "--orgType"): 
            referralType = arg

    if( len(inputFile) == 0 or len(referralType) == 0 ):
        print "\nMissing required arguments\n"
        usage()
        sys.exit(2)

    exampleLiquibase()        
    createOrgTypeSelection(referralType)
    openOutputFiles()
    writeHeaders()
    orgFile = open(inputFile, "r")

    for line in orgFile:
        writeOrgLine( line )

    closeFiles()
    
if __name__ == "__main__":
    main(sys.argv[1:])    