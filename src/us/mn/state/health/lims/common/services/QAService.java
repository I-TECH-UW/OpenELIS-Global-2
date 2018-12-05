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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.validator.GenericValidator;

import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.common.services.StatusService.OrderStatus;
import us.mn.state.health.lims.common.util.DAOImplFactory;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.dictionary.dao.DictionaryDAO;
import us.mn.state.health.lims.dictionary.daoimpl.DictionaryDAOImpl;
import us.mn.state.health.lims.qaevent.dao.QaEventDAO;
import us.mn.state.health.lims.qaevent.dao.QaObservationDAO;
import us.mn.state.health.lims.qaevent.daoimpl.QaEventDAOImpl;
import us.mn.state.health.lims.qaevent.daoimpl.QaObservationDAOImpl;
import us.mn.state.health.lims.qaevent.daoimpl.QaObservationTypeDAOImpl;
import us.mn.state.health.lims.qaevent.valueholder.QaEvent;
import us.mn.state.health.lims.qaevent.valueholder.QaObservation;
import us.mn.state.health.lims.qaevent.valueholder.QaObservation.ObservedType;
import us.mn.state.health.lims.qaevent.valueholder.QaObservationType;
import us.mn.state.health.lims.referencetables.dao.ReferenceTablesDAO;
import us.mn.state.health.lims.referencetables.valueholder.ReferenceTables;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;
import us.mn.state.health.lims.sampleqaevent.dao.SampleQaEventDAO;
import us.mn.state.health.lims.sampleqaevent.daoimpl.SampleQaEventDAOImpl;
import us.mn.state.health.lims.sampleqaevent.valueholder.SampleQaEvent;


public class QAService {
	public static final String TABLE_REFERENCE_ID;
	public static final String NOTE_TYPE = "I";
	public static final String NOTE_SUBJECT = "QaEvent Note";
	private final QaObservationDAO observationDAO = new QaObservationDAOImpl();
	private final DictionaryDAO dictDAO = new DictionaryDAOImpl();
	private final QaEventDAO qaEventDAO = new QaEventDAOImpl();
	private static final SampleQaEventDAO sampleQaEventDAO = new SampleQaEventDAOImpl();

	private final SampleQaEvent sampleQaEvent;
	private final List<QaObservation> observationList = new ArrayList<QaObservation>();
	private String currentUserId = null;

	public enum QAObservationType {
		AUTHORIZER("authorizer"), SECTION("section"), DOC_NUMBER("documentNumber");

		private final String dbName;
		private QaObservationType type;

		QAObservationType(String dbName) {
			this.dbName = dbName;
		}

		QaObservationType getType() {
			if (type == null) {
				type = new QaObservationTypeDAOImpl().getQaObservationTypeByName(getDBName());
			}

			return type;
		}

		String getDBName() {
			return dbName;
		}
	}

	public enum QAObservationValueType {
		LITERAL("L"), DICTIONARY("D"), KEY("K");

		private final String dbSymbol;

		QAObservationValueType(String symbol) {
			this.dbSymbol = symbol;
		}

		String getDBSymbol() {
			return dbSymbol;
		}
	}

	static {
		ReferenceTablesDAO rtDAO = DAOImplFactory.getInstance().getReferenceTablesDAOImpl();
		ReferenceTables referenceTable = new ReferenceTables();

		referenceTable.setTableName("SAMPLE_QAEVENT");
		referenceTable = rtDAO.getReferenceTableByName(referenceTable);
		TABLE_REFERENCE_ID = referenceTable.getId();

	}

	public QAService(SampleQaEvent event) {
		sampleQaEvent = event;
	}

	public void setCurrentUserId(String currentUserId) {
		this.currentUserId = currentUserId;
		sampleQaEvent.setSysUserId(currentUserId);
		for (QaObservation observation : observationList) {
			observation.setSysUserId(currentUserId);
		}

	}

	public String getEventId() {
		return sampleQaEvent.getId();
	}

	public SampleItem getSampleItem() {
		return sampleQaEvent.getSampleItem();
	}

	public String getObservationValue( QAObservationType section ) {
		QaObservation observation = observationDAO.getQaObservationByTypeAndObserved(section.getDBName(), "SAMPLE", sampleQaEvent.getId());
        return observation == null ? null : observation.getValue();
	}

    public String getObservationForDisplay(QAObservationType section){
        QaObservation observation = observationDAO.getQaObservationByTypeAndObserved(section.getDBName(), "SAMPLE", sampleQaEvent.getId());

        if (observation != null) {
            if( "K".equals( observation.getValueType() )){
                return StringUtil.getContextualMessageForKey(observation.getValue()) ;
            }else if ("L".equals(observation.getValueType())) {
                return observation.getValue();
            } else if ("D".equals(observation.getValueType())) {
                return dictDAO.getDictionaryById(observation.getValue()).getDictEntry();
            }
        }

        return null;
    }
	public Timestamp getLastupdated() {
		return sampleQaEvent.getLastupdated();
	}

