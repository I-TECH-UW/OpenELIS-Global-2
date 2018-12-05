/**
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
*/
package us.mn.state.health.lims.common.provider.reports;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionMessages;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;
import us.mn.state.health.lims.analysis.dao.AnalysisDAO;
import us.mn.state.health.lims.analysis.daoimpl.AnalysisDAOImpl;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.analyte.valueholder.Analyte;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.exception.LIMSResultsReportHasNoDataException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.services.TestService;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.common.util.validator.ActionError;
import us.mn.state.health.lims.dictionary.dao.DictionaryDAO;
import us.mn.state.health.lims.dictionary.daoimpl.DictionaryDAOImpl;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.login.valueholder.UserSessionData;
import us.mn.state.health.lims.organization.valueholder.Organization;
import us.mn.state.health.lims.patient.dao.PatientDAO;
import us.mn.state.health.lims.patient.daoimpl.PatientDAOImpl;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.person.dao.PersonDAO;
import us.mn.state.health.lims.person.daoimpl.PersonDAOImpl;
import us.mn.state.health.lims.person.valueholder.Person;
import us.mn.state.health.lims.provider.dao.ProviderDAO;
import us.mn.state.health.lims.provider.daoimpl.ProviderDAOImpl;
import us.mn.state.health.lims.provider.valueholder.Provider;
import us.mn.state.health.lims.reports.valueholder.common.JRHibernateDataSource;
import us.mn.state.health.lims.reports.valueholder.resultsreport.ResultsReportAnalyteResult;
import us.mn.state.health.lims.reports.valueholder.resultsreport.ResultsReportAnalyteResultComparator;
import us.mn.state.health.lims.reports.valueholder.resultsreport.ResultsReportLabProject;
import us.mn.state.health.lims.reports.valueholder.resultsreport.ResultsReportSample;
import us.mn.state.health.lims.reports.valueholder.resultsreport.ResultsReportSampleComparator;
import us.mn.state.health.lims.reports.valueholder.resultsreport.ResultsReportTest;
import us.mn.state.health.lims.result.dao.ResultDAO;
import us.mn.state.health.lims.result.daoimpl.ResultDAOImpl;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.sample.dao.SampleDAO;
import us.mn.state.health.lims.sample.daoimpl.SampleDAOImpl;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.samplehuman.dao.SampleHumanDAO;
import us.mn.state.health.lims.samplehuman.daoimpl.SampleHumanDAOImpl;
import us.mn.state.health.lims.samplehuman.valueholder.SampleHuman;
import us.mn.state.health.lims.sampleitem.dao.SampleItemDAO;
import us.mn.state.health.lims.sampleitem.daoimpl.SampleItemDAOImpl;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;
import us.mn.state.health.lims.sampleorganization.dao.SampleOrganizationDAO;
import us.mn.state.health.lims.sampleorganization.daoimpl.SampleOrganizationDAOImpl;
import us.mn.state.health.lims.sampleorganization.valueholder.SampleOrganization;
import us.mn.state.health.lims.sampleproject.valueholder.SampleProject;
import us.mn.state.health.lims.sourceofsample.valueholder.SourceOfSample;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;

/**
 * @author benzd1
 * bugzilla 2264
 * bugzilla 1856 move pending tests to top section
 *               sort tests (parent/child recursive by sort order of test)
 *               add section previously reported tests
 */
public class ResultsReportProvider extends BaseReportsProvider{

	private AnalysisDAO analysisDAO;
	private SampleDAO sampleDAO;
	private ResultDAO resultDAO;
	private DictionaryDAO dictionaryDAO;
	private String dateAsText;
	private String originalMessage;
	private String amendedMessage;
	private String typeMessage;
	private String sourceMessage;
	private String sourceOtherMessage;
	private String notApplicableMessage;
	String testingPendingMessage; 
	private String resultsReportType;
	private ResultsReportAnalyteResult reportAnalyteResult;
	private final static int CURRENT_SECTION = 1;
	private final static int PREVIOUS_SECTION = 2;

