package org.openelisglobal.citystatezip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.citystatezip.dao.CityViewDAO;
import org.openelisglobal.citystatezip.valueholder.CityView;

@Service
public class CityViewServiceImpl extends BaseObjectServiceImpl<CityView, String> implements CityViewService {
	@Autowired
	protected CityViewDAO baseObjectDAO;

	CityViewServiceImpl() {
		super(CityView.class);
	}

	@Override
	protected CityViewDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}
}
