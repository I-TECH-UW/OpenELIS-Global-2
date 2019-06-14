package spring.service.gender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.common.exception.LIMSDuplicateRecordException;
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
	public String insert(Gender gender) {
		if (getBaseObjectDAO().duplicateGenderExists(gender)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + gender.getGenderType());
		}
		return super.insert(gender);
	}

	@Override
	public Gender save(Gender gender) {
		if (getBaseObjectDAO().duplicateGenderExists(gender)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + gender.getGenderType());
		}
		return super.save(gender);
	}

	@Override
	public Gender update(Gender gender) {
		if (getBaseObjectDAO().duplicateGenderExists(gender)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + gender.getGenderType());
		}
		return super.update(gender);
	}

	@Override
	@Transactional(readOnly = true)
	public Gender getGenderByType(String type) {
		return getMatch("genderType", type).orElse(null);
	}

}
