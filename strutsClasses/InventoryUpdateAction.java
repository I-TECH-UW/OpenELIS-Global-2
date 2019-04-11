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
package us.mn.state.health.lims.inventory.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.hibernate.Transaction;

import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.validator.ActionError;
import us.mn.state.health.lims.dictionary.dao.DictionaryDAO;
import us.mn.state.health.lims.dictionary.daoimpl.DictionaryDAOImpl;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.inventory.dao.InventoryItemDAO;
import us.mn.state.health.lims.inventory.dao.InventoryLocationDAO;
import us.mn.state.health.lims.inventory.dao.InventoryReceiptDAO;
import us.mn.state.health.lims.inventory.daoimpl.InventoryItemDAOImpl;
import us.mn.state.health.lims.inventory.daoimpl.InventoryLocationDAOImpl;
import us.mn.state.health.lims.inventory.daoimpl.InventoryReceiptDAOImpl;
import us.mn.state.health.lims.inventory.form.InventoryKitItem;
import us.mn.state.health.lims.inventory.valueholder.InventoryItem;
import us.mn.state.health.lims.inventory.valueholder.InventoryLocation;
import us.mn.state.health.lims.inventory.valueholder.InventoryReceipt;
import us.mn.state.health.lims.organization.dao.OrganizationDAO;
import us.mn.state.health.lims.organization.daoimpl.OrganizationDAOImpl;
import us.mn.state.health.lims.organization.valueholder.Organization;
import us.mn.state.health.lims.scriptlet.dao.ScriptletDAO;
import us.mn.state.health.lims.scriptlet.daoimpl.ScriptletDAOImpl;
import us.mn.state.health.lims.scriptlet.valueholder.Scriptlet;
import us.mn.state.health.lims.test.dao.TestDAO;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;
import us.mn.state.health.lims.test.valueholder.Test;

public class InventoryUpdateAction extends BaseAction {

	private List<InventoryKitItem> modifiedItems;
	private List<InventorySet> newInventory;
	private List<InventorySet> modifiedInventory;
	private String sysUserId;
	private OrganizationDAO organizationDAO = new OrganizationDAOImpl();
	
	protected ActionForward performAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String forward = FWD_SUCCESS;
		request.setAttribute(ALLOW_EDITS_KEY, "true");
		request.setAttribute(PREVIOUS_DISABLED, "true");
		request.setAttribute(NEXT_DISABLED, "true");

		sysUserId  = getSysUserId(request);
		
		BaseActionForm dynaForm = (BaseActionForm) form;

		setModifiedItems(dynaForm);
		createInventoryFromModifiedItems();
		createNewInventory(dynaForm);

		InventoryItemDAO itemDAO = new InventoryItemDAOImpl();
	
		ActionMessages errors = validateNewInventory( itemDAO );

		if (errors.size() > 0) {
			saveErrors(request, errors);
			return mapping.findForward(FWD_FAIL);
		}

		InventoryLocationDAO locationDAO = new InventoryLocationDAOImpl();
		InventoryReceiptDAO receiptDAO = new InventoryReceiptDAOImpl();
		
		Transaction tx = HibernateUtil.getSession().beginTransaction();
		try {
			for( InventorySet inventory : modifiedInventory){
				itemDAO.updateData(inventory.getItem());
				
				inventory.getLocation().setInventoryItem(inventory.getItem());
				locationDAO.updateData(inventory.getLocation());
				
				receiptDAO.updateData(inventory.getReceipt());
			}
			
			for( InventorySet inventory : newInventory){
				itemDAO.insertData(inventory.getItem());
				
				String id = inventory.getItem().getId();
				inventory.getLocation().setInventoryItem(inventory.getItem());
				inventory.getReceipt().setInventoryItemId(id);
				
				locationDAO.insertData(inventory.getLocation());
				receiptDAO.insertData(inventory.getReceipt());
			}
			
			tx.commit();
		} catch (LIMSRuntimeException lre) {
			tx.rollback();
			forward = FWD_FAIL;
		}

