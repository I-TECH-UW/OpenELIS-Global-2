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
package us.mn.state.health.lims.login.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.provider.validation.FileValidationProvider;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.common.util.resources.ResourceLocator;
import us.mn.state.health.lims.login.dao.UserModuleDAO;
import us.mn.state.health.lims.login.dao.UserTestSectionDAO;
import us.mn.state.health.lims.login.valueholder.UserSessionData;
import us.mn.state.health.lims.result.valueholder.Sample_TestAnalyte;
import us.mn.state.health.lims.result.valueholder.Test_TestAnalyte;
import us.mn.state.health.lims.sample.dao.SampleDAO;
import us.mn.state.health.lims.sample.daoimpl.SampleDAOImpl;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.test.dao.TestDAO;
import us.mn.state.health.lims.test.dao.TestSectionDAO;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;
import us.mn.state.health.lims.test.daoimpl.TestSectionDAOImpl;
import us.mn.state.health.lims.test.valueholder.TestSection;

/**
 *  @author     Hung Nguyen (Hung.Nguyen@health.state.mn.us)
 */
public class UserTestSectionDAOImpl extends BaseDAOImpl implements UserTestSectionDAO {
	
	public List<Object> getAllUserTestSectionsByName(HttpServletRequest request, String testSectionName) 
		throws LIMSRuntimeException {
		
		TestSectionDAO testSectionDAO = new TestSectionDAOImpl();
		List<Object> list = new ArrayList<Object>();
		
		try {
			if ( SystemConfiguration.getInstance().getEnableUserTestSection().equals(NO) ) {
				list = testSectionDAO.getTestSections(testSectionName);
			} else {
				UserSessionData usd = (UserSessionData)request.getSession().getAttribute(USER_SESSION_DATA);
				//bugzilla 2160
				UserModuleDAO userModuleDAO = new UserModuleDAOImpl();
				if ( !userModuleDAO.isUserAdmin(request) )
					list = testSectionDAO.getTestSectionsBySysUserId(testSectionName,usd.getSystemUserId());
				else
					list = testSectionDAO.getTestSections(testSectionName);	
			}
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("UserTestSectionDAOImpl","getAllUserTestSectionsByName()",e.toString()); 
			throw new LIMSRuntimeException("Error in UserTestSectionDAOImpl getAllUserTestSectionsByName()", e);
		}
		return list;		
	}
//	 bugzilla 2371 add the searching capability
	public List getPageOfTestsBySysUserId(HttpServletRequest request, int startingRecNo, String doingSearch, String searchStr) 
		throws LIMSRuntimeException {	
		
		List list = new ArrayList();
		TestDAO testDAO = new TestDAOImpl();	
		
		try {
			if ( SystemConfiguration.getInstance().getEnableUserTestSection().equals(NO) ) {
				
				if (!StringUtil.isNullorNill(doingSearch) && doingSearch.equals(YES))
				   list = testDAO.getPageOfSearchedTests (startingRecNo, searchStr);
				else
				   list = testDAO.getPageOfTests(startingRecNo);
			} else {
				UserSessionData usd = (UserSessionData)request.getSession().getAttribute(USER_SESSION_DATA);
				//bugzilla 2160
				UserModuleDAO userModuleDAO = new UserModuleDAOImpl();
				if ( !userModuleDAO.isUserAdmin(request) ) 
				 {
					if (!StringUtil.isNullorNill(doingSearch) && doingSearch.equals(YES)) {
					
					   list = testDAO.getPageOfSearchedTestsBySysUserId(startingRecNo, usd.getSystemUserId(), searchStr);
					   }
					else
					   list = testDAO.getPageOfTestsBySysUserId(startingRecNo, usd.getSystemUserId());
				 }   
				else {
					
					
					 if (!StringUtil.isNullorNill(searchStr)) 
				  	     list = testDAO.getPageOfSearchedTests(startingRecNo, searchStr);	
					 else
						 list = testDAO.getPageOfTests (startingRecNo);
		             	
			     }
				// end if bugzilla 2371
			}
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("UserTestSectionDAOImpl","getPageOfTestsBySysUserId()",e.toString()); 
			throw new LIMSRuntimeException("Error in UserTestSectionDAOImpl getPageOfTestsBySysUserId()", e);
		}
		return list;	
	}	
	
	public List getAllUserTestSections(HttpServletRequest request) 
		throws LIMSRuntimeException {
		
		List list = new ArrayList();
		TestSectionDAO testSectDAO = new TestSectionDAOImpl();
		
		try {
			if ( SystemConfiguration.getInstance().getEnableUserTestSection().equals(NO) ) {
				list = testSectDAO.getAllTestSections();
			} else {
				UserSessionData usd = (UserSessionData)request.getSession().getAttribute(USER_SESSION_DATA);
				//bugzilla 2160
				UserModuleDAO userModuleDAO = new UserModuleDAOImpl();
				if ( !userModuleDAO.isUserAdmin(request) )
					list = testSectDAO.getAllTestSectionsBySysUserId(usd.getSystemUserId());
				else
					list = testSectDAO.getAllTestSections();		
			}
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("UserTestSectionDAOImpl","getAllUserTestSections()",e.toString()); 
			throw new LIMSRuntimeException("Error in UserTestSectionDAOImpl getAllUserTestSections()", e);
		} 	
		return list;
	}
	
	//bugzilla 2291 added onlyTestsFullySetup
	public List getAllUserTests(HttpServletRequest request, boolean onlyTestsFullySetup) throws LIMSRuntimeException {
		List list = new ArrayList();
		TestDAO testDAO = new TestDAOImpl();
		
		try {
			if ( SystemConfiguration.getInstance().getEnableUserTestSection().equals(NO) ) {
				list = testDAO.getAllTests(onlyTestsFullySetup);
			} else {
				UserSessionData usd = (UserSessionData)request.getSession().getAttribute(USER_SESSION_DATA);
				//bugzilla 2160
				UserModuleDAO userModuleDAO = new UserModuleDAOImpl();
				if ( !userModuleDAO.isUserAdmin(request) )
					list = testDAO.getAllTestsBySysUserId(usd.getSystemUserId(), onlyTestsFullySetup);
				else
					list = testDAO.getAllTests(onlyTestsFullySetup);			
			}
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("UserTestSectionDAOImpl","getAllUserTests()",e.toString());
			throw new LIMSRuntimeException("Error in UserTestSectionDAOImpl getAllUserTests()", e);
		} 	
		return list;
	}
	
	public List getSampleTestAnalytes(HttpServletRequest request, 
			List sample_Tas, List testSections) throws LIMSRuntimeException {
		
		try {
			if ( SystemConfiguration.getInstance().getEnableUserTestSection().equals(NO) ) {
				return sample_Tas;
			} else {
				//bugzilla 2160
				UserModuleDAO userModuleDAO = new UserModuleDAOImpl();				
				if ( !userModuleDAO.isUserAdmin(request) ) {		
					for ( int i=0; i<sample_Tas.size(); i++ ) {
						Sample_TestAnalyte sample_ta = (Sample_TestAnalyte)sample_Tas.get(i);
						Test_TestAnalyte test = sample_ta.getTestTestAnalyte();
						for ( int j =0; j<testSections.size(); j++ ) {
							TestSection testSection = (TestSection)testSections.get(j);
							if ( !test.getTest().getTestSection().getId().equals(testSection.getId()) ) {
								if ( sample_Tas.size() > 0 )
									sample_Tas.remove(i);
							}		
						}				
					}
				}
			}
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("UserTestSectionDAOImpl","getSampleTestAnalytes()",e.toString());
			throw new LIMSRuntimeException("Error in UserTestSectionDAOImpl getSampleTestAnalytes()", e);
		} 	
		return sample_Tas;
	}
	
	public List getSamplePdfList(HttpServletRequest request, Locale locale, 
			String sampStatus, String humanDomain) throws LIMSRuntimeException {
		
		List samplePdfList = new java.util.ArrayList();
		SampleDAO sampleDAO = new SampleDAOImpl();
		
		try {
			List statuses = new ArrayList();
			statuses.add(sampStatus);
			//bugzilla 2437: made getSamplesByStatusAndDomain more generic to take in list of statuses of samples to retrieve
			List sampleList = sampleDAO.getSamplesByStatusAndDomain(statuses,humanDomain);		
			FileValidationProvider fvp = new FileValidationProvider();
	
			String pdfMsg = ResourceLocator.getInstance().getMessageResources().getMessage(locale, "human.sample.pdf.message");			
		     
			for ( int i=0; i<sampleList.size(); i++ ) {
				Sample sam = (Sample)sampleList.get(i);
				String msg = fvp.validate(sam.getAccessionNumber());
				org.apache.struts.util.LabelValueBean lvb = new org.apache.struts.util.LabelValueBean();
				lvb.setValue(sam.getAccessionNumber());
				if ( msg.equals(VALID) )
					lvb.setLabel(sam.getAccessionNumber() + " - " + pdfMsg);
				else
					lvb.setLabel(sam.getAccessionNumber());

				samplePdfList.add(lvb);
			}
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("UserTestSectionDAOImpl","getSamplePdfList()",e.toString());
			throw new LIMSRuntimeException("Error in UserTestSectionDAOImpl getSamplePdfList()", e);
		} 	
		return samplePdfList;
	}	
	
	//bugzilla 2433, remove the analysis test section that does not assigned to the user
	public List getAnalyses(HttpServletRequest request, 
			List analyses, List testSections) throws LIMSRuntimeException {
		
		List newAnalyses = new ArrayList();
		try {
			if ( SystemConfiguration.getInstance().getEnableUserTestSection().equals(NO) ) {
				return newAnalyses;
			} else {
				UserModuleDAO userModuleDAO = new UserModuleDAOImpl();				
				if ( !userModuleDAO.isUserAdmin(request) ) {		
					newAnalyses = new ArrayList();
					for ( int i=0; i<analyses.size(); i++ ) {
						us.mn.state.health.lims.analysis.valueholder.Analysis analysis =
							(us.mn.state.health.lims.analysis.valueholder.Analysis)analyses.get(i);
						for ( int j =0; j<testSections.size(); j++ ) {
							TestSection testSection = (TestSection)testSections.get(j);
							if ( analysis.getTestSection().getId().equals(testSection.getId()) ) {
								newAnalyses.add(analysis);
							}		
						}				
					}
				} else {
					return analyses;
				}
			}

		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("UserTestSectionDAOImpl","getAnalyses()",e.toString());
			throw new LIMSRuntimeException("Error in UserTestSectionDAOImpl getAnalyses()", e);
		} 	
		return newAnalyses;		
		
	}
}