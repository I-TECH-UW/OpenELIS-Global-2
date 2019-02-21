package spring.mine.dataexchange.order.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import spring.mine.dataexchange.order.form.ElectronicOrderViewForm;
import us.mn.state.health.lims.common.util.validator.GenericValidator;
import us.mn.state.health.lims.dataexchange.order.dao.ElectronicOrderDAO;
import us.mn.state.health.lims.dataexchange.order.daoimpl.ElectronicOrderDAOImpl;
import us.mn.state.health.lims.dataexchange.order.valueholder.ElectronicOrder;
import us.mn.state.health.lims.statusofsample.dao.StatusOfSampleDAO;
import us.mn.state.health.lims.statusofsample.daoimpl.StatusOfSampleDAOImpl;
import us.mn.state.health.lims.statusofsample.valueholder.StatusOfSample;

@Controller
public class ElectronicOrdersController extends BaseController {

	@RequestMapping(value = "/ElectronicOrders", method = RequestMethod.GET)
	public ModelAndView showElectronicOrders(HttpServletRequest request,
			@ModelAttribute("form") ElectronicOrderViewForm form) {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new ElectronicOrderViewForm();
		}
		form.setFormAction("");
		Errors errors = new BaseErrors();
		

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
		form.setEOrders(eOrders);

		return findForward(forward, form);
	}

	private boolean valid(String sortOrder, String pageNumber) {
		if (!GenericValidator.isInt(pageNumber)) {
			return false;
		} else if (Integer.parseInt(pageNumber) <= 0) {
			return false;
		} else if (!"lastupdated".equals(sortOrder) && !"externalId".equals(sortOrder)
				&& !"statusId".equals(sortOrder)) {
			return false;
		}
		return true;
	}

	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if ("success".equals(forward)) {
			return new ModelAndView("electronicOrderViewDefinition", "form", form);
		} else {
			return new ModelAndView("PageNotFound");
		}
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
