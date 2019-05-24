package spring.service.typeoftestresult;

import java.util.List;

import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.typeoftestresult.dao.TypeOfTestResultDAO;
import us.mn.state.health.lims.typeoftestresult.daoimpl.TypeOfTestResultDAOImpl;
import us.mn.state.health.lims.typeoftestresult.valueholder.TypeOfTestResult;

@Service
@DependsOn({ "springContext" })
public class TypeOfTestResultServiceImpl extends BaseObjectServiceImpl<TypeOfTestResult> implements TypeOfTestResultService {

	public enum ResultType {
		REMARK("R"), DICTIONARY("D"), TITER("T"), NUMERIC("N"), ALPHA("A"), MULTISELECT("M"), CASCADING_MULTISELECT("C");

		String DBValue;
		String id;

		ResultType(String dbValue) {
			DBValue = dbValue;
			id = new TypeOfTestResultDAOImpl().getTypeOfTestResultByType(dbValue).getId();
		}

		public String getCharacterValue() {
			return DBValue;
		}

		public String getId() {
			return id;
		}

		public boolean matches(String type) {
			return DBValue.equals(type);
		}

		public static boolean isDictionaryVariant(String type) {
			return !GenericValidator.isBlankOrNull(type) && "DMC".contains(type);
		}

		public static boolean isMultiSelectVariant(String type) {
			return !GenericValidator.isBlankOrNull(type) && "MC".contains(type);
		}

		public static boolean isTextOnlyVariant(String type) {
			return !GenericValidator.isBlankOrNull(type) && "AR".contains(type);
		}

		public static boolean isTextOnlyVariant(ResultType type) {
			return "AR".contains(type.getCharacterValue());
		}

		public static boolean isNumericById(String resultTypeId) {
			return NUMERIC.getId().equals(resultTypeId);
		}

		public static boolean isNumeric(ResultType type) {
			return "N".equals(type.getCharacterValue());
		}

		public static boolean isDictionaryVarientById(String resultTypeId) {
			return DICTIONARY.getId().equals(resultTypeId) || MULTISELECT.getId().equals(resultTypeId) || CASCADING_MULTISELECT.getId().equals(resultTypeId);
		}
	}

	@Autowired
	protected TypeOfTestResultDAO baseObjectDAO;

	TypeOfTestResultServiceImpl() {
		super(TypeOfTestResult.class);
	}

	@Override
	protected TypeOfTestResultDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	public static ResultType getResultTypeById(String id) {
		for (ResultType type : ResultType.values()) {
			if (type.getId().equals(id)) {
				return type;
			}
		}

		return null;
	}

	@Override
	public void getData(TypeOfTestResult typeOfTestResult) {
        getBaseObjectDAO().getData(typeOfTestResult);

	}

	@Override
	public void deleteData(List typeOfTestResults) {
        getBaseObjectDAO().deleteData(typeOfTestResults);

	}

	@Override
	public void updateData(TypeOfTestResult typeOfTestResult) {
        getBaseObjectDAO().updateData(typeOfTestResult);

	}

	@Override
	public boolean insertData(TypeOfTestResult typeOfTestResult) {
        return getBaseObjectDAO().insertData(typeOfTestResult);
	}

	@Override
	public Integer getTotalTypeOfTestResultCount() {
        return getBaseObjectDAO().getTotalTypeOfTestResultCount();
	}

	@Override
	public List getNextTypeOfTestResultRecord(String id) {
        return getBaseObjectDAO().getNextTypeOfTestResultRecord(id);
	}

	@Override
	public List getPageOfTypeOfTestResults(int startingRecNo) {
        return getBaseObjectDAO().getPageOfTypeOfTestResults(startingRecNo);
	}

	@Override
	public List getAllTypeOfTestResults() {
        return getBaseObjectDAO().getAllTypeOfTestResults();
	}

	@Override
	public TypeOfTestResult getTypeOfTestResultByType(TypeOfTestResult typeOfTestResult) {
        return getBaseObjectDAO().getTypeOfTestResultByType(typeOfTestResult);
	}

	@Override
	public TypeOfTestResult getTypeOfTestResultByType(String type) {
        return getBaseObjectDAO().getTypeOfTestResultByType(type);
	}

	@Override
	public List getPreviousTypeOfTestResultRecord(String id) {
        return getBaseObjectDAO().getPreviousTypeOfTestResultRecord(id);
	}
}
