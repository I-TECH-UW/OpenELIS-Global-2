package org.openelisglobal.county.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.county.valueholder.County;

@Component
@Transactional
public class CountyDAOImpl extends BaseDAOImpl<County, String> implements CountyDAO {
    CountyDAOImpl() {
        super(County.class);
    }
}
