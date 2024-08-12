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
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.openelisglobal.common.action.IActionConstants;
import org.openelisglobal.common.exception.LIMSInvalidConfigurationException;
import org.openelisglobal.common.util.XMLUtil;
import org.openelisglobal.organization.service.OrganizationService;
import org.openelisglobal.organization.valueholder.Organization;
import org.openelisglobal.spring.util.SpringContext;

public class DepartmentsForReferringClinicProvider extends BaseQueryProvider {

    protected OrganizationService organizationService = SpringContext.getBean(OrganizationService.class);

    /**
     * @throws LIMSInvalidConfigurationException
     * @see org.openelisglobal.common.provider.query.BaseQueryProvider#processRequest(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        StringBuilder xml = new StringBuilder();
        String result = VALID;

        List<Organization> districts = organizationService
                .getOrganizationsByParentId(request.getParameter("referringClinicId")).stream()
                .filter(org -> org.getIsActive().equals(IActionConstants.YES)).collect(Collectors.toList());
        createDepartmentsXml(districts, request.getParameter("selectedValue"), xml);

        ajaxServlet.sendData(xml.toString(), result, request, response);
    }

    private void createDepartmentsXml(List<Organization> departments, String selectedValue, StringBuilder xml) {
        xml.append("<departments>");
        for (Organization org : departments) {
            xml.append("<department ");
            XMLUtil.appendAttributeKeyValue("id", org.getId(), xml);
            XMLUtil.appendAttributeKeyValue("value", org.getOrganizationName(), xml);
            xml.append(" />");
        }
        xml.append("</departments>");

        // prevent potential DOS with StringBuilder
        if (selectedValue.length() > 10) {
            selectedValue = selectedValue.subSequence(0, 10) + "...";
        }
        XMLUtil.appendKeyValue("selectedValue", selectedValue, xml);
    }
}
