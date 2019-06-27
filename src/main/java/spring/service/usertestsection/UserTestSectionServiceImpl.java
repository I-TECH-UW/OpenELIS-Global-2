package spring.service.usertestsection;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import spring.mine.internationalization.MessageUtil;
import spring.service.sample.SampleService;
import spring.service.test.TestSectionService;
import spring.service.test.TestService;
import spring.util.SpringContext;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.provider.validation.FileValidationProvider;
import us.mn.state.health.lims.common.util.LabelValuePair;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.login.dao.UserModuleService;
import us.mn.state.health.lims.login.valueholder.UserSessionData;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.test.valueholder.TestSection;

@Component
@Transactional
public class UserTestSectionServiceImpl implements UserTestSectionService {

	@Autowired
	private TestSectionService testSectionService;
	@Autowired
	private UserModuleService userModuleService;
	@Autowired
	private SampleService sampleService;

	@Override
	@Transactional(readOnly = true)
	public List<Object> getAllUserTestSectionsByName(HttpServletRequest request, String testSectionName)
			throws LIMSRuntimeException {
		List<Object> list = new ArrayList<>();

		try {
			if (SystemConfiguration.getInstance().getEnableUserTestSection().equals(IActionConstants.NO)) {
				list = testSectionService.getTestSections(testSectionName);
			} else {
				UserSessionData usd = (UserSessionData) request.getSession()
						.getAttribute(IActionConstants.USER_SESSION_DATA);
				// bugzilla 2160

				if (!userModuleService.isUserAdmin(request)) {
					list = testSectionService.getTestSectionsBySysUserId(testSectionName, usd.getSystemUserId());
				} else {
					list = testSectionService.getTestSections(testSectionName);
				}
			}
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("UserTestSectionServiceImpl", "getAllUserTestSectionsByName()", e.toString());
			throw new LIMSRuntimeException("Error in UserTestSectionServiceImpl getAllUserTestSectionsByName()", e);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List getPageOfTestsBySysUserId(HttpServletRequest request, int startingRecNo, String doingSearch,
			String searchStr) throws LIMSRuntimeException {

		List list = new ArrayList();
		TestService testService = SpringContext.getBean(TestService.class);

		try {
			if (SystemConfiguration.getInstance().getEnableUserTestSection().equals(IActionConstants.NO)) {

				if (!StringUtil.isNullorNill(doingSearch) && doingSearch.equals(IActionConstants.YES)) {
					list = testService.getPageOfSearchedTests(startingRecNo, searchStr);
				} else {
					list = testService.getPageOfTests(startingRecNo);
				}
			} else {
				UserSessionData usd = (UserSessionData) request.getSession()
						.getAttribute(IActionConstants.USER_SESSION_DATA);
				// bugzilla 2160
				UserModuleService userModuleService = SpringContext.getBean(UserModuleService.class);
				if (!userModuleService.isUserAdmin(request)) {
					if (!StringUtil.isNullorNill(doingSearch) && doingSearch.equals(IActionConstants.YES)) {

						list = testService.getPageOfSearchedTestsBySysUserId(startingRecNo, usd.getSystemUserId(),
								searchStr);
					} else {
						list = testService.getPageOfTestsBySysUserId(startingRecNo, usd.getSystemUserId());
					}
				} else {

					if (!StringUtil.isNullorNill(searchStr)) {
						list = testService.getPageOfSearchedTests(startingRecNo, searchStr);
					} else {
						list = testService.getPageOfTests(startingRecNo);
					}

				}
				// end if bugzilla 2371
			}
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("UserTestSectionServiceImpl", "getPageOfTestsBySysUserId()", e.toString());
			throw new LIMSRuntimeException("Error in UserTestSectionServiceImpl getPageOfTestsBySysUserId()", e);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List getAllUserTestSections(HttpServletRequest request) throws LIMSRuntimeException {

		List list = new ArrayList();
		try {
			if (SystemConfiguration.getInstance().getEnableUserTestSection().equals(IActionConstants.NO)) {
				list = testSectionService.getAllTestSections();
			} else {
				UserSessionData usd = (UserSessionData) request.getSession()
						.getAttribute(IActionConstants.USER_SESSION_DATA);
				// bugzilla 2160
				if (!userModuleService.isUserAdmin(request)) {
					list = testSectionService.getAllTestSectionsBySysUserId(usd.getSystemUserId());
				} else {
					list = testSectionService.getAllTestSections();
				}
			}
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("UserTestSectionServiceImpl", "getAllUserTestSections()", e.toString());
			throw new LIMSRuntimeException("Error in UserTestSectionServiceImpl getAllUserTestSections()", e);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List getAllUserTests(HttpServletRequest request, boolean onlyTestsFullySetup) throws LIMSRuntimeException {
		List list = new ArrayList();
		TestService testService = SpringContext.getBean(TestService.class);

		try {
			if (SystemConfiguration.getInstance().getEnableUserTestSection().equals(IActionConstants.NO)) {
				list = testService.getAllTests(onlyTestsFullySetup);
			} else {
				UserSessionData usd = (UserSessionData) request.getSession()
						.getAttribute(IActionConstants.USER_SESSION_DATA);
				// bugzilla 2160
				UserModuleService userModuleService = SpringContext.getBean(UserModuleService.class);
				if (!userModuleService.isUserAdmin(request)) {
					list = testService.getAllTestsBySysUserId(usd.getSystemUserId(), onlyTestsFullySetup);
				} else {
					list = testService.getAllTests(onlyTestsFullySetup);
				}
			}
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("UserTestSectionServiceImpl", "getAllUserTests()", e.toString());
			throw new LIMSRuntimeException("Error in UserTestSectionServiceImpl getAllUserTests()", e);
		}
		return list;
	}

//	@Override
//	@Transactional(readOnly = true)
//	public List getSampleTestAnalytes(HttpServletRequest request, List sample_Tas, List testSections)
//			throws LIMSRuntimeException {
//
//		try {
//			if (SystemConfiguration.getInstance().getEnableUserTestSection().equals(IActionConstants.NO)) {
//				return sample_Tas;
//			} else {
//				// bugzilla 2160
//				UserModuleService userModuleService = SpringContext.getBean(UserModuleService.class);
//				if (!userModuleService.isUserAdmin(request)) {
//					for (int i = 0; i < sample_Tas.size(); i++) {
//						Sample_TestAnalyte sample_ta = (Sample_TestAnalyte) sample_Tas.get(i);
//						Test_TestAnalyte test = sample_ta.getTestTestAnalyte();
//						for (int j = 0; j < testSections.size(); j++) {
//							TestSection testSection = (TestSection) testSections.get(j);
//							if (!test.getTest().getTestSection().getId().equals(testSection.getId())) {
//								if (sample_Tas.size() > 0) {
//									sample_Tas.remove(i);
//								}
//							}
//						}
//					}
//				}
//			}
//		} catch (Exception e) {
//			// bugzilla 2154
//			LogEvent.logError("UserTestSectionServiceImpl", "getSampleTestAnalytes()", e.toString());
//			throw new LIMSRuntimeException("Error in UserTestSectionServiceImpl getSampleTestAnalytes()", e);
//		}
//		return sample_Tas;
//	}

	@Override
	@Transactional(readOnly = true)
	public List getSamplePdfList(HttpServletRequest request, Locale locale, String sampStatus, String humanDomain)
			throws LIMSRuntimeException {

		List samplePdfList = new java.util.ArrayList();

		try {
			List statuses = new ArrayList();
			statuses.add(sampStatus);
			// bugzilla 2437: made getSamplesByStatusAndDomain more generic to take in list
			// of statuses of samples to retrieve
			List sampleList = sampleService.getSamplesByStatusAndDomain(statuses, humanDomain);
			FileValidationProvider fvp = new FileValidationProvider();

			String pdfMsg = MessageUtil.getMessage("human.sample.pdf.message");
			// ResourceLocator.getInstance().getMessageResources().getMessage(locale,
			// "human.sample.pdf.message");

			for (int i = 0; i < sampleList.size(); i++) {
				Sample sam = (Sample) sampleList.get(i);
				String msg = fvp.validate(sam.getAccessionNumber());
				LabelValuePair lvp = new LabelValuePair();
				lvp.setValue(sam.getAccessionNumber());
				if (msg.equals(IActionConstants.VALID)) {
					lvp.setLabel(sam.getAccessionNumber() + " - " + pdfMsg);
				} else {
					lvp.setLabel(sam.getAccessionNumber());
				}

				samplePdfList.add(lvp);
			}
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("UserTestSectionServiceImpl", "getSamplePdfList()", e.toString());
			throw new LIMSRuntimeException("Error in UserTestSectionServiceImpl getSamplePdfList()", e);
		}
		return samplePdfList;
	}

	@Override
	@Transactional(readOnly = true)
	public List getAnalyses(HttpServletRequest request, List analyses, List testSections) throws LIMSRuntimeException {

		List newAnalyses = new ArrayList();
		try {
			if (SystemConfiguration.getInstance().getEnableUserTestSection().equals(IActionConstants.NO)) {
				return newAnalyses;
			} else {
				UserModuleService userModuleService = SpringContext.getBean(UserModuleService.class);
				if (!userModuleService.isUserAdmin(request)) {
					newAnalyses = new ArrayList();
					for (int i = 0; i < analyses.size(); i++) {
						us.mn.state.health.lims.analysis.valueholder.Analysis analysis = (us.mn.state.health.lims.analysis.valueholder.Analysis) analyses
								.get(i);
						for (int j = 0; j < testSections.size(); j++) {
							TestSection testSection = (TestSection) testSections.get(j);
							if (analysis.getTestSection().getId().equals(testSection.getId())) {
								newAnalyses.add(analysis);
							}
						}
					}
				} else {
					return analyses;
				}
			}

		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("UserTestSectionServiceImpl", "getAnalyses()", e.toString());
			throw new LIMSRuntimeException("Error in UserTestSectionServiceImpl getAnalyses()", e);
		}
		return newAnalyses;

	}

//	@Override
//	@Transactional(readOnly = true)
//	public List getSampleTestAnalytes(HttpServletRequest request, List sample_Tas, List testSections)
//			throws LIMSRuntimeException {
//
//		try {
//			if (SystemConfiguration.getInstance().getEnableUserTestSection().equals(IActionConstants.NO)) {
//				return sample_Tas;
//			} else {
//				// bugzilla 2160
//				UserModuleService userModuleService = SpringContext.getBean(UserModuleService.class);
//				if (!userModuleService.isUserAdmin(request)) {
//					for (int i = 0; i < sample_Tas.size(); i++) {
//						Sample_TestAnalyte sample_ta = (Sample_TestAnalyte) sample_Tas.get(i);
//						Test_TestAnalyte test = sample_ta.getTestTestAnalyte();
//						for (int j = 0; j < testSections.size(); j++) {
//							TestSection testSection = (TestSection) testSections.get(j);
//							if (!test.getTest().getTestSection().getId().equals(testSection.getId())) {
//								if (sample_Tas.size() > 0) {
//									sample_Tas.remove(i);
//								}
//							}
//						}
//					}
//				}
//			}
//		} catch (Exception e) {
//			// bugzilla 2154
//			LogEvent.logError("UserTestSectionServiceImpl", "getSampleTestAnalytes()", e.toString());
//			throw new LIMSRuntimeException("Error in UserTestSectionServiceImpl getSampleTestAnalytes()", e);
//		}
//		return sample_Tas;
//}

}
