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
package us.mn.state.health.lims.audittrail.daoimpl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Vector;

import org.apache.struts.util.LabelValueBean;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.valueholder.History;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.referencetables.dao.ReferenceTablesDAO;
import us.mn.state.health.lims.referencetables.daoimpl.ReferenceTablesDAOImpl;
import us.mn.state.health.lims.referencetables.valueholder.ReferenceTables;


public class AuditTrailDAOImpl extends BaseDAOImpl implements AuditTrailDAO {

	//For an insert log the id, sys_user_id, ref id, reftable, timestamp, activity (='I').  The change column would be blank, since the
	//before data did not contain anything.  Note: This requires making the changes column (in history table) nullable
	public void saveNewHistory(Object newObject, String sysUserId, String tableName) throws LIMSRuntimeException{

		ReferenceTablesDAO referenceTablesDAO = new ReferenceTablesDAOImpl();
		ReferenceTables referenceTables = new ReferenceTables();
		referenceTables.setTableName(tableName);
		ReferenceTables referenceTable = referenceTablesDAO.getReferenceTableByName(referenceTables);

		//bugzilla 2111: if keepHistory is N then return - don't throw exception
		if (referenceTable != null && !referenceTable.getKeepHistory().equals(YES)) {
			LogEvent.logDebug("AuditTrailDAOImpl","saveNewHistory()","NO CHANGES: REF TABLE KEEP_HISTORY IS N");
			return;
		}
		//if logging failes an exception should be thrown so that INSERT/UPDATE is rolled back
		if ( referenceTable == null ) {
			LogEvent.logDebug("AuditTrailDAOImpl","saveNewHistory()","NO CHANGES: REF TABLE IS NULL");
			throw new LIMSRuntimeException("Reference Table is null in AuditTrailDAOImpl saveNewHistory()");
		}

		if ( (sysUserId == null) || (sysUserId.length()==0) ) {
			LogEvent.logDebug("AuditTrailDAOImpl","saveNewHistory()","NO CHANGES: SYS_USER_ID IS NULL");
			throw new LIMSRuntimeException("System User ID is null in AuditTrailDAOImpl saveNewHistory()");
		}

		if ( newObject==null || tableName == null) {
			LogEvent.logDebug("AuditTrailDAOImpl","saveNewHistory()","NO CHANGES: EITHER OBJECT or TABLE NAME IS NULL");
			throw new LIMSRuntimeException("Either new object or table name is null in AuditTrailDAOImpl saveNewHistory()");
		}

		History hist = new History();

		try {
		Method m1 =	newObject.getClass().getMethod("getId", new Class[0]);
		String referenceId = (String)m1.invoke(newObject, (Object[])new Class[0]);
		hist.setReferenceId(referenceId);
		hist.setSysUserId(sysUserId);

		Method m3 = newObject.getClass().getMethod("getLastupdated", new Class[0]);
		Timestamp timestamp = (Timestamp)m3.invoke(newObject, (Object[])new Class[0]);
		if ( timestamp == null )
			timestamp = new Timestamp(System.currentTimeMillis());

		hist.setTimestamp(timestamp);
		hist.setActivity(IActionConstants.AUDIT_TRAIL_INSERT);
		hist.setReferenceTable( referenceTable.getId() );
		insertData(hist);
		} catch (Exception e) {
			LogEvent.logError("AuditTrailDAOImpl","saveNewHistory()",e.toString());
			throw new LIMSRuntimeException("Error occurred logging INSERT", e);
		}

	}

	public void saveHistory(Object newObject, Object existingObject,
			 				String sysUserId, String event, String tableName) throws LIMSRuntimeException {

    	//bugzilla 2571 go through ReferenceTablesDAO to get reference tables info
		ReferenceTablesDAO referenceTablesDAO = new ReferenceTablesDAOImpl();
		ReferenceTables referenceTables = new ReferenceTables();
		referenceTables.setTableName(tableName);
		ReferenceTables rt = referenceTablesDAO.getReferenceTableByName(referenceTables);

//		bugzilla 2111: if keepHistory is N then return - don't throw exception
		if (rt != null && !rt.getKeepHistory().equals(YES)) {
			//bugzilla 2154
			LogEvent.logDebug("AuditTrailDAOImpl","saveHistory()","NO CHANGES: REF TABLE KEEP_HISTORY IS N");
			return;
		}
		if ( rt == null ) {
			//bugzilla 2154
			LogEvent.logDebug("AuditTrailDAOImpl","saveHistory()","NO CHANGES: REF TABLE IS NULL");
			//bugzilla 1926
			throw new LIMSRuntimeException("Reference Table is null in AuditTrailDAOImpl saveHistory()");
		}

		if ( (sysUserId == null) || (sysUserId.length()==0) ) {
			//bugzilla 2154
			LogEvent.logDebug("AuditTrailDAOImpl","saveHistory()","NO CHANGES: SYS_USER_ID IS NULL");
			//bugzilla 1926
			throw new LIMSRuntimeException("System User ID is null in AuditTrailDAOImpl saveHistory() for table " + tableName);
		}

		if ( newObject==null || existingObject==null || event==null || tableName == null) {
			//bugzilla 2154
			LogEvent.logDebug("AuditTrailDAOImpl","saveHistory()","NO CHANGES: EITHER OBJECTS or EVENT or TABLE NAME IS NULL");
			//bugzilla 1926
			throw new LIMSRuntimeException("New object, existing object, table name or event is null in AuditTrailDAOImpl saveHistory()");
		}

		try {
			String xml = getChanges(newObject,existingObject,tableName);

			if ( (xml != null) && (xml.length()>0) ) {
				History hist = new History();

				Method m1 =
					existingObject.getClass().getMethod("getId", new Class[0]);
				String referenceId = (String)m1.invoke(existingObject, (Object[])new Class[0]);
				hist.setReferenceId(referenceId);
				hist.setSysUserId(sysUserId);

				byte[] bytes = xml.getBytes();
				hist.setChanges(Hibernate.createBlob(bytes));

				Method m3 =
					existingObject.getClass().getMethod("getLastupdated", new Class[0]);
				//java.sql.Timestamp ts = (java.sql.Timestamp)m3.invoke(existingObject, (Object[])new Class[0]);
				//if ( ts == null )
				//bugzilla 2574
				java.sql.Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());

				hist.setTimestamp(ts);
				hist.setActivity(event);
				hist.setReferenceTable(rt.getId());
				insertData(hist);
			}
		} catch(Exception e) {
			//buzilla 2154
			LogEvent.logError("AuditTrailDAOImpl","saveHistory()",e.toString());
			throw new LIMSRuntimeException("Error in AuditTrail saveHistory()", e);
		}
	}

	/**
     * Returns an array of all fields used by this object from it's class and all superclasses.
     * @param objectClass the class
     * @param fields the current field list
     * @return an array of fields
     */
    private Field[] getAllFields(Class objectClass, Field[] fields) {

        Field[] newFields = objectClass.getDeclaredFields();

        int fieldsSize = 0;
        int newFieldsSize = 0;

        if(fields != null) {
            fieldsSize = fields.length;
        }

        if(newFields != null) {
            newFieldsSize = newFields.length;
        }

        Field[] totalFields = new Field[fieldsSize + newFieldsSize];

        if(fieldsSize > 0) {
        	System.arraycopy(fields, 0, totalFields, 0, fieldsSize);
        }

        if(newFieldsSize > 0) {
        	System.arraycopy(newFields, 0, totalFields, fieldsSize, newFieldsSize);
        }

        Class superClass = objectClass.getSuperclass();

        Field[] finalFieldsArray;

        if (superClass != null && ! superClass.getName().equals("java.lang.Object")) {
        	finalFieldsArray = getAllFields(superClass, totalFields);
        } else {
        	finalFieldsArray = totalFields;
        }

        return finalFieldsArray;
    }

	/**
	 * Logs changes to persistent data
	 * @param newObject the object being saved, updated or deleted
	 * @param existingObject the existing object in the database.  Used only for updates
	 * @param tableName the name of the table being logged.
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private String getChanges(Object newObject, Object existingObject, String tableName) {

		//bugzilla 1857
		Vector<Object> optionList = new Vector<Object>();
		Class objectClass = newObject.getClass();
		//get an array of all fields in the class including those in superclasses if this is a subclass.
		Field[] fields = getAllFields(objectClass, null);

		// Iterate through all the fields in the object
		fieldIteration: for (int ii = 0; ii < fields.length; ii++) {

			//make private fields accessible so we can access their values
			fields[ii].setAccessible(true);

			//if the current field is static, transient or final then don't log it as
			//these modifiers are v.unlikely to be part of the data model.
			if( Modifier.isTransient( fields[ ii ].getModifiers() )
					|| Modifier.isFinal(fields[ii].getModifiers())
					|| Modifier.isStatic(fields[ii].getModifiers())) {
	              	continue fieldIteration;
			}

			String fieldName = fields[ii].getName();
			if( (!fieldName.equals("id"))
			        //bugzilla 2574
					//&& (!fieldName.equals("lastupdated"))
					&& (!fieldName.equals("sysUserId"))
					&& (!fieldName.equals("systemUser"))
					&& (!fieldName.equals("originalLastupdated")) ) {
					//bugzilla 2578
					//&& (!fieldName.equals("collectionDateForDisplay"))
					//&& (!fieldName.equals("collectionTimeForDisplay")) ) {
				Class interfaces[] = fields[ii].getType().getInterfaces();
				for (int i = 0; i < interfaces.length;) {
					if (interfaces[i].getName().equals("java.util.Collection")) {
						continue fieldIteration;
					}
					i++;
				}

	          	String propertyNewState;
	          	String propertyPreUpdateState;

	          	//get new field values
	          	try {
	          		Object objPropNewState = fields[ii].get(newObject);
	          		if (objPropNewState != null) {
	          			propertyNewState = objPropNewState.toString();
	          		} else {
	          			propertyNewState = "";
	          		}

	          	} catch (Exception e) {
	          		//buzilla 2154
	    			LogEvent.logError("AuditTrailDAOImpl","getChanges()",e.toString());
	          		propertyNewState = "";
	          	}

          		try {
          			Object objPreUpdateState = fields[ii].get(existingObject);
          			if (objPreUpdateState != null) {
         				propertyPreUpdateState = objPreUpdateState.toString();
       				} else {
          				propertyPreUpdateState = "";
          			}
          		} catch (IllegalArgumentException iae) {
          			propertyPreUpdateState = "";
          		} catch (Exception e) {
	          		//buzilla 2154
	    			LogEvent.logError("AuditTrailDAOImpl","getChanges()",e.toString());
          			propertyPreUpdateState = "";
          		}

          		//bugzilla 2134 fixed the analysis_qaevent completed date problem
          		//bugzilla 2122 fixed the sample collection date problem
				if (fieldName.equals("qaEvent") || fieldName.equals("sample")) {
					LabelValueBean lvb = processLabelValueFixes(fieldName,propertyPreUpdateState,existingObject,newObject);
					if ( lvb != null ) {
          				String label = lvb.getLabel();
          				String value = lvb.getValue();
          				optionList.add(new LabelValueBean(label,value));
          			}
				} else {
					// Ignore the parent class if any, only compare the current level
					if ( propertyNewState.startsWith("{us.mn.state.health.lims") )
						propertyNewState = propertyPreUpdateState;
					if ( propertyPreUpdateState.startsWith("{us.mn.state.health.lims"))
						propertyPreUpdateState = propertyNewState;

	         		//LogEvent.logInfo("AuditTrailDAOImpl","getChanges","TABLE NAME: " + tableName);
	         		//LogEvent.logInfo("AuditTrailDAOImpl","getChanges","FIELD NAME: " + fieldName);
	         		//LogEvent.logInfo("AuditTrailDAOImpl","getChanges","PRE UPDATE: " + propertyPreUpdateState);
	         		//LogEvent.logInfo("AuditTrailDAOImpl","getChanges","NEW UPDATE: " + propertyNewState);
	         		//LogEvent.logInfo("","","\n");

					// Now we have the two property values - compare them
					if (propertyNewState.equals(propertyPreUpdateState)) {
						continue; // Values haven't changed so loop to next property
					} else  {
						LabelValueBean lvb = processLabelValue(fieldName,propertyPreUpdateState,existingObject,newObject);
						if ( lvb != null ) {
							optionList.add(new LabelValueBean(lvb.getLabel(),lvb.getValue()));
						}
					}
				}
			}
		}

		String xml = null;
		if ( optionList.size()>0 )
			xml = getXMLFormat(optionList);

		return xml;
	}

	/**
	 * Process and compare the child value objects using java reflection
	 * @param fieldName the method name
	 * @param propertyPreUpdateState the previous value
	 * @param existingObject the old data object
	 * @param newObject the new data object
	 * @return a label value object
	 * bugzilla 2134 fixed the analysis_qaevent completed date problem
	 * bugzilla 2122 fixed the sample collection_date problem
	 */
	private LabelValueBean processLabelValueFixes(String fieldName, String propertyPreUpdateState,
			                       Object existingObject, Object newObject) {
		LabelValueBean lvb = null;
		Method m1;
		Method m2;
		Object o1 = null;
		Object o2 = null;

		try {
			if ( fieldName.equals("qaEvent")) {
				fieldName = "completedDate";
				m1 = existingObject.getClass().getMethod("getCompletedDate", new Class[0]);
				o1 = m1.invoke(existingObject, (Object[])new Class[0]);

				m2 = newObject.getClass().getMethod("getCompletedDate", new Class[0]);
				o2 = m2.invoke(newObject, (Object[])new Class[0]);

			}
			if ( fieldName.equals("sample")) {
				fieldName = "collectionDate";
				try {
					m1 = existingObject.getClass().getMethod("getCollectionDate", new Class[0]);
					o1 = m1.invoke(existingObject, (Object[])new Class[0]);

					m2 = newObject.getClass().getMethod("getCollectionDate", new Class[0]);
					o2 = m2.invoke(newObject, (Object[])new Class[0]);
				} catch (NoSuchMethodException nsme) {
					//ignore for SampleItem (which does not have getCollectionDate method
				}
			}

			String oldID = "";
			String newID = "";
			if ( o1 != null ) {
				oldID = o1.toString();
			}

			if ( o2 != null ) {
				newID = o2.toString();
			}

			if ( oldID.compareTo(newID)==0 ) {
				fieldName = null;
				propertyPreUpdateState = null;
			}
			else {
				propertyPreUpdateState = newID;
			}

		} catch (Exception e) {
      		//buzilla 2154
			LogEvent.logError("AuditTrailDAOImpl","processLabelValueFixes()",e.toString());
			throw new LIMSRuntimeException("Error in AuditTrail processLabelValueFixes()", e);
		}

		if ( ((fieldName != null) && (fieldName.length()>0)) &&
				 ((propertyPreUpdateState != null) && (propertyPreUpdateState.length()>0)) ) {
				if ( propertyPreUpdateState.equals("{null}") || propertyPreUpdateState.equals("null") ) {
					lvb = new LabelValueBean();
					lvb.setLabel(fieldName);
					lvb.setValue("");
				} else {
					lvb = new LabelValueBean();
					lvb.setLabel(fieldName);
					lvb.setValue(propertyPreUpdateState);
				}
		}

		return lvb;
	}

	/**
	 * Process and compare the child value objects using java reflection
	 * @param fieldName the method name
	 * @param propertyPreUpdateState the previous value
	 * @param existingObject the old data object
	 * @param newObject the new data object
	 * @return a label value object
	 */
	private LabelValueBean processLabelValue(String fieldName, String propertyPreUpdateState,
			                       Object existingObject, Object newObject) {

		LabelValueBean lvb = null;
		if ( propertyPreUpdateState.startsWith("{us.mn.state.health.lims") ) {
			if ( fieldName.equals("test") ) {
	    		try {
	    			Method m1 = existingObject.getClass().getMethod("getTest", new Class[0]);
	    			Object o1 = m1.invoke(existingObject, (Object[])new Class[0]);

	    			Method m2 = newObject.getClass().getMethod("getTest", new Class[0]);
	    			Object o2 = m2.invoke(newObject, (Object[])new Class[0]);

	    			String oldID = "";
	    			String newID = "";
	    			if ( o1 != null ) {
	    				Method m11 = o1.getClass().getMethod("getId", new Class[0]);
	    				oldID = (String)m11.invoke(o1, (Object[])new Class[0]);
	    			}

	    			if ( o2 != null ) {
	    				Method m22 = o2.getClass().getMethod("getId", new Class[0]);
	    				newID = (String)m22.invoke(o2, (Object[])new Class[0]);
	    			}

					if ( oldID.compareTo(newID)==0 ) {
						fieldName = null;
						propertyPreUpdateState = null;
					}
					else {
						propertyPreUpdateState = oldID;
					}

	    		} catch (Exception e) {
	          		//buzilla 2154
	    			LogEvent.logError("AuditTrailDAOImpl","processLabelValue()",e.toString());
	    			throw new LIMSRuntimeException("Error in AuditTrail processLabelValue()", e);
	    		}
			} else if ( fieldName.equals("testSection") ) {
	    		try {
	    			Method m1 = existingObject.getClass().getMethod("getTestSection", new Class[0]);
	    			Object o1 = m1.invoke(existingObject, (Object[])new Class[0]);

	    			Method m2 = newObject.getClass().getMethod("getTestSection", new Class[0]);
	    			Object o2 = m2.invoke(newObject, (Object[])new Class[0]);

	    			String oldID = "";
	    			String newID = "";
	    			if ( o1 != null ) {
	    				Method m11 = o1.getClass().getMethod("getId", new Class[0]);
	    				oldID = (String)m11.invoke(o1, (Object[])new Class[0]);
	    			}

	    			if ( o2 != null ) {
	    				Method m22 = o2.getClass().getMethod("getId", new Class[0]);
	    				newID = (String)m22.invoke(o2, (Object[])new Class[0]);
	    			}

					if ( oldID.compareTo(newID)==0 ) {
						fieldName = null;
						propertyPreUpdateState = null;
					}
					else {
						propertyPreUpdateState = oldID;
					}

	    		} catch (Exception e) {
	    			//buzilla 2154
	    			LogEvent.logError("AuditTrailDAOImpl","processLabelValue()",e.toString());
	    			throw new LIMSRuntimeException("Error in AuditTrail processLabelValue()", e);
	    		}
	    	} else if ( fieldName.equals("county") ) {
	    		try {
	    			Method m1 = existingObject.getClass().getMethod("getCounty", new Class[0]);
	    			Object o1 = m1.invoke(existingObject, (Object[])new Class[0]);

	    			Method m2 = newObject.getClass().getMethod("getCounty", new Class[0]);
	    			Object o2 = m2.invoke(newObject, (Object[])new Class[0]);

	    			String oldID = "";
	    			String newID = "";
	    			if ( o1 != null ) {
	    				Method m11 = o1.getClass().getMethod("getId", new Class[0]);
	    				oldID = (String)m11.invoke(o1, (Object[])new Class[0]);
	    			}

	    			if ( o2 != null ) {
	    				Method m22 = o2.getClass().getMethod("getId", new Class[0]);
	    				newID = (String)m22.invoke(o2, (Object[])new Class[0]);
	    			}

					if ( oldID.compareTo(newID)==0 ) {
						fieldName = null;
						propertyPreUpdateState = null;
					}
					else {
						propertyPreUpdateState = oldID;
					}

	    		} catch (Exception e) {
	    			//buzilla 2154
	    			LogEvent.logError("AuditTrailDAOImpl","processLabelValue()",e.toString());
	    			throw new LIMSRuntimeException("Error in AuditTrail processLabelValue()", e);
	    		}
	    	} else if ( fieldName.equals("region") ) {
	    		try {
	    			Method m1 = existingObject.getClass().getMethod("getRegion", new Class[0]);
	    			Object o1 = m1.invoke(existingObject, (Object[])new Class[0]);

	    			Method m2 = newObject.getClass().getMethod("getRegion", new Class[0]);
	    			Object o2 = m2.invoke(newObject, (Object[])new Class[0]);

	    			String oldID = "";
	    			String newID = "";
	    			if ( o1 != null ) {
	    				Method m11 = o1.getClass().getMethod("getId", new Class[0]);
	    				oldID = (String)m11.invoke(o1, (Object[])new Class[0]);
	    			}

	    			if ( o2 != null ) {
	    				Method m22 = o2.getClass().getMethod("getId", new Class[0]);
	    				newID = (String)m22.invoke(o2, (Object[])new Class[0]);
	    			}

					if ( oldID.compareTo(newID)==0 ) {
						fieldName = null;
						propertyPreUpdateState = null;
					}
					else {
						propertyPreUpdateState = oldID;
					}

	    		} catch (Exception e) {
	    			//buzilla 2154
	    			LogEvent.logError("AuditTrailDAOImpl","processLabelValue()",e.toString());
	    			throw new LIMSRuntimeException("Error in AuditTrail processLabelValue()", e);
	    		}
	    	} else if ( fieldName.equals("scriptlet") ) {
	    		try {
	    			Method m1 = existingObject.getClass().getMethod("getScriptlet", new Class[0]);
	    			Object o1 = m1.invoke(existingObject, (Object[])new Class[0]);

	    			Method m2 = newObject.getClass().getMethod("getScriptlet", new Class[0]);
	    			Object o2 = m2.invoke(newObject, (Object[])new Class[0]);

	    			String oldID = "";
	    			String newID = "";
	    			if ( o1 != null ) {
	    				Method m11 = o1.getClass().getMethod("getId", new Class[0]);
	    				oldID = (String)m11.invoke(o1, (Object[])new Class[0]);
	    			}

	    			if ( o2 != null ) {
	    				Method m22 = o2.getClass().getMethod("getId", new Class[0]);
	    				newID = (String)m22.invoke(o2, (Object[])new Class[0]);
	    			}

					if ( oldID.compareTo(newID)==0 ) {
						fieldName = null;
						propertyPreUpdateState = null;
					}
					else {
						propertyPreUpdateState = oldID;
					}

	    		} catch (Exception e) {
	    			//buzilla 2154
	    			LogEvent.logError("AuditTrailDAOImpl","processLabelValue()",e.toString());
	    			throw new LIMSRuntimeException("Error in AuditTrail processLabelValue()", e);
	    		}
	    	} else if ( fieldName.equals("organization") ) {
	    		try {
	    			Method m1 = existingObject.getClass().getMethod("getOrganization", new Class[0]);
	    			Object o1 = m1.invoke(existingObject, (Object[])new Class[0]);

	    			Method m2 = newObject.getClass().getMethod("getOrganization", new Class[0]);
	    			Object o2 = m2.invoke(newObject, (Object[])new Class[0]);

	    			String oldID = "";
	    			String newID = "";
	    			if ( o1 != null ) {
	    				Method m11 = o1.getClass().getMethod("getId", new Class[0]);
	    				oldID = (String)m11.invoke(o1, (Object[])new Class[0]);
	    			}

	    			if ( o2 != null ) {
	    				Method m22 = o2.getClass().getMethod("getId", new Class[0]);
	    				newID = (String)m22.invoke(o2, (Object[])new Class[0]);
	    			}

					if ( oldID.compareTo(newID)==0 ) {
						fieldName = null;
						propertyPreUpdateState = null;
					}
					else {
						propertyPreUpdateState = oldID;
					}

	    		} catch (Exception e) {
	    			//buzilla 2154
	    			LogEvent.logError("AuditTrailDAOImpl","processLabelValue()",e.toString());
	    			throw new LIMSRuntimeException("Error in AuditTrail processLabelValue()", e);
	    		}
	    	} else if ( fieldName.equals("panel") ) {
	    		try {
	    			Method m1 = existingObject.getClass().getMethod("getPanel", new Class[0]);
	    			Object o1 = m1.invoke(existingObject, (Object[])new Class[0]);

	    			Method m2 = newObject.getClass().getMethod("getPanel", new Class[0]);
	    			Object o2 = m2.invoke(newObject, (Object[])new Class[0]);

	    			String oldID = "";
	    			String newID = "";
	    			if ( o1 != null ) {
	    				Method m11 = o1.getClass().getMethod("getId", new Class[0]);
	    				oldID = (String)m11.invoke(o1, (Object[])new Class[0]);
	    			}

	    			if ( o2 != null ) {
	    				Method m22 = o2.getClass().getMethod("getId", new Class[0]);
	    				newID = (String)m22.invoke(o2, (Object[])new Class[0]);
	    			}

					if ( oldID.compareTo(newID)==0 ) {
						fieldName = null;
						propertyPreUpdateState = null;
					}
					else {
						propertyPreUpdateState = oldID;
					}

	    		} catch (Exception e) {
	    			//buzilla 2154
	    			LogEvent.logError("AuditTrailDAOImpl","processLabelValue()",e.toString());
	    			throw new LIMSRuntimeException("Error in AuditTrail processLabelValue()", e);
	    		}
	    	} else if ( fieldName.equals("person") ) {
	    		try {
	    			Method m1 = existingObject.getClass().getMethod("getPerson", new Class[0]);
	    			Object o1 = m1.invoke(existingObject, (Object[])new Class[0]);

	    			Method m2 = newObject.getClass().getMethod("getPerson", new Class[0]);
	    			Object o2 = m2.invoke(newObject, (Object[])new Class[0]);

	    			String oldID = "";
	    			String newID = "";
	    			if ( o1 != null ) {
	    				Method m11 = o1.getClass().getMethod("getId", new Class[0]);
	    				oldID = (String)m11.invoke(o1, (Object[])new Class[0]);
	    			}

	    			if ( o2 != null ) {
	    				Method m22 = o2.getClass().getMethod("getId", new Class[0]);
	    				newID = (String)m22.invoke(o2, (Object[])new Class[0]);
	    			}

					if ( oldID.compareTo(newID)==0 ) {
						fieldName = null;
						propertyPreUpdateState = null;
					}
					else {
						propertyPreUpdateState = oldID;
					}

	    		} catch (Exception e) {
	    			//buzilla 2154
	    			LogEvent.logError("AuditTrailDAOImpl","processLabelValue()",e.toString());
	    			throw new LIMSRuntimeException("Error in AuditTrail processLabelValue()", e);
	    		}
	    	} else if ( fieldName.equals("testResult") ) {
	    		try {
	    			Method m1 = existingObject.getClass().getMethod("getTestResult", new Class[0]);
	    			Object o1 = m1.invoke(existingObject, (Object[])new Class[0]);

	    			Method m2 = newObject.getClass().getMethod("getTestResult", new Class[0]);
	    			Object o2 = m2.invoke(newObject, (Object[])new Class[0]);

	    			String oldID = "";
	    			String newID = "";
	    			if ( o1 != null ) {
	    				Method m11 = o1.getClass().getMethod("getId", new Class[0]);
	    				oldID = (String)m11.invoke(o1, (Object[])new Class[0]);
	    			}

	    			if ( o2 != null ) {
	    				Method m22 = o2.getClass().getMethod("getId", new Class[0]);
	    				newID = (String)m22.invoke(o2, (Object[])new Class[0]);
	    			}

					if ( oldID.compareTo(newID)==0 ) {
						fieldName = null;
						propertyPreUpdateState = null;
					}
					else {
						propertyPreUpdateState = oldID;
					}

	    		} catch (Exception e) {
	    			//buzilla 2154
	    			LogEvent.logError("AuditTrailDAOImpl","processLabelValue()",e.toString());
	    			throw new LIMSRuntimeException("Error in AuditTrail processLabelValue()", e);
	    		}
	    	} else if ( fieldName.equals("analysis") ) {
	    		try {
	    			Method m1 = existingObject.getClass().getMethod("getAnalysis", new Class[0]);
	    			Object o1 = m1.invoke(existingObject, (Object[])new Class[0]);

	    			Method m2 = newObject.getClass().getMethod("getAnalysis", new Class[0]);
	    			Object o2 = m2.invoke(newObject, (Object[])new Class[0]);

	    			String oldID = "";
	    			String newID = "";
	    			if ( o1 != null ) {
	    				Method m11 = o1.getClass().getMethod("getId", new Class[0]);
	    				oldID = (String)m11.invoke(o1, (Object[])new Class[0]);
	    			}

	    			if ( o2 != null ) {
	    				Method m22 = o2.getClass().getMethod("getId", new Class[0]);
	    				newID = (String)m22.invoke(o2, (Object[])new Class[0]);
	    			}

					if ( oldID.compareTo(newID)==0 ) {
						fieldName = null;
						propertyPreUpdateState = null;
					}
					else {
						propertyPreUpdateState = oldID;
					}

	    		} catch (Exception e) {
	    			//buzilla 2154
	    			LogEvent.logError("AuditTrailDAOImpl","processLabelValue()",e.toString());
	    			throw new LIMSRuntimeException("Error in AuditTrail processLabelValue()", e);
	    		}
	    	} else if ( fieldName.equals("analyte") ) {
	    		try {
	    			Method m1 = existingObject.getClass().getMethod("getAnalyte", new Class[0]);
	    			Object o1 = m1.invoke(existingObject, (Object[])new Class[0]);

	    			Method m2 = newObject.getClass().getMethod("getAnalyte", new Class[0]);
	    			Object o2 = m2.invoke(newObject, (Object[])new Class[0]);

	    			String oldID = "";
	    			String newID = "";
	    			if ( o1 != null ) {
	    				Method m11 = o1.getClass().getMethod("getId", new Class[0]);
	    				oldID = (String)m11.invoke(o1, (Object[])new Class[0]);
	    			}

	    			if ( o2 != null ) {
	    				Method m22 = o2.getClass().getMethod("getId", new Class[0]);
	    				newID = (String)m22.invoke(o2, (Object[])new Class[0]);
	    			}

					if ( oldID.compareTo(newID)==0 ) {
						fieldName = null;
						propertyPreUpdateState = null;
					}
					else {
						propertyPreUpdateState = oldID;
					}

	    		} catch (Exception e) {
	    			//buzilla 2154
	    			LogEvent.logError("AuditTrailDAOImpl","processLabelValue()",e.toString());
	    			throw new LIMSRuntimeException("Error in AuditTrail processLabelValue()", e);
	    		}
	    	} else if ( fieldName.equals("sampleItem") ) {
	    		try {
	    			Method m1 = existingObject.getClass().getMethod("getSampleItem", new Class[0]);
	    			Object o1 = m1.invoke(existingObject, (Object[])new Class[0]);

	    			Method m2 = newObject.getClass().getMethod("getSampleItem", new Class[0]);
	    			Object o2 = m2.invoke(newObject, (Object[])new Class[0]);

	    			String oldID = "";
	    			String newID = "";
	    			if ( o1 != null ) {
	    				Method m11 = o1.getClass().getMethod("getId", new Class[0]);
	    				oldID = (String)m11.invoke(o1, (Object[])new Class[0]);
	    			}

	    			if ( o2 != null ) {
	    				Method m22 = o2.getClass().getMethod("getId", new Class[0]);
	    				newID = (String)m22.invoke(o2, (Object[])new Class[0]);
	    			}

					if ( oldID.compareTo(newID)==0 ) {
						fieldName = null;
						propertyPreUpdateState = null;
					}
					else {
						propertyPreUpdateState = oldID;
					}

	    		} catch (Exception e) {
	    			//buzilla 2154
	    			LogEvent.logError("AuditTrailDAOImpl","processLabelValue()",e.toString());
	    			throw new LIMSRuntimeException("Error in AuditTrail processLabelValue()", e);
	    		}
	    	} else if ( fieldName.equals("parentAnalysis") ) {
	    		try {
	    			Method m1 = existingObject.getClass().getMethod("getParentAnalysis", new Class[0]);
	    			Object o1 = m1.invoke(existingObject, (Object[])new Class[0]);

	    			Method m2 = newObject.getClass().getMethod("getParentAnalysis", new Class[0]);
	    			Object o2 = m2.invoke(newObject, (Object[])new Class[0]);

	    			String oldID = "";
	    			String newID = "";
	    			if ( o1 != null ) {
	    				Method m11 = o1.getClass().getMethod("getId", new Class[0]);
	    				oldID = (String)m11.invoke(o1, (Object[])new Class[0]);
	    			}

	    			if ( o2 != null ) {
	    				Method m22 = o2.getClass().getMethod("getId", new Class[0]);
	    				newID = (String)m22.invoke(o2, (Object[])new Class[0]);
	    			}

					if ( oldID.compareTo(newID)==0 ) {
						fieldName = null;
						propertyPreUpdateState = null;
					}
					else {
						propertyPreUpdateState = oldID;
					}

	    		} catch (Exception e) {
	    			//buzilla 2154
	    			LogEvent.logError("AuditTrailDAOImpl","processLabelValue()",e.toString());
	    			throw new LIMSRuntimeException("Error in AuditTrail processLabelValue()", e);
	    		}
	    	} else if ( fieldName.equals("parentResult") ) {
	    		try {
	    			Method m1 = existingObject.getClass().getMethod("getParentResult", new Class[0]);
	    			Object o1 = m1.invoke(existingObject, (Object[])new Class[0]);

	    			Method m2 = newObject.getClass().getMethod("getParentResult", new Class[0]);
	    			Object o2 = m2.invoke(newObject, (Object[])new Class[0]);

	    			String oldID = "";
	    			String newID = "";
	    			if ( o1 != null ) {
	    				Method m11 = o1.getClass().getMethod("getId", new Class[0]);
	    				oldID = (String)m11.invoke(o1, (Object[])new Class[0]);
	    			}

	    			if ( o2 != null ) {
	    				Method m22 = o2.getClass().getMethod("getId", new Class[0]);
	    				newID = (String)m22.invoke(o2, (Object[])new Class[0]);
	    			}

					if ( oldID.compareTo(newID)==0 ) {
						fieldName = null;
						propertyPreUpdateState = null;
					}
					else {
						propertyPreUpdateState = oldID;
					}

	    		} catch (Exception e) {
	    			//buzilla 2154
	    			LogEvent.logError("AuditTrailDAOImpl","processLabelValue()",e.toString());
	    			throw new LIMSRuntimeException("Error in AuditTrail processLabelValue()", e);
	    		}
	    	} else if ( fieldName.equals("sample") ) {
	    		try {
	    			Method m1 = existingObject.getClass().getMethod("getSample", new Class[0]);
	    			Object o1 = m1.invoke(existingObject, (Object[])new Class[0]);

	    			Method m2 = newObject.getClass().getMethod("getSample", new Class[0]);
	    			Object o2 = m2.invoke(newObject, (Object[])new Class[0]);

	    			String oldID = "";
	    			String newID = "";
	    			if ( o1 != null ) {
	    				Method m11 = o1.getClass().getMethod("getId", new Class[0]);
	    				oldID = (String)m11.invoke(o1, (Object[])new Class[0]);
	    			}

	    			if ( o2 != null ) {
	    				Method m22 = o2.getClass().getMethod("getId", new Class[0]);
	    				newID = (String)m22.invoke(o2, (Object[])new Class[0]);
	    			}

					if ( oldID.compareTo(newID)==0 ) {
						fieldName = null;
						propertyPreUpdateState = null;
					}
					else {
						propertyPreUpdateState = oldID;
					}

	    		} catch (Exception e) {
	    			//buzilla 2154
	    			LogEvent.logError("AuditTrailDAOImpl","processLabelValue()",e.toString());
	    			throw new LIMSRuntimeException("Error in AuditTrail processLabelValue()", e);
	    		}
	    	} else if ( fieldName.equals("method") ) {
	    		try {
	    			Method m1 = existingObject.getClass().getMethod("getMethod", new Class[0]);
	    			Object o1 = m1.invoke(existingObject, (Object[])new Class[0]);

	    			Method m2 = newObject.getClass().getMethod("getMethod", new Class[0]);
	    			Object o2 = m2.invoke(newObject, (Object[])new Class[0]);

	    			String oldID = "";
	    			String newID = "";
	    			if ( o1 != null ) {
	    				Method m11 = o1.getClass().getMethod("getId", new Class[0]);
	    				oldID = (String)m11.invoke(o1, (Object[])new Class[0]);
	    			}

	    			if ( o2 != null ) {
	    				Method m22 = o2.getClass().getMethod("getId", new Class[0]);
	    				newID = (String)m22.invoke(o2, (Object[])new Class[0]);
	    			}

					if ( oldID.compareTo(newID)==0 ) {
						fieldName = null;
						propertyPreUpdateState = null;
					}
					else {
						propertyPreUpdateState = oldID;
					}

	    		} catch (Exception e) {
	    			//buzilla 2154
	    			LogEvent.logError("AuditTrailDAOImpl","processLabelValue()",e.toString());
	    			throw new LIMSRuntimeException("Error in AuditTrail processLabelValue()", e);
	    		}
	    	} else if ( fieldName.equals("testTrailer") ) {
	    		try {
	    			Method m1 = existingObject.getClass().getMethod("getTestTrailer", new Class[0]);
	    			Object o1 = m1.invoke(existingObject, (Object[])new Class[0]);

	    			Method m2 = newObject.getClass().getMethod("getTestTrailer", new Class[0]);
	    			Object o2 = m2.invoke(newObject, (Object[])new Class[0]);

	    			String oldID = "";
	    			String newID = "";
	    			if ( o1 != null ) {
	    				Method m11 = o1.getClass().getMethod("getId", new Class[0]);
	    				oldID = (String)m11.invoke(o1, (Object[])new Class[0]);
	    			}

	    			if ( o2 != null ) {
	    				Method m22 = o2.getClass().getMethod("getId", new Class[0]);
	    				newID = (String)m22.invoke(o2, (Object[])new Class[0]);
	    			}

					if ( oldID.compareTo(newID)==0 ) {
						fieldName = null;
						propertyPreUpdateState = null;
					}
					else {
						propertyPreUpdateState = oldID;
					}

	    		} catch (Exception e) {
	    			//buzilla 2154
	    			LogEvent.logError("AuditTrailDAOImpl","processLabelValue()",e.toString());
	    			throw new LIMSRuntimeException("Error in AuditTrail processLabelValue()", e);
	    		}
	    	} else if ( fieldName.equals("unitOfMeasure") ) {
	    		try {
	    			Method m1 = existingObject.getClass().getMethod("getUnitOfMeasure", new Class[0]);
	    			Object o1 = m1.invoke(existingObject, (Object[])new Class[0]);

	    			Method m2 = newObject.getClass().getMethod("getUnitOfMeasure", new Class[0]);
	    			Object o2 = m2.invoke(newObject, (Object[])new Class[0]);

	    			String oldID = "";
	    			String newID = "";
	    			if ( o1 != null ) {
	    				Method m11 = o1.getClass().getMethod("getId", new Class[0]);
	    				oldID = (String)m11.invoke(o1, (Object[])new Class[0]);
	    			}

	    			if ( o2 != null ) {
	    				Method m22 = o2.getClass().getMethod("getId", new Class[0]);
	    				newID = (String)m22.invoke(o2, (Object[])new Class[0]);
	    			}

					if ( oldID.compareTo(newID)==0 ) {
						fieldName = null;
						propertyPreUpdateState = null;
					}
					else {
						propertyPreUpdateState = oldID;
					}

	    		} catch (Exception e) {
	    			//buzilla 2154
	    			LogEvent.logError("AuditTrailDAOImpl","processLabelValue()",e.toString());
	    			throw new LIMSRuntimeException("Error in AuditTrail processLabelValue()", e);
	    		}
	    	} else if ( fieldName.equals("testAnalyte") ) {
	    		try {
	    			Method m1 = existingObject.getClass().getMethod("getTestAnalyte", new Class[0]);
	    			Object o1 = m1.invoke(existingObject, (Object[])new Class[0]);

	    			Method m2 = newObject.getClass().getMethod("getTestAnalyte", new Class[0]);
	    			Object o2 = m2.invoke(newObject, (Object[])new Class[0]);

	    			String oldID = "";
	    			String newID = "";
	    			if ( o1 != null ) {
	    				Method m11 = o1.getClass().getMethod("getId", new Class[0]);
	    				oldID = (String)m11.invoke(o1, (Object[])new Class[0]);
	    			}

	    			if ( o2 != null ) {
	    				Method m22 = o2.getClass().getMethod("getId", new Class[0]);
	    				newID = (String)m22.invoke(o2, (Object[])new Class[0]);
	    			}

					if ( oldID.compareTo(newID)==0 ) {
						fieldName = null;
						propertyPreUpdateState = null;
					}
					else {
						propertyPreUpdateState = oldID;
					}

	    		} catch (Exception e) {
	    			//buzilla 2154
	    			LogEvent.logError("AuditTrailDAOImpl","processLabelValue()",e.toString());
	    			throw new LIMSRuntimeException("Error in AuditTrail processLabelValue()", e);
	    		}
	    	} else if ( fieldName.equals("label") ) {
	    		try {
	    			Method m1 = existingObject.getClass().getMethod("getLabel", new Class[0]);
	    			Object o1 = m1.invoke(existingObject, (Object[])new Class[0]);

	    			Method m2 = newObject.getClass().getMethod("getLabel", new Class[0]);
	    			Object o2 = m2.invoke(newObject, (Object[])new Class[0]);

	    			String oldID = "";
	    			String newID = "";
	    			if ( o1 != null ) {
	    				Method m11 = o1.getClass().getMethod("getId", new Class[0]);
	    				oldID = (String)m11.invoke(o1, (Object[])new Class[0]);
	    			}

	    			if ( o2 != null ) {
	    				Method m22 = o2.getClass().getMethod("getId", new Class[0]);
	    				newID = (String)m22.invoke(o2, (Object[])new Class[0]);
	    			}

					if ( oldID.compareTo(newID)==0 ) {
						fieldName = null;
						propertyPreUpdateState = null;
					}
					else {
						propertyPreUpdateState = oldID;
					}

	    		} catch (Exception e) {
	    			//buzilla 2154
	    			LogEvent.logError("AuditTrailDAOImpl","processLabelValue()",e.toString());
	    			throw new LIMSRuntimeException("Error in AuditTrail processLabelValue()", e);
	    		}
	    	} else if ( fieldName.equals("city") ) {
	    		try {
	    			Method m1 = existingObject.getClass().getMethod("getCity", new Class[0]);
	    			Object o1 = m1.invoke(existingObject, (Object[])new Class[0]);

	    			Method m2 = newObject.getClass().getMethod("getCity", new Class[0]);
	    			Object o2 = m2.invoke(newObject, (Object[])new Class[0]);

	    			String oldID = "";
	    			String newID = "";
	    			if ( o1 != null ) {
	    				Method m11 = o1.getClass().getMethod("getId", new Class[0]);
	    				oldID = (String)m11.invoke(o1, (Object[])new Class[0]);
	    			}

	    			if ( o2 != null ) {
	    				Method m22 = o2.getClass().getMethod("getId", new Class[0]);
	    				newID = (String)m22.invoke(o2, (Object[])new Class[0]);
	    			}

					if ( oldID.compareTo(newID)==0 ) {
						fieldName = null;
						propertyPreUpdateState = null;
					}
					else {
						propertyPreUpdateState = oldID;
					}

	    		} catch (Exception e) {
	    			//buzilla 2154
	    			LogEvent.logError("AuditTrailDAOImpl","processLabelValue()",e.toString());
	    			throw new LIMSRuntimeException("Error in AuditTrail processLabelValue()", e);
	    		}
	    	} else if ( fieldName.equals("addedTest") ) {
	    		//testreflex
	    		try {
	    			us.mn.state.health.lims.testreflex.valueholder.TestReflex data =
    	    		 	(us.mn.state.health.lims.testreflex.valueholder.TestReflex) existingObject;

	    			us.mn.state.health.lims.testreflex.valueholder.TestReflex data2 =
    	    		 	(us.mn.state.health.lims.testreflex.valueholder.TestReflex) newObject;

	    			String oldID = "";
	    			String newID = "";
	    			if ( (data.getAddedTest().getId() != null) ) {
	    				oldID = data.getAddedTest().getId();
	    			}

	    			if ( (data2.getAddedTest().getId() != null) ) {
	    				newID = data2.getAddedTest().getId();
	    			}

					if ( oldID.compareTo(newID)==0 ) {
						fieldName = null;
						propertyPreUpdateState = null;
					}
					else {
						propertyPreUpdateState = oldID;
					}

	    		} catch (Exception e) {
	    			//buzilla 2154
	    			LogEvent.logError("AuditTrailDAOImpl","processLabelValue()",e.toString());
	    			throw new LIMSRuntimeException("Error in AuditTrail processLabelValue()", e);
	    		}
	    	}
		}

		if ( ((fieldName != null) && (fieldName.length()>0)) &&
				//bugzilla 2578 (blank to filled in collection date does not appear in history
			 ((propertyPreUpdateState != null)) ) {
			if ( propertyPreUpdateState.equals("{null}") || propertyPreUpdateState.equals("null") ) {
				lvb = new LabelValueBean();
				lvb.setLabel(fieldName);
				lvb.setValue("");
			} else {
				lvb = new LabelValueBean();
				lvb.setLabel(fieldName);
				lvb.setValue(propertyPreUpdateState);
			}
		}

		return lvb;
	}

	/**
	 * Convert to xml format
	 * @param list the list to be converted
	 * @return xml string
	 */
	private String getXMLFormat(Vector list) {
		StringBuffer xml = new StringBuffer();

		for ( int i=0; i<list.size(); i++ ) {
			LabelValueBean lvb = (LabelValueBean)list.elementAt(i);
            xml.append( "<" ).append( lvb.getLabel() ).append( ">" );
            xml.append( lvb.getValue() );
            xml.append( "</" ).append( lvb.getLabel() ).append( ">\n" );
        }

		return xml.toString();
	}

	/**
	 * Convert to xml format by reading the table bases on it's id (dom4j)
	 * @param table the table name
	 * @param id the primary id
	 * @return xml string
	 */
    public String getXML(String table, String id) throws LIMSRuntimeException {
    	org.hibernate.Session session = HibernateUtil.getSession();
		org.hibernate.Session dom4jSession  = session.getSession(org.hibernate.EntityMode.DOM4J);

		Element elem = (Element)dom4jSession.createQuery("from "+table+" where id="+id).uniqueResult();
		java.io.StringWriter sw = new java.io.StringWriter();
		if ( elem != null ) {
			try {
				Document doc = DocumentHelper.createDocument();
				doc.add(elem);
				OutputFormat format = OutputFormat.createPrettyPrint();
				XMLWriter writer = new XMLWriter( sw, format );
				writer.write( doc );
				writer.flush();
				writer.close();

				return sw.toString();
			} catch( Exception e ) {
    			//buzilla 2154
				LogEvent.logError("AuditTrailDAOImpl","getXML()",e.toString());
	        	throw new LIMSRuntimeException("Error in AuditTrail getXML()", e);
			}
		}
		return null;
    }

	/**
	 * Convert to xml format by reading the table bases on it's id ( oracle dbms )
	 * @param table the table name
	 * @param id the primary id
	 * @return xml string
	 */
    public String getXMLData(String table, String id) throws LIMSRuntimeException {
    	StringBuffer xml;

		LogEvent.logDebug("AuditTrailDAOImpl","getXMLData()","getting History instance");
        try {
        	String out = (String)HibernateUtil.getSession().createSQLQuery("select to_char(dbms_xmlgen.getxml('select * from "+table+" where id="+id+"')) as xml from dual").addScalar("xml", Hibernate.STRING).uniqueResult();
        	xml = new StringBuffer().append(out);

        	return xml.toString();

         } catch (Exception e) {
			 LogEvent.logError("AuditTrailDAOImpl","getXMLData()",e.toString());
        	 throw new LIMSRuntimeException("Error in AuditTrail getXMLData()", e);
         }
    }

	/**
     * bugzilla 2569
	 * Get list of history records by referenceId and referenceTableId
	 * @param history valueholder
	 * @return list of history objects
	 */
    public List getHistoryByRefIdAndRefTableId(History history) throws LIMSRuntimeException {
        return getHistoryByRefIdAndRefTableId( history.getReferenceId(), history.getReferenceTable() );
    }

    @Override
    public List getHistoryByRefIdAndRefTableId( String refId, String tableId ) throws LIMSRuntimeException{
        List list;

        try {

            String sql = "from History h where h.referenceId = :refId and h.referenceTable = :tableId order by h.timestamp desc, h.activity desc";
            org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
            query.setInteger("refId", Integer.parseInt(refId));
            query.setInteger("tableId", Integer.parseInt(tableId));

            list = query.list();
            HibernateUtil.getSession().flush();
            HibernateUtil.getSession().clear();
        } catch (HibernateException e) {
            LogEvent.logError("AuditTrailDAOImpl","getHistoryByRefIdAndRefTableId()",e.toString());
            throw new LIMSRuntimeException("Error in AuditTrail getHistoryByRefIdAndRefTableId()", e);
        }
        return list;
    }

    @SuppressWarnings( "unchecked" )
    @Override
    public List<History> getHistoryByRefTableIdAndDateRange( String referenceTableId, Date start, Date end ) throws LIMSRuntimeException{
        String sql = "from History h where h.referenceTable = :tableId and (h.timestamp between :start and :end)  order by h.referenceId, h.timestamp desc";

        try{

            org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
            query.setInteger("tableId", Integer.parseInt(referenceTableId));
            query.setTimestamp( "start", start );
            query.setTimestamp( "end", end );

            List<History> list = query.list();
            closeSession();
            return list;
        } catch (Exception e) {
            handleException(e, "getHistoryByRefTableIdAndDateRange");
        }

        return null;
    }

    /**
	 * Get list of history records by systemUser, date and referenceTableId
	 * @param history valueholder
	 * @return list of history objects
	 */
    public List getHistoryBySystemUserAndDateAndRefTableId(History history) throws LIMSRuntimeException {
    	List list;

    	try {

    	    String sql;
    		if (!StringUtil.isNullorNill(history.getReferenceTable())) {
    			if (!StringUtil.isNullorNill(history.getSysUserId())) {
    				if (history.getTimestamp() != null) {
    		         sql = "from History h where h.referenceTable = :param1 and h.sysUserId = :param2 and trunc(h.timestamp) = TO_DATE(:param3, 'MM-DD-YYYY') order by h.sysUserId asc, h.timestamp desc";
    				} else {
       		         sql = "from History h where h.referenceTable = :param1 and h.sysUserId = :param2 order by h.sysUserId asc, h.timestamp desc";
    				}
    			} else {
    			   if (history.getTimestamp() != null) {
    				sql = "from History h where h.referenceTable = :param1 and trunc(h.timestamp) = TO_DATE(:param3, 'MM-DD-YYYY')order by h.sysUserId asc, h.timestamp desc";
    			   } else {
    				sql = "from History h where h.referenceTable = :param1 order by h.sysUserId asc, h.timestamp desc";
    			   }
    			}
    		} else {
    			if (!StringUtil.isNullorNill(history.getSysUserId())) {
    			 if (history.getTimestamp() != null) {
    	   	      sql = "from History h where h.sysUserId = :param2 and trunc(h.timestamp) = TO_DATE(:param3, 'MM-DD-YYYY') order by h.sysUserId asc, h.timestamp desc";
    			 } else {
       	   	      sql = "from History h where h.sysUserId = :param2 order by h.sysUserId asc, h.timestamp desc";
    			 }
    			} else {
    			 if (history.getTimestamp() != null) {
    	   	   	  sql = "from History h where trunc(h.timestamp) = TO_DATE(:param3, 'MM-DD-YYYY') order by h.sysUserId asc, h.timestamp desc";
    			 } else {
    	   	   	  sql = "from History h order by h.sysUserId asc, h.timestamp desc";
    			 }
    			}
    		}
    		org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);

    		if (!StringUtil.isNullorNill(history.getReferenceTable())) {
    		  query.setParameter("param1", history.getReferenceTable());
    		}
    		if (!StringUtil.isNullorNill(history.getSysUserId())) {
    		  query.setParameter("param2", history.getSysUserId());
    		}

    		if (history.getTimestamp() != null) {
    		  query.setParameter("param3", DateUtil.convertTimestampToStringDate(history.getTimestamp()));
    		}

    		list = query.list();
    		HibernateUtil.getSession().flush();
    		HibernateUtil.getSession().clear();
    		LogEvent.logDebug("AuditTrailDAOImpl","getHistoryBySystemUserAndDateAndRefTableId()","getting History List");


         } catch (Exception e) {
 			 LogEvent.logError("AuditTrailDAOImpl","getHistoryBySystemUserAndDateAndRefTableId()",e.toString());
        	 throw new LIMSRuntimeException("Error in AuditTrail getHistoryByRefIdAndRefTableId()", e);
         }
         return list;
    }


	/**
	 * Read the blob and convert to xml string
	 * @param id the primary id
	 * @return a string
	 */
    public String retrieveBlobData( String id ) throws LIMSRuntimeException {
    	java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream(1024);
        try {
            History history = (History) HibernateUtil.getSession().get(History.class, id);
        	if ( history != null ) {
        		byte[] bindata = new byte[1024];
        		int bytesread;
        		java.io.BufferedInputStream bis =
        			new java.io.BufferedInputStream( history.getChanges().getBinaryStream() );
        		if ( (bytesread = bis.read(bindata,0,bindata.length)) != -1 ) {
        			baos.write(bindata,0,bytesread);
        		}
        	}
        } catch (Exception e) {
       	 	//buzilla 2154
        	LogEvent.logError("AuditTrailDAOImpl","retrieveBlobData()",e.toString());
			throw new LIMSRuntimeException("Error in AuditTrail retrieveBlobData()", e);
        }
        return baos.toString();
    }

	/**
	 * Save the object into history table
	 * @param history the history object being saved
	 */
    private void insertData(History history) throws LIMSRuntimeException {
		try {
			String id = (String)HibernateUtil.getSession().save(history);
			history.setId(id);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//buzilla 2154
        	LogEvent.logError("AuditTrailDAOImpl","insertData()",e.toString());
			throw new LIMSRuntimeException("Error in AuditTrail insertData()", e);
		}
    }

}
