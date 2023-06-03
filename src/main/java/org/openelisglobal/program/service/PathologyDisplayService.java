package org.openelisglobal.program.service;

import org.openelisglobal.program.valueholder.pathology.PathologyCaseViewDisplayItem;
import org.openelisglobal.program.valueholder.pathology.PathologyDisplayItem;
import org.openelisglobal.program.valueholder.pathology.PathologySample;

public interface PathologyDisplayService {

    PathologyDisplayItem convertToDisplayItem(PathologySample pathologySample);

    PathologyCaseViewDisplayItem convertToCaseDisplayItem(PathologySample pathologySample);
}
