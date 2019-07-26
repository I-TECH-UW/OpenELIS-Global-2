package org.openelisglobal.action.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import org.openelisglobal.action.valueholder.Action;
import  org.openelisglobal.common.daoimpl.BaseDAOImpl;

@Component
@Transactional
public class ActionDAOImpl extends BaseDAOImpl<Action, String> implements ActionDAO {
	ActionDAOImpl() {
		super(Action.class);
	}
}
