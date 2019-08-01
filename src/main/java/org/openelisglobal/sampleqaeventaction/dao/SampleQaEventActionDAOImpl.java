package org.openelisglobal.sampleqaeventaction.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.sampleqaeventaction.valueholder.SampleQaEventAction;

@Component
@Transactional
public class SampleQaEventActionDAOImpl extends BaseDAOImpl<SampleQaEventAction, String>
        implements SampleQaEventActionDAO {
    SampleQaEventActionDAOImpl() {
        super(SampleQaEventAction.class);
    }
}
