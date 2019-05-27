package spring.service.statusofsample;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.statusofsample.dao.StatusOfSampleDAO;
import us.mn.state.health.lims.statusofsample.valueholder.StatusOfSample;

@Service
public class StatusOfSampleServiceImpl extends BaseObjectServiceImpl<StatusOfSample> implements StatusOfSampleService {
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
	public void updateData(StatusOfSample sourceOfSample) {
        getBaseObjectDAO().updateData(sourceOfSample);

	}

	@Override
	public boolean insertData(StatusOfSample sourceOfSample) {
        return getBaseObjectDAO().insertData(sourceOfSample);
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
}
