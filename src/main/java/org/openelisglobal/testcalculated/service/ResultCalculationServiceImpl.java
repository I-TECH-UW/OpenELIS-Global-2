package org.openelisglobal.testcalculated.service;

import org.openelisglobal.common.dao.BaseDAO;
import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.testcalculated.dao.ResultCalculationDAO;
import org.openelisglobal.testcalculated.valueholder.ResultCalculation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultCalculationServiceImpl extends BaseObjectServiceImpl<ResultCalculation, Integer> implements ResultCalculationService{
   @Autowired
   ResultCalculationDAO resultCalculationDAOdao;
   
    public ResultCalculationServiceImpl() {
        super(ResultCalculation.class);
    }

    @Override
    protected BaseDAO<ResultCalculation, Integer> getBaseObjectDAO() {
        return resultCalculationDAOdao;
    }
    
}
