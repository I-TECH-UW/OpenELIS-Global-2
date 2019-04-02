package spring.generated.inventory.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.generated.forms.InventoryForm;
import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import spring.mine.internationalization.MessageUtil;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.dictionary.dao.DictionaryDAO;
import us.mn.state.health.lims.dictionary.daoimpl.DictionaryDAOImpl;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.inventory.action.InventoryUtility;
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

@Controller
@SessionAttributes("form")
public class ManageInventoryController extends BaseController {

	private List<InventoryKitItem> modifiedItems;
	private List<InventorySet> newInventory;
	private List<InventorySet> modifiedInventory;

	private OrganizationDAO organizationDAO = new OrganizationDAOImpl();

	@ModelAttribute("form")
	public BaseForm initForm() {
		return new InventoryForm();
	}

	@RequestMapping(value = "/ManageInventory", method = RequestMethod.GET)
	public ModelAndView showManageInventory(HttpServletRequest request, @ModelAttribute("form") BaseForm form)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		if (form.getClass() != InventoryForm.class) {
			form = new InventoryForm();
			request.getSession().setAttribute("form", form);
		}

		request.setAttribute(ALLOW_EDITS_KEY, "true");
		request.setAttribute(PREVIOUS_DISABLED, "true");
		request.setAttribute(NEXT_DISABLED, "true");
		request.getSession().setAttribute(SAVE_DISABLED, FALSE);

		InventoryUtility utility = new InventoryUtility();
		List<InventoryKitItem> list = utility.getExistingInventory();
		PropertyUtils.setProperty(form, "inventoryItems", list);

		List<String> kitTypes = getTestKitTypes();
		PropertyUtils.setProperty(form, "kitTypes", kitTypes);

		List<IdValuePair> sources = getSources();
		PropertyUtils.setProperty(form, "sources", sources);

