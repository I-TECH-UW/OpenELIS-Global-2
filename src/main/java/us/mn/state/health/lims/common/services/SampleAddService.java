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
package us.mn.state.health.lims.common.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.validator.GenericValidator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import us.mn.state.health.lims.common.formfields.FormFields;
import us.mn.state.health.lims.common.formfields.FormFields.Field;
import us.mn.state.health.lims.common.services.StatusService.SampleStatus;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory;
import us.mn.state.health.lims.observationhistorytype.dao.ObservationHistoryTypeDAO;
import us.mn.state.health.lims.observationhistorytype.daoImpl.ObservationHistoryTypeDAOImpl;
import us.mn.state.health.lims.observationhistorytype.valueholder.ObservationHistoryType;
import us.mn.state.health.lims.panel.dao.PanelDAO;
import us.mn.state.health.lims.panel.daoimpl.PanelDAOImpl;
import us.mn.state.health.lims.panel.valueholder.Panel;
import us.mn.state.health.lims.panelitem.dao.PanelItemDAO;
import us.mn.state.health.lims.panelitem.daoimpl.PanelItemDAOImpl;
import us.mn.state.health.lims.panelitem.valueholder.PanelItem;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.typeofsample.dao.TypeOfSampleDAO;
import us.mn.state.health.lims.typeofsample.daoimpl.TypeOfSampleDAOImpl;

public class SampleAddService {
	private final String xml;
	private final String currentUserId;
	private final Sample sample;
	private final List<SampleTestCollection> sampleItemsTests = new ArrayList<SampleTestCollection>();
	private final String receivedDate;
	private final Map<String, Panel> panelIdPanelMap = new HashMap<String, Panel>();
	private boolean xmlProcessed = false;
	private int sampleItemIdIndex = 0;
	private static final boolean USE_INITIAL_SAMPLE_CONDITION = FormFields.getInstance().useField(Field.InitialSampleCondition);
	private static final boolean USE_RECEIVE_DATE_FOR_COLLECTION_DATE = !FormFields.getInstance().useField(Field.CollectionDate);
	private static final String INITIAL_CONDITION_OBSERVATION_ID;
	private static final TypeOfSampleDAO typeOfSampleDAO = new TypeOfSampleDAOImpl();
	private static final PanelDAO panelDAO = new PanelDAOImpl();
	private static final PanelItemDAO panelItemDAO = new PanelItemDAOImpl();
	
	static{
		ObservationHistoryTypeDAO ohtDAO = new ObservationHistoryTypeDAOImpl();

		INITIAL_CONDITION_OBSERVATION_ID = getObservationHistoryTypeId(ohtDAO, "initialSampleCondition");
	}

	private static String getObservationHistoryTypeId(ObservationHistoryTypeDAO ohtDAO, String name) {
		ObservationHistoryType oht;
		oht = ohtDAO.getByName(name);
		if (oht != null) {
			return oht.getId();
		}

		return null;
	}

	public SampleAddService(String xml, String currentUserId, Sample sample, String receiveDate) {
		this.xml = xml;
		this.currentUserId = currentUserId;
		this.sample = sample;
		this.receivedDate = receiveDate;
	}

