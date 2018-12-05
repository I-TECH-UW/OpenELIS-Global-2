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
* Copyright (C) The Minnesota Department of Health.  All Rights Reserved.
*/
package us.mn.state.health.lims.provider.valueholder;

import us.mn.state.health.lims.common.valueholder.BaseObject;
import us.mn.state.health.lims.common.valueholder.ValueHolder;
import us.mn.state.health.lims.common.valueholder.ValueHolderInterface;
import us.mn.state.health.lims.person.valueholder.Person;

public class Provider extends BaseObject {

	private String id;

	private String externalId;

	private String npi;

	private String providerType;

	private ValueHolderInterface person;

	private String selectedPersonId;

	public Provider() {
		super();
		this.person = new ValueHolder();

	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public String getNpi() {
		return npi;
	}

	public void setNpi(String npi) {
		this.npi = npi;
	}

	public String getProviderType() {
		return providerType;
	}

	public void setProviderType(String providerType) {
		this.providerType = providerType;
	}

	public Person getPerson() {
		return (Person) this.person.getValue();
	}

	protected ValueHolderInterface getPersonHolder() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person.setValue(person);
	}

	protected void setPersonHolder(ValueHolderInterface person) {
		this.person = person;
	}

	public void setSelectedPersonId(String selectedPersonId) {
		this.selectedPersonId = selectedPersonId;
	}

	public String getSelectedPersonId() {
		return this.selectedPersonId;
	}

}