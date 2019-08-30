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

package org.openelisglobal.testconfiguration.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import org.openelisglobal.common.action.BaseAction;
import org.openelisglobal.common.services.DisplayListService;
import org.openelisglobal.common.services.DisplayListService.ListType;
import org.openelisglobal.resultlimit.service.ResultLimitServiceImpl;
import org.openelisglobal.typeoftestresult.service.TypeOfTestResultServiceImpl;
import org.openelisglobal.common.util.IdValuePair;
import org.openelisglobal.common.util.validator.GenericValidator;
import org.openelisglobal.dictionary.dao.DictionaryDAO;
import org.openelisglobal.dictionary.daoimpl.DictionaryDAOImpl;
import org.openelisglobal.dictionary.valueholder.Dictionary;
import org.openelisglobal.testresult.daoimpl.TestResultDAOImpl;
import org.openelisglobal.testresult.valueholder.TestResult;

public class TestAddAction extends BaseAction {

    @Override
    protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
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

//        System.out.println("TestAddAction:performAction");

        return mapping.findForward(FWD_SUCCESS);
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

    @Override
    protected String getPageTitleKey() {
        return null;
    }

    @Override
    protected String getPageSubtitleKey() {
        return null;
    }
}
