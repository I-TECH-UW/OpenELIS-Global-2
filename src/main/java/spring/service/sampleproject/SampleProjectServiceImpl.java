package spring.service.sampleproject;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.sampleproject.dao.SampleProjectDAO;
import us.mn.state.health.lims.sampleproject.valueholder.SampleProject;

@Service
public class SampleProjectServiceImpl extends BaseObjectServiceImpl<SampleProject, String> implements SampleProjectService {
	@Autowired
	protected SampleProjectDAO baseObjectDAO;

	SampleProjectServiceImpl() {
		super(SampleProject.class);
	}

	@Override
	protected SampleProjectDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional
	public SampleProject getSampleProjectBySampleId(String id) {
		return getMatch("sample.id", id).orElse(null);
	}

	@Override
	public void getData(SampleProject sampleProj) {
		getBaseObjectDAO().getData(sampleProj);

	}

	@Override
	public void deleteData(List sampleProjs) {
		getBaseObjectDAO().deleteData(sampleProjs);

	}

	@Override
	public void updateData(SampleProject sampleProj) {
		getBaseObjectDAO().updateData(sampleProj);

	}

	@Override
	public boolean insertData(SampleProject sampleProj) {
		return getBaseObjectDAO().insertData(sampleProj);
	}

	@Override
	public List getSampleProjectsByProjId(String projId) {
		return getBaseObjectDAO().getSampleProjectsByProjId(projId);
	}

	@Override
	public List<SampleProject> getByOrganizationProjectAndReceivedOnRange(String organizationId, String projectName,
			Date lowDate, Date highDate) {
		return getBaseObjectDAO().getByOrganizationProjectAndReceivedOnRange(organizationId, projectName, lowDate,
				highDate);
	}
}
