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
package us.mn.state.health.lims.common.provider.selectdropdown;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.dictionary.dao.DictionaryDAO;
import us.mn.state.health.lims.dictionary.daoimpl.DictionaryDAOImpl;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.testanalyte.dao.TestAnalyteDAO;
import us.mn.state.health.lims.testanalyte.daoimpl.TestAnalyteDAOImpl;
import us.mn.state.health.lims.testanalyte.valueholder.TestAnalyte;
import us.mn.state.health.lims.testresult.dao.TestResultDAO;
import us.mn.state.health.lims.testresult.daoimpl.TestResultDAOImpl;
import us.mn.state.health.lims.testresult.valueholder.TestResult;
import us.mn.state.health.lims.testresult.valueholder.TestResultComparator;

/**
 * An example servlet that responds to an ajax:autocomplete tag action. This
 * servlet would be referenced by the baseUrl attribute of the JSP tag.
 * <p>
 * This servlet should generate XML in the following format:
 * </p>
 * <code><![CDATA[<?xml version="1.0"?>
 * <list>
 *   <item value="Item1">First Item</item>
 *   <item value="Item2">Second Item</item>
 *   <item value="Item3">Third Item</item>
 * </list>]]></code>
 * 
 * @author Darren L. Spurgeon
 */
public class TestAnalyteTestResultSelectDropDownProvider extends
		BaseSelectDropDownProvider {

	/**
	 * @see org.ajaxtags.demo.servlet.BaseAjaxServlet#getXmlContent(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public List processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		//System.out
		//		.println("I am in TestAnalyteTestResultSelectDropDownProvider ");

		String testAnalyteId = request.getParameter("testAnalyteId");

		List listOfTestResults = new ArrayList();

		TestResultDAO testResultDAO = new TestResultDAOImpl();
		TestAnalyte testAnalyte = new TestAnalyte();
		TestAnalyteDAO testAnalyteDAO = new TestAnalyteDAOImpl();
		DictionaryDAO dictDAO = new DictionaryDAOImpl();

		if (!StringUtil.isNullorNill(testAnalyteId)) {
			testAnalyte.setId(testAnalyteId);
			testAnalyteDAO.getData(testAnalyte);
			listOfTestResults = testResultDAO
					.getTestResultsByTestAndResultGroup(testAnalyte);
		}

		//System.out.println("Returning from Running getTestResultsByTestAndResultGr ");
		//System.out.println("size ofo list " + listOfTestResults.size());
		if ( listOfTestResults != null && listOfTestResults.size() > 0 ) {
			for (int i = 0;i < listOfTestResults.size();i++) {
				//System.out.println("one elem " + listOfTestResults.get(i));
			}
		}
		// for testResults load the value field with dict entry if needed
		List list = new ArrayList();
		for (int i = 0; i < listOfTestResults.size(); i++) {
			TestResult tr = new TestResult();
			tr = (TestResult) listOfTestResults.get(i);
			if (tr.getTestResultType().equals(
					SystemConfiguration.getInstance().getDictionaryType())) {
				// get from dictionary
				Dictionary dictionary = new Dictionary();
				dictionary.setId(tr.getValue());
				dictDAO.getData(dictionary);
				// System.out.println("setting dictEntry "
				// + dictionary.getDictEntry());
				//bugzilla 1847: use dictEntryDisplayValue
				tr.setValue(dictionary.getDictEntryDisplayValue());

			}
			list.add(tr);
		}

		Collections.sort(list, TestResultComparator.VALUE_COMPARATOR);

		return list;
	}

}
