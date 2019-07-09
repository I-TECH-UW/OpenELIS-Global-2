package us.mn.state.health.lims.reports.send.sample.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import  us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.reports.send.sample.valueholder.SampleTransmissionSequence;

@Component
@Transactional 
public class SampleTransmissionSequenceDAOImpl extends BaseDAOImpl<SampleTransmissionSequence, String> implements SampleTransmissionSequenceDAO {
  SampleTransmissionSequenceDAOImpl() {
    super(SampleTransmissionSequence.class);
  }
}
