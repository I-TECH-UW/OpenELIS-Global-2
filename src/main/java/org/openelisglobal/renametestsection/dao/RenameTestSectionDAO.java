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
package org.openelisglobal.renametestsection.dao;

import java.util.List;

import org.openelisglobal.common.dao.BaseDAO;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.renametestsection.valueholder.RenameTestSection;

/**
 * @author diane benz
 *
 *         To change this generated comment edit the template variable
 *         "typecomment": Window>Preferences>Java>Templates. To enable and
 *         disable the creation of type comments go to
 *         Window>Preferences>Java>Code Generation.
 */
public interface RenameTestSectionDAO extends BaseDAO<RenameTestSection, String> {

//	public boolean insertData(RenameTestSection testSection) throws LIMSRuntimeException;

//	public void deleteData(List testSections) throws LIMSRuntimeException;

	public List getAllTestSections() throws LIMSRuntimeException;

	public List getPageOfTestSections(int startingRecNo) throws LIMSRuntimeException;

	public void getData(RenameTestSection testSection) throws LIMSRuntimeException;

//	public void updateData(RenameTestSection testSection) throws LIMSRuntimeException;

	public List getNextTestSectionRecord(String id) throws LIMSRuntimeException;

	public List getPreviousTestSectionRecord(String id) throws LIMSRuntimeException;

	public RenameTestSection getTestSectionByName(RenameTestSection testSection) throws LIMSRuntimeException;

	public List getTestSections(String filter) throws LIMSRuntimeException;

	// bugzilla 1411
	public Integer getTotalTestSectionCount() throws LIMSRuntimeException;

	public RenameTestSection getTestSectionById(String id);

	boolean duplicateTestSectionExists(RenameTestSection testSection) throws LIMSRuntimeException;
}
