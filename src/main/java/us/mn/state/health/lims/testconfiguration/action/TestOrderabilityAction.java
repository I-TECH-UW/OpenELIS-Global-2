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
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.TestService;
import us.mn.state.health.lims.common.services.TypeOfSampleService;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.test.beanItems.TestActivationBean;
import us.mn.state.health.lims.test.valueholder.Test;

public class TestOrderabilityAction extends BaseAction {
    @Override
    protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PropertyUtils.setProperty(form, "orderableTestList", createTestList());

        return mapping.findForward(FWD_SUCCESS);
    }

    private List<TestActivationBean> createTestList() {
        ArrayList<TestActivationBean> testList = new ArrayList<TestActivationBean>();

        List<IdValuePair> sampleTypeList = DisplayListService.getList( DisplayListService.ListType.SAMPLE_TYPE_ACTIVE );

        for( IdValuePair pair : sampleTypeList){
            TestActivationBean bean = new TestActivationBean();

            List<Test> tests = TypeOfSampleService.getActiveTestsBySampleTypeId(pair.getId(), false);
            List<IdValuePair> orderableTests = new ArrayList<IdValuePair>();
            List<IdValuePair> unorderableTests = new ArrayList<IdValuePair>();

            //initial ordering will be by display order.  Inactive tests will then be re-ordered alphabetically
            Collections.sort(tests, new Comparator<Test>() {
                @Override
                public int compare(Test o1, Test o2) {
                    return Integer.parseInt(o1.getSortOrder()) - Integer.parseInt(o2.getSortOrder());
                }
            });

            for( Test test : tests) {
                if( test.getOrderable()) {
                     orderableTests.add(new IdValuePair(test.getId(), TestService.getUserLocalizedTestName(test)));
                }else{
                    unorderableTests.add(new IdValuePair(test.getId(), TestService.getUserLocalizedTestName(test)));
                }
            }


            bean.setActiveTests( orderableTests);
            bean.setInactiveTests(unorderableTests);
            if( ! orderableTests.isEmpty() || !unorderableTests.isEmpty()) {
                bean.setSampleType(pair);
                testList.add(bean);
            }
        }


        return testList;
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