		return mapping.findForward(forward);
	}

	private ActionMessages validateNewInventory(InventoryItemDAO itemDAO) {
		ActionMessages errors = new ActionMessages();
		
		List<InventoryItem> items = itemDAO.getAllInventoryItems();
		List<String> names = new ArrayList<String>();
		
		for( InventoryItem item : items){
			names.add(item.getName());
		}
		
		
		for( InventorySet kitItem : newInventory){
			for( String name : names ){
				if( name.equals(kitItem.getItem().getName())){
					ActionError error = new ActionError("errors.DuplicateRecord" ,StringUtil.getMessageForKey("inventory.testKit.name"), null );
					errors.add(ActionMessages.GLOBAL_MESSAGE, error);
					return errors;
				}
			}
		}
		
		return errors;
	}

	private void createInventoryFromModifiedItems() {
		modifiedInventory = new ArrayList<InventorySet>();
		
		for( InventoryKitItem kitItem : modifiedItems){
			modifiedInventory.add( createInventorySetFromInventoryKitItem(kitItem));
		}
	}

	private InventorySet createInventorySetFromInventoryKitItem(InventoryKitItem kitItem) {
		InventoryItem item = new InventoryItem();
		InventoryLocation location = new InventoryLocation();
		InventoryReceipt receipt = new InventoryReceipt();

		InventorySet set = new InventorySet( item, location, receipt);
		
		item.setId(kitItem.getInventoryItemId());
		item.setIsActive(kitItem.getIsActive() ? "Y" : "N");
		item.setDescription(kitItem.getType());
		item.setName(kitItem.getKitName());
		item.setSysUserId(sysUserId);
		
		location.setId(kitItem.getInventoryLocationId());
		location.setExpirationDate(DateUtil.convertStringDateToTruncatedTimestamp(kitItem.getExpirationDate()));
		location.setLotNumber(kitItem.getLotNumber());
		location.setSysUserId(sysUserId);
		
		Organization organization = new Organization();
		organization.setId(kitItem.getOrganizationId());
		organizationDAO.getData(organization);
		
		receipt.setId(kitItem.getInventoryReceiptId());
		receipt.setOrganization(organization);
		receipt.setInventoryItemId(kitItem.getInventoryItemId());
		receipt.setReceivedDate(DateUtil.convertStringDateToTruncatedTimestamp(kitItem.getReceiveDate()));
		receipt.setSysUserId(sysUserId);
		
		
		return set;
	}

	
	@SuppressWarnings("unchecked")
	private void createNewInventory(BaseActionForm dynaForm) {
		newInventory = new ArrayList<InventorySet>();

		String newInventoryXml = dynaForm.getString("newKitsXML");

		try{
		Document inventoryDom = DocumentHelper.parseText(newInventoryXml);
		
		 for ( Iterator i = inventoryDom.getRootElement().elementIterator( "kit" ); i.hasNext(); ) {
			 
			 	Element kitItem = (Element) i.next();
	            
	            String kitName = kitItem.attributeValue("kitName");
	            String receiveDate = kitItem.attributeValue("receive");
	            String expirationDate = kitItem.attributeValue("expiration");
	            String lotNumber = kitItem.attributeValue("lotNumber");
	            String kitType = kitItem.attributeValue("kitType");
	            String organizationId = kitItem.attributeValue("organizationId");
	            
	            InventorySet set = createInventorySet(kitName, 
	            									  receiveDate, 
	            									  expirationDate, 
	            									  lotNumber, 
	            									  kitType,
	            									  organizationId);
	            
	    		newInventory.add(set);
		 }
	} catch (DocumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}

	private InventorySet createInventorySet(String kitName, String receiveDate, String expirationDate,
			String lotNumber, String kitType, String organizationId) throws LIMSRuntimeException {
		InventoryItem item = new InventoryItem();
		InventoryLocation location = new InventoryLocation();
		InventoryReceipt receipt = new InventoryReceipt();
		
		InventorySet set = new InventorySet(item, location, receipt);
		
		item.setIsActive( "Y" );
		item.setDescription(kitType);
		item.setName(kitName);
		item.setSysUserId(sysUserId);
		
		location.setExpirationDate(DateUtil.convertStringDateToTruncatedTimestamp(expirationDate));
		location.setLotNumber(lotNumber);
		location.setSysUserId(sysUserId);
		
		Organization organization = new Organization();
		organization.setId(organizationId);
		organizationDAO.getData(organization);
		
		receipt.setOrganization(organization);
		receipt.setReceivedDate(DateUtil.convertStringDateToTruncatedTimestamp(receiveDate));
		receipt.setSysUserId(sysUserId);
		return set;
	}

	@SuppressWarnings("unchecked")
	private void setModifiedItems(BaseActionForm dynaForm) {
		List<InventoryKitItem> allItems = (List<InventoryKitItem>) dynaForm.get("inventoryItems");
		modifiedItems = new ArrayList<InventoryKitItem>();


		for (InventoryKitItem item : allItems) {
			if (item.getIsModified()) {
				modifiedItems.add(item);
			}
		}
	}

	protected String getPageTitleKey() {
		return "inventory.manage.title";
	}

	protected String getPageSubtitleKey() {
		return "inventory.manage.title";
	}

	protected ActionMessages validateAll(HttpServletRequest request,
			ActionMessages errors, BaseActionForm dynaForm) throws Exception {

		// test validation against database
		String testNameSelected = (String) dynaForm.get("testName");

		if (!StringUtil.isNullorNill(testNameSelected)) {
			Test test = new Test();
			test.setTestName(testNameSelected);
			TestDAO testDAO = new TestDAOImpl();
			test = testDAO.getTestByName(test);

			String messageKey = "testresult.testName";

			if (test == null) {
				// the test is not in database - not valid
				// testName
				ActionError error = new ActionError("errors.invalid",
						getMessageForKey(messageKey), null);
				errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			}
		}

		// scriptlet validation against database
		String scriptletSelected = (String) dynaForm.get("scriptletName");

		if (!StringUtil.isNullorNill(scriptletSelected)) {
			Scriptlet scriptlet = new Scriptlet();
			scriptlet.setScriptletName(scriptletSelected);
			ScriptletDAO scriptletDAO = new ScriptletDAOImpl();
			scriptlet = scriptletDAO.getScriptletByName(scriptlet);

			String messageKey = "testresult.scriptletName";

			if (scriptlet == null) {
				// the scriptlet is not in database - not valid
				// parentScriptlet
				ActionError error = new ActionError("errors.invalid",
						getMessageForKey(messageKey), null);
				errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			}
		}

		// validate for testResult D -> value must be dictionary ID
		String testResultType = (String) dynaForm.get("testResultType");

		if (testResultType.equals("D")) {
			String val = (String) dynaForm.get("value");
			String messageKey = "testresult.value";
			try {
				Integer.parseInt(val);

				Dictionary dictionary = new Dictionary();
				dictionary.setId(val);
				DictionaryDAO dictDAO = new DictionaryDAOImpl();
				List dictionarys = dictDAO.getAllDictionarys();

				boolean found = false;
				for (int i = 0; i < dictionarys.size(); i++) {
					Dictionary d = (Dictionary) dictionarys.get(i);
					if (dictionary.getId().equals(d.getId())) {
						found = true;
					}
				}

				if (!found) {
					ActionError error = new ActionError("errors.invalid",
							getMessageForKey(messageKey), null);
					errors.add(ActionMessages.GLOBAL_MESSAGE, error);
				}
			} catch (NumberFormatException nfe) {
    			//bugzilla 2154
			    LogEvent.logError("TestResultUpdateAction","validateAll()",nfe.toString());
				ActionError error = new ActionError("errors.invalid",
						getMessageForKey(messageKey), null);
				errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			}
		}

		return errors;
	}

	class InventorySet{
		
		private InventoryLocation location;
		private InventoryItem item;
		private InventoryReceipt receipt;
		
		
		public InventorySet(InventoryItem item, InventoryLocation location, InventoryReceipt receipt) {
			this.item = item;
			this.location = location;
			this.receipt = receipt;
		}
		public InventoryLocation getLocation() {
			return location;
		}

		public InventoryItem getItem() {
			return item;
		}
		
		public InventoryReceipt getReceipt() {
			return receipt;
		}
	}
}