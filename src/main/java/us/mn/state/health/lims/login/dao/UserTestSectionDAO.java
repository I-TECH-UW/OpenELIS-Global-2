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
package us.mn.state.health.lims.login.dao;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;

/**
 *  @author     Hung Nguyen (Hung.Nguyen@health.state.mn.us)
 */
public interface UserTestSectionDAO extends BaseDAO {

	public List<Object> getAllUserTestSectionsByName(HttpServletRequest request, 
			String testSectionName) throws LIMSRuntimeException; 
	// bugzilla 2371
	public List<Object> getPageOfTestsBySysUserId(HttpServletRequest request, 
			int startingRecNo, String doingSearch, String searchStr) throws LIMSRuntimeException; 
	public List<Object> getAllUserTestSections(HttpServletRequest request) throws LIMSRuntimeException;
	//bugzilla 2291 (added boolean onlyTestsFullySetup)
	public List<Object> getAllUserTests(HttpServletRequest request, boolean onlyTestsFullySetup) throws LIMSRuntimeException; 
	public List<Object> getSampleTestAnalytes(HttpServletRequest request,
			List<Object> sample_Tas, List<Object> testSections) throws LIMSRuntimeException; 
	public List<Object> getSamplePdfList(HttpServletRequest request, 
			Locale locale, String sampStatus, String humanDomain) throws LIMSRuntimeException;
	//bugzilla 2433
	public List<Object> getAnalyses(HttpServletRequest request,
			List<Object> analyses, List<Object> testSections) throws LIMSRuntimeException; 
}
