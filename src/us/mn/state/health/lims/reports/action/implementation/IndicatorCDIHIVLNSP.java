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
 * Copyright (C) CIRG, University of Washington, Seattle WA.  All Rights Reserved.
 *
 */
package us.mn.state.health.lims.reports.action.implementation;

import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.dictionary.dao.DictionaryDAO;
import us.mn.state.health.lims.dictionary.daoimpl.DictionaryDAOImpl;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
//Note both Clinical and LNSP should extend common subclass
public class IndicatorCDIHIVLNSP extends IndicatorHIV implements IReportCreator, IReportParameterSetter {
	private static String HIV_POSITIVE1_ID = "undefined";
	private static String HIV_POSITIVE2_ID = "undefined";
	private static String HIV_POSITIVE12_ID = "undefined";
	private static String HIV_INDETERMINATE_ID = "undefined";
	
	static{
		HIV_TESTS.add("Dénombrement des lymphocytes CD4 (mm3)");
		HIV_TESTS.add("Dénombrement des lymphocytes  CD4 (%)");
		HIV_TESTS.add("Test rapide HIV 1 + HIV 2");
		
		DictionaryDAO dictionaryDAO = new DictionaryDAOImpl();
		Dictionary dictionary = dictionaryDAO.getDictionaryEntrysByNameAndCategoryDescription("Positif VIH 1", "Haiti Lab");
		if (dictionary != null) {
			HIV_POSITIVE1_ID = dictionary.getId();
		}
		
		dictionary = dictionaryDAO.getDictionaryEntrysByNameAndCategoryDescription("Positif VIH 2", "Haiti Lab");
		if (dictionary != null) {
			HIV_POSITIVE2_ID = dictionary.getId();
		}
		
		dictionary = dictionaryDAO.getDictionaryEntrysByNameAndCategoryDescription("Positif VIH1 et 2", "Haiti Lab");
		if (dictionary != null) {
			HIV_POSITIVE12_ID = dictionary.getId();
		}
		
		dictionary = dictionaryDAO.getDictionaryEntrysByNameAndCategoryDescription("Indetermine", "Haiti Lab");
		if (dictionary != null) {
			HIV_INDETERMINATE_ID = dictionary.getId();
		}
	}
	
	@Override
	protected String getLabNameLine1() {
		return StringUtil.getContextualMessageForKey("report.labName.one");
	}

	@Override
	protected String getLabNameLine2() {
		return StringUtil.getContextualMessageForKey("report.labName.two");
	}
	
	@Override
	protected boolean isPositive(String value) {
		return HIV_POSITIVE1_ID.equals(value) ||
			HIV_POSITIVE2_ID.equals(value) ||
			HIV_POSITIVE12_ID.equals(value);
	}
	
	@Override
	protected boolean isIndeterminate(String value) {
		return HIV_INDETERMINATE_ID.equals(value);
	}
	
}
