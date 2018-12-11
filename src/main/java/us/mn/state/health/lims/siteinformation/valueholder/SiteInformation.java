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
*
* Contributor(s): CIRG, University of Washington, Seattle WA.
*/
package us.mn.state.health.lims.siteinformation.valueholder;

import us.mn.state.health.lims.common.valueholder.BaseObject;
import us.mn.state.health.lims.common.valueholder.ValueHolder;
import us.mn.state.health.lims.scheduler.valueholder.CronScheduler;

public class SiteInformation extends BaseObject{

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String description;
	private String value;
	private boolean encrypted;
	private String valueType;
	private String instructionKey;
	private ValueHolder domain = new ValueHolder();
	private int group;
	private String tag;
	private ValueHolder schedule = new ValueHolder();
	private String dictionaryCategoryId;
	private String descriptionKey;

	public int getGroup() {
		return group;
	}
	public void setGroup(int group) {
		this.group = group;
	}

	public CronScheduler getSchedule() {
		return (CronScheduler)schedule.getValue();
	}
	public void setSchedule(CronScheduler schedule) {
		this.schedule.setValue(schedule);
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public void setValue(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}

	public void setEncrypted(boolean encrypted) {
		this.encrypted = encrypted;
	}
	public boolean isEncrypted() {
		return encrypted;
	}
	public void setDomain(SiteInformationDomain domain) {
		this.domain.setValue(domain);
	}
	public SiteInformationDomain getDomain() {
		return (SiteInformationDomain)domain.getValue();
	}
	public String getValueType() {
		return valueType;
	}
	public void setValueType(String valueType) {
		this.valueType = valueType;
	}
	public String getInstructionKey() {
		return instructionKey;
	}
	public void setInstructionKey(String instructionKey) {
		this.instructionKey = instructionKey;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getDictionaryCategoryId() {
		return dictionaryCategoryId;
	}
	public void setDictionaryCategoryId(String dictionaryCategoryId) {
		this.dictionaryCategoryId = dictionaryCategoryId;
	}
	public String getDescriptionKey() {
		return descriptionKey;
	}
	public void setDescriptionKey(String descriptionKey) {
		this.descriptionKey = descriptionKey;
	}
	protected String getDefaultLocalizedName(){
		return getName();
	}
}
