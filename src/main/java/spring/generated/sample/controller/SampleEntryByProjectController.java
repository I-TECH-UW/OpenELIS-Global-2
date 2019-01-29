package spring.generated.sample.controller;

import java.lang.String;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import spring.generated.forms.SampleEntryByProjectForm;
import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import spring.mine.sample.controller.BaseSampleEntryController;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.DisplayListService.ListType;
import us.mn.state.health.lims.common.services.StatusService.SampleStatus;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.dictionary.ObservationHistoryList;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.organization.util.OrganizationTypeList;
import us.mn.state.health.lims.organization.valueholder.Organization;
import us.mn.state.health.lims.patient.saving.Accessioner;
import us.mn.state.health.lims.patient.saving.SampleEntry;
import us.mn.state.health.lims.patient.saving.SampleEntryAfterPatientEntry;
import us.mn.state.health.lims.patient.saving.SampleSecondEntry;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;


@Controller
public class SampleEntryByProjectController extends BaseSampleEntryController {
  @RequestMapping(
      value = "/SampleEntryByProject",
      method = RequestMethod.GET
  )
  public ModelAndView showSampleEntryByProject(HttpServletRequest request,
      @ModelAttribute("form") SampleEntryByProjectForm form) {
    String forward = FWD_SUCCESS;
    if (form == null) {
    	form = new SampleEntryByProjectForm();
    }
    form.setFormAction("");
    
    Date today = Calendar.getInstance().getTime();
    String dateAsText = DateUtil.formatDateAsText(today);
    form.setReceivedDateForDisplay(dateAsText);
    form.setInterviewDate(dateAsText);

    Map<String, List<Dictionary>> formListsMapOfLists = new HashMap<String, List<Dictionary>>();
    List<Dictionary> listOfDictionary = new ArrayList<Dictionary>();
    List<IdValuePair> genders = DisplayListService.getList(ListType.GENDERS);
    
    for (IdValuePair i : genders)  {
    	Dictionary dictionary = new Dictionary();
    	dictionary.setId(i.getId());
    	dictionary.setDictEntry(i.getValue());
    	listOfDictionary.add( dictionary);
    }
    
    formListsMapOfLists.put("GENDERS", listOfDictionary);
    form.setFormLists(formListsMapOfLists);
    
    //Get Lists
    Map<String, List<Dictionary>> observationHistoryMapOfLists = new HashMap<String, List<Dictionary>>();
    observationHistoryMapOfLists.put("EID_WHICH_PCR", ObservationHistoryList.EID_WHICH_PCR.getList());
    observationHistoryMapOfLists.put("EID_SECOND_PCR_REASON", ObservationHistoryList.EID_SECOND_PCR_REASON.getList());
    observationHistoryMapOfLists.put("EID_TYPE_OF_CLINIC", ObservationHistoryList.EID_TYPE_OF_CLINIC.getList());
    observationHistoryMapOfLists.put("EID_HOW_CHILD_FED", ObservationHistoryList.EID_HOW_CHILD_FED.getList());
    observationHistoryMapOfLists.put("EID_STOPPED_BREASTFEEDING", ObservationHistoryList.EID_STOPPED_BREASTFEEDING.getList());
    observationHistoryMapOfLists.put("YES_NO", ObservationHistoryList.YES_NO.getList());
    observationHistoryMapOfLists.put("EID_INFANT_PROPHYLAXIS_ARV", ObservationHistoryList.EID_INFANT_PROPHYLAXIS_ARV.getList());
    observationHistoryMapOfLists.put("YES_NO_UNKNOWN", ObservationHistoryList.YES_NO_UNKNOWN.getList());
    observationHistoryMapOfLists.put("EID_MOTHERS_HIV_STATUS", ObservationHistoryList.EID_MOTHERS_HIV_STATUS.getList());
    observationHistoryMapOfLists.put("EID_MOTHERS_ARV_TREATMENT", ObservationHistoryList.EID_MOTHERS_ARV_TREATMENT.getList());
    observationHistoryMapOfLists.put("HIV_STATUSES", ObservationHistoryList.HIV_STATUSES.getList());
    observationHistoryMapOfLists.put("SPECIAL_REQUEST_REASONS", ObservationHistoryList.SPECIAL_REQUEST_REASONS.getList());
    observationHistoryMapOfLists.put("ARV_REGIME", ObservationHistoryList.ARV_REGIME.getList());
    observationHistoryMapOfLists.put("ARV_REASON_FOR_VL_DEMAND", ObservationHistoryList.ARV_REASON_FOR_VL_DEMAND.getList());
    
	form.setDictionaryLists(observationHistoryMapOfLists);
	
	//Get EID Sites
	Map<String, List<Organization>> organizationTypeMapOfLists = new HashMap<String, List<Organization>>();
	organizationTypeMapOfLists.put("ARV_ORGS", OrganizationTypeList.ARV_ORGS.getList());
	organizationTypeMapOfLists.put("ARV_ORGS_BY_NAME", OrganizationTypeList.ARV_ORGS_BY_NAME.getList());
	organizationTypeMapOfLists.put("EID_ORGS_BY_NAME", OrganizationTypeList.EID_ORGS_BY_NAME.getList());
	organizationTypeMapOfLists.put("EID_ORGS", OrganizationTypeList.EID_ORGS.getList());
	form.setOrganizationTypeLists(organizationTypeMapOfLists);
	    
    BaseErrors errors = new BaseErrors();
    if (form.getErrors() != null) {
    	errors = (BaseErrors) form.getErrors();
    }
    ModelAndView mv = checkUserAndSetup(form, errors, request);

    if (errors.hasErrors()) {
    	return mv;
    }

    return findForward(forward, form);}
  
