package spring.mine.dataexchange.order.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.dataexchange.order.form.ElectronicOrderViewForm;
import spring.mine.dataexchange.order.validator.ElectronicOrderViewFormValidator;
import us.mn.state.health.lims.dataexchange.order.dao.ElectronicOrderDAO;
import us.mn.state.health.lims.dataexchange.order.daoimpl.ElectronicOrderDAOImpl;
import us.mn.state.health.lims.dataexchange.order.valueholder.ElectronicOrder;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.statusofsample.dao.StatusOfSampleDAO;
import us.mn.state.health.lims.statusofsample.daoimpl.StatusOfSampleDAOImpl;
import us.mn.state.health.lims.statusofsample.valueholder.StatusOfSample;

@Controller
public class ElectronicOrdersController extends BaseController {

	@Autowired
	ElectronicOrderViewFormValidator formValidator;

	@RequestMapping(value = "/ElectronicOrders", method = RequestMethod.GET)
	public ModelAndView showElectronicOrders(HttpServletRequest request,
			@ModelAttribute("form") ElectronicOrderViewForm form, BindingResult result) {
		String forward = FWD_SUCCESS;
		formValidator.validate(form, result);

		// if invalid request, default to basic values
		if (result.hasErrors()) {
			saveErrors(result);
			form.setSortOrder("lastupdated");
			form.setPage(1);
		}

		Transaction tx = HibernateUtil.getSession().beginTransaction();
		ElectronicOrderDAO electronicOrderDAO = new ElectronicOrderDAOImpl();
		List<ElectronicOrder> eOrders = electronicOrderDAO.getAllElectronicOrdersOrderedBy(form.getSortOrder());
		tx.commit();

		// correct for proper bounds
		int startIndex = (form.getPage() - 1) * 50;
		startIndex = startIndex > eOrders.size() ? 0 : startIndex;
		int endIndex = startIndex + 50 > eOrders.size() ? eOrders.size() : startIndex + 50;

		// set attributes for use in jsp
		request.setAttribute("startIndex", startIndex);
		request.setAttribute("endIndex", endIndex);
		request.setAttribute("total", eOrders.size());

		// get section of list for display on current page
		eOrders = eOrders.subList(startIndex, endIndex);
		tx = HibernateUtil.getSession().beginTransaction();
		StatusOfSampleDAO statusDAO = new StatusOfSampleDAOImpl();
		for (ElectronicOrder eOrder : eOrders) {
			StatusOfSample status = new StatusOfSample();
			status.setId(eOrder.getStatusId());
			statusDAO.getData(status);
			eOrder.setStatus(status);
		}
		tx.commit();
		form.setEOrders(eOrders);

		return findForward(forward, form);
	}

	@Override
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "electronicOrderViewDefinition";
		} else {
			return "PageNotFound";
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
