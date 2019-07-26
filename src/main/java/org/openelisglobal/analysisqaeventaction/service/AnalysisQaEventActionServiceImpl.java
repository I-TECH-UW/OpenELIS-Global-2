package org.openelisglobal.analysisqaeventaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.analysisqaeventaction.dao.AnalysisQaEventActionDAO;
import org.openelisglobal.analysisqaeventaction.valueholder.AnalysisQaEventAction;

@Service
public class AnalysisQaEventActionServiceImpl extends BaseObjectServiceImpl<AnalysisQaEventAction, String> implements AnalysisQaEventActionService {
	@Autowired
	protected AnalysisQaEventActionDAO baseObjectDAO;

	AnalysisQaEventActionServiceImpl() {
		super(AnalysisQaEventAction.class);
	}

	@Override
	protected AnalysisQaEventActionDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}
}
