package spring.service.sampleitem;

import java.util.List;
import java.util.Set;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;

public interface SampleItemService extends BaseObjectService<SampleItem> {

	List<SampleItem> getSampleItemsBySampleId(String id);

	List<SampleItem> getSampleItemsBySampleIdAndStatus(String id, Set<Integer> enteredStatusSampleList);
}
