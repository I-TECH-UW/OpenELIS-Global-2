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
package us.mn.state.health.lims.reports.action.implementation.reportBeans;

import static org.apache.commons.validator.GenericValidator.isBlankOrNull;
import static us.mn.state.health.lims.reports.action.implementation.reportBeans.CSVRoutineColumnBuilder.Strategy.DICT;
import static us.mn.state.health.lims.reports.action.implementation.reportBeans.CSVRoutineColumnBuilder.Strategy.TEST_RESULT;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import us.mn.state.health.lims.analyte.dao.AnalyteDAO;
import us.mn.state.health.lims.analyte.daoimpl.AnalyteDAOImpl;
import us.mn.state.health.lims.analyte.valueholder.Analyte;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.OrderStatus;
import us.mn.state.health.lims.common.services.TestService;
import us.mn.state.health.lims.common.services.TypeOfTestResultService;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.observationhistorytype.dao.ObservationHistoryTypeDAO;
import us.mn.state.health.lims.observationhistorytype.daoImpl.ObservationHistoryTypeDAOImpl;
import us.mn.state.health.lims.observationhistorytype.valueholder.ObservationHistoryType;
import us.mn.state.health.lims.result.dao.ResultDAO;
import us.mn.state.health.lims.result.daoimpl.ResultDAOImpl;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.testresult.daoimpl.TestResultDAOImpl;
import us.mn.state.health.lims.testresult.valueholder.TestResult;

/**
 * @author pahill (pahill@uw.edu)
 * @since Mar 18, 2011
 */
abstract public class CSVRoutineColumnBuilder {
	/**
     * 
     */
	public CSVRoutineColumnBuilder(StatusService.AnalysisStatus validStatusFilter) {
		// we'll round up everything via hibernate first.
		ResourceTranslator.DictionaryTranslator.getInstance();
		ResourceTranslator.GenderTranslator.getInstance();

		if (validStatusFilter != null) {
			this.validStatusId = StatusService.getInstance().getStatusID(validStatusFilter);
		}
	}

	/**
	 * The list of all columns that can be exported for lookup when report is
	 * generated
	 */
	private final Map<String, CSVRoutineColumn> columnsByDbName = new HashMap<String, CSVRoutineColumn>();

	/**
	 * The list of columns in order of definition, so that we can generate
	 * JasperReports XML in order for display
	 */
	private final List<CSVRoutineColumn> columnsInOrder = new ArrayList<CSVRoutineColumn>();

	/**
	 * In order as postgres would order varchar columns (alphabetical ignoring
	 * case?).
	 */
	protected List<ObservationHistoryType> allObHistoryTypes;

	/**
	 * All possible tests, so we can have 1 result per test.
	 */
	protected List<Test> allTests;

	/**
	 * This table provide a mapping from test name (CSV column heading also) to
	 * the TestResult record so we can look at the result type (Dictionary vs.
	 * constant etc.)
	 */
	protected Map<String, TestResult> testResultsByTestName;

	protected String validStatusId;

	protected static final SimpleDateFormat postgresDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat postgresDateTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	protected ResultSet resultSet;

	protected String eol = System.getProperty("line.separator");

	protected ResultDAO resultDAO = new ResultDAOImpl();
	
	//This is the largest value possible for a postgres column name.  The code will convert the
	//test description to a column name so we need to truncate
	//It's actually 63 but UTF_8 makes 59 safer.  
	private static final int MAX_POSTGRES_COL_NAME = 59; 
	/**
	 * the test have to be sorted by the name, because they have to match the
	 * pivot table order in the results
	 */
	@SuppressWarnings("unchecked")
	protected void defineAllTestsAndResults() {
		if (allTests == null) {
			allTests = new TestDAOImpl().getAllOrderBy("name");
		}
		if (testResultsByTestName == null) {
			testResultsByTestName = new HashMap<String, TestResult>();
			List<TestResult> allTestResults = new TestResultDAOImpl().getAllTestResults();
			for (TestResult testResult : allTestResults) {
				String key = TestService.getLocalizedTestNameWithType( testResult.getTest() );
				testResultsByTestName.put(key, testResult);
			}
		}
	}

