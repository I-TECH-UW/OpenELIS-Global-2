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

import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.analyte.valueholder.Analyte;
import us.mn.state.health.lims.common.valueholder.EnumValueItemImpl;
import us.mn.state.health.lims.common.valueholder.ValueHolder;
import us.mn.state.health.lims.common.valueholder.ValueHolderInterface;
import us.mn.state.health.lims.dataexchange.orderresult.OrderResponseWorker.Event;
import us.mn.state.health.lims.testresult.valueholder.TestResult;

public class Result extends EnumValueItemImpl {


	private static final long serialVersionUID = 1L;

	private String id;
	private ValueHolderInterface analysis;
	private ValueHolderInterface analyte;
	private ValueHolderInterface testResult;
	private String sortOrder;
	private String isReportable;
	private String resultType;
	private String value;
	private Double minNormal;
	private Double maxNormal;
    private int significantDigits;
	private ValueHolder parentResult;
    private int grouping;
    
    private Event resultEvent;

	public Result() {
		super();
		analysis = new ValueHolder();
		analyte = new ValueHolder();
		testResult = new ValueHolder();
		parentResult = new ValueHolder();
	}

	public Analysis getAnalysis() {
		return (Analysis) this.analysis.getValue();
	}

	public void setAnalysis(Analysis analysis) {
		this.analysis.setValue(analysis);
	}

	public Analyte getAnalyte() {
		return (Analyte) this.analyte.getValue();
	}

	public void setAnalyte(Analyte analyte) {
		this.analyte.setValue(analyte);
	}

	public String getIsReportable() {
		return isReportable;
	}

	public void setIsReportable(String isReportable) {
		this.isReportable = isReportable;
	}

	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	@Override
	public String getSortOrder() {
		return sortOrder;
	}

	@Override
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public TestResult getTestResult() {
		return (TestResult) this.testResult.getValue();
	}

	public void setTestResult(TestResult testResult) {
		this.testResult.setValue(testResult);
	}


	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public Double getMinNormal() {
		return minNormal;
	}

	public void setMinNormal(Double minNormal) {
		this.minNormal = minNormal;
	}

	public Double getMaxNormal() {
		return maxNormal;
	}

	public void setMaxNormal(Double maxNormal) {
		this.maxNormal = maxNormal;
	}

    public int getSignificantDigits(){
        return significantDigits;
    }

    public void setSignificantDigits( int significantDigits ){
        this.significantDigits = significantDigits;
    }

    public Result getParentResult() {
		return (Result)parentResult.getValue();
	}

	public void setParentResult(Result parentResult) {
		this.parentResult.setValue( parentResult );
	}

    public int getGrouping(){
        return grouping;
    }

    public void setGrouping( int grouping ){
        this.grouping = grouping;
    }

	public Event getResultEvent() {
		return resultEvent;
	}

	public void setResultEvent(Event resultEvent) {
		this.resultEvent = resultEvent;
	}
}
