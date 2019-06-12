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
package us.mn.state.health.lims.dataexchange.aggregatereporting.daoimpl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.dataexchange.aggregatereporting.dao.ReportExternalExportDAO;
import us.mn.state.health.lims.dataexchange.aggregatereporting.valueholder.ReportExternalExport;

@Component
@Transactional
public class ReportExternalExportDAOImpl extends BaseDAOImpl<ReportExternalExport, String>
		implements ReportExternalExportDAO {
	public ReportExternalExportDAOImpl() {
		super(ReportExternalExport.class);
	}

	private static final long DAY_IN_MILLSEC = 1000L * 60L * 60L * 24L;
	private final String TYPE_PARAM = "typeId";

	@Override
	public List<ReportExternalExport> getRecalculateReportExports(String reportQueueTypeId)
			throws LIMSRuntimeException {
		String sql = "from ReportExternalExport rq where rq.recalculate = true and rq.typeId = :" + TYPE_PARAM;

		return handleListResultWithTypeId(sql, reportQueueTypeId);
	}

	@Override
	public List<ReportExternalExport> getUnsentReportExports(String reportQueueTypeId) throws LIMSRuntimeException {
		String sql = "from ReportExternalExport rq where rq.send = true and rq.typeId = :" + TYPE_PARAM;

		return handleListResultWithTypeId(sql, reportQueueTypeId);

	}

	@Override
	public ReportExternalExport getLatestSentReportExport(String reportQueueTypeId) throws LIMSRuntimeException {
		String sql = "from ReportExternalExport rq where rq.send = false and rq.typeId = :" + TYPE_PARAM
				+ " order by rq.sentDate desc";

		return handleMaxResultWithTypeId(reportQueueTypeId, sql);
	}

	@Override
	public ReportExternalExport getLatestEventReportExport(String reportQueueTypeId) throws LIMSRuntimeException {
		String sql = "from ReportExternalExport rq where rq.typeId = :" + TYPE_PARAM + " order by rq.eventDate desc";

		return handleMaxResultWithTypeId(reportQueueTypeId, sql);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReportExternalExport> getReportsInDateRange(Timestamp lower, Timestamp upper, String reportQueueTypeId)
			throws LIMSRuntimeException {
		String sql = "from ReportExternalExport rq where rq.sentDate >= :lower and rq.sentDate <= :upper";

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
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
//			String id = (String) sessionFactory.getCurrentSession().save(report);
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
//			sessionFactory.getCurrentSession().merge(report);
//			// sessionFactory.getCurrentSession().flush(); // CSL remove old
//			// sessionFactory.getCurrentSession().clear(); // CSL remove old
//			// sessionFactory.getCurrentSession().evict // CSL remove old(report);
//			// sessionFactory.getCurrentSession().refresh // CSL remove old(report);
//		} catch (Exception e) {
//			handleException(e, "updateReportExternalExport");
//		}
//	}

	@Override
	public ReportExternalExport readReportExternalExport(String idString) throws LIMSRuntimeException {

		try {
			ReportExternalExport data = sessionFactory.getCurrentSession().get(ReportExternalExport.class, idString);
			// closeSession(); // CSL remove old
			return data;
		} catch (HibernateException e) {
			handleException(e, "readReportExternalExport");
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private List<ReportExternalExport> handleListResultWithTypeId(String sql, String typeId) {
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			query.setInteger(TYPE_PARAM, Integer.parseInt(typeId));
			List<ReportExternalExport> reports = query.list();

			// closeSession(); // CSL remove old

			return reports;
		} catch (HibernateException e) {
			handleException(e, "getRecalculateReportExternalExports");
		}

		return null;
	}

	private ReportExternalExport handleMaxResultWithTypeId(String typeId, String sql) {
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			query.setInteger(TYPE_PARAM, Integer.parseInt(typeId));
			ReportExternalExport report = (ReportExternalExport) query.setMaxResults(1).uniqueResult();

			// closeSession(); // CSL remove old

			return report;
		} catch (HibernateException e) {
			handleException(e, "getLatestSentReportExternalExport");
		}

		return null;
	}

	@Override
	public Timestamp getLastSentTimestamp() throws LIMSRuntimeException {
		String sql = "From ReportExternalExport ree where ree.sentDate IS NOT NULL order by ree.sentDate DESC";

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
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
	public Timestamp getLastCollectedTimestamp() throws LIMSRuntimeException {
		String sql = "From ReportExternalExport ree order by ree.collectionDate DESC";

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
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
	public ReportExternalExport getReportByEventDateAndType(ReportExternalExport report) throws LIMSRuntimeException {
		String sql = "From ReportExternalExport ree where ree.eventDate >= :eventDate and ree.eventDate < :nextDay and ree.typeId = :typeId";

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
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
//			sessionFactory.getCurrentSession().delete(readReportExternalExport(report.getId()));
//			// closeSession(); // CSL remove old
//		} catch (Exception e) {
//			handleException(e, "delete");
//		}
//	}
}