	/**
	 * map to provide appropriate tag to identify the project.
	 */

	//static Map<String /* project Id */, String /* project tag */> projectTagById = new HashMap<String, String>();
/*	static {
		defineAllProjectTags();
	} 

	public static void defineAllProjectTags() {
		ProjectDAO projectDAO = new ProjectDAOImpl();
		List<Project> allProjects = projectDAO.getAllProjects();
		for (Project project : allProjects) {
			String projectTag;
			// Watch the order on these 1st 2!
			if (project.getNameKey().contains("ARVF")) {
				projectTag = "ARVS";
			} else if (project.getNameKey().contains("ARV")) {
				projectTag = "ARVB";
			} else if (project.getNameKey().contains("VLS")) {
				projectTag = "VLS";
			} else {
				// otherwise we use the letters from the Sample ID prefix, which
				// at some locations for some projects is undefined.
				String code = project.getProgramCode();
                projectTag = (code == null)?"":code.substring(1); // drop the L
			}
			projectTagById.put(project.getId(), projectTag);
		}
	}
*/
	protected StringBuilder query;

	private String gendCD4CountAnalyteId;

	/**
	 * The various ways that columns are converted for CSV export
	 * 
	 * @author Paul A. Hill (pahill@uw.edu)
	 * @since Feb 1, 2011
	 */
	public static enum Strategy {
		DICT, // dictionary localized value
		DICT_PLUS, // dictionary localized value or a string constant
        DICT_RAW,   // dictionary localized value, no attempts at trimming to show just code number.
        DATE,       // date (i.e. 01/01/2013)
        DATE_TIME,  // date with time (i.e. 01/01/2013 12:12:00)
        NONE,
        GENDER,
        DROP_ZERO,
        TEST_RESULT,
        GEND_CD4,
        SAMPLE_STATUS,
        PROJECT,
        PROGRAM,     // program defined in routine order.
        LOG,        // results is a real number, but display the log of it.
        AGE_YEARS,
        AGE_MONTHS,
        AGE_WEEKS,
        DEBUG,
        CUSTOM, //special handling which is encapsulated in an instance of ICSVColumnCustomStrategy
        BLANK //Will always be an empty string.  Used when column is wanted but data is not
	}

	public void buildDataSource() throws Exception {
		buildResultSet();
	}

	protected void buildResultSet() throws SQLException {
		makeSQL();
		String sql = query.toString();
        //System.out.println("===1===\n" + sql.substring(0, 7000)); // the SQL is chunked out only because Eclipse thinks printing really big strings to the console must be wrong, so it truncates them
		//System.out.println("===2===\n" + sql.substring(7000));
		Session session = HibernateUtil.getSession().getSessionFactory().openSession();
		PreparedStatement stmt = session.connection().prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		resultSet = stmt.executeQuery();
	}

	/**
	 * @param value
	 * @return
	 */
	protected String datetimeToLocalDate(String value) {
		try {
			Date parsed = postgresDateTime.parse(value);
			java.sql.Date date = new java.sql.Date(parsed.getTime());
			return DateUtil.convertSqlDateToStringDate(date);
		} catch (Exception e) {
			return value;
		}
	}

    protected String datetimeToLocalDateTime(String value) {
        try {
            Date parsed = postgresDateTime.parse(value);
            return DateUtil.formatDateTimeAsText(parsed);
        } catch (Exception e) {
            return value;
        }
    }
	
	
	public String getValue(CSVRoutineColumn column, String accessionNumber) throws Exception {
		String value;
		// look in the data source for a value
		try {
			value = resultSet.getString( trimToPostgresMaxColumnName(column.dbName));
		} catch (Exception e) {
			// if you end up where it is because the result set doesn't return a
			// column of the right name
			// Check MAX_POSTGRES_COL_NAME if this fails on a long name
			System.out.println("Internal Error: Unable to find db column \"" + column.dbName + "\" in data.");
			return "?" + column.csvName + "?";
		}

		String result = column.translate(value, accessionNumber);
		// translate should never return null, "" is better while it is doing
		// translation.
		if (result == null) {
			System.out.println("A null found " + column.dbName);
		}
		return result;
	}

