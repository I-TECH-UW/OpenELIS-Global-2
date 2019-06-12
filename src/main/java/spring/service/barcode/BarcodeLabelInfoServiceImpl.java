package spring.service.barcode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.barcode.dao.BarcodeLabelInfoDAO;
import us.mn.state.health.lims.barcode.valueholder.BarcodeLabelInfo;

@Service
public class BarcodeLabelInfoServiceImpl extends BaseObjectServiceImpl<BarcodeLabelInfo, String>
		implements BarcodeLabelInfoService {
	@Autowired
	protected BarcodeLabelInfoDAO baseObjectDAO;

	BarcodeLabelInfoServiceImpl() {
		super(BarcodeLabelInfo.class);
	}

	@Override
	protected BarcodeLabelInfoDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public BarcodeLabelInfo getDataByCode(String code) {
		return getMatch("code", code).orElse(null);
	}
}
