package us.mn.state.health.lims.barcode.labeltype;

import java.util.ArrayList;

import us.mn.state.health.lims.barcode.LabelField;
import us.mn.state.health.lims.common.util.StringUtil;

/**
 * Stores Formatting for a Blank Order label
 * 
 * @author Caleb
 *
 */
public class BlankLabel extends Label {

  public BlankLabel(String code) {
    aboveFields = new ArrayList<LabelField>();
    LabelField field;
    
    field = new LabelField(StringUtil.getMessageForKey("barcode.label.info.patientname"), "", 6);
    field.setDisplayFieldName(true);
    field.setUnderline(true);
    aboveFields.add(field);
    field = new LabelField(StringUtil.getMessageForKey("barcode.label.info.patientdob"), "", 4);
    field.setDisplayFieldName(true);
    field.setUnderline(true);
    aboveFields.add(field);
    field = new LabelField(StringUtil.getMessageForKey("barcode.label.info.patientid"), "", 5);
    field.setDisplayFieldName(true);
    field.setUnderline(true);
    aboveFields.add(field);
    field = new LabelField(StringUtil.getMessageForKey("barcode.label.info.site"), "", 5);
    field.setDisplayFieldName(true);
    field.setUnderline(true);
    aboveFields.add(field);

    setCode(code);
  }

  @Override
  public int getNumTextRowsBefore() {
    Iterable<LabelField> fields = getAboveFields();
    return getNumRows(fields);
  }

  @Override
  public int getNumTextRowsAfter() {
    return 0;
  }

  @Override
  public int getMaxNumLabels() {
    return 10;
  }

}
