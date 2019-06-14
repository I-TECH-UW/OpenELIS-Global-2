package spring.service.sampleorganization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleorganization.dao.SampleOrganizationDAO;
import us.mn.state.health.lims.sampleorganization.valueholder.SampleOrganization;

@Service
public class SampleOrganizationServiceImpl extends BaseObjectServiceImpl<SampleOrganization, String>
		implements SampleOrganizationService {
	@Autowired
	protected SampleOrganizationDAO baseObjectDAO;

	SampleOrganizationServiceImpl() {
		super(SampleOrganization.class);
	}

	@Override
	protected SampleOrganizationDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional(readOnly = true)
	public void getData(SampleOrganization sampleOrg) {
		getBaseObjectDAO().getData(sampleOrg);

	}

	@Override
	@Transactional(readOnly = true)
	public void getDataBySample(SampleOrganization sampleOrg) {
		getBaseObjectDAO().getDataBySample(sampleOrg);

	}

	@Override
	@Transactional(readOnly = true)
	public SampleOrganization getDataBySample(Sample sample) {
		return getBaseObjectDAO().getDataBySample(sample);
	}
}
