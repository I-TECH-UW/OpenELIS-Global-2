package org.openelisglobal.typeofsample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.typeofsample.dao.TypeOfSamplePanelDAO;
import org.openelisglobal.typeofsample.valueholder.TypeOfSamplePanel;

@Service
public class TypeOfSamplePanelServiceImpl extends BaseObjectServiceImpl<TypeOfSamplePanel, String>
		implements TypeOfSamplePanelService {
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
	@Transactional(readOnly = true)
	public void getData(TypeOfSamplePanel typeOfSamplePanel) {
		getBaseObjectDAO().getData(typeOfSamplePanel);

	}

	@Override
	@Transactional(readOnly = true)
	public List getAllTypeOfSamplePanels() {
		return getBaseObjectDAO().getAllTypeOfSamplePanels();
	}

	@Override
	@Transactional(readOnly = true)
	public List getPageOfTypeOfSamplePanel(int startingRecNo) {
		return getBaseObjectDAO().getPageOfTypeOfSamplePanel(startingRecNo);
	}

	@Override
	@Transactional(readOnly = true)
	public List getNextTypeOfSamplePanelRecord(String id) {
		return getBaseObjectDAO().getNextTypeOfSamplePanelRecord(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Integer getTotalTypeOfSamplePanelCount() {
		return getBaseObjectDAO().getTotalTypeOfSamplePanelCount();
	}

	@Override
	@Transactional(readOnly = true)
	public List<TypeOfSamplePanel> getTypeOfSamplePanelsForPanel(String panelId) {
		return getBaseObjectDAO().getTypeOfSamplePanelsForPanel(panelId);
	}

	@Override
	@Transactional(readOnly = true)
	public List getPreviousTypeOfSamplePanelRecord(String id) {
		return getBaseObjectDAO().getPreviousTypeOfSamplePanelRecord(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TypeOfSamplePanel> getTypeOfSamplePanelsForSampleType(String sampleType) {
		return getBaseObjectDAO().getTypeOfSamplePanelsForSampleType(sampleType);
	}
}
