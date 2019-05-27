package spring.service.analyte;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.analyte.dao.AnalyteDAO;
import us.mn.state.health.lims.analyte.valueholder.Analyte;

@Service
public class AnalyteServiceImpl extends BaseObjectServiceImpl<Analyte> implements AnalyteService {
	@Autowired
	protected AnalyteDAO baseObjectDAO;

	AnalyteServiceImpl() {
		super(Analyte.class);
	}

	@Override
	protected AnalyteDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public void getData(Analyte analyte) {
        getBaseObjectDAO().getData(analyte);

	}

	@Override
	public List getAnalytes(String filter) {
        return getBaseObjectDAO().getAnalytes(filter);
	}

	@Override
	public Analyte getAnalyteByName(Analyte analyte, boolean ignoreCase) {
        return getBaseObjectDAO().getAnalyteByName(analyte,ignoreCase);
	}

	@Override
	public List getAllAnalytes() {
        return getBaseObjectDAO().getAllAnalytes();
	}

	@Override
	public void deleteData(List analytes) {
        getBaseObjectDAO().deleteData(analytes);

	}

	@Override
	public void updateData(Analyte analyte) {
        getBaseObjectDAO().updateData(analyte);

	}

	@Override
	public boolean insertData(Analyte analyte) {
        return getBaseObjectDAO().insertData(analyte);
	}

	@Override
	public List getPageOfAnalytes(int startingRecNo) {
        return getBaseObjectDAO().getPageOfAnalytes(startingRecNo);
	}

	@Override
	public Integer getTotalAnalyteCount() {
        return getBaseObjectDAO().getTotalAnalyteCount();
	}

	@Override
	public Integer getTotalSearchedAnalyteCount(String searchString) {
        return getBaseObjectDAO().getTotalSearchedAnalyteCount(searchString);
	}

	@Override
	public List getPreviousAnalyteRecord(String id) {
        return getBaseObjectDAO().getPreviousAnalyteRecord(id);
	}

	@Override
	public List getNextAnalyteRecord(String id) {
        return getBaseObjectDAO().getNextAnalyteRecord(id);
	}

	@Override
	public List getPagesOfSearchedAnalytes(int startRecNo, String searchString) {
        return getBaseObjectDAO().getPagesOfSearchedAnalytes(startRecNo,searchString);
	}
}
