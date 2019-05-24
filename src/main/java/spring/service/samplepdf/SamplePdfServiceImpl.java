package spring.service.samplepdf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.samplepdf.dao.SamplePdfDAO;
import us.mn.state.health.lims.samplepdf.valueholder.SamplePdf;

@Service
public class SamplePdfServiceImpl extends BaseObjectServiceImpl<SamplePdf> implements SamplePdfService {
	@Autowired
	protected SamplePdfDAO baseObjectDAO;

	SamplePdfServiceImpl() {
		super(SamplePdf.class);
	}

	@Override
	protected SamplePdfDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public boolean isAccessionNumberFound(int accessionNumber) {
        return getBaseObjectDAO().isAccessionNumberFound(accessionNumber);
	}

	@Override
	public SamplePdf getSamplePdfByAccessionNumber(SamplePdf samplePdf) {
        return getBaseObjectDAO().getSamplePdfByAccessionNumber(samplePdf);
	}
}