  @RequestMapping(
	      value = "/SampleEntryByProject",
	      method = RequestMethod.POST
	  )
	  public ModelAndView postSampleEntryByProject(HttpServletRequest request,
	      @ModelAttribute("form") SampleEntryByProjectForm form) throws Exception {
	    
	  // bean to form conversion side effect 
	  if(form.getGender().indexOf("id=M") != -1)
		  	form.setGender("M");
	  else if (form.getGender().indexOf("id=F") != -1)
	  		form.setGender("F");
	  
	  //System.out.println("Gender:" + form.getGender());
	  
	    String forward = FWD_SUCCESS;

	    Accessioner accessioner;
	    accessioner = new SampleSecondEntry(form, currentUserId, request);
	    if (accessioner.canAccession()) {
	        forward = handleSave(request, accessioner, form);
	        if (forward != null) {
	            return findForward(forward, form);
	        }
	    }
	    accessioner = new SampleEntry(form, currentUserId, request);
	    if (accessioner.canAccession()) {
	        forward = handleSave(request, accessioner, form);
	        if (forward != null) {
	            return findForward(forward, form);
	        }
	    }
	    accessioner = new SampleEntryAfterPatientEntry(form, currentUserId, request);
	    if (accessioner.canAccession()) {
	        forward = handleSave(request, accessioner, form);
	        if (forward != null) {
	            return findForward(forward, form);
	        }
	    }
	    logAndAddMessage(request, "performAction", "errors.UpdateException");
	    
	    form.setFormName("sampleEntryByProjectForm");
	    form.setFormAction("");
	    
	    // TODO: gnr: will need all 
	    Map<String, List<Dictionary>> formListsMapOfLists = new HashMap<String, List<Dictionary>>();
	    List<Dictionary> listOfDictionary = new ArrayList<Dictionary>();
	    List<IdValuePair> genders = DisplayListService.getList(ListType.GENDERS);
	    
	    for (IdValuePair i : genders)  {
	    	Dictionary dictionary = new Dictionary();
	    	dictionary.setId(i.getId());
	    	dictionary.setDictEntry(i.getValue());
	    	listOfDictionary.add( dictionary);
	    }
	    
	    formListsMapOfLists.put("GENDERS", listOfDictionary);
	    form.setFormLists(formListsMapOfLists);
		
	    Map<String, List<Dictionary>> observationHistoryMapOfLists = new HashMap<String, List<Dictionary>>();
	    observationHistoryMapOfLists.put("YES_NO", ObservationHistoryList.YES_NO.getList());
	    form.setDictionaryLists(observationHistoryMapOfLists);

	    BaseErrors errors = new BaseErrors();
	    if (form.getErrors() != null) {
	    	errors = (BaseErrors) form.getErrors();
	    }
	    ModelAndView mv = checkUserAndSetup(form, errors, request);

	    if (errors.hasErrors()) {
	    	return mv;
	    }
	    
	    // return findForward(FWD_FAIL, form);
	    return findForward(forward, form);
	}

	public SampleItem getSampleItem(Sample sample, TypeOfSample typeofsample){
		SampleItem item = new SampleItem();
		item.setSample(sample);
		item.setTypeOfSample(typeofsample);
		item.setSortOrder(Integer.toString(1));
		item.setStatusId(StatusService.getInstance().getStatusID(SampleStatus.Entered));

		return item;
	}

  protected ModelAndView findLocalForward(String forward, BaseForm form) {
    if ("success".equals(forward)) {
      return new ModelAndView("sampleEntryByProjectDefinition", "form", form);
    } else if ("eid_entry".equals(forward)) {
      return new ModelAndView("sampleEntryEIDDefinition", "form", form);
    } else if ("vl_entry".equals(forward)) {
      return new ModelAndView("sampleEntryVLDefinition", "form", form);
    } else if ("fail".equals(forward)) {
      return new ModelAndView("homePageDefinition", "form", form);
    } else {
      return new ModelAndView("PageNotFound");
    }
  }

  protected String getPageTitleKey() {
    return null;
  }

  protected String getPageSubtitleKey() {
    return null;
  }
}
