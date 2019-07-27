package org.openelisglobal.sampletracking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.sampletracking.dao.SampleTrackingDAO;
import org.openelisglobal.sampletracking.valueholder.SampleTracking;

@Service
public class SampleTrackingServiceImpl extends BaseObjectServiceImpl<SampleTracking, String> implements SampleTrackingService {
	@Autowired
	protected SampleTrackingDAO baseObjectDAO;

	SampleTrackingServiceImpl() {
		super(SampleTracking.class);
	}

	@Override
	protected SampleTrackingDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}
}
