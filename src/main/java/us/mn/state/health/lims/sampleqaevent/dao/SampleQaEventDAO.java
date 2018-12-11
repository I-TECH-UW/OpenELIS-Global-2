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
* Contributor(s): ITECH, University of Washington, Seattle WA.
*/
package us.mn.state.health.lims.sampleqaevent.dao;

import java.sql.Date;
import java.util.List;

import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleqaevent.valueholder.SampleQaEvent;

/**
 *  $Header$
 *
 *  @author         Diane Benz
 *  @date created   06/12/2008
 *  @version        $Revision$
 *  bugzilla 2510
 */
public interface SampleQaEventDAO extends BaseDAO {

	public boolean insertData(SampleQaEvent sampleQaEvent) throws LIMSRuntimeException;

	public void deleteData(List sampleQaEvents) throws LIMSRuntimeException;

	public void getData(SampleQaEvent sampleQaEvent) throws LIMSRuntimeException;

	public void updateData(SampleQaEvent sampleQaEvent) throws LIMSRuntimeException;

	public List getSampleQaEventsBySample(SampleQaEvent sampleQaEvent) throws LIMSRuntimeException;

	public List<SampleQaEvent> getSampleQaEventsBySample(Sample sample) throws LIMSRuntimeException;

	public SampleQaEvent getSampleQaEventBySampleAndQaEvent(SampleQaEvent sampleQaEvent) throws LIMSRuntimeException;

    public List<SampleQaEvent> getSampleQaEventsByUpdatedDate(Date lowDate, Date highDate) throws LIMSRuntimeException;

	public List<SampleQaEvent> getAllUncompleatedEvents() throws LIMSRuntimeException;

	public SampleQaEvent getData(String sampleQaEventId) throws LIMSRuntimeException;

}
