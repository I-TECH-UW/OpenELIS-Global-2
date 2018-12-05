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
* Copyright (C) CIRG, University of Washington, Seattle WA.  All Rights Reserved.
*
*/
package us.mn.state.health.lims.common.provider.validation;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import us.mn.state.health.lims.common.provider.validation.IAccessionNumberValidator.ValidationResults;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.sample.dao.SampleDAO;
import us.mn.state.health.lims.sample.daoimpl.SampleDAOImpl;

public abstract class BaseSiteYearAccessionValidator {

	protected static final String INCREMENT_STARTING_VALUE = "000001";
	protected static final int UPPER_INC_RANGE = 999999;
	protected static final int SITE_START = 0;
	protected int SITE_END = getSiteEndIndex();
	protected int YEAR_START = getYearStartIndex();
	protected int YEAR_END = getYearEndIndex();
	protected int INCREMENT_START = getIncrementStartIndex();
	protected int INCREMENT_END = getMaxAccessionLength();
	protected int LENGTH = getMaxAccessionLength();
	protected static final boolean NEED_PROGRAM_CODE = false;
	private static Set<String> REQUESTED_NUMBERS = new HashSet<String>();

	public boolean needProgramCode() {
		return NEED_PROGRAM_CODE;
	}

	// input parameter is not used in this case
	public String createFirstAccessionNumber(String nullPrefix) {
		return getPrefix() + DateUtil.getTwoDigitYear() + INCREMENT_STARTING_VALUE;
	}

	public String getInvalidMessage(ValidationResults results) {
		String suggestedAccessionNumber = getNextAvailableAccessionNumber(null);

		return StringUtil.getMessageForKey("sample.entry.invalid.accession.number.suggestion") + " " + suggestedAccessionNumber;

	}

	// input parameter is not used in this case
	public String getNextAvailableAccessionNumber(String nullPrefix) {

		String nextAccessionNumber;

		SampleDAO sampleDAO = new SampleDAOImpl();

		String curLargestAccessionNumber = sampleDAO.getLargestAccessionNumberMatchingPattern(ConfigurationProperties.getInstance().getPropertyValue(Property.ACCESSION_NUMBER_PREFIX),
				                                                                               getMaxAccessionLength());


		if (curLargestAccessionNumber == null) {
			if( REQUESTED_NUMBERS.isEmpty()){
				nextAccessionNumber = createFirstAccessionNumber(null);
			}else{
				nextAccessionNumber = REQUESTED_NUMBERS.iterator().next();
			}
		} else {
			nextAccessionNumber = incrementAccessionNumber(curLargestAccessionNumber);
		}
		
		while( REQUESTED_NUMBERS.contains(nextAccessionNumber) ){
			nextAccessionNumber = incrementAccessionNumber(nextAccessionNumber);
		}
		
		REQUESTED_NUMBERS.add(nextAccessionNumber);
		
		return nextAccessionNumber;
	}

	public String incrementAccessionNumber(String currentHighAccessionNumber) throws IllegalArgumentException {
		// if the year differs then start the sequence again. If not then
		// increment but check for overflow into year
		int year = new GregorianCalendar().get(Calendar.YEAR) - 2000;

		try {
			if (year != Integer.parseInt(currentHighAccessionNumber.substring(YEAR_START, YEAR_END))) {
				return createFirstAccessionNumber(null);
			}
		} catch (NumberFormatException nfe) {
			return createFirstAccessionNumber(null);
		}

		int increment = Integer.parseInt(currentHighAccessionNumber.substring(INCREMENT_START));
		String incrementAsString;

		if (increment < UPPER_INC_RANGE) {
			increment++;
			incrementAsString = String.format("%06d", increment);
		} else {
			throw new IllegalArgumentException("AccessionNumber has no next value");
		}

		return currentHighAccessionNumber.substring(SITE_START, YEAR_END) + incrementAsString;
	}

	// recordType parameter is not used in this case
	public boolean accessionNumberIsUsed(String accessionNumber, String recordType) {

		SampleDAO SampleDAO = new SampleDAOImpl();

		return SampleDAO.getSampleByAccessionNumber(accessionNumber) != null;
	}

	public ValidationResults checkAccessionNumberValidity(String accessionNumber, String recordType, String isRequired,
			String projectFormName) {

			ValidationResults results = validFormat(accessionNumber, true);
			//TODO refactor accessionNumberIsUsed into two methods so the null isn't needed. (Its only used for program accession number)
			if (results == ValidationResults.SUCCESS && accessionNumberIsUsed(accessionNumber, null)) {
				results = ValidationResults.USED_FAIL;
			}

			return results;
	}

	public ValidationResults validFormat(String accessionNumber, boolean checkDate) {
		if (accessionNumber.length() != LENGTH) {
			return ValidationResults.LENGTH_FAIL;
		}

		if (!accessionNumber.substring(SITE_START, SITE_END).equals( getPrefix())) {
			return ValidationResults.SITE_FAIL;
		}

		

		if (checkDate) {
			int year = new GregorianCalendar().get(Calendar.YEAR);
			try {
				if ((year - 2000) != Integer.parseInt(accessionNumber.substring(YEAR_START, YEAR_END))) {
					return ValidationResults.YEAR_FAIL;
				}
			} catch (NumberFormatException nfe) {
				return ValidationResults.YEAR_FAIL;
			}
		}else{
			try { //quick and dirty to make sure they are digits
				Integer.parseInt(accessionNumber.substring(YEAR_START, YEAR_END));
			} catch (NumberFormatException nfe) {
				return ValidationResults.YEAR_FAIL;
			}
		}

		try {
			Integer.parseInt(accessionNumber.substring(INCREMENT_START));
		} catch (NumberFormatException e) {
			return ValidationResults.FORMAT_FAIL;
		}

		return ValidationResults.SUCCESS;
	}


    public String getInvalidFormatMessage( ValidationResults results ){
        return StringUtil.getMessageForKey( "sample.entry.invalid.accession.number.format.corrected", getFormatPattern(), getFormatExample() );
    }

    private String getFormatPattern(){
        StringBuilder format = new StringBuilder( getPrefix() );
        format.append( StringUtil.getMessageForKey( "date.two.digit.year" ) );
        for( int i = 0; i < getChangeableLength(); i++){
            format.append( "#" );
        }
        return format.toString();
    }

    private String getFormatExample(){
        StringBuilder format = new StringBuilder( getPrefix() );
        format.append( DateUtil.getTwoDigitYear() );
        for( int i = 0; i < getChangeableLength() - 1; i++){
            format.append( "0" );
        }

        format.append( "1" );

        return format.toString();
    }
    protected abstract String getPrefix();

	protected abstract int getIncrementStartIndex();

	protected abstract int getYearEndIndex();

	protected abstract int getYearStartIndex();

	protected abstract int getSiteEndIndex();

	protected abstract int getMaxAccessionLength();

    protected abstract int getChangeableLength();
}
