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
package us.mn.state.health.lims.typeofsample.dao;

import java.util.List;

import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSamplePanel;

public interface TypeOfSamplePanelDAO extends BaseDAO {
	
	public boolean insertData(TypeOfSamplePanel typeOfSamplePanel)	throws LIMSRuntimeException;

	public void deleteData(String[] typeOfSamplePanelIds, String currentUserId) throws LIMSRuntimeException;

	public List getAllTypeOfSamplePanels() throws LIMSRuntimeException;

	public List getPageOfTypeOfSamplePanel(int startingRecNo) throws LIMSRuntimeException;

	public void getData(TypeOfSamplePanel typeOfSamplePanel) throws LIMSRuntimeException;
		
	public List getNextTypeOfSamplePanelRecord(String id) throws LIMSRuntimeException;

	public List getPreviousTypeOfSamplePanelRecord(String id) throws LIMSRuntimeException;

	public Integer getTotalTypeOfSamplePanelCount() throws LIMSRuntimeException;	
	
	public List<TypeOfSamplePanel> getTypeOfSamplePanelsForSampleType(String sampleType);

	public List<TypeOfSamplePanel> getTypeOfSamplePanelsForPanel(String panelId) throws LIMSRuntimeException;	
}
