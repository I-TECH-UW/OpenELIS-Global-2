package us.mn.state.health.lims.reports.action.implementation.reportBeans;

import static us.mn.state.health.lims.reports.action.implementation.reportBeans.CSVColumnBuilder.Strategy.AGE_MONTHS;
import static us.mn.state.health.lims.reports.action.implementation.reportBeans.CSVColumnBuilder.Strategy.AGE_WEEKS;
import static us.mn.state.health.lims.reports.action.implementation.reportBeans.CSVColumnBuilder.Strategy.AGE_YEARS;
import static us.mn.state.health.lims.reports.action.implementation.reportBeans.CSVColumnBuilder.Strategy.DATE;
import static us.mn.state.health.lims.reports.action.implementation.reportBeans.CSVColumnBuilder.Strategy.DATE_TIME;
import static us.mn.state.health.lims.reports.action.implementation.reportBeans.CSVColumnBuilder.Strategy.GENDER;
import static us.mn.state.health.lims.reports.action.implementation.reportBeans.CSVColumnBuilder.Strategy.LOG;
import static us.mn.state.health.lims.reports.action.implementation.reportBeans.CSVColumnBuilder.Strategy.NONE;
import static us.mn.state.health.lims.reports.action.implementation.reportBeans.CSVColumnBuilder.Strategy.PROJECT;
import static us.mn.state.health.lims.reports.action.implementation.reportBeans.CSVColumnBuilder.Strategy.SAMPLE_STATUS;

import org.apache.commons.validator.GenericValidator;

import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.reports.action.implementation.Report.DateRange;

/**
 * If we had a big resultSet with various columns for CSV export, we need a few things defined so we can translate to a useable CSV file values.
 * This class builds the SQL for the one row per CSV output and maps everything to the output including looking up resource names.
 * This class also can print out just the XML needed for the Jasper report which helps make the CSV file (see the call to generateJasperXML).
 * @author Paul A. Hill (pahill@uw.edu)
 * @since Jan 28, 2011
 */
public abstract class CIColumnBuilder extends CSVColumnBuilder {
    
    /**
     *  The basic SQL SELECT to get start on finding a sample, sample_item, patient and organization  
     */
    protected static final String SELECT_SAMPLE_PATIENT_ORGANIZATION = 
        "SELECT DISTINCT s.id as sample_id, s.accession_number, s.entered_date, s.received_date, s.collection_date, s.status_id " +
             "\n, pat.national_id, pat.external_id, pat.birth_date, per.first_name, per.last_name, pat.gender " +
             "\n, o.short_name as organization_code, o.name AS organization_name, sp.proj_id as project_id " +
             "\n, o.datim_org_code, o.datim_org_name "+
             "\n ";
    /**
     * The column select which puts all demographic and result columns in the result set.
     */
    protected static final String SELECT_ALL_DEMOGRAPHIC_AND_RESULTS =     
        "\n, demo.*, result.*" +
        "\n ";
    /**
     * the basic SQL FROM clause for the selection from basic lab tables for sample, sample_item, patient & organization
     */
    protected static final String FROM_SAMPLE_PATIENT_ORGANIZATION = 
        " FROM sample as s, patient as pat, person as per, sample_human as sh, sample_projects AS sp, sample_organization AS so, organization AS o \n ";
    protected DateRange dateRange;
    protected String projectStr;

    /**
     *
     */
    public CIColumnBuilder(DateRange dateRange, String projectStr) {
        super(StatusService.AnalysisStatus.Finalized);
        this.dateRange = dateRange;
        this.projectStr = projectStr;
        defineAllObservationHistoryTypes();
        defineAllTestsAndResults();
        defineAllProjectTags();
        defineAllReportColumns();
    }

    protected abstract void defineAllReportColumns();
    
    public abstract void makeSQL();    

    /**
     * Useful when building the SQL String
     * @param lowDatePostgres
     * @param highDatePostgres
     * @return String starting "WHERE ..." joining patient, sample, organization
     */
    protected String buildWhereSamplePatienOrgSQL(String lowDatePostgres, String highDatePostgres) {
        String WHERE_SAMPLE_PATIENT_ORG = " WHERE "
        + "\n pat.id = sh.patient_id "
        + "\n AND sh.samp_id = s.id "
        + "\n AND s.collection_date >= '" + lowDatePostgres + "'"
        + "\n AND s.collection_date <= '" + highDatePostgres + "'"
        + "\n AND s.id = sp.samp_id "
        + "\n AND pat.person_id = per.id "
        + "\n AND so.samp_id = s.id "
        + ((GenericValidator.isBlankOrNull(projectStr))?"": " AND sp.proj_id = " + projectStr)
        + "\n AND o.id = so.org_id ";
        return WHERE_SAMPLE_PATIENT_ORG;
    }

