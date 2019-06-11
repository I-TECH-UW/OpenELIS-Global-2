package spring.service.analyte;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.analyte.valueholder.Analyte;

public interface AnalyteService extends BaseObjectService<Analyte, String> {

	Analyte getAnalyteByName(Analyte analyte, boolean ignoreCase);

}
