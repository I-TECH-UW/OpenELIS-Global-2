package us.mn.state.health.lims.patientidentitytype.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.validator.GenericValidator;

import us.mn.state.health.lims.patientidentity.valueholder.PatientIdentity;
import us.mn.state.health.lims.patientidentitytype.dao.PatientIdentityTypeDAO;
import us.mn.state.health.lims.patientidentitytype.daoimpl.PatientIdentityTypeDAOImpl;
import us.mn.state.health.lims.patientidentitytype.valueholder.PatientIdentityType;

public class PatientIdentityTypeMap {

	private static PatientIdentityTypeMap s_instance = null;
	private final Map<String, String> m_map;
	
	public static PatientIdentityTypeMap getInstance(){
		
		if( s_instance == null){
			s_instance = new PatientIdentityTypeMap();
		}
		
		return s_instance;
	}
	
	/*
	 * Will force the a new fetch of the map and any new PatientIdentityTypes in the DB will be picked up
	 * 
	 * Expected user will be the code which inserts new types into the DB
	 */
	public static void reset(){
		s_instance = null;
	}
	
	private PatientIdentityTypeMap(){
		m_map = new HashMap<String, String>();
		
		PatientIdentityTypeDAO patientIdentityTypeDAO = new PatientIdentityTypeDAOImpl();
		List<PatientIdentityType> identityList = patientIdentityTypeDAO.getAllPatientIdenityTypes();
		
		for( PatientIdentityType patientIdentityType : identityList){
			m_map.put(patientIdentityType.getIdentityType(), patientIdentityType.getId());
		}
	}
	
	public String getIDForType( String type){
		if( GenericValidator.isBlankOrNull(type)){
			return null;
		}
		
		String upperType = type.toUpperCase();
		String id = m_map.get(upperType);
		
		if( id == null){
			id = insertNewIdentityType(upperType);
		}
		
		return id;
	}

	private String insertNewIdentityType(String type) {
		String id;
		PatientIdentityType patientIdentityType = new PatientIdentityType();
		patientIdentityType.setIdentityType(type);
		PatientIdentityTypeDAO patientIdentityTypeDAO = new PatientIdentityTypeDAOImpl();
		patientIdentityTypeDAO.insertData(patientIdentityType);
		
		id = patientIdentityType.getId();
		
		if( !GenericValidator.isBlankOrNull(id)){
			m_map.put(type, id);
		}
		
		return id;
	}
	
	public String getIdentityValue(List<PatientIdentity> identityList, String type) {

		String id = getIDForType(type);

		if (!GenericValidator.isBlankOrNull(id)) {
			for (PatientIdentity identity : identityList) {
				if (id.equals(identity.getIdentityTypeId())) {
					return identity.getIdentityData();
				}
			}
		}
		return "";
	}
}
