package org.openelisglobal.qaevent.service;

import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.internationalization.MessageUtil;
import org.openelisglobal.qaevent.dao.NceTypeDAO;
import org.openelisglobal.qaevent.valueholder.NceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NceTypeServiceImpl extends BaseObjectServiceImpl<NceType, String> implements NceTypeService {

    @Autowired
    protected NceTypeDAO baseObjectDAO;

    NceTypeServiceImpl() {
        super(NceType.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List getAllNceTypes() {
        List<NceType> nceTypeList = baseObjectDAO.getAllNceType();
        for (NceType type: nceTypeList) {
            type.setName(MessageUtil.getMessage(type.getDisplayKey()));
        }
        return nceTypeList;
    }

    @Override
    protected NceTypeDAO getBaseObjectDAO() {
        return baseObjectDAO;
    }
}
