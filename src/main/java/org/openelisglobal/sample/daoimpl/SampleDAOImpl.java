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
package org.openelisglobal.sample.daoimpl;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.util.DateUtil;
import org.openelisglobal.common.util.SystemConfiguration;
import org.openelisglobal.sample.dao.SampleDAO;
import org.openelisglobal.sample.valueholder.Sample;
import org.openelisglobal.sampleproject.valueholder.SampleProject;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author diane benz
 */
@Component
@Transactional
public class SampleDAOImpl extends BaseDAOImpl<Sample, String> implements SampleDAO {

    public SampleDAOImpl() {
        super(Sample.class);
    }

    private static final String ACC_NUMBER_SEQ_BEGIN = "000001";

//	@Override
//	public void deleteData(List samples) throws LIMSRuntimeException {
//		// add to audit trail
//		try {
//
//			for (int i = 0; i < samples.size(); i++) {
//				Sample data = (Sample) samples.get(i);
//
//				Sample oldData = readSample(data.getId());
//				Sample newData = new Sample();
//
//				String sysUserId = data.getSysUserId();
//				String event = IActionConstants.AUDIT_TRAIL_DELETE;
//				String tableName = "SAMPLE";
//				auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("SampleDAOImpl", "AuditTrail deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in Sample AuditTrail deleteData()", e);
//		}
//
//		try {
//			for (int i = 0; i < samples.size(); i++) {
//				Sample data = (Sample) samples.get(i);
//				// bugzilla 2206
//				data = readSample(data.getId());
//				entityManager.unwrap(Session.class).delete(data);
//				// entityManager.unwrap(Session.class).flush(); // CSL remove old
//				// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			}
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("SampleDAOImpl", "deleteData()", e.toString());
//			throw new LIMSRuntimeException("Error in Sample deleteData()", e);
//		}
//	}

    // Note: the accession number is a business rule and should be externalized
//	@Override
//	public boolean insertData(Sample sample) throws LIMSRuntimeException {
//
//		try {
//			sample.setAccessionNumber(getNextAccessionNumber());
//			String id = (String) entityManager.unwrap(Session.class).save(sample);
//			sample.setId(id);
//
//			String sysUserId = sample.getSysUserId();
//			String tableName = "SAMPLE";
//			auditDAO.saveNewHistory(sample, sysUserId, tableName);
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//
//		} catch (RuntimeException e) {
//			LogEvent.logError("SampleDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in Sample insertData()", e);
//		}
//
//		return true;
//	}

    /**
     * Insert the specified Sample, which has already been assigned an accession
     * number by the user from the input view.
     *
     * @param sample The Sample to be inserted into the database.
     *
     * @return boolean True if the insert was successful.
     */
//	@Override
//	public boolean insertDataWithAccessionNumber(Sample sample) throws LIMSRuntimeException {
//		try {
//
//			String id = (String) entityManager.unwrap(Session.class).save(sample);
//			sample.setId(id);
//
//			auditDAO.saveNewHistory(sample, sample.getSysUserId(), "SAMPLE");
//
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("SampleDAOImpl", "insertData()", e.toString());
//			throw new LIMSRuntimeException("Error in Sample insertData()", e);
//		}
//		return true;
//	}

//	@Override
//	public void updateData(Sample sample) throws LIMSRuntimeException {
//
//		Sample oldData = readSample(sample.getId());
//		Sample newData = sample;
//
//		// add to audit trail
//		try {
//
//			String sysUserId = sample.getSysUserId();
//			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
//			String tableName = "SAMPLE";
//			auditDAO.saveHistory(newData, oldData, sysUserId, event, tableName);
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("SampleDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in Sample AuditTrail updateData()", e);
//		}
//
//		try {
//			entityManager.unwrap(Session.class).merge(sample);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			// entityManager.unwrap(Session.class).evict // CSL remove old(sample);
//			// entityManager.unwrap(Session.class).refresh // CSL remove old(sample);
//		} catch (RuntimeException e) {
//			// bugzilla 2154
//			LogEvent.logError("SampleDAOImpl", "updateData()", e.toString());
//			throw new LIMSRuntimeException("Error in Sample updateData()", e);
//		}
//	}

