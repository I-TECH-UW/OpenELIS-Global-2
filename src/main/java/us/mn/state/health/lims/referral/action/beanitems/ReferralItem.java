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
package us.mn.state.health.lims.referral.action.beanitems;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import us.mn.state.health.lims.common.util.IdValuePair;

public class ReferralItem implements IReferralResultTest, Serializable {

	private static final long serialVersionUID = 1L;

	private String referralId;
	private String accessionNumber;
	private String sampleType;
	private String referringTestName = "";
	private String referralResults = "";
	private String referralDate;
	private String referrer;
	private String referredInstituteId;
	private String referredTestId;
    //the shadow is to track if the test has been changed by the user
    private String referredTestIdShadow;
	private List<IdValuePair> testSelectionList;
	private String referredResult = "";
	private String referredDictionaryResult;
    private String referredMultiDictionaryResult = "";
	private String referredResultType = "";
	private List<IdValuePair> dictionaryResults = new ArrayList<IdValuePair>(  );
	private String referredSendDate;
	private String referredReportDate;
	private String pastNotes;
	private String note;
	private String additionalTestsXMLWad;
	private boolean canceled = false;
	private String referralReasonId;
	private String referralResultId;
	private boolean modified = false;
	private List<ReferredTest> additionalTests;
	private String inLabResultId;
    private String multiSelectResultValues;

	public String getReferralId() {
		return referralId;
	}
	public void setReferralId(String referralId) {
		this.referralId = referralId;
	}
	public String getAccessionNumber() {
		return accessionNumber;
	}
	public void setAccessionNumber(String accessionNumber) {
		this.accessionNumber = accessionNumber;
	}
	public String getSampleType() {
		return sampleType;
	}
	public void setSampleType(String sampleType) {
		this.sampleType = sampleType;
	}
	public String getReferringTestName() {
		return referringTestName;
	}
	public void setReferringTestName(String referringTestName) {
		this.referringTestName = referringTestName;
	}

	public String getReferralResults() {
		return referralResults;
	}

    public String getReferralDate() {
		return referralDate;
	}
	public void setReferralDate(String referralDate) {
		this.referralDate = referralDate;
	}
	public String getReferrer() {
		return referrer;
	}
	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}
	public String getReferredInstituteId() {
		return referredInstituteId;
	}
	public void setReferredInstituteId(String referredInstituteId) {
		this.referredInstituteId = referredInstituteId;
	}
	public String getReferredTestId() {
		return referredTestId;
	}
	public void setReferredTestId(String referredTestId) {
		this.referredTestId = referredTestId;
	}

    public String getReferredTestIdShadow(){
        return referredTestIdShadow;
    }

    public void setReferredTestIdShadow( String referredTestIdShadow ){
        this.referredTestIdShadow = referredTestIdShadow;
    }

    public List<IdValuePair> getTestSelectionList() {
		return testSelectionList;
	}
	public void setTestSelectionList(List<IdValuePair> testSelectionList) {
		this.testSelectionList = testSelectionList;
	}
	public String getReferredResult() {
		return referredResult;
	}
	public void setReferredResult(String referredResult) {
		this.referredResult = referredResult;
	}
	public String getReferredResultType() {
		return referredResultType;
	}
	public void setReferredResultType(String referredResultType) {
		this.referredResultType = referredResultType;
	}
	public List<IdValuePair> getDictionaryResults() {
		return dictionaryResults;
	}
	public void setDictionaryResults(List<IdValuePair> dictionaryResults) {
		this.dictionaryResults = dictionaryResults;
	}
	public String getReferredSendDate() {
		return referredSendDate;
	}
	public void setReferredSendDate(String referredRecieveDate) {
		this.referredSendDate = referredRecieveDate;
	}
	public String getReferredReportDate() {
		return referredReportDate;
	}
	public void setReferredReportDate(String referredReportDate) {
		this.referredReportDate = referredReportDate;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getAdditionalTestsXMLWad() {
		return additionalTestsXMLWad;
	}
	public void setAdditionalTestsXMLWad(String additionalTestsXMLWad) {
		this.additionalTestsXMLWad = additionalTestsXMLWad;
	}
	public boolean isCanceled() {
		return canceled;
	}
	public void setCanceled(boolean cancel) {
		this.canceled = cancel;
	}
	public void setReferralReasonId(String referralReasonId) {
		this.referralReasonId = referralReasonId;
	}
	public String getReferralReasonId() {
		return referralReasonId;
	}
	public void setModified(boolean modified) {
		this.modified = modified;
	}
	public boolean isModified() {
		return modified;
	}
	public void setAdditionalTests(List<ReferredTest> additionalTests) {
		this.additionalTests = additionalTests;
	}
	public List<ReferredTest> getAdditionalTests() {
		return additionalTests;
	}
	public void setReferralResultId(String referralResultId) {
		this.referralResultId = referralResultId;
	}
	public String getReferralResultId() {
		return referralResultId;
	}
	public String getReferredDictionaryResult() {
		return referredDictionaryResult;
	}
	public void setReferredDictionaryResult(String referredDictionaryResult) {
		this.referredDictionaryResult = referredDictionaryResult;
	}

	public String getInLabResultId() {
		return inLabResultId;
	}
	public void setInLabResultId( String inLabResultId ) {
		this.inLabResultId = inLabResultId;
	}
    public void setReferralResults(String referralResults) {
        this.referralResults = referralResults;
    }
    public void setReferredMultiDictionaryResult(String referredMultiDictionaryResult) {
        this.referredMultiDictionaryResult = referredMultiDictionaryResult;
    }
    public String getReferredMultiDictionaryResult() {
        return referredMultiDictionaryResult;
    }
	public void setPastNotes(String pastNotes) {
		this.pastNotes = pastNotes;
	}
	public String getPastNotes() {
		return pastNotes;
	}

    public String getMultiSelectResultValues(){
        return multiSelectResultValues;
    }

    public void setMultiSelectResultValues( String multiSelectResultValues ){
        this.multiSelectResultValues = multiSelectResultValues;
    }
}
