package org.openelisglobal.dataexchange.service.aggregatereporting;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.dataexchange.aggregatereporting.valueholder.ReportQueueType;

public interface ReportQueueTypeService extends BaseObjectService<ReportQueueType, String> {
    ReportQueueType getReportQueueTypeByName(String name);
}
