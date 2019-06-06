package us.mn.state.health.lims.reports.action.implementation;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import spring.mine.common.form.BaseForm;
import spring.mine.internationalization.MessageUtil;
import spring.service.observationhistory.ObservationHistoryService;
import spring.service.samplehuman.SampleHumanService;
import spring.util.SpringContext;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory;
import us.mn.state.health.lims.observationhistorytype.ObservationHistoryTypeMap;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.sample.valueholder.Sample;

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
