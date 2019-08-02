package org.openelisglobal.citystatezip.dao;

import org.openelisglobal.citystatezip.valueholder.CityView;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class CityViewDAOImpl extends BaseDAOImpl<CityView, String> implements CityViewDAO {
    CityViewDAOImpl() {
        super(CityView.class);
    }
}