    @Override
    @Transactional(readOnly = true)
    public void getData(Sample sample) throws LIMSRuntimeException {
        try {
            Sample samp = entityManager.unwrap(Session.class).get(Sample.class, sample.getId());

            if (samp != null) {

                // set sample projects
                String sql = "from SampleProject sp where samp_id = :sampleId";
                Query query = entityManager.unwrap(Session.class).createQuery(sql);
                query.setParameter("sampleId", Integer.parseInt(samp.getId()));
                List<Sample> list = query.list();
                // entityManager.unwrap(Session.class).flush(); // CSL remove old
                // entityManager.unwrap(Session.class).clear(); // CSL remove old

                samp.setSampleProjects(list);

                PropertyUtils.copyProperties(sample, samp);
            } else {
                sample.setId(null);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Sample getData()", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Sample> getPageOfSamples(int startingRecNo) throws LIMSRuntimeException {
        List<Sample> samples;
        try {

            // calculate maxRow to be one more than the page size
            int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

            String sql = "from Sample s order by s.id";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setFirstResult(startingRecNo - 1);
            query.setMaxResults(endingRecNo - 1);

            samples = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

            Sample samp;

            // set the display dates for STARTED_DATE, COMPLETED_DATE
            for (int i = 0; i < samples.size(); i++) {
                samp = samples.get(i);
                samp.setEnteredDateForDisplay(DateUtil.convertSqlDateToStringDate(samp.getEnteredDate()));
                samp.setReceivedDateForDisplay(DateUtil.convertSqlDateToStringDate(samp.getReceivedDate()));
                samp.setCollectionDateForDisplay(DateUtil.convertTimestampToStringDate(samp.getCollectionDate()));
                samp.setTransmissionDateForDisplay(DateUtil.convertSqlDateToStringDate(samp.getTransmissionDate()));
                samp.setReleasedDateForDisplay(DateUtil.convertSqlDateToStringDate(samp.getReleasedDate()));
            }
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Sample getPageOfSamples()", e);
        }

        return samples;
    }

    @Override
    @Transactional(readOnly = true)
    public void getSampleByAccessionNumber(Sample sample) throws LIMSRuntimeException {
        try {
            String sql = "from Sample s where s.accessionNumber = :param";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("param", sample.getAccessionNumber());
            List<Sample> list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

            Sample samp = null;
            if (list.size() > 0) {
                samp = list.get(0);
            }

            if (samp != null) {
                // set sample projects
                sql = "from SampleProject sp where samp_id = :param";
                query = entityManager.unwrap(Session.class).createQuery(sql);
                query.setInteger("param", Integer.parseInt(samp.getId()));
                List<SampleProject> sp = query.list();
                // entityManager.unwrap(Session.class).flush(); // CSL remove old
                // entityManager.unwrap(Session.class).clear(); // CSL remove old
                samp.setSampleProjects(sp);

                PropertyUtils.copyProperties(sample, samp);
            } else {
                sample.setId(null);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Sample getDataByAccessionNumber()", e);
        }
    }

    public Sample readSample(String idString) {
        Sample samp = null;
        try {
            samp = entityManager.unwrap(Session.class).get(Sample.class, idString);
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Sample readSample()", e);
        }
        return samp;
    }

    @Override
    @Transactional(readOnly = true)
    public String getNextAccessionNumber() {
        String accessionNumber = null;
        String lastAccessionNumber = null;

        // get the current year
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);

        try {
            String sql = "select max(s.accessionNumber) from Sample s";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);

            List reports = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old

            if (reports != null && reports.get(0) != null) {
                if (reports.get(0) != null) {
                    lastAccessionNumber = (String) reports.get(0);
                    if (lastAccessionNumber != null) {
                        String lastAccessionNumberYear = lastAccessionNumber.substring(0, 4);
                        if (lastAccessionNumberYear.equals(String.valueOf(currentYear))) {
                            String seqNumber = lastAccessionNumber.substring(4);
                            int sequence = Integer.parseInt(seqNumber);
                            sequence++;
                            String stringSequenceShort = String.valueOf(sequence);
                            String stringSequenceLong = "";
                            if (stringSequenceShort.length() < 6) {
                                int zeroPaddingLength = 6 - stringSequenceShort.length();
                                StringBuffer zeros = new StringBuffer();
                                for (int i = 0; i < zeroPaddingLength; i++) {
                                    zeros.append("0");
                                }
                                stringSequenceLong = zeros + stringSequenceShort;
                            }
                            if (stringSequenceShort.length() == 6) {
                                stringSequenceLong = stringSequenceShort;
                            }
                            if (stringSequenceShort.length() > 6) {// we
                                // are
                                // over
                                // the
                                // limit
                                // LogEvent.logInfo(this.getClass().getName(), "method unkown", "Error in Sample
                                // getNextAccessionNumber() max sequence
                                // number reached");
                                throw new LIMSRuntimeException(
                                        "Error in Sample getNextAccessionNumber() max sequence number reached");
                            }
                            accessionNumber = currentYear + stringSequenceLong;
                        } else {
                            // start with new sequence - new year
                            accessionNumber = currentYear + ACC_NUMBER_SEQ_BEGIN;
                        }
                    } else {
                        // nothing retrieved (start from scratch)
                        accessionNumber = currentYear + ACC_NUMBER_SEQ_BEGIN;
                    }
                } else {
                    // nothing retrieved (start from scratch)
                    accessionNumber = currentYear + ACC_NUMBER_SEQ_BEGIN;
                }
            } else {
                // fixed bug - don't throw an exception - if no samples in database start from
                // scratch
                // nothing retrieved (start from scratch)
                accessionNumber = currentYear + ACC_NUMBER_SEQ_BEGIN;
            }

        } catch (RuntimeException e) {
            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Sample getNextAccessionNumber()", e);
        }
        return accessionNumber;
    }

    /**
     * Get the Sample for the specified accession number.
     *
     * @param accessionNumber The accession number of the Sample being sought.
     *
     * @return Sample The Sample for the specified accession number, or null if the
     *         accession number does not exist.
     */
    @Override

    @Transactional(readOnly = true)
    public Sample getSampleByAccessionNumber(String accessionNumber) throws LIMSRuntimeException {
        Sample sample = null;
        try {
            String sql = "from Sample s where accession_number = :param";
            Query query = entityManager.unwrap(Session.class).createQuery(sql);

            query.setParameter("param", accessionNumber);
            List<Sample> list = query.list();
            if ((list != null) && !list.isEmpty()) {
                sample = list.get(0);
            }
            // closeSession(); // CSL remove old
        } catch (RuntimeException e) {
            throw new LIMSRuntimeException("Exception occurred in getSampleForAccessionNumber", e);
        }
        return sample;
    }
    // ==============================================================

    @Override
    @Transactional(readOnly = true)
    public List<Sample> getSamplesByStatusAndDomain(List<String> statuses, String domain) throws LIMSRuntimeException {
        List<Sample> list;
        try {
            String sql = "from Sample s where status in (:param1) and domain = :param2";
            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameterList("param1", statuses);
            query.setParameter("param2", domain);
            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {

            // bugzilla 2154
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Sample getAllSampleByStatusAndDomain()", e);
        }

        return list;
    }

    @Override

    @Transactional(readOnly = true)
    public List<Sample> getSamplesWithPendingQaEvents(Sample sample, boolean filterByQaEventCategory,
            String qaEventCategoryId, boolean filterByDomain) throws LIMSRuntimeException {
        List<Sample> list;

        try {

            String sql = "";

            if (filterByDomain) {
                if (filterByQaEventCategory) {
                    sql = "from Sample s where s.id IN "
                            + "(select sqe.sample.id from SampleQaEvent sqe where sqe.completedDate is null and sqe.qaEvent.category = :param2) "
                            + " or s.id IN "
                            + "(select aqe.analysis.sampleItem.sample.id from AnalysisQaEvent aqe where aqe.completedDate is null and aqe.qaEvent.category = :param2 and "
                            +
                            // bugzilla 2300 exclude canceled tests
                            "aqe.analysis.status NOT IN (:param3) and" +
                            // make sure we only pick the max revision analyses that have qa events pending
                            "(aqe.analysis.sampleItem.id, aqe.analysis.test.id, aqe.analysis.revision) IN (select b.sampleItem.id, b.test.id, max(b.revision) from Analysis b "
                            + " group by b.sampleItem.id, b.test.id)) " + "and s.domain = :param "
                            + "order by s.accessionNumber";
                } else {
                    sql = "from Sample s where s.id IN "
                            + "(select sqe.sample.id from SampleQaEvent sqe where sqe.completedDate is null) "
                            + " or s.id IN "
                            + "(select aqe.analysis.sampleItem.sample.id from AnalysisQaEvent aqe where aqe.completedDate is null and "
                            +
                            // bugzilla 2300 exclude canceled tests
                            "aqe.analysis.status NOT IN (:param3) and " +
                            // make sure we only pick the max revision analyses that have qa events pending
                            "(aqe.analysis.sampleItem.id, aqe.analysis.test.id, aqe.analysis.revision) IN (select b.sampleItem.id, b.test.id, max(b.revision) from Analysis b "
                            + " group by b.sampleItem.id, b.test.id)) " + "and s.domain = :param "
                            + "order by s.accessionNumber";
                }
            } else {
                if (filterByQaEventCategory) {
                    sql = "from Sample s where s.id IN "
                            + "(select sqe.sample.id from SampleQaEvent sqe where sqe.completedDate is null and sqe.qaEvent.category = :param2) "
                            + " or s.id IN "
                            + "(select aqe.analysis.sampleItem.sample.id from AnalysisQaEvent aqe where aqe.completedDate is null and aqe.qaEvent.category = :param2 and "
                            +
                            // bugzilla 2300 exclude canceled tests
                            "aqe.analysis.status NOT IN (:param3) and" +
                            // make sure we only pick the max revision analyses that have qa events pending
                            "(aqe.analysis.sampleItem.id, aqe.analysis.test.id, aqe.analysis.revision) IN (select b.sampleItem.id, b.test.id, max(b.revision) from Analysis b "
                            + " group by b.sampleItem.id, b.test.id)) " + "order by s.accessionNumber";
                } else {
                    sql = "from Sample s where s.id IN "
                            + "(select sqe.sample.id from SampleQaEvent sqe where sqe.completedDate is null) "
                            + " or s.id IN "
                            + "(select aqe.analysis.sampleItem.sample.id from AnalysisQaEvent aqe where aqe.completedDate is null and "
                            +
                            // bugzilla 2300 exclude canceled tests
                            "aqe.analysis.status NOT IN (:param3) and " +
                            // make sure we only pick the max revision analyses that have qa events pending
                            "(aqe.analysis.sampleItem.id, aqe.analysis.test.id, aqe.analysis.revision) IN (select b.sampleItem.id, b.test.id, max(b.revision) from Analysis b "
                            + " group by b.sampleItem.id, b.test.id)) " + "order by s.accessionNumber";
                }
            }

            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            if (filterByDomain) {
                query.setParameter("param", sample.getDomain());
            }
            if (filterByQaEventCategory) {
                query.setParameter("param2", qaEventCategoryId);
            }

            List<String> statusesToExclude = new ArrayList<>();
            statusesToExclude.add(SystemConfiguration.getInstance().getAnalysisStatusCanceled());
            query.setParameterList("param3", statusesToExclude);
            list = query.list();
            // entityManager.unwrap(Session.class).flush(); // CSL remove old
            // entityManager.unwrap(Session.class).clear(); // CSL remove old
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Sample getSamplesWithPendingQaEvents()", e);
        }

        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Sample> getSamplesReceivedOn(String receivedDate) throws LIMSRuntimeException {
        // covers full day so handles time stamps
        return getSamplesReceivedInDateRange(receivedDate, receivedDate);
    }

    /**
     * @see org.openelisglobal.sample.dao.SampleDAO#getSamplesReceivedInDateRange(String,
     *      String) (java.lang.String, java.lang.String)
     */

    @Override
    @Transactional(readOnly = true)
    public List<Sample> getSamplesReceivedInDateRange(String receivedDateStart, String receivedDateEnd)
            throws LIMSRuntimeException {
        List<Sample> list;

        Calendar start = getCalendarForDateString(receivedDateStart);
        if (GenericValidator.isBlankOrNull(receivedDateEnd)) {
            receivedDateEnd = receivedDateStart;
        }
        Calendar end = getCalendarForDateString(receivedDateEnd);
        // worried about time stamps including time information, so might be missed
        // comparing to midnight (00:00:00.00) on the last day of range.
        end.add(Calendar.DAY_OF_YEAR, 1);
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        try {
            String sql = "from Sample as s where s.receivedTimestamp >= :start AND s.receivedTimestamp < :end";
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setDate("start", start.getTime());
            query.setDate("end", end.getTime());
            list = query.list();
        } catch (HibernateException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Sample getSamplesReceivedInDateRange()", e);
        }
        return list;
    }

    @Override

    @Transactional(readOnly = true)
    public List<Sample> getSamplesCollectedOn(String collectionDate) throws LIMSRuntimeException {
        List<Sample> list = null;

        Calendar calendar = getCalendarForDateString(collectionDate);

        try {
            String sql = "from Sample as sample where sample.collectionDate = :date";
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setDate("date", calendar.getTime());
            list = query.list();

        } catch (HibernateException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Sample getSamplesRecievedOn()", e);
        }

        return list;
    }

    private Calendar getCalendarForDateString(String recievedDate) {
        String localeName = SystemConfiguration.getInstance().getDefaultLocale().toString();
        Locale locale = new Locale(localeName);
        Calendar calendar = Calendar.getInstance(locale);

        Date date = DateUtil.convertStringDateToSqlDate(recievedDate, localeName);
        calendar.setTime(date);
        return calendar;
    }

    @Override

    @Transactional(readOnly = true)
    public List<Sample> getSamplesByProjectAndStatusIDAndAccessionRange(List<Integer> inclusiveProjectIdList,
            List<Integer> inclusiveStatusIdList, String minAccession, String maxAccession) throws LIMSRuntimeException {

        String sql = "from Sample s where s.statusId in (:statusList) and "
                + "s.accessionNumber >= :minAccess and s.accessionNumber <= :maxAccess and "
                + "s.id in (select sp.sample.id from SampleProject sp where sp.project.id in (:projectId))";
        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameterList("statusList", inclusiveStatusIdList);
            query.setParameterList("projectId", inclusiveProjectIdList);
            query.setString("minAccess", minAccession);
            query.setString("maxAccess", maxAccession);

            List<Sample> sampleList = query.list();

            // closeSession(); // CSL remove old

            return sampleList;
        } catch (HibernateException e) {
            handleException(e, "getSamplesByProjectAndStatusIDAndAccessionRange");
        }

        return null;
    }

    @Override

    @Transactional(readOnly = true)
    public List<Sample> getSamplesByProjectAndStatusIDAndAccessionRange(String projectId,
            List<Integer> inclusiveStatusIdList, String minAccession, String maxAccession) throws LIMSRuntimeException {

        String sql = "from Sample s where s.statusId in (:statusList) and "
                + "s.accessionNumber >= :minAccess and s.accessionNumber <= :maxAccess and "
                + "s.id in (select sp.sample.id from SampleProject sp where sp.project.id = :projectId)";
        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameterList("statusList", inclusiveStatusIdList);
            query.setInteger("projectId", Integer.parseInt(projectId));
            query.setString("minAccess", minAccession);
            query.setString("maxAccess", maxAccession);

            List<Sample> sampleList = query.list();

            // closeSession(); // CSL remove old

            return sampleList;
        } catch (HibernateException e) {
            handleException(e, "getSamplesByProjectAndStatusIDAndAccessionRange");
        }

        return null;
    }

    @Override

    @Transactional(readOnly = true)
    public List<Sample> getSamplesByAccessionRange(String minAccession, String maxAccession)
            throws LIMSRuntimeException {

        String sql = "from Sample s where s.accessionNumber >= :minAccess and s.accessionNumber <= :maxAccess";
        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setString("minAccess", minAccession);
            query.setString("maxAccess", maxAccession);

            List<Sample> sampleList = query.list();

            // closeSession(); // CSL remove old

            return sampleList;
        } catch (HibernateException e) {
            handleException(e, "getSamplesByAccessionRange");
        }

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public String getLargestAccessionNumber() throws LIMSRuntimeException {
        String greatestAccessionNumber = null;

        try {
            String sql = "select max(s.accessionNumber) from Sample s";
            Query query = entityManager.unwrap(Session.class).createQuery(sql);

            greatestAccessionNumber = (String) query.uniqueResult();

        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Exception occurred in SampleDAOImpl.getLargestAccessionNumber", e);
        }

        return greatestAccessionNumber;
    }

    @Override
    @Transactional(readOnly = true)
    public String getLargestAccessionNumberWithPrefix(String prefix) throws LIMSRuntimeException {
        String greatestAccessionNumber = null;

        try {
            String sql = "select max(s.accessionNumber) from Sample s where s.accessionNumber like :prefix";
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("prefix", prefix + "%");
            greatestAccessionNumber = (String) query.uniqueResult();
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException(
                    "Exception occurred in SampleNumberDAOImpl.getLargestAccessionNumberWithPrefix", e);
        }

        return greatestAccessionNumber;
    }

    @Override
    @Transactional(readOnly = true)
    public String getLargestAccessionNumberMatchingPattern(String startingWith, int accessionSize)
            throws LIMSRuntimeException {
        String greatestAccessionNumber = null;

        try {
            String sql = "select max(s.accessionNumber) from Sample s where s.accessionNumber LIKE :starts and length(s.accessionNumber) = :numberSize";
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("starts", startingWith + "%");
            query.setInteger("numberSize", accessionSize);
            greatestAccessionNumber = (String) query.uniqueResult();
        } catch (RuntimeException e) {
            handleException(e, "getLargestAccessionNumberMatchingPattern");
        }

        return greatestAccessionNumber;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Sample> getSamplesWithPendingQaEventsByService(String serviceId) throws LIMSRuntimeException {
        String sql = "Select sqa.sample From SampleQaEvent sqa where sqa.sample.id IN (select sa.sample.id from SampleOrganization sa where sa.organization.id = :serviceId) ";

        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setInteger("serviceId", Integer.parseInt(serviceId));
            List<Sample> samples = query.list();
            // closeSession(); // CSL remove old
            return samples;
        } catch (HibernateException e) {
            handleException(e, "getSamplesWithPendingQaEventsByService");
        }
        return null;
    }

    @Override

    @Transactional(readOnly = true)
    public List<Sample> getConfirmationSamplesReceivedInDateRange(Date receivedDateStart, Date receivedDateEnd)
            throws LIMSRuntimeException {
        String sql = "from Sample s where s.isConfirmation = true and s.receivedTimestamp BETWEEN :lowDate AND :highDate";
        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setDate("lowDate", receivedDateStart);
            query.setDate("highDate", receivedDateEnd);

            List<Sample> list = query.list();
            // closeSession(); // CSL remove old
            return list;
        } catch (HibernateException e) {
            handleException(e, "getResultsInDateRange");
        }

        return null;
    }

