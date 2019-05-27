package spring.service.program;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.program.dao.ProgramDAO;
import us.mn.state.health.lims.program.valueholder.Program;

@Service
public class ProgramServiceImpl extends BaseObjectServiceImpl<Program> implements ProgramService {
	@Autowired
	protected ProgramDAO baseObjectDAO;

	ProgramServiceImpl() {
		super(Program.class);
	}

	@Override
	protected ProgramDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}
}
