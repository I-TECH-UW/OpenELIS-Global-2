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
 * Copyright (C) ITECH, University of Washington, Seattle WA.  All Rights Reserved.
 *
 */

package org.openelisglobal.dataexchange.order.valueholder;

import java.util.List;

public class ElectronicOrderDisplayItem {

    private String electronicOrderId;

    private String externalOrderId;

    private String requestDateDisplay;

    private String patientLastName;

    private String patientFirstName;

    private String patientNationalId;

    private String requestingFacility;

    private String status;

    private String testName;

    private String referringLabNumber;

    private String passportNumber;

    private String subjectNumber;

    private String labNumber;

    private List<String> warnings;

    public String getElectronicOrderId() {
        return electronicOrderId;
    }

    public void setElectronicOrderId(String electronicOrderId) {
        this.electronicOrderId = electronicOrderId;
    }

    public String getRequestDateDisplay() {
        return requestDateDisplay;
    }

    public void setRequestDateDisplay(String requestDateDisplay) {
        this.requestDateDisplay = requestDateDisplay;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientNationalId() {
        return patientNationalId;
    }

    public void setPatientNationalId(String patientNationalId) {
        this.patientNationalId = patientNationalId;
    }

    public String getRequestingFacility() {
        return requestingFacility;
    }

    public void setRequestingFacility(String requestingFacility) {
        this.requestingFacility = requestingFacility;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getReferringLabNumber() {
        return referringLabNumber;
    }

    public void setReferringLabNumber(String referringLabNumber) {
        this.referringLabNumber = referringLabNumber;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getSubjectNumber() {
        return subjectNumber;
    }

    public void setSubjectNumber(String subjectNumber) {
        this.subjectNumber = subjectNumber;
    }

    public String getLabNumber() {
        return labNumber;
    }

    public void setLabNumber(String labNumber) {
        this.labNumber = labNumber;
    }

    public String getExternalOrderId() {
        return externalOrderId;
    }

    public void setExternalOrderId(String externalOrderId) {
        this.externalOrderId = externalOrderId;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }

}
