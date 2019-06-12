package spring.service.sampleorganization;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleorganization.valueholder.SampleOrganization;

public interface SampleOrganizationService extends BaseObjectService<SampleOrganization, String> {
	void getData(SampleOrganization sampleOrg);

	void getDataBySample(SampleOrganization sampleOrg);

	SampleOrganization getDataBySample(Sample sample);
}
