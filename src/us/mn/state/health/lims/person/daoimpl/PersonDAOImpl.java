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
package us.mn.state.health.lims.person.daoimpl;

import java.util.List;
import java.util.Vector;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.person.dao.PersonDAO;
import us.mn.state.health.lims.person.valueholder.Person;

/**
 * @author diane benz
 */
public class PersonDAOImpl extends BaseDAOImpl implements PersonDAO {

	public void deleteData(List persons) throws LIMSRuntimeException {
		//add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			for (int i = 0; i < persons.size(); i++) {
				Person data = (Person)persons.get(i);

				Person oldData = (Person)readPerson(data.getId());
				Person newData = new Person();

				String sysUserId = data.getSysUserId();
				String event = IActionConstants.AUDIT_TRAIL_DELETE;
				String tableName = "PERSON";
				auditDAO.saveHistory(newData,oldData,sysUserId,event,tableName);
			}
		}  catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("PersonDAOImpl","AuditTrail deleteData()",e.toString());
			throw new LIMSRuntimeException("Error in Person AuditTrail deleteData()", e);
		}

		try {
			for (int i = 0; i < persons.size(); i++) {
				Person data = (Person) persons.get(i);
				//bugzilla 2206
     			data = (Person)readPerson(data.getId());
				HibernateUtil.getSession().delete(data);
				HibernateUtil.getSession().flush();
				HibernateUtil.getSession().clear();
			}
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("PersonDAOImpl","deleteData()",e.toString());
			throw new LIMSRuntimeException("Error in Person deleteData()", e);
		}
	}

	public boolean insertData(Person person) throws LIMSRuntimeException {

		try {
			String id = (String)HibernateUtil.getSession().save(person);
			person.setId(id);

			//bugzilla 1824 inserts will be logged in history table
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = person.getSysUserId();
			String tableName = "PERSON";
			auditDAO.saveNewHistory(person,sysUserId,tableName);

			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("PersonDAOImpl","insertData()",e.toString());
			throw new LIMSRuntimeException("Error in Person insertData()", e);
		}

		return true;
	}


	public void updateData(Person person) throws LIMSRuntimeException {

		Person oldData = (Person)readPerson(person.getId());
		Person newData = person;

		//add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = person.getSysUserId();
			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
			String tableName = "PERSON";
			auditDAO.saveHistory(newData,oldData,sysUserId,event,tableName);
		}  catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("PersonDAOImpl","AuditTrail updateData()",e.toString());
			throw new LIMSRuntimeException("Error in Person AuditTrail updateData()", e);
		}

		try {
			HibernateUtil.getSession().merge(person);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(person);
			HibernateUtil.getSession().refresh(person);
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("PersonDAOImpl","updateData()",e.toString());
			throw new LIMSRuntimeException("Error in Person updateData()", e);
		}
	}

	public void getData(Person person) throws LIMSRuntimeException {
		try {
			Person pers = (Person)HibernateUtil.getSession().get(Person.class, person.getId());
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			if (pers != null) {
			  PropertyUtils.copyProperties(person, pers);
			} else {
				person.setId(null);
			}

		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("PersonDAOImpl","getData()",e.toString());
			throw new LIMSRuntimeException("Error in Person getData()", e);
		}
	}

	public List getAllPersons() throws LIMSRuntimeException {
		List list = new Vector();
		try {
			String sql = "from Person";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("PersonDAOImpl","getAllPersons()",e.toString());
			throw new LIMSRuntimeException("Error in Person getAllPersons()", e);
		}

		return list;
	}

	public List getPageOfPersons(int startingRecNo) throws LIMSRuntimeException {
		List list = new Vector();
		try {
			// calculate maxRow to be one more than the page size
			int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

			String sql = "from Person t order by t.id";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setFirstResult(startingRecNo-1);
			query.setMaxResults(endingRecNo-1);

			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("PersonDAOImpl","getPageOfPersons()",e.toString());
			throw new LIMSRuntimeException("Error in Person getPageOfPersons()", e);
		}

		return list;
	}

	public Person readPerson(String idString) {
		Person person = null;
		try {
			person = (Person)HibernateUtil.getSession().get(Person.class, idString);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("PersonDAOImpl","readPerson()",e.toString());
			throw new LIMSRuntimeException("Error in Person readPerson()", e);
		}

		return person;
	}

	public List getNextPersonRecord(String id) throws LIMSRuntimeException {

		return getNextRecord(id, "Person", Person.class);

	}

	public List getPreviousPersonRecord(String id) throws LIMSRuntimeException {

		return getPreviousRecord(id, "Person", Person.class);
	}

	@SuppressWarnings("unchecked")
	public Person getPersonByLastName(String lastName) throws LIMSRuntimeException {
		List<Person> list = null;
		try {
			String sql = "from Person p where p.lastName = :lastName";
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setString("lastName", lastName);

			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			LogEvent.logError("PersonDAOImpl","getPersonByLastName()",e.toString());
			throw new LIMSRuntimeException("Error in Person getPersonByLastName()", e);
		}

		if( list.size() > 0){
			return list.get(0);
		}

		return null;
	}

	@Override
	public Person getPersonById(String personId) throws LIMSRuntimeException {
		String sql = "From Person p where id = :personId";
		try{
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("personId", Integer.parseInt(personId));
			Person person = (Person)query.uniqueResult();
			closeSession();
			return person;
		}catch(HibernateException e){
			handleException(e, "getPersonById");
		}
		return null;
	}

}