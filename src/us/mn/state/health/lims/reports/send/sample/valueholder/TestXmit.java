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
package us.mn.state.health.lims.reports.send.sample.valueholder;

import java.sql.Date;
import java.util.ArrayList;

import us.mn.state.health.lims.common.valueholder.BaseObject;

public class TestXmit extends
		BaseObject {

	private CodeElementXmit name;

	private String method;

	private String status;

	private String testType;
	
	private String typeOfSample;
	
	private String sourceOfSample;
	
	private ArrayList results;
	
	private CommentXmit comment;
	
	private Date releasedDate;

	public CommentXmit getComment() {
		return comment;
	}

	public void setComment(CommentXmit comment) {
		this.comment = comment;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public CodeElementXmit getName() {
		return name;
	}

	public void setName(CodeElementXmit name) {
		this.name = name;
	}

	public Date getReleasedDate() {
		return releasedDate;
	}

	public void setReleasedDate(Date releasedDate) {
		this.releasedDate = releasedDate;
	}

	public ArrayList getResults() {
		return results;
	}

	public void setResults(ArrayList results) {
		this.results = results;
	}

	public String getSourceOfSample() {
		return sourceOfSample;
	}

	public void setSourceOfSample(String sourceOfSample) {
		this.sourceOfSample = sourceOfSample;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	public String getTypeOfSample() {
		return typeOfSample;
	}

	public void setTypeOfSample(String typeOfSample) {
		this.typeOfSample = typeOfSample;
	} 
	
}