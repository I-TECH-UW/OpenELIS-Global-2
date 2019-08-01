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
package org.openelisglobal.common.provider.validation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openelisglobal.samplepdf.service.SamplePdfService;
import org.openelisglobal.spring.util.SpringContext;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.servlet.validation.AjaxServlet;
import org.openelisglobal.common.util.StringUtil;

public class FileValidationProvider extends BaseValidationProvider {

    protected SamplePdfService samplePdfService = SpringContext.getBean(SamplePdfService.class);

    int indentLevel = -1;

    public FileValidationProvider() {
        super();
    }

    public FileValidationProvider(AjaxServlet ajaxServlet) {
        this.ajaxServlet = ajaxServlet;
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // get id from request
        String targetId = (String) request.getParameter("id");
        String formField = (String) request.getParameter("field");
        String result = validate(targetId);
        ajaxServlet.sendData(formField, result, request, response);
    }

    public String validate(String targetId) throws LIMSRuntimeException {
        String msg = INVALID;
        boolean isFound = false;
        if (!StringUtil.isNullorNill(targetId)) {
            try {
                int x = Integer.parseInt(targetId);
                isFound = samplePdfService.isAccessionNumberFound(x);
                if (isFound)
                    msg = VALID;
            } catch (NullPointerException npe) {
                // bugzilla 2154
                LogEvent.logError("FileValidationProvider", "validate()", npe.toString());
                msg = INVALID;
            }
        } else {
            msg = VALID;
        }
        return msg;
    }
}
