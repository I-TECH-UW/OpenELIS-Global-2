package spring.service.sampleitem;

import java.lang.Integer;
import java.lang.String;
import java.util.List;
import java.util.Set;
import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;

public interface SampleItemService extends BaseObjectService<SampleItem> {
	void getData(SampleItem sampleItem);

	SampleItem getData(String sampleItemId);

	void deleteData(List<SampleItem> sampleItems);

	void updateData(SampleItem sampleItem);

	boolean insertData(SampleItem sampleItem);

	List<SampleItem> getSampleItemsBySampleIdAndType(String sampleId, TypeOfSample typeOfSample);

	List<SampleItem> getPageOfSampleItems(int startingRecNo);

	List<SampleItem> getPreviousSampleItemRecord(String id);

	List<SampleItem> getAllSampleItems();

	List<SampleItem> getSampleItemsBySampleId(String id);

	List<SampleItem> getNextSampleItemRecord(String id);

	List<SampleItem> getSampleItemsBySampleIdAndStatus(String id, Set<Integer> includedStatusList);

	void getDataBySample(SampleItem sampleItem);
}
