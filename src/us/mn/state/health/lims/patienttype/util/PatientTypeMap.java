package us.mn.state.health.lims.patienttype.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.validator.GenericValidator;

import us.mn.state.health.lims.patientidentity.valueholder.PatientIdentity;
import us.mn.state.health.lims.patienttype.dao.PatientTypeDAO;
import us.mn.state.health.lims.patienttype.daoimpl.PatientTypeDAOImpl;
import us.mn.state.health.lims.patienttype.valueholder.PatientType;

public class PatientTypeMap {

	private static PatientTypeMap s_instance = null;
	private Map<String, String> m_map;
	
	
	public static PatientTypeMap getInstance(){
		
		if( s_instance == null){
			s_instance = new PatientTypeMap();
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
	
	@SuppressWarnings("unchecked")
	private PatientTypeMap(){
		m_map = new HashMap<String, String>();
		
		PatientTypeDAO patientTypeDAO = new PatientTypeDAOImpl();

		List<PatientType> patientTypes = patientTypeDAO.getAllPatientTypes();

		for( PatientType patientType : patientTypes){
			m_map.put(patientType.getType(), patientType.getId());
		}
	}
	
	public String getIDForType( String type){
		return m_map.get(type);
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
