package spring.service.analyte;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.analyte.valueholder.Analyte;

public interface AnalyteService extends BaseObjectService<Analyte, String> {
	void getData(Analyte analyte);

	List getAnalytes(String filter);

	Analyte getAnalyteByName(Analyte analyte, boolean ignoreCase);

	List getAllAnalytes();

	void deleteData(List analytes);

	void updateData(Analyte analyte);

	boolean insertData(Analyte analyte);

	List getPageOfAnalytes(int startingRecNo);

	Integer getTotalAnalyteCount();

	Integer getTotalSearchedAnalyteCount(String searchString);

	List getPreviousAnalyteRecord(String id);

	List getNextAnalyteRecord(String id);

	List getPagesOfSearchedAnalytes(int startRecNo, String searchString);
}
