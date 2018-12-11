package us.mn.state.health.lims.barcode.labeltype;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

import us.mn.state.health.lims.barcode.LabelField;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.services.PatientService;
import us.mn.state.health.lims.common.services.SampleOrderService;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.person.valueholder.Person;
import us.mn.state.health.lims.sample.valueholder.Sample;

/**
 * Stores values and formatting for Order Labels
 * 
 * @author Caleb
 *
 */
public class OrderLabel extends Label {

  /**
   * @param patient   Who to include on order label
   * @param sample    What to include on order label
   * @param labNo     Code to include in bar code
   */
  public OrderLabel(Patient patient, Sample sample, String labNo) {
    // set dimensions
    try {
      width = Float.parseFloat(
              ConfigurationProperties.getInstance().getPropertyValue(Property.ORDER_BARCODE_WIDTH));
      height = Float.parseFloat(ConfigurationProperties.getInstance()
              .getPropertyValue(Property.ORDER_BARCODE_HEIGHT));
    } catch (Exception e) {
      LogEvent.logError("OrderLabel", "OrderLabel OrderLabel()", e.toString());
    }
    // get information to display above bar code
    Person person = patient.getPerson();
    SampleOrderService sampleOrderService = new SampleOrderService(sample);
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
    
    // adding bar code
    setCode(labNo);
  }

  /**
   * Get first available id to identify a patient (Subject Number > National Id)
   * @param patient   Who to find identification for
   * @return          label field containing patient id
   */
  private LabelField getAvailableIdField(Patient patient) {
    PatientService service = new PatientService(patient);
    String patientId = service.getSubjectNumber();
    if (!StringUtil.isNullorNill(patientId)) {
      return new LabelField(StringUtil.getMessageForKey("barcode.label.info.patientid"),
              StringUtils.substring(patientId, 0, 25), 6);
    }
    patientId = service.getNationalId();
    if (!StringUtil.isNullorNill(patientId)) {
      return new LabelField(StringUtil.getMessageForKey("barcode.label.info.patientid"),
              StringUtils.substring(patientId, 0, 25), 6);
    }
    return new LabelField(StringUtil.getMessageForKey("barcode.label.info.patientid"), "", 6);
  }
  
  /* (non-Javadoc)
   * @see us.mn.state.health.lims.barcode.labeltype.Label#getNumTextRowsBefore()
   */
  @Override
  public int getNumTextRowsBefore() {
    int numRows = 0;
    int curColumns = 0;
    boolean completeRow = true;
    Iterable<LabelField> fields = getAboveFields();
    for (LabelField field : fields) {
      // add to num row if start on newline
      if (field.isStartNewline() && !completeRow) {
        ++numRows;
        curColumns = 0;
      }
      curColumns += field.getColspan();
      if (curColumns > 10) {
        // TO DO: (caleb) throw error
      // row is completed, add to num row
      } else if (curColumns == 10) {
        completeRow = true;
        curColumns = 0;
        ++numRows;
      } else {
        completeRow = false;
      }
    }
    // add to num row if last row was incomplete
    if (!completeRow) {
      ++numRows;
    }

    return numRows;
  }

  /* (non-Javadoc)
   * @see us.mn.state.health.lims.barcode.labeltype.Label#getNumTextRowsAfter()
   */
  @Override
  public int getNumTextRowsAfter() {
    return 0;
  }

  /* (non-Javadoc)
   * @see us.mn.state.health.lims.barcode.labeltype.Label#getMaxNumLabels()
   */
  @Override
  public int getMaxNumLabels() {
    int max = 0;
    try {
      max = Integer.parseInt(
              ConfigurationProperties.getInstance().getPropertyValue(Property.MAX_ORDER_PRINTED));
    } catch (Exception e) {
      LogEvent.logError("OrderLabel", "OrderLabel getMaxNumLabels()", e.toString());
    }
    return max;
  }

}

