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
package us.mn.state.health.lims.analyzerimport.valueholder;

import org.apache.commons.validator.GenericValidator;

import us.mn.state.health.lims.common.valueholder.BaseObject;

public class AnalyzerTestMapping extends BaseObject{

	private static final long serialVersionUID = 1L;

	private String id = "1"; //not this is not used but it is needed in the audit trail
	private AnalyzerTestMappingPK compoundId = new AnalyzerTestMappingPK();
	private String testId;
	private String uniqueIdentifyer;
	
	public void setCompoundId(AnalyzerTestMappingPK compoundId) {
		uniqueIdentifyer = null;
		this.compoundId = compoundId;
	}

	public AnalyzerTestMappingPK getCompoundId() {
		return compoundId;
	}

	public void setAnalyzerId(String analyzerId) {
		uniqueIdentifyer = null;
		compoundId.setAnalyzerId(analyzerId);
	}

	public String getAnalyzerId() {
		return compoundId == null ? null : compoundId.getAnalyzerId();
	}

	public String getAnalyzerTestName() {
		return compoundId == null ? null : compoundId.getAnalyzerTestName();
	}
	public void setAnalyzerTestName(String analyzerTestName) {
		uniqueIdentifyer = null;
		compoundId.setAnalyzerTestName(analyzerTestName);
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	public String getTestId() {
		return testId;
	}

	public void setUniqueIdentifyer(String uniqueIdentifyer) {
		this.uniqueIdentifyer = uniqueIdentifyer;
	}

	public String getUniqueIdentifyer() {
		if( GenericValidator.isBlankOrNull(uniqueIdentifyer)){
			uniqueIdentifyer = getAnalyzerId() + "-" + getAnalyzerTestName();
		}

		return uniqueIdentifyer;
	}

	public String getId() {
		return id;
	}

}
