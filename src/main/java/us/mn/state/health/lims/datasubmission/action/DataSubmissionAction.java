package us.mn.state.health.lims.datasubmission.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.validator.GenericValidator;
import us.mn.state.health.lims.datasubmission.DataIndicatorFactory;
import us.mn.state.health.lims.datasubmission.dao.DataIndicatorDAO;
import us.mn.state.health.lims.datasubmission.dao.TypeOfDataIndicatorDAO;
import us.mn.state.health.lims.datasubmission.daoimpl.DataIndicatorDAOImpl;
import us.mn.state.health.lims.datasubmission.daoimpl.TypeOfDataIndicatorDAOImpl;
import us.mn.state.health.lims.datasubmission.valueholder.DataIndicator;
import us.mn.state.health.lims.datasubmission.valueholder.TypeOfDataIndicator;
import us.mn.state.health.lims.siteinformation.daoimpl.SiteInformationDAOImpl;

public class DataSubmissionAction extends BaseAction {

	@Override
	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String forward = "success";
		
		DynaActionForm dynaForm = (DynaActionForm) form;
		dynaForm.initialize(mapping);

		int month = GenericValidator.isBlankOrNull(request.getParameter("month")) ? 
				DateUtil.getCurrentMonth() + 1 : Integer.parseInt(request.getParameter("month"));
		int year = GenericValidator.isBlankOrNull(request.getParameter("year")) ? 
				DateUtil.getCurrentYear() : Integer.parseInt(request.getParameter("year"));
				
		DataIndicatorDAO indicatorDAO = new DataIndicatorDAOImpl();
		TypeOfDataIndicatorDAO typeOfIndicatorDAO = new TypeOfDataIndicatorDAOImpl();
		

		List<DataIndicator> indicators = new ArrayList<DataIndicator>();
		List<TypeOfDataIndicator> typeOfIndicatorList = typeOfIndicatorDAO.getAllTypeOfDataIndicator();
		for (TypeOfDataIndicator typeOfIndicator : typeOfIndicatorList) {
			DataIndicator indicator = indicatorDAO.getIndicatorByTypeYearMonth(typeOfIndicator, year, month);
			if (indicator == null) {
				indicator = DataIndicatorFactory.createBlankDataIndicatorForType(typeOfIndicator);
			}
			indicator.setYear(year);
			indicator.setMonth(month);
			indicators.add(indicator);
		}
		
		dynaForm.set("dataSubUrl", (new SiteInformationDAOImpl()).getSiteInformationByName("Data Sub URL"));
		dynaForm.set("indicators", indicators);
		dynaForm.set("month", month);
		dynaForm.set("year", year);
		dynaForm.set("siteId", ConfigurationProperties.getInstance().getPropertyValue(Property.SiteCode));
		return mapping.findForward(forward);
	}

	@Override
	protected String getPageTitleKey() {
		return StringUtil.getMessageForKey("datasubmission.browse.title");
	}

	@Override
	protected String getPageSubtitleKey() {
		return StringUtil.getMessageForKey("datasubmission.browse.title");
	}

}
