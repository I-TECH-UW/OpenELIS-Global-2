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
package us.mn.state.health.lims.provider.dao;

import java.util.List;

import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.person.valueholder.Person;
import us.mn.state.health.lims.provider.valueholder.Provider;

/**
 * @author diane benz
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public interface ProviderDAO extends BaseDAO {

	public boolean insertData(Provider provider) throws LIMSRuntimeException;

	public void deleteData(List providers) throws LIMSRuntimeException;

	public List getAllProviders() throws LIMSRuntimeException;

	public List getPageOfProviders(int startingRecNo)
			throws LIMSRuntimeException;

	public void getData(Provider provider) throws LIMSRuntimeException;

	public void updateData(Provider provider) throws LIMSRuntimeException;
	
	public List getNextProviderRecord(String id) throws LIMSRuntimeException;

	public List getPreviousProviderRecord(String id) throws LIMSRuntimeException;

	/*
	 * The intent of this is to find the provider linked to a person.  It assumes that each provider is
	 * uniquely linked to a person.  If more than one provider is linked to the same person then the first
	 * provider is returned.
	 */
	public Provider getProviderByPerson(Person person)  throws LIMSRuntimeException;;

}
