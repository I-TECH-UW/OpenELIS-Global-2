package spring.service.sampleitem;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.sampleitem.dao.SampleItemDAO;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;

@Service
public class SampleItemServiceImpl extends BaseObjectServiceImpl<SampleItem> implements SampleItemService {
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
	@Transactional
	public List<SampleItem> getSampleItemsBySampleId(String id) {
		return baseObjectDAO.getAllMatching("sample.id", id);
	}

	@Override
	@Transactional
	public List<SampleItem> getSampleItemsBySampleIdAndStatus(String id, Set<Integer> enteredStatusSampleList) {
		return baseObjectDAO.getSampleItemsBySampleIdAndStatus(id, enteredStatusSampleList);
	}

	@Override
	public void getData(SampleItem sampleItem) {
        getBaseObjectDAO().getData(sampleItem);

	}

	@Override
	public SampleItem getData(String sampleItemId) {
        return getBaseObjectDAO().getData(sampleItemId);
	}

	@Override
	public void deleteData(List<SampleItem> sampleItems) {
        getBaseObjectDAO().deleteData(sampleItems);

	}

	@Override
	public void updateData(SampleItem sampleItem) {
        getBaseObjectDAO().updateData(sampleItem);

	}

	@Override
	public boolean insertData(SampleItem sampleItem) {
        return getBaseObjectDAO().insertData(sampleItem);
	}

	@Override
	public List<SampleItem> getSampleItemsBySampleIdAndType(String sampleId, TypeOfSample typeOfSample) {
        return getBaseObjectDAO().getSampleItemsBySampleIdAndType(sampleId,typeOfSample);
	}

	@Override
	public List<SampleItem> getPageOfSampleItems(int startingRecNo) {
        return getBaseObjectDAO().getPageOfSampleItems(startingRecNo);
	}

	@Override
	public List<SampleItem> getPreviousSampleItemRecord(String id) {
        return getBaseObjectDAO().getPreviousSampleItemRecord(id);
	}

	@Override
	public List<SampleItem> getAllSampleItems() {
        return getBaseObjectDAO().getAllSampleItems();
	}

	@Override
	public List<SampleItem> getNextSampleItemRecord(String id) {
        return getBaseObjectDAO().getNextSampleItemRecord(id);
	}

	@Override
	public void getDataBySample(SampleItem sampleItem) {
        getBaseObjectDAO().getDataBySample(sampleItem);

	}
}
