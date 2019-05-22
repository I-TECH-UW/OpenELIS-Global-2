package spring.service.sampleproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.sampleproject.dao.SampleProjectDAO;
import us.mn.state.health.lims.sampleproject.valueholder.SampleProject;

@Service
public class SampleProjectServiceImpl extends BaseObjectServiceImpl<SampleProject> implements SampleProjectService {
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
		return getMatch("sample.id", id).get();
	}
}
