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
package us.mn.state.health.lims.samplehuman.dao;

import java.util.List;

import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.provider.valueholder.Provider;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.samplehuman.valueholder.SampleHuman;

/**
 *  $Header$
 *
 *  @author         Hung Nguyen
 *  @date created   08/04/2006
 *  @version        $Revision$
 */
public interface SampleHumanDAO extends BaseDAO {

	public boolean insertData(SampleHuman sampleHuman) throws LIMSRuntimeException;

	public void deleteData(List sampleHumans) throws LIMSRuntimeException;

	public void getData(SampleHuman sampleHuman) throws LIMSRuntimeException;

	public void updateData(SampleHuman sampleHuman) throws LIMSRuntimeException;
	
	public void getDataBySample(SampleHuman sampleHuman) throws LIMSRuntimeException;

	public Patient getPatientForSample(Sample sample) throws LIMSRuntimeException;
	
	public Provider getProviderForSample(Sample sample) throws LIMSRuntimeException;

	public List<Sample> getSamplesForPatient(String patientID)throws LIMSRuntimeException;

}
