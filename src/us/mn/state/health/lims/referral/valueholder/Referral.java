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
package us.mn.state.health.lims.referral.valueholder;

import java.sql.Timestamp;

import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.valueholder.BaseObject;
import us.mn.state.health.lims.common.valueholder.ValueHolder;
import us.mn.state.health.lims.common.valueholder.ValueHolderInterface;
import us.mn.state.health.lims.organization.valueholder.Organization;

public class Referral extends BaseObject {

	private static final long serialVersionUID = 1L;
	private String id;
	@SuppressWarnings("unused")
	private String analysisId;
	@SuppressWarnings("unused")
	private String organizationId;
	private String organizationName;
	private Timestamp requestDate;
	private Timestamp sendReadyDate;
	private Timestamp sentDate;
	private Timestamp resultRecievedDate;
	private String referralReasonId;
	private String referralTypeId;
	private String requesterName;
	private boolean canceled;

	private ValueHolderInterface analysis = new ValueHolder();
	private ValueHolderInterface organization = new ValueHolder();


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public Timestamp getSendReadyDate() {
		return sendReadyDate;
	}
	public void setSendReadyDate(Timestamp sendReadyDate) {
		this.sendReadyDate = sendReadyDate;
	}
	public Timestamp getSentDate() {
		return sentDate;
	}
	public void setSentDate(Timestamp sentDate) {
		this.sentDate = sentDate;
	}
	public Timestamp getResultRecievedDate() {
		return resultRecievedDate;
	}
	public void setResultRecievedDate(Timestamp resultRecievedDate) {
		this.resultRecievedDate = resultRecievedDate;
	}
	public String getReferralTypeId() {
		return referralTypeId;
	}
	public void setReferralTypeId(String referralTypeId) {
		this.referralTypeId = referralTypeId;
	}
	public String getRequesterName() {
		return requesterName;
	}
	public void setRequesterName(String requesterName) {
		this.requesterName = requesterName;
	}
	public Analysis getAnalysis() {
		return (Analysis)analysis.getValue();
	}
	public void setAnalysis(Analysis analysis) {
		this.analysis.setValue(analysis);
	}

	public Organization getOrganization() {
		return (Organization)organization.getValue();
	}
	public void setOrganization(Organization organization) {
		this.organization.setValue(organization);
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}
	public boolean isCanceled() {
		return canceled;
	}
	public void setRequestDate(Timestamp requestDate) {
		this.requestDate = requestDate;
	}
	public Timestamp getRequestDate() {
		return requestDate;
	}
	public void setReferralReasonId(String referralReasonId) {
		this.referralReasonId = referralReasonId;
	}
	public String getReferralReasonId() {
		return referralReasonId;
	}
}
