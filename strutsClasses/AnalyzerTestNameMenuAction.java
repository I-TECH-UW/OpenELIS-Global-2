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
*
* Contributor(s): CIRG, University of Washington, Seattle WA.
*/
package us.mn.state.health.lims.analyzerimport.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import us.mn.state.health.lims.analyzer.dao.AnalyzerDAO;
import us.mn.state.health.lims.analyzer.daoimpl.AnalyzerDAOImpl;
import us.mn.state.health.lims.analyzer.valueholder.Analyzer;
import us.mn.state.health.lims.analyzerimport.action.beans.NamedAnalyzerTestMapping;
import us.mn.state.health.lims.analyzerimport.util.AnalyzerTestNameCache;
import us.mn.state.health.lims.analyzerimport.util.MappedTestName;
import us.mn.state.health.lims.common.action.BaseMenuAction;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;


public class AnalyzerTestNameMenuAction extends BaseMenuAction {


	@SuppressWarnings("rawtypes")
	protected List createMenuList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		request.setAttribute("menuDefinition", "AnalyzerTestNameMenuDefinition");

		String stringStartingRecNo = (String) request.getAttribute("startingRecNo");
		int startingRecNo = Integer.parseInt(stringStartingRecNo);


		List<NamedAnalyzerTestMapping> mappedTestNameList = new ArrayList<NamedAnalyzerTestMapping>();
		List<String> analyzerList = AnalyzerTestNameCache.instance().getAnalyzerNames();
		AnalyzerDAO analyzerDAO = new AnalyzerDAOImpl();
		Analyzer analyzer = new Analyzer();

		for( String analyzerName : analyzerList){
			Collection<MappedTestName> mappedTestNames = AnalyzerTestNameCache.instance().getMappedTestsForAnalyzer(analyzerName).values();
			if( mappedTestNames.size() > 0){
				analyzer.setId(((MappedTestName)mappedTestNames.toArray()[0]).getAnalyzerId());
				analyzer = analyzerDAO.getAnalyzerById(analyzer);
				mappedTestNameList.addAll(convertedToNamedList(mappedTestNames, analyzer.getName()));
			}
		}

		setDisplayPageBounds(request, mappedTestNameList.size(), startingRecNo );

		return mappedTestNameList.subList(Math.min(mappedTestNameList.size(), startingRecNo -1 ),
										  Math.min(mappedTestNameList.size(), startingRecNo + getPageSize() ));

		//return mappedTestNameList;
	}


	private List<NamedAnalyzerTestMapping> convertedToNamedList(Collection<MappedTestName> mappedTestNameList, String analyzerName) {
		List<NamedAnalyzerTestMapping> namedMappingList = new ArrayList<NamedAnalyzerTestMapping>();

		for( MappedTestName test : mappedTestNameList){
			NamedAnalyzerTestMapping namedMapping = new NamedAnalyzerTestMapping();
			namedMapping.setActualTestName(test.getOpenElisTestName());
			namedMapping.setAnalyzerTestName(test.getAnalyzerTestName());
			namedMapping.setAnalyzerName(analyzerName);

			namedMappingList.add(namedMapping);
		}

		return namedMappingList;
	}

	private void setDisplayPageBounds(HttpServletRequest request, int listSize, int startingRecNo )
	throws LIMSRuntimeException {
		request.setAttribute(MENU_TOTAL_RECORDS, String.valueOf(listSize));
		request.setAttribute(MENU_FROM_RECORD, String.valueOf(startingRecNo));

		int numOfRecs = 0;
		if (listSize != 0) {
			numOfRecs = Math.min(listSize, getPageSize());

			numOfRecs--;
		}

		int endingRecNo = startingRecNo + numOfRecs;
		request.setAttribute(MENU_TO_RECORD, String.valueOf(endingRecNo));
	}

	protected String getPageTitleKey() {
		return "analyzerTestName.browse.title";
	}

	protected String getPageSubtitleKey() {
		return "analyzerTestName.browse.title";
	}


	protected String getDeactivateDisabled() {
		return "false";
	}

	protected String getEditDisabled() {
		return "true";
	}
}
