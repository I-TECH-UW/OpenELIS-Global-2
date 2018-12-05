package us.mn.state.health.lims.barcode.daoimpl;

import java.util.List;

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.barcode.dao.BarcodeLabelInfoDAO;
import us.mn.state.health.lims.barcode.valueholder.BarcodeLabelInfo;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.hibernate.HibernateUtil;

/**
 * Class for inserting, updating, and retrieving
 * lims/barcode/valueholder/BarcodeLabelInfo from the database
 * (clinlims.barcode_label_info)
 * 
 * @author Caleb
 *
 */
public class BarcodeLabelInfoDAOImpl extends BaseDAOImpl implements BarcodeLabelInfoDAO {

  @SuppressWarnings("rawtypes")
  List list;

  /* (non-Javadoc)
   * @see us.mn.state.health.lims.barcode.dao.BarcodeLabelInfoDAO#insertData(us.mn.state.health.lims.barcode.valueholder.BarcodeLabelInfo)
   */
  public boolean insertData(BarcodeLabelInfo barcodeLabelInfo) throws LIMSRuntimeException {
    try {
      String id = (String) HibernateUtil.getSession().save(barcodeLabelInfo);
      barcodeLabelInfo.setId(id);

      // add to audit trail
      AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
      String sysUserId = barcodeLabelInfo.getSysUserId();
      String tableName = "BARCODE_LABEL_INFO";
      auditDAO.saveNewHistory(barcodeLabelInfo, sysUserId, tableName);

      HibernateUtil.getSession().flush();
      HibernateUtil.getSession().clear();
    } catch (Exception e) {
      LogEvent.logError("BarcodeLabelInfoDAOImpl", "insertData()", e.toString());
      throw new LIMSRuntimeException("Error in BarcodeLabelInfo insertData()", e);
    }
    return true;
  }

  /* (non-Javadoc)
   * @see us.mn.state.health.lims.barcode.dao.BarcodeLabelInfoDAO#updateData(us.mn.state.health.lims.barcode.valueholder.BarcodeLabelInfo)
   */
  public void updateData(BarcodeLabelInfo barcodeLabelInfo) throws LIMSRuntimeException {
    BarcodeLabelInfo oldData = readBarcodeLabelInfo(barcodeLabelInfo.getId());
    BarcodeLabelInfo newData = barcodeLabelInfo;

    // add to audit trail
    try {
      AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
      String sysUserId = barcodeLabelInfo.getSysUserId();
      String event = IActionConstants.AUDIT_TRAIL_UPDATE;
      String tableName = "BARCODE_LABEL_INFO";
      auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
    } catch (Exception e) {
      // bugzilla 2154
      LogEvent.logError("BarcodeLabelInfoDAOImpl", "AuditTrail updateData()", e.toString());
      throw new LIMSRuntimeException("Error in Login AuditTrail updateData()", e);
    }

    try {
      HibernateUtil.getSession().merge(barcodeLabelInfo);
      HibernateUtil.getSession().flush();
      HibernateUtil.getSession().clear();
      HibernateUtil.getSession().evict(barcodeLabelInfo);
      HibernateUtil.getSession().refresh(barcodeLabelInfo);
    } catch (Exception e) {
      LogEvent.logError("BarcodeLabelInfoDAOImpl", "updateData()", e.toString());
      throw new LIMSRuntimeException("Error in BarcodeLabelInfo updateData()", e);
    }
  }

  /* (non-Javadoc)
   * @see us.mn.state.health.lims.barcode.dao.BarcodeLabelInfoDAO#getDataByCode(java.lang.String)
   */
  public BarcodeLabelInfo getDataByCode(String code) throws LIMSRuntimeException {
    BarcodeLabelInfo bli = null;
    try {
      String sql = "From BarcodeLabelInfo b where b.code = :param";
      org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
      query.setParameter("param", code.trim());
      list = query.list();
      if (list != null && list.size() > 0)
        bli = (BarcodeLabelInfo) list.get(0);
      HibernateUtil.getSession().flush();
      HibernateUtil.getSession().clear();
    } catch (Exception e) {
      LogEvent.logError("BarcodeLabelInfoDAOImpl", "getDataByCode()", e.toString());
      throw new LIMSRuntimeException("Error in getDataByCode()", e);
    }
    return bli;
  }

  /**
   * Get BarcodeLabelInfo by id
   * @param idString  PK of the BarcodeLabelInfo to be retrieved 
   * @return          The persisted BarcodeLabelInfo
   */
  public BarcodeLabelInfo readBarcodeLabelInfo(String idString) {
    BarcodeLabelInfo recoveredBarcodeLabelInfo;
    try {
      recoveredBarcodeLabelInfo = (BarcodeLabelInfo) HibernateUtil.getSession()
              .get(BarcodeLabelInfo.class, idString);
      HibernateUtil.getSession().flush();
      HibernateUtil.getSession().clear();
    } catch (Exception e) {
      LogEvent.logError("BarcodeLabelInfoDAOImpl", "readBarcodeLabelInfo()", e.toString());
      throw new LIMSRuntimeException("Error in BarcodeLabelInfo readBarcodeLabelInfo()", e);
    }
    return recoveredBarcodeLabelInfo;
  }

}
