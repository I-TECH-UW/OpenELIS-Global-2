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
package org.openelisglobal.analyzerimport.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.validator.GenericValidator;
import org.openelisglobal.analyzerimport.analyzerreaders.AnalyzerReader;
import org.openelisglobal.analyzerimport.analyzerreaders.AnalyzerReaderFactory;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.login.service.LoginService;
import org.openelisglobal.login.valueholder.Login;
import org.openelisglobal.spring.util.SpringContext;
import org.openelisglobal.systemuser.service.SystemUserService;
import org.openelisglobal.systemuser.valueholder.SystemUser;

public class AnalyzerImportServlet extends HttpServlet {

    protected LoginService loginService = SpringContext.getBean(LoginService.class);
    protected SystemUserService systemUserService = SpringContext.getBean(SystemUserService.class);

    private static final long serialVersionUID = 1L;
    private static final String USER_FIELD_NAME = "user";
    private static final String PASSWORD_FIELD_NAME = "password";

    private static final long FILE_SIZE_MAX = 5 * 1024 * 1024;
    private static final long FIELD_SIZE_MAX = 1024;
    private static final long TOTAL_SIZE_MAX = FILE_SIZE_MAX + (2 * FIELD_SIZE_MAX);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String password = null;
        String user = null;
        AnalyzerReader reader = null;
        boolean fileRead = false;

        InputStream stream = null;

        try {
            ServletFileUpload upload = new ServletFileUpload();
            upload.setFileSizeMax(FILE_SIZE_MAX);
            upload.setSizeMax(TOTAL_SIZE_MAX);

            FileItemIterator iterator = upload.getItemIterator(request);
            while (iterator.hasNext()) {
                FileItemStream item = iterator.next();
                stream = item.openStream();

                String name = null;

                if (item.isFormField()) {

                    if (PASSWORD_FIELD_NAME.equals(item.getFieldName())) {
                        password = fieldStreamToString(stream);
                    } else if (USER_FIELD_NAME.equals(item.getFieldName())) {
                        user = fieldStreamToString(stream);
                    }

                } else {

                    name = item.getName();

                    reader = AnalyzerReaderFactory.getReaderFor(name);

                    if (reader != null) {
                        fileRead = reader.readStream(new InputStreamReader(stream));
                    }
                }

                stream.close();
            }
        } catch (Exception e) {
            LogEvent.logError(e.getMessage(), e);
            throw new ServletException(e);
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    LogEvent.logError(e.getMessage(), e);
                }
            }
        }

        if (GenericValidator.isBlankOrNull(user) || GenericValidator.isBlankOrNull(password)) {
            response.getWriter().print("missing user");
            response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
            return;
        }

        if (!userValid(user, password)) {
            response.getWriter().print("invalid user/password");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        if (fileRead) {
            boolean successful = reader.insertAnalyzerData(getSysUserId(user, password));

            if (successful) {
                response.getWriter().print("success");
                response.setStatus(HttpServletResponse.SC_OK);
                return;
            } else {
                if (reader != null) {
                    response.getWriter().print(reader.getError());
                }
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }

        } else {
            if (reader != null) {
                response.getWriter().print(reader.getError());
            }
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

    }

    private String getSysUserId(String user, String password) {
        Login login = new Login();
        login.setLoginName(user);
        login.setPassword(password);

        login = loginService.getValidatedLogin(user, password).orElse(null);

        if (login != null) {
            SystemUser systemUser = systemUserService.getDataForLoginUser(login.getLoginName());
            return systemUser.getId();
        }

        return "";
    }

    private boolean userValid(String user, String password) {
        Login login = new Login();
        login.setLoginName(user);
        login.setPassword(password);

        login = loginService.getValidatedLogin(user, password).orElse(null);

        if (login == null) {
            return false;
        } else {
            return true;
        }
    }

//    private String streamToString(InputStream stream) throws IOException {
//        StringBuilder builder = new StringBuilder();
//        int len;
//        byte[] buffer = new byte[1024];
//        while ((len = stream.read(buffer, 0, buffer.length)) != -1) {
//            builder.append(new String(buffer, 0, len, StandardCharsets.UTF_8));
//        }
//        return builder.toString();
//    }

    private String fieldStreamToString(InputStream stream) throws IOException {
        StringBuilder builder = new StringBuilder((int) (FIELD_SIZE_MAX / 2));
        int len;
        byte[] buffer = new byte[32];
        int totalFieldSize = 0;

        while ((len = stream.read(buffer, 0, buffer.length)) != -1) {
            builder.append(new String(buffer, 0, len, StandardCharsets.UTF_8));
            totalFieldSize += len;
            if (totalFieldSize >= FIELD_SIZE_MAX) {
                break;
            }
        }
        return builder.toString();
    }
}
