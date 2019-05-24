package spring.service.sampleproject;

import java.lang.String;
import java.sql.Date;
import java.util.List;
import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.sampleproject.valueholder.SampleProject;

public interface SampleProjectService extends BaseObjectService<SampleProject> {
	void getData(SampleProject sampleProj);

	void deleteData(List sampleProjs);

	void updateData(SampleProject sampleProj);

	boolean insertData(SampleProject sampleProj);

	List getSampleProjectsByProjId(String projId);

	SampleProject getSampleProjectBySampleId(String id);

	List<SampleProject> getByOrganizationProjectAndReceivedOnRange(String organizationId, String projectName,
			Date lowDate, Date highDate);
}
