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
package us.mn.state.health.lims.hibernate.resources.usertype;

import java.io.InputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;

import org.dom4j.Node;
import org.hibernate.EntityMode;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.jdbc.Size;
import org.hibernate.engine.spi.Mapping;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
//import org.hibernate.lob.BlobImpl;
//import org.hibernate.lob.SerializableBlob;
import org.hibernate.type.AbstractType;
//import org.hibernate.util.ArrayHelper;

/**
 * Accesses property values via a get/set pair, which may be nonpublic. The
 * default (and recommended strategy).
 *
 * @author Diane Benz
 * added for bugzilla 1908: to solve Vietnam compatibility problem with Oracle Blob - Postgres bytea
 * and allow inserts/updates to BLOB/bytea
 * Have created this UserType to convert between Blob/bytea and ObjectInputStream/ObjectOutputStream
 * Updated history.hbm.xml file changes column (BLOB/bytea) to use this custom type instead of java.sql.Blob
 */
/**
 * <tt>blob</tt>: A type that maps an SQL BLOB to a java.sql.Blob.
 *
 * @author Gavin King
 */
public class LIMSRawDataUserType extends AbstractType {

	// bugzilla 1908 modified this method. This seems to work for postgres (bytea)
	// AND oracle (Blob)
	public void set(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
			throws HibernateException, SQLException {

		if (value == null) {
			// 1908 changed from Types.Blob to Types.BINARY for postgres
			st.setNull(index, Types.BINARY);
		}
//		else {
//
//			if (value instanceof SerializableBlob) {
//				value = ( (SerializableBlob) value ).getWrappedBlob();
//			}
//
//
//			BlobImpl blob = (BlobImpl) value;
//			st.setBinaryStream( index, blob.getBinaryStream(), (int) blob.length() );
//
//		}

	}

	// bugzilla 1908 modified this method. This seems to work for postgres (bytea)
	// AND oracle (Blob)
	public Object get(ResultSet rs, String name, SharedSessionContractImplementor session)
			throws HibernateException, SQLException {

//		SerializableBlob serializableBlob = null;
		InputStream is = rs.getBinaryStream(name);
//		BlobImpl blob = null;

		// bugzilla 2569 need to check if blob in history.changes is null when getting
		// data
		if (is != null) {
			// blob = new BlobImpl(is, is.available());
			if (true) {
			}
		}

//		return rs.wasNull() ? null : serializableBlob;
		return null;

	}

	@Override
	public Class getReturnedClass() {
		return Blob.class;
	}

	public boolean isEqual(Object x, Object y, EntityMode entityMode) {
		return x == y;
	}

	public int getHashCode(Object x, EntityMode entityMode) {
		return System.identityHashCode(x);
	}

	public int compare(Object x, Object y, EntityMode entityMode) {
		return 0; // lobs cannot be compared
	}

	@Override
	public String getName() {
		return "blob";
	}

	@Override
	public Serializable disassemble(Object value, SharedSessionContractImplementor session, Object owner)
			throws HibernateException {
		throw new UnsupportedOperationException("Blobs are not cacheable");
	}

	public Object deepCopy(Object value, EntityMode entityMode, SessionFactoryImplementor factory) {
		return value;
	}

	public Object fromXMLNode(Node xml, Mapping factory) {
		throw new UnsupportedOperationException("todo");
	}

	@Override
	public int getColumnSpan(Mapping mapping) {
		return 1;
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String name, SharedSessionContractImplementor session, Object owner)
			throws HibernateException, SQLException {
		return get(rs, name, session);
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner)
			throws HibernateException, SQLException {
		return get(rs, names[0], session);
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, boolean[] settable,
			SharedSessionContractImplementor session) throws HibernateException, SQLException {
		if (settable[0]) {
			set(st, value, index, session);
		}
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
			throws HibernateException, SQLException {
		set(st, value, index, session);
	}

	@Override
	public Object replace(Object original, Object target, SharedSessionContractImplementor session, Object owner,
			Map copyCache) throws HibernateException {
		// Blobs are ignored by merge()
		return target;
	}

	@Override
	public int[] sqlTypes(Mapping mapping) throws MappingException {
		return new int[] { Types.BLOB };
	}

	public void setToXMLNode(Node node, Object value, SessionFactoryImplementor factory) {
		throw new UnsupportedOperationException("todo");
	}

	@Override
	public String toLoggableString(Object value, SessionFactoryImplementor factory) throws HibernateException {
		return value == null ? "null" : value.toString();
	}

	@Override
	public boolean[] toColumnNullness(Object value, Mapping mapping) {
//		return value==null ? ArrayHelper.FALSE : ArrayHelper.TRUE;
		return null;
	}

	@Override
	public boolean isDirty(Object old, Object current, boolean[] checkable, SharedSessionContractImplementor session)
			throws HibernateException {
		return checkable[0] && isDirty(old, current, session);
	}

	@Override
	public Size[] dictatedSizes(Mapping mapping) throws MappingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Size[] defaultSizes(Mapping mapping) throws MappingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object deepCopy(Object value, SessionFactoryImplementor factory) throws HibernateException {
		return value;
	}

}