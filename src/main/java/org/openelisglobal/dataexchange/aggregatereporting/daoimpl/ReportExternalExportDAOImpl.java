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
 * Copyright (C) CIRG, University of Washington, Seattle WA.  All Rights Reserved.
 *
 */
package org.openelisglobal.dataexchange.aggregatereporting.daoimpl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.dataexchange.aggregatereporting.dao.ReportExternalExportDAO;
import org.openelisglobal.dataexchange.aggregatereporting.valueholder.ReportExternalExport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ReportExternalExportDAOImpl extends BaseDAOImpl<ReportExternalExport, String>
        implements ReportExternalExportDAO {
    public ReportExternalExportDAOImpl() {
        super(ReportExternalExport.class);
    }

    private static final long DAY_IN_MILLSEC = 1000L * 60L * 60L * 24L;

    @Override
    @Transactional(readOnly = true)
    public List<ReportExternalExport> getRecalculateReportExports(String reportQueueTypeId)
            throws LIMSRuntimeException {
        String sql = "from ReportExternalExport rq where rq.recalculate = true and rq.typeId = :typeId";
        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("typeId", Integer.parseInt(reportQueueTypeId));
            List<ReportExternalExport> reports = query.list();

            // closeSession(); // CSL remove old

            return reports;
        } catch (HibernateException e) {
            handleException(e, "getRecalculateReportExports");
        }
        return null;

    }

    @Override
    @Transactional(readOnly = true)
    public List<ReportExternalExport> getUnsentReportExports(String reportQueueTypeId) throws LIMSRuntimeException {
        String sql = "from ReportExternalExport rq where rq.send = true and rq.typeId = :typeId";
        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("typeId", Integer.parseInt(reportQueueTypeId));
            List<ReportExternalExport> reports = query.list();

            // closeSession(); // CSL remove old

            return reports;
        } catch (HibernateException e) {
            handleException(e, "getRecalculateReportExternalExports");
        }

        return null;

    }

    @Override
    @Transactional(readOnly = true)
    public ReportExternalExport getLatestSentReportExport(String reportQueueTypeId) throws LIMSRuntimeException {
        String sql = "from ReportExternalExport rq where rq.send = false and rq.typeId = :typeId order by rq.sentDate desc";

        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("typeId", Integer.parseInt(reportQueueTypeId));
            ReportExternalExport report = (ReportExternalExport) query.setMaxResults(1).uniqueResult();

            // closeSession(); // CSL remove old

            return report;
        } catch (HibernateException e) {
            handleException(e, "getLatestSentReportExternalExport");
        }

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public ReportExternalExport getLatestEventReportExport(String reportQueueTypeId) throws LIMSRuntimeException {
        String sql = "from ReportExternalExport rq where rq.typeId = :typeId order by rq.eventDate desc";

        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("typeId", Integer.parseInt(reportQueueTypeId));
            ReportExternalExport report = (ReportExternalExport) query.setMaxResults(1).uniqueResult();

            // closeSession(); // CSL remove old

            return report;
        } catch (HibernateException e) {
            handleException(e, "getLatestSentReportExternalExport");
        }

        return null;

    }

    
    @Override
    @Transactional(readOnly = true)
    public List<ReportExternalExport> getReportsInDateRange(Timestamp lower, Timestamp upper, String reportQueueTypeId)
            throws LIMSRuntimeException {
        String sql = "from ReportExternalExport rq where rq.sentDate >= :lower and rq.sentDate <= :upper";

        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setTimestamp("lower", lower);
            query.setTimestamp("upper", upper);
            List<ReportExternalExport> reports = query.list();

            // closeSession(); // CSL remove old

            return reports;
        } catch (HibernateException e) {
            handleException(e, "getReportsInDateRange");
        }

        return null;
    }

//	@Override
//	public void insertReportExternalExport(ReportExternalExport report) throws LIMSRuntimeException {
//		try {
//			String id = (String) entityManager.unwrap(Session.class).save(report);
//			report.setId(id);
//			// closeSession(); // CSL remove old
//		} catch (HibernateException e) {
//			handleException(e, "insertReportExternalExport");
//		}
//	}

//	@Override
//	public void updateReportExternalExport(ReportExternalExport report) throws LIMSRuntimeException {
//
//		try {
//			entityManager.unwrap(Session.class).merge(report);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(report);
//			// entityManager.unwrap(Session.class).refresh // CSL remove old(report);
//		} catch (RuntimeException e) {
//			handleException(e, "updateReportExternalExport");
//		}
//	}

    @Override
    public ReportExternalExport readReportExternalExport(String idString) throws LIMSRuntimeException {

        try {
            ReportExternalExport data = entityManager.unwrap(Session.class).get(ReportExternalExport.class, idString);
            // closeSession(); // CSL remove old
            return data;
        } catch (HibernateException e) {
            handleException(e, "readReportExternalExport");
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Timestamp getLastSentTimestamp() throws LIMSRuntimeException {
        String sql = "From ReportExternalExport ree where ree.sentDate IS NOT NULL order by ree.sentDate DESC";

        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            ReportExternalExport report = (ReportExternalExport) query.setMaxResults(1).uniqueResult();
            // closeSession(); // CSL remove old
            if (report != null) {
                return report.getSentDate();
            }
        } catch (HibernateException e) {
            handleException(e, "getLastSentTimestamp");
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Timestamp getLastCollectedTimestamp() throws LIMSRuntimeException {
        String sql = "From ReportExternalExport ree order by ree.collectionDate DESC";

        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            ReportExternalExport report = (ReportExternalExport) query.setMaxResults(1).uniqueResult();
            // closeSession(); // CSL remove old
            if (report != null) {
                return report.getCollectionDate();
            }
        } catch (HibernateException e) {
            handleException(e, "getLastCollectedTimestamp");
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public ReportExternalExport getReportByEventDateAndType(ReportExternalExport report) throws LIMSRuntimeException {
        String sql = "From ReportExternalExport ree where ree.eventDate >= :eventDate and ree.eventDate < :nextDay and ree.typeId = :typeId";

        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setDate("eventDate", report.getEventDate());
            query.setDate("nextDay", new Timestamp(report.getEventDate().getTime() + DAY_IN_MILLSEC));
            query.setInteger("typeId", Integer.parseInt(report.getTypeId()));
            ReportExternalExport foundReport = (ReportExternalExport) query.setMaxResults(1).uniqueResult();
            // closeSession(); // CSL remove old
            return foundReport == null ? report : foundReport;

        } catch (HibernateException e) {
            handleException(e, "getReportByEventDateAndType");
        }
        return report;
    }

    @Override
    public ReportExternalExport loadReport(ReportExternalExport report) throws LIMSRuntimeException {
        return readReportExternalExport(report.getId());
    }

//	@Override
//	public void delete(ReportExternalExport report) throws LIMSRuntimeException {
//		try {
//			entityManager.unwrap(Session.class).delete(readReportExternalExport(report.getId()));
//			// closeSession(); // CSL remove old
//		} catch (RuntimeException e) {
//			handleException(e, "delete");
//		}
//	}
}
