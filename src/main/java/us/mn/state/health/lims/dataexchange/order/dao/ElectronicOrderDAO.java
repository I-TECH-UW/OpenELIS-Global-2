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
* Copyright (C) ITECH, University of Washington, Seattle WA.  All Rights Reserved.
*
*/
package us.mn.state.health.lims.dataexchange.order.dao;

import java.util.List;

import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.dataexchange.order.valueholder.ElectronicOrder;

public interface ElectronicOrderDAO extends BaseDAO {
    
    public List<ElectronicOrder> getElectronicOrdersByExternalId(String id) throws LIMSRuntimeException;
    
    public List<ElectronicOrder> getElectronicOrdersByPatientId(String id) throws LIMSRuntimeException;

	public void insertData(ElectronicOrder eOrder) throws LIMSRuntimeException;
	
	public void updateData(ElectronicOrder eOrder) throws LIMSRuntimeException;

	public List<ElectronicOrder> getAllElectronicOrders();
	
	List<ElectronicOrder> getAllElectronicOrdersOrderedBy(String order);

}
