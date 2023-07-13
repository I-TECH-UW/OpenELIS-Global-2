package org.openelisglobal.testcalculated.daoimpl;

import javax.transaction.Transactional;

import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.testcalculated.dao.ResultCalculationDAO;
import org.openelisglobal.testcalculated.valueholder.ResultCalculation;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class ResultCalculationDAOImpl extends BaseDAOImpl<ResultCalculation, Integer> implements ResultCalculationDAO{

    public ResultCalculationDAOImpl() {
         super(ResultCalculation.class);
    }
    
}
