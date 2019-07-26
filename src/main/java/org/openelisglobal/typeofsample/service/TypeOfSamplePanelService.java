package org.openelisglobal.typeofsample.service;

import java.util.List;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.typeofsample.valueholder.TypeOfSamplePanel;

public interface TypeOfSamplePanelService extends BaseObjectService<TypeOfSamplePanel, String> {
	void getData(TypeOfSamplePanel typeOfSamplePanel);

	List getAllTypeOfSamplePanels();

	List getPageOfTypeOfSamplePanel(int startingRecNo);

	List getNextTypeOfSamplePanelRecord(String id);

	Integer getTotalTypeOfSamplePanelCount();

	List<TypeOfSamplePanel> getTypeOfSamplePanelsForPanel(String panelId);

	List getPreviousTypeOfSamplePanelRecord(String id);

	List<TypeOfSamplePanel> getTypeOfSamplePanelsForSampleType(String sampleType);
}
