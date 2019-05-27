package spring.service.dataexchange.aggregatereporting;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.dataexchange.aggregatereporting.dao.ReportExternalImportDAO;
import us.mn.state.health.lims.dataexchange.aggregatereporting.valueholder.ReportExternalImport;

@Service
public class ReportExternalImportServiceImpl extends BaseObjectServiceImpl<ReportExternalImport> implements ReportExternalImportService {
	@Autowired
	protected ReportExternalImportDAO baseObjectDAO;

	ReportExternalImportServiceImpl() {
		super(ReportExternalImport.class);
	}

	@Override
	protected ReportExternalImportDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public List<ReportExternalImport> getReportsInDateRangeSortedForSite(Timestamp beginning, Timestamp end, String site) {
        return getBaseObjectDAO().getReportsInDateRangeSortedForSite(beginning,end,site);
	}

	@Override
	public List<ReportExternalImport> getReportsInDateRangeSorted(Timestamp lower, Timestamp upper) {
        return getBaseObjectDAO().getReportsInDateRangeSorted(lower,upper);
	}

	@Override
	public ReportExternalImport getReportByEventDateSiteType(ReportExternalImport importReport) {
        return getBaseObjectDAO().getReportByEventDateSiteType(importReport);
	}

	@Override
	public void updateReportExternalImport(ReportExternalImport report) {
        getBaseObjectDAO().updateReportExternalImport(report);

	}

	@Override
	public void insertReportExternalImport(ReportExternalImport report) {
        getBaseObjectDAO().insertReportExternalImport(report);

	}

	@Override
	public List<String> getUniqueSites() {
        return getBaseObjectDAO().getUniqueSites();
	}
}
