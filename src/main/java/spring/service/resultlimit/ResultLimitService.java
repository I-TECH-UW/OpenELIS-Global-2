package spring.service.resultlimit;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.resultlimits.valueholder.ResultLimit;
import us.mn.state.health.lims.test.valueholder.Test;

public interface ResultLimitService extends BaseObjectService<ResultLimit> {

	boolean insertData(ResultLimit resultLimit) throws LIMSRuntimeException;

	void deleteData(List resultLimits) throws LIMSRuntimeException;

	List getAllResultLimits() throws LIMSRuntimeException;

	List getPageOfResultLimits(int startingRecNo) throws LIMSRuntimeException;

	void getData(ResultLimit resultLimit) throws LIMSRuntimeException;

	void updateData(ResultLimit resultLimit) throws LIMSRuntimeException;

	List getNextResultLimitRecord(String id) throws LIMSRuntimeException;

	List getPreviousResultLimitRecord(String id) throws LIMSRuntimeException;

	List<ResultLimit> getAllResultLimitsForTest(String testId) throws LIMSRuntimeException;

	ResultLimit getResultLimitById(String resultLimitId) throws LIMSRuntimeException;

	String getDisplayAgeRange(ResultLimit resultLimit, String separator);

	String getDisplayValidRange(ResultLimit resultLimit, String significantDigits, String separator);

	String getDisplayReferenceRange(ResultLimit resultLimit, String significantDigits, String separator);

	String getDisplayNormalRange(double low, double high, String significantDigits, String separator);

	ResultLimit getResultLimitForTestAndPatient(String testId, Patient patient);

	ResultLimit getResultLimitForTestAndPatient(Test test, Patient patient);

	List<IdValuePair> getPredefinedAgeRanges();

	List<ResultLimit> getResultLimits(String testId);

	List<ResultLimit> getResultLimits(Test test);
}