	private String trimToPostgresMaxColumnName(String name) {
		if( name.length() <= MAX_POSTGRES_COL_NAME ){
			return name;
		}else{
			return name.substring(0, MAX_POSTGRES_COL_NAME);
		}		
	}

	public void add(String dbName, String csvTitle) {
		add(dbName, csvTitle, DICT);
	}

	public void add(String dbName, String csvTitle, Strategy strat) {
		CSVRoutineColumn col = new CSVRoutineColumn(dbName, csvTitle, strat);
		columnsByDbName.put(dbName, col);
		columnsInOrder.add(col);
	}

    public void add(String dbName, String csvTitle, ICSVRoutineColumnCustomStrategy customStrategy  ){
    	CSVRoutineColumn col = new CSVRoutineColumn(dbName, csvTitle, Strategy.CUSTOM, customStrategy);
		columnsByDbName.put(dbName, col);
		columnsInOrder.add(col);
    }
	/**
	 * A utility routine for finding the project short tag (used in exporting
	 * etc.) from a projectId.

	 */
/*	public static String translateProjectId(String projectId) {
		return (projectId == null) ? null : projectTagById.get(projectId);
	}
*/
	public class CSVRoutineColumn {
		public String csvName;
		public String dbName;
		public Strategy strategy;
		public ICSVRoutineColumnCustomStrategy customStrategy;

		public CSVRoutineColumn(String dbName, String csvName, Strategy strategy) {
			this.csvName = csvName;
			this.dbName = dbName;
			this.strategy = strategy;
		}
		
		public CSVRoutineColumn(String dbName, String csvName, Strategy strategy, ICSVRoutineColumnCustomStrategy customStrategy) {
			this.csvName = csvName;
			this.dbName = dbName;
			this.strategy = strategy;
			this.customStrategy = customStrategy;
		}

		public String translate(String value, String accessionNumber) throws Exception {
			switch (strategy) {
			case CUSTOM:
				return customStrategy.translate(value, accessionNumber, csvName, dbName);
			case DICT_RAW:
				return isBlankOrNull(value) ? "" : ResourceTranslator.DictionaryTranslator.getInstance().translateRaw(value);
			case DICT_PLUS:
				return isBlankOrNull(value) ? "" : ResourceTranslator.DictionaryTranslator.getInstance().translateOrNot(value);
			case DICT:
				return isBlankOrNull(value) ? "" : ResourceTranslator.DictionaryTranslator.getInstance().translate(value);
			case PROGRAM:
				return isBlankOrNull(value) ? "" : ResourceTranslator.DictionaryTranslator.getInstance().translate(value);
			case DATE:
				return isBlankOrNull(value) ? "" : datetimeToLocalDate(value);
			case DATE_TIME:
			    return isBlankOrNull(value) ? "" : datetimeToLocalDateTime(value);
			case AGE_YEARS:
			case AGE_MONTHS:
			case AGE_WEEKS:
				return isBlankOrNull(value) ? "" : translateAge(strategy, value);
			case GENDER:
				return isBlankOrNull(value) ? "" : ResourceTranslator.GenderTranslator.getInstance().translate(value);
			case DROP_ZERO:
				return ("0".equals(value) || value == null) ? "" : value;
			case TEST_RESULT:
				return isBlankOrNull(value) ? "" : translateTestResult(this.csvName, value);
			case GEND_CD4:
				return isBlankOrNull(value) ? "" : translateGendResult(getGendCD4CountAnalyteId(), value);
			case LOG:
				return isBlankOrNull(value) ? "" : translateLog(value);
			case SAMPLE_STATUS:
				OrderStatus orderStatus = StatusService.getInstance().getOrderStatusForID(value);
				if (orderStatus == null)
					return "?";
				switch (orderStatus) {
				case Entered:
					return "Saisie"; // entered,
				case Started:
					return "En_Cours"; // commenced,
				case Finished:
					return "Fini"; // Finished,
				case NonConforming_depricated:
					return "Non-conforme"; // Non-conforming, Non-conformes
				}
			
			case DEBUG:
				System.out.println("Processing Column Value: " + this.csvName + " \"" + value + "\"");
			case BLANK:
				return "";
			default:
				return isBlankOrNull(value) ? "" : value;
			}
		}

