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

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.validator.GenericValidator;
import org.json.simple.JSONObject;

import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.dictionary.dao.DictionaryDAO;
import us.mn.state.health.lims.dictionary.daoimpl.DictionaryDAOImpl;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.referencetables.daoimpl.ReferenceTablesDAOImpl;
import us.mn.state.health.lims.result.dao.ResultDAO;
import us.mn.state.health.lims.result.dao.ResultSignatureDAO;
import us.mn.state.health.lims.result.daoimpl.ResultDAOImpl;
import us.mn.state.health.lims.result.daoimpl.ResultSignatureDAOImpl;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.result.valueholder.ResultSignature;
import us.mn.state.health.lims.resultlimits.daoimpl.ResultLimitDAOImpl;
import us.mn.state.health.lims.resultlimits.valueholder.ResultLimit;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.typeofsample.daoimpl.TypeOfSampleDAOImpl;
import us.mn.state.health.lims.typeofsample.daoimpl.TypeOfSampleTestDAOImpl;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSampleTest;

public class ResultService {

    private static DictionaryDAO dictionaryDAO = new DictionaryDAOImpl();
    private static ResultDAO resultDAO = new ResultDAOImpl();
    private static ResultSignatureDAO signatureDAO = new ResultSignatureDAOImpl();
	private Result result;
	private Test test;
	private List<ResultLimit> resultLimit;
    public static final String TABLE_REFERENCE_ID;

    static{
        TABLE_REFERENCE_ID = new ReferenceTablesDAOImpl().getReferenceTableByName("RESULT").getId();
    }

	public ResultService(Result result) {
		this.result = result;

		test = result.getAnalysis() != null ? result.getAnalysis().getTest() : null;
	}

	public String getLabSectionName() {
		return result.getAnalysis().getTestSection().getName();
	}

	public String getTestName() {
		return TestService.getUserLocalizedTestName(test);
	}

    public String getReportingTestName() {
        return TestService.getUserLocalizedReportingTestName(test);
    }

	public String getTestDescription() {
		return TestService.getLocalizedTestNameWithType( test );
	}

	public String getSampleType() {
        if( test == null){
            return "";
        }

		TypeOfSampleTest sampleTestType = new TypeOfSampleTestDAOImpl().getTypeOfSampleTestForTest(test.getId());

		if (sampleTestType != null) {
			return new TypeOfSampleDAOImpl().getNameForTypeOfSampleId(sampleTestType.getTypeOfSampleId());
		}

		return "";
	}

	public String getLOINCCode() {
		return test != null ? test.getLoinc() : "";
	}

	public String getTestTime() {
		return result.getAnalysis().getCompletedDateForDisplay();
	}

	public String getTestType() {
		return result.getResultType();
	}

    /**
     * This gets the simple value of the result, it treats multiresults as single dictionary values and
     * does not try to get the complete set
     * @return The String value
     */
    public String getSimpleResultValue(){
        if (GenericValidator.isBlankOrNull(result.getValue())) {
            return "";
        }

        if (TypeOfTestResultService.ResultType.isDictionaryVariant( getTestType() )) {
            return getDictEntry(  );
        } else if (TypeOfTestResultService.ResultType.NUMERIC.matches(getTestType())) {
            int significantPlaces = result.getSignificantDigits();
            if (significantPlaces == 0) {
                return result.getValue().split("\\.")[0];
            }
            StringBuilder value = new StringBuilder();
            value.append(result.getValue());
            int startFill = 0;

            if (!result.getValue().contains(".")) {
                value.append(".");
            } else {
                startFill = result.getValue().length() - result.getValue().lastIndexOf(".") - 1;
            }

            for (int i = startFill ; i < significantPlaces; i++) {
                value.append("0");
            }

            return value.toString();
        }else if (TypeOfTestResultService.ResultType.ALPHA.matches(result.getResultType()) && !GenericValidator.isBlankOrNull(result.getValue())) {
            return result.getValue().split("\\(")[0].trim();
        }else {
            return result.getValue();
        }
    }
    /**
     * This returns a textual representation of the result value.  Multiselect results are returned as a comma
     * delimited string. If there is a qualified value it is not included
     * @param printable If true the results will be suitable for printing, otherwise they will be suitable for a
     *                  web form
     * @return A textual representation of the value
     */
    public String getResultValue(boolean printable ){
       return getResultValue( ",", printable, false );
    }

    public String getResultValue( String separator, boolean printable, boolean includeUOM){
		if (GenericValidator.isBlankOrNull(result.getValue())) {
			return "";
		}

		if (TypeOfTestResultService.ResultType.DICTIONARY.matches(getTestType())) {
		    
		    if (!printable) {
		        return result.getValue();
		    }
		    String reportResult = "";
		    List<Result> resultList = resultDAO.getResultsByAnalysis( result.getAnalysis());
	        if( !resultList.isEmpty()){
	            if( resultList.size() == 1 ){
	                reportResult = getDictEntry();
	            }else{
	                //If dictionary result it can also have a quantified result
                    List<Result> dictionaryResults = new ArrayList<Result>();
                    Result quantification = null;
                    for( Result sibResult : resultList ){
                        if( TypeOfTestResultService.ResultType.DICTIONARY.matches( sibResult.getResultType() ) ){
                            dictionaryResults.add( sibResult );
                        }else if( TypeOfTestResultService.ResultType.ALPHA.matches( sibResult.getResultType() ) && sibResult.getParentResult() != null ){
                            quantification = sibResult;
                        }
                    }

                    for( Result sibResult : dictionaryResults ){
                        Dictionary dictionary = dictionaryDAO.getDictionaryById(sibResult.getValue());
                        reportResult = (dictionary != null && dictionary.getId() != null) ? dictionary.getLocalizedName() : "";
                        if( quantification != null && quantification.getParentResult().getId().equals( sibResult.getId() ) ){
                            reportResult += separator + quantification.getValue();
                        }
                    }
	            }   
	        }

            if( includeUOM && !GenericValidator.isBlankOrNull( reportResult )){
                String uom = getUOM();
                if( !GenericValidator.isBlankOrNull( uom )){
                    reportResult += " " + uom;
                }
            }

			return StringEscapeUtils.escapeHtml(reportResult);
		} else if (TypeOfTestResultService.ResultType.isMultiSelectVariant(getTestType())) {
			StringBuilder buffer = new StringBuilder();
			boolean firstPass = true;

			List<Result> results = new ResultDAOImpl().getResultsByAnalysis(result.getAnalysis());

			for (Result multiResult : results) {
				if (!GenericValidator.isBlankOrNull(multiResult.getValue()) && TypeOfTestResultService.ResultType.isMultiSelectVariant(multiResult.getResultType())) {
					if (firstPass) {
						firstPass = false;
					} else {
						buffer.append(separator);
					}
					buffer.append(dictionaryDAO.getDataForId(multiResult.getValue()).getDictEntry());
				}
			}
			return buffer.toString();
		} else if (TypeOfTestResultService.ResultType.NUMERIC.matches(getTestType())) {
            int significantPlaces = result.getSignificantDigits();
            if( significantPlaces == -1){
                return result.getValue() + appendUOM( includeUOM );
            }
            if (significantPlaces == 0) {
                return result.getValue().split("\\.")[0] + appendUOM( includeUOM );
            }
            StringBuilder value = new StringBuilder();
            value.append(result.getValue());
            int startFill = 0;

            if (!result.getValue().contains(".")) {
                value.append(".");
            } else {
                startFill = result.getValue().length() - result.getValue().lastIndexOf(".") - 1;
            }

            for (int i = startFill ; i < significantPlaces; i++) {
                value.append("0");
            }

            return value.toString() + appendUOM( includeUOM );
        }else if (TypeOfTestResultService.ResultType.ALPHA.matches(result.getResultType()) && !GenericValidator.isBlankOrNull(result.getValue())) {
            return result.getValue().split("\\(")[0].trim();
        }else {
            return result.getValue();
		}
	}

    private String getDictEntry(  ){
        Dictionary dictionary = dictionaryDAO.getDataForId( result.getValue() );
        return dictionary != null ? dictionary.getDictEntry() : "";
    }

    private String appendUOM( boolean includeUOM ){
        if( includeUOM && result.getAnalysis().getTest().getUnitOfMeasure() != null ){
            return " " + result.getAnalysis().getTest().getUnitOfMeasure().getName();
        }else{
            return "";
        }
    }

    public String getMultiSelectSelectedIdValues(){
        if (GenericValidator.isBlankOrNull(result.getValue())) {
            return "";
        }

        if (TypeOfTestResultService.ResultType.MULTISELECT.getCharacterValue().equals(getTestType())) {
            StringBuilder buffer = new StringBuilder();
            boolean firstPass = true;

            List<Result> results = new ResultDAOImpl().getResultsByAnalysis(result.getAnalysis());

            for (Result multiResult : results) {
                if (!GenericValidator.isBlankOrNull(multiResult.getValue()) && TypeOfTestResultService.ResultType.isMultiSelectVariant(multiResult.getResultType())) {
                    if (firstPass) {
                        firstPass = false;
                    } else {
                        buffer.append(",");
                    }
                    buffer.append(multiResult.getValue());
                }
            }
            return buffer.toString();
        }

        return "";
    }

	public String getUOM() {
		return test != null && test.getUnitOfMeasure() != null ? test.getUnitOfMeasure().getUnitOfMeasureName() : "";
	}

