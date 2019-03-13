package spring.generated.testconfiguration.controller;

import java.lang.String;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import spring.generated.forms.UomRenameEntryForm;
import us.mn.state.health.lims.common.services.DisplayListService;

import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.localization.daoimpl.LocalizationDAOImpl;
import us.mn.state.health.lims.localization.valueholder.Localization;
import us.mn.state.health.lims.unitofmeasure.dao.UnitOfMeasureDAO;
import us.mn.state.health.lims.unitofmeasure.daoimpl.UnitOfMeasureDAOImpl;
import us.mn.state.health.lims.unitofmeasure.valueholder.UnitOfMeasure;

@Controller
public class UomRenameEntryController extends BaseController {
	@RequestMapping(
			value = "/UomRenameEntry", 
			method = RequestMethod.GET
	)
	public ModelAndView showUomRenameEntry(HttpServletRequest request,
			@ModelAttribute("form") UomRenameEntryForm form) {	
		String forward = FWD_SUCCESS;
		if (form == null ) {
			 form = new UomRenameEntryForm();
		}
		form.setFormAction("");
		BaseErrors errors = new BaseErrors();
		ModelAndView mv = checkUserAndSetup(form, errors, request);

		if (errors.hasErrors()) {
			return mv;
		}

		form.setUomList(DisplayListService.getList(DisplayListService.ListType.UNIT_OF_MEASURE));

		return findForward(forward, form);
	}

	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if (FWD_SUCCESS.equals(forward)) {
			return new ModelAndView("uomRenameDefinition", "form", form);
		} else if (FWD_SUCCESS_INSERT.equals(forward)) {
		      return new ModelAndView("redirect:/UomRenameEntry.do", "form", form);
		} else {
			return new ModelAndView("PageNotFound");
		}
	}
	
	@RequestMapping(
			value = "/UomRenameEntry", 
			method = RequestMethod.POST
		)
	public ModelAndView updateUomRenameEntry(HttpServletRequest request,
			@ModelAttribute("form") UomRenameEntryForm form) {	
		
		String forward = FWD_SUCCESS_INSERT;

		String uomId = form.getUomId();
		String nameEnglish = form.getNameEnglish();
		String userId = getSysUserId(request);

		updateUomNames(uomId, nameEnglish, userId);

		form = new UomRenameEntryForm();
		form.setFormAction("");

		form.setUomList(DisplayListService.getList(DisplayListService.ListType.UNIT_OF_MEASURE));

		return findForward(forward, form);
	}

	private void updateUomNames(String uomId, String nameEnglish, String userId) {
		UnitOfMeasureDAO unitOfMeasureDAO = new UnitOfMeasureDAOImpl();
    	UnitOfMeasure unitOfMeasure = unitOfMeasureDAO.getUnitOfMeasureById(uomId);
		
        if( unitOfMeasure != null ){
        	
        	// not using localization for UOM
        	
//            Localization name = unitOfMeasure.getLocalization();
//
//            name.setEnglish( nameEnglish.trim() );
//            name.setFrench( nameFrench.trim() );
//            name.setSysUserId( userId );
        	
        	unitOfMeasure.setUnitOfMeasureName(nameEnglish.trim() );
        	unitOfMeasure.setSysUserId(userId);

            Transaction tx = HibernateUtil.getSession().beginTransaction();

            try{
//              new LocalizationDAOImpl().updateData( name );
                //new
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

	protected String getPageTitleKey() {
		return null;
	}

	protected String getPageSubtitleKey() {
		return null;
	}
}
