package org.openelisglobal.qaevent.worker;

import org.openelisglobal.qaevent.form.NonConformingEventForm;
import org.openelisglobal.qaevent.valueholder.NcEvent;

import java.util.List;

public interface NonConformingEventWorker {

    NcEvent create(String labOrderId, List<String> specimens, String systemUserId, String nceNumber);

    boolean update(NonConformingEventForm form);

    boolean updateFollowUp(NonConformingEventForm form);
}
