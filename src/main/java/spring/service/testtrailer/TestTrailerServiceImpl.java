package spring.service.testtrailer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.common.exception.LIMSDuplicateRecordException;
import us.mn.state.health.lims.testtrailer.dao.TestTrailerDAO;
import us.mn.state.health.lims.testtrailer.valueholder.TestTrailer;

@Service
public class TestTrailerServiceImpl extends BaseObjectServiceImpl<TestTrailer, String> implements TestTrailerService {
	@Autowired
	protected TestTrailerDAO baseObjectDAO;

	TestTrailerServiceImpl() {
		super(TestTrailer.class);
	}

	@Override
	protected TestTrailerDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public void getData(TestTrailer testTrailer) {
		getBaseObjectDAO().getData(testTrailer);

	}

	@Override
	public List getPageOfTestTrailers(int startingRecNo) {
		return getBaseObjectDAO().getPageOfTestTrailers(startingRecNo);
	}

	@Override
	public List getNextTestTrailerRecord(String id) {
		return getBaseObjectDAO().getNextTestTrailerRecord(id);
	}

	@Override
	public Integer getTotalTestTrailerCount() {
		return getBaseObjectDAO().getTotalTestTrailerCount();
	}

	@Override
	public TestTrailer getTestTrailerByName(TestTrailer testTrailer) {
		return getBaseObjectDAO().getTestTrailerByName(testTrailer);
	}

	@Override
	public List getPreviousTestTrailerRecord(String id) {
		return getBaseObjectDAO().getPreviousTestTrailerRecord(id);
	}

	@Override
	public List getAllTestTrailers() {
		return getBaseObjectDAO().getAllTestTrailers();
	}

	@Override
	public List getTestTrailers(String filter) {
		return getBaseObjectDAO().getTestTrailers(filter);
	}

	@Override
	public String insert(TestTrailer testTrailer) {
		if (baseObjectDAO.duplicateTestTrailerExists(testTrailer)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + testTrailer.getTestTrailerName());
		}
		return super.insert(testTrailer);
	}

	@Override
	public TestTrailer save(TestTrailer testTrailer) {
		if (baseObjectDAO.duplicateTestTrailerExists(testTrailer)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + testTrailer.getTestTrailerName());
		}
		return super.save(testTrailer);
	}

	@Override
	public TestTrailer update(TestTrailer testTrailer) {
		if (baseObjectDAO.duplicateTestTrailerExists(testTrailer)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + testTrailer.getTestTrailerName());
		}
		return super.update(testTrailer);
	}
}
