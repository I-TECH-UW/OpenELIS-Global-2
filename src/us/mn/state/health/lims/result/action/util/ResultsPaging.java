/**
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
* Copyright (C) CIRG, University of Washington, Seattle WA.  All Rights Reserved.
*
*/
package us.mn.state.health.lims.result.action.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.DynaActionForm;

import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.paging.IPageDivider;
import us.mn.state.health.lims.common.paging.IPageFlattener;
import us.mn.state.health.lims.common.paging.IPageUpdater;
import us.mn.state.health.lims.common.paging.PagingBean;
import us.mn.state.health.lims.common.paging.PagingUtility;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.test.beanItems.TestResultItem;

public class ResultsPaging {
	private PagingUtility<List<TestResultItem>> paging = new PagingUtility<List<TestResultItem>>();
	
	private static TestItemPageHelper pagingHelper = new TestItemPageHelper();

	public void setDatabaseResults(HttpServletRequest request, DynaActionForm dynaForm, List<TestResultItem> tests)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		paging.setDatabaseResults(request.getSession(), tests, pagingHelper);
		
		List<TestResultItem> resultPage = paging.getPage(1, request.getSession());
		if (resultPage != null) {
			PropertyUtils.setProperty(dynaForm, "testResult", resultPage);
			PropertyUtils.setProperty(dynaForm, "paging", paging.getPagingBeanWithSearchMapping(1, request.getSession()));
		}
	}

	@SuppressWarnings("unchecked")
	public void page(HttpServletRequest request, DynaActionForm dynaForm, String newPage) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		request.getSession().setAttribute(IActionConstants.SAVE_DISABLED, IActionConstants.FALSE);
		List<TestResultItem> clientTests = (List<TestResultItem>) dynaForm.get("testResult");
		PagingBean bean = (PagingBean) dynaForm.get("paging");
		String testSectionId = (request.getParameter("testSectionId"));
		paging.updatePagedResults(request.getSession(), clientTests, bean, pagingHelper);

		int page = Integer.parseInt(newPage);

		List<TestResultItem> resultPage = paging.getPage(page, request.getSession());
		if (resultPage != null) {
			PropertyUtils.setProperty(dynaForm, "testResult", resultPage);
			PropertyUtils.setProperty(dynaForm, "testSectionId", "0");
			PropertyUtils.setProperty(dynaForm, "paging", paging.getPagingBeanWithSearchMapping(page, request.getSession()));
		}
		
	}

	@SuppressWarnings("unchecked")
	public void updatePagedResults(HttpServletRequest request, DynaActionForm dynaForm) {
		List<TestResultItem> clientTests = (List<TestResultItem>) dynaForm.get("testResult");
		PagingBean bean = (PagingBean) dynaForm.get("paging");

		paging.updatePagedResults(request.getSession(), clientTests, bean, pagingHelper);
	}

	public List<TestResultItem> getResults(HttpServletRequest request) {
		return paging.getAllResults(request.getSession(), pagingHelper);
	}

	private static class TestItemPageHelper implements IPageDivider<List<TestResultItem>>, IPageUpdater<List<TestResultItem>>,
			IPageFlattener<List<TestResultItem>> {

		public void createPages(List<TestResultItem> tests, List<List<TestResultItem>> pagedResults) {
			List<TestResultItem> page = new ArrayList<TestResultItem>();

			String accessionSequenceNumber = null;
			int resultCount = 0;

			for (TestResultItem item : tests) {
				if (accessionSequenceNumber != null && !accessionSequenceNumber.equals(item.getSequenceAccessionNumber())) {
					resultCount = 0;
					accessionSequenceNumber = null;
					pagedResults.add(page);
					page = new ArrayList<TestResultItem>();
				}
				if (resultCount >= IActionConstants.PAGING_SIZE) {
					accessionSequenceNumber = item.getSequenceAccessionNumber();
				}

				page.add(item);
				resultCount++;
			}

			if (!page.isEmpty() || pagedResults.isEmpty()) {
				pagedResults.add(page);
			}
		}

		public void updateCache(List<TestResultItem> cacheItems, List<TestResultItem> clientItems) {
			for (int i = 0; i < clientItems.size(); i++) {
				if (clientItems.get(i).getIsModified()) {
					cacheItems.set(i, clientItems.get(i));
				}
			}

		}

		public List<TestResultItem> flattenPages(List<List<TestResultItem>> pages) {

			List<TestResultItem> allResults = new ArrayList<TestResultItem>();

			for (List<TestResultItem> page : pages) {
				for (TestResultItem item : page) {
					allResults.add(item);
				}
			}

			return allResults;

		}

		public List<IdValuePair> createSearchToPageMapping(List<List<TestResultItem>> allPages) {
			List<IdValuePair> mappingList = new ArrayList<IdValuePair>();
			
			int page = 0;
			for( List<TestResultItem> resultList : allPages){
				page++;
				String pageString = String.valueOf(page);
				
				String currentAccession = null;
				
				for( TestResultItem resultItem : resultList){
					if( !resultItem.getAccessionNumber().equals(currentAccession)){
						currentAccession = resultItem.getAccessionNumber();
						mappingList.add( new IdValuePair(currentAccession, pageString));
					}
				}
				
			}
			
			return mappingList;
		}
	}
}
