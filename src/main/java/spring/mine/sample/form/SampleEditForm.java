package spring.mine.sample.form;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.ValidationHelper;
import spring.mine.validation.annotations.ValidAccessionNumber;
import spring.mine.validation.annotations.ValidDate;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.common.util.validator.CustomDateValidator.DateRelation;
import us.mn.state.health.lims.patient.action.bean.PatientSearch;
import us.mn.state.health.lims.sample.bean.SampleEditItem;
import us.mn.state.health.lims.sample.bean.SampleOrderItem;
import us.mn.state.health.lims.sample.util.AccessionNumberUtil;

public class SampleEditForm extends BaseForm {
	@NotNull
	private Boolean noSampleFound = Boolean.FALSE;

	@NotNull
	private Boolean isConfirmationSample = Boolean.FALSE;

	@NotNull
	private Boolean isEditable = Boolean.TRUE;

	@Pattern(regexp = ValidationHelper.NAME_REGEX)
	private String patientName = "";

	@NotBlank
	@ValidDate(relative = DateRelation.PAST)
	private String dob = "";

	@NotBlank
	@Pattern(regexp = ValidationHelper.GENDER_REGEX)
	private String gender = "";

	@Pattern(regexp = ValidationHelper.PATIENT_ID_REGEX)
	private String nationalId = "";

	@ValidAccessionNumber
	private String accessionNumber;

	@ValidAccessionNumber
	private String newAccessionNumber = "";

	private Timestamp lastupdated;

	// TODO
	@Valid
	private List<SampleEditItem> existingTests;

	// TODO
	@Valid
	private List<SampleEditItem> possibleTests;

	@NotNull
	private Boolean searchFinished = false;

	// for display
	private List<IdValuePair> sampleTypes;

	// in validator
	private String maxAccessionNumber = "";

	// in validator
	private String sampleXML = "";

	// for display
	private List<IdValuePair> initialSampleConditionList;

	@ValidDate(relative = DateRelation.TODAY)
	private String currentDate = "";

	// for display
	private List<IdValuePair> testSectionList;

	// TODO
	@Valid
	private SampleOrderItem sampleOrderItems;

	// for display
	private PatientSearch patientSearch;

	@NotNull
	private Boolean ableToCancelResults = false;

	@NotNull
	private Boolean warning = false;

	// in validator
	private String idSeparator = SystemConfiguration.getInstance().getDefaultIdSeparator();

	// in validator
	private String accessionFormat = ConfigurationProperties.getInstance().getPropertyValue(Property.AccessionFormat);

	// in validator
	private int editableAccession = AccessionNumberUtil.getChangeableLength();

	// in validator
	private int nonEditableAccession = AccessionNumberUtil.getInvarientLength();

	// in validator
	private int maxAccessionLength = editableAccession + nonEditableAccession;

	public SampleEditForm() {
		setFormName("SampleEditForm");
	}

	public Boolean getNoSampleFound() {
		return noSampleFound;
	}

	public void setNoSampleFound(Boolean noSampleFound) {
		this.noSampleFound = noSampleFound;
	}

	public Boolean getIsConfirmationSample() {
		return isConfirmationSample;
	}

	public void setIsConfirmationSample(Boolean isConfirmationSample) {
		this.isConfirmationSample = isConfirmationSample;
	}

	public Boolean getIsEditable() {
		return isEditable;
	}

	public void setIsEditable(Boolean isEditable) {
		this.isEditable = isEditable;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNationalId() {
		return nationalId;
	}

	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}

	public String getAccessionNumber() {
		return accessionNumber;
	}

	public void setAccessionNumber(String accessionNumber) {
		this.accessionNumber = accessionNumber;
	}

	public String getNewAccessionNumber() {
		return newAccessionNumber;
	}

	public void setNewAccessionNumber(String newAccessionNumber) {
		this.newAccessionNumber = newAccessionNumber;
	}

	public Timestamp getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(Timestamp lastupdated) {
		this.lastupdated = lastupdated;
	}

	public List<SampleEditItem> getExistingTests() {
		return existingTests;
	}

	public void setExistingTests(List<SampleEditItem> existingTests) {
		this.existingTests = existingTests;
	}

	public List<SampleEditItem> getPossibleTests() {
		return possibleTests;
	}

	public void setPossibleTests(List<SampleEditItem> possibleTests) {
		this.possibleTests = possibleTests;
	}

	public Boolean getSearchFinished() {
		return searchFinished;
	}

	public void setSearchFinished(Boolean searchFinished) {
		this.searchFinished = searchFinished;
	}

	public List<IdValuePair> getSampleTypes() {
		return sampleTypes;
	}

	public void setSampleTypes(List<IdValuePair> sampleTypes) {
		this.sampleTypes = sampleTypes;
	}

	public String getMaxAccessionNumber() {
		return maxAccessionNumber;
	}

	public void setMaxAccessionNumber(String maxAccessionNumber) {
		this.maxAccessionNumber = maxAccessionNumber;
	}

	public String getSampleXML() {
		return sampleXML;
	}

	public void setSampleXML(String sampleXML) {
		this.sampleXML = sampleXML;
	}

	public List<IdValuePair> getInitialSampleConditionList() {
		return initialSampleConditionList;
	}

	public void setInitialSampleConditionList(List<IdValuePair> initialSampleConditionList) {
		this.initialSampleConditionList = initialSampleConditionList;
	}

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public List<IdValuePair> getTestSectionList() {
		return testSectionList;
	}

	public void setTestSectionList(List<IdValuePair> testSectionList) {
		this.testSectionList = testSectionList;
	}

	public SampleOrderItem getSampleOrderItems() {
		return sampleOrderItems;
	}

	public void setSampleOrderItems(SampleOrderItem sampleOrderItems) {
		this.sampleOrderItems = sampleOrderItems;
	}

	public PatientSearch getPatientSearch() {
		return patientSearch;
	}

	public void setPatientSearch(PatientSearch patientSearch) {
		this.patientSearch = patientSearch;
	}

	public Boolean getAbleToCancelResults() {
		return ableToCancelResults;
	}

	public void setAbleToCancelResults(Boolean ableToCancelResults) {
		this.ableToCancelResults = ableToCancelResults;
	}

	public Boolean getWarning() {
		return warning;
	}

	public void setWarning(Boolean warning) {
		this.warning = warning;
	}

	public String getIdSeparator() {
		return idSeparator;
	}

	public void setIdSeparator(String idSeparator) {
		this.idSeparator = idSeparator;
	}

	public String getAccessionFormat() {
		return accessionFormat;
	}

	public void setAccessionFormat(String accessionFormat) {
		this.accessionFormat = accessionFormat;
	}

	public int getEditableAccession() {
		return editableAccession;
	}

	public void setEditableAccession(int editableAccession) {
		this.editableAccession = editableAccession;
	}

	public int getNonEditableAccession() {
		return nonEditableAccession;
	}

	public void setNonEditableAccession(int nonEditableAccession) {
		this.nonEditableAccession = nonEditableAccession;
	}

	public int getMaxAccessionLength() {
		return maxAccessionLength;
	}

	public void setMaxAccessionLength(int maxAccessionLength) {
		this.maxAccessionLength = maxAccessionLength;
	}
}
