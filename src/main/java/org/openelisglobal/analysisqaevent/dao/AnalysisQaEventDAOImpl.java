package org.openelisglobal.analysisqaevent.dao;

import org.openelisglobal.analysisqaevent.valueholder.AnalysisQaEvent;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class AnalysisQaEventDAOImpl extends BaseDAOImpl<AnalysisQaEvent, String> implements AnalysisQaEventDAO {
    AnalysisQaEventDAOImpl() {
        super(AnalysisQaEvent.class);
    }
}
