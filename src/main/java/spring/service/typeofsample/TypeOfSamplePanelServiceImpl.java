package spring.service.typeofsample;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.typeofsample.dao.TypeOfSamplePanelDAO;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSamplePanel;

@Service
public class TypeOfSamplePanelServiceImpl extends BaseObjectServiceImpl<TypeOfSamplePanel, String> implements TypeOfSamplePanelService {
	@Autowired
	protected TypeOfSamplePanelDAO baseObjectDAO;

	TypeOfSamplePanelServiceImpl() {
		super(TypeOfSamplePanel.class);
	}

	@Override
	protected TypeOfSamplePanelDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public void getData(TypeOfSamplePanel typeOfSamplePanel) {
        getBaseObjectDAO().getData(typeOfSamplePanel);

	}

	@Override
	public void deleteData(String[] typeOfSamplePanelIds, String currentUserId) {
        getBaseObjectDAO().deleteData(typeOfSamplePanelIds,currentUserId);

	}

	@Override
	public boolean insertData(TypeOfSamplePanel typeOfSamplePanel) {
        return getBaseObjectDAO().insertData(typeOfSamplePanel);
	}

	@Override
	public List getAllTypeOfSamplePanels() {
        return getBaseObjectDAO().getAllTypeOfSamplePanels();
	}

	@Override
	public List getPageOfTypeOfSamplePanel(int startingRecNo) {
        return getBaseObjectDAO().getPageOfTypeOfSamplePanel(startingRecNo);
	}

	@Override
	public List getNextTypeOfSamplePanelRecord(String id) {
        return getBaseObjectDAO().getNextTypeOfSamplePanelRecord(id);
	}

	@Override
	public Integer getTotalTypeOfSamplePanelCount() {
        return getBaseObjectDAO().getTotalTypeOfSamplePanelCount();
	}

	@Override
	public List<TypeOfSamplePanel> getTypeOfSamplePanelsForPanel(String panelId) {
        return getBaseObjectDAO().getTypeOfSamplePanelsForPanel(panelId);
	}

	@Override
	public List getPreviousTypeOfSamplePanelRecord(String id) {
        return getBaseObjectDAO().getPreviousTypeOfSamplePanelRecord(id);
	}

	@Override
	public List<TypeOfSamplePanel> getTypeOfSamplePanelsForSampleType(String sampleType) {
        return getBaseObjectDAO().getTypeOfSamplePanelsForSampleType(sampleType);
	}
}
