package org.openelisglobal.program.service;

import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.program.dao.ProgramSampleDAO;
import org.openelisglobal.program.valueholder.ProgramSample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgramSampleServiceImpl extends BaseObjectServiceImpl<ProgramSample, Integer>
        implements ProgramSampleService {
    @Autowired
    protected ProgramSampleDAO baseObjectDAO;

    ProgramSampleServiceImpl() {
        super(ProgramSample.class);
    }

    @Override
    protected ProgramSampleDAO getBaseObjectDAO() {
        return baseObjectDAO;
    }

    @Override
    public ProgramSample getProgrammeSampleBySample(Integer sampleId ,String programName) {
        return getBaseObjectDAO().getProgrammeSampleBySample(sampleId ,programName);
    }
}