		private String translateGendResult(String gendResultAnalyteId, String sampleItemId) {
			// 'generated CD4 Count'
			Result gendCD4Result = resultDAO.getResultForAnalyteAndSampleItem(gendResultAnalyteId, sampleItemId);
			if (gendCD4Result == null) {
				return "";
			}
			String value = gendCD4Result.getValue();
			return (value == null) ? "" : value;
		}

		/**
		 * The log value of the given value as a floating-point value.
		 *
		 * @param value
		 * @return The log value
		 */
		private String translateLog(String value) {
			try {
				double d = Double.parseDouble(value);
				return String.valueOf(Math.log10(d));
			} catch (NumberFormatException nfe) {
				return "";
			}
		}

		public String translateAge(Strategy strategy, String end) throws Exception {
			Date birthday = resultSet.getDate("birth_date");
			Date endDate = postgresDateTime.parse(end);
		if ((birthday!=null)&&(endDate!=null)){
			switch (strategy) {
			case AGE_YEARS:
				return String.valueOf(DateUtil.getAgeInYears(birthday, endDate));
			case AGE_MONTHS:
				return String.valueOf(DateUtil.getAgeInMonths(birthday, endDate));
			case AGE_WEEKS:
				return String.valueOf(DateUtil.getAgeInWeeks(birthday, endDate));
			}
			return "";
		}
		return "";}

		/**
		 * @param value
		 * @return
		 * @throws Exception
		 */
		public String translateTestResult(String testName, String value) throws Exception {
			TestResult testResult = testResultsByTestName.get(testName);
			// if it is not in the table then its just a value in the result
			// that was NOT selected from a list, thus no translation
			if (testResult == null) {
				return value;
			}
			String type = testResult.getTestResultType();
			// if it is in the table D have to be translated through the
			// dictionary, otherwise don't
			if (TypeOfTestResultService.ResultType.DICTIONARY.getCharacterValue().equals(type)) {
				return ResourceTranslator.DictionaryTranslator.getInstance().translateRaw(value);
			} else if ( TypeOfTestResultService.ResultType.MULTISELECT.getCharacterValue().equals(type)) {
				return findMultiSelectItemsForTest(testResult.getTest().getId());
			}
			return value;
		}

		/**
		 * @param testId
		 * @return
		 * @throws SQLException
		 */
		private String findMultiSelectItemsForTest(String testId) throws SQLException {
			String sampleId = resultSet.getString("sample_id");
			List<Result> results = resultDAO.getResultsForTestAndSample(sampleId, testId);
			StringBuilder multi = new StringBuilder();
			for (Result result : results) {
				multi.append(ResourceTranslator.DictionaryTranslator.getInstance().translateRaw(result.getValue()));
                multi.append( "," );
			}
			
			if( multi.length() > 0){
				multi.setLength(multi.length() - 1);				
			}

			return multi.toString();
		}
	}


	abstract public void makeSQL();

	protected void defineAllObservationHistoryTypes() {
		ObservationHistoryTypeDAO ohtDao = new ObservationHistoryTypeDAOImpl();
		allObHistoryTypes = ohtDao.getAllOrderBy("type_name");
	}

