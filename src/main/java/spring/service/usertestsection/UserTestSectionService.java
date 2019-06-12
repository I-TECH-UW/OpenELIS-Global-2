package spring.service.usertestsection;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.test.valueholder.TestSection;

public interface UserTestSectionService  extends BaseObjectService<TestSection, String> {
	public List<Object> getAllUserTestSectionsByName(HttpServletRequest request, String testSectionName)
			throws LIMSRuntimeException;

	// bugzilla 2371
	public List<Object> getPageOfTestsBySysUserId(HttpServletRequest request, int startingRecNo, String doingSearch,
			String searchStr) throws LIMSRuntimeException;

	public List<Object> getAllUserTestSections(HttpServletRequest request) throws LIMSRuntimeException;

	// bugzilla 2291 (added boolean onlyTestsFullySetup)
	public List<Object> getAllUserTests(HttpServletRequest request, boolean onlyTestsFullySetup)
			throws LIMSRuntimeException;

	public List<Object> getSampleTestAnalytes(HttpServletRequest request, List<Object> sample_Tas,
			List<Object> testSections) throws LIMSRuntimeException;

	public List<Object> getSamplePdfList(HttpServletRequest request, Locale locale, String sampStatus,
			String humanDomain) throws LIMSRuntimeException;

	// bugzilla 2433
	public List<Object> getAnalyses(HttpServletRequest request, List<Object> analyses, List<Object> testSections)
			throws LIMSRuntimeException;
}
