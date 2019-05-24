package spring.service.sampleorganization;

import java.util.List;
import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleorganization.valueholder.SampleOrganization;

public interface SampleOrganizationService extends BaseObjectService<SampleOrganization> {
	void getData(SampleOrganization sampleOrg);

	void deleteData(List sampleOrgs);

	void updateData(SampleOrganization sampleOrg);

	boolean insertData(SampleOrganization sampleOrg);

	void getDataBySample(SampleOrganization sampleOrg);

	SampleOrganization getDataBySample(Sample sample);
}
