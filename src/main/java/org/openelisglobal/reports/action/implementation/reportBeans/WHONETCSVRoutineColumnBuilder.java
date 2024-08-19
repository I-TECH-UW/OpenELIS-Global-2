/*
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations under
 * the License.
 *
 * The Original Code is OpenELIS code.
 *
 * Copyright (C) The Minnesota Department of Health.  All Rights Reserved.
 *
 * Contributor(s): CIRG, University of Washington, Seattle WA.
 */
package org.openelisglobal.reports.action.implementation.reportBeans;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.openelisglobal.common.util.DateUtil;
import org.openelisglobal.result.service.ResultService;
import org.openelisglobal.spring.util.SpringContext;
import org.openelisglobal.test.service.TestService;
import org.openelisglobal.testresult.service.TestResultService;

/**
 * @author pahill (pahill@uw.edu)
 * @since Mar 18, 2011
 */
public abstract class WHONETCSVRoutineColumnBuilder {

    public static class WHONetRow {
        private String nationalId;
        private String firstName;
        private String lastName;
        private String sex;
        private String birthdate;
        private String enteredDate;
        private String labNo;
        private String collectionDate;
        private String sampleType;
        private String antibiotic;
        private String organism;
        private String result;
        private String method;

        private String delimeter = "\t";

        public WHONetRow(String nationalId, String firstName, String lastName, String sex, String birthdate,
                String enteredDate, String labNo, String collectionDate, String sampleType, String antibiotic,
                String organism, String result, String method) {
            this.nationalId = nationalId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.sex = sex;
            this.birthdate = birthdate;
            this.enteredDate = enteredDate;
            this.labNo = labNo;
            this.collectionDate = collectionDate;
            this.sampleType = sampleType;
            this.antibiotic = antibiotic;
            this.organism = organism;
            this.result = result;
            this.method = method;
        }

        public String getRow() {
            List<String> rowValues = Arrays.asList(nationalId, firstName, lastName, sex, birthdate, enteredDate, labNo,
                    collectionDate, sampleType, antibiotic, organism, result, method);
            return String.join(delimeter, rowValues);
        }
    }

    // these are used so we are not passing around strings in the methods that are
    // appended to sql
    // this is to cover any potential sql injection that could be introduced by a
    // developer
    protected enum SQLConstant {
        DEMO("demo"), RESULT("result");

        private final String nameInSql;

        private SQLConstant(String name) {
            nameInSql = name;
        }

        @Override
        public String toString() {
            return nameInSql;
        }
    }

    protected static final SimpleDateFormat postgresDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat postgresDateTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    protected List<WHONetRow> rows;
    protected int index = -1;

    protected String eol = System.getProperty("line.separator");

    protected ResultService resultService = SpringContext.getBean(ResultService.class);
    protected TestService testService = SpringContext.getBean(TestService.class);
    protected TestResultService testResultService = SpringContext.getBean(TestResultService.class);

    /**
     * The various ways that columns are converted for CSV export
     *
     * @author Paul A. Hill (pahill@uw.edu)
     * @since Feb 1, 2011
     */
    public enum Strategy {
        DICT, // dictionary localized value
        DICT_PLUS, // dictionary localized value or a string constant
        DICT_RAW, // dictionary localized value, no attempts at trimming to show just code number.
        DATE, // date (i.e. 01/01/2013)
        DATE_TIME, // date with time (i.e. 01/01/2013 12:12:00)
        NONE, GENDER, DROP_ZERO, TEST_RESULT, GEND_CD4, SAMPLE_STATUS, PROJECT, PROGRAM, // program defined in routine
        // order.
        LOG, // results is a real number, but display the log of it.
        AGE_YEARS, AGE_MONTHS, AGE_WEEKS, DEBUG, CUSTOM, // special handling which is encapsulated in an instance of
        // ICSVColumnCustomStrategy
        BLANK // Will always be an empty string. Used when column is wanted but data is not
    }

    public void buildDataSource() throws SQLException {
        buildResultSet();
    }

    protected void buildResultSet() throws SQLException {
        searchForWHONetResults();
    }

    protected synchronized String formatDateForDatabaseSql(Date date) {
        // SimpleDateFormat is not thread safe
        return postgresDateFormat.format(date);
    }

    protected synchronized Date parseDateForDatabaseSql(String date) throws ParseException {
        // SimpleDateFormat is not thread safe
        return postgresDateFormat.parse(date);
    }

    private synchronized Date parseDateTimeForDatabaseSql(String date) throws ParseException {
        // SimpleDateFormat is not thread safe
        return postgresDateTime.parse(date);
    }

    /**
     * @param value
     * @return
     */
    protected String datetimeToLocalDate(String value) {
        try {
            Date parsed = parseDateTimeForDatabaseSql(value);
            java.sql.Date date = new java.sql.Date(parsed.getTime());
            return DateUtil.convertSqlDateToStringDate(date);
        } catch (ParseException e) {
            return value;
        }
    }

    protected String datetimeToLocalDateTime(String value) {
        try {
            Date parsed = parseDateTimeForDatabaseSql(value);
            return DateUtil.formatDateTimeAsText(parsed);
        } catch (ParseException e) {
            return value;
        }
    }

    public abstract void searchForWHONetResults();

    /**
     * Useful for the 1st line of a CSV files. This produces a completely escaped
     * for MSExcel comma separated list of columns.
     *
     * @return one string with all names.
     */
    public String getColumnNamesLine() {
        return new StringBuilder().append(
                new WHONetRow("NATIONAL ID", "FIRST NAME", "LAST NAME", "SEX", "BIRTHDATE", "DATE ENTERED", "LABNO",
                        "DATE COLLECTED", "SPECIMEN TYPE", "ANTIBIOTIC", "ORGANISM", "RESULT", "METHOD").getRow())
                .append(eol).toString();
    }

    /**
     * @return @
     * @throws ParseException
     * @throws SQLException
     */
    public String nextLine() throws SQLException, ParseException {
        return new StringBuilder().append(rows.get(index).getRow()).append(eol).toString();
    }

    public boolean next() throws SQLException {
        return ++index < rows.size();
    }
}
