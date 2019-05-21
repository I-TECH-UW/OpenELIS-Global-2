package spring.service.typeofsample;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.typeofsample.dao.TypeOfSampleTestDAO;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSampleTest;

@Service
public class TypeOfSampleTestServiceImpl extends BaseObjectServiceImpl<TypeOfSampleTest>
		implements TypeOfSampleTestService {
	@Autowired
	protected TypeOfSampleTestDAO baseObjectDAO;

	TypeOfSampleTestServiceImpl() {
		super(TypeOfSampleTest.class);
	}

	@Override
	protected TypeOfSampleTestDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional
	public TypeOfSampleTest getTypeOfSampleTestForTest(String testId) {
		return getMatch("testId", testId).get();
	}

	@Override
	@Transactional
	public List<TypeOfSampleTest> getTypeOfSampleTestsForTest(String id) {
		return baseObjectDAO.getAllMatching("testId", id);
	}

	@Override
	@Transactional
	public List<TypeOfSampleTest> getTypeOfSampleTestsForSampleType(String id) {
		return baseObjectDAO.getAllMatching("typeOfSampleId", id);
	}
}
