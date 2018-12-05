package us.mn.state.health.lims.barcode.valueholder;

import us.mn.state.health.lims.common.valueholder.BaseObject;

/**
 * Class for persisting bar code label information in the database
 * 
 * @author Caleb
 *
 */
public class BarcodeLabelInfo extends BaseObject {

  private static final long serialVersionUID = 1L;

  //PK
  private String id;
  //Other values to persist
  private int numPrinted;
  private String code;
  private String type;

  public BarcodeLabelInfo() {
    super();
    numPrinted = 0;
  }

  public BarcodeLabelInfo(String code) {
    super();
    this.code = code;
    numPrinted = 0;
    type = parseCodeForType();
  }

  public void incrementNumPrinted() {
    ++numPrinted;
  }

  /**
   * Determines the type of label based on the given code
   * @return    The type of label this code belongs to
   */
  public String parseCodeForType() {
    if (code.contains("-")) {
      return "aliquot";
    } else if (code.contains(".")) {
      return "specimen";
    } else {
      return "order";
    }
  }
  
  /**
   * Get the id (PK of the object in the database)
   * @return    PK
   */
  public String getId() {
    return id;
  }

  /**
   * Set the id (PK of the object in the database)
   * @param id    PK
   */
  public void setId(String id) {
    this.id = id;
  }

  public int getNumPrinted() {
    return numPrinted;
  }

  public void setNumPrinted(int numPrinted) {
    this.numPrinted = numPrinted;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

}
