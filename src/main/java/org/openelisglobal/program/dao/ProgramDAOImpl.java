package org.openelisglobal.program.dao;

import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.program.valueholder.Program;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ProgramDAOImpl extends BaseDAOImpl<Program, String> implements ProgramDAO {
  ProgramDAOImpl() {
    super(Program.class);
  }
}
