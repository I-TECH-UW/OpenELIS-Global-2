package spring.service.typeofsample;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSamplePanel;

public interface TypeOfSamplePanelService extends BaseObjectService<TypeOfSamplePanel> {

	void insertData(TypeOfSamplePanel typeOfSamplePanel);
}
