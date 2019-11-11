package org.openelisglobal.qaevent.service;

import java.util.List;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.qaevent.valueholder.QaEvent;

public interface QaEventService extends BaseObjectService<QaEvent, String> {
    void getData(QaEvent qaEvent);

    QaEvent getQaEventByName(QaEvent qaEvent);

    List getQaEvents(String filter);

    List getAllQaEvents();

    Integer getTotalQaEventCount();

    List getPageOfQaEvents(int startingRecNo);




}
