package org.openelisglobal.samplepdf.service;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.samplepdf.valueholder.SamplePdf;

public interface SamplePdfService extends BaseObjectService<SamplePdf, String> {
  boolean isAccessionNumberFound(int accessionNumber);

  SamplePdf getSamplePdfByAccessionNumber(SamplePdf samplePdf);
}
