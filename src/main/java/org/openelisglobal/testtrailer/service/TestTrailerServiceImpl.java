package org.openelisglobal.testtrailer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.common.exception.LIMSDuplicateRecordException;
import org.openelisglobal.testtrailer.dao.TestTrailerDAO;
import org.openelisglobal.testtrailer.valueholder.TestTrailer;

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
	@Transactional(readOnly = true)
	public void getData(TestTrailer testTrailer) {
		getBaseObjectDAO().getData(testTrailer);

	}

	@Override
	@Transactional(readOnly = true)
	public List getPageOfTestTrailers(int startingRecNo) {
		return getBaseObjectDAO().getPageOfTestTrailers(startingRecNo);
	}

	@Override
	@Transactional(readOnly = true)
	public List getNextTestTrailerRecord(String id) {
		return getBaseObjectDAO().getNextTestTrailerRecord(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Integer getTotalTestTrailerCount() {
		return getBaseObjectDAO().getTotalTestTrailerCount();
	}

	@Override
	@Transactional(readOnly = true)
	public TestTrailer getTestTrailerByName(TestTrailer testTrailer) {
		return getBaseObjectDAO().getTestTrailerByName(testTrailer);
	}

	@Override
	@Transactional(readOnly = true)
	public List getPreviousTestTrailerRecord(String id) {
		return getBaseObjectDAO().getPreviousTestTrailerRecord(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List getAllTestTrailers() {
		return getBaseObjectDAO().getAllTestTrailers();
	}

	@Override
	@Transactional(readOnly = true)
	public List getTestTrailers(String filter) {
		return getBaseObjectDAO().getTestTrailers(filter);
	}

	@Override
	public String insert(TestTrailer testTrailer) {
		if (duplicateTestTrailerExists(testTrailer)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + testTrailer.getTestTrailerName());
		}
		return super.insert(testTrailer);
	}

	@Override
	public TestTrailer save(TestTrailer testTrailer) {
		if (duplicateTestTrailerExists(testTrailer)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + testTrailer.getTestTrailerName());
		}
		return super.save(testTrailer);
	}

	@Override
	public TestTrailer update(TestTrailer testTrailer) {
		if (duplicateTestTrailerExists(testTrailer)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + testTrailer.getTestTrailerName());
		}
		return super.update(testTrailer);
	}

	private boolean duplicateTestTrailerExists(TestTrailer testTrailer) {
		return baseObjectDAO.duplicateTestTrailerExists(testTrailer);
	}
}
