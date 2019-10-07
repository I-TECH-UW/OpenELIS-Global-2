package org.openelisglobal.qaevent.service;

import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.qaevent.dao.NceSpecimenDAO;
import org.openelisglobal.qaevent.valueholder.NceSpecimen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NceSpecimenServiceImpl extends BaseObjectServiceImpl<NceSpecimen, String> implements NceSpecimenService {

    @Autowired
    protected NceSpecimenDAO baseObjectDAO;

    public NceSpecimenServiceImpl() {
        super(NceSpecimen.class);
    }
    @Override
    @Transactional(readOnly = true)
    public List getSpecimenByNceId(String nceId) {
        return baseObjectDAO.getSpecimenByNceId(nceId);
    }

    @Override
    protected NceSpecimenDAO getBaseObjectDAO() {
        return baseObjectDAO;
    }
}
