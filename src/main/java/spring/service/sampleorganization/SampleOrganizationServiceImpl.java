package spring.service.sampleorganization;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleorganization.dao.SampleOrganizationDAO;
import us.mn.state.health.lims.sampleorganization.valueholder.SampleOrganization;

@Service
public class SampleOrganizationServiceImpl extends BaseObjectServiceImpl<SampleOrganization> implements SampleOrganizationService {
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
	public void getData(SampleOrganization sampleOrg) {
        getBaseObjectDAO().getData(sampleOrg);

	}

	@Override
	public void deleteData(List sampleOrgs) {
        getBaseObjectDAO().deleteData(sampleOrgs);

	}

	@Override
	public void updateData(SampleOrganization sampleOrg) {
        getBaseObjectDAO().updateData(sampleOrg);

	}

	@Override
	public boolean insertData(SampleOrganization sampleOrg) {
        return getBaseObjectDAO().insertData(sampleOrg);
	}

	@Override
	public void getDataBySample(SampleOrganization sampleOrg) {
        getBaseObjectDAO().getDataBySample(sampleOrg);

	}

	@Override
	public SampleOrganization getDataBySample(Sample sample) {
        return getBaseObjectDAO().getDataBySample(sample);
	}
}
