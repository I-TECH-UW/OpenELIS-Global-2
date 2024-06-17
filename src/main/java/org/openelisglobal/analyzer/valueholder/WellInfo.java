package org.openelisglobal.analyzer.valueholder;

public class WellInfo {

  private int wellRows;

  private int wellColumns;

  public WellInfo(int wellColumns, int wellRows) {
    this.wellColumns = wellColumns;
    this.wellRows = wellRows;
  }

  public int getWellRows() {
    return wellRows;
  }

  public void setWellRows(int wellRows) {
    this.wellRows = wellRows;
  }

  public int getWellColumns() {
    return wellColumns;
  }

  public void setWellColumns(int wellColumns) {
    this.wellColumns = wellColumns;
  }
}
