package org.openelisglobal.testcalculated.daoimpl;

import javax.transaction.Transactional;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.testcalculated.dao.TestCalculationDAO;
import org.openelisglobal.testcalculated.valueholder.Calculation;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class TestCalculationDAOImpl extends BaseDAOImpl<Calculation, Integer> implements TestCalculationDAO {

    public TestCalculationDAOImpl() {
        super(Calculation.class);
    }
}
