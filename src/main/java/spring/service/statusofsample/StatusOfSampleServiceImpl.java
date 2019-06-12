package spring.service.statusofsample;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.common.exception.LIMSDuplicateRecordException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.statusofsample.dao.StatusOfSampleDAO;
import us.mn.state.health.lims.statusofsample.valueholder.StatusOfSample;

@Service
public class StatusOfSampleServiceImpl extends BaseObjectServiceImpl<StatusOfSample, String>
		implements StatusOfSampleService {
	@Autowired
	protected StatusOfSampleDAO baseObjectDAO;

	StatusOfSampleServiceImpl() {
		super(StatusOfSample.class);
	}

	@Override
	protected StatusOfSampleDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public void getData(StatusOfSample sourceOfSample) {
		getBaseObjectDAO().getData(sourceOfSample);

	}

	@Override
	public List getPreviousStatusOfSampleRecord(String id) {
		return getBaseObjectDAO().getPreviousStatusOfSampleRecord(id);
	}

	@Override
	public List getPageOfStatusOfSamples(int startingRecNo) {
		return getBaseObjectDAO().getPageOfStatusOfSamples(startingRecNo);
	}

	@Override
	public Integer getTotalStatusOfSampleCount() {
		return getBaseObjectDAO().getTotalStatusOfSampleCount();
	}

	@Override
	public StatusOfSample getDataByStatusTypeAndStatusCode(StatusOfSample statusofsample) {
		return getBaseObjectDAO().getDataByStatusTypeAndStatusCode(statusofsample);
	}

	@Override
	public List getAllStatusOfSamples() {
		return getBaseObjectDAO().getAllStatusOfSamples();
	}

	@Override
	public List getNextStatusOfSampleRecord(String id) {
		return getBaseObjectDAO().getNextStatusOfSampleRecord(id);
	}

	@Override
	public String insert(StatusOfSample statusOfSample) {
		if (baseObjectDAO.duplicateStatusOfSampleExists(statusOfSample)) {
			StringBuffer sb = new StringBuffer();
			sb.append("Duplicate record exists for Description: ");
			sb.append(statusOfSample.getDescription());
			sb.append(" Status Type: ");
			sb.append(statusOfSample.getStatusType());
			// bugzilla 2154
			LogEvent.logError("StatusOfSample", "insertData()", sb.toString());
			throw new LIMSDuplicateRecordException(sb.toString());
		}
		return super.insert(statusOfSample);
	}

	@Override
	public StatusOfSample save(StatusOfSample statusOfSample) {
		if (baseObjectDAO.duplicateStatusOfSampleExists(statusOfSample)) {
			StringBuffer sb = new StringBuffer();
			sb.append("Duplicate record exists for Description: ");
			sb.append(statusOfSample.getDescription());
			sb.append(" Status Type: ");
			sb.append(statusOfSample.getStatusType());
			// bugzilla 2154
			LogEvent.logError("StatusOfSample", "insertData()", sb.toString());
			throw new LIMSDuplicateRecordException(sb.toString());
		}
		return super.save(statusOfSample);
	}

	@Override
	public StatusOfSample update(StatusOfSample statusOfSample) {
		if (baseObjectDAO.duplicateStatusOfSampleExists(statusOfSample)) {
			StringBuffer sb = new StringBuffer();
			sb.append("Duplicate record exists for Description: ");
			sb.append(statusOfSample.getDescription());
			sb.append(" Status Type: ");
			sb.append(statusOfSample.getStatusType());
			// bugzilla 2154
			LogEvent.logError("StatusOfSample", "insertData()", sb.toString());
			throw new LIMSDuplicateRecordException(sb.toString());
		}
		return super.update(statusOfSample);
	}
}