	/**
	 * For every sample, one row per sample item, one column per test result for
	 * that sample item.
	 * 
	 * @param lowDatePostgres
	 * @param highDatePostgres
	 */
	protected void appendResultCrosstab(String lowDatePostgres, String highDatePostgres) {
		// A list of analytes which should not show up in the regular results,
		// because they are not the primary results, but, for example, is a
		// conclusion.
		// String excludeAnalytes = getExcludedAnalytesSet();
		String listName = "result";
		query.append(", \n\n ( SELECT si.samp_id, si.id AS sampleItem_id, si.sort_order AS sampleItemNo, " + listName + ".* "
				+ " FROM sample_item AS si LEFT JOIN \n ");

		// Begin cross tab / pivot table
        query.append(
               " crosstab( " 
				+ "\n 'SELECT si.id, t.description, r.value "
				+ "\n FROM clinlims.result AS r, clinlims.analysis AS a, clinlims.sample_item AS si, clinlims.sample AS s, clinlims.test AS t, clinlims.test_result AS tr "
                    + "\n WHERE "
                    + "\n s.id = si.samp_id"
                    + " AND s.entered_date >= date(''" + lowDatePostgres + "'')  AND s.entered_date <= date(''" + highDatePostgres + " '') "
                    + "\n AND s.id = si.samp_id "
                    + "\n AND si.id = a.sampitem_id "
                    + (( validStatusId == null )?"":
                           " AND a.status_id = " + validStatusId)
                    + "\n AND a.id = r.analysis_id "
                    + "\n AND r.test_result_id = tr.id"
                    + "\n AND tr.test_id = t.id       "                  
				// + (( excludeAnalytes == null)?"":
				// " AND r.analyte_id NOT IN ( " + excludeAnalytes) + ")"
				// + " AND a.test_id = t.id "
				+ "\n ORDER BY 1, 2 " 
                + "\n ', 'SELECT description FROM test where description != ''CD4'' AND is_active = ''Y'' ORDER BY 1' ) ");
		// end of cross tab

		// Name the test pivot table columns . We'll name them all after the
		// resource name, because some tests have fancy characters in them and
		// somewhere
		// between iReport, Java and postgres a complex name (e.g. one including
		// a beta, "�HCG Quant") get messed up and isn't found.
		query.append("\n as " + listName + " ( " // inner use of the list name
				+ "\"si_id\" numeric(10) ");
		for (Test col : allTests) {
			String testName = TestService.getLocalizedTestNameWithType( col );
			if (!"CD4".equals(testName)) { // CD4 is listed as a test name but
											// it isn't clear it should be line
											// 446 may also have to be changed
				query.append("\n, \"" + trimToPostgresMaxColumnName(testName) + "\" varchar(200) ");
			}
		}
		query.append(" ) \n");
        // left join all sample Items from the right sample range to the results table.
        query.append(
                        "\n ON si.id = " + listName + ".si_id "             // the inner use a few lines above
                      + "\n ORDER BY si.samp_id, si.id "
                      + "\n) AS " + listName + "\n " );     // outer re-use the list name to name this sparse matrix of results.        
	}

	/**
	 * @return
	 */
	private String getExcludedAnalytesSet() {
		String[] excludedAnalytes = new String[] { getGendCD4CountAnalyteId() };
		StringBuilder sb = new StringBuilder();
		for (String a : excludedAnalytes) {
			sb.append(a).append(",");
		}
		sb.setLength(sb.length() - 1);
		return sb.toString();
	}

	protected void appendObservationHistoryCrosstab(String lowDatePostgres, String highDatePostgres) {
		String listName = "demo";
		appendCrosstabPreamble(listName);
		query.append( // any Observation History items
                        "\n crosstab( " +
                        "\n 'SELECT DISTINCT oh.sample_id as samp_id, oht.type_name, value " +
                        "\n FROM observation_history AS oh, sample AS s, observation_history_type AS oht " +
                        "\n WHERE s.entered_date >= date(''" + lowDatePostgres + "'') " +
                        "\n AND s.entered_date <= date(''" + highDatePostgres +  "'')" +
                        "\n AND s.id = oh.sample_id AND oh.observation_history_type_id = oht.id order by 1;' " +
                        "\n , " +
                        "\n 'SELECT DISTINCT oht.type_name FROM observation_history_type AS oht ORDER BY 1;' " + // must be the same list as the column definition for the demo table below.
				"\n ) \n ");

		// in the following list of observation history items, all valid values
		// are listed in alphabetical order (since that is how crosstab lists
		// them), so that that the queries constructed don't have to filter the
		// exceptional ones.
		// It is the column name list defined in the constructor of this class
		// which picks the right column to display (and in what order). That
		// list is also used to generated the JasperReport XML
		// That list skips the OH types which actually repeat (priorDiseases,
		// priorARVTreatmentINNs) which are returned as as crosstab set of
		// columns either specially named columns (disease acronyms for each
		// diseases) or with with n after their name (priorARVTreatmentINNs1, 2,
		// ... )
		query.append(" as demo ( " + " \"s_id\"                           numeric(10) ");
		for (ObservationHistoryType oht : allObHistoryTypes) {
			query.append("\n," + oht.getTypeName() + " varchar(100) ");
		}
		query.append(" ) \n");
		appendCrosstabPostfix(lowDatePostgres, highDatePostgres, listName);
	}

