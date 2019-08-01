package org.openelisglobal.reports.action.implementation;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import org.openelisglobal.common.form.BaseForm;
import org.openelisglobal.internationalization.MessageUtil;
import org.openelisglobal.observationhistory.service.ObservationHistoryService;
import org.openelisglobal.samplehuman.service.SampleHumanService;
import org.openelisglobal.spring.util.SpringContext;
import org.openelisglobal.observationhistory.valueholder.ObservationHistory;
import org.openelisglobal.observationhistorytype.ObservationHistoryTypeMap;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.sample.valueholder.Sample;

public class RetroCIPatientCollectionReport extends CollectionReport implements IReportParameterSetter {

    private ObservationHistoryService ohService = SpringContext.getBean(ObservationHistoryService.class);
    private SampleHumanService sampleHumanService = SpringContext.getBean(SampleHumanService.class);

    @Override
    public void setRequestParameters(BaseForm form) {
        try {
            PropertyUtils.setProperty(form, "reportName", MessageUtil.getMessage("patient.report.collection.name"));
            PropertyUtils.setProperty(form, "usePatientNumberDirect", Boolean.TRUE);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected List<byte[]> generateReports() {
        List<byte[]> byteList = new ArrayList<>();

        Patient patient = getPatient();

        if (patient != null) {
            String formNameId = ObservationHistoryTypeMap.getInstance().getIDForType("projectFormName");
            List<Sample> samples = sampleHumanService.getSamplesForPatient(patient.getId());

            for (Sample sample : samples) {
                List<ObservationHistory> projects = ohService.getAll(patient, sample, formNameId);

                if (!projects.isEmpty()) {
                    try {
                        PropertyUtils.setProperty(form, "accessionDirect", sample.getAccessionNumber());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }

                    if ("InitialARV_Id".equals(projects.get(0).getValue())) {
                        byteList.add(createReport("patientARVInitial1"));
                        byteList.add(createReport("patientARVInitial2"));
                    } else if ("FollowUpARV_Id".equals(projects.get(0).getValue())) {
                        byteList.add(createReport("patientARVFollowup1"));
                        byteList.add(createReport("patientARVFollowup2"));
                    }
                }
            }
        }
        return byteList;
    }

}
