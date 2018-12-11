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
package us.mn.state.health.lims.project.dao;

import java.util.List;

import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.project.valueholder.Project;

/**
 * @author diane benz
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public interface ProjectDAO extends BaseDAO {

	public boolean insertData(Project project) throws LIMSRuntimeException;

	public void deleteData(List projects) throws LIMSRuntimeException;

	public List<Project> getAllProjects() throws LIMSRuntimeException;

	public List getPageOfProjects(int startingRecNo)
			throws LIMSRuntimeException;

	public void getData(Project project) throws LIMSRuntimeException;

	public void updateData(Project project) throws LIMSRuntimeException;
	
	public List getNextProjectRecord(String id) throws LIMSRuntimeException;

	public List getPreviousProjectRecord(String id) throws LIMSRuntimeException;
	
	//bugzilla 1978: added param activeOnly
	public Project getProjectByName(Project project, boolean ignoreCase, boolean activeOnly) throws LIMSRuntimeException;
	
     //bugzilla 1978: added param activeOnly
	public List getProjects(String filter, boolean activeOnly) throws LIMSRuntimeException;

	//bugzilla 1411
	public Integer getTotalProjectCount() throws LIMSRuntimeException; 	

	//bugzilla 2438
	public Project getProjectByLocalAbbreviation(Project project, boolean activeOnly) throws LIMSRuntimeException;

	public Project getProjectById(String id) throws LIMSRuntimeException;
}
