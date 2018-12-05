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

import us.mn.state.health.lims.address.daoimpl.AddressPartDAOImpl;
import us.mn.state.health.lims.address.daoimpl.PersonAddressDAOImpl;
import us.mn.state.health.lims.address.valueholder.AddressPart;
import us.mn.state.health.lims.address.valueholder.PersonAddress;
import us.mn.state.health.lims.dictionary.dao.DictionaryDAO;
import us.mn.state.health.lims.dictionary.daoimpl.DictionaryDAOImpl;
import us.mn.state.health.lims.person.valueholder.Person;

public class PersonService {
	
	private static Map<String, String> addressPartIdToNameMap = new HashMap<String, String>();
	private static final DictionaryDAO dictionaryDAO = new DictionaryDAOImpl();
	
	private Person person;
	
	static{
		
		List<AddressPart> parts = new AddressPartDAOImpl().getAll();
		
		for( AddressPart part : parts){
			addressPartIdToNameMap.put(part.getId(), part.getPartName());
		}
	}
	
	public PersonService(Person person){
		this.person = person;
	}
	
	public String getFirstName(){
		return person != null ? person.getFirstName() : "";
	}
	
	public String getLastName(){
		return person != null ? person.getLastName() : "";
	}
	
	public String getLastFirstName(){
		String lastName = getLastName();
		String firstName = getFirstName();
		if( !GenericValidator.isBlankOrNull(lastName) && !GenericValidator.isBlankOrNull(firstName)){
			lastName += ", ";
		}

        lastName += firstName;

		return lastName;
				
	}
	
	public Map<String, String> getAddressComponents(){
		String value;
		Map<String, String> addressMap = new HashMap<String, String>();
		
		if( person == null){
			return addressMap;
		}
		
		List<PersonAddress> addressParts = new PersonAddressDAOImpl().getAddressPartsByPersonId(person.getId());
		
		for( PersonAddress parts : addressParts){
			if( "D".equals(parts.getType()) && !GenericValidator.isBlankOrNull(parts.getValue())){
				addressMap.put(addressPartIdToNameMap.get(parts.getAddressPartId()), dictionaryDAO.getDataForId(parts.getValue()).getDictEntry());
			}else{
				value = parts.getValue();
				addressMap.put(addressPartIdToNameMap.get(parts.getAddressPartId()), value == null ? "" : value.trim());
			}
		}
		
		value = person.getCity();
		addressMap.put("City", value == null ? "" : value.trim());
		value = person.getCountry();
		addressMap.put("Country", value == null ? "" : value.trim());
		value = person.getState();
		addressMap.put("State", value == null ? "" : value.trim());
		value = person.getStreetAddress();
		addressMap.put("Street", value == null ? "" : value.trim());
		value = person.getZipCode();
		addressMap.put("Zip", value == null ? "" : value.trim());
		
		return addressMap;
	}
		
	public String getPhone(){
		if( person == null){
			return "";
		}
		
		String phone = person.getHomePhone();
		
		if(GenericValidator.isBlankOrNull(phone)){
			phone = person.getCellPhone();
		}
		
		if(GenericValidator.isBlankOrNull(phone)){
			phone = person.getWorkPhone();
		}
		
		return phone;
	}

    public String getWorkPhone(){
        return person.getWorkPhone();
    }

    public String getCellPhone(){
        return person.getCellPhone();
    }

    public String getHomePhone(){
        return person.getHomePhone();
    }

    public String getFax(){
        return person.getFax();
    }

    public String getEmail(){
        return person.getEmail();
    }
	public Person getPerson(){
		return person;
	}
}
