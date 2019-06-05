package us.mn.state.health.lims.program.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.program.valueholder.Program;

@Component
@Transactional 
public class ProgramDAOImpl extends BaseDAOImpl<Program, String> implements ProgramDAO {
  ProgramDAOImpl() {
    super(Program.class);
  }
}
