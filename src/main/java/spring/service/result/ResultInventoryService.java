package spring.service.result;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.result.valueholder.ResultInventory;

public interface ResultInventoryService extends BaseObjectService<ResultInventory, String> {
	void getData(ResultInventory resultInventory);

	ResultInventory getResultInventoryById(ResultInventory resultInventory);

	List getAllResultInventoryss();

	List<ResultInventory> getResultInventorysByResult(Result result);
}
