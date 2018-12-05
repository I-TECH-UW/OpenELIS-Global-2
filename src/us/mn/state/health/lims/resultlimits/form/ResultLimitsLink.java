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
package us.mn.state.health.lims.resultlimits.form;


import org.apache.commons.validator.GenericValidator;

import us.mn.state.health.lims.dictionary.dao.DictionaryDAO;
import us.mn.state.health.lims.dictionary.daoimpl.DictionaryDAOImpl;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.resultlimits.valueholder.ResultLimit;

public class ResultLimitsLink{

	private static final String NO_VALUE_READ_ONLY = "-";
	private static final String NO_VALUE_READ_WRITE = "";
	private static final double POS_DEFAULT = Double.POSITIVE_INFINITY;
	private static final double NEG_DEFAULT = Double.NEGATIVE_INFINITY;
	private static final double ZERO_DEFAULT = 0;
	private static final double MONTH_TO_DAY = 362.0/12.0;
	
	private boolean readWrite = true;
	
	private static DictionaryDAO dictionaryDAO = new DictionaryDAOImpl();
	private String id;
	private String testId;
	private String resultTypeId;
	private String gender;
	private double minAge = 0.0;
	private double maxAge = Double.POSITIVE_INFINITY;
	private double lowNormal = Double.NEGATIVE_INFINITY;
	private double highNormal = Double.POSITIVE_INFINITY;
	private double lowValid = Double.NEGATIVE_INFINITY;
	private double highValid = Double.POSITIVE_INFINITY;
	private String testName;
	private String resultType;
	private String dictionaryNormal;
	
	public void setReadWrite(boolean readWrite) {
		this.readWrite = readWrite;
	}
	
	public void setResultLimit(ResultLimit limit){
		setId(limit.getId());
		setTestId (limit.getTestId());
		setResultTypeId( limit.getResultTypeId());
		setGender( limit.getGender());
		setMinAge( limit.getMinAge());
		setMaxAge( limit.getMaxAge());
		setLowNormal( limit.getLowNormal());
		setHighNormal( limit.getHighNormal());
		setLowValid( limit.getLowValid());
		setHighValid( limit.getHighValid());
		if( !GenericValidator.isBlankOrNull(limit.getDictionaryNormalId())){
			Dictionary normal = dictionaryDAO.getDictionaryById(limit.getDictionaryNormalId());
			if( normal != null){
				setDictionaryNormal(normal.getDictEntry());		
			}
		}
		
	}
	
	public ResultLimit getResultLimit(){
		ResultLimit limit = new ResultLimit();
		
		return populateResultLimit(limit);
	}
	
	public ResultLimit populateResultLimit(ResultLimit limit) {

		if( limit == null ){
			limit = new ResultLimit();
		}
		
		limit.setId(id);
		limit.setTestId(testId);
		limit.setResultTypeId(resultTypeId);
		limit.setGender(gender);
		limit.setMinAge(getMinAge());
		limit.setMaxAge(getMaxAge());
		limit.setLowNormal(getLowNormal());
		limit.setHighNormal(getHighNormal());
		limit.setLowValid(getLowValid());
		limit.setHighValid(getHighValid());
		limit.setDictionaryNormalId(getDictionaryNormal());

		return limit;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	public String getResultTypeId() {
		return resultTypeId;
	}

	public void setResultTypeId(String resultTypeId) {
		this.resultTypeId = resultTypeId;
	}

	public String getMinAgeDisplay() {
		return getDisplayValue(minAge);
	}

	public String getMinDayAgeDisplay() {
		int dayAge = minAge == Double.NEGATIVE_INFINITY ? Integer.MIN_VALUE : (int)(minAge * MONTH_TO_DAY + (minAge/48.0));
		return getIntDisplayValue(dayAge);
	}
	
	public void setMinAgeDisplay(String minAgeDisplay) {
		this.minAge = saftelyGetValue(minAgeDisplay, ZERO_DEFAULT);
	}

	public String getMaxAgeDisplay() {
		return getDisplayValue(maxAge);
	}

	public String getMaxDayAgeDisplay() {
		int dayAge = maxAge == Double.POSITIVE_INFINITY ? Integer.MAX_VALUE : (int)(maxAge * MONTH_TO_DAY + (maxAge/48.0));
		return getIntDisplayValue(dayAge);
	}
	
	public void setMaxAgeDisplay(String maxAgeDisplay) {
		this.maxAge = saftelyGetValue(maxAgeDisplay, POS_DEFAULT);
	}

	public String getLowNormalDisplay() {
		return getDisplayValue(lowNormal);
	}

	public void setLowNormalDisplay(String lowNormalDisplay) {
		this.lowNormal = saftelyGetValue(lowNormalDisplay, NEG_DEFAULT);
	}

	public String getHighNormalDisplay() {
		return getDisplayValue(highNormal);
	}

	public void setHighNormalDisplay(String highNormalDisplay) {
		this.highNormal= saftelyGetValue(highNormalDisplay, POS_DEFAULT);
	}

	public String getLowValidDisplay() {
		return getDisplayValue(lowValid);
	}

	public void setLowValidDisplay(String lowValidDisplay) {
		this.lowValid = saftelyGetValue(lowValidDisplay, NEG_DEFAULT);
	}

	public String getHighValidDisplay() {
		return getDisplayValue(highValid);
	}

	public void setHighValidDisplay(String highValidDisplay) {
		this.highValid = saftelyGetValue(highValidDisplay, POS_DEFAULT);
	}
	
	private double getMinAge() {
		return  minAge;
	}
	
	private void setMinAge(double minAge) {
		this.minAge = minAge;
	}
	
	private double getMaxAge() {
		return maxAge;
	}
	private void setMaxAge(double maxAge) {
		this.maxAge = maxAge;
	}
	private double getLowNormal() {
		return lowNormal;
	}
	private void setLowNormal(double lowNormal) {
		this.lowNormal = lowNormal;
	}
	private double getHighNormal() {
		return highNormal;
	}
	private void setHighNormal(double highNormal) {
		this.highNormal = highNormal;
	}
	private double getLowValid() {
		return lowValid;
	}
	private void setLowValid(double lowValid) {
		this.lowValid = lowValid;
	}
	private double getHighValid() {
		return highValid;
	}
	private void setHighValid(double highValid) { 
		this.highValid = highValid;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getTestName() {
		return testName == null ? getTestId() : testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getResultType() {
		return resultType == null ? getResultTypeId() : resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	private String getDisplayValue(double value) {
		if(value == Double.POSITIVE_INFINITY || value == Double.NEGATIVE_INFINITY ){
			return readWrite ? NO_VALUE_READ_WRITE : NO_VALUE_READ_ONLY;
		}else{
			return Double.toString( Math.floor(value/12.0));
		}
	}

	private String getIntDisplayValue(int value) {
		if(value == Integer.MAX_VALUE || value == Integer.MIN_VALUE ){
			return readWrite ? NO_VALUE_READ_WRITE : NO_VALUE_READ_ONLY;
		}else{
			return Integer.toString(value);
		}
	}


	private double saftelyGetValue(String value, double defaultValue) {
		
		try
		{
			return Double.parseDouble(value);
		}catch(NumberFormatException nfe){
			return defaultValue;
		}
	}

	public String getDictionaryNormal() {
		return dictionaryNormal;
	}

	public void setDictionaryNormal(String dictionaryNormal) {
		this.dictionaryNormal = dictionaryNormal;
	}	
}
