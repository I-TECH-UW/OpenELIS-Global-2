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
 * Copyright (C) The Minnesota Department of Health.  All Rights Reserved.
 *
 * Contributor(s): CIRG, University of Washington, Seattle WA.
 */
package org.openelisglobal.common.provider.query;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;

import org.openelisglobal.sample.service.SampleService;
import org.openelisglobal.samplehuman.service.SampleHumanService;
import org.openelisglobal.sampleorganization.service.SampleOrganizationService;
import org.openelisglobal.spring.util.SpringContext;
import org.openelisglobal.common.exception.LIMSInvalidConfigurationException;
import org.openelisglobal.common.services.StatusService;
import org.openelisglobal.common.services.StatusSet;
import org.openelisglobal.common.util.XMLUtil;
import org.openelisglobal.organization.valueholder.Organization;
import org.openelisglobal.sample.valueholder.Sample;
import org.openelisglobal.sampleorganization.valueholder.SampleOrganization;

/**
 * @author Paul A. Hill (pahill@uw.edu)
 * @since Jul 14, 2010
 */
public class SampleSearchPopulateProvider extends BaseQueryProvider {

    protected SampleService sampleService = SpringContext.getBean(SampleService.class);
    protected SampleHumanService sampleHumanService = SpringContext.getBean(SampleHumanService.class);
    protected SampleOrganizationService sampleOrganizationService = SpringContext
            .getBean(SampleOrganizationService.class);

    /**
     * @throws LIMSInvalidConfigurationException
     * @see org.openelisglobal.common.provider.query.BaseQueryProvider#processRequest(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String patientID = (String) request.getParameter("patientKey");
        String accessionNo = (String) request.getParameter("accessionNo");

        StringBuilder xml = new StringBuilder();
        String result = VALID;
        Sample sample;
        if (!GenericValidator.isBlankOrNull(patientID)) {
            sample = getSampleForPatientID(patientID);
        } else {
            sample = sampleService.getSampleByAccessionNumber(accessionNo);
            StatusSet statusSet = StatusService.getInstance().getStatusSetForAccessionNumber(accessionNo);
            patientID = statusSet.getPatientId();
        }

        if (sample == null) {
            xml.append("empty");
            // result = MessageUtil.getMessage("xxx");
        } else {
            createReturnXML(sample, patientID, xml);
        }

        ajaxServlet.sendData(xml.toString(), result, request, response);

    }

    /**
     * @param sample a sample found in the DB
     * @param xml
     */
    private void createReturnXML(Sample sample, String patientId, StringBuilder xml) {
        XMLUtil.appendKeyValue("patientPK", patientId, xml);
        XMLUtil.appendKeyValue("samplePK", sample.getId(), xml);
        XMLUtil.appendKeyValue("labNo", sample.getAccessionNumber(), xml);
        XMLUtil.appendKeyValue("receivedDateForDisplay", sample.getReceivedDateForDisplay(), xml);
        XMLUtil.appendKeyValue("collectionDateForDisplay", sample.getCollectionDateForDisplay(), xml);
        XMLUtil.appendKeyValue("receivedTimeForDisplay", sample.getReceivedTimeForDisplay(), xml);
        XMLUtil.appendKeyValue("collectionTimeForDisplay", sample.getCollectionTimeForDisplay(), xml);

        Organization o = getOrganizationForSample(sample);
        if (o != null) {
            XMLUtil.appendKeyValue("centerName", o.getOrganizationName(), xml);
            XMLUtil.appendKeyValue("centerCode", o.getId(), xml);
        }
    }

    /**
     * @param sample
     * @return first organization, if any of this sample
     */
    private Organization getOrganizationForSample(Sample sample) {
        SampleOrganization so = new SampleOrganization();
        so.setSample(sample);
        sampleOrganizationService.getDataBySample(so);
        return so.getOrganization();
    }

    private Sample getSampleForPatientID(String patientID) {
        List<Sample> samples = sampleHumanService.getSamplesForPatient(patientID);
        Sample sample = findBestMatch(samples);
        // Reread in order to fill in pretend columns (aka accessionNumber)
        sampleService.getData(sample);
        return sample;
    }

    /**
     *
     * @param samples
     * @return one Sample to return to the UI
     */
    private Sample findBestMatch(List<Sample> samples) {
        Sample best = samples.get(0);
        for (Sample sample : samples) {
            // currently latest entered date is the criteria
            if (best.getEnteredDate().getTime() <= sample.getEnteredDate().getTime()) {
                best = sample;
            }
        }
        return best;
    }
}
