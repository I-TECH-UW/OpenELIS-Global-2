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
package us.mn.state.health.lims.result.valueholder;

import java.util.List;

import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.valueholder.BaseObject;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.person.valueholder.Person;
import us.mn.state.health.lims.sample.valueholder.Sample;

/**
 * @author benzd1 bugzilla 1992 - cleanup (for batchresultsverification: view
 *         all) - one instance of Sample_TestAnalyte per Analysis - one instance
 *         of Test_TestAnalyte per Sample_TestAnalyte
 * 
 */
public class Sample_TestAnalyte extends BaseObject {

	private List testAnalytes;

	private List sampleTestResultIds;

	private List sampleTestResults;

	private List resultLastupdatedList;
	
	// bugzilla 1942 this is a list of boolean flags on whether the result on this sample have notes attached
	//this is used for readonly in batch results entry to determine whether a result is eligible to be deleted or not (if note attached - don't delete result)
	private List resultHasNotesList;

	private Sample sample;

	// bugzilla #1346 add ability to hover over accession number and
	// view patient/person information (first and last name and external id)
	private Person person;

	private Patient patient;

	private Analysis analysis;

	// AIS - bugzilla 1872
	// bugzilla 1992: this object should be 1:1 with Sample_TestAnalyte
	private Test_TestAnalyte testTestAnalyte;

	// AIS - bugzilla 1863
	private List testResultValues;

	private List resultIds;
	
	//bugzilla 2227
	private String sampleHasTestRevisions;
	
	//bugzilla 1856 (is this analysis parent of other analyses?)
	private List children;
	
    //bugzilla 1856 this is used for sorting:
    //parent tests are loaded into the list of tests to sort in order to 
    //maintain the original sorting order
    //phantom tests are removed before displaying/reporting
	private boolean isPhantom;

	public List getChildren() {
		return children;
	}

	public void setChildren(List children) {
		this.children = children;
	}

	public String getSampleHasTestRevisions() {
		return sampleHasTestRevisions;
	}

	public void setSampleHasTestRevisions(String sampleHasTestRevisions) {
		this.sampleHasTestRevisions = sampleHasTestRevisions;
	}

	public Analysis getAnalysis() {
		return analysis;
	}

	public void setAnalysis(Analysis analysis) {
		this.analysis = analysis;
	}

	public Sample_TestAnalyte() {
		super();
	}

	public List getTestAnalytes() {
		return testAnalytes;
	}

	public void setTestAnalytes(List testAnalytes) {
		this.testAnalytes = testAnalytes;
	}

	public List getSampleTestResultIds() {
		return sampleTestResultIds;
	}

	public void setSampleTestResultIds(List sampleTestResultIds) {
		this.sampleTestResultIds = sampleTestResultIds;
	}

	public Sample getSample() {
		return sample;
	}

	public void setSample(Sample sample) {
		this.sample = sample;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List getSampleTestResults() {
		return sampleTestResults;
	}

	public void setSampleTestResults(List sampleTestResults) {
		this.sampleTestResults = sampleTestResults;
	}

	public List getResultLastupdatedList() {
		return resultLastupdatedList;
	}

	public void setResultLastupdatedList(List resultLastupdatedList) {
		this.resultLastupdatedList = resultLastupdatedList;
	}

	public Test_TestAnalyte getTestTestAnalyte() {
		return testTestAnalyte;
	}

	public void setTestTestAnalyte(Test_TestAnalyte testTestAnalyte) {
		this.testTestAnalyte = testTestAnalyte;
	}

	public List getTestResultValues() {
		return testResultValues;
	}

	public void setTestResultValues(List testResultValues) {
		this.testResultValues = testResultValues;
	}

	public List getResultIds() {
		return resultIds;
	}

	public void setResultIds(List resultIds) {
		this.resultIds = resultIds;
	}

	public List getResultHasNotesList() {
		return resultHasNotesList;
	}

	public void setResultHasNotesList(List resultHasNotesList) {
		this.resultHasNotesList = resultHasNotesList;
	}

	public boolean isPhantom() {
		return isPhantom;
	}

	public void setPhantom(boolean isPhantom) {
		this.isPhantom = isPhantom;
	}

}
