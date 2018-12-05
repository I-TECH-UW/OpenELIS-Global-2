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
package us.mn.state.health.lims.qaevent.valueholder;

import java.util.List;

import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.valueholder.BaseObject;

public class Test_QaEvents extends BaseObject {

	private Analysis analysis;
	private List qaEvents;


	public Test_QaEvents() {
		super();
	}


	public Analysis getAnalysis() {
		return analysis;
	}


	public void setAnalysis(Analysis analysis) {
		this.analysis = analysis;
	}

	public List getQaEvents() {
		return qaEvents;
	}


	public void setQaEvents(List qaEvents) {
		this.qaEvents = qaEvents;
	}

}
