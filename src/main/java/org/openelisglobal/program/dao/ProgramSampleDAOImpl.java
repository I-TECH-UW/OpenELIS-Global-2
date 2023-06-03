package org.openelisglobal.program.dao;

import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.program.valueholder.ProgramSample;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ProgramSampleDAOImpl extends BaseDAOImpl<ProgramSample, Integer> implements ProgramSampleDAO {
    ProgramSampleDAOImpl() {
        super(ProgramSample.class);
    }
}
