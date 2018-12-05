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
package us.mn.state.health.lims.analyzerimport.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.validator.GenericValidator;

import us.mn.state.health.lims.analyzerimport.analyzerreaders.AnalyzerReader;
import us.mn.state.health.lims.analyzerimport.analyzerreaders.AnalyzerReaderFactory;
import us.mn.state.health.lims.login.dao.LoginDAO;
import us.mn.state.health.lims.login.daoimpl.LoginDAOImpl;
import us.mn.state.health.lims.login.valueholder.Login;
import us.mn.state.health.lims.systemuser.dao.SystemUserDAO;
import us.mn.state.health.lims.systemuser.daoimpl.SystemUserDAOImpl;
import us.mn.state.health.lims.systemuser.valueholder.SystemUser;

public class AnalyzerImportServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String USER = "user";
	private static final String PASSWORD = "password";
	private String systemUserId;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String password = null;
		String user = null;
		AnalyzerReader reader = null;
		boolean fileRead = false;
			
		InputStream stream = null;
		
		try {
			ServletFileUpload upload = new ServletFileUpload();

			FileItemIterator iterator = upload.getItemIterator(request);
			while (iterator.hasNext()) {
				FileItemStream item = iterator.next();
				stream = item.openStream();
				
				String name = null;
				
				if( item.isFormField()){
					
					if(PASSWORD.equals(item.getFieldName())){
						password = streamToString(stream);
					}else if( USER.equals(item.getFieldName())){
						user = streamToString(stream);
					}
					
				}else{
					
					name = item.getName();
					
					reader = AnalyzerReaderFactory.getReaderFor(name);
					
					if( reader != null){
						fileRead = reader.readStream(new InputStreamReader( stream ));
					}					
				}
				
				stream.close();
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
				//	LOG.warning(e.toString());
				}
			}
		}
		
		if( GenericValidator.isBlankOrNull(user) || GenericValidator.isBlankOrNull(password)){
			response.getWriter().print("missing user");
			response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
			return;
		}
		
		if( !userValid(user, password)){
			response.getWriter().print("invalid user/password");
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return;
		}
		
		if(fileRead){
			boolean successful = reader.insertAnalyzerData(systemUserId);
			
			if( successful){
				response.getWriter().print("success");
				response.setStatus(HttpServletResponse.SC_OK);
				return;
			}else{
				response.getWriter().print(reader.getError());
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
			
		}else{
			response.getWriter().print(reader.getError());
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
	}
	
	private boolean userValid(String user, String password) {
		Login login = new Login();
		login.setLoginName(user);
		login.setPassword(password);
		
		LoginDAO loginDAO = new LoginDAOImpl();
		
		login = loginDAO.getValidateLogin(login);
		
		
		
		if( login == null ){
			return false;
		}else{
			SystemUserDAO systemUserDAO = new SystemUserDAOImpl();
			SystemUser systemUser = systemUserDAO.getDataForLoginUser(login.getLoginName());
			systemUserId = systemUser.getId();
		}
			
		return true;
	}

	private String streamToString(InputStream stream) throws IOException {
		StringBuilder builder = new StringBuilder();
		int len;
		byte[] buffer = new byte[1024];
		while ((len = stream.read(buffer, 0, buffer.length)) != -1) {
			builder.append(new String(buffer, 0, len));
		}
		return builder.toString();
	}
}
