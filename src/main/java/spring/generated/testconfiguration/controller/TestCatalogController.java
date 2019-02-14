package spring.generated.testconfiguration.controller;

import java.lang.String;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.generated.forms.TestCatalogForm;
import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import us.mn.state.health.lims.common.services.LocalizationService;
import us.mn.state.health.lims.common.services.ResultLimitService;
import us.mn.state.health.lims.common.services.TestService;
import us.mn.state.health.lims.common.services.TypeOfTestResultService;
import us.mn.state.health.lims.common.util.validator.GenericValidator;
import us.mn.state.health.lims.dictionary.dao.DictionaryDAO;
import us.mn.state.health.lims.dictionary.daoimpl.DictionaryDAOImpl;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.panel.valueholder.Panel;
import us.mn.state.health.lims.resultlimits.valueholder.ResultLimit;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.test.valueholder.TestCatalog;
import us.mn.state.health.lims.testconfiguration.beans.ResultLimitBean;
import us.mn.state.health.lims.testresult.valueholder.TestResult;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;

@Controller
public class TestCatalogController extends BaseController {
  @RequestMapping(
      value = "/TestCatalog",
      method = RequestMethod.GET
  )
  
  public ModelAndView showTestCatalog(HttpServletRequest request,
      @ModelAttribute("form") TestCatalogForm form) {
    String forward = FWD_SUCCESS;
    if (form == null) {
    	form = new TestCatalogForm();
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
    
    
    List<TestCatalog> testCatalogList = createTestList();
    form.setTestCatalogList(testCatalogList);
    
    
    List<String> testSectionList = new ArrayList<String>();
    for( TestCatalog testCatalog : testCatalogList){
        if( !testSectionList.contains( testCatalog.getTestUnit())){
            testSectionList.add(testCatalog.getTestUnit());
        }
    }
    form.setTestSectionList(testSectionList);

    return findForward(forward, form);}

  protected ModelAndView findLocalForward(String forward, BaseForm form) {
    if ("success".equals(forward)) {
      return new ModelAndView("testCatalogDefinition", "form", form);
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
  
  private List<TestCatalog> createTestList() {
      List<TestCatalog> catalogList = new ArrayList<TestCatalog>();

      List<Test> testList = new TestDAOImpl().getAllTests(false);

      for( Test test : testList){

          TestCatalog catalog = new TestCatalog();
          TestService testService = new TestService(test);
          String resultType = testService.getResultType();
          catalog.setId(test.getId());
          catalog.setEnglishName(test.getLocalizedTestName().getEnglish());
          catalog.setFrenchName(test.getLocalizedTestName().getFrench());
          catalog.setEnglishReportName(test.getLocalizedReportingName().getEnglish());
          catalog.setFrenchReportName(test.getLocalizedReportingName().getFrench());
          if (NumberUtils.isNumber(test.getSortOrder())) {
        	  catalog.setTestSortOrder(Integer.parseInt(test.getSortOrder()));
          }
          catalog.setTestUnit(testService.getTestSectionName());
          catalog.setPanel(createPanelList(testService));
          catalog.setResultType(resultType);
          TypeOfSample typeOfSample = testService.getTypeOfSample();
          catalog.setSampleType(typeOfSample != null ? typeOfSample.getLocalizedName() : "n/a");
          catalog.setOrderable(test.getOrderable() ? "Orderable" : "Not orderable");
          catalog.setLoinc(test.getLoinc());
          catalog.setActive(test.isActive() ? "Active" : "Not active");
          catalog.setUom(testService.getUOM(false));
          if( TypeOfTestResultService.ResultType.NUMERIC.matches(resultType)) {
        	  catalog.setSignificantDigits(testService.getPossibleTestResults().get(0).getSignificantDigits());
        	  catalog.setHasLimitValues(true);
        	  catalog.setResultLimits(getResultLimits(test, catalog.getSignificantDigits()));
          }
          catalog.setHasDictionaryValues(TypeOfTestResultService.ResultType.isDictionaryVariant(catalog.getResultType()));
          if( catalog.isHasDictionaryValues()){
        	  catalog.setDictionaryValues(createDictionaryValues(testService));
        	  catalog.setReferenceValue(createReferenceValueForDictionaryType(test));
          }
          catalogList.add(catalog);
      }
      
      Collections.sort(catalogList, new Comparator<TestCatalog>() {
          @Override
          public int compare(TestCatalog o1, TestCatalog o2) {
              //sort by test section, sample type, panel, sort order
              int comparison = o1.getTestUnit().compareTo(o2.getTestUnit());
              if (comparison != 0) {
                  return comparison;
              }

              comparison = o1.getSampleType().compareTo(o2.getSampleType());
              if (comparison != 0) {
                  return comparison;
              }

              comparison = o1.getPanel().compareTo(o2.getPanel());
              if (comparison != 0) {
                  return comparison;
              }

              return o1.getTestSortOrder() - o2.getTestSortOrder();
          }
      });           
      
      return catalogList;
  }
      
      private String createReferenceValueForDictionaryType(Test test) {
          List<ResultLimit> resultLimits = ResultLimitService.getResultLimits(test);

          if( resultLimits.isEmpty() ){
              return "n/a";
          }

          return ResultLimitService.getDisplayReferenceRange(resultLimits.get(0),null, null);

      }

      private List<String> createDictionaryValues(TestService testService) {
          List<String> dictionaryList = new ArrayList<String>();
          List<TestResult> testResultList = testService.getPossibleTestResults();
          for( TestResult testResult : testResultList){
              CollectionUtils.addIgnoreNull(dictionaryList, getDictionaryValue(testResult));
          }

          return dictionaryList;
      }
      private DictionaryDAO dictionaryDAO = new DictionaryDAOImpl();
      private String getDictionaryValue(TestResult testResult) {

          if (TypeOfTestResultService.ResultType.isDictionaryVariant(testResult.getTestResultType())) {
              Dictionary dictionary = dictionaryDAO.getDataForId(testResult.getValue());
              String displayValue = dictionary.getLocalizedName();

              if ("unknown".equals(displayValue)) {
                  displayValue = !GenericValidator.isBlankOrNull(dictionary.getDictEntry()) ?
                          dictionary.getDictEntry() : dictionary.getLocalAbbreviation();
              }

              if (testResult.getIsQuantifiable()) {
                  displayValue += " Qualifiable";
              }
              return displayValue;
          }

          return null;
      }
      
      private List<ResultLimitBean> getResultLimits(Test test, String significantDigits) {
          List<ResultLimitBean> limitBeans = new ArrayList<ResultLimitBean>();

          List<ResultLimit> resultLimitList = ResultLimitService.getResultLimits(test);

          Collections.sort(resultLimitList, new Comparator<ResultLimit>() {
              @Override
              public int compare(ResultLimit o1, ResultLimit o2) {
                  return (int) (o1.getMinAge() - o2.getMinAge());
              }
          });

          for( ResultLimit limit : resultLimitList){
              ResultLimitBean bean = new ResultLimitBean();
              bean.setNormalRange(ResultLimitService.getDisplayReferenceRange(limit, significantDigits, "-"));
              bean.setValidRange(ResultLimitService.getDisplayValidRange(limit, significantDigits, "-"));
              bean.setGender(limit.getGender());
              bean.setAgeRange( ResultLimitService.getDisplayAgeRange(limit, "-"));
              limitBeans.add(bean);
          }
          return limitBeans;
      }
      
      private String createPanelList(TestService testService) {
          StringBuilder builder = new StringBuilder();

          List<Panel> panelList = testService.getPanels();
          for(Panel panel : panelList){
              builder.append(LocalizationService.getLocalizedValueById(panel.getLocalization().getId()));
              builder.append(", ");
          }

          String panelString = builder.toString();
          if( panelString.isEmpty()){
              panelString = "None";
          }else{
              panelString = panelString.substring(0, panelString.length() - 2 );
          }

          return panelString;
      }


}
