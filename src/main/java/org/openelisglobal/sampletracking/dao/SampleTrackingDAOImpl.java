package org.openelisglobal.sampletracking.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.sampletracking.valueholder.SampleTracking;

@Component
@Transactional
public class SampleTrackingDAOImpl extends BaseDAOImpl<SampleTracking, String> implements SampleTrackingDAO {
    SampleTrackingDAOImpl() {
        super(SampleTracking.class);
    }
}
