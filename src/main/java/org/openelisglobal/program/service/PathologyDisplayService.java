package org.openelisglobal.program.service;

import org.openelisglobal.program.valueholder.pathology.PathologyCaseViewDisplayItem;
import org.openelisglobal.program.valueholder.pathology.PathologyDisplayItem;

public interface PathologyDisplayService {

    PathologyCaseViewDisplayItem convertToCaseDisplayItem(Integer pathologySampleId);

    PathologyDisplayItem convertToDisplayItem(Integer pathologySampleId);
}
