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
package us.mn.state.health.lims.analyzerresults.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.DynaActionForm;

import us.mn.state.health.lims.analyzerresults.action.beanitems.AnalyzerResultItem;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.paging.IPageDivider;
import us.mn.state.health.lims.common.paging.IPageFlattener;
import us.mn.state.health.lims.common.paging.IPageUpdater;
import us.mn.state.health.lims.common.paging.PagingBean;
import us.mn.state.health.lims.common.paging.PagingUtility;
import us.mn.state.health.lims.common.util.IdValuePair;

public class AnalyzerResultsPaging {

	private PagingUtility<List<AnalyzerResultItem>> paging = new PagingUtility<List<AnalyzerResultItem>>();
	private static TestItemPageHelper pagingHelper = new TestItemPageHelper();

	public void setDatabaseResults(HttpServletRequest request, DynaActionForm dynaForm, List<AnalyzerResultItem> tests)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		paging.setDatabaseResults(request.getSession(), tests, pagingHelper);

		List<AnalyzerResultItem> resultPage = paging.getPage(1, request.getSession());
		if (resultPage != null) {
			PropertyUtils.setProperty(dynaForm, "resultList", resultPage);
			PropertyUtils.setProperty(dynaForm, "paging", paging.getPagingBeanWithSearchMapping(1, request.getSession()));
		}
	}

	@SuppressWarnings("unchecked")
	public void page(HttpServletRequest request, DynaActionForm dynaForm, String newPage) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		request.getSession().setAttribute(IActionConstants.SAVE_DISABLED, IActionConstants.FALSE);
		List<AnalyzerResultItem> clientTests = (List<AnalyzerResultItem>) dynaForm.get("resultList");
		PagingBean bean = (PagingBean) dynaForm.get("paging");

		paging.updatePagedResults(request.getSession(), clientTests, bean, pagingHelper);

		int page = Integer.parseInt(newPage);

		List<AnalyzerResultItem> resultPage = paging.getPage(page, request.getSession());
		if (resultPage != null) {
			PropertyUtils.setProperty(dynaForm, "resultList", resultPage);
			PropertyUtils.setProperty(dynaForm, "paging", paging.getPagingBeanWithSearchMapping(page, request.getSession()));
		}
	}

	@SuppressWarnings("unchecked")
	public void updatePagedResults(HttpServletRequest request, DynaActionForm dynaForm) {
		List<AnalyzerResultItem> clientTests = (List<AnalyzerResultItem>) dynaForm.get("resultList");
		PagingBean bean = (PagingBean) dynaForm.get("paging");

		paging.updatePagedResults(request.getSession(), clientTests, bean, pagingHelper);
	}

	public List<AnalyzerResultItem> getResults(HttpServletRequest request) {
		return paging.getAllResults(request.getSession(), pagingHelper);
	}

	public void setEmptyPageBean(HttpServletRequest request, DynaActionForm dynaForm) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		PropertyUtils.setProperty(dynaForm, "paging", paging.getPagingBeanWithSearchMapping(0, request.getSession()));
	}

	private static class TestItemPageHelper implements IPageDivider<List<AnalyzerResultItem>>, IPageUpdater<List<AnalyzerResultItem>>,
			IPageFlattener<List<AnalyzerResultItem>> {

		public void createPages(List<AnalyzerResultItem> tests, List<List<AnalyzerResultItem>> pagedResults) {
			List<AnalyzerResultItem> page = new ArrayList<AnalyzerResultItem>();

			int sampleGroupingNumber = -1;
			int resultCount = 0;

			for (AnalyzerResultItem item : tests) {
				if (sampleGroupingNumber != -1 && sampleGroupingNumber != item.getSampleGroupingNumber()) {
					resultCount = 0;
					sampleGroupingNumber = -1;
					pagedResults.add(page);
					page = new ArrayList<AnalyzerResultItem>();
				}
				if (resultCount >= IActionConstants.PAGING_SIZE) {
					sampleGroupingNumber = item.getSampleGroupingNumber();
				}

				page.add(item);
				resultCount++;
			}

			if (!page.isEmpty() || pagedResults.isEmpty()) {
				pagedResults.add(page);
			}
		}

		public void updateCache(List<AnalyzerResultItem> cacheItems, List<AnalyzerResultItem> clientItems) {
			for (int i = 0; i < clientItems.size(); i++) {
				cacheItems.set(i, clientItems.get(i));
			}

		}

		public List<AnalyzerResultItem> flattenPages(List<List<AnalyzerResultItem>> pages) {

			List<AnalyzerResultItem> allResults = new ArrayList<AnalyzerResultItem>();

			for (List<AnalyzerResultItem> page : pages) {
				for (AnalyzerResultItem item : page) {
					allResults.add(item);
				}
			}
			return allResults;
		}

		@Override
		public List<IdValuePair> createSearchToPageMapping(List<List<AnalyzerResultItem>> allPages) {
			List<IdValuePair> mappingList = new ArrayList<IdValuePair>();

			int page = 0;
			for (List<AnalyzerResultItem> resultList : allPages) {
				page++;
				String pageString = String.valueOf(page);

				String currentAccession = null;

				for (AnalyzerResultItem resultItem : resultList) {
					if (!resultItem.getAccessionNumber().equals(currentAccession)) {
						currentAccession = resultItem.getAccessionNumber();
						mappingList.add(new IdValuePair(currentAccession, pageString));
					}
				}

			}

			return mappingList;
		}
	}
}
