/*
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations under
 * the License.
 *
 * The Original Code is OpenELIS code.
 *
 * Copyright (C) ITECH, University of Washington, Seattle WA.  All Rights Reserved.
 */
package us.mn.state.health.lims.testconfiguration.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.DisplayListService.ListType;
import spring.service.localization.LocalizationServiceImpl;
import spring.service.resultlimit.ResultLimitServiceImpl;
import us.mn.state.health.lims.common.services.TestService;
import spring.service.typeoftestresult.TypeOfTestResultServiceImpl;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.common.util.validator.GenericValidator;
import us.mn.state.health.lims.dictionary.dao.DictionaryDAO;
import us.mn.state.health.lims.dictionary.daoimpl.DictionaryDAOImpl;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.panel.valueholder.Panel;
import us.mn.state.health.lims.resultlimits.valueholder.ResultLimit;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.testconfiguration.beans.ResultLimitBean;
import us.mn.state.health.lims.testconfiguration.beans.TestCatalogBean;
import us.mn.state.health.lims.testresult.daoimpl.TestResultDAOImpl;
import us.mn.state.health.lims.testresult.valueholder.TestResult;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;

public class TestModifyAction extends BaseAction {
	private DictionaryDAO dictionaryDAO = new DictionaryDAOImpl();

	protected ActionForward performAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		((DynaValidatorForm)form).initialize(mapping);
        
    	List<IdValuePair> allSampleTypesList = new ArrayList<IdValuePair>();
        allSampleTypesList.addAll(DisplayListService.getInstance().getList(ListType.SAMPLE_TYPE_ACTIVE));
        allSampleTypesList.addAll(DisplayListService.getInstance().getList(ListType.SAMPLE_TYPE_INACTIVE));
        PropertyUtils.setProperty(form, "sampleTypeList", allSampleTypesList);
        PropertyUtils.setProperty(form, "panelList", DisplayListService.getInstance().getList(ListType.PANELS));
        PropertyUtils.setProperty(form, "resultTypeList", DisplayListService.getInstance().getList(ListType.RESULT_TYPE_LOCALIZED));
        PropertyUtils.setProperty(form, "uomList", DisplayListService.getInstance().getList(ListType.UNIT_OF_MEASURE));
        PropertyUtils.setProperty(form, "labUnitList", DisplayListService.getInstance().getList(ListType.TEST_SECTION));
        PropertyUtils.setProperty(form, "ageRangeList", SpringContext.getBean(ResultLimitService.class).getPredefinedAgeRanges());
        PropertyUtils.setProperty(form, "dictionaryList", DisplayListService.getInstance().getList(ListType.DICTIONARY_TEST_RESULTS));
        PropertyUtils.setProperty(form, "groupedDictionaryList", createGroupedDictionaryList());
        PropertyUtils.setProperty( form, "testList", DisplayListService.getInstance().getFreshList( DisplayListService.ListType.ALL_TESTS ) );
        
        // gnr: ALL_TESTS calls getActiveTests, this could be a way to enable maintenance of inactive tests
        // PropertyUtils.setProperty( form, "testListInactive", DisplayListService.getInstance().getList( DisplayListService.ListType.ALL_TESTS_INACTIVE ) );
        
        List<TestCatalogBean> testCatBeanList = createTestCatBeanList();
        PropertyUtils.setProperty(form, "testCatBeanList", testCatBeanList);
        
        
//        System.out.println("TestModifyAction:performAction");