    @Transactional(readOnly = true)
    public List<Sample> getSamplesBySampleItem(Integer sampleitemId) throws LIMSRuntimeException {

        String sql = "from Sample s where s.id in (select si.sample.id from SampleItem si where si.id = :sampleitemId)";
        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("sampleitemId", sampleitemId);
            List<Sample> sampleList = query.list();

            // closeSession(); // CSL remove old

            return sampleList;
        } catch (HibernateException e) {
            handleException(e, "getSamplesBySampleItem");
        }

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Sample getSampleByReferringId(String referringId) throws LIMSRuntimeException {

        String sql = "from Sample s where s.referringId = :referringId";
        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("referringId", referringId);
            Sample sample = (Sample) query.uniqueResult();

            // closeSession(); // CSL remove old

            return sample;
        } catch (HibernateException e) {
            handleException(e, "getSampleByReferringId");
        }

        return null;
    }

    @Override
    public List<Sample> getAllMissingFhirUuid() {
        String sql = "from Sample s where s.fhirUuid is NULL";
        try {
            Query query = entityManager.unwrap(Session.class).createQuery(sql);
            List<Sample> sampleList = query.list();

            return sampleList;
        } catch (HibernateException e) {
            handleException(e, "getSamplesBySampleItem");
        }
        return new ArrayList<>();
    }
}
