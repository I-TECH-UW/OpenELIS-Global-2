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

import spring.generated.testconfiguration.form.SampleTypeRenameEntryForm;
import spring.mine.common.controller.BaseController;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.localization.daoimpl.LocalizationDAOImpl;
import us.mn.state.health.lims.localization.valueholder.Localization;
import us.mn.state.health.lims.typeofsample.daoimpl.TypeOfSampleDAOImpl;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;

@Controller
public class SampleTypeRenameEntryController extends BaseController {

	@RequestMapping(value = "/SampleTypeRenameEntry", method = RequestMethod.GET)
	public ModelAndView showSampleTypeRenameEntry(HttpServletRequest request) {
		SampleTypeRenameEntryForm form = new SampleTypeRenameEntryForm();

		form.setSampleTypeList(DisplayListService.getInstance().getList(DisplayListService.ListType.SAMPLE_TYPE_ACTIVE));

		return findForward(FWD_SUCCESS, form);
	}

	@Override
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "sampleTypeRenameDefinition";
		} else if (FWD_SUCCESS_INSERT.equals(forward)) {
			return "redirect:/SampleTypeRenameEntry.do";
		} else if (FWD_FAIL_INSERT.equals(forward)) {
			return "sampleTypeRenameDefinition";
		} else {
			return "PageNotFound";
		}
	}

	@RequestMapping(value = "/SampleTypeRenameEntry", method = RequestMethod.POST)
	public ModelAndView updateSampleTypeRenameEntry(HttpServletRequest request,
			@ModelAttribute("form") @Valid SampleTypeRenameEntryForm form, BindingResult result) {
		if (result.hasErrors()) {
			saveErrors(result);
			form.setSampleTypeList(DisplayListService.getInstance().getList(DisplayListService.ListType.SAMPLE_TYPE_ACTIVE));
			return findForward(FWD_FAIL_INSERT, form);
		}

		String sampleTypeId = form.getSampleTypeId();
		String nameEnglish = form.getNameEnglish();
		String nameFrench = form.getNameFrench();
		String userId = getSysUserId(request);

		updateSampleTypeNames(sampleTypeId, nameEnglish, nameFrench, userId);

		form.setSampleTypeList(DisplayListService.getInstance().getList(DisplayListService.ListType.SAMPLE_TYPE_ACTIVE));

		return findForward(FWD_SUCCESS_INSERT, form);
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
		DisplayListService.getInstance().getFreshList(DisplayListService.ListType.SAMPLE_TYPE_ACTIVE);
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
