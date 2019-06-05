package spring.service.result;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.result.dao.ResultInventoryDAO;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.result.valueholder.ResultInventory;

@Service
public class ResultInventoryServiceImpl extends BaseObjectServiceImpl<ResultInventory, String> implements ResultInventoryService {
	@Autowired
	protected ResultInventoryDAO baseObjectDAO;

	ResultInventoryServiceImpl() {
		super(ResultInventory.class);
	}

	@Override
	protected ResultInventoryDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public void getData(ResultInventory resultInventory) {
        getBaseObjectDAO().getData(resultInventory);

	}

	@Override
	public void deleteData(List results) {
        getBaseObjectDAO().deleteData(results);

	}

	@Override
	public void updateData(ResultInventory resultInventory) {
        getBaseObjectDAO().updateData(resultInventory);

	}

	@Override
	public boolean insertData(ResultInventory resultInventory) {
        return getBaseObjectDAO().insertData(resultInventory);
	}

	@Override
	public ResultInventory getResultInventoryById(ResultInventory resultInventory) {
        return getBaseObjectDAO().getResultInventoryById(resultInventory);
	}

	@Override
	public List getAllResultInventoryss() {
        return getBaseObjectDAO().getAllResultInventoryss();
	}

	@Override
	public List<ResultInventory> getResultInventorysByResult(Result result) {
        return getBaseObjectDAO().getResultInventorysByResult(result);
	}
}