    protected void appendRepeatingObservation(String aOhTypeName, int maxCols, String lowDatePostgres,
                    String highDatePostgres) {
        appendCrosstabPreamble(aOhTypeName);
        
        query.append( " crosstab( "
                    + "' SELECT s.id as s_id, type, value FROM Sample AS s "
                    + " LEFT JOIN"
                    + " ( SELECT DISTINCT s.id as s_id , oh.observation_history_type_id AS type, oh.value AS value, oh.id "
                    + " FROM Sample as s, Observation_History AS oh"
                    + " WHERE oh.sample_id = s.id"
                    + " AND s.collection_date >= date(''" + lowDatePostgres + "'') "
                    + " AND s.collection_date <= date(''" + highDatePostgres +  "'')"
                    + " AND oh.observation_history_type_id = (select id FROM observation_history_type WHERE type_name = ''" + aOhTypeName + "'')  ORDER by 1,2, oh.id desc ) AS repeatCols"
                    + " ON s.id = repeatCols.s_id"
                    + " WHERE s.collection_date >= date(''" + lowDatePostgres + "'') "
                    + " AND s.collection_date <= date(''" + highDatePostgres +  "'')"
                    + "' )"
                + " AS " + aOhTypeName + " ( s_id NUMERIC(10) "     );
        for (int col = 1; col <= maxCols; col++) {
            query.append( ", \"").append(aOhTypeName).append( col ).append("\" VARCHAR(100)");
        }
        query.append( " )\n ");
        appendCrosstabPostfix(lowDatePostgres, highDatePostgres, aOhTypeName);
    }
    
 //----------------------------
    
    protected void appendRepeatingObservation(String aOhTypeName, int maxCols, String lowDatePostgres,
            String highDatePostgres, String byDate) {
appendCrosstabPreamble(aOhTypeName);

query.append( " crosstab( "
            + "' SELECT s.id as s_id, type, value FROM Sample AS s "
            + " LEFT JOIN"
            + " ( SELECT DISTINCT s.id as s_id , oh.observation_history_type_id AS type, oh.value AS value, oh.id "
            + " FROM Sample as s, Observation_History AS oh, document_track as dt"
            + " WHERE oh.sample_id = s.id"
            + " AND dt.row_id = s.id"
            + " AND "+byDate+" >= date(''" + lowDatePostgres + "'') "
            + " AND "+byDate+" <= date(''" + highDatePostgres +  "'')"
            + " AND oh.observation_history_type_id = (select id FROM observation_history_type WHERE type_name = ''" + aOhTypeName + "'')  ORDER by 1,2, oh.id desc ) AS repeatCols"
            + " ON s.id = repeatCols.s_id"
            + " WHERE "+byDate+" >= date(''" + lowDatePostgres + "'') "
            + " AND "+byDate+" <= date(''" + highDatePostgres +  "'')"
            + "' )"
        + " AS " + aOhTypeName + " ( s_id NUMERIC(10) "     );
for (int col = 1; col <= maxCols; col++) {
    query.append( ", \"").append(aOhTypeName).append( col ).append("\" VARCHAR(100)");
}
query.append( " )\n ");
appendCrosstabPostfix(lowDatePostgres, highDatePostgres, aOhTypeName);
}
    
    
    
    
 //------------------------------------

    protected void appendOtherDiseaseCrosstab(String lowDatePostgres, String highDatePostgres,
                    String diseaseListName, String otherColumnName) {
        appendCrosstabPreamble(otherColumnName );
        query.append( " crosstab( " +
        		" 'SELECT DISTINCT s.id, oh.observation_history_type_id AS ohType, oh.value AS value "
                + " FROM  Sample as s, Observation_History AS oh, Observation_history_type as oht "
                + " WHERE s.collection_date >= ''" + lowDatePostgres + "''"
                + "   AND s.collection_date <= ''" + highDatePostgres + "''"
                + "   AND s.id = oh.sample_id "
                + "   AND oh.observation_history_type_id = oht.id "
                + "   AND oh.observation_history_type_id = (select id FROM observation_history_type WHERE type_name = ''" + diseaseListName + "'') "
                + "   AND oh.value !~ ''^[0-9]+$'' "
                + " ORDER by 1,2,3' ) AS " + otherColumnName + " ( s_id NUMERIC(10), " + otherColumnName + " varChar(100) ) "
                + "\n" );
        appendCrosstabPostfix(lowDatePostgres, highDatePostgres, otherColumnName);
    }

    protected void defineBasicColumns() {
        add("accession_number", "LABNO",    NONE);
        add("status_id", "ECHSTAT", SAMPLE_STATUS);
        add("national_id",      "SUJETNO",  NONE);
        add("project_id",        "ETUDE",    PROJECT );
        add("external_id",      "SUJETSIT", NONE);
        add("received_date",    "DRCPT",    DATE_TIME );      // reception date
        add("collection_date",  "DINTV",    DATE_TIME );      // interview date
        add("organization_code", "CODE_SITE", NONE);
        add("organization_name", "NOM_SITE",  NONE);
        add("datim_org_code", "CODE_SITE_DATIM", NONE);
        add("datim_org_name", "NOM_SITE_DATIM",  NONE);
       // add("last_name",        "NOM",      NONE);
       // add("first_name",       "PRENOM",   NONE);
        add("gender",           "SEXE",     GENDER);
        add("birth_date",       "DATENAIS", DATE);
        add("collection_date",  "AGEANS",   AGE_YEARS); 
        add("collection_date",  "AGEMOIS",  AGE_MONTHS); 
        add("collection_date",  "AGESEMS",  AGE_WEEKS);
    }
    
    @Override
    protected void addAllResultsColumns() {
        super.addAllResultsColumns();
        add("Viral Load", "Viral Load log", LOG );
    }
}