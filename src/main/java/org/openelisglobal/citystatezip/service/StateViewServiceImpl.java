package org.openelisglobal.citystatezip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.citystatezip.dao.StateViewDAO;
import org.openelisglobal.citystatezip.valueholder.StateView;

@Service
public class StateViewServiceImpl extends BaseObjectServiceImpl<StateView, String> implements StateViewService {
	@Autowired
	protected StateViewDAO baseObjectDAO;

	StateViewServiceImpl() {
		super(StateView.class);
	}

	@Override
	protected StateViewDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}
}
