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
package us.mn.state.health.lims.sample.dao;

import java.sql.Date;
import java.util.List;

import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.sample.valueholder.Sample;

public interface SampleDAO extends BaseDAO {

	public boolean insertData(Sample sample) throws LIMSRuntimeException;

	public void deleteData(List samples) throws LIMSRuntimeException;

	public List getPageOfSamples(int startingRecNo) throws LIMSRuntimeException;
	   
	public void getData(Sample sample) throws LIMSRuntimeException;

	public void updateData(Sample sample) throws LIMSRuntimeException;

	public List getNextSampleRecord(String id) throws LIMSRuntimeException;

	public List getPreviousSampleRecord(String id) throws LIMSRuntimeException;

	public void getSampleByAccessionNumber(Sample sample) throws LIMSRuntimeException;

	public Sample getSampleByAccessionNumber(String accessionNumber) throws LIMSRuntimeException;

	public boolean insertDataWithAccessionNumber(Sample sample) throws LIMSRuntimeException;

	public List getSamplesByStatusAndDomain(List statuses, String domain) throws LIMSRuntimeException;

	public List<Sample> getSamplesWithPendingQaEvents(Sample sample, boolean filterByCategory, String qaEventCategoryId, boolean filterByDomain) throws LIMSRuntimeException;

	public List<Sample> getSamplesReceivedOn(String recievedDate) throws LIMSRuntimeException;
	
	public Sample getSampleByReferringId(String referringId) throws LIMSRuntimeException;

	/**
	 * Find a range of samples INCLUSIVE of the given dates.
	 */
    public List<Sample> getSamplesReceivedInDateRange(String receivedDateStart, String receivedDateEnd) throws LIMSRuntimeException;

	public List<Sample> getSamplesCollectedOn(String collectionDate) throws LIMSRuntimeException;

	public List<Sample> getSamplesByProjectAndStatusIDAndAccessionRange(String projectId,  List<Integer> inclusiveStatusIdList, String minAccession, String maxAccession) throws LIMSRuntimeException;

	public List<Sample> getSamplesByProjectAndStatusIDAndAccessionRange(List<Integer> inclusiveProjectIdList,  List<Integer> inclusiveStatusIdList, String minAccession, String maxAccession) throws LIMSRuntimeException;

	public List<Sample> getSamplesByAccessionRange(String minAccession, String maxAccession) throws LIMSRuntimeException;

	public String getLargestAccessionNumber();

    public String getLargestAccessionNumberWithPrefix(String prefix);

    public String  getLargestAccessionNumberMatchingPattern(String startingWith, int size) throws LIMSRuntimeException;

	public List<Sample> getSamplesWithPendingQaEventsByService(String serviceId) throws LIMSRuntimeException;

    public List<Sample> getConfirmationSamplesReceivedInDateRange(Date receivedDateStart, Date receivedDateEnd) throws LIMSRuntimeException;
}
