package org.openelisglobal.barcode.daoimpl;

import org.openelisglobal.barcode.dao.BarcodeLabelInfoDAO;
import org.openelisglobal.barcode.valueholder.BarcodeLabelInfo;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class for inserting, updating, and retrieving
 * lims/barcode/valueholder/BarcodeLabelInfo from the database
 * (clinlims.barcode_label_info)
 *
 * @author Caleb
 *
 */
@Component
@Transactional
public class BarcodeLabelInfoDAOImpl extends BaseDAOImpl<BarcodeLabelInfo, String> implements BarcodeLabelInfoDAO {

    public BarcodeLabelInfoDAOImpl() {
        super(BarcodeLabelInfo.class);
    }

    /*
     * // * (non-Javadoc) // * // * @see // *
     * org.openelisglobal.barcode.dao.BarcodeLabelInfoDAO#insertData(us.mn. // *
     * state.health.lims.barcode.valueholder.BarcodeLabelInfo) //
     */
//	@Override
//	public boolean insertData(BarcodeLabelInfo barcodeLabelInfo) throws LIMSRuntimeException {
//		try {
//			String id = (String) entityManager.unwrap(Session.class).save(barcodeLabelInfo);
//			barcodeLabelInfo.setId(id);
//
//			// add to audit trail
//
//			String sysUserId = barcodeLabelInfo.getSysUserId();
//			String tableName = "BARCODE_LABEL_INFO";
//			auditDAO.saveNewHistory(barcodeLabelInfo, sysUserId, tableName);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			LogEvent.logError("BarcodeLabelInfoDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in BarcodeLabelInfo insertData()", e);
//		}
//		return true;
//	}

    /*
     * (non-Javadoc)
     *
     * @see org.openelisglobal.barcode.dao.BarcodeLabelInfoDAO#updateData(us.mn.
     * state.health.lims.barcode.valueholder.BarcodeLabelInfo)
     */
//	@Override
//	public void updateData(BarcodeLabelInfo barcodeLabelInfo) throws LIMSRuntimeException {
//		BarcodeLabelInfo oldData = readBarcodeLabelInfo(barcodeLabelInfo.getId());
//		BarcodeLabelInfo newData = barcodeLabelInfo;
//
//		// add to audit trail
//		try {
//
//			String sysUserId = barcodeLabelInfo.getSysUserId();
//			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
//			String tableName = "BARCODE_LABEL_INFO";
//			auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("BarcodeLabelInfoDAOImpl", "AuditTrail updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in Login AuditTrail updateData()", e);
//		}
//
//		try {
//			entityManager.unwrap(Session.class).merge(barcodeLabelInfo);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(barcodeLabelInfo);
//			// entityManager.unwrap(Session.class).refresh // CSL remove
//			// old(barcodeLabelInfo);
//		} catch (RuntimeException e) {
//			LogEvent.logError("BarcodeLabelInfoDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in BarcodeLabelInfo updateData()", e);
//		}
//	}

    /*
     * (non-Javadoc)
     *
     * @see org.openelisglobal.barcode.dao.BarcodeLabelInfoDAO#getDataByCode(java.
     * lang.String)
     */
//	@Override
//	public BarcodeLabelInfo getDataByCode(String code) throws LIMSRuntimeException {
//		BarcodeLabelInfo bli = null;
//		try {
//			String sql = "From BarcodeLabelInfo b where b.code = :param";
//			org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
//			query.setParameter("param", code.trim());
//			list = query.list();
//			if (list != null && list.size() > 0) {
//				bli = (BarcodeLabelInfo) list.get(0);
//			}
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			LogEvent.logError("BarcodeLabelInfoDAOImpl", "getDataByCode()", e.toString());
//			throw new LIMSRuntimeException("Error in getDataByCode()", e);
//		}
//		return bli;
//	}

    /**
     * Get BarcodeLabelInfo by id
     *
     * @param idString PK of the BarcodeLabelInfo to be retrieved
     * @return The persisted BarcodeLabelInfo
     */
//	public BarcodeLabelInfo readBarcodeLabelInfo(String idString) {
//		BarcodeLabelInfo recoveredBarcodeLabelInfo;
//		try {
//			recoveredBarcodeLabelInfo = entityManager.unwrap(Session.class).get(BarcodeLabelInfo.class, idString);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//		} catch (RuntimeException e) {
//			LogEvent.logError("BarcodeLabelInfoDAOImpl", "readBarcodeLabelInfo()", e.toString());
//			throw new LIMSRuntimeException("Error in BarcodeLabelInfo readBarcodeLabelInfo()", e);
//		}
//		return recoveredBarcodeLabelInfo;
//	}

}
