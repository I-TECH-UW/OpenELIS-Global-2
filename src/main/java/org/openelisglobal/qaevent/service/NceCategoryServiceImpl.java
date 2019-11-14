package org.openelisglobal.qaevent.service;

import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.internationalization.MessageUtil;
import org.openelisglobal.qaevent.dao.NceCategoryDAO;
import org.openelisglobal.qaevent.valueholder.NceCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NceCategoryServiceImpl extends BaseObjectServiceImpl<NceCategory, String> implements NceCategoryService {

    @Autowired
    protected NceCategoryDAO baseObjectDAO;

    NceCategoryServiceImpl() {
        super(NceCategory.class);
    }
    @Override
    @Transactional(readOnly = true)
    public List getAllNceCategories() {
        List<NceCategory> nceCategoryList = baseObjectDAO.getAllNceCategory();
        for (NceCategory category: nceCategoryList) {
            category.setName(MessageUtil.getMessage(category.getDisplayKey()));
        }
        return nceCategoryList;
    }

    @Override
    protected NceCategoryDAO getBaseObjectDAO() {
        return baseObjectDAO;
    }
}
