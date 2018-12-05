/*
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
 * Copyright (C) ITECH, University of Washington, Seattle WA.  All Rights Reserved.
 */

package us.mn.state.health.lims.sample.bean;

import java.io.Serializable;
import java.util.Collection;

public class SampleOrderItem implements Serializable{
    private static final long serialVersionUID = 1L;

    private String newRequesterName;
    private Collection orderTypes;
    private String orderType;
    private String externalOrderNumber;
    private String labNo;
    private String requestDate;
    private String receivedDateForDisplay;
    private String receivedTime;
    private String nextVisitDate;
    private String requesterSampleID;
    private String referringPatientNumber;
    private String referringSiteId;
    private String referringSiteCode;
    private String referringSiteName;
    private Collection referringSiteList;
    private String providerId;
    private String providerFirstName;
    private String providerLastName;
    private String providerWorkPhone;
    private String providerFax;
    private String providerEmail;
    private String facilityAddressStreet;
    private String facilityAddressCommune;
    private String facilityPhone;
    private String facilityFax;
    private String paymentOptionSelection;
    private Collection paymentOptions;
    private String otherLocationCode;
    private Boolean modified = false;
    private String sampleId;
    private boolean readOnly = false;
    private String billingReferenceNumber;
    private String testLocationCode;
    private Collection testLocationCodeList;
    private String program;
    private Collection programList;

    public String getNewRequesterName(){
        return newRequesterName;
    }

    public void setNewRequesterName( String newRequesterName ){
        this.newRequesterName = newRequesterName;
    }

    public Collection getOrderTypes(){
        return orderTypes;
    }

    public void setOrderTypes( Collection orderTypes ){
        this.orderTypes = orderTypes;
    }

    public String getOrderType(){
        return orderType;
    }

    public void setOrderType( String orderType ){
        this.orderType = orderType;
    }

    public String getExternalOrderNumber(){
        return externalOrderNumber;
    }

    public void setExternalOrderNumber( String externalOrderNumber ){
        this.externalOrderNumber = externalOrderNumber;
    }

    public String getLabNo(){
        return labNo;
    }

    public void setLabNo( String labNo ){
        this.labNo = labNo;
    }

    public String getRequestDate(){
        return requestDate;
    }

    public void setRequestDate( String requestDate ){
        this.requestDate = requestDate;
    }

    public String getReceivedDateForDisplay(){
        return receivedDateForDisplay;
    }

    public void setReceivedDateForDisplay( String receivedDateForDisplay ){
        this.receivedDateForDisplay = receivedDateForDisplay;
    }

    public String getReceivedTime(){
        return receivedTime;
    }

    public void setReceivedTime( String receivedTime ){
        this.receivedTime = receivedTime;
    }

    public String getNextVisitDate(){
        return nextVisitDate;
    }

    public void setNextVisitDate( String nextVisitDate ){
        this.nextVisitDate = nextVisitDate;
    }

    public String getRequesterSampleID(){
        return requesterSampleID;
    }

    public void setRequesterSampleID( String requesterSampleID ){
        this.requesterSampleID = requesterSampleID;
    }

    public String getReferringPatientNumber(){
        return referringPatientNumber;
    }

    public void setReferringPatientNumber( String referringPatientNumber ){
        this.referringPatientNumber = referringPatientNumber;
    }

    public String getReferringSiteId(){
        return referringSiteId;
    }

    public void setReferringSiteId( String referringSiteId ){
        this.referringSiteId = referringSiteId;
    }

    public String getReferringSiteCode(){
        return referringSiteCode;
    }

    public void setReferringSiteCode( String referringSiteCode ){
        this.referringSiteCode = referringSiteCode;
    }

    public String getReferringSiteName(){
        return referringSiteName;
    }

    public void setReferringSiteName( String referringSiteName ){
        this.referringSiteName = referringSiteName;
    }

    public Collection getReferringSiteList(){
        return referringSiteList;
    }

    public void setReferringSiteList( Collection referringSiteList ){
        this.referringSiteList = referringSiteList;
    }

    public String getProviderId(){
        return providerId;
    }

    public void setProviderId( String providerId ){
        this.providerId = providerId;
    }

    public String getProviderFirstName(){
        return providerFirstName;
    }

    public void setProviderFirstName( String providerFirstName ){
        this.providerFirstName = providerFirstName;
    }

    public String getProviderLastName(){
        return providerLastName;
    }

    public void setProviderLastName( String providerLastName ){
        this.providerLastName = providerLastName;
    }

    public String getProviderWorkPhone(){
        return providerWorkPhone;
    }

    public void setProviderWorkPhone( String providerWorkPhone ){
        this.providerWorkPhone = providerWorkPhone;
    }

    public String getProviderFax(){
        return providerFax;
    }

    public void setProviderFax( String providerFax ){
        this.providerFax = providerFax;
    }

    public String getProviderEmail(){
        return providerEmail;
    }

    public void setProviderEmail( String providerEmail ){
        this.providerEmail = providerEmail;
    }

    public String getFacilityAddressStreet(){
        return facilityAddressStreet;
    }

    public void setFacilityAddressStreet( String facilityAddressStreet ){
        this.facilityAddressStreet = facilityAddressStreet;
    }

    public String getFacilityAddressCommune(){
        return facilityAddressCommune;
    }

    public void setFacilityAddressCommune( String facilityAddressCommune ){
        this.facilityAddressCommune = facilityAddressCommune;
    }

    public String getFacilityPhone(){
        return facilityPhone;
    }

    public void setFacilityPhone( String facilityPhone ){
        this.facilityPhone = facilityPhone;
    }

    public String getFacilityFax(){
        return facilityFax;
    }

    public void setFacilityFax( String facilityFax ){
        this.facilityFax = facilityFax;
    }

    public String getPaymentOptionSelection(){
        return paymentOptionSelection;
    }

    public void setPaymentOptionSelection( String paymentOptionSelection ){
        this.paymentOptionSelection = paymentOptionSelection;
    }

    public Collection getPaymentOptions(){
        return paymentOptions;
    }

    public void setPaymentOptions( Collection paymentOptions ){
        this.paymentOptions = paymentOptions;
    }

    public String getOtherLocationCode(){
        return otherLocationCode;
    }

    public void setOtherLocationCode( String otherLocationCode ){
        this.otherLocationCode = otherLocationCode;
    }

    public Boolean getModified(){
        return modified;
    }

    public void setModified( Boolean modified ){
        this.modified = modified;
    }

    public String getSampleId(){
        return sampleId;
    }

    public void setSampleId( String sampleId ){
        this.sampleId = sampleId;
    }

    public boolean isReadOnly(){
        return readOnly;
    }

    public void setReadOnly( boolean readOnly ){
        this.readOnly = readOnly;
    }

    public String getBillingReferenceNumber(){
        return billingReferenceNumber;
    }

    public void setBillingReferenceNumber( String billingReferenceNumber ){
        this.billingReferenceNumber = billingReferenceNumber;
    }

    public String getTestLocationCode(){
        return testLocationCode;
    }

    public void setTestLocationCode( String testLocationCode ){
        this.testLocationCode = testLocationCode;
    }

    public Collection getTestLocationCodeList(){
        return testLocationCodeList;
    }

    public void setTestLocationCodeList( Collection testLocationCodeList ){
        this.testLocationCodeList = testLocationCodeList;
    }

    public String getProgram(){
        return program;
    }

    public void setProgram( String program ){
        this.program = program;
    }

    public Collection getProgramList(){
        return programList;
    }

    public void setProgramList( Collection programList ){
        this.programList = programList;
    }
}