package org.openelisglobal.qaevent.daoimpl;

import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.qaevent.dao.LabComponentDAO;
import org.openelisglobal.qaevent.valueholder.LabComponent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class LabComponentDAOImpl extends BaseDAOImpl<LabComponent, String> implements LabComponentDAO {

    public LabComponentDAOImpl() {
        super(LabComponent.class);
    }
}
