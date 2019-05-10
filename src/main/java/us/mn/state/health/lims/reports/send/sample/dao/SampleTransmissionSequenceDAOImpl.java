package us.mn.state.health.lims.reports.send.sample.dao;

import org.springframework.stereotype.Component;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.reports.send.sample.valueholder.SampleTransmissionSequence;

@Component
public class SampleTransmissionSequenceDAOImpl extends BaseDAOImpl<SampleTransmissionSequence> implements SampleTransmissionSequenceDAO {
  SampleTransmissionSequenceDAOImpl() {
    super(SampleTransmissionSequence.class);
  }
}
