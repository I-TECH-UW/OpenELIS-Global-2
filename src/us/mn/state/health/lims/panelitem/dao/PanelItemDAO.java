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
package us.mn.state.health.lims.panelitem.dao;

import java.util.List;

import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.panel.valueholder.Panel;
import us.mn.state.health.lims.panelitem.valueholder.PanelItem;

/**
 * @author diane benz
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public interface PanelItemDAO extends BaseDAO {

	public boolean insertData(PanelItem panelItem) throws LIMSRuntimeException;

	public void deleteData(List panelItems) throws LIMSRuntimeException;

	public List getAllPanelItems() throws LIMSRuntimeException;

	public List getPageOfPanelItems(int startingRecNo)
			throws LIMSRuntimeException;

	public void getData(PanelItem panelItem) throws LIMSRuntimeException;

	public void updateData(PanelItem panelItem) throws LIMSRuntimeException;

	public List getPanelItems(String filter) throws LIMSRuntimeException;

	public List getNextPanelItemRecord(String id) throws LIMSRuntimeException;

	public List getPreviousPanelItemRecord(String id)
			throws LIMSRuntimeException;

	public Integer getTotalPanelItemCount() throws LIMSRuntimeException; 
	
	public boolean getDuplicateSortOrderForPanel(PanelItem panelItem);
	
	public List getPanelItemByPanel(Panel panel, boolean onlyTestsFullySetup) throws LIMSRuntimeException;

	public List getPanelItemsForPanel(String panelId) throws LIMSRuntimeException;

	public List<PanelItem> getPanelItemByTestId(String id) throws LIMSRuntimeException;

	public List<PanelItem> getPanelItemsForPanelAndItemList(String panelId, List<Integer> testList) throws LIMSRuntimeException;
}
