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
package us.mn.state.health.lims.common.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.transaction.Synchronization;

import org.hibernate.CacheMode;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

import us.mn.state.health.lims.hibernate.HibernateUtil;

/*
 * The intent of this class is to make testing of classes which access the db easier.
 */
public class HibernateProxy {
	
	private static boolean useActualImp = true;
	
	public static void useTestImpl(boolean useTest){
		useActualImp = !useTest;
	}
	
	public static Transaction beginTransaction(){
		if( useActualImp ){
			return HibernateUtil.getSession().beginTransaction(); 
		}else{
			return TestImpl.beginTransaction();
		}
	}
	
	public static Query createSQLQuery(String query){
		if( useActualImp){
			return HibernateUtil.getSession().createSQLQuery(query);
		}else{
			return new TestQuery();
		}
	}
	
	public static void closeSession(){
		if( useActualImp ){
			HibernateUtil.closeSession();
		}
	}
	
	public static void flush(){
		if( useActualImp ){
			HibernateUtil.getSession().flush();
		}
	}

	public static void clear(){
		if( useActualImp ){
			HibernateUtil.getSession().clear();
		}
	}
	public static class TestImpl{
		
		public static Transaction beginTransaction(){
			return new Transaction(){

				public void begin() throws HibernateException {
					
					
				}

				public void commit() throws HibernateException {
					
					
				}

				public boolean isActive() throws HibernateException {
					
					return false;
				}

				public void registerSynchronization(Synchronization arg0) throws HibernateException {
					
					
				}

				public void rollback() throws HibernateException {
					
					
				}

				public void setTimeout(int arg0) {
					
					
				}

				public boolean wasCommitted() throws HibernateException {
					
					return false;
				}

				public boolean wasRolledBack() throws HibernateException {
					
					return false;
				}
				
			};
		}
	}
	
	
	public static class TestQuery implements Query{
		@SuppressWarnings("unchecked")
		private static List queryList = null;
		
		@SuppressWarnings("unchecked")
		public static void setQueryListResponse(List list ){
			queryList = list;
		}
		public TestQuery(){}
		
		public int executeUpdate() throws HibernateException {
			
			return 0;
		}

		public String[] getNamedParameters() throws HibernateException {
			
			return null;
		}

		public String getQueryString() {
			
			return null;
		}

		public String[] getReturnAliases() throws HibernateException {
			
			return null;
		}

		public Type[] getReturnTypes() throws HibernateException {
			
			return null;
		}

		@SuppressWarnings("unchecked")
		public Iterator iterate() throws HibernateException {
			
			return null;
		}

		@SuppressWarnings("unchecked")
		public List list() throws HibernateException {
			
			if( queryList != null){
				return queryList;
			}
			
			return new ArrayList();
		}

		public ScrollableResults scroll() throws HibernateException {
			
			return null;
		}

		public ScrollableResults scroll(ScrollMode arg0) throws HibernateException {
			
			return null;
		}

		public Query setBigDecimal(int arg0, BigDecimal arg1) {
			
			return null;
		}

		public Query setBigDecimal(String arg0, BigDecimal arg1) {
			
			return null;
		}

		public Query setBigInteger(int arg0, BigInteger arg1) {
			
			return null;
		}

		public Query setBigInteger(String arg0, BigInteger arg1) {
			
			return null;
		}

		public Query setBinary(int arg0, byte[] arg1) {
			
			return null;
		}

		public Query setBinary(String arg0, byte[] arg1) {
			
			return null;
		}

		public Query setBoolean(int arg0, boolean arg1) {
			
			return null;
		}

		public Query setBoolean(String arg0, boolean arg1) {
			
			return null;
		}

		public Query setByte(int arg0, byte arg1) {
			
			return null;
		}

		public Query setByte(String arg0, byte arg1) {
			
			return null;
		}

		public Query setCacheMode(CacheMode arg0) {
			
			return null;
		}

		public Query setCacheRegion(String arg0) {
			
			return null;
		}

		public Query setCacheable(boolean arg0) {
			
			return null;
		}

		public Query setCalendar(int arg0, Calendar arg1) {
			
			return null;
		}

		public Query setCalendar(String arg0, Calendar arg1) {
			
			return null;
		}

