package org.openelisglobal.label.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import  org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.label.valueholder.Label;

@Component
@Transactional 
public class LabelDAOImpl extends BaseDAOImpl<Label, String> implements LabelDAO {
  LabelDAOImpl() {
    super(Label.class);
  }
}
