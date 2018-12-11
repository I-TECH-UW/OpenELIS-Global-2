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
package us.mn.state.health.lims.result.dao;

import java.sql.Date;
import java.util.List;

import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.testanalyte.valueholder.TestAnalyte;
import us.mn.state.health.lims.testresult.valueholder.TestResult;

/**
 * @author diane benz
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public interface ResultDAO extends BaseDAO {

	public boolean insertData(Result result) throws LIMSRuntimeException;

	public void deleteData(List results) throws LIMSRuntimeException;

	public List getAllResults() throws LIMSRuntimeException;

	public List getPageOfResults(int startingRecNo) throws LIMSRuntimeException;

	public void getData(Result result) throws LIMSRuntimeException;

	public void updateData(Result result) throws LIMSRuntimeException;

	public List getNextResultRecord(String id) throws LIMSRuntimeException;

	public List getPreviousResultRecord(String id) throws LIMSRuntimeException;

	public Result getResultById(Result result) throws LIMSRuntimeException;

	public void getResultByAnalysisAndAnalyte(Result result, Analysis analysis,	TestAnalyte ta) throws LIMSRuntimeException;

	public void getResultByTestResult(Result result, TestResult testResult) throws LIMSRuntimeException;

	public List<Result> getResultsByAnalysis(Analysis analysis) throws LIMSRuntimeException;

	public List<Result> getReportableResultsByAnalysis(Analysis analysis) throws LIMSRuntimeException;


	public Result getResultForAnalyteInAnalysisSet(String analyteId, List<Integer> analysisIDList) throws LIMSRuntimeException;

    public Result getResultForAnalyteAndSampleItem(String analyteId, String sampleItemId) throws LIMSRuntimeException;

	public Result getResultById(String resultId) throws LIMSRuntimeException;


	public void deleteData(Result result) throws LIMSRuntimeException;

	public List<Result> getResultsForAnalysisIdList(List<Integer> analysisIdList) throws LIMSRuntimeException;
	
	public List<Result> getResultsForTestAndSample(String sampleId, String testId);

	public List<Result> getResultsForSample(Sample sample) throws LIMSRuntimeException;

	public List<Result> getChildResults(String resultId) throws LIMSRuntimeException;

    public List<Result> getResultsForTestInDateRange( String testId, Date startDate, Date endDate )throws LIMSRuntimeException;

    public List<Result> getResultsForPanelInDateRange( String panelId, Date lowDate, Date highDate )throws LIMSRuntimeException;

    public List<Result> getResultsForTestSectionInDateRange( String testSectionId, Date lowDate, Date highDate ) throws LIMSRuntimeException;
}
