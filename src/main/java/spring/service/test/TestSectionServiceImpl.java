package spring.service.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.test.dao.TestSectionDAO;
import us.mn.state.health.lims.test.valueholder.TestSection;

@Service
public class TestSectionServiceImpl extends BaseObjectServiceImpl<TestSection> implements TestSectionService {
	@Autowired
	protected TestSectionDAO baseObjectDAO;

	TestSectionServiceImpl() {
		super(TestSection.class);
	}

	@Override
	protected TestSectionDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional
	public List<TestSection> getAllActiveTestSections() {
		return baseObjectDAO.getAllMatchingOrdered("isActive", "Y", "sortOrderInt", false);
	}
}
