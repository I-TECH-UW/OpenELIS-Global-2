package org.openelisglobal.sourceofsample.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import  org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.sourceofsample.valueholder.SourceOfSample;

@Component
@Transactional 
public class SourceOfSampleDAOImpl extends BaseDAOImpl<SourceOfSample, String> implements SourceOfSampleDAO {
  SourceOfSampleDAOImpl() {
    super(SourceOfSample.class);
  }
}
