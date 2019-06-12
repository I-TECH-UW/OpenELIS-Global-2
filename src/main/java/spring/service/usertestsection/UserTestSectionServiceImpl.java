package spring.service.usertestsection;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import spring.mine.internationalization.MessageUtil;
import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.provider.validation.FileValidationProvider;
import us.mn.state.health.lims.common.util.LabelValuePair;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.login.dao.UserModuleService;
import us.mn.state.health.lims.login.daoimpl.UserModuleServiceImpl;
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

@Component
@Transactional 
public class UserTestSectionServiceImpl extends BaseObjectServiceImpl<TestSection, String> implements UserTestSectionService{

	public UserTestSectionServiceImpl(Class<TestSection> clazz) {
		super(clazz);
		// TODO Auto-generated constructor stub
	}
	
	public UserTestSectionServiceImpl() {
		super(TestSection.class);
	}
	
	@Override
	protected BaseDAO<TestSection, String> getBaseObjectDAO() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public TestSection get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<TestSection> getMatch(String propertyName, Object propertyValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<TestSection> getMatch(Map<String, Object> propertyValues) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TestSection> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TestSection> getAllMatching(String propertyName, Object propertyValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TestSection> getAllMatching(Map<String, Object> propertyValues) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TestSection> getAllOrdered(String orderProperty, boolean descending) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TestSection> getAllOrdered(List<String> orderProperties, boolean descending) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TestSection> getAllMatchingOrdered(String propertyName, Object propertyValue, String orderProperty,
			boolean descending) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TestSection> getAllMatchingOrdered(String propertyName, Object propertyValue,
			List<String> orderProperties, boolean descending) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TestSection> getAllMatchingOrdered(Map<String, Object> propertyValues, String orderProperty,
			boolean descending) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TestSection> getAllMatchingOrdered(Map<String, Object> propertyValues, List<String> orderProperties,
			boolean descending) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TestSection> getPage(int startingRecNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TestSection> getMatchingPage(String propertyName, Object propertyValue, int startingRecNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TestSection> getMatchingPage(Map<String, Object> propertyValues, int startingRecNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TestSection> getOrderedPage(String orderProperty, boolean descending, int startingRecNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TestSection> getOrderedPage(List<String> orderProperties, boolean descending, int startingRecNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TestSection> getMatchingOrderedPage(String propertyName, Object propertyValue, String orderProperty,
			boolean descending, int startingRecNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TestSection> getMatchingOrderedPage(String propertyName, Object propertyValue,
			List<String> orderProperties, boolean descending, int startingRecNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TestSection> getMatchingOrderedPage(Map<String, Object> propertyValues, String orderProperty,
			boolean descending, int startingRecNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TestSection> getMatchingOrderedPage(Map<String, Object> propertyValues, List<String> orderProperties,
			boolean descending, int startingRecNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String insert(TestSection baseObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> insertAll(List<TestSection> baseObjects) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TestSection save(TestSection baseObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TestSection> saveAll(List<TestSection> baseObjects) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TestSection update(TestSection baseObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TestSection> updateAll(List<TestSection> baseObjects) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(TestSection baseObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id, String sysUserId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(List<TestSection> baseObjects) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(List<String> ids, String sysUserId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer getCount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getCountMatching(String propertyName, Object propertyValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getCountMatching(Map<String, Object> propertyValues) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getCountLike(String propertyName, String propertyValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getCountLike(Map<String, String> propertyValues) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TestSection getNext(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TestSection getPrevious(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasNext(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasPrevious(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Object> getAllUserTestSectionsByName(HttpServletRequest request, String testSectionName)
			throws LIMSRuntimeException {
		TestSectionDAO testSectionDAO = new TestSectionDAOImpl();
		List<Object> list = new ArrayList<>();

		try {
			if (SystemConfiguration.getInstance().getEnableUserTestSection().equals(IActionConstants.NO)) {
				list = testSectionDAO.getTestSections(testSectionName);
			} else {
				UserSessionData usd = (UserSessionData) request.getSession().getAttribute(IActionConstants.USER_SESSION_DATA);
				// bugzilla 2160
				UserModuleService userModuleService = new UserModuleServiceImpl();
				if (!userModuleService.isUserAdmin(request)) {
					list = testSectionDAO.getTestSectionsBySysUserId(testSectionName, usd.getSystemUserId());
				} else {
					list = testSectionDAO.getTestSections(testSectionName);
				}
			}
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("UserTestSectionDAOImpl", "getAllUserTestSectionsByName()", e.toString());
			throw new LIMSRuntimeException("Error in UserTestSectionDAOImpl getAllUserTestSectionsByName()", e);
		}
		return list;
	}

	@Override
	public List getPageOfTestsBySysUserId(HttpServletRequest request, int startingRecNo, String doingSearch,
			String searchStr) throws LIMSRuntimeException {

		List list = new ArrayList();
		TestDAO testDAO = new TestDAOImpl();

		try {
			if (SystemConfiguration.getInstance().getEnableUserTestSection().equals(IActionConstants.NO)) {

				if (!StringUtil.isNullorNill(doingSearch) && doingSearch.equals(IActionConstants.YES)) {
					list = testDAO.getPageOfSearchedTests(startingRecNo, searchStr);
				} else {
					list = testDAO.getPageOfTests(startingRecNo);
				}
			} else {
				UserSessionData usd = (UserSessionData) request.getSession().getAttribute(IActionConstants.USER_SESSION_DATA);
				// bugzilla 2160
				UserModuleService userModuleService = new UserModuleServiceImpl();
				if (!userModuleService.isUserAdmin(request)) {
					if (!StringUtil.isNullorNill(doingSearch) && doingSearch.equals(IActionConstants.YES)) {

						list = testDAO.getPageOfSearchedTestsBySysUserId(startingRecNo, usd.getSystemUserId(),
								searchStr);
					} else {
						list = testDAO.getPageOfTestsBySysUserId(startingRecNo, usd.getSystemUserId());
					}
				} else {

					if (!StringUtil.isNullorNill(searchStr)) {
						list = testDAO.getPageOfSearchedTests(startingRecNo, searchStr);
					} else {
						list = testDAO.getPageOfTests(startingRecNo);
					}

				}
				// end if bugzilla 2371
			}
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("UserTestSectionDAOImpl", "getPageOfTestsBySysUserId()", e.toString());
			throw new LIMSRuntimeException("Error in UserTestSectionDAOImpl getPageOfTestsBySysUserId()", e);
		}
		return list;
	}


	@Override
	public List getAllUserTestSections(HttpServletRequest request) throws LIMSRuntimeException {

		List list = new ArrayList();
		TestSectionDAO testSectDAO = new TestSectionDAOImpl();

		try {
			if (SystemConfiguration.getInstance().getEnableUserTestSection().equals(IActionConstants.NO)) {
				list = testSectDAO.getAllTestSections();
			} else {
				UserSessionData usd = (UserSessionData) request.getSession().getAttribute(IActionConstants.USER_SESSION_DATA);
				// bugzilla 2160
				UserModuleService userModuleService = new UserModuleServiceImpl();
				if (!userModuleService.isUserAdmin(request)) {
					list = testSectDAO.getAllTestSectionsBySysUserId(usd.getSystemUserId());
				} else {
					list = testSectDAO.getAllTestSections();
				}
			}
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("UserTestSectionDAOImpl", "getAllUserTestSections()", e.toString());
			throw new LIMSRuntimeException("Error in UserTestSectionDAOImpl getAllUserTestSections()", e);
		}
		return list;
	}
	
	@Override
	public List getAllUserTests(HttpServletRequest request, boolean onlyTestsFullySetup) throws LIMSRuntimeException {
		List list = new ArrayList();
		TestDAO testDAO = new TestDAOImpl();

		try {
			if (SystemConfiguration.getInstance().getEnableUserTestSection().equals(IActionConstants.NO)) {
				list = testDAO.getAllTests(onlyTestsFullySetup);
			} else {
				UserSessionData usd = (UserSessionData) request.getSession().getAttribute(IActionConstants.USER_SESSION_DATA);
				// bugzilla 2160
				UserModuleService userModuleService = new UserModuleServiceImpl();
				if (!userModuleService.isUserAdmin(request)) {
					list = testDAO.getAllTestsBySysUserId(usd.getSystemUserId(), onlyTestsFullySetup);
				} else {
					list = testDAO.getAllTests(onlyTestsFullySetup);
				}
			}
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("UserTestSectionDAOImpl", "getAllUserTests()", e.toString());
			throw new LIMSRuntimeException("Error in UserTestSectionDAOImpl getAllUserTests()", e);
		}
		return list;
	}

	@Override
	public List getSampleTestAnalytes(HttpServletRequest request, List sample_Tas, List testSections)
			throws LIMSRuntimeException {

		try {
			if (SystemConfiguration.getInstance().getEnableUserTestSection().equals(IActionConstants.NO)) {
				return sample_Tas;
			} else {
				// bugzilla 2160
				UserModuleService userModuleService = new UserModuleServiceImpl();
				if (!userModuleService.isUserAdmin(request)) {
					for (int i = 0; i < sample_Tas.size(); i++) {
						Sample_TestAnalyte sample_ta = (Sample_TestAnalyte) sample_Tas.get(i);
						Test_TestAnalyte test = sample_ta.getTestTestAnalyte();
						for (int j = 0; j < testSections.size(); j++) {
							TestSection testSection = (TestSection) testSections.get(j);
							if (!test.getTest().getTestSection().getId().equals(testSection.getId())) {
								if (sample_Tas.size() > 0) {
									sample_Tas.remove(i);
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("UserTestSectionDAOImpl", "getSampleTestAnalytes()", e.toString());
			throw new LIMSRuntimeException("Error in UserTestSectionDAOImpl getSampleTestAnalytes()", e);
		}
		return sample_Tas;
	}

	@Override
	public List getSamplePdfList(HttpServletRequest request, Locale locale, String sampStatus, String humanDomain)
			throws LIMSRuntimeException {

		List samplePdfList = new java.util.ArrayList();
		SampleDAO sampleDAO = new SampleDAOImpl();

		try {
			List statuses = new ArrayList();
			statuses.add(sampStatus);
			// bugzilla 2437: made getSamplesByStatusAndDomain more generic to take in list
			// of statuses of samples to retrieve
			List sampleList = sampleDAO.getSamplesByStatusAndDomain(statuses, humanDomain);
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
			LogEvent.logError("UserTestSectionDAOImpl", "getSamplePdfList()", e.toString());
			throw new LIMSRuntimeException("Error in UserTestSectionDAOImpl getSamplePdfList()", e);
		}
		return samplePdfList;
	}
	
	@Override
	public List getAnalyses(HttpServletRequest request, List analyses, List testSections) throws LIMSRuntimeException {

		List newAnalyses = new ArrayList();
		try {
			if (SystemConfiguration.getInstance().getEnableUserTestSection().equals(IActionConstants.NO)) {
				return newAnalyses;
			} else {
				UserModuleService userModuleService = new UserModuleServiceImpl();
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
			LogEvent.logError("UserTestSectionDAOImpl", "getAnalyses()", e.toString());
			throw new LIMSRuntimeException("Error in UserTestSectionDAOImpl getAnalyses()", e);
		}
		return newAnalyses;

	}



}
