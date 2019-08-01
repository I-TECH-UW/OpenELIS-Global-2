package org.openelisglobal.analyte.service;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.analyte.valueholder.Analyte;

public interface AnalyteService extends BaseObjectService<Analyte, String> {

    Analyte getAnalyteByName(Analyte analyte, boolean ignoreCase);

}
