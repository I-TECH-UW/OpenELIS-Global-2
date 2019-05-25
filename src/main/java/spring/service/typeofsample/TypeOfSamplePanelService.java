package spring.service.typeofsample;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSamplePanel;

public interface TypeOfSamplePanelService extends BaseObjectService<TypeOfSamplePanel> {
	void getData(TypeOfSamplePanel typeOfSamplePanel);

	void deleteData(String[] typeOfSamplePanelIds, String currentUserId);

	boolean insertData(TypeOfSamplePanel typeOfSamplePanel);

	List getAllTypeOfSamplePanels();

	List getPageOfTypeOfSamplePanel(int startingRecNo);

	List getNextTypeOfSamplePanelRecord(String id);

	Integer getTotalTypeOfSamplePanelCount();

	List<TypeOfSamplePanel> getTypeOfSamplePanelsForPanel(String panelId);

	List getPreviousTypeOfSamplePanelRecord(String id);

	List<TypeOfSamplePanel> getTypeOfSamplePanelsForSampleType(String sampleType);
}
