package org.openelisglobal;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

public class DbUnitUtil extends DBTestCase {

    private final String datasetName;
    private final String dbDriverClass="org.postgresql.Driver";
    private final String dbConnetionURL="jdbc:postgresql://localhost:5432/clinlims";
    private final String userName="clinlims";
    private final String password="clinlims";

      public DbUnitUtil(String datasetName) 
    {
        super();

        this.datasetName = datasetName;
    
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, dbDriverClass);
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, dbConnetionURL );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, userName );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, password );
	// System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "" );
    }

    public IDataSet getDataSet() throws Exception {
        try {
            return new FlatXmlDataSetBuilder().build(new FileInputStream(datasetName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Failed to load dataset: " + datasetName, e);
        }
    }
}
