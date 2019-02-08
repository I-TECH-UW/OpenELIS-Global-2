package spring.generated.testconfiguration.controller;

import java.lang.String;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.generated.forms.TestActivationForm;
import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.TestService;
import us.mn.state.health.lims.common.services.TypeOfSampleService;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.test.beanItems.TestActivationBean;

@Controller
public class TestActivationController extends BaseController {
  @RequestMapping(
      value = "/TestActivation",
      method = RequestMethod.GET
  )
  public ModelAndView showTestActivation(HttpServletRequest request,
      @ModelAttribute("form") TestActivationForm form) {
    String forward = FWD_SUCCESS;
    if (form == null) {
    	form = new TestActivationForm();
    }
        form.setFormAction("");
    BaseErrors errors = new BaseErrors();
    if (form.getErrors() != null) {
    	errors = (BaseErrors) form.getErrors();
    }
    ModelAndView mv = checkUserAndSetup(form, errors, request);

    if (errors.hasErrors()) {
    	return mv;
    }
    
    List<TestActivationBean> activeTestList = createTestList(true);
    List<TestActivationBean> inactiveTestList = createTestList(false);
    form.setActiveTestList(activeTestList);
    form.setInactiveTestList(inactiveTestList);
    
    return findForward(forward, form);
  }
  
  private List<TestActivationBean> createTestList(boolean active) {
      ArrayList<TestActivationBean> testList = new ArrayList<TestActivationBean>();

      List<IdValuePair> sampleTypeList = DisplayListService.getList(active ? DisplayListService.ListType.SAMPLE_TYPE_ACTIVE : DisplayListService.ListType.SAMPLE_TYPE_INACTIVE);

      //if not active we use alphabetical ordering, the default is display order
      if( !active){
        IdValuePair.sortByValue( sampleTypeList );
      }

      for( IdValuePair pair : sampleTypeList){
          TestActivationBean bean = new TestActivationBean();

          List<Test> tests = TypeOfSampleService.getAllTestsBySampleTypeId(pair.getId());
          List<IdValuePair> activeTests = new ArrayList<IdValuePair>();
          List<IdValuePair> inactiveTests = new ArrayList<IdValuePair>();

          //initial ordering will be by display order.  Inactive tests will then be re-ordered alphabetically
          Collections.sort(tests, new Comparator<Test>() {
              @Override
              public int compare(Test o1, Test o2) {
              	//compare sort order
              	if (NumberUtils.isNumber(o1.getSortOrder()) && NumberUtils.isNumber(o2.getSortOrder())) {
              		return Integer.parseInt(o1.getSortOrder()) - Integer.parseInt(o2.getSortOrder());
                  //if o2 has no sort order o1 does, o2 is assumed to be higher
              	} else if (NumberUtils.isNumber(o1.getSortOrder())){
                  	return -1;
                  //if o1 has no sort order o2 does, o1 is assumed to be higher
                  } else if (NumberUtils.isNumber(o2.getSortOrder())) {
                  	return 1;
                  //else they are considered equal
                  } else {
                  	return 0;
                  }
              }
          });

          for( Test test : tests) {
              if( test.isActive()) {
                  activeTests.add(new IdValuePair(test.getId(), TestService.getUserLocalizedTestName(test)));
              }else{
                  inactiveTests.add(new IdValuePair(test.getId(), TestService.getUserLocalizedTestName(test)));
              }
          }

          IdValuePair.sortByValue( inactiveTests);

          bean.setActiveTests(activeTests);
          bean.setInactiveTests(inactiveTests);
          if( !activeTests.isEmpty() || !inactiveTests.isEmpty()) {
              bean.setSampleType(pair);
              testList.add(bean);
          }
      }

      return testList;
  }

  protected ModelAndView findLocalForward(String forward, BaseForm form) {
    if ("success".equals(forward)) {
      return new ModelAndView("testActivationDefinition", "form", form);
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
