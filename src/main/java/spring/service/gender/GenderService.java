package spring.service.gender;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.gender.valueholder.Gender;

public interface GenderService extends BaseObjectService<Gender, String> {
	void getData(Gender gender);

	void deleteData(List genders);

	void updateData(Gender gender);

	boolean insertData(Gender gender);

	List getNextGenderRecord(String id);

	Integer getTotalGenderCount();

	List getPreviousGenderRecord(String id);

	List getAllGenders();

	Gender getGenderByType(String type);

	List getPageOfGenders(int startingRecNo);
}