	public QaEvent getQAEvent() {
		return sampleQaEvent.getQaEvent();
	}

	public void setQaEventById(String qaEventId) {
		QaEvent event = new QaEvent();
		event.setId(qaEventId);
		qaEventDAO.getData(event);
		sampleQaEvent.setQaEvent(event);
	}

	public void setObservation(QAObservationType observationType, String value, QAObservationValueType type, boolean rejectEmptyValues) {
		if( rejectEmptyValues && 
		    ((type == QAObservationValueType.DICTIONARY && "O".equals(value)) || GenericValidator.isBlankOrNull(value))){
			return;
		}
		
		QaObservation observation = null;

		if (sampleQaEvent.getId() != null) {
			observation = observationDAO.getQaObservationByTypeAndObserved(observationType.getDBName(), ObservedType.SAMPLE.getDBName(), sampleQaEvent.getId());
		}

		if (observation == null) {
			observation = new QaObservation();
			observation.setObservationType(observationType.getType());
			// id may be null at this point
			observation.setObservedId(sampleQaEvent.getId()); 
			observation.setObservedType(ObservedType.SAMPLE.getDBName());
			observation.setValueType(type.getDBSymbol());
		}

		observation.setValue(value);
		observation.setSysUserId(currentUserId);
		observationList.add(observation);
	}

	public void setSampleItem(SampleItem sampleItem) {
		sampleQaEvent.setSampleItem(sampleItem);
	}

	public SampleQaEvent getSampleQaEvent() {
		return sampleQaEvent;
	}

	public List<QaObservation> getUpdatedObservations() {
		return observationList;
	}

	public void setReportTime(String enteredDate) {
		sampleQaEvent.setEnteredDate(DateUtil.convertStringDateToTimestamp( enteredDate));
	}

	public static boolean isAnalysisParentNonConforming(Analysis analysis) {
		SampleItem sampleItem = analysis.getSampleItem();
		
		if( sampleItem != null){
			boolean nonconforming = nonconformingByDepricatedStatus(sampleItem.getSample(), analysis);
			
			if( !nonconforming){
				nonconforming = hasOrderOnlyQaEventOrSampleQaEvent(sampleItem);
			}		
			return nonconforming;
		}
		
		return false;
	}


	public static boolean isOrderNonConforming(Sample sample) {
		if( sample != null){
			boolean nonconforming = nonconformingByDepricatedStatus(sample);
			
			if( !nonconforming){
				nonconforming = hasOrderSampleQaEvent( sample);
			}
			
			return nonconforming;
			
		}
		
		
		return false;
	}

	private static boolean nonconformingByDepricatedStatus(Sample sample, Analysis analysis) {
		
		return nonconformingByDepricatedStatus(sample) ||
			   analysis.getStatusId().equals(StatusService.getInstance().getStatusID(AnalysisStatus.NonConforming_depricated) );
	}

	private static boolean nonconformingByDepricatedStatus(Sample sample) {
		return sample.getStatusId().equals(StatusService.getInstance().getStatusID(OrderStatus.NonConforming_depricated) );
	}
	
	private static boolean hasOrderSampleQaEvent(Sample sample) {
		return !sampleQaEventDAO.getSampleQaEventsBySample(sample).isEmpty();
	}
	
	private static boolean hasOrderOnlyQaEventOrSampleQaEvent(SampleItem sampleItem) {
		List<SampleQaEvent> sampleQaEvents =  sampleQaEventDAO.getSampleQaEventsBySample(sampleItem.getSample());
		
		boolean sampleItemLabeled = false;
		for( SampleQaEvent sampleEvent : sampleQaEvents){
			if( sampleEvent.getSampleItem() != null ){
				sampleItemLabeled = true;
				if( sampleEvent.getSampleItem().getId().equals(sampleItem.getId())){
					return true;
				}
			}
		}
		
		//Return true is there was something matching in the table and
		// there was no sampleItem tagged that was not the one
		//we care about( in which case it would have returned before here)
		return !sampleQaEvents.isEmpty() && !sampleItemLabeled;  
	}

	public static List<SampleItem> getNonConformingSampleItems(Sample sample) {
		List<SampleItem> nonConformingSampleItems = new ArrayList<SampleItem>();	
		List<SampleQaEvent> sampleQaEvents =  sampleQaEventDAO.getSampleQaEventsBySample(sample);
		
		for( SampleQaEvent sampleEvent : sampleQaEvents ){
			if( sampleEvent.getSampleItem() != null){
				nonConformingSampleItems.add(sampleEvent.getSampleItem());
			}
		}
		
		return nonConformingSampleItems;
	}
}