	public List<SampleTestCollection> createSampleTestCollection() {
		xmlProcessed = true;
		String collectionDateFromRecieveDate = null;
		if (USE_RECEIVE_DATE_FOR_COLLECTION_DATE) {
			collectionDateFromRecieveDate = receivedDate + " 00:00:00";
		}
		
		try {
			Document sampleDom = DocumentHelper.parseText(xml);

			for (@SuppressWarnings("rawtypes")
			Iterator i = sampleDom.getRootElement().elementIterator("sample"); i.hasNext();) {
				sampleItemIdIndex++;

				Element sampleItem = (Element) i.next();

				String testIDs = sampleItem.attributeValue("tests");
				String panelIDs = sampleItem.attributeValue("panels");
				Map<String, String> testIdToUserSectionMap = getTestIdToSelectionMap(sampleItem.attributeValue("testSectionMap"));
                Map<String, String> testIdToSampleTypeMap = getTestIdToSelectionMap( sampleItem.attributeValue( "testSampleTypeMap" ) );
				
				String collectionDate = sampleItem.attributeValue("date").trim();
				String collectionTime = sampleItem.attributeValue("time").trim();
				String collectionDateTime = null;
				
				if (!GenericValidator.isBlankOrNull(collectionDate) && !GenericValidator.isBlankOrNull(collectionTime))
				    collectionDateTime = collectionDate + " " + collectionTime;
				else if (!GenericValidator.isBlankOrNull(collectionDate) && GenericValidator.isBlankOrNull(collectionTime))
				    collectionDateTime = collectionDate + " 00:00";

				augmentPanelIdToPanelMap(panelIDs);
				List<ObservationHistory> initialConditionList = null;

				if (USE_INITIAL_SAMPLE_CONDITION) {
					initialConditionList = addInitialSampleConditions(sampleItem, initialConditionList);
				}

				SampleItem item = new SampleItem();
				item.setSysUserId(currentUserId);
				item.setSample(sample);
				item.setTypeOfSample(typeOfSampleDAO.getTypeOfSampleById(sampleItem.attributeValue("sampleID")));
				item.setSortOrder(Integer.toString(sampleItemIdIndex));
				item.setStatusId(StatusService.getInstance().getStatusID(SampleStatus.Entered));
				item.setCollector(sampleItem.attributeValue("collector"));
				
				if (!GenericValidator.isBlankOrNull(collectionDateTime))
				    item.setCollectionDate(DateUtil.convertStringDateToTimestamp(collectionDateTime));
				List<Test> tests = new ArrayList<Test>();

				addTests(testIDs, tests);

				sampleItemsTests.add(new SampleTestCollection(item,	tests,
															  USE_RECEIVE_DATE_FOR_COLLECTION_DATE ? collectionDateFromRecieveDate : collectionDateTime,
															  initialConditionList, testIdToUserSectionMap, testIdToSampleTypeMap));

			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		return sampleItemsTests;
	}

    public Panel getPanelForTest(Test test) throws IllegalThreadStateException{
		if( !xmlProcessed){
			throw new IllegalThreadStateException("createSampleTestCollection must be called first");
		}	
		
		List<PanelItem> panelItems = panelItemDAO.getPanelItemByTestId( test.getId());
		
		for( PanelItem panelItem : panelItems){
			Panel panel = panelIdPanelMap.get(panelItem.getPanel().getId());
			if( panel != null){
				return panel;
			}
		}

		return null;
	}

	public void setInitialSampleItemOrderValue(int initialValue ){
		sampleItemIdIndex = initialValue;
	}
	
	private Map<String, String> getTestIdToSelectionMap(String mapPairs) {
		Map<String, String> sectionMap = new HashMap<String, String>();

		String[] maps = mapPairs.split(",");
		for (String map : maps) {
			String[] mapping = map.split(":");
			if (mapping.length == 2) {
				sectionMap.put(mapping[0].trim(), mapping[1].trim());
			}
		}

		return sectionMap;
	}

    private void augmentPanelIdToPanelMap(String panelIDs) {
		if(panelIDs != null){
			String[] ids = panelIDs.split(",");
			for( String id : ids){
				if( !GenericValidator.isBlankOrNull(id)){
					panelIdPanelMap.put(id, panelDAO.getPanelById(id));
				}
			}
		}
	}
	
	private List<ObservationHistory> addInitialSampleConditions(Element sampleItem, List<ObservationHistory> initialConditionList) {
		String initialSampleConditionIdString = sampleItem.attributeValue("initialConditionIds");
		if (!GenericValidator.isBlankOrNull(initialSampleConditionIdString)) {
			String[] initialSampleConditionIds = initialSampleConditionIdString.split(",");
			initialConditionList = new ArrayList<ObservationHistory>();

			for (int j = 0; j < initialSampleConditionIds.length; ++j) {
				ObservationHistory initialSampleConditions = new ObservationHistory();
				initialSampleConditions.setValue(initialSampleConditionIds[j]);
				initialSampleConditions.setValueType(ObservationHistory.ValueType.DICTIONARY);
				initialSampleConditions.setObservationHistoryTypeId(INITIAL_CONDITION_OBSERVATION_ID);
				initialConditionList.add(initialSampleConditions);
			}
		}
		return initialConditionList;
	}
	
	private void addTests(String testIDs, List<Test> tests) {
		StringTokenizer tokenizer = new StringTokenizer(testIDs, ",");

		while (tokenizer.hasMoreTokens()) {
			Test test = new Test();
			test.setId(tokenizer.nextToken().trim());
			tests.add(test);
		}
	}
	
	public final class SampleTestCollection {
		public SampleItem item;
		public List<Test> tests;
		public String collectionDate;
		public List<ObservationHistory> initialSampleConditionIdList;
		public Map<String,String> testIdToUserSectionMap;
        public Map<String,String> testIdToUserSampleTypeMap;

		public SampleTestCollection(SampleItem item, List<Test> tests, String collectionDate, List<ObservationHistory> initialConditionList, Map<String,String> testIdToUserSectionMap, Map<String,String> testIdToUserSampleTypeMap) {
			this.item = item;
			this.tests = tests;
			this.collectionDate = collectionDate;
			this.testIdToUserSectionMap = testIdToUserSectionMap;
            this.testIdToUserSampleTypeMap = testIdToUserSampleTypeMap;
			initialSampleConditionIdList = initialConditionList;
			
		}
	}
}
