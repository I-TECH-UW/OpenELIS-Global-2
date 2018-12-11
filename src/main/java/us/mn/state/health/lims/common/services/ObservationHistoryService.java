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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.validator.GenericValidator;

import us.mn.state.health.lims.dictionary.dao.DictionaryDAO;
import us.mn.state.health.lims.dictionary.daoimpl.DictionaryDAOImpl;
import us.mn.state.health.lims.observationhistory.dao.ObservationHistoryDAO;
import us.mn.state.health.lims.observationhistory.daoimpl.ObservationHistoryDAOImpl;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory.ValueType;
import us.mn.state.health.lims.observationhistorytype.dao.ObservationHistoryTypeDAO;
import us.mn.state.health.lims.observationhistorytype.daoImpl.ObservationHistoryTypeDAOImpl;
import us.mn.state.health.lims.observationhistorytype.valueholder.ObservationHistoryType;

public class ObservationHistoryService{

	private static final ObservationHistoryDAO observationDAO = new ObservationHistoryDAOImpl();
	private static final DictionaryDAO dictionaryDAO = new DictionaryDAOImpl();
	private static final Map<ObservationType, String> observationTypeToIdMap = new HashMap<ObservationType, String>();

    public enum ObservationType{
		INITIAL_SAMPLE_CONDITION("initialSampleCondition"), 
		PAYMENT_STATUS("paymentStatus"), 
		REQUEST_DATE("requestDate"), 
		NEXT_VISIT_DATE("nextVisitDate"), 
		REFERRING_SITE("referringSite"),
		REFERRERS_PATIENT_ID("referrersPatientId"),
        BILLING_REFERENCE_NUMBER("billingRefNumber"),
        TEST_LOCATION_CODE("testLocationCode"),
        TEST_LOCATION_CODE_OTHER("testLocationCodeOther"),
        PROGRAM("program");
		
		private String dbName;

		private ObservationType(String dbName){
			this.dbName = dbName;
		}

		public String getDatabaseName(){
			return dbName;
		}
	}

	public static String getObservationTypeIdForType( ObservationType type ){
		if( observationTypeToIdMap.isEmpty()){
			initialize();
		}
		return observationTypeToIdMap.get(type);
	}

    public static List<ObservationHistory> getObservationsByTypeAndValue(ObservationType type, String value ){
        if( observationTypeToIdMap.isEmpty()){
            initialize();
        }
        String typeId = getObservationTypeIdForType( type );

        if(!GenericValidator.isBlankOrNull(typeId)){
            return observationDAO.getObservationHistoriesByValueAndType( value, typeId, ValueType.LITERAL.getCode() );
        }else{
            return null;
        }
    }
	public static String getValueForSample( ObservationType type, String sampleId ){
		ObservationHistory observation =  getObservationForSample( type, sampleId );
        return getValueForObservation( observation );
	}

    private static String getValueForObservation( ObservationHistory observation ){
        if(observation != null){
            if(observation.getValueType().equals(ObservationHistory.ValueType.LITERAL.getCode())){
                return observation.getValue();
            }else{
                if(!GenericValidator.isBlankOrNull( observation.getValue() )){
                    return dictionaryDAO.getDataForId(observation.getValue()).getLocalizedName();
                }
            }
        }

        return null;
    }

    public static String getMostRecentValueForPatient( ObservationType type, String patientId){
        ObservationHistory observation =  getLastObservationForPatient( type, patientId );
        return getValueForObservation( observation );
    }

    public static String getRawValueForSample( ObservationType type, String sampleId ){
        ObservationHistory observation =  getObservationForSample( type, sampleId );
        return observation != null ? observation.getValue( ) : null;
    }


    public static ObservationHistory getObservationForSample( ObservationType type, String sampleId ){
        if( observationTypeToIdMap.isEmpty()){
            initialize();
        }
        String typeId = getObservationTypeIdForType( type );

        if(!GenericValidator.isBlankOrNull(typeId)){
            return observationDAO.getObservationHistoriesBySampleIdAndType(sampleId, typeId);
        }else{
            return null;
        }

    }

    public static ObservationHistory getLastObservationForPatient( ObservationType type, String patientId ){
        if( observationTypeToIdMap.isEmpty()){
            initialize();
        }

        String typeId = getObservationTypeIdForType( type );

        if(!GenericValidator.isBlankOrNull(typeId)){
            List<ObservationHistory> observationList = observationDAO.getObservationHistoriesByPatientIdAndType ( patientId, typeId);
            if( !observationList.isEmpty()){
                return observationList.get( 0 ); //sorted descending
            }
        }

        return null;
    }
	private static void initialize(){
		ObservationHistoryType oht;
		ObservationHistoryTypeDAO ohtDAO = new ObservationHistoryTypeDAOImpl();

		for(ObservationType type : ObservationType.values()){
			oht = ohtDAO.getByName(type.getDatabaseName());
			if(oht != null){
				observationTypeToIdMap.put( type, oht.getId() );
			}
		}
	}
}
