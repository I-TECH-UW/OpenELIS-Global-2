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
import spring.generated.forms.SampleTypeRenameEntryForm;
import us.mn.state.health.lims.common.services.DisplayListService;

import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.localization.daoimpl.LocalizationDAOImpl;
import us.mn.state.health.lims.localization.valueholder.Localization;
import us.mn.state.health.lims.typeofsample.daoimpl.TypeOfSampleDAOImpl;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;

@Controller
public class SampleTypeRenameEntryController extends BaseController {
	@RequestMapping(
			value = "/SampleTypeRenameEntry", 
			method = RequestMethod.GET
	)
	public ModelAndView showSampleTypeRenameEntry(HttpServletRequest request,
			@ModelAttribute("form") SampleTypeRenameEntryForm form) {	
		String forward = FWD_SUCCESS;
		if (form == null ) {
			 form = new SampleTypeRenameEntryForm();
		}
		form.setFormAction("");
		BaseErrors errors = new BaseErrors();
		ModelAndView mv = checkUserAndSetup(form, errors, request);

		if (errors.hasErrors()) {
			return mv;
		}

		form.setSampleTypeList(DisplayListService.getList(DisplayListService.ListType.SAMPLE_TYPE_ACTIVE));

		return findForward(forward, form);
	}

	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if (FWD_SUCCESS.equals(forward)) {
			return new ModelAndView("sampleTypeRenameDefinition", "form", form);
		} else if (FWD_SUCCESS_INSERT.equals(forward)) {
		      return new ModelAndView("redirect:/SampleTypeRenameEntry.do", "form", form);
		} else {
			return new ModelAndView("PageNotFound");
		}
	}
	
	@RequestMapping(
			value = "/SampleTypeRenameEntry", 
			method = RequestMethod.POST
		)
	public ModelAndView updateSampleTypeRenameEntry(HttpServletRequest request,
			@ModelAttribute("form") SampleTypeRenameEntryForm form) {	
		
		String forward = FWD_SUCCESS_INSERT;

		String sampleTypeId = form.getSampleTypeId();
		String nameEnglish = form.getNameEnglish();
		String nameFrench = form.getNameFrench();
		String userId = getSysUserId(request);

		updateSampleTypeNames(sampleTypeId, nameEnglish, nameFrench, userId);

		form = new SampleTypeRenameEntryForm();
		form.setFormAction("");

		form.setSampleTypeList(DisplayListService.getList(DisplayListService.ListType.SAMPLE_TYPE_ACTIVE));

		return findForward(forward, form);
	}

	private void updateSampleTypeNames(String sampleTypeId, String nameEnglish, String nameFrench, String userId) {
		TypeOfSample typeOfSample = new TypeOfSampleDAOImpl().getTypeOfSampleById(sampleTypeId);
		
		if (typeOfSample != null) {

			Localization name = typeOfSample.getLocalization();
			name.setEnglish(nameEnglish.trim());
			name.setFrench(nameFrench.trim());
			name.setSysUserId(userId);

			Transaction tx = HibernateUtil.getSession().beginTransaction();

			try {
				new LocalizationDAOImpl().updateData(name);
				tx.commit();
			} catch (HibernateException e) {
				tx.rollback();
			} finally {
				HibernateUtil.closeSession();
			}

		}

		// Refresh SampleType names
		DisplayListService.getFreshList(DisplayListService.ListType.SAMPLE_TYPE_ACTIVE);
	}

	protected String getPageTitleKey() {
		return null;
	}

	protected String getPageSubtitleKey() {
		return null;
	}
}
