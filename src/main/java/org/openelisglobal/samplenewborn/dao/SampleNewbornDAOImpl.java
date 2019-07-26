package org.openelisglobal.samplenewborn.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import  org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.samplenewborn.valueholder.SampleNewborn;

@Component
@Transactional 
public class SampleNewbornDAOImpl extends BaseDAOImpl<SampleNewborn, String> implements SampleNewbornDAO {
  SampleNewbornDAOImpl() {
    super(SampleNewborn.class);
  }
}
