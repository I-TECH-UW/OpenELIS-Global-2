package spring.service.sampleitem;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.sampleitem.dao.SampleItemDAO;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;

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
}
