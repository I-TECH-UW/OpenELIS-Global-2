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
package us.mn.state.health.lims.common.provider.query;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.jfree.util.Log;

import us.mn.state.health.lims.common.servlet.validation.AjaxServlet;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.project.dao.ProjectDAO;
import us.mn.state.health.lims.project.daoimpl.ProjectDAOImpl;
import us.mn.state.health.lims.project.valueholder.Project;
import us.mn.state.health.lims.sample.util.AccessionNumberUtil;


public class ScanGeneratorProvider extends BaseQueryProvider {

	public ScanGeneratorProvider() {
		super();
	}

	public ScanGeneratorProvider(AjaxServlet ajaxServlet) {
		this.ajaxServlet = ajaxServlet;
	}

	@Override
	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String programCode = request.getParameter("programCode");
		String nextNumber = null;
		String error = null;
		try {
			if (GenericValidator.isBlankOrNull(programCode)) {
				nextNumber = getNextScanNumber("");	
			} else {
				//check program code validity
				ProjectDAO projectDAO = new ProjectDAOImpl();
				List<Project> programCodes = projectDAO.getAllProjects();
				boolean found = false;
				for ( Project code: programCodes ){
					if ( programCode.equals(code.getProgramCode())){
						found = true;
						break;
					}
			    }
				if (found) {
					nextNumber = getNextScanNumber(programCode);
					if (GenericValidator.isBlankOrNull(nextNumber)) {
						error = StringUtil.getMessageForKey("error.accession.no.next");
					}
				} else {
					error = StringUtil.getMessageForKey("errors.invalid", "program.code");
				}
			}
		} catch (IllegalStateException e) {
			error = StringUtil.getMessageForKey("error.accession.no.next");
			Log.error(e.toString());
		} catch (IllegalArgumentException e) {
			error = StringUtil.getMessageForKey("error.accession.no.next");
			Log.error(e.toString());
		}
		
		String result = GenericValidator.isBlankOrNull(nextNumber) ? INVALID : VALID;
		String returnData = GenericValidator.isBlankOrNull(error) ? nextNumber : error;

		ajaxServlet.sendData(returnData, result, request, response);
	}

	private String getNextScanNumber(String optionalPrefix) throws IllegalStateException {

		return AccessionNumberUtil.getNextAccessionNumber(optionalPrefix);
	}

}
