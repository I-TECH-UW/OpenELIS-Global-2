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
package us.mn.state.health.lims.organization.dao;

import java.util.List;
import java.util.Set;

import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.organization.valueholder.Organization;

/**
 * @author diane benz
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public interface OrganizationDAO extends BaseDAO {

	public boolean insertData(Organization organization)
			throws LIMSRuntimeException;

	public void deleteData(List organizations) throws LIMSRuntimeException;

	public List getAllOrganizations() throws LIMSRuntimeException;

	public List getPageOfOrganizations(int startingRecNo)
			throws LIMSRuntimeException;

	public void getData(Organization organization) throws LIMSRuntimeException;

	public void updateData(Organization organization)
			throws LIMSRuntimeException;

	public List getOrganizations(String filter) throws LIMSRuntimeException;

	public List getNextOrganizationRecord(String id)
			throws LIMSRuntimeException;

	public List getPreviousOrganizationRecord(String id)
			throws LIMSRuntimeException;

	public Organization getOrganizationByName(Organization organization, boolean ignoreCase)
			throws LIMSRuntimeException;

	public Integer getTotalOrganizationCount() throws LIMSRuntimeException;

	public Organization getOrganizationByLocalAbbreviation(Organization organization, boolean ignoreCase)
	throws LIMSRuntimeException;

	public List getPagesOfSearchedOrganizations (int startRecNo, String searchString)
                          throws LIMSRuntimeException;
	public Integer getTotalSearchedOrganizationCount(String searchString) throws LIMSRuntimeException;

	/**
	 * Find all organizations which are associated with a particular project.
	 * @param projectName  project.projectName to match
	 * @return a Set of organizations.
	 */
	public Set<Organization> getOrganizationsByProjectName(String projectName);

	/**
	 * Find all organzations which are of the given organization type (matching the types name).
	 */
	public List<Organization> getOrganizationsByTypeName(String orderByProperty, String... typeName);

	public List<Organization> getOrganizationsByTypeNameAndLeadingChars(String partialName, String typeName) throws LIMSRuntimeException;

	public Organization getOrganizationById(String organizationId) throws LIMSRuntimeException;
    public void insertOrUpdateData( Organization organization ) throws LIMSRuntimeException;
	public List<Organization> getOrganizationsByParentId( String parentId) throws LIMSRuntimeException;


}
