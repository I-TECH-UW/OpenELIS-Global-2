package spring.mine.workplan.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import spring.mine.common.controller.BaseController;
import spring.mine.internationalization.MessageUtil;
import spring.service.test.TestService;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.formfields.FormFields;
import us.mn.state.health.lims.common.formfields.FormFields.Field;
import us.mn.state.health.lims.common.services.IPatientService;
import us.mn.state.health.lims.common.services.ObservationHistoryService;
import us.mn.state.health.lims.common.services.ObservationHistoryService.ObservationType;
import us.mn.state.health.lims.common.services.PatientService;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.test.valueholder.Test;

public abstract class BaseWorkplanController extends BaseController {

	public enum WorkplanType {
		UNKNOWN, TEST, PANEL, IMMUNOLOGY, HEMATO_IMMUNOLOGY, HEMATOLOGY, BIOCHEMISTRY, SEROLOGY, VIROLOGY, CHEM,
		BACTERIOLOGY, PARASITOLOGY, IMMUNO, ECBU, HIV, MYCROBACTERIOLOGY, MOLECULAR_BIOLOGY, LIQUID_BIOLOGY,
		ENDOCRINOLOGY, CYTOBACTERIOLOGY, MYCOLOGY, SEROLOGY_IMMUNOLOGY, MALARIA
	}

	@Autowired
	protected TestService testService;

	protected static List<Integer> statusList;
	protected static boolean useReceptionTime = FormFields.getInstance().useField(Field.SampleEntryUseReceptionHour);
	protected static List<String> nfsTestIdList;

	@PostConstruct
	public void initialize() {
		if (statusList == null) {
			statusList = new ArrayList<>();
			statusList.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.NotStarted)));
			statusList.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.BiologistRejected)));
			statusList.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.TechnicalRejected)));
			statusList.add(
					Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.NonConforming_depricated)));
		}

		if (nfsTestIdList == null) {
			nfsTestIdList = new ArrayList<>();
			nfsTestIdList.add(getTestId("GB"));
			nfsTestIdList.add(getTestId("Neut %"));
			nfsTestIdList.add(getTestId("Lymph %"));
			nfsTestIdList.add(getTestId("Mono %"));
			nfsTestIdList.add(getTestId("Eo %"));
			nfsTestIdList.add(getTestId("Baso %"));
			nfsTestIdList.add(getTestId("GR"));
			nfsTestIdList.add(getTestId("Hb"));
			nfsTestIdList.add(getTestId("HCT"));
			nfsTestIdList.add(getTestId("VGM"));
			nfsTestIdList.add(getTestId("TCMH"));
			nfsTestIdList.add(getTestId("CCMH"));
			nfsTestIdList.add(getTestId("PLQ"));
		}

	}

	@Override
	protected String getMessageForKey(String messageKey) throws Exception {
		return MessageUtil.getMessage("workplan.page.title", messageKey);
	}

	protected boolean allNFSTestsRequested(List<String> testIdList) {
		return (testIdList.containsAll(nfsTestIdList));

	}

	protected String getTestId(String testName) {
		Test test = testService.getMatch("testName", testName).orElse(new Test());
		return test.getId();

	}

	protected String getSubjectNumber(Analysis analysis) {
		if (ConfigurationProperties.getInstance().isPropertyValueEqual(Property.SUBJECT_ON_WORKPLAN, "true")) {
			IPatientService patientService = new PatientService(analysis.getSampleItem().getSample());
			return patientService.getSubjectNumber();
		} else {
			return "";
		}
	}

	protected String getPatientName(Analysis analysis) {
		if (ConfigurationProperties.getInstance().isPropertyValueEqual(Property.configurationName, "Haiti LNSP")) {
			Sample sample = analysis.getSampleItem().getSample();
			IPatientService patientService = new PatientService(sample);
			List<String> values = new ArrayList<>();
			values.add(patientService.getLastName() == null ? "" : patientService.getLastName().toUpperCase());
			values.add(patientService.getNationalId());

			String referringPatientId = ObservationHistoryService
					.getValueForSample(ObservationType.REFERRERS_PATIENT_ID, sample.getId());
			values.add(referringPatientId == null ? "" : referringPatientId);
			return StringUtil.buildDelimitedStringFromList(values, " / ", true);

		} else {
			return "";
		}
	}

	protected String getReceivedDateDisplay(Sample sample) {
		String receptionTime = useReceptionTime ? " " + sample.getReceivedTimeForDisplay() : "";
		return sample.getReceivedDateForDisplay() + receptionTime;
	}

	static class TypeNameGroup {
		private String name;
		private String key;
		private WorkplanType workplanType;

		TypeNameGroup(String name, String key, WorkplanType workplanType) {
			this.name = name;
			this.key = key;
			this.workplanType = workplanType;
		}

		public String getName() {
			return name;
		}

		public String getKey() {
			return key;
		}

		public WorkplanType getWorkplanType() {
			return workplanType;
		}

	}

}
