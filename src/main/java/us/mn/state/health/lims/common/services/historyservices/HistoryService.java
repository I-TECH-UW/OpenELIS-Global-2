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
package us.mn.state.health.lims.common.services.historyservices;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.validator.GenericValidator;

import us.mn.state.health.lims.audittrail.action.workers.AuditTrailItem;
import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.audittrail.valueholder.History;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.TypeOfTestResultService;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.dictionary.dao.DictionaryDAO;
import us.mn.state.health.lims.dictionary.daoimpl.DictionaryDAOImpl;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.systemuser.dao.SystemUserDAO;
import us.mn.state.health.lims.systemuser.daoimpl.SystemUserDAOImpl;
import us.mn.state.health.lims.systemuser.valueholder.SystemUser;

public abstract class HistoryService {

	protected static final String STATUS_ATTRIBUTE = "status";
	protected static final String VALUE_ATTRIBUTE = "value";

	protected static AuditTrailDAO auditTrailDAO = new AuditTrailDAOImpl();
	private static SystemUserDAO userDAO = new SystemUserDAOImpl();
	protected static DictionaryDAO dictDAO = new DictionaryDAOImpl();

	protected Map<String, String> attributeToIdentifierMap;
	protected List<History> historyList;
	protected String identifier = "";

	protected Map<String, String> newValueMap;

	protected HistoryService() {
	}

	protected abstract void addInsertion(History history, List<AuditTrailItem> items);

	protected abstract String getObjectName();

	protected abstract void getObservableChanges(History history, Map<String, String> changeMap, String changes);

	// should be overridden if needed
	protected boolean showAttribute() {
		return false;
	}

	
	private void reverseSortByTime(List<History> list) {
		Collections.sort(list, new Comparator<History>() {
			@Override
			public int compare(History o1, History o2) {
				return o2.getTimestamp().compareTo(o1.getTimestamp());
			}
		});
	}

	public List<AuditTrailItem> getAuditTrailItems() {
		reverseSortByTime(historyList);
		List<AuditTrailItem> items = new ArrayList<AuditTrailItem>();

		for (History history : historyList) {
			try {
				if ("U".equals(history.getActivity()) || "D".equals(history.getActivity())) {
					Map<String, String> changeMaps = getChangeMap(history);

					if (!changeMaps.isEmpty()) {
						addItemsForKeys(items, history, changeMaps);
					}
				} else {
					addInsertion(history, items);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return items;
	}

	protected void addItemsForKeys(List<AuditTrailItem> items, History history, Map<String, String> changeMaps) {
		for (String key : changeMaps.keySet()) {
			setIdentifierForKey(key);
			AuditTrailItem item = getCoreTrail(history);
            item.setAttribute(showAttribute() && !GenericValidator.isBlankOrNull( key ) ? key : StringUtil.getMessageForKey( "auditTrail.action.update" ));
			item.setOldValue(changeMaps.get(key));
			item.setNewValue(newValueMap.get(key));
			newValueMap.put(key, item.getOldValue());
			if (item.newOldDiffer()) {
				items.add(item);
			}
		}
	}

	protected void setAndAddIfValueNotNull(List<AuditTrailItem> items, History history, String attribute) {
		String value = newValueMap.get(attribute);
		if (!GenericValidator.isBlankOrNull(value)) {
			setIdentifierForKey(attribute);
			AuditTrailItem item;
			item = getCoreTrail(history);
			item.setNewValue(value);
			items.add(item);
			newValueMap.remove(attribute);
		}
	}

	protected void setIdentifierForKey(String key) {
		if (attributeToIdentifierMap != null && attributeToIdentifierMap.get(key) != null) {
			identifier = attributeToIdentifierMap.get(key);
		}

	}

	protected AuditTrailItem getCoreTrail(History history) {
		AuditTrailItem ati = new AuditTrailItem();
		ati.setTimeStamp(history.getTimestamp());
		ati.setDate(DateUtil.convertTimestampToStringDate(history.getTimestamp()));
		ati.setTime(DateUtil.convertTimestampToStringTime(history.getTimestamp()));
		ati.setAction(history.getActivity());
		ati.setUser(getUserName(history));
		ati.setItem(getObjectName());
		ati.setIdentifier(identifier);
		return ati;
	}

	private Map<String, String> getChangeMap(History history) throws SQLException, IOException {
		Map<String, String> changeMap = new HashMap<String, String>();
	    //System.out.println( history.getId() + " : " + history.getActivity() );
		if ("U".equals(history.getActivity()) || "D".equals(history.getActivity())) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);

			byte[] bindata = new byte[1024];
			int bytesread;
			BufferedInputStream bis = new BufferedInputStream(history.getChanges().getBinaryStream());
			if ((bytesread = bis.read(bindata, 0, bindata.length)) != -1) {
				baos.write(bindata, 0, bytesread);
			}

			String changes = baos.toString();
			// System.out.println(history.getActivity() + " : "+ changes);
			getObservableChanges(history, changeMap, changes);

		}

		return changeMap;
	}

	protected void simpleChange(Map<String, String> changeMap, String changesString, String attribute) {
		String value = extractSimple(changesString, attribute);
		if (value != null) {
			changeMap.put(attribute, value);
		}
	}

	protected String extractSimple(String changes, String attribute) {
		String startTag = "<" + attribute + ">";
		int begin = changes.indexOf(startTag);
		if (begin > -1) {
			begin += startTag.length();
			int end = changes.indexOf("</" + attribute + ">");

			return changes.substring(begin, end);
		}
		return null;
	}

	protected String extractStatus(String changes) {
		String statusId = extractSimple(changes, "statusId");
		return statusId == null ? null : StatusService.getInstance().getStatusNameFromId(statusId);
	}

	protected String getViewableValue(String value, Result result) {
		if ( TypeOfTestResultService.ResultType.isDictionaryVariant(result.getResultType()) && !GenericValidator.isBlankOrNull(value) && org.apache.commons.lang.StringUtils.isNumeric(value)) {
			Dictionary dictionaryValue = dictDAO.getDictionaryById(value);
			value = dictionaryValue != null ? dictionaryValue.getDictEntry() : StringUtil.getMessageForKey("result.undefined");
		}

		return value;
	}

	private String getUserName(History history) {
		SystemUser user = userDAO.getUserById(history.getSysUserId());
		return user.getDisplayName();
	}

}
