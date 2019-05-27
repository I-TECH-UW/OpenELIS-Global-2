package spring.service.samplepdf;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.samplepdf.valueholder.SamplePdf;

public interface SamplePdfService extends BaseObjectService<SamplePdf> {
	boolean isAccessionNumberFound(int accessionNumber);

	SamplePdf getSamplePdfByAccessionNumber(SamplePdf samplePdf);
}