	public double getlowNormalRange() {
		return result.getMinNormal();
	}

	public double getHighNormalRange() {
		return result.getMaxNormal();
	}

	/**
	 * 
	 * @return true if any of the result limits for this test have a gender
	 *         specification
	 */
	public boolean ageInRangeCriteria() {
		List<ResultLimit> resultLimits = getResultLimits();

		for (ResultLimit limit : resultLimits) {
			if (limit.getMaxAge() != limit.getMinAge()) {
				return true;
			}
		}

		return false;
	}

	public boolean genderInRangeCritera() {
		List<ResultLimit> resultLimits = getResultLimits();

		for (ResultLimit limit : resultLimits) {
			if (!GenericValidator.isBlankOrNull(limit.getGender())) {
				return true;
			}
		}

		return false;
	}

    public String getDisplayReferenceRange(boolean includeSelectList){
        String range = "";
        if( TypeOfTestResultService.ResultType.NUMERIC.matches( result.getResultType() ) ){
            if( result.getMinNormal() != null && result.getMaxNormal() != null && !result.getMinNormal().equals( result.getMaxNormal() ) ){
                range = ResultLimitService.getDisplayNormalRange( result.getMinNormal(), result.getMaxNormal(), String.valueOf( result.getSignificantDigits() ), "-" );
            }
        }else if( includeSelectList && TypeOfTestResultService.ResultType.isDictionaryVariant( result.getResultType() )){
            List<ResultLimit> limits = getResultLimits();
            if( !limits.isEmpty() && !GenericValidator.isBlankOrNull( limits.get( 0 ).getDictionaryNormalId() )){
                range = dictionaryDAO.getDataForId( limits.get( 0 ).getDictionaryNormalId() ).getLocalizedName();
            }
        }
        return range;
    }

	private List<ResultLimit> getResultLimits() {
		if (resultLimit == null) {
			resultLimit = test != null ? new ResultLimitDAOImpl().getAllResultLimitsForTest(test.getId()) : new ArrayList<ResultLimit>();
		}

		return resultLimit;
	}

    public boolean isAbnormalDictionaryResult(){
        if( result.getValue() != null && TypeOfTestResultService.ResultType.isDictionaryVariant( result.getResultType() )){
            List<ResultLimit> limits = getResultLimits();
            if( !limits.isEmpty()){
                return !result.getValue().equals( limits.get(0).getDictionaryNormalId() );
            }
        }

        return false;
    }
	public String getLastUpdatedTime() {
		return  DateUtil.convertTimestampToStringDate(result.getLastupdated());
	}

    public String getSignature(){
        List<ResultSignature> signatures = signatureDAO.getResultSignaturesByResult( result );
         return signatures.isEmpty() ? "" : signatures.get( 0 ).getNonUserName();
    }
    public static List<Result> getResultsInTimePeriodWithTest( Date startDate, Date endDate, String testId){
        return resultDAO.getResultsForTestInDateRange( testId, startDate, DateUtil.addDaysToSQLDate( endDate, 1)  );
    }

    public static List<Result> getResultsInTimePeriodInPanel( Date lowDate, Date highDate, String panelId ){
        return resultDAO.getResultsForPanelInDateRange( panelId, lowDate, DateUtil.addDaysToSQLDate( highDate, 1 ) );
    }

    public static List<Result> getResultsInTimePeriodInTestSection( Date lowDate, Date highDate, String testSectionId ){
        return resultDAO.getResultsForTestSectionInDateRange( testSectionId, lowDate, DateUtil.addDaysToSQLDate(highDate, 1 ) );
    }

    public static String getJSONStringForMultiSelect( List<Result> resultList){
        Collections.sort( resultList, new Comparator<Result>(){
            @Override
            public int compare( Result o1, Result o2 ){
                return o1.getGrouping() - o2.getGrouping();
            }
        } );

        JSONObject jsonRep =new JSONObject();

        int currentGrouping = -1;
        StringBuilder currentString = new StringBuilder( );

        for(Result result : resultList){
            if( TypeOfTestResultService.ResultType.isMultiSelectVariant( result.getResultType() ) && result.getValue() != null){
                if( currentGrouping != result.getGrouping()){
                    if( currentString.length() > 1 ){
                        currentString.setLength( currentString.length() - 1 );
                        jsonRep.put( String.valueOf( currentGrouping ), currentString.toString() );
                    }

                    currentGrouping = result.getGrouping();
                    currentString = new StringBuilder( );
                }

                currentString.append( result.getValue() );
                currentString.append( "," );
            }
        }

        if( currentString.length() > 1 ){
            currentString.setLength( currentString.length() - 1 );
            jsonRep.put( String.valueOf( currentGrouping ), currentString.toString() );
        }

        return jsonRep.toJSONString();
    }
}
