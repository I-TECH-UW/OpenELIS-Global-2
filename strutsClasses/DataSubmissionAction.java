package org.openelisglobal.datasubmission.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import org.openelisglobal.common.action.BaseAction;
import org.openelisglobal.common.util.ConfigurationProperties;
import org.openelisglobal.common.util.ConfigurationProperties.Property;
import org.openelisglobal.common.util.DateUtil;
import org.openelisglobal.common.util.StringUtil;
import org.openelisglobal.common.util.validator.GenericValidator;
import org.openelisglobal.datasubmission.DataIndicatorFactory;
import org.openelisglobal.datasubmission.dao.DataIndicatorDAO;
import org.openelisglobal.datasubmission.dao.TypeOfDataIndicatorDAO;
import org.openelisglobal.datasubmission.daoimpl.DataIndicatorDAOImpl;
import org.openelisglobal.datasubmission.daoimpl.TypeOfDataIndicatorDAOImpl;
import org.openelisglobal.datasubmission.valueholder.DataIndicator;
import org.openelisglobal.datasubmission.valueholder.TypeOfDataIndicator;
import org.openelisglobal.siteinformation.daoimpl.SiteInformationDAOImpl;

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
