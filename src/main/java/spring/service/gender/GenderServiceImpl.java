package spring.service.gender;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.gender.dao.GenderDAO;
import us.mn.state.health.lims.gender.valueholder.Gender;

@Service
public class GenderServiceImpl extends BaseObjectServiceImpl<Gender, String> implements GenderService {
	@Autowired
	protected GenderDAO baseObjectDAO;

	GenderServiceImpl() {
		super(Gender.class);
	}

	@Override
	protected GenderDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public void getData(Gender gender) {
        getBaseObjectDAO().getData(gender);

	}

	@Override
	public void deleteData(List genders) {
        getBaseObjectDAO().deleteData(genders);

	}

	@Override
	public void updateData(Gender gender) {
        getBaseObjectDAO().updateData(gender);

	}

	@Override
	public boolean insertData(Gender gender) {
        return getBaseObjectDAO().insertData(gender);
	}

	@Override
	public List getNextGenderRecord(String id) {
        return getBaseObjectDAO().getNextGenderRecord(id);
	}

	@Override
	public Integer getTotalGenderCount() {
        return getBaseObjectDAO().getTotalGenderCount();
	}

	@Override
	public List getPreviousGenderRecord(String id) {
        return getBaseObjectDAO().getPreviousGenderRecord(id);
	}

	@Override
	public List getAllGenders() {
        return getBaseObjectDAO().getAllGenders();
	}

	@Override
	public Gender getGenderByType(String type) {
        return getBaseObjectDAO().getGenderByType(type);
	}

	@Override
	public List getPageOfGenders(int startingRecNo) {
        return getBaseObjectDAO().getPageOfGenders(startingRecNo);
	}
}
