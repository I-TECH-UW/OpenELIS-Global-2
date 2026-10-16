package org.openelisglobal.reportqueue.dao;

import java.util.List;
import java.util.Optional;

/**
 * The server-side queue query: which patient orders have a released result,
 * what their report state and print status are, filtered, sorted and paged in
 * the database so the browser never holds the whole list.
 */
public interface ReportQueueQueryDAO {

    /** The page of rows the criteria select. */
    List<ReportQueueRowRecord> list(ReportQueueCriteria criteria);

    /** How many rows the criteria select in all. */
    long count(ReportQueueCriteria criteria);

    /**
     * Unprinted rows, and amended unprinted rows, ignoring the state and print
     * status filters.
     */
    long[] tiles(ReportQueueCriteria criteria);

    /** The row of one order whatever the filters, if it has a released result. */
    Optional<ReportQueueRowRecord> findRow(Long sampleId, ReportQueueCriteria criteria);

    /**
     * Whether the order's last printed version still matches what is released:
     * false when nothing was printed, when results were released since, or when a
     * printed result changed.
     */
    boolean isPrinted(Long sampleId, String finalizedStatusId, List<String> excludedStatusIds);
}
