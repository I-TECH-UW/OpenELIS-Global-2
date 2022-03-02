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
package org.openelisglobal.userrole.dao;

import java.util.Collection;
import java.util.List;

import org.openelisglobal.common.dao.BaseDAO;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.userrole.valueholder.UserRole;
import org.openelisglobal.userrole.valueholder.UserRolePK;

public interface UserRoleDAO extends BaseDAO<UserRole, UserRolePK> {

//	public boolean insertData(UserRole userRole) throws LIMSRuntimeException;

//	public void deleteData(List<UserRole> userRoles) throws LIMSRuntimeException;

//	public List getAllUserRoles() throws LIMSRuntimeException;

//	public List getPageOfUserRoles(int startingRecNo) throws LIMSRuntimeException;

//	public void getData(UserRole userRole) throws LIMSRuntimeException;

//	public void updateData(UserRole userRole) throws LIMSRuntimeException;





//	public List<String> getRoleIdsForUser(String userId) throws LIMSRuntimeException;

    public boolean userInRole(String userId, String roleName) throws LIMSRuntimeException;

    public boolean userInRole(String userId, Collection<String> roleNames) throws LIMSRuntimeException;

    List<String> getRoleIdsForUser(String userId) throws LIMSRuntimeException;

}
