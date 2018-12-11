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
* Copyright (C) CIRG, University of Washington, Seattle WA.  All Rights Reserved.
*
*/
package us.mn.state.health.lims.address.dao;

import java.util.List;

import us.mn.state.health.lims.address.valueholder.PersonAddress;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;

public interface PersonAddressDAO {
	public List<PersonAddress> getAddressPartsByPersonId(String personId) throws LIMSRuntimeException;
	public void insert(PersonAddress personAddress) throws LIMSRuntimeException;
	public void update(PersonAddress personAddress) throws LIMSRuntimeException;
	public PersonAddress getByPersonIdAndPartId(String personId, String addressPartId) throws LIMSRuntimeException;
}