		addFlashMsgsToRequest(request);
		return findForward(FWD_SUCCESS, form);
	}

	private List<String> getTestKitTypes() {
		List<String> types = new ArrayList<>();
		types.add(InventoryUtility.HIV);
		types.add(InventoryUtility.SYPHILIS);

		return types;
	}

	private List<IdValuePair> getSources() {
		List<IdValuePair> sources = new ArrayList<>();

		OrganizationDAO organizationDAO = new OrganizationDAOImpl();
		List<Organization> organizations = organizationDAO.getOrganizationsByTypeName("organizationName",
				"TestKitVender");

		for (Organization organization : organizations) {
			sources.add(new IdValuePair(organization.getId(), organization.getOrganizationName()));
		}

		return sources;
	}

	@RequestMapping(value = "/ManageInventoryUpdate", method = RequestMethod.POST)
	public ModelAndView showManageInventoryUpdate(HttpServletRequest request,
			@ModelAttribute("form") InventoryForm form, BindingResult result, RedirectAttributes redirectAttributes,
			SessionStatus status) {
		request.setAttribute(ALLOW_EDITS_KEY, "true");
		request.setAttribute(PREVIOUS_DISABLED, "true");
		request.setAttribute(NEXT_DISABLED, "true");

		setModifiedItems(form);
		createInventoryFromModifiedItems();
		createNewInventory(form);

		InventoryItemDAO itemDAO = new InventoryItemDAOImpl();

		Errors errors = validateNewInventory(itemDAO);

		if (errors.hasErrors()) {
			saveErrors(errors);
			return findForward(FWD_FAIL_INSERT, form);
		}

		InventoryLocationDAO locationDAO = new InventoryLocationDAOImpl();
		InventoryReceiptDAO receiptDAO = new InventoryReceiptDAOImpl();

		Transaction tx = HibernateUtil.getSession().beginTransaction();
		try {
			for (InventorySet inventory : modifiedInventory) {
				itemDAO.updateData(inventory.getItem());

				inventory.getLocation().setInventoryItem(inventory.getItem());
				locationDAO.updateData(inventory.getLocation());

				receiptDAO.updateData(inventory.getReceipt());
			}

			for (InventorySet inventory : newInventory) {
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
			return findForward(FWD_FAIL_INSERT, form);
		}

		status.setComplete();
		redirectAttributes.addFlashAttribute(FWD_SUCCESS, true);
		return findForward(FWD_SUCCESS_INSERT, form);
	}

	private Errors validateNewInventory(InventoryItemDAO itemDAO) {
		Errors errors = new BaseErrors();

		List<InventoryItem> items = itemDAO.getAllInventoryItems();
		List<String> names = new ArrayList<>();

		for (InventoryItem item : items) {
			names.add(item.getName());
		}

		for (InventorySet kitItem : newInventory) {
			for (String name : names) {
				if (name.equals(kitItem.getItem().getName())) {
					errors.reject("errors.DuplicateRecord",
							new String[] { MessageUtil.getMessage("inventory.testKit.name") },
							"errors.DuplicateRecord");
					return errors;
				}
			}
		}

		return errors;
	}

	private void createInventoryFromModifiedItems() {
		modifiedInventory = new ArrayList<>();

		for (InventoryKitItem kitItem : modifiedItems) {
			modifiedInventory.add(createInventorySetFromInventoryKitItem(kitItem));
		}
	}

	private InventorySet createInventorySetFromInventoryKitItem(InventoryKitItem kitItem) {
		InventoryItem item = new InventoryItem();
		InventoryLocation location = new InventoryLocation();
		InventoryReceipt receipt = new InventoryReceipt();

		InventorySet set = new InventorySet(item, location, receipt);

		item.setId(kitItem.getInventoryItemId());
		item.setIsActive(kitItem.getIsActive() ? "Y" : "N");
		item.setDescription(kitItem.getType());
		item.setName(kitItem.getKitName());
		item.setSysUserId(getSysUserId(request));

		location.setId(kitItem.getInventoryLocationId());
		location.setExpirationDate(DateUtil.convertStringDateToTruncatedTimestamp(kitItem.getExpirationDate()));
		location.setLotNumber(kitItem.getLotNumber());
		location.setSysUserId(getSysUserId(request));

		Organization organization = new Organization();
		organization.setId(kitItem.getOrganizationId());
		organizationDAO.getData(organization);

		receipt.setId(kitItem.getInventoryReceiptId());
		receipt.setOrganization(organization);
		receipt.setInventoryItemId(kitItem.getInventoryItemId());
		receipt.setReceivedDate(DateUtil.convertStringDateToTruncatedTimestamp(kitItem.getReceiveDate()));
		receipt.setSysUserId(getSysUserId(request));

		return set;
	}

	@SuppressWarnings("unchecked")
	private void createNewInventory(InventoryForm form) {
		newInventory = new ArrayList<>();

		String newInventoryXml = form.getString("newKitsXML");

		try {
			Document inventoryDom = DocumentHelper.parseText(newInventoryXml);

			for (Iterator i = inventoryDom.getRootElement().elementIterator("kit"); i.hasNext();) {

				Element kitItem = (Element) i.next();

				String kitName = kitItem.attributeValue("kitName");
				String receiveDate = kitItem.attributeValue("receive");
				String expirationDate = kitItem.attributeValue("expiration");
				String lotNumber = kitItem.attributeValue("lotNumber");
				String kitType = kitItem.attributeValue("kitType");
				String organizationId = kitItem.attributeValue("organizationId");

				InventorySet set = createInventorySet(kitName, receiveDate, expirationDate, lotNumber, kitType,
						organizationId);

				newInventory.add(set);
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private InventorySet createInventorySet(String kitName, String receiveDate, String expirationDate, String lotNumber,
			String kitType, String organizationId) throws LIMSRuntimeException {
		InventoryItem item = new InventoryItem();
		InventoryLocation location = new InventoryLocation();
		InventoryReceipt receipt = new InventoryReceipt();

		InventorySet set = new InventorySet(item, location, receipt);

		item.setIsActive("Y");
		item.setDescription(kitType);
		item.setName(kitName);
		item.setSysUserId(getSysUserId(request));

		location.setExpirationDate(DateUtil.convertStringDateToTruncatedTimestamp(expirationDate));
		location.setLotNumber(lotNumber);
		location.setSysUserId(getSysUserId(request));

		Organization organization = new Organization();
		organization.setId(organizationId);
		organizationDAO.getData(organization);

		receipt.setOrganization(organization);
		receipt.setReceivedDate(DateUtil.convertStringDateToTruncatedTimestamp(receiveDate));
		receipt.setSysUserId(getSysUserId(request));
		return set;
	}

	@SuppressWarnings("unchecked")
	private void setModifiedItems(InventoryForm form) {
		List<InventoryKitItem> allItems = (List<InventoryKitItem>) form.get("inventoryItems");
		modifiedItems = new ArrayList<>();

		if (allItems != null) {
			for (InventoryKitItem item : allItems) {
				if (item.getIsModified()) {
					modifiedItems.add(item);
				}
			}
		}
	}

	protected Errors validateAll(HttpServletRequest request, Errors errors, BaseForm form) throws Exception {

		// test validation against database
		String testNameSelected = (String) form.get("testName");

		if (!StringUtil.isNullorNill(testNameSelected)) {
			Test test = new Test();
			test.setTestName(testNameSelected);
			TestDAO testDAO = new TestDAOImpl();
			test = testDAO.getTestByName(test);

			String messageKey = "testresult.testName";

			if (test == null) {
				errors.reject("errors.invalid", new Object[] { MessageUtil.getMessage(messageKey) }, "errors.invalid");
			}
		}

		// scriptlet validation against database
		String scriptletSelected = (String) form.get("scriptletName");

		if (!StringUtil.isNullorNill(scriptletSelected)) {
			Scriptlet scriptlet = new Scriptlet();
			scriptlet.setScriptletName(scriptletSelected);
			ScriptletDAO scriptletDAO = new ScriptletDAOImpl();
			scriptlet = scriptletDAO.getScriptletByName(scriptlet);

			String messageKey = "testresult.scriptletName";

			if (scriptlet == null) {
				errors.reject("errors.invalid", new Object[] { MessageUtil.getMessage(messageKey) }, "errors.invalid");
			}
		}

		// validate for testResult D -> value must be dictionary ID
		String testResultType = (String) form.get("testResultType");

		if (testResultType.equals("D")) {
			String val = (String) form.get("value");
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
					errors.reject("errors.invalid", new Object[] { MessageUtil.getMessage(messageKey) },
							"errors.invalid");
				}
			} catch (NumberFormatException nfe) {
				// bugzilla 2154
				LogEvent.logError("TestResultUpdateAction", "validateAll()", nfe.toString());
				errors.reject("errors.invalid", new Object[] { MessageUtil.getMessage(messageKey) }, "errors.invalid");
			}
		}

		return errors;
	}

	@Override
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "manageInventoryDefinition";
		} else if (FWD_SUCCESS_INSERT.equals(forward)) {
			return "redirect:/ManageInventory.do";
		} else if (FWD_FAIL_INSERT.equals(forward)) {
			return "manageInventoryDefinition";
		} else {
			return "PageNotFound";
		}
	}

	@Override
	protected String getPageTitleKey() {
		return "inventory.manage.title";
	}

	@Override
	protected String getPageSubtitleKey() {
		return "inventory.manage.title";
	}

	class InventorySet {

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
