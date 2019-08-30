package org.openelisglobal.sampleqaeventaction.service;

import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.sampleqaeventaction.dao.SampleQaEventActionDAO;
import org.openelisglobal.sampleqaeventaction.valueholder.SampleQaEventAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SampleQaEventActionServiceImpl extends BaseObjectServiceImpl<SampleQaEventAction, String>
        implements SampleQaEventActionService {
    @Autowired
    protected SampleQaEventActionDAO baseObjectDAO;

    SampleQaEventActionServiceImpl() {
        super(SampleQaEventAction.class);
    }

    @Override
    protected SampleQaEventActionDAO getBaseObjectDAO() {
        return baseObjectDAO;
    }
}
