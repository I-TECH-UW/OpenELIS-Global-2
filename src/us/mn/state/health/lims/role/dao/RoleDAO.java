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
package us.mn.state.health.lims.role.dao;

import java.util.List;

import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.role.valueholder.Role;

public interface RoleDAO extends BaseDAO {

	public boolean insertData(Role role) throws LIMSRuntimeException;

	public void deleteData(List<Role> roles) throws LIMSRuntimeException;

	public List<Role> getAllRoles() throws LIMSRuntimeException;

	public List<Role> getPageOfRoles(int startingRecNo) throws LIMSRuntimeException;

	public void getData(Role role) throws LIMSRuntimeException;

	public void updateData(Role role) throws LIMSRuntimeException;

	@SuppressWarnings("rawtypes")
	public List getNextRoleRecord(String id) throws LIMSRuntimeException;

	@SuppressWarnings("rawtypes")
	public List getPreviousRoleRecord(String id)throws LIMSRuntimeException;

	public List<Role> getReferencingRoles(Role role) throws LIMSRuntimeException;

	public List<Role> getAllActiveRoles() throws LIMSRuntimeException;

	public Role getRoleByName(String name) throws LIMSRuntimeException;

	public Role getRoleById(String roleId) throws LIMSRuntimeException;
}
