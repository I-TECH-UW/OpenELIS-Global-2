package org.openelisglobal.reports.action.implementation.reportBeans;

import org.openelisglobal.reports.action.implementation.Report.DateRange;

/**
 * If we had a big resultSet with various columns for CSV export, we need a few
 * things defined so we can translate to a useable CSV file values. This class
 * builds the SQL for the one row per CSV output and maps everything to the
 * output including looking up resource names. This class also can print out
 * just the XML needed for the Jasper report which helps make the CSV file (see
 * the call to generateJasperXML).
 *
 * @author Paul A. Hill (pahill@uw.edu)
 * @since Jan 28, 2011
 */
public abstract class WHONETCIRoutineColumnBuilder extends WHONETCSVRoutineColumnBuilder {

    protected static final String WHONET_SELECT = "select r.value as result_value," //
            + " s.accession_number as lab_no, s.collection_date, s.entered_date," //
            + " pe.last_name, pe.first_name," //
            + " pa.national_id, pa.gender, pa.birth_date,"
            + " t.id as test_id, t.name as test_name, t.description as test_description, t.loinc, t.guid," //
            + " tos.description as sample_type" //
            + " from sample_item si" + " join analysis a on a.sampitem_id = si.id" //
            + " join sample s on si.samp_id = s.id" //
            + " join sample_human sh on sh.samp_id = s.id" + " join sample_requester sr1 on sr1.sample_id = s.id"
            + " join type_of_sample tos on si.typeosamp_id = tos.id" //
            + " join patient pa on pa.id = sh.patient_id" //
            + " join person pe on pe.id = pa.person_id" + " join test t on t.id = a.test_id" //
            + " left join result r on r.analysis_id = a.id" //
            + " where t.antimicrobial_resistance = true"
            + " and sr1.requester_type_id = (SELECT id from requester_type rt where rt.requester_type = 'organization')";

    protected DateRange dateRange;
    // protected String projectStr;

    /** */
    public WHONETCIRoutineColumnBuilder(DateRange dateRange) {
        super();
        this.dateRange = dateRange;
    }

    @Override
    public abstract void searchForWHONetResults();

    // protected void defineBasicColumns() {
    // // // patient fields
    // // add("national_id", "IDENTIFIER", NONE);
    // // add("first_name", "FIRST NAME", NONE);
    // // add("last_name", "LAST NAME", NONE);
    // // add("gender", "SEX", NONE);
    // // add("birth_date", "BIRTHDATE", DATE);
    // // add("entered_date", "DATE ENTERED", DATE_TIME); // interview date

    // // // add("organization_code", "CODEREFERER", NONE);
    // // // add("organization_name", "REFERER", NONE);

    // // // specimen fields
    // // add("lab_no", "LABNO", NONE);
    // // add("collection_date", "DATE COLLECTED", DATE_TIME); // collection date
    // // add("sample_type", "SPECIMEN TYPE", NONE);

    // // add("test_name", "ANTIBIOTIC", NONE);
    // // add("organism", "ORGANISM", NONE);
    // // add("loinc", "LOINC", NONE);
    // // add("guid", "GUID", NONE);

    // }
    /*
     * add("accession_number", "LABNO", NONE); add("national_id", "IDENTIFIER",
     * NONE); add("entered_date", "AGEYEARS", AGE_YEARS); add("entered_date",
     * "AGEMONTHS", AGE_MONTHS); add("entered_date", "AGEWEEKS", AGE_WEEKS);//
     * reception date add("entered_date", "DATEENTERED", DATE_TIME); // interview
     * date add("collection_date", "DATECOLLECT", DATE_TIME); // collection date
     */

    // add("released_date", "DATEVALIDATION", DATE_TIME ); // validation date

    /*
     * add("organization_code", "CODEREFERER", NONE); add("organization_name",
     * "REFERER", NONE); add("program", "PROGRAM", PROGRAM); add("status_id",
     * "STATUS", SAMPLE_STATUS);
     */

    // add("external_id", "SUJETSIT", NONE);
    // add("last_name", "NOM", NONE);
    // add("first_name", "PRENOM", NONE);

}