	/* (non-Javadoc)
	 * @see us.mn.state.health.lims.common.provider.reports.BaseReportsProvider#processRequest(java.util.Map, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public boolean processRequest(Map parameters, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		//either standard or amended
		if (request.getParameter(RESULTS_REPORT_TYPE_PARAM) != null) { 
			resultsReportType = (String) request.getParameter(RESULTS_REPORT_TYPE_PARAM);
		} else {
			//bugzilla 1900 preview
			resultsReportType = (String)request.getAttribute(RESULTS_REPORT_TYPE_PARAM);
		}

		//bugzilla 1900 for preview we may have one to many accession numbers (samples)
		//              for which we need to preview report
		List accessionNumbers = new ArrayList();
		if (resultsReportType.equals(RESULTS_REPORT_TYPE_PREVIEW)) {
			if (request.getAttribute(ACCESSION_NUMBERS) != null) {
				accessionNumbers = (ArrayList)request.getAttribute(ACCESSION_NUMBERS);
			}
		}
		
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		File mainReportFile = new File(context
				.getRealPath("/WEB-INF/reports/rslts_main_report.jasper"));

		//  this report has several sub reports (and other dependencies)
		//  it is recommended to pass these in as JasperReport object parameters
		//  this works both for oc4j AND tomcat (where problems were occuring before this mod)
		File providerDetailsReportFile = new File(context
				.getRealPath("/WEB-INF/reports/rslts_provider_details.jasper"));
		File projectDetailsReportFile = new File(context
				.getRealPath("/WEB-INF/reports/rslts_project_details.jasper"));
		File sourceTypeDetailsReportFile = new File(context
				.getRealPath("/WEB-INF/reports/rslts_sourcetype_details.jasper"));
		File patientDetailsReportFile = new File(context
				.getRealPath("/WEB-INF/reports/rslts_patient_details.jasper"));
		File testResultsReportFile = new File(context
				.getRealPath("/WEB-INF/reports/rslts_test_results.jasper"));
		File resultValueReportFile = new File(context
				.getRealPath("/WEB-INF/reports/rslts_result_value.jasper"));
		File logoGifFile = new File(context
				.getRealPath("/WEB-INF/reports/images/rslts_logo.gif"));
		//bugzilla 1900
		File previewWaterMark = new File(context
				.getRealPath("/WEB-INF/reports/images/rslts_previewwatermark.gif"));

		Locale locale = SystemConfiguration.getInstance().getDefaultLocale();
		//bugzilla 2227
		analysisDAO = new AnalysisDAOImpl();
		sampleDAO = new SampleDAOImpl();
		Date today = Calendar.getInstance().getTime();
		dateAsText = DateUtil.formatDateAsText(today );

		org.hibernate.Transaction tx = HibernateUtil.getSession()
				.beginTransaction();
		ActionMessages errors = new ActionMessages();
		ActionError error = null;
		try {

			parameters.put(JRParameter.REPORT_LOCALE, locale);
			parameters.put(JRParameter.REPORT_RESOURCE_BUNDLE, ResourceBundle
					.getBundle("MessageResources", locale));

			//turn subreport jasper files into JasperReport objects to pass in as parameters
			JasperReport providerDetailsReport = (JasperReport) JRLoader
					.loadObject(providerDetailsReportFile.getPath());
			JasperReport projectDetailsReport = (JasperReport) JRLoader
					.loadObject(projectDetailsReportFile.getPath());
			JasperReport sourceTypeDetailsReport = (JasperReport) JRLoader
					.loadObject(sourceTypeDetailsReportFile.getPath());
			JasperReport patientDetailsReport = (JasperReport) JRLoader
					.loadObject(patientDetailsReportFile.getPath());
			JasperReport testResultsReport = (JasperReport) JRLoader
					.loadObject(testResultsReportFile.getPath());
			JasperReport resultValueReport = (JasperReport) JRLoader
					.loadObject(resultValueReportFile.getPath());

			parameters.put("Provider_Details", providerDetailsReport);
			parameters.put("Project_Details", projectDetailsReport);
			parameters.put("Sourcetype_Details", sourceTypeDetailsReport);
			parameters.put("Patient_Details", patientDetailsReport);
			parameters.put("Test_Results", testResultsReport);
			parameters.put("Result_Value", resultValueReport);
			parameters.put("Logo", logoGifFile);
			//bugzilla 1900
			parameters.put("PreviewWaterMark", previewWaterMark);

			//bugzilla 2227 find out if there are amended results
			List unfilteredListOfCurrentAnalyses = new ArrayList();
			//bugzilla 1900
			if (resultsReportType.equals(RESULTS_REPORT_TYPE_PREVIEW)) {
			  unfilteredListOfCurrentAnalyses = analysisDAO.getMaxRevisionAnalysesReadyForReportPreviewBySample(accessionNumbers);	
			} else {
			  unfilteredListOfCurrentAnalyses = analysisDAO
					.getMaxRevisionAnalysesReadyToBeReported();
			}
			
			List analysesPrinted = new ArrayList();

			HashMap samples = new HashMap();
			ResultsReportSample reportSample = new ResultsReportSample();
			List currentTests = new ArrayList();
			List previousTests = new ArrayList();
			List currentTestsToReport = new ArrayList();
			List previousTestsToReport = new ArrayList();
			List currentAnalyteResults = new ArrayList();
			List previousAnalyteResults = new ArrayList();
			reportAnalyteResult = new ResultsReportAnalyteResult();
			
			amendedMessage = getMessageForKey(request,
					"label.jasper.results.report.amended");
			originalMessage = getMessageForKey(request,
			"label.jasper.results.report.original");
			sourceMessage = getMessageForKey(request,
			"label.jasper.results.report.sourceofsample");
	        typeMessage = getMessageForKey(request,
			"label.jasper.results.report.typeofsample");
	        sourceOtherMessage = getMessageForKey(request,
			"label.jasper.results.report.sourceother");
            notApplicableMessage = getMessageForKey(request,
			"label.jasper.results.report.notapplicable");
            testingPendingMessage = getMessageForKey(request,
			"label.jasper.results.report.testing.pending");

			
            currentTestsToReport = populateTests(unfilteredListOfCurrentAnalyses, CURRENT_SECTION);

            //bugzilla 1900
            if (resultsReportType.equals(RESULTS_REPORT_TYPE_PREVIEW) && (currentTestsToReport == null || 
            		 currentTestsToReport.size() == 0)) {
               //throw Exception
            	throw new LIMSResultsReportHasNoDataException(
						"No data to display");
            }

            //based on currentTestsToReport find and populate sample information for reporting
			for (int i = 0; i < currentTestsToReport.size(); i++) {
				ResultsReportTest reportTest = (ResultsReportTest)currentTestsToReport.get(i);
				String id = (String) reportTest.getAnalysisId();
				Analysis analysis = new Analysis();
				analysis.setId(id);
				analysisDAO.getData(analysis);
				
				String accessionNumber = analysis.getSampleItem().getSample()
						.getAccessionNumber();
				if (samples.containsKey(accessionNumber)) {
					//get existing sample object
					reportSample = (ResultsReportSample) samples
							.get(accessionNumber);
					if (!analysis.getRevision().equals("0")) {
						reportSample.setSampleHasTestRevisions(TRUE);
					}
					currentTests = (List) reportSample.getTests();
				} else {
					Patient patient = new Patient();
					Person person = new Person();
					Provider provider = new Provider();
					Person providerPerson = new Person();
					Sample sample = new Sample();
					SampleHuman sampleHuman = new SampleHuman();
					SampleOrganization sampleOrganization = new SampleOrganization();
					SampleItem sampleItem = new SampleItem();
					TypeOfSample typeOfSample = new TypeOfSample();
					SourceOfSample sourceOfSample = new SourceOfSample();
					String sourceOther = null;

					PatientDAO patientDAO = new PatientDAOImpl();
					PersonDAO personDAO = new PersonDAOImpl();
					ProviderDAO providerDAO = new ProviderDAOImpl();
					SampleDAO sampleDAO = new SampleDAOImpl();
					SampleItemDAO sampleItemDAO = new SampleItemDAOImpl();
					SampleHumanDAO sampleHumanDAO = new SampleHumanDAOImpl();
					SampleOrganizationDAO sampleOrganizationDAO = new SampleOrganizationDAOImpl();

					sample.setAccessionNumber(accessionNumber);
					sampleDAO.getSampleByAccessionNumber(sample);
					if (!StringUtil.isNullorNill(sample.getId())) {
						reportSample = new ResultsReportSample();
						//initialize this value to false
						reportSample.setSampleHasTestRevisions(FALSE);
						//bugzilla 1900
						if (resultsReportType.equals(RESULTS_REPORT_TYPE_PREVIEW)) {
							reportSample.setSampleIsForPreview(TRUE);
						}

						reportSample.setSample(analysis.getSampleItem()
								.getSample());

						sampleHuman.setSampleId(sample.getId());
						sampleHumanDAO.getDataBySample(sampleHuman);
						sampleOrganization.setSample(sample);
						sampleOrganizationDAO
								.getDataBySample(sampleOrganization);
						sampleItem.setSample(sample);
						sampleItemDAO.getDataBySample(sampleItem);
						patient.setId(sampleHuman.getPatientId());
						patientDAO.getData(patient);
						person = patient.getPerson();
						personDAO.getData(person);

						provider.setId(sampleHuman.getProviderId());
						providerDAO.getData(provider);
						providerPerson = provider.getPerson();
						personDAO.getData(providerPerson);

						sourceOfSample = sampleItem.getSourceOfSample();
						typeOfSample = sampleItem.getTypeOfSample();
						sourceOther = sampleItem.getSourceOther();

						//now populate the reportSample
						Organization organization = sampleOrganization
								.getOrganization();
						String patientName = " " + StringUtil.trim(person.getLastName()) + ", "
								+ StringUtil.trim(person.getFirstName()) + "  "
								+ StringUtil.trim(person.getMiddleName());
						reportSample.setPatientName(patientName);
						String patientStreetAddress = " "
								+ StringUtil.trim(person.getStreetAddress())
								+ "  " + StringUtil.trim(person.getMultipleUnit());
						reportSample
								.setPatientStreetAddress(patientStreetAddress);
						//bugzilla 1852 splip out individual patient address fields
						String patientCity = ""
								+ StringUtil.trim(person.getCity());
    					String patientState = ""
							    + StringUtil.trim(person.getState());
						String patientZip = "" 
							    + StringUtil.trim(person.getZipCode());
						String patientCountry = ""
								+ StringUtil.trim(person.getCountry());
						reportSample
								.setPatientCity(patientCity);
						reportSample
						.setPatientState(patientState);
						reportSample.setPatientZip(patientZip);
						reportSample.setPatientCountry(patientCountry);
						String patientGender = patient.getGender();
						reportSample.setPatientGender(patientGender);
						String patientExternalId = patient.getExternalId();
						reportSample.setPatientExternalId(patientExternalId);
						String patientDateOfBirth = patient
								.getBirthDateForDisplay();
						reportSample.setPatientDateOfBirth(patientDateOfBirth);

						reportSample.setSampleItem(sampleItem);
						reportSample.setAccessionNumber(sample
								.getAccessionNumber());
						//bugzilla 2633
						if (organization != null) {
							reportSample.setOrganizationId(organization.getId());
							reportSample.setOrganizationName(organization
									.getOrganizationName());
							reportSample.setOrganizationStreetAddress(" "
									+ StringUtil.trim(organization.getStreetAddress()) + " "
									+ StringUtil.trim(organization.getMultipleUnit()));
							reportSample.setOrganizationCityStateZip(" "
									+ StringUtil.trim(organization.getCity()) + " "
									+ StringUtil.trim(organization.getState()) + " "
									+ StringUtil.trim(organization.getZipCode()));
						}
						reportSample.setSampleCollectionDate(sample
								.getCollectionDateForDisplay() + " " + sample.getCollectionTimeForDisplay());
						reportSample.setSampleReceivedDate(sample
								.getReceivedDateForDisplay());
						reportSample.setSampleClientReferenceNumber(sample
								.getClientReference());
						reportSample.setClinicianName(" "
								+ StringUtil.trim(providerPerson.getLastName())
								+ " "
								+ StringUtil
										.trim(providerPerson.getFirstName()));

						SampleProject project1 = null;
						SampleProject project2 = null;
						List sampleProjects = sample.getSampleProjects();
						if (sampleProjects != null && sampleProjects.size() > 0) {
							project1 = (SampleProject) sampleProjects.get(0);
							if (sampleProjects.size() > 1) {
								project2 = (SampleProject) sampleProjects
										.get(1);
							}
						}

						ResultsReportLabProject labProject = null;
						List labProjects = new ArrayList();
						if (project1 != null) {
							//add a labProject object
							String project = " "
									+ project1.getProject().getId() + "/"
									+ project1.getProject().getProjectName();
							labProject = new ResultsReportLabProject();
							labProject.setLabProject(project);
							labProjects.add(labProject);
						}
						if (project2 != null) {
							//add a labProject object
							String project = " "
									+ project2.getProject().getId() + "/"
									+ project2.getProject().getProjectName();
							labProject = new ResultsReportLabProject();
							labProject.setLabProject(project);
							labProjects.add(labProject);
						}
						JRHibernateDataSource resultsReport_Projects= new JRHibernateDataSource(
								labProjects);
						reportSample
								.setResultsReportProjects(resultsReport_Projects);

						//source of sample
						if (sampleItem.getSourceOfSample() != null
								&& !StringUtil.isNullorNill(sampleItem
										.getSourceOther())) {
							reportSample.setSourceOfSample(sourceMessage + ":"
									+ sourceOfSample.getDescription() + ","
									+ sourceOtherMessage + ":" + sourceOther);
						} else if (sampleItem.getSourceOfSample() != null
								&& StringUtil.isNullorNill(sampleItem
										.getSourceOther())) {
							reportSample.setSourceOfSample(sourceMessage + ":"
									+ sourceOfSample.getDescription() + ","
									+ sourceOtherMessage + ":"
									+ notApplicableMessage);
						} else if (sampleItem.getSourceOfSample() == null
								&& !StringUtil.isNullorNill(sampleItem
										.getSourceOther())) {
							reportSample.setSourceOfSample(sourceMessage + ":"
									+ notApplicableMessage + ","
									+ sourceOtherMessage + ":" + sourceOther);
						} else if (sampleItem.getSourceOfSample() == null
								&& StringUtil.isNullorNill(sampleItem
										.getSourceOther())) {
							reportSample.setSourceOfSample(sourceMessage + ":"
									+ notApplicableMessage + ","
									+ sourceOtherMessage + ":"
									+ notApplicableMessage);
						}
						//type of sample
						reportSample.setTypeOfSample(typeMessage + ":"
								+ typeOfSample.getDescription());

						if (!analysis.getRevision().equals("0")) {
							reportSample.setSampleHasTestRevisions(TRUE);
						} 
						currentTests = new ArrayList();
					}
				}
				currentTests.add(reportTest);
				reportSample.setTests(currentTests);
				samples.put(accessionNumber, reportSample);

			}
	

			//JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(samples);
			//convert hashmap to list
			List samplesList = new ArrayList();
			for (Iterator entryIter = samples.entrySet().iterator(); entryIter
					.hasNext();) {
				Map.Entry entry = (Map.Entry) entryIter.next();

				ResultsReportSample samp = (ResultsReportSample) entry
						.getValue();

				//are we printing this sample?
				if ((samp.getSampleHasTestRevisions().equals(TRUE) && resultsReportType.equals(RESULTS_REPORT_TYPE_AMENDED))
					|| (samp.getSampleHasTestRevisions().equals(FALSE) && resultsReportType.equals(RESULTS_REPORT_TYPE_ORIGINAL))
					//bugzilla 1900
			        || (resultsReportType.equals(RESULTS_REPORT_TYPE_PREVIEW))) {
				    //then this sample will print
			    } else {
			    	//skip this sample
			    	continue;
			    }

				//also make JRHibernateDataSource objects out of subreport lists
				currentTests = samp.getTests();
				
				//get this list of analyses BEFORE adding the pending tests to list (pending tests should not get a printed date)
				//bugzilla 1900 do not update printedDate on preview
				if (!resultsReportType.equals(RESULTS_REPORT_TYPE_PREVIEW)) {
					for (int i = 0; i < currentTests.size(); i++) {
						ResultsReportTest test = (ResultsReportTest)currentTests.get(i);
						//don't overwrite printed date on previously reported (amended) tests
						//but collect ones that are printed today (printed date is equal to today's date)
						//so we can update the database with the date also
						if (test.getPrintedDate() != null && test.getPrintedDate().equals(dateAsText)) {
							analysesPrinted.add(test.getAnalysis());
						}
					}
				}
				//get the pending tests as well for this sample: 
				List pendingAnalyses = null;
				//bugzilla 1900
				if (resultsReportType.equals(RESULTS_REPORT_TYPE_PREVIEW)) {
				 pendingAnalyses = analysisDAO
						.getMaxRevisionPendingAnalysesReadyForReportPreviewBySample(samp
								.getSample());
				} else {
					 pendingAnalyses = analysisDAO
						.getMaxRevisionPendingAnalysesReadyToBeReportedBySample(samp
								.getSample());	
				}
				
				List pendingReportTests = populatePendingTests(pendingAnalyses);
				
				//bugzilla 2027
				List previouslyReportedTests = analysisDAO.getAnalysesAlreadyReportedBySample(samp.getSample());
				previouslyReportedTests = populateTests(previouslyReportedTests, PREVIOUS_SECTION);

				//sort the pending tests
				pendingReportTests = completeHierarchyOfTestsForSorting(pendingReportTests);
				List totalList = sortTests(pendingReportTests);
				totalList = removePhantomTests(totalList);

				//sort the tests with results to report
				//first bring over any possible parent test hierarchies from previouslyReportedTests
				moveParentTestsOfCurrentTestsFromPreviouslyReportedTests(currentTests, previouslyReportedTests);
				currentTests = completeHierarchyOfTestsForSorting(currentTests);
				currentTests= sortTests(currentTests);
				currentTests = removePhantomTests(currentTests);
				//bugzilla 1900
				if (resultsReportType.equals(RESULTS_REPORT_TYPE_AMENDED) || resultsReportType.equals(RESULTS_REPORT_TYPE_PREVIEW)) {
                    currentTests = addPreviouslyReportedForAmendedTests(currentTests);
				}
				//bugzilla 1900
				populateTestsWithResults(currentTests);
				totalList.addAll(currentTests);
				
				
				//sort the previously reported tests
				previouslyReportedTests = completeHierarchyOfTestsForSorting(previouslyReportedTests);
				previouslyReportedTests = sortTests(previouslyReportedTests);
				previouslyReportedTests = removePhantomTests(previouslyReportedTests);
				//bugzilla 1900
				populateTestsWithResults(previouslyReportedTests);
				totalList.addAll(previouslyReportedTests);

				JRHibernateDataSource testsDS = new JRHibernateDataSource(totalList);
				samp.setResultsReportTests(testsDS);

		        samplesList.add(samp);

			}

			//sort samples by organization id and accessionNumber
			Collections.sort(samplesList, ResultsReportSampleComparator.VALUE_COMPARATOR);
			
			JRHibernateDataSource ds = new JRHibernateDataSource(samplesList);

			parameters.put("Report_Datasource", ds);

			//Finally, we have to be able to call the Hibernate data source from 
			//JasperReports. To do so, we start by looking at the JasperManager fillReport() 
			//method, which takes a JRDataSource object as its third parameter and uses it 
			//to generate the report : 

            //bugzilla 1900 moved this to here
			byte[] bytes = null;
			bytes = JasperRunManager.runReportToPdf(mainReportFile.getPath(),
					parameters, ds);

            //bugzilla 1900 moved this to here to fix java.lang.IllegalStateException: getOutputStream() has already been called
			ServletOutputStream servletOutputStream = response.getOutputStream();
			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);

			servletOutputStream.write(bytes, 0, bytes.length);
			servletOutputStream.flush();
			servletOutputStream.close();

			UserSessionData usd = (UserSessionData) request.getSession()
					.getAttribute(IActionConstants.USER_SESSION_DATA);
			String sysUserId = String.valueOf(usd.getSystemUserId());
			for (int i = 0; i < analysesPrinted.size(); i++) {
				Analysis analysis = (Analysis)analysesPrinted.get(i);
				analysis.setSysUserId(sysUserId);
				analysis.setPrintedDateForDisplay(dateAsText);
				analysisDAO.updateData(analysis);
			}
			tx.commit();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("ResultsReportProvider","processRequest()",e.toString());
			tx.rollback();

			if (e instanceof JRException) {
                error = new ActionError("errors.jasperreport.general", null, null);
    			//bugzilla 2154
			    LogEvent.logError("ResultsReportProvider","processRequest()",e.toString());
    		//bugzilla 1900
			} else if (e instanceof LIMSResultsReportHasNoDataException) {
			  if (accessionNumbers.size() > 1) {
				//message if report is for several samples
  				error = new ActionError("errors.jasperreport.resultsreports.nodata", null, null);
			  } else {
			    //message if report is for one sample
				error = new ActionError("errors.jasperreport.resultsreport.nodata", null, null);
			  }
			} else if (e instanceof org.hibernate.StaleObjectStateException) {
				error = new ActionError("errors.OptimisticLockException", null, null);
			} else {
				error = new ActionError("errors.jasperreport.general", null, null);
			}
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			request.setAttribute(Globals.ERROR_KEY, errors);
			
		} finally {
			HibernateUtil.closeSession();
    	}
		
		if (error != null) {
			return false;
		} else {
			return true;
		}
	}
	
	//this is for current and previous tests
	private List populateTests(List listOfTests, int section) {
		    resultDAO = new ResultDAOImpl();
		    dictionaryDAO = new DictionaryDAOImpl();
		    Dictionary dictionary = new Dictionary();
			//filter list of analyses to report (depending on whether amended/original report
			//preload list of resultsReportTests - then process those in a later loop
		    List testsToReport = new ArrayList();
			for (int i = 0; i < listOfTests.size(); i++) {
				String id = (String) listOfTests.get(i);
				Analysis analysis = new Analysis();
				analysis.setId(id);
				analysisDAO.getData(analysis);
				

				ResultsReportTest reportTest = new ResultsReportTest();
				reportTest.setAnalysis(analysis);
				String testName = TestService.getUserLocalizedTestName( analysis.getTest() );
				reportTest.setTestName(testName);
				//bugzilla 1900
				if (!resultsReportType.equals(RESULTS_REPORT_TYPE_PREVIEW)) {
					if (section == CURRENT_SECTION)
						reportTest.setPrintedDate(dateAsText);
				}
				if (section == PREVIOUS_SECTION)
				  reportTest.setPrintedDate(analysis.getPrintedDateForDisplay());
				String testMessage = "";
				//only do this for current test section
				if (section == CURRENT_SECTION) {
					if (!analysis.getRevision().equals("0")) {
						testMessage = " " + amendedMessage;
					}
				}
                reportTest.setTestMessage(testMessage);
				reportTest.setTestDescription(TestService.getLocalizedTestNameWithType( analysis.getTest() ));
				reportTest.setTestId(analysis.getTest().getId());
				reportTest.setAnalysisId(analysis.getId());

				testsToReport.add(reportTest);
				
			
			}
		return testsToReport;
	}
	
	private List populatePendingTests(List pendingAnalyses) throws Exception{
		List pendingReportTests = new ArrayList();
		for (int i = 0; i < pendingAnalyses.size(); i++) {
			String id = (String) pendingAnalyses.get(i);
			Analysis pendingAnalysis = new Analysis();
			pendingAnalysis.setId(id);
			analysisDAO.getData(pendingAnalysis);
			ResultsReportTest reportTest = new ResultsReportTest();
			reportTest.setAnalyteResults(null);
			reportTest.setAnalysis(pendingAnalysis);
			//this will be Testing Pending for other tests
			reportTest.setAnalysisStatus(testingPendingMessage);

			String testName = TestService.getUserLocalizedTestName( pendingAnalysis.getTest() );
			reportTest.setTestName(testName);
			reportTest.setTestMessage("");
			reportTest.setTestId(pendingAnalysis.getTest().getId());
			reportTest.setAnalysisId(pendingAnalysis.getId());
			reportTest.setTestDescription(TestService.getLocalizedTestNameWithType( pendingAnalysis.getTest() ));
			pendingReportTests.add(reportTest);

		}
		return pendingReportTests;

	}
	
	//bugzilla 1856
	protected List addPreviouslyReportedForAmendedTests(List currentTests) {
		if (currentTests != null && currentTests.size() > 0) {
			List tempCurrentTests = new ArrayList();
			for (int i = 0; i < currentTests.size(); i++) {
				ResultsReportTest test = (ResultsReportTest)currentTests.get(i);
				tempCurrentTests.add(test);
			}
			Iterator currentIt = tempCurrentTests.iterator();

			currentTests = new ArrayList();
			while (currentIt.hasNext()) {
				ResultsReportTest currentTest = (ResultsReportTest)currentIt.next();
				currentTests.add(currentTest);
				//only add a previous test IF this is a current test (not yet printed) AND revision > 0
				if (!currentTest.getAnalysis().getRevision().equals("0") && currentTest.getAnalysis().getPrintedDate() == null) {
					//get original test also
					Analysis previousAnalysis = analysisDAO.getPreviousAnalysisForAmendedAnalysis(currentTest.getAnalysis());
					ResultsReportTest reportTest = new ResultsReportTest();
					reportTest.setAnalysis(previousAnalysis);
					String testName = TestService.getUserLocalizedTestName( previousAnalysis.getTest() );
					reportTest.setTestName(testName);
					reportTest.setPrintedDate(previousAnalysis.getPrintedDateForDisplay());
					String testMessage = " " + originalMessage;
					reportTest.setTestMessage(testMessage);
					reportTest.setTestDescription(TestService.getUserLocalizedTestName( previousAnalysis.getTest() ));
					reportTest.setTestId(previousAnalysis.getTest().getId());
					reportTest.setAnalysisId(previousAnalysis.getId());
					currentTests.add(reportTest);
				}
			}
		}	
		return currentTests;
	}

	private void populateTestsWithResults(List testsToReport) {
		for (int i = 0; i < testsToReport.size(); i++) {
			ResultsReportTest reportTest = (ResultsReportTest)testsToReport.get(i);
			String id = (String) reportTest.getAnalysisId();
			Analysis analysis = new Analysis();
			analysis.setId(id);
			analysisDAO.getData(analysis);
			
			String accessionNumber = analysis.getSampleItem().getSample()
					.getAccessionNumber();

			//get reportable results for test, and corresponding analyte information
			List results = resultDAO
					.getReportableResultsByAnalysis(analysis);

			//double-check that there is at least one reportable result
			if (results == null || results.size() == 0) {
				continue;
			}

			List analyteResults = new ArrayList();
			for (int j = 0; j < results.size(); j++) {
				Result result = (Result) results.get(j);
				reportAnalyteResult = new ResultsReportAnalyteResult();
				reportAnalyteResult.setResult(result);
				Analyte analyte = (Analyte) result.getAnalyte();
				reportAnalyteResult.setAnalyte(analyte);
				reportAnalyteResult.setComponentName(analyte
						.getAnalyteName());
				String resultValue = "";
				if (result.getResultType().equals(
						SystemConfiguration.getInstance()
								.getDictionaryType())) {
					Dictionary dictionary = new Dictionary();
					dictionary.setId(result.getValue());
					dictionaryDAO.getData(dictionary);
					resultValue = dictionary.getDictEntry();
				} else {
					resultValue = result.getValue();
				}
				reportAnalyteResult.setResultValue(resultValue);
				String testResultSortOrder = "";
				testResultSortOrder = result.getSortOrder();
				reportAnalyteResult.setTestResultSortOrder(testResultSortOrder);
				analyteResults.add(reportAnalyteResult);
			}

		
			//sort the results by sort order of test result
			Collections.sort(analyteResults, ResultsReportAnalyteResultComparator.VALUE_COMPARATOR);
			reportTest.setAnalyteResults(analyteResults);
            reportTest.setAnalysisStatus(null);
            
			JRHibernateDataSource analyteResultsDS = new JRHibernateDataSource(
					analyteResults);
			reportTest.setResultsReportAnalyteResults(analyteResultsDS);
		}
	}
}
