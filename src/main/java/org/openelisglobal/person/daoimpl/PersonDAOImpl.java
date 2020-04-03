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
package org.openelisglobal.person.daoimpl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Vector;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.util.SystemConfiguration;
import org.openelisglobal.person.dao.PersonDAO;
import org.openelisglobal.person.valueholder.Person;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author diane benz
 */
@Component
@Transactional
public class PersonDAOImpl extends BaseDAOImpl<Person, String> implements PersonDAO {

    public PersonDAOImpl() {
        super(Person.class);
    }

//	@Override
//	public void deleteData(List persons) throws LIMSRuntimeException {
//		// add to audit trail
//		try {
//			for (int i = 0; i < persons.size(); i++) {
//				Person data = (Person) persons.get(i);
//
//				Person oldData = readPerson(data.getId());
//				Person newData = new Person();
//
//				String sysUserId = data.getSysUserId();
//				String event = IActionConstants.AUDIT_TRAIL_DELETE;
//				String tableName = "PERSON";
//				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("PersonDAOImpl", "AuditTrail deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in Person AuditTrail deleteData()", e);
//		}
//
//		try {
//			for (int i = 0; i < persons.size(); i++) {
//				Person data = (Person) persons.get(i);
//				// bugzilla 2206
//				data = readPerson(data.getId());
//				entityManager.unwrap(Session.class).delete(data);
//				// entityManager.unwrap(Session.class).flush(); // CSL remove old
//				// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("PersonDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in Person deleteData()", e);
//		}
//	}

//	@Override
//	public boolean insertData(Person person) throws LIMSRuntimeException {
//
//		try {
//			String id = (String) entityManager.unwrap(Session.class).save(person);
//			person.setId(id);
//
//			// bugzilla 1824 inserts will be logged in history table
//			String sysUserId = person.getSysUserId();
//			String tableName = "PERSON";
//			auditDAO.saveNewHistory(person, sysUserId, tableName);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("PersonDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in Person insertData()", e);
//		}
//
//		return true;
//	}
//
//	@Override
//	public void updateData(Person person) throws LIMSRuntimeException {
//
//		Person oldData = readPerson(person.getId());
//		Person newData = person;
//
//		// add to audit trail
//		try {
//
//			String sysUserId = person.getSysUserId();
//			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
//			String tableName = "PERSON";
//			auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("PersonDAOImpl", "AuditTrail updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in Person AuditTrail updateData()", e);
//		}
//
//		try {
//			entityManager.unwrap(Session.class).merge(person);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(person);
//			// entityManager.unwrap(Session.class).refresh // CSL remove old(person);
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("PersonDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in Person updateData()", e);
//		}
//	}

    @Override
    @Transactional(readOnly = true)
    public void getData(Person person) throws LIMSRuntimeException {
        try {
            Person pers = entityManager.unwrap(Session.class).get(Person.class, person.getId());
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
            if (pers != null) {
                PropertyUtils.copyProperties(person, pers);
            } else {
                person.setId(null);
            }

        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Person getData()", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Person> getAllPersons() throws LIMSRuntimeException {
        List<Person> list = new Vector<>();
        try {
            String sql = "from Person";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Person getAllPersons()", e);
        }

        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Person> getPageOfPersons(int startingRecNo) throws LIMSRuntimeException {
        List<Person> list = new Vector<>();
        try {
            // calculate maxRow to be one more than the page size
            int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

            String sql = "from Person t order by t.id";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setFirstResult(startingRecNo - 1);
            query.setMaxResults(endingRecNo - 1);

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Person getPageOfPersons()", e);
        }

        return list;
    }

    public Person readPerson(String idString) {
        Person person = null;
        try {
            person = entityManager.unwrap(Session.class).get(Person.class, idString);
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Person readPerson()", e);
        }

        return person;
    }

    @Override

    @Transactional(readOnly = true)
    public Person getPersonByLastName(String lastName) throws LIMSRuntimeException {
        List<Person> list = null;
        try {
            String sql = "from Person p where p.lastName = :lastName";
            Query<Person> query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setString("lastName", lastName);

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Person getPersonByLastName()", e);
        }

        if (list.size() > 0) {
            return list.get(0);
        }

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Person getPersonById(String personId) throws LIMSRuntimeException {
        String sql = "From Person p where id = :personId";
        try {
            Query<Person> query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("personId", Integer.parseInt(personId));
            Person person = query.uniqueResult();
            // closeSession(); // CSL remove old
            return person;
        } catch (HibernateException e) {
            handleException(e, "getPersonById");
        }
        return null;
    }

}