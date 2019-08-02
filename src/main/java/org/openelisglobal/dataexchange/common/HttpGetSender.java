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
package org.openelisglobal.dataexchange.common;

import java.util.ArrayList;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.validator.GenericValidator;
import org.openelisglobal.common.log.LogEvent;

public class HttpGetSender extends HttpSender {

    /*
     * (non-Javadoc)
     * 
     * @see org.openelisglobal.dataexchange.IExternalSender#sendMessage()
     */
    @Override
    public boolean sendMessage() {

        errors = new ArrayList<String>();

        if (GenericValidator.isBlankOrNull(url)) {
            LogEvent.logWarn("HttpGetSender", "send message", "The url is null");
            errors.add("send message The url is null");
            return false;
        }

        HttpClient httpclient = new HttpClient();
        setTimeout(httpclient);

        GetMethod httpGet = new GetMethod(url);

        sendByHttp(httpclient, httpGet);

        return returnStatus == HttpStatus.SC_OK;
    }

}
