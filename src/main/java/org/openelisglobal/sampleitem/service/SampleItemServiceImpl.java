package org.openelisglobal.sampleitem.service;

import java.util.List;
import java.util.Set;

import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.sampleitem.dao.SampleItemDAO;
import org.openelisglobal.sampleitem.valueholder.SampleItem;
import org.openelisglobal.typeofsample.valueholder.TypeOfSample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SampleItemServiceImpl extends BaseObjectServiceImpl<SampleItem, String> implements SampleItemService {
    @Autowired
    protected SampleItemDAO baseObjectDAO;

    SampleItemServiceImpl() {
        super(SampleItem.class);
    }

    @Override
    protected SampleItemDAO getBaseObjectDAO() {
        return baseObjectDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SampleItem> getSampleItemsBySampleId(String id) {
        return baseObjectDAO.getAllMatching("sample.id", id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SampleItem> getSampleItemsBySampleIdAndStatus(String id, Set<Integer> enteredStatusSampleList) {
        return baseObjectDAO.getSampleItemsBySampleIdAndStatus(id, enteredStatusSampleList);
    }

    @Override
    @Transactional(readOnly = true)
    public void getData(SampleItem sampleItem) {
        getBaseObjectDAO().getData(sampleItem);

    }

    @Override
    @Transactional(readOnly = true)
    public SampleItem getData(String sampleItemId) {
        return getBaseObjectDAO().getData(sampleItemId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SampleItem> getSampleItemsBySampleIdAndType(String sampleId, TypeOfSample typeOfSample) {
        return getBaseObjectDAO().getSampleItemsBySampleIdAndType(sampleId, typeOfSample);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SampleItem> getPageOfSampleItems(int startingRecNo) {
        return getBaseObjectDAO().getPageOfSampleItems(startingRecNo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SampleItem> getAllSampleItems() {
        return getBaseObjectDAO().getAllSampleItems();
    }

    @Override
    @Transactional(readOnly = true)
    public void getDataBySample(SampleItem sampleItem) {
        getBaseObjectDAO().getDataBySample(sampleItem);

    }

    @Override
    @Transactional(readOnly = true)
    public String getTypeOfSampleId(SampleItem sampleItem) {
        sampleItem = get(sampleItem.getId());
        return sampleItem.getTypeOfSampleId();
    }
}
