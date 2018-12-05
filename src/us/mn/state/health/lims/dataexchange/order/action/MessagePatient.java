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
package us.mn.state.health.lims.dataexchange.order.action;


public class MessagePatient{
	private String guid;
	private String obNumber;
	private String nationalId;
	private String pcNumber;
	private String stNumber;
	private String externalId;
	
	private String gender;
	private String displayDOB;
	private String firstName;
	private String lastName;
	private String mothersFirstName;
	
	private String addressStreet;
	private String addressVillage;
	private String addressDepartment;
	
	public String getGuid(){
		return guid;
	}
	public void setGuid(String guid){
		this.guid = guid;
	}
	public String getObNumber(){
		return obNumber;
	}
	public void setObNumber(String obNumber){
		this.obNumber = obNumber;
	}
	public String getNationalId(){
		return nationalId;
	}
	public void setNationalId(String nationalId){
		this.nationalId = nationalId;
	}
	public String getPcNumber(){
		return pcNumber;
	}
	public void setPcNumber(String pcNumber){
		this.pcNumber = pcNumber;
	}
	public String getStNumber(){
		return stNumber;
	}
	public void setStNumber(String stNumber){
		this.stNumber = stNumber;
	}
	public String getExternalId() {
		return externalId;
	}
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	public String getGender(){
		return gender;
	}
	public void setGender(String gender){
		this.gender = gender;
	}
	
	public String getFirstName(){
		return firstName;
	}
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	public String getLastName(){
		return lastName;
	}
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	public String getMothersFirstName(){
		return mothersFirstName;
	}
	public void setMothersFirstName(String mothersFirstName){
		this.mothersFirstName = mothersFirstName;
	}
	public String getAddressStreet(){
		return addressStreet;
	}
	public void setAddressStreet(String addressStreet){
		this.addressStreet = addressStreet;
	}
	public String getAddressVillage(){
		return addressVillage;
	}
	public void setAddressVillage(String addressVillage){
		this.addressVillage = addressVillage;
	}
	public String getAddressDepartment(){
		return addressDepartment;
	}
	public void setAddressDepartment(String addressDepartment){
		this.addressDepartment = addressDepartment;
	}
	public String getDisplayDOB(){
		return displayDOB;
	}
	public void setDisplayDOB(String displayDOB){
		this.displayDOB = displayDOB;
	}
	
	
}
