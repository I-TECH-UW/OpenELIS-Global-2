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
 * Copyright (C) ITECH, University of Washington, Seattle WA.  All Rights Reserved.
 *
 */
package us.mn.state.health.lims.dataexchange.aggregatereporting;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.json.simple.JSONObject;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.services.IResultSaveService;
import us.mn.state.health.lims.common.services.ResultService;
import us.mn.state.health.lims.common.services.registration.interfaces.IResultUpdate;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.dataexchange.aggregatereporting.dao.ReportExternalExportDAO;
import us.mn.state.health.lims.dataexchange.aggregatereporting.daoimpl.ReportExternalExportDAOImpl;
import us.mn.state.health.lims.dataexchange.aggregatereporting.daoimpl.ReportQueueTypeDAOImpl;
import us.mn.state.health.lims.dataexchange.aggregatereporting.valueholder.ReportExternalExport;
import us.mn.state.health.lims.dataexchange.aggregatereporting.valueholder.ReportQueueType;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.result.action.util.ResultSet;
import us.mn.state.health.lims.result.valueholder.Result;

public class TestUsageUpdate implements IResultUpdate {

	private static String TEST_USAGE_TYPE_ID;
	private ReportExternalExportDAO queueDAO = new ReportExternalExportDAOImpl();
	private static ContainerFactory CONTAINER_FACTORY; 
	
	static {
		ReportQueueType queueType = new ReportQueueTypeDAOImpl().getReportQueueTypeByName("labIndicator");
		if (queueType != null) {
			TEST_USAGE_TYPE_ID = queueType.getId();
		}
		
		CONTAINER_FACTORY = new ContainerFactory() {
			@SuppressWarnings("rawtypes")
			public List creatArrayContainer() {
				return new ArrayList();
			}

			public Map<String, Long> createObjectContainer() {
				return new HashMap<String, Long>();
			}

		};

	}

	@Override
	public void transactionalUpdate(IResultSaveService resultService) throws LIMSRuntimeException {
		// TODO Auto-generated method stub

	}

	@Override
	public void postTransactionalCommitUpdate(IResultSaveService resultSaveService) {
		Map<String, Map<String, Integer>> dateTestMap = new HashMap<String, Map<String, Integer>>();
		List<ReportExternalExport> exports = new ArrayList<ReportExternalExport>();
		List<Result> results = getAllResults(resultSaveService);

		createMaps(dateTestMap, results);

		updateData(dateTestMap, exports);

		applyUpdatesToDB(exports);
	}

	private List<Result> getAllResults(IResultSaveService resultSaveService) {
		List<Result> results = new ArrayList<Result>();

		for (ResultSet resultSet : resultSaveService.getNewResults()) {
			results.add(resultSet.result);
		}

		for (ResultSet resultSet : resultSaveService.getModifiedResults()) {
			results.add(resultSet.result);
		}
		return results;
	}
	
	private void createMaps(Map<String, Map<String, Integer>> dateTestMap, List<Result> results) {
		for (Result result : results) {
			ResultService resultService = new ResultService(result);
			String testDate = resultService.getTestTime();
			if( testDate == null){
				testDate = resultService.getLastUpdatedTime();
			}
			Map<String, Integer> testCountMap = dateTestMap.get(testDate);

			if (testCountMap == null) {
				testCountMap = new HashMap<String, Integer>();
				dateTestMap.put(testDate, testCountMap);
			}

			String testDescription = resultService.getTestDescription();
			
			Integer count = testCountMap.get(testDescription);
			testCountMap.put(testDescription, count == null ? 1 : count + 1);
		}
	}

	private void updateData(Map<String, Map<String, Integer>> dateTestMap, List<ReportExternalExport> exports) {
		for (String date : dateTestMap.keySet()) {
			ReportExternalExport export = new ReportExternalExport();
			export.setTypeId(TEST_USAGE_TYPE_ID);
			export.setEventDate(DateUtil.convertStringDateToTruncatedTimestamp(date));
			export = queueDAO.getReportByEventDateAndType(export);

			updateExport(export, dateTestMap.get(date));

			export.setCollectionDate(DateUtil.getNowAsTimestamp());
			
			exports.add(export);
		}
	}

	@SuppressWarnings("unchecked")
	private void updateExport(ReportExternalExport export, Map<String, Integer> testCountMap) {
		JSONParser parser = new JSONParser();
		Map<String, Long> databaseTestCountList = null;

		if( export.getData() == null){
			export.setData("{}");
		}
		
		export.setSend(true);
		
		try {	
			databaseTestCountList = (Map<String, Long>) parser.parse(export.getData().replace("\n", ""), CONTAINER_FACTORY);

			for (String test : testCountMap.keySet()) {
				Long count = databaseTestCountList.get(test);
				databaseTestCountList.put(test, count == null ? 1 : count + testCountMap.get(test));
			}
		} catch (ParseException pe) {
			System.out.println(pe);
		}

		JSONObject json = new JSONObject();
		for (String name : databaseTestCountList.keySet()) {
				json.put(name, databaseTestCountList.get(name));
		}

		StringWriter buffer = new StringWriter();
		try {
			json.writeJSONString(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String data = buffer.toString().replace("\n", "");
		export.setData(data);
	}

	private void applyUpdatesToDB(List<ReportExternalExport> exports) {
		Transaction tx = HibernateUtil.getSession().beginTransaction();

		try {
			for( ReportExternalExport export : exports){
				if( export.getId() == null){
					queueDAO.insertReportExternalExport(export);
				}else{
					queueDAO.updateReportExternalExport(export);
				}
			}
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			LogEvent.logError("TestUsageUpdate", "applyUpdatesToDB", e.toString());
		}
	}
}