        return mapping.findForward(FWD_SUCCESS);
	}
	
    private List<TestCatalogBean> createTestCatBeanList() {
        List<TestCatalogBean> beanList = new ArrayList<TestCatalogBean>();

        List<Test> testList = new TestDAOImpl().getAllTests(false);

        for( Test test : testList){

            TestCatalogBean bean = new TestCatalogBean();
            TestService testService = new TestService(test);
            String resultType = testService.getResultType();
            bean.setId(test.getId());
            bean.setEnglishName(test.getLocalizedTestName().getEnglish());
            bean.setFrenchName(test.getLocalizedTestName().getFrench());
            bean.setEnglishReportName(test.getLocalizedReportingName().getEnglish());
            bean.setFrenchReportName(test.getLocalizedReportingName().getFrench());
            bean.setTestSortOrder(Integer.parseInt(test.getSortOrder()));
            bean.setTestUnit(testService.getTestSectionName());
            bean.setPanel(createPanelList(testService));
            bean.setResultType(resultType);
            TypeOfSample typeOfSample = testService.getTypeOfSample();
            bean.setSampleType(typeOfSample != null ? typeOfSample.getLocalizedName() : "n/a");
            bean.setOrderable(test.getOrderable() ? "Orderable" : "Not orderable");
            bean.setLoinc(test.getLoinc());
            bean.setActive(test.isActive() ? "Active" : "Not active");
            bean.setUom(testService.getUOM(false));
            if( TypeOfTestResultServiceImpl.ResultType.NUMERIC.matches(resultType)) {
                bean.setSignificantDigits(testService.getPossibleTestResults().get(0).getSignificantDigits());
                bean.setHasLimitValues(true);
                bean.setResultLimits(getResultLimits(test, bean.getSignificantDigits()));
            }
            bean.setHasDictionaryValues(TypeOfTestResultServiceImpl.ResultType.isDictionaryVariant(bean.getResultType()));
            if( bean.isHasDictionaryValues()){
                bean.setDictionaryValues(createDictionaryValues(testService));
                bean.setReferenceValue(createReferenceValueForDictionaryType(test));
                bean.setDictionaryIds(createDictionaryIds(testService));
                bean.setReferenceId(createReferenceIdForDictionaryType(test));
                bean.setReferenceId(getDictionaryIdByDictEntry(bean.getReferenceValue(), bean.getDictionaryIds(), bean.getDictionaryValues()));
            }
            beanList.add(bean);
        }

        Collections.sort(beanList, new Comparator<TestCatalogBean>() {
            @Override
            public int compare(TestCatalogBean o1, TestCatalogBean o2) {
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

        return beanList;
    }

    private List<ResultLimitBean> getResultLimits(Test test, String significantDigits) {
        List<ResultLimitBean> limitBeans = new ArrayList<ResultLimitBean>();

        List<ResultLimit> resultLimitList = SpringContext.getBean(ResultLimitService.class).getResultLimits(test);

        Collections.sort(resultLimitList, new Comparator<ResultLimit>() {
            @Override
            public int compare(ResultLimit o1, ResultLimit o2) {
                return (int) (o1.getMinAge() - o2.getMinAge());
            }
        });

        for( ResultLimit limit : resultLimitList){
            ResultLimitBean bean = new ResultLimitBean();
            bean.setNormalRange(SpringContext.getBean(ResultLimitService.class).getDisplayReferenceRange(limit, significantDigits, "-"));
            bean.setValidRange(SpringContext.getBean(ResultLimitService.class).getDisplayValidRange(limit, significantDigits, "-"));
            bean.setGender(limit.getGender());
            bean.setAgeRange( SpringContext.getBean(ResultLimitService.class).getDisplayAgeRange(limit, "-"));
            limitBeans.add(bean);
        }
        return limitBeans;
    }

    private String createReferenceValueForDictionaryType(Test test) {
        List<ResultLimit> resultLimits = SpringContext.getBean(ResultLimitService.class).getResultLimits(test);

        if( resultLimits.isEmpty() ){
            return "n/a";
        }

        return SpringContext.getBean(ResultLimitService.class).getDisplayReferenceRange(resultLimits.get(0),null, null);

    }

    private List<String> createDictionaryValues(TestService testService) {
        List<String> dictionaryList = new ArrayList<String>();
        List<TestResult> testResultList = testService.getPossibleTestResults();
        for( TestResult testResult : testResultList){
            CollectionUtils.addIgnoreNull(dictionaryList, getDictionaryValue(testResult));
        }

        return dictionaryList;
    }

    private String getDictionaryValue(TestResult testResult) {

        if (TypeOfTestResultServiceImpl.ResultType.isDictionaryVariant(testResult.getTestResultType())) {
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

    private String createReferenceIdForDictionaryType(Test test) {
        List<ResultLimit> resultLimits = SpringContext.getBean(ResultLimitService.class).getResultLimits(test);

        if( resultLimits.isEmpty() ){
            return "n/a";
        }

        return SpringContext.getBean(ResultLimitService.class).getDisplayReferenceRange(resultLimits.get(0),null, null);
    }

    private List<String> createDictionaryIds(TestService testService) {
        List<String> dictionaryList = new ArrayList<String>();
        List<TestResult> testResultList = testService.getPossibleTestResults();
        for( TestResult testResult : testResultList){
            CollectionUtils.addIgnoreNull(dictionaryList, getDictionaryId(testResult));
        }

        return dictionaryList;
    }

    private String getDictionaryIdByDictEntry(String dict_entry, List<String> ids, List<String> values) {
    	
    	if("n/a".equals(dict_entry)) {
    		return null;
    	}
    	
    	for (int i = 0; i < ids.size(); i++ ) {
    		if( values.get(i).equals(dict_entry)) {
    			return ids.get(i);
    		}
    	}
    	
    	return null;
    }
    
    private String getDictionaryId(TestResult testResult) {

        if (TypeOfTestResultServiceImpl.ResultType.isDictionaryVariant(testResult.getTestResultType())) {
            Dictionary dictionary = dictionaryDAO.getDataForId(testResult.getValue());
            String displayId = dictionary.getId();

            if ("unknown".equals(displayId)) {
            	displayId = !GenericValidator.isBlankOrNull(dictionary.getDictEntry()) ?
                        dictionary.getDictEntry() : dictionary.getLocalAbbreviation();
            }

            if (testResult.getIsQuantifiable()) {
            	displayId += " Qualifiable";
            }
            return displayId;
        }

        return null;
    }

    
    
    private String createPanelList(TestService testService) {
        StringBuilder builder = new StringBuilder();

        List<Panel> panelList = testService.getPanels();
        for(Panel panel : panelList){
            builder.append(LocalizationServiceImpl.getLocalizedValueById(panel.getLocalization().getId()));
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
	
    private List<List<IdValuePair>> createGroupedDictionaryList() {
        List<TestResult> testResults = getSortedTestResults();

        HashSet<String> dictionaryIdGroups = getDictionaryIdGroups(testResults);

        return getGroupedDictionaryPairs(dictionaryIdGroups);
    }

    @SuppressWarnings("unchecked")
    private List<TestResult> getSortedTestResults() {
        List<TestResult> testResults = new TestResultDAOImpl().getAllTestResults();

        Collections.sort(testResults, new Comparator<TestResult>() {
            @Override
            public int compare(TestResult o1, TestResult o2) {
                int result = o1.getTest().getId().compareTo(o2.getTest().getId());

                if (result != 0) {
                    return result;
                }

                return GenericValidator.isBlankOrNull(o1.getSortOrder()) ? 0 :Integer.parseInt(o1.getSortOrder()) - Integer.parseInt(o2.getSortOrder());
            }
        });
        return testResults;
    }
    private HashSet<String> getDictionaryIdGroups(List<TestResult> testResults) {
        HashSet< String > dictionaryIdGroups = new HashSet<String>();
        String currentTestId = null;
        String dictionaryIdGroup = null;
        for( TestResult testResult : testResults){
            if(TypeOfTestResultServiceImpl.ResultType.isDictionaryVariant(testResult.getTestResultType()) ){
                if( testResult.getTest().getId().equals(currentTestId) ){
                    dictionaryIdGroup += "," + testResult.getValue();
                }else{
                    currentTestId = testResult.getTest().getId();
                    if( dictionaryIdGroup != null){
                        dictionaryIdGroups.add(dictionaryIdGroup);
                    }

                    dictionaryIdGroup = testResult.getValue();
                }

            }
        }

        if( dictionaryIdGroup != null){
            dictionaryIdGroups.add(dictionaryIdGroup);
        }

        return dictionaryIdGroups;
    }


    private List<List<IdValuePair>> getGroupedDictionaryPairs( HashSet<String> dictionaryIdGroups) {
        List<List<IdValuePair>> groups = new ArrayList<List<IdValuePair>>();
        DictionaryDAO dictionaryDAO = new DictionaryDAOImpl();
        for( String group : dictionaryIdGroups){
            List<IdValuePair> dictionaryPairs = new ArrayList<IdValuePair>();
            for( String id : group.split(",")){
                Dictionary dictionary = dictionaryDAO.getDictionaryById(id);
                if( dictionary != null){
                    dictionaryPairs.add(new IdValuePair(id, dictionary.getLocalizedName()));
                }
            }
            groups.add(dictionaryPairs);
        }

        Collections.sort(groups, new Comparator<List<IdValuePair>>() {
            @Override
            public int compare(List<IdValuePair> o1, List<IdValuePair> o2) {
                return o1.size() - o2.size();
            }
        });
        return groups;
    }

	protected String getPageTitleKey() {
		return "";
	}

	protected String getPageSubtitleKey() {
		return "";
	}

}
