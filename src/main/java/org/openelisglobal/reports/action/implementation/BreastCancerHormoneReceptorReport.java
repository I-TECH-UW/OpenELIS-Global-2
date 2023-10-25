package org.openelisglobal.reports.action.implementation;

import org.openelisglobal.program.service.ImmunohistochemistrySampleService;
import org.openelisglobal.program.valueholder.immunohistochemistry.ImmunohistochemistrySample;
import org.openelisglobal.reports.form.ReportForm;
import org.openelisglobal.spring.util.SpringContext;


public class BreastCancerHormoneReceptorReport extends PatientProgramReport {
    
    protected ImmunohistochemistrySampleService immunohistochemistrySampleService = SpringContext
            .getBean(ImmunohistochemistrySampleService.class);
    
    private ImmunohistochemistrySample immunohistochemistrySample;
    
    @Override
    protected String getReportName() {
        return "BreastCancerHormoneReceptorReport";
    }
    
    @Override
    protected void setAdditionalReportItems() {
        data.setErResult("0.1");
        data.setPrResult("0.2");
        data.setMibResult("33");
    }
    
    @Override
    protected void createExtraReportParameters() {
        reportParameters.put("erPercent", form.getErPercent());
        reportParameters.put("erIntensity", form.getErIntensity());
        reportParameters.put("erScore", form.getErScore());
        reportParameters.put("prPercent", form.getPrPercent());
        reportParameters.put("prIntensity", form.getPrIntensity());
        reportParameters.put("prScore", form.getPrScore());
        reportParameters.put("mib", form.getMib());
        reportParameters.put("pattern", form.getPattern());
        reportParameters.put("herAssesment", form.getHerAssesment());
        reportParameters.put("herScore", form.getHerScore());
        reportParameters.put("diagnosis", form.getDiagnosis());
        reportParameters.put("molecularSubType", form.getMolecularSubType());
    }
    
    @Override
    protected void innitializeSample(ReportForm form) {
        immunohistochemistrySample = immunohistochemistrySampleService.get(Integer.valueOf(form.getProgramSampleId()));
        sample = immunohistochemistrySample.getSample();
    }
    
}
