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
package org.openelisglobal.login.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.login.dao.LoginUserDAO;
import org.openelisglobal.login.valueholder.LoginUser;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Hung Nguyen (Hung.Nguyen@health.state.mn.us)
 */
@Component
@Transactional
public class LoginUserDAOImpl extends BaseDAOImpl<LoginUser, Integer> implements LoginUserDAO {

    public LoginUserDAOImpl() {
        super(LoginUser.class);
    }

//	@Override
//	public void deleteData(List logins) throws LIMSRuntimeException {
//		// add to audit trail
//		try {
//
//			for (int i = 0; i < logins.size(); i++) {
//				Login data = (Login) logins.get(i);
//
//				Login oldData = readLoginUser(data.getId());
//				Login newData = new Login();
//
//				String sysUserId = data.getSysUserId();
//				String event = IActionConstants.AUDIT_TRAIL_DELETE;
//				String tableName = "LOGIN_USER";
//				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("LoginDAOImpl", "AuditTrail deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in Login AuditTrail deleteData()", e);
//		}
//
//		try {
//			for (int i = 0; i < logins.size(); i++) {
//				Login data = (Login) logins.get(i);
//				// bugzilla 2206
//				data = readLoginUser(data.getId());
//				entityManager.unwrap(Session.class).delete(data);
//				// entityManager.unwrap(Session.class).flush(); // CSL remove old
//				// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("LoginDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in Login deleteData()", e);
//		}
//	}

//	@Override
//	public boolean insertData(Login login) throws LIMSRuntimeException {
//
//		try {
//			if (duplicateLoginNameExists(login)) {
//				throw new LIMSDuplicateRecordException("Duplicate record exists for " + login.getLoginName());
//			}
//
//			// Crypto crypto = new Crypto();
//			PasswordUtil passUtil = new PasswordUtil();
//			String id = (String) entityManager.unwrap(Session.class).save(login);
//			login.setId(id);
//			// login.setPassword(crypto.getEncrypt(login.getPassword()));
//			login.setPassword(passUtil.hashPassword(login.getPassword()));
//
//			// add to audit trail
//
//			String sysUserId = login.getSysUserId();
//			String tableName = "LOGIN_USER";
//			auditDAO.saveNewHistory(login, sysUserId, tableName);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("LoginDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in Login insertData()", e);
//		}
//
//		return true;
//	}

    // Update login data, keep old password unless flag set
//	@Override
//	public void updateData(Login login, boolean passwordUpdated) throws LIMSRuntimeException {
//		try {
//			if (duplicateLoginNameExists(login)) {
//				throw new LIMSDuplicateRecordException("Duplicate record exists for " + login.getLoginName());
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("LoginDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in Login updateData()", e);
//		}
//
//		Login oldData = readLoginUser(login.getId());
//		// Crypto crypto = new Crypto();
//
//		Login newData = login;
//		// newData.setPassword(crypto.getEncrypt(login.getPassword()));
//		if (passwordUpdated) {
//			PasswordUtil passUtil = new PasswordUtil();
//			newData.setPassword(passUtil.hashPassword(login.getPassword()));
//		}
//
//		// add to audit trail
//		try {
//
//			String sysUserId = login.getSysUserId();
//			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
//			String tableName = "LOGIN_USER";
//			auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("LoginDAOImpl", "AuditTrail updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in Login AuditTrail updateData()", e);
//		}
//
//		try {
//			entityManager.unwrap(Session.class).merge(login);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(login);
//			// entityManager.unwrap(Session.class).refresh // CSL remove old(login);
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("LoginDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in Login updateData()", e);
//		}
//	}

//	@Override
//	public void getData(Login login) throws LIMSRuntimeException {
//		try {
//			Login l = entityManager.unwrap(Session.class).get(Login.class, login.getId());
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			if (l != null) {
//				// Crypto crypto = new Crypto();
//				// l.setPassword(crypto.getDecrypt(l.getPassword()));
//				PropertyUtils.copyProperties(login, l);
//			} else {
//				login.setId(null);
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("LoginDAOImpl", "getData()", e.toString());
//			throw new LIMSRuntimeException("Error in Login getData()", e);
//		}
//	}

//	@Override
//	public List getAllLoginUsers() throws LIMSRuntimeException {
//		List list ;
//		try {
//			String sql = "from LoginUser";
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			list = query.list();
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("LoginDAOImpl", "getAllLogins()", e.toString());
//			throw new LIMSRuntimeException("Error in Login getAllLogins()", e);
//		}
//
//		return list;
//	}

//	@Override
//	public List getPageOfLoginUsers(int startingRecNo) throws LIMSRuntimeException {
//		List list ;
//		try {
//			// calculate maxRow to be one more than the page size
//			int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);
//
//			String sql = "from LoginUser l order by l.loginName";
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			query.setFirstResult(startingRecNo - 1);
//			query.setMaxResults(endingRecNo - 1);
//
//			list = query.list();
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("LoginDAOImpl", "getPageOfLogins()", e.toString());
//			throw new LIMSRuntimeException("Error in Login getPageOfLogins()", e);
//		}
//
//		return list;
//	}

//	public Login readLoginUser(String idString) {
//		Login l = null;
//		try {
//			l = entityManager.unwrap(Session.class).get(Login.class, idString);
//			// Crypto crypto = new Crypto();
//			// l.setPassword(crypto.getDecrypt(l.getPassword()));
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("LoginDAOImpl", "readLoginUser()", e.toString());
//			throw new LIMSRuntimeException("Error in Login readLoginUser(idString)", e);
//		}
//
//		return l;
//	}

//	@Override
//	public List getNextLoginUserRecord(String id) throws LIMSRuntimeException {
//
//		return getNextRecord(id, "Login", Login.class);
//	}

//	@Override
//	public List getPreviousLoginUserRecord(String id) throws LIMSRuntimeException {
//
//		return getPreviousRecord(id, "Login", Login.class);
//	}

//	@Override
//	public Integer getTotalLoginUserCount() throws LIMSRuntimeException {
//		return getCount();
//	}

//	@Override
//	public List getNextRecord(String id, String table, Class clazz) throws LIMSRuntimeException {
//		int currentId = (Integer.valueOf(id)).intValue();
//		String tablePrefix = getTablePrefix(table);
//
//		List list ;
//		int rrn = 0;
//		try {
//			String sql = "select l.id from LoginUser l order by l.loginName";
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			list = query.list();
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			rrn = list.indexOf(String.valueOf(currentId));
//
//			list = entityManager.unwrap(Session.class).getNamedQuery(tablePrefix + "getNext").setFirstResult(rrn + 1)
//					.setMaxResults(2).list();
//
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("LoginDAOImpl", "getNextRecord()", e.toString());
//			throw new LIMSRuntimeException("Error in getNextRecord() for " + table, e);
//		}
//
//		return list;
//	}

//	@Override
//	public List getPreviousRecord(String id, String table, Class clazz) throws LIMSRuntimeException {
//		int currentId = (Integer.valueOf(id)).intValue();
//		String tablePrefix = getTablePrefix(table);
//
//		List list ;
//		int rrn = 0;
//		try {
//			String sql = "select l.id from LoginUser l order by l.loginName";
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			list = query.list();
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			rrn = list.indexOf(String.valueOf(currentId));
//
//			list = entityManager.unwrap(Session.class).getNamedQuery(tablePrefix + "getPrevious").setFirstResult(rrn + 1)
//					.setMaxResults(2).list();
//
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("LoginDAOImpl", "getPreviousRecord()", e.toString());
//			throw new LIMSRuntimeException("Error in getPreviousRecord() for " + table, e);
//		}
//
//		return list;
//	}

    @Override
    public boolean duplicateLoginNameExists(LoginUser login) throws LIMSRuntimeException {
        try {

            List<LoginUser> list = new ArrayList<>();

            String sql = "from LoginUser l where trim(lower(l.loginName)) = :loginName and l.id != :loginId";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("loginName", login.getLoginName().toLowerCase().trim());

            Integer loginId = 0;
            if (null != login.getId()) {
                loginId = login.getId();
            }

            query.setInteger("loginId", loginId);

            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

            return list.size() > 0;

        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in duplicateLoginNameExists()", e);
        }
    }

    /**
     * Validate the user name, password
     *
     * @param login the login object
     * @return login object value
     */
//	@Override
//	public Login getValidateLogin(Login login) throws LIMSRuntimeException {
//
//		// Crypto crypto = new Crypto();
//		PasswordUtil passUtil = new PasswordUtil();
//		Login loginData = null;
//
//		try {
//			List list = new ArrayList();
//			String sql = "from LoginUser l where l.loginName = :param1";
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			query.setParameter("param1", login.getLoginName());
//			// query.setParameter("param2", crypto.getEncrypt(login.getPassword()));
//
//			list = query.list();
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//
//			if (list.size() > 0) {
//				loginData = (Login) list.get(0);
//				int passwordExpiredDayNo = getPasswordExpiredDayNo(login);
//				int systemUserId = getSystemUserId(login);
//				loginData.setPasswordExpiredDayNo(passwordExpiredDayNo);
//				loginData.setSystemUserId(systemUserId);
//				try {
//					// null login if incorrect password is entered
//					if (!passUtil.checkPassword(login.getPassword(), loginData.getPassword())) {
//						loginData = null;
//					}
//					// error when no salt present, must be old password
//				} catch (IllegalArgumentException e) {
//					// move passwords from encryption to salted hashing
//
//					// if (loginData.getPassword().equals(crypto.getEncrypt(login.getPassword()))) {
//					// updateCryptoPasswordToHash(loginData);
//					// } else {
//					// loginData = null;
//					PasswordUtil pUtil = new PasswordUtil();
//					loginData.setPassword(pUtil.hashPassword(login.getPassword()));
//					// }
//				}
//			}
//
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("LoginDAOImpl", "getValidateLogin()", e.toString());
//			throw new LIMSRuntimeException("Error in Login getValidateLogin()", e);
//		}
//
//		return loginData;
//	}

    /**
     * Get the user login information base on login name
     *
     * @param loginName the user login name
     * @return login object
     */
//	@Override
//	public Login getUserProfile(String loginName) throws LIMSRuntimeException {
//
//		Login login = null;
//		try {
//			List list = new ArrayList();
//			String sql = "from LoginUser l where l.loginName = :param";
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			query.setParameter("param", loginName);
//
//			list = query.list();
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//
//			if (list.size() > 0) {
//				login = (Login) list.get(0);
//				int passwordExpiredDayNo = getPasswordExpiredDayNo(login);
//				int systemUserId = getSystemUserId(login);
//				login.setPasswordExpiredDayNo(passwordExpiredDayNo);
//				login.setSystemUserId(systemUserId);
//			}
//
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("LoginDAOImpl", "getUserProfile()", e.toString());
//			throw new LIMSRuntimeException("Error in Login getUserProfile()", e);
//		}
//		return login;
//	}

    /**
     * Get the password expiration day
     *
     * @param login the login object
     * @return type integer the password expiration day
     */
    @Override
    @Transactional
    public int getPasswordExpiredDayNo(LoginUser login) throws LIMSRuntimeException {
        int retVal = 0;
        try {
            String sql = "SELECT \n" + "                    floor(current_date-password_expired_dt)*-1 as cnt\n"
                    + "                FROM Login_User l where l.LOGIN_NAME = :loginName ";
            Object obj = entityManager.unwrap(Session.class).createSQLQuery(sql)
                    .setString("loginName", login.getLoginName()).uniqueResult();
            if (obj != null) {
                retVal = (int) Float.parseFloat(obj.toString());
            }
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in getPasswordExpiredDayNo()", e);
        }

        return retVal;

    }

    /**
     * Get the system user id
     *
     * @param login the login object
     * @return type integer the system user id
     */
    @Override
    @Transactional
    public int getSystemUserId(LoginUser login) throws LIMSRuntimeException {
        int retVal = 0;
        try {
            String sql = "SELECT id from System_User su Where su.login_name = :loginName and su.is_active='Y'";
            Query query = entityManager.unwrap(Session.class).createSQLQuery(sql);
            query.setString("loginName", login.getLoginName());
            Object obj = query.uniqueResult();
//            Object obj = entityManager.unwrap(Session.class).getNamedQuery("login.getSystemUserId")
//                    .setString("loginName", login.getLoginName()).uniqueResult();

            if (obj != null) {
                retVal = Integer.parseInt(obj.toString());
            }

        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in getSystemUserId()", e);
        } finally {
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        }

        return retVal;
    }

    /**
     * Update the user passsword
     *
     * @param login the login object
     * @return true if success, false otherwise
     */
//	@Override
//	public void updatePassword(Login login) throws LIMSRuntimeException {
//
//		// Crypto crypto = new Crypto();
//		PasswordUtil passUtil = new PasswordUtil();
//
//		try {
//			String password = login.getPassword();
//			// login.setPassword(crypto.getEncrypt(login.getPassword()));
//			login.setPassword(passUtil.hashPassword(login.getPassword()));
//
//			auditDAO.saveHistory(login, readLoginUser(login.getId()), login.getSysUserId(),
//					IActionConstants.AUDIT_TRAIL_UPDATE, "LOGIN_USER");
//
//			entityManager.unwrap(Session.class).merge(login);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(login);
//			// entityManager.unwrap(Session.class).refresh // CSL remove old(login);
//
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("LoginDAOImpl", "updatePassword()", e.toString());
//			throw new LIMSRuntimeException("Error in Login updatePassword()", e);
//		}
//	}

    /**
     * bugzilla 2286 Lock the user account after number of failed attempt
     *
     * @param login the login object
     * @return true if success, false otherwise
     */
//	@Override
//	public boolean lockAccount(Login login) throws LIMSRuntimeException {
//		boolean isSuccess = false;
//		try {
//			entityManager.unwrap(Session.class).merge(login);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(login);
//			// entityManager.unwrap(Session.class).refresh // CSL remove old(login);
//			isSuccess = true;
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("LoginDAOImpl", "lockAccount()", e.toString());
//			throw new LIMSRuntimeException("Error in Login lockAccount()", e);
//		}
//		return isSuccess;
//	}

    /**
     * bugzilla 2286 unlock the user account after number of minutes
     *
     * @param login the login object
     * @return true if success, false otherwise
     */
//	@Override
//	public boolean unlockAccount(Login login) throws LIMSRuntimeException {
//		boolean isSuccess = false;
//		try {
//			entityManager.unwrap(Session.class).merge(login);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(login);
//			// entityManager.unwrap(Session.class).refresh // CSL remove old(login);
//			isSuccess = true;
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("LoginDAOImpl", "unlockAccount()", e.toString());
//			throw new LIMSRuntimeException("Error in Login unlockAccount()", e);
//		}
//		return isSuccess;
//	}
}