package org.openelisglobal.reports.action.implementation;
import java.util.ArrayList;
import java.util.List;

import org.openelisglobal.program.service.cytology.CytologySampleService;
import org.openelisglobal.program.valueholder.cytology.CytologySample;
import org.openelisglobal.reports.form.ReportForm;
import org.openelisglobal.spring.util.SpringContext;

public class PatientCytologyReport extends PatientProgramReport {

    protected  CytologySampleService cytologySampleService = SpringContext.getBean(CytologySampleService.class);
    private CytologySample cytologySample;

    @Override
    protected String getReportName() {
        return "PatientCytologyReport";
    }

    @Override
    protected void setAdditionalReportItems() {
        data.setSpecimenAdequacy("Dummy specimenn");
        data.setDiagnosis("NEGATIVE DIAGNOSIS");

        List<String> stringList = new ArrayList<>();
        stringList.add("dummy diagnosis");
        stringList.add("sample diagnosis");
        stringList.add("example diagnosis");

        data.setEpithelialCellAbnomalities(stringList);
        data.setReactiveCellularChanges(stringList);
        data.setOrganisms(stringList);
        data.setOtherDiagnoses(stringList);
    }

    @Override
    protected void innitializeSample(ReportForm form) {
        cytologySample = cytologySampleService.get(Integer.valueOf(form.getProgramSampleId()));
        sample = cytologySample.getSample();
    }
    
}
