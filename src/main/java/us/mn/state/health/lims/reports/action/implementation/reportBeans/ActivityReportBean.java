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

package us.mn.state.health.lims.reports.action.implementation.reportBeans;

import java.sql.Timestamp;

/**
 */
public class ActivityReportBean{

    private String accessionNumber;
    private String receivedDate;
    private String collectionDate;
    private String resultDate;
    private String patientOrTestName;
    private String technician;
    private String resultValue;
    private String resultStatus;
    private String nonPrintingPatient;
    private Timestamp activityTime;


    public String getAccessionNumber(){
        return accessionNumber;
    }

    public void setAccessionNumber( String accessionNumber ){
        this.accessionNumber = accessionNumber;
    }

    public String getReceivedDate(){
        return receivedDate;
    }

    public void setReceivedDate( String receivedDate ){
        this.receivedDate = receivedDate;
    }

    public String getCollectionDate(){
        return collectionDate;
    }

    public void setCollectionDate( String collectionDate ){
        this.collectionDate = collectionDate;
    }

    public String getResultDate(){
        return resultDate;
    }

    public void setResultDate( String resultDate ){
        this.resultDate = resultDate;
    }

    public String getPatientOrTestName(){
        return patientOrTestName;
    }

    public void setPatientOrTestName( String patientOrTestName ){
        this.patientOrTestName = patientOrTestName;
    }

    public String getTechnician(){
        return technician;
    }

    public void setTechnician( String technician ){
        this.technician = technician;
    }

    public String getResultValue(){
        return resultValue;
    }

    public void setResultValue( String resultValue ){
        this.resultValue = resultValue;
    }

    public String getResultStatus(){
        return resultStatus;
    }

    public void setResultStatus( String resultStatus ){
        this.resultStatus = resultStatus;
    }

    public String getNonPrintingPatient(){
        return nonPrintingPatient;
    }

    public void setNonPrintingPatient( String nonPrintingPatient ){
        this.nonPrintingPatient = nonPrintingPatient;
    }

    public Timestamp getActivityTime(){
        return activityTime;
    }

    public void setActivityTime( Timestamp activityTime ){
        this.activityTime = activityTime;
    }
}