	protected void appendCrosstabPreamble(String listName) {
        query.append( ", \n\n ( SELECT s.id AS samp_id, " + listName + ".* "
                        + " FROM sample AS s LEFT JOIN \n " );
	}

	protected void appendCrosstabPostfix(String lowDatePostgres, String highDatePostgres, String listName) {
        query.append(
              "\n ON s.id = " + listName + ".s_id "
            + " AND s.entered_date >= '" + lowDatePostgres + "'"
            + " AND s.entered_date <= '" + highDatePostgres + "'"
            + " ORDER BY 1 "
            + "\n) AS " + listName + "\n " );
	}

        
	protected Object pad20(Object translate) {
		// uncomment the following lines to help make everything line up when
		// debugging output.
		// String strIn = (String)translate;
		// int len = strIn.length();
		// len = ( len > 30 )? 30: len;
    //        translate = translate +  "                              ".substring(len);
		return translate;
	}

	/**
	 * Generate a column to the list of all columns. One for each possible test.
	 */
	protected void addAllResultsColumns() {
		for (Test test : allTests) {
			String testTag = TestService.getLocalizedTestNameWithType( test );
			if (!"CD4".equals(testTag)) {
				add(testTag, TestService.getLocalizedTestNameWithType( test ), TEST_RESULT );
			}
		}
	}

	/**
	 * Useful for the 1st line of a CSV files. This produces a completely
	 * escaped for MSExcel comma separated list of columns.
	 * 
	 * @return one string with all names.
	 */
	public String getColumnNamesLine() {
		StringBuilder line = new StringBuilder();
		for (CSVRoutineColumn column : columnsInOrder) {
			line.append(StringUtil.escapeCSVValue(column.csvName));
			line.append(",");
		}
		line.setLength(line.length() - 1);
		line.append(eol);
		return line.toString();
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public String nextLine() throws Exception {
		StringBuilder line = new StringBuilder();
		String accessionNumber = null;
		for (CSVRoutineColumn column : columnsInOrder) {
			//OK this is a little hocky.  Some of the custom translation strategies need the accession number and MOST of the time
			//they are called after the accession number column.  Not sure what to do if they are ever called before.
			String translatedValue = StringUtil.escapeCSVValue(getValue(column, accessionNumber));
			if( accessionNumber == null && "accession_number".equals(column.dbName)){
				accessionNumber = translatedValue;
			}
			line.append(translatedValue);
			line.append(",");
		}
		line.setLength(line.length() - 1);
		line.append(eol);
		return line.toString();
	}

	public boolean next() throws Exception {
		return resultSet.next();
	}

	/**
	 * @throws SQLException
	 * 
	 */
	public void closeResultSet() throws SQLException {
		resultSet.close();
		resultSet = null;
	}

	protected String getGendCD4CountAnalyteId() {
		if (gendCD4CountAnalyteId == null) {
			AnalyteDAO analyteDAO = new AnalyteDAOImpl();
			Analyte a = new Analyte();
			a.setAnalyteName("generated CD4 Count");
			this.gendCD4CountAnalyteId = analyteDAO.getAnalyteByName(a, false).getId();
		}
		return gendCD4CountAnalyteId;
	}
}
