package spring.service.barcode;

import javax.validation.Valid;

import spring.mine.barcode.form.BarcodeConfigurationForm;

public interface BarcodeInformationService {

	void updateBarcodeInfoFromForm(@Valid BarcodeConfigurationForm form, String sysUserId);

}
