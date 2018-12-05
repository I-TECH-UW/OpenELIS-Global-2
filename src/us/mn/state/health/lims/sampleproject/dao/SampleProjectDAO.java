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
package us.mn.state.health.lims.sampleproject.dao;

import java.sql.Date;
import java.util.List;

import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.sampleproject.valueholder.SampleProject;

/**
 *  $Header$
 *
 *  @author         Hung Nguyen
 *  @date created   08/04/2006
 *  @version        $Revision$
 *	// AIS - bugzilla 1851
 *  //bugzilla 1920 standards
 */
public interface SampleProjectDAO extends BaseDAO {

	public boolean insertData(SampleProject sampleProj) throws LIMSRuntimeException;

	public void deleteData(List sampleProjs) throws LIMSRuntimeException;

	public void getData(SampleProject sampleProj) throws LIMSRuntimeException;

	public void updateData(SampleProject sampleProj) throws LIMSRuntimeException;

	public List getSampleProjectsByProjId(String projId) throws LIMSRuntimeException;

	public SampleProject getSampleProjectBySampleId(String id) throws LIMSRuntimeException;

    /**
     * @param locationId
     * @param projectName
     * @param lowDate
     * @param highDate
     * @return
     */
    public List<SampleProject> getByOrganizationProjectAndReceivedOnRange(String organizationId, String projectName, Date lowDate, Date highDate);
}
