package spring.generated.testconfiguration.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.generated.testconfiguration.form.UomRenameEntryForm;
import spring.mine.common.controller.BaseController;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.unitofmeasure.dao.UnitOfMeasureDAO;
import us.mn.state.health.lims.unitofmeasure.daoimpl.UnitOfMeasureDAOImpl;
import us.mn.state.health.lims.unitofmeasure.valueholder.UnitOfMeasure;

@Controller
public class UomRenameEntryController extends BaseController {

	@RequestMapping(value = "/UomRenameEntry", method = RequestMethod.GET)
	public ModelAndView showUomRenameEntry(HttpServletRequest request) {
		UomRenameEntryForm form = new UomRenameEntryForm();
		form.setUomList(DisplayListService.getList(DisplayListService.ListType.UNIT_OF_MEASURE));

		return findForward(FWD_SUCCESS, form);
	}

	@Override
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "uomRenameDefinition";
		} else if (FWD_SUCCESS_INSERT.equals(forward)) {
			return "redirect:/UomRenameEntry.do";
		} else if (FWD_FAIL_INSERT.equals(forward)) {
			return "uomRenameDefinition";
		} else {
			return "PageNotFound";
		}
	}

	@RequestMapping(value = "/UomRenameEntry", method = RequestMethod.POST)
	public ModelAndView updateUomRenameEntry(HttpServletRequest request,
			@ModelAttribute("form") @Valid UomRenameEntryForm form, BindingResult result) {
		if (result.hasErrors()) {
			saveErrors(result);
			form.setUomList(DisplayListService.getList(DisplayListService.ListType.UNIT_OF_MEASURE));
			return findForward(FWD_FAIL_INSERT, form);
		}
		String uomId = form.getUomId();
		String nameEnglish = form.getNameEnglish();
		String userId = getSysUserId(request);

		updateUomNames(uomId, nameEnglish, userId);

		return findForward(FWD_SUCCESS_INSERT, form);
	}

	private void updateUomNames(String uomId, String nameEnglish, String userId) {
		UnitOfMeasureDAO unitOfMeasureDAO = new UnitOfMeasureDAOImpl();
		UnitOfMeasure unitOfMeasure = unitOfMeasureDAO.getUnitOfMeasureById(uomId);

		if (unitOfMeasure != null) {

			// not using localization for UOM

//            Localization name = unitOfMeasure.getLocalization();
//
//            name.setEnglish( nameEnglish.trim() );
//            name.setFrench( nameFrench.trim() );
//            name.setSysUserId( userId );

			unitOfMeasure.setUnitOfMeasureName(nameEnglish.trim());
			unitOfMeasure.setSysUserId(userId);

			Transaction tx = HibernateUtil.getSession().beginTransaction();

			try {
//              new LocalizationDAOImpl().updateData( name );
				// new
				unitOfMeasureDAO.updateData(unitOfMeasure);

				tx.commit();
			} catch (HibernateException e) {
				tx.rollback();
			} finally {
				HibernateUtil.closeSession();
			}

		}

		// Refresh Uom names
		DisplayListService.getFreshList(DisplayListService.ListType.UNIT_OF_MEASURE);
	}

	@Override
	protected String getPageTitleKey() {
		return null;
	}

	@Override
	protected String getPageSubtitleKey() {
		return null;
	}
}
