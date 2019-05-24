package spring.service.datasubmission;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.datasubmission.dao.TypeOfDataIndicatorDAO;
import us.mn.state.health.lims.datasubmission.valueholder.TypeOfDataIndicator;

@Service
public class TypeOfDataIndicatorServiceImpl extends BaseObjectServiceImpl<TypeOfDataIndicator> implements TypeOfDataIndicatorService {
	@Autowired
	protected TypeOfDataIndicatorDAO baseObjectDAO;

	TypeOfDataIndicatorServiceImpl() {
		super(TypeOfDataIndicator.class);
	}

	@Override
	protected TypeOfDataIndicatorDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public void getData(TypeOfDataIndicator typeOfIndicator) {
        getBaseObjectDAO().getData(typeOfIndicator);

	}

	@Override
	public void updateData(TypeOfDataIndicator typeOfIndicator) {
        getBaseObjectDAO().updateData(typeOfIndicator);

	}

	@Override
	public boolean insertData(TypeOfDataIndicator typeOfIndicator) {
        return getBaseObjectDAO().insertData(typeOfIndicator);
	}

	@Override
	public TypeOfDataIndicator getTypeOfDataIndicator(String id) {
        return getBaseObjectDAO().getTypeOfDataIndicator(id);
	}

	@Override
	public List<TypeOfDataIndicator> getAllTypeOfDataIndicator() {
        return getBaseObjectDAO().getAllTypeOfDataIndicator();
	}
}
