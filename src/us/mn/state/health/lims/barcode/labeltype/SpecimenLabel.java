package us.mn.state.health.lims.barcode.labeltype;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import us.mn.state.health.lims.analysis.dao.AnalysisDAO;
import us.mn.state.health.lims.analysis.daoimpl.AnalysisDAOImpl;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.barcode.LabelField;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.services.PatientService;
import us.mn.state.health.lims.common.services.SampleOrderService;
import us.mn.state.health.lims.common.services.TestService;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.person.valueholder.Person;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;

/**
 * Stores values and formatting for Specimen Labels
 * 
 * @author Caleb
 *
 */
public class SpecimenLabel extends Label {

  /**
   * @param patient     Who include on specimen label
   * @param sample      What sample to include on specimen label
   * @param sampleItem  What specific sample item to include on specimen label
   * @param labNo       Number to start code with
   */
  public SpecimenLabel(Patient patient, Sample sample, SampleItem sampleItem, String labNo) {
    // set dimensions
    try {
      width = Float.parseFloat(ConfigurationProperties.getInstance()
              .getPropertyValue(Property.SPECIMEN_BARCODE_WIDTH));
      height = Float.parseFloat(ConfigurationProperties.getInstance()
              .getPropertyValue(Property.SPECIMEN_BARCODE_HEIGHT));
    } catch (Exception e) {
      LogEvent.logError("SpecimenLabel", "SpecimenLabel SpecimenLabel()", e.toString());
    }
    // get information for displaying above bar code
    SampleOrderService sampleOrderService = new SampleOrderService(sample);
    Person person = patient.getPerson();
    String referringFacility = StringUtil.replaceNullWithEmptyString(
            sampleOrderService.getSampleOrderItem().getReferringSiteName());
    String patientName = StringUtil.replaceNullWithEmptyString(person.getLastName()) + ", "
            + StringUtil.replaceNullWithEmptyString(person.getFirstName());
    if (patientName.trim().equals(",")) {
      patientName = " ";
    }
    patientName = StringUtils.substring(patientName.replaceAll("( )+", " "), 0, 30);
    String dob = StringUtil.replaceNullWithEmptyString(patient.getBirthDateForDisplay());
    
    // adding fields above bar code
    aboveFields = new ArrayList<LabelField>();
    aboveFields.add(new LabelField(StringUtil.getMessageForKey("barcode.label.info.patientname"),
            patientName, 6));
    aboveFields.add(
            new LabelField(StringUtil.getMessageForKey("barcode.label.info.patientdob"), dob, 4));
    aboveFields.add(getAvailableIdField(patient));
    LabelField siteField = new LabelField(StringUtil.getMessageForKey("barcode.label.info.site"),
            StringUtils.substring(referringFacility, 0, 20), 4);
    siteField.setDisplayFieldName(true);
    aboveFields.add(siteField);

    // getting fields for below bar code
    Timestamp timestamp = sampleItem.getCollectionDate();
    String collectionDate = DateUtil.convertTimestampToStringDate(timestamp);
    String collectionTime = DateUtil.convertTimestampToStringTime(timestamp);
    AnalysisDAO analysisDAO = new AnalysisDAOImpl();
    String collector = sampleItem.getCollector();
    StringBuilder tests = new StringBuilder();
    String seperator = "";    // separator for appending tests to each other
    List<Analysis> analysisList = analysisDAO.getAnalysesBySampleItem(sampleItem);
    for (Analysis analysis : analysisList) {
      tests.append(seperator);
      tests.append(TestService.getUserLocalizedTestName(analysis.getTest()));
      seperator = ", ";
    }
    
    // adding fields below bar code
    belowFields = new ArrayList<LabelField>();
    String useDateTime = ConfigurationProperties.getInstance()
            .getPropertyValue(Property.SPECIMEN_FIELD_DATE);
    String useSex = ConfigurationProperties.getInstance()
            .getPropertyValue(Property.SPECIMEN_FIELD_SEX);
    String useTests = ConfigurationProperties.getInstance()
            .getPropertyValue(Property.SPECIMEN_FIELD_TESTS);
    if ("true".equals(useSex)) {
      LabelField sexField = new LabelField(
              StringUtil.getMessageForKey("barcode.label.info.patientsex"),
              StringUtil.replaceNullWithEmptyString(patient.getGender()), 2);
      sexField.setDisplayFieldName(true);
      belowFields.add(sexField);
    }
    if ("true".equals(useDateTime)) {
      LabelField dateField = new LabelField(
              StringUtil.getMessageForKey("barcode.label.info.collectiondate"), collectionDate, 3);
      dateField.setDisplayFieldName(true);
      belowFields.add(dateField);
      dateField = new LabelField(StringUtil.getMessageForKey("barcode.label.info.collectiontime"),
              StringUtil.replaceNullWithEmptyString(collectionTime), 2);
      belowFields.add(dateField);
    }
    LabelField collectorField = new LabelField(
            StringUtil.getMessageForKey("barcode.label.info.collectorid"),
            StringUtils.substring(StringUtil.replaceNullWithEmptyString(collector), 0, 15), 3);
    collectorField.setDisplayFieldName(true);
    belowFields.add(collectorField);
    if ("true".equals(useTests)) {
      LabelField testsField = new LabelField(
              StringUtil.getMessageForKey("barcode.label.info.tests"),
              StringUtil.replaceNullWithEmptyString(tests.toString()), 10);
      testsField.setStartNewline(true);
      belowFields.add(testsField);
    }

    // add code
    String sampleCode = sampleItem.getSortOrder();
    setCode(labNo + "." + sampleCode);
  }

  /**
   * Get first available id to identify a patient (Subject Number > National Id)
   * @param patient   Who to find identification for
   * @return          label field containing patient id
   */
  private LabelField getAvailableIdField(Patient patient) {
    PatientService service = new PatientService(patient);
    String patientId = service.getSubjectNumber();
    if (!StringUtil.isNullorNill(patientId))
      return new LabelField(StringUtil.getMessageForKey("barcode.label.info.patientid"),
              StringUtils.substring(patientId, 0, 25), 6);
    patientId = service.getNationalId();
    if (!StringUtil.isNullorNill(patientId))
      return new LabelField(StringUtil.getMessageForKey("barcode.label.info.patientid"),
              StringUtils.substring(patientId, 0, 25), 6);
    return new LabelField(StringUtil.getMessageForKey("barcode.label.info.patientid"), "", 6);
  }

  /* (non-Javadoc)
   * @see us.mn.state.health.lims.barcode.labeltype.Label#getNumTextRowsBefore()
   */
  @Override
  public int getNumTextRowsBefore() {
    Iterable<LabelField> fields = getAboveFields();
    return getNumRows(fields);
  }

  /* (non-Javadoc)
   * @see us.mn.state.health.lims.barcode.labeltype.Label#getNumTextRowsAfter()
   */
  @Override
  public int getNumTextRowsAfter() {
    Iterable<LabelField> fields = getBelowFields();
    return getNumRows(fields);
  }

  /* (non-Javadoc)
   * @see us.mn.state.health.lims.barcode.labeltype.Label#getMaxNumLabels()
   */
  @Override
  public int getMaxNumLabels() {
    int max = 0;
    try {
      max = Integer.parseInt(ConfigurationProperties.getInstance()
              .getPropertyValue(Property.MAX_SPECIMEN_PRINTED));
    } catch (Exception e) {
      LogEvent.logError("SpecimenLabel", "SpecimenLabel getMaxNumLabels()", e.toString());
    }
    
    return max;
  }

}

