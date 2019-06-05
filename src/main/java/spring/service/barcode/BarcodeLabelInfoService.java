package spring.service.barcode;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.barcode.valueholder.BarcodeLabelInfo;

public interface BarcodeLabelInfoService extends BaseObjectService<BarcodeLabelInfo, String> {
	void updateData(BarcodeLabelInfo barcodeLabelInfo);

	boolean insertData(BarcodeLabelInfo barcodeLabelInfo);

	BarcodeLabelInfo getDataByCode(String code);
}
