package us.mn.state.health.lims.dataexchange.order.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.common.util.validator.GenericValidator;
import us.mn.state.health.lims.dataexchange.order.dao.ElectronicOrderDAO;
import us.mn.state.health.lims.dataexchange.order.daoimpl.ElectronicOrderDAOImpl;
import us.mn.state.health.lims.dataexchange.order.valueholder.ElectronicOrder;
import us.mn.state.health.lims.statusofsample.dao.StatusOfSampleDAO;
import us.mn.state.health.lims.statusofsample.daoimpl.StatusOfSampleDAOImpl;
import us.mn.state.health.lims.statusofsample.valueholder.StatusOfSample;

public class ElectronicOrderViewAction extends BaseAction {

	@Override
	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String forward = FWD_SUCCESS;
		DynaActionForm dynaForm = ( DynaActionForm ) form ;
		String sortOrder = request.getParameter("sortOrder");
		String pageNumber = request.getParameter("page");
		
		sortOrder = sortOrder == null ? "lastupdated" : sortOrder;
		pageNumber = pageNumber == null ? "1" : pageNumber;
		
		// if invalid request, default to basic values
		if (!valid(sortOrder, pageNumber)) {
			sortOrder = "lastupdated";
			pageNumber = "1";
		}
		
		ElectronicOrderDAO electronicOrderDAO = new ElectronicOrderDAOImpl();
		List<ElectronicOrder> eOrders = electronicOrderDAO.getAllElectronicOrdersOrderedBy(sortOrder);
	
		// correct for proper bounds
		int startIndex = (Integer.parseInt(pageNumber) - 1) * 50;
		startIndex = startIndex > eOrders.size() ? 0 : startIndex;
		int endIndex = startIndex + 50 > eOrders.size() ? eOrders.size() : startIndex + 50;
		
		// set attributes for use in jsp
		request.setAttribute("sortOrder", sortOrder);
		request.setAttribute("startIndex", startIndex);
		request.setAttribute("endIndex", endIndex);
		request.setAttribute("total", eOrders.size());
		
		// get section of list for display on current page
		eOrders = eOrders.subList(startIndex, endIndex);
		StatusOfSampleDAO statusDAO = new StatusOfSampleDAOImpl();
		for (ElectronicOrder eOrder : eOrders) {
			StatusOfSample status = new StatusOfSample();
			status.setId(eOrder.getStatusId());
			statusDAO.getData(status);
			eOrder.setStatus(status);
		}
		
		dynaForm.set("eOrders", eOrders);
		return mapping.findForward(forward);
	}

	private boolean valid(String sortOrder, String pageNumber) {
		if (!GenericValidator.isInt(pageNumber)) {
			return false;
		} else if (Integer.parseInt(pageNumber) <= 0) {
			return false;
		} else if (!"lastupdated".equals(sortOrder) && !"externalId".equals(sortOrder) && !"statusId".equals(sortOrder)) {
			return false;
		}
		return true;
	}

	@Override
	protected String getPageTitleKey() {
		return "eorder.browse.title";
	}

	@Override
	protected String getPageSubtitleKey() {
		return "eorder.browse.title";
	}

}
