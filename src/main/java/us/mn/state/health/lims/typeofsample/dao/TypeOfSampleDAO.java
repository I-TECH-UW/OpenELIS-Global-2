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
package us.mn.state.health.lims.typeofsample.dao;

import java.util.List;

import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;

/**
 * @author diane benz
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public interface TypeOfSampleDAO extends BaseDAO {

	public enum SampleDomain {
		HUMAN, ANIMAL, ENVIRONMENTAL
	};

	public String getNameForTypeOfSampleId( String id );

	public boolean insertData(TypeOfSample typeOfSample)
			throws LIMSRuntimeException;

	public void deleteData(List typeOfSamples) throws LIMSRuntimeException;

	public List getAllTypeOfSamples() throws LIMSRuntimeException;
	
	public List<TypeOfSample> getAllTypeOfSamplesSortOrdered() throws LIMSRuntimeException;

	public List getPageOfTypeOfSamples(int startingRecNo)
			throws LIMSRuntimeException;

	public void getData(TypeOfSample typeOfSample) throws LIMSRuntimeException;

	public void updateData(TypeOfSample typeOfSample)
			throws LIMSRuntimeException;

	public List getTypes(String filter, String domain) throws LIMSRuntimeException;

	public List getTypesForDomain(SampleDomain domain) throws LIMSRuntimeException;

	public List getNextTypeOfSampleRecord(String id) throws LIMSRuntimeException;

	public List getPreviousTypeOfSampleRecord(String id) throws LIMSRuntimeException;

	public Integer getTotalTypeOfSampleCount() throws LIMSRuntimeException;

	public TypeOfSample getTypeOfSampleByDescriptionAndDomain(TypeOfSample tos, boolean ignoreCase) throws LIMSRuntimeException;

	public TypeOfSample getTypeOfSampleById(String typeOfSampleId) throws LIMSRuntimeException;

	public List<TypeOfSample> getTypesForDomainBySortOrder(SampleDomain human) throws LIMSRuntimeException;

    public TypeOfSample getTypeOfSampleByLocalAbbrevAndDomain( String localAbbrev, String domain) throws LIMSRuntimeException;

	public TypeOfSample getSampleTypeFromTest(Test test);
}
