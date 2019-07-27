package org.openelisglobal.program.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import  org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.program.valueholder.Program;

@Component
@Transactional 
public class ProgramDAOImpl extends BaseDAOImpl<Program, String> implements ProgramDAO {
  ProgramDAOImpl() {
    super(Program.class);
  }
}
