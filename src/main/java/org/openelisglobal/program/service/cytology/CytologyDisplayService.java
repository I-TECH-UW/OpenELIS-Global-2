package org.openelisglobal.program.service.cytology;

import org.openelisglobal.program.valueholder.cytology.CytologyCaseViewDisplayItem;
import org.openelisglobal.program.valueholder.cytology.CytologyDisplayItem;

public interface CytologyDisplayService {

    CytologyCaseViewDisplayItem convertToCaseDisplayItem(Integer cytologySampleId);

    CytologyDisplayItem convertToDisplayItem(Integer cytologySampleId); 
}
