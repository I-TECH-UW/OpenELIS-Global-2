package org.openelisglobal.common.dao;

import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.valueholder.EnumValueItemImpl;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class EnumValueItemImplDAOImpl extends BaseDAOImpl<EnumValueItemImpl, String> implements EnumValueItemImplDAO {
    EnumValueItemImplDAOImpl() {
        super(EnumValueItemImpl.class);
    }
}
