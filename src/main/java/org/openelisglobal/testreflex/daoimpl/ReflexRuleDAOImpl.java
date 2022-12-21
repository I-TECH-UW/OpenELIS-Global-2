package org.openelisglobal.testreflex.daoimpl;

import javax.transaction.Transactional;

import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.testreflex.action.bean.ReflexRule;
import org.openelisglobal.testreflex.dao.ReflexRuleDAO;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class ReflexRuleDAOImpl extends BaseDAOImpl<ReflexRule, Integer> implements ReflexRuleDAO{

    public ReflexRuleDAOImpl() {
        super(ReflexRule.class);
    } 
}
