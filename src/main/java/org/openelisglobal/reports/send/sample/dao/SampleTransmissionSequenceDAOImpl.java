package org.openelisglobal.reports.send.sample.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import  org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.reports.send.sample.valueholder.SampleTransmissionSequence;

@Component
@Transactional 
public class SampleTransmissionSequenceDAOImpl extends BaseDAOImpl<SampleTransmissionSequence, String> implements SampleTransmissionSequenceDAO {
  SampleTransmissionSequenceDAOImpl() {
    super(SampleTransmissionSequence.class);
  }
}