		public Query setCalendarDate(int arg0, Calendar arg1) {
			
			return null;
		}

		public Query setCalendarDate(String arg0, Calendar arg1) {
			
			return null;
		}

		public Query setCharacter(int arg0, char arg1) {
			
			return null;
		}

		public Query setCharacter(String arg0, char arg1) {
			
			return null;
		}

		public Query setComment(String arg0) {
			
			return null;
		}

		public Query setDate(int arg0, Date arg1) {
			
			return null;
		}

		public Query setDate(String arg0, Date arg1) {
			
			return null;
		}

		public Query setDouble(int arg0, double arg1) {
			
			return null;
		}

		public Query setDouble(String arg0, double arg1) {
			
			return null;
		}

		public Query setEntity(int arg0, Object arg1) {
			
			return null;
		}

		public Query setEntity(String arg0, Object arg1) {
			
			return null;
		}

		public Query setFetchSize(int arg0) {
			
			return null;
		}

		public Query setFirstResult(int arg0) {
			
			return null;
		}

		public Query setFloat(int arg0, float arg1) {
			
			return null;
		}

		public Query setFloat(String arg0, float arg1) {
			
			return null;
		}

		public Query setFlushMode(FlushMode arg0) {
			
			return null;
		}

		public Query setInteger(int arg0, int arg1) {
			
			return null;
		}

		public Query setInteger(String arg0, int arg1) {
			
			return null;
		}

		public Query setLocale(int arg0, Locale arg1) {
			
			return null;
		}

		public Query setLocale(String arg0, Locale arg1) {
			
			return null;
		}

		public Query setLockMode(String arg0, LockMode arg1) {
			
			return null;
		}

		public Query setLong(int arg0, long arg1) {
			
			return null;
		}

		public Query setLong(String arg0, long arg1) {
			
			return null;
		}

		public Query setMaxResults(int arg0) {
			
			return null;
		}

		public Query setParameter(int arg0, Object arg1) throws HibernateException {
			
			return null;
		}

		public Query setParameter(String arg0, Object arg1) throws HibernateException {
			
			return null;
		}

		public Query setParameter(int arg0, Object arg1, Type arg2) {
			
			return null;
		}

		public Query setParameter(String arg0, Object arg1, Type arg2) {
			
			return null;
		}

		@SuppressWarnings("unchecked")
		public Query setParameterList(String arg0, Collection arg1) throws HibernateException {
			
			return null;
		}

		public Query setParameterList(String arg0, Object[] arg1) throws HibernateException {
			
			return null;
		}

		@SuppressWarnings("unchecked")
		public Query setParameterList(String arg0, Collection arg1, Type arg2) throws HibernateException {
			
			return null;
		}

		public Query setParameterList(String arg0, Object[] arg1, Type arg2) throws HibernateException {
			
			return null;
		}

		public Query setParameters(Object[] arg0, Type[] arg1) throws HibernateException {
			
			return null;
		}

		public Query setProperties(Object arg0) throws HibernateException {
			
			return null;
		}

		public Query setReadOnly(boolean arg0) {
			
			return null;
		}

		public Query setSerializable(int arg0, Serializable arg1) {
			
			return null;
		}

		public Query setSerializable(String arg0, Serializable arg1) {
			
			return null;
		}

		public Query setShort(int arg0, short arg1) {
			
			return null;
		}

		public Query setShort(String arg0, short arg1) {
			
			return null;
		}

		public Query setString(int arg0, String arg1) {
			
			return null;
		}

		public Query setString(String arg0, String arg1) {
			
			return null;
		}

		public Query setText(int arg0, String arg1) {
			
			return null;
		}

		public Query setText(String arg0, String arg1) {
			
			return null;
		}

		public Query setTime(int arg0, Date arg1) {
			
			return null;
		}

		public Query setTime(String arg0, Date arg1) {
			
			return null;
		}

		public Query setTimeout(int arg0) {
			
			return null;
		}

		public Query setTimestamp(int arg0, Date arg1) {
			
			return null;
		}

		public Query setTimestamp(String arg0, Date arg1) {
			
			return null;
		}

		public Object uniqueResult() throws HibernateException {
			
			return null;
		}
		
	}
}	

