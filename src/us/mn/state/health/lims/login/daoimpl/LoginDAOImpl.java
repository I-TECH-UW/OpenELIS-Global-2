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
package us.mn.state.health.lims.login.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.beanutils.PropertyUtils;

import phl.util.Crypto;
import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSDuplicateRecordException;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.login.dao.LoginDAO;
import us.mn.state.health.lims.login.valueholder.Login;
import us.mn.state.health.lims.security.PasswordUtil;

/**
 * @author Hung Nguyen (Hung.Nguyen@health.state.mn.us)
 */
public class LoginDAOImpl extends BaseDAOImpl implements LoginDAO {

	public void deleteData(List logins) throws LIMSRuntimeException {
		// add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			for (int i = 0; i < logins.size(); i++) {
				Login data = (Login) logins.get(i);

				Login oldData = readLoginUser(data.getId());
				Login newData = new Login();

				String sysUserId = data.getSysUserId();
				String event = IActionConstants.AUDIT_TRAIL_DELETE;
				String tableName = "LOGIN_USER";
				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
			}
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("LoginDAOImpl", "AuditTrail deleteData()", e.toString());
			throw new LIMSRuntimeException("Error in Login AuditTrail deleteData()", e);
		}

		try {
			for (int i = 0; i < logins.size(); i++) {
				Login data = (Login) logins.get(i);
				// bugzilla 2206
				data = readLoginUser(data.getId());
				HibernateUtil.getSession().delete(data);
				HibernateUtil.getSession().flush();
				HibernateUtil.getSession().clear();
			}
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("LoginDAOImpl", "deleteData()", e.toString());
			throw new LIMSRuntimeException("Error in Login deleteData()", e);
		}
	}

	public boolean insertData(Login login) throws LIMSRuntimeException {

		try {
			if (duplicateLoginNameExists(login)) {
				throw new LIMSDuplicateRecordException("Duplicate record exists for " + login.getLoginName());
			}

			//Crypto crypto = new Crypto();
			PasswordUtil passUtil = new PasswordUtil();
			String id = (String) HibernateUtil.getSession().save(login);
			login.setId(id);
			//login.setPassword(crypto.getEncrypt(login.getPassword()));
			login.setPassword(passUtil.hashPassword(login.getPassword()));

			// add to audit trail
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = login.getSysUserId();
			String tableName = "LOGIN_USER";
			auditDAO.saveNewHistory(login, sysUserId, tableName);

			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("LoginDAOImpl", "insertData()", e.toString());
			throw new LIMSRuntimeException("Error in Login insertData()", e);
		}

		return true;
	}

	//Update login data, keep old password unless flag set
	public void updateData(Login login, boolean passwordUpdated) throws LIMSRuntimeException {
		try {
			if (duplicateLoginNameExists(login)) {
				throw new LIMSDuplicateRecordException("Duplicate record exists for " + login.getLoginName());
			}
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("LoginDAOImpl", "updateData()", e.toString());
			throw new LIMSRuntimeException("Error in Login updateData()", e);
		}

		Login oldData = readLoginUser(login.getId());
		//Crypto crypto = new Crypto();
		
		Login newData = login;
		//newData.setPassword(crypto.getEncrypt(login.getPassword()));
		if (passwordUpdated) {
			PasswordUtil passUtil = new PasswordUtil();
			newData.setPassword(passUtil.hashPassword(login.getPassword()));
		}

		// add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = login.getSysUserId();
			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
			String tableName = "LOGIN_USER";
			auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("LoginDAOImpl", "AuditTrail updateData()", e.toString());
			throw new LIMSRuntimeException("Error in Login AuditTrail updateData()", e);
		}

		try {
			HibernateUtil.getSession().merge(login);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(login);
			HibernateUtil.getSession().refresh(login);
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("LoginDAOImpl", "updateData()", e.toString());
			throw new LIMSRuntimeException("Error in Login updateData()", e);
		}
	}

	public void getData(Login login) throws LIMSRuntimeException {
		try {
			Login l = (Login) HibernateUtil.getSession().get(Login.class, login.getId());
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			if (l != null) {
				//Crypto crypto = new Crypto();
				//l.setPassword(crypto.getDecrypt(l.getPassword()));
				PropertyUtils.copyProperties(login, l);
			} else {
				login.setId(null);
			}
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("LoginDAOImpl", "getData()", e.toString());
			throw new LIMSRuntimeException("Error in Login getData()", e);
		}
	}

	public List getAllLoginUsers() throws LIMSRuntimeException {
		List list = new Vector();
		try {
			String sql = "from Login";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("LoginDAOImpl", "getAllLogins()", e.toString());
			throw new LIMSRuntimeException("Error in Login getAllLogins()", e);
		}

		return list;
	}

	public List getPageOfLoginUsers(int startingRecNo) throws LIMSRuntimeException {
		List list = new Vector();
		try {
			// calculate maxRow to be one more than the page size
			int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

			String sql = "from Login l order by l.loginName";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setFirstResult(startingRecNo - 1);
			query.setMaxResults(endingRecNo - 1);

			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("LoginDAOImpl", "getPageOfLogins()", e.toString());
			throw new LIMSRuntimeException("Error in Login getPageOfLogins()", e);
		}

		return list;
	}

	public Login readLoginUser(String idString) {
		Login l = null;
		try {
			l = (Login) HibernateUtil.getSession().get(Login.class, idString);
			//Crypto crypto = new Crypto();
			//l.setPassword(crypto.getDecrypt(l.getPassword()));
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("LoginDAOImpl", "readLoginUser()", e.toString());
			throw new LIMSRuntimeException("Error in Login readLoginUser(idString)", e);
		}

		return l;
	}

	public List getNextLoginUserRecord(String id) throws LIMSRuntimeException {

		return getNextRecord(id, "Login", Login.class);
	}

	public List getPreviousLoginUserRecord(String id) throws LIMSRuntimeException {

		return getPreviousRecord(id, "Login", Login.class);
	}

	public Integer getTotalLoginUserCount() throws LIMSRuntimeException {
		return getTotalCount("Login", Login.class);
	}

	@Override
	public List getNextRecord(String id, String table, Class clazz) throws LIMSRuntimeException {
		int currentId = (Integer.valueOf(id)).intValue();
		String tablePrefix = getTablePrefix(table);

		List list = new Vector();
		int rrn = 0;
		try {
			String sql = "select l.id from Login l order by l.loginName";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			rrn = list.indexOf(String.valueOf(currentId));

			list = HibernateUtil.getSession().getNamedQuery(tablePrefix + "getNext").setFirstResult(rrn + 1)
					.setMaxResults(2).list();

		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("LoginDAOImpl", "getNextRecord()", e.toString());
			throw new LIMSRuntimeException("Error in getNextRecord() for " + table, e);
		}

		return list;
	}

	@Override
	public List getPreviousRecord(String id, String table, Class clazz) throws LIMSRuntimeException {
		int currentId = (Integer.valueOf(id)).intValue();
		String tablePrefix = getTablePrefix(table);

		List list = new Vector();
		int rrn = 0;
		try {
			String sql = "select l.id from Login l order by l.loginName";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			rrn = list.indexOf(String.valueOf(currentId));

			list = HibernateUtil.getSession().getNamedQuery(tablePrefix + "getPrevious").setFirstResult(rrn + 1)
					.setMaxResults(2).list();

		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("LoginDAOImpl", "getPreviousRecord()", e.toString());
			throw new LIMSRuntimeException("Error in getPreviousRecord() for " + table, e);
		}

		return list;
	}

	private boolean duplicateLoginNameExists(Login login) throws LIMSRuntimeException {
		try {

			List list = new ArrayList();

			String sql = "from Login l where trim(lower(l.loginName)) = :loginName and l.id != :loginId";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setParameter("loginName", login.getLoginName().toLowerCase().trim());

			String loginId = "0";
			if (!StringUtil.isNullorNill(login.getId())) {
				loginId = login.getId();
			}

			query.setInteger("loginId", Integer.parseInt(loginId));

			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

			return list.size() > 0;

		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("LoginDAOImpl", "duplicateLoginNameExists()", e.toString());
			throw new LIMSRuntimeException("Error in duplicateLoginNameExists()", e);
		}
	}

	/**
	 * Validate the user name, password
	 * 
	 * @param login
	 *            the login object
	 * @return login object value
	 */
	public Login getValidateLogin(Login login) throws LIMSRuntimeException {

		Crypto crypto = new Crypto();
		PasswordUtil passUtil = new PasswordUtil();
		Login loginData = null;

		try {
			List list = new ArrayList();
			String sql = "from Login l where l.loginName = :param1";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setParameter("param1", login.getLoginName());
			//query.setParameter("param2", crypto.getEncrypt(login.getPassword()));

			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

			if (list.size() > 0) {
				loginData = (Login) list.get(0);
				int passwordExpiredDayNo = getPasswordExpiredDayNo(login);
				int systemUserId = getSystemUserId(login);
				loginData.setPasswordExpiredDayNo(passwordExpiredDayNo);
				loginData.setSystemUserId(systemUserId);
				try {
					//null login if incorrect password is entered
					if ( !passUtil.checkPassword(login.getPassword(), loginData.getPassword()) ) {
						loginData = null;
					}
				//error when no salt present, must be old password	
				} catch (IllegalArgumentException e) {
					//move passwords from encryption to salted hashing
					if (loginData.getPassword().equals(crypto.getEncrypt(login.getPassword()))) {
						updateCryptoPasswordToHash(loginData);
					} else {
						loginData = null;
					}
				}
			}

		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("LoginDAOImpl", "getValidateLogin()", e.toString());
			throw new LIMSRuntimeException("Error in Login getValidateLogin()", e);
		}

		return loginData;
	}

	/**
	 * Get the user login information base on login name
	 * 
	 * @param loginName
	 *            the user login name
	 * @return login object
	 */
	public Login getUserProfile(String loginName) throws LIMSRuntimeException {

		Login login = null;
		try {
			List list = new ArrayList();
			String sql = "from Login l where l.loginName = :param";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setParameter("param", loginName);

			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

			if (list.size() > 0) {
				login = (Login) list.get(0);
				int passwordExpiredDayNo = getPasswordExpiredDayNo(login);
				int systemUserId = getSystemUserId(login);
				login.setPasswordExpiredDayNo(passwordExpiredDayNo);
				login.setSystemUserId(systemUserId);
			}

		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("LoginDAOImpl", "getUserProfile()", e.toString());
			throw new LIMSRuntimeException("Error in Login getUserProfile()", e);
		}
		return login;
	}

	/**
	 * Get the password expiration day
	 * 
	 * @param login
	 *            the login object
	 * @return type integer the password expiration day
	 */
	public int getPasswordExpiredDayNo(Login login) throws LIMSRuntimeException {
		int retVal = 0;
		try {
			Object obj = HibernateUtil.getSession().getNamedQuery("login.getAnalysisPasswordExpiredDayCount")
					.setString("loginName", login.getLoginName()).uniqueResult();

			if (obj != null) {
				retVal = Integer.parseInt(obj.toString());
			}

		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("LoginDAOImpl", "getPasswordExpiredDayNo()", e.toString());
			throw new LIMSRuntimeException("Error in getPasswordExpiredDayNo()", e);
		} finally {
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		}

		return retVal;

	}

	/**
	 * Get the system user id
	 * 
	 * @param login
	 *            the login object
	 * @return type integer the system user id
	 */
	public int getSystemUserId(Login login) throws LIMSRuntimeException {
		int retVal = 0;
		try {
			Object obj = HibernateUtil.getSession().getNamedQuery("login.getSystemUserId").setString("loginName",
					login.getLoginName()).uniqueResult();

			if (obj != null) {
				retVal = Integer.parseInt(obj.toString());
			}

		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("LoginDAOImpl", "getSystemUserId()", e.toString());
			throw new LIMSRuntimeException("Error in getSystemUserId()", e);
		} finally {
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		}

		return retVal;
	}
	
	/**
	 * Update the user passsword
	 * 
	 * @param login
	 *            the login object
	 * @return true if success, false otherwise
	 */
	public boolean updateCryptoPasswordToHash(Login login) throws LIMSRuntimeException {

		Crypto crypto = new Crypto();
		PasswordUtil passUtil = new PasswordUtil();

		try {
			login.setPassword(crypto.getDecrypt(login.getPassword()));
			login.setPassword(passUtil.hashPassword(login.getPassword()));

			HibernateUtil.getSession().merge(login);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(login);
			HibernateUtil.getSession().refresh(login);

		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("LoginDAOImpl", "updateCryptoPasswordToHash()", e.toString());
			throw new LIMSRuntimeException("Error in Login updateCryptoPasswordToHash()", e);
		}

		return true;
	}

	/**
	 * Update the user passsword
	 * 
	 * @param login
	 *            the login object
	 * @return true if success, false otherwise
	 */
	public boolean updatePassword(Login login) throws LIMSRuntimeException {

		//Crypto crypto = new Crypto();
		PasswordUtil passUtil = new PasswordUtil();

		try {
			String password = login.getPassword();
			//login.setPassword(crypto.getEncrypt(login.getPassword()));
			login.setPassword(passUtil.hashPassword(login.getPassword()));
			
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			auditDAO.saveHistory(login, readLoginUser(login.getId()), login.getSysUserId(), IActionConstants.AUDIT_TRAIL_UPDATE, "LOGIN_USER");
			
			HibernateUtil.getSession().merge(login);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(login);
			HibernateUtil.getSession().refresh(login);

		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("LoginDAOImpl", "updatePassword()", e.toString());
			throw new LIMSRuntimeException("Error in Login updatePassword()", e);
		}

		return true;
	}

	/**
	 * bugzilla 2286 Lock the user account after number of failed attempt
	 * 
	 * @param login
	 *            the login object
	 * @return true if success, false otherwise
	 */
	public boolean lockAccount(Login login) throws LIMSRuntimeException {
		boolean isSuccess = false;
		try {
			HibernateUtil.getSession().merge(login);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(login);
			HibernateUtil.getSession().refresh(login);
			isSuccess = true;
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("LoginDAOImpl", "lockAccount()", e.toString());
			throw new LIMSRuntimeException("Error in Login lockAccount()", e);
		}
		return isSuccess;
	}

	/**
	 * bugzilla 2286 unlock the user account after number of minutes
	 * 
	 * @param login
	 *            the login object
	 * @return true if success, false otherwise
	 */
	public boolean unlockAccount(Login login) throws LIMSRuntimeException {
		boolean isSuccess = false;
		try {
			HibernateUtil.getSession().merge(login);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(login);
			HibernateUtil.getSession().refresh(login);
			isSuccess = true;
		} catch (Exception e) {
			// bugzilla 2154
			LogEvent.logError("LoginDAOImpl", "unlockAccount()", e.toString());
			throw new LIMSRuntimeException("Error in Login unlockAccount()", e);
		}
		return isSuccess;
	}
}