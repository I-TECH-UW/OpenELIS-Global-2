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
package us.mn.state.health.lims.sampleqaevent.valueholder;

import java.sql.Date;
import java.sql.Timestamp;

import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.common.valueholder.BaseObject;
import us.mn.state.health.lims.common.valueholder.ValueHolder;
import us.mn.state.health.lims.common.valueholder.ValueHolderInterface;
import us.mn.state.health.lims.qaevent.valueholder.QaEvent;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;

/**
 * @author benzd1
 * bugzilla 2510
 */
public class SampleQaEvent extends BaseObject {

	/**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String id;

	private String qaEventId;

	private ValueHolderInterface qaEvent;

	private String sampleId;

	private ValueHolderInterface sample;

	private String sampleItemId;

	private ValueHolderInterface sampleItem;

	private Date completedDate;
	
	private Timestamp enteredDate;

	private String completedDateForDisplay;
	

	public SampleQaEvent() {
		sample = new ValueHolder();
		sampleItem = new ValueHolder();
	    qaEvent = new ValueHolder();
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	
	public Sample getSample() {
		return (Sample) this.sample.getValue();
	}

	public void setSample(Sample sample) {
		this.sample.setValue(sample);
	}

	public SampleItem getSampleItem() {
	    return (SampleItem)this.sampleItem.getValue();
	}

	public void setSampleItem(SampleItem sampleItem) {
		this.sampleItem.setValue(sampleItem);
	}

	public QaEvent getQaEvent() {
		return (QaEvent) this.qaEvent.getValue();
	}

	public void setQaEvent(QaEvent qaEvent) {
		this.qaEvent.setValue(qaEvent);
	}

	public String getSampleId() {
		return sampleId;
	}

	public void setSampleId(String sampleId) {
		this.sampleId = sampleId;
	}

	public String getSampleItemId() {
		return sampleItemId;
	}

	public void setSampleItemId(String sampleItemId) {
		this.sampleItemId = sampleItemId;
	}

	public String getQaEventId() {
	    return qaEventId;
	}
	public void setQaEventId(String qaEventId) {
	    this.qaEventId = qaEventId;
	}
		
	public Date getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
		this.completedDateForDisplay = DateUtil.convertSqlDateToStringDate(completedDate);
	}

	public String getCompletedDateForDisplay() {
		return completedDateForDisplay;
	}

	public void setCompletedDateForDisplay(String completedDateForDisplay) {
		this.completedDateForDisplay = completedDateForDisplay;
		// also update the java.sql.Date
		String locale = SystemConfiguration.getInstance().getDefaultLocale()
				.toString();
		this.completedDate = DateUtil.convertStringDateToSqlDate(
				completedDateForDisplay, locale);
	}

	public Timestamp getEnteredDate() {
		return enteredDate;
	}

	public void setEnteredDate(Timestamp enteredDate) {
		this.enteredDate = enteredDate;
	}
}