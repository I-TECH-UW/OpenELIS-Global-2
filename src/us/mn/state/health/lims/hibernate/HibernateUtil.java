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
package us.mn.state.health.lims.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Interceptor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.hibernate.resources.interceptor.LIMSTrimDataInterceptor;

/**
 * Basic Hibernate helper class, handles SessionFactory, Session and Transaction.
 * <p>
 * Uses a static initializer for the initial SessionFactory creation
 * and holds Session and Transactions in thread local variables. All
 * exceptions are wrapped in an unchecked InfrastructureException.
 *
 * @author christian@hibernate.org
 */
public class HibernateUtil {

    public static final String HIBERNATE_CFG_FILE_PROPERTY = "openELIS.hibernate.cfg";
    private static Configuration configuration;
    private static SessionFactory sessionFactory;
    private static final ThreadLocal threadSession = new ThreadLocal();
    private static final ThreadLocal threadTransaction = new ThreadLocal();
    private static final ThreadLocal threadInterceptor = new ThreadLocal();
    private static String CONFIG_FILE_LOCATION = "/us/mn/state/health/lims/hibernate/hibernate.cfg.xml";
    
    private static String configFile = CONFIG_FILE_LOCATION;
    
    static {
        String testCfg = System.getProperty(HIBERNATE_CFG_FILE_PROPERTY);
        if (null != testCfg) {
            configFile = testCfg;
        }
    }

    static {
        try {  	
        	
        	configuration = new Configuration();
			//bugzilla 1939 (trim changed data before update/insert)
			configuration.setInterceptor(new LIMSTrimDataInterceptor());
             sessionFactory = configuration.configure(configFile).buildSessionFactory();
            // We could also let Hibernate bind it to JNDI:
            
            // configuration.configure().buildSessionFactory()
        } catch (Throwable ex) {
            // We have to catch Throwable, otherwise we will miss
            // NoClassDefFoundError and other subclasses of Error
        	//ex.printStackTrace();
            //log.error("Building SessionFactory failed.", ex);
            //bugzilla 2154
			LogEvent.logError("HibernateUtil","static constructor","Building SessionFactory failed. " + ex.toString());
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Returns the SessionFactory used for this static class.
     *
     * @return SessionFactory
     */

    public static SessionFactory getSessionFactory() {
        /* Instead of a static variable, use JNDI:
                         SessionFactory sessions = null;
                         try {
                Context ctx = new InitialContext();
                String jndiName = "java:hibernate/HibernateFactory";
                sessions = (SessionFactory)ctx.lookup(jndiName);
                         } catch (NamingException ex) {
                throw new InfrastructureException(ex);
                         }
                         return sessions;
         */
        if( sessionFactory == null){
            rebuildSessionFactory();
        }

        return sessionFactory;
    }

    /**
     * Returns the original Hibernate configuration.
     *
     * @return Configuration
     */

    public static Configuration getConfiguration() {
        return configuration;
    }

    /**
     * Rebuild the SessionFactory with the static Configuration.
     *
     */
    public static void rebuildSessionFactory() throws LIMSRuntimeException {
        synchronized (sessionFactory) {
            try {
                sessionFactory = getConfiguration().buildSessionFactory();
            } catch (Exception ex) {
                //bugzilla 2154
			    LogEvent.logError("HibernateUtil","rebuildSessionFactory()",ex.toString());
            	throw new LIMSRuntimeException("Error in rebuildSessionFactory()", ex);
            }
        }
    }

    /**
     * Rebuild the SessionFactory with the given Hibernate Configuration.
     *
     * @param cfg
     */

    public static void rebuildSessionFactory(Configuration cfg) throws LIMSRuntimeException {
        synchronized (sessionFactory) {
            try {
                sessionFactory = cfg.buildSessionFactory();
                configuration = cfg;
            } catch (Exception ex) {
                //bugzilla 2154
			    LogEvent.logError("HibernateUtil","rebuildSessionFactory()",ex.toString());
            	throw new LIMSRuntimeException("Error in rebuildSessionFactory()", ex);
            }
        }
    }

    /**
     * Retrieves the current Session local to the thread.
     * <p/>

     * If no Session is open, opens a new Session for the running thread.
     *
     * @return Session
     */
    public static Session getSession() throws LIMSRuntimeException {
        Session s = (Session) threadSession.get();
        try {
            if (s == null) {
                //bugzilla 2154
                LogEvent.logDebug("HibernateUtil","getSession()","Opening new Session for this thread.");
                if (getInterceptor() != null) {
                    LogEvent.logDebug("HibernateUtil","getSession()","Using interceptor: " + getInterceptor().getClass());
                    s = getSessionFactory().openSession(getInterceptor());
                } else {
                    s = getSessionFactory().openSession();
                }
                threadSession.set(s);
            }
        } catch (HibernateException ex) {
            //bugzilla 2154
			LogEvent.logError("HibernateUtil","getSession()",ex.toString());
        	throw new LIMSRuntimeException("Error in getSession()", ex);
        }
        return s;
    }

    /**
     * Closes the Session local to the thread.
     */

    public static void closeSession() throws LIMSRuntimeException {
        try {
            Session s = (Session) threadSession.get();
            threadSession.set(null);
            if (s != null && s.isOpen()) {
                //bugzilla 2154
                LogEvent.logDebug("HibernateUtil","closeSession()","Closing Session of this thread.");
                s.close();
            }
        } catch (HibernateException ex) {
            //bugzilla 2154
			LogEvent.logError("HibernateUtil","closeSession()",ex.toString());
        	throw new LIMSRuntimeException("Error in closeSession()", ex);
        }
    }

    /**
     * Start a new database transaction.
     */
/*
    public static void beginTransaction() throws LIMSRuntimeException {
        Transaction tx = (Transaction) threadTransaction.get();
        try {
            if (tx == null) {
                log.debug("Starting new database transaction in this thread.");
                tx = getSession().beginTransaction();
                threadTransaction.set(tx);
            }
        } catch (HibernateException ex) {
            throw new LIMSRuntimeException("Error in beginTransaction()", ex);
        }
    }
*/
    /**
     * Commit the database transaction.
     */
/*
    public static void commitTransaction() throws LIMSRuntimeException {
        Transaction tx = (Transaction) threadTransaction.get();
        try {
            if (tx != null && !tx.wasCommitted()
                && !tx.wasRolledBack()) {
                log.debug("Committing database transaction of this thread.");
                tx.commit();
            }
            threadTransaction.set(null);
        } catch (HibernateException ex) {
            rollbackTransaction();
            throw new LIMSRuntimeException("Error in commitTransaction()", ex);
        }
    }
*/
    /**
     * Commit the database transaction.
     */
/*
    public static void rollbackTransaction() throws LIMSRuntimeException {
        Transaction tx = (Transaction) threadTransaction.get();
        try {
            threadTransaction.set(null);
            if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack()) {
                log.debug(
                        "Tyring to rollback database transaction of this thread.");
                tx.rollback();
            }
        } catch (HibernateException ex) {
            throw new LIMSRuntimeException("Error in rollbackTransaction()", ex);
        } finally {
            closeSession();
        }
    }
*/
    /**
     * Reconnects a Hibernate Session to the current Thread.
     *
     * @param session The Hibernate Session to be reconnected.
     */

    //public static void reconnect(Session session) throws LIMSRuntimeException {
        //try {
            //session.reconnect();
            //threadSession.set(session);
        //} catch (HibernateException ex) {
            //throw new LIMSRuntimeException("Error in reconnect()", ex);
        //}
    //}

    /**
     * Disconnect and return Session from current Thread.
     *
     * @return Session the disconnected Session
     */
/*
    public static Session disconnectSession() throws LIMSRuntimeException {

        Session session = getSession();
        try {
            threadSession.set(null);
            if (session.isConnected() && session.isOpen()) {
                session.disconnect();
            }
        } catch (HibernateException ex) {
            throw new LIMSRuntimeException("Error in disconnectSession()", ex);
        }
        return session;
    }
*/
    /**
     * Register a Hibernate interceptor with the current thread.
     * <p>

     * Every Session opened is opened with this interceptor after
     * registration. Has no effect if the current Session of the
     * thread is already open, effective on next close()/getSession().
     */
    public static void registerInterceptor(Interceptor interceptor) {
        threadInterceptor.set(interceptor);
    }

    private static Interceptor getInterceptor() {
        Interceptor interceptor =
                (Interceptor) threadInterceptor.get();
        return interceptor;
    }

}
