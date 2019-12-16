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

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.validator.GenericValidator;
import org.openelisglobal.common.log.LogEvent;

public class HttpPostSender extends HttpSender {

    @Override
    public boolean sendMessage() {

        errors = new ArrayList<>();

        if (GenericValidator.isBlankOrNull(message) || GenericValidator.isBlankOrNull(url)) {
            LogEvent.logWarn("HttpPutSender", "send message",
                    "The " + message == null ? " message " : "url" + " is null");
            errors.add("send message The " + (message == null ? " message " : "url") + " is null");
            errors.add("Application not configured correctly for sending results");
            return false;
        }

        HttpClient httpclient = new HttpClient();
        setTimeout(httpclient);

        PostMethod httpPost = new PostMethod(url);

        RequestEntity requestEntity = null;
        try {
            requestEntity = new StringRequestEntity(message, "text/plain", "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            LogEvent.logError(e1);
        }

        httpPost.setRequestEntity(requestEntity);

        sendByHttp(httpclient, httpPost);

        return returnStatus == HttpStatus.SC_OK;
    }

}
