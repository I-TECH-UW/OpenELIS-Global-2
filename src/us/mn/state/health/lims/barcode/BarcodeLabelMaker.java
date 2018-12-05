package us.mn.state.health.lims.barcode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.Barcode;
import com.lowagie.text.pdf.Barcode128;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;

import us.mn.state.health.lims.barcode.labeltype.BlankLabel;
import us.mn.state.health.lims.barcode.labeltype.Label;
import us.mn.state.health.lims.barcode.labeltype.OrderLabel;
import us.mn.state.health.lims.barcode.labeltype.SpecimenLabel;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.SampleStatus;
import us.mn.state.health.lims.patient.dao.PatientDAO;
import us.mn.state.health.lims.patient.daoimpl.PatientDAOImpl;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.sample.dao.SampleDAO;
import us.mn.state.health.lims.sample.daoimpl.SampleDAOImpl;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sampleitem.dao.SampleItemDAO;
import us.mn.state.health.lims.sampleitem.daoimpl.SampleItemDAOImpl;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;

/**
 * Class for taking lists of Label objects and turning them into a printable format
 * 
 * @author Caleb
 *
 */
public class BarcodeLabelMaker {

  // number of columns for label layout grid
  private static int NUM_COLUMNS = 10;

  // stores labels between generation and creating pdf
  private ArrayList<Label> labels;
  
  // whether max print numbers should be ignored
  private String override;
  
  // for audit trail when incrementing num printed
  private String sysUserId;
  
  private static final Set<Integer> ENTERED_STATUS_SAMPLE_LIST = new HashSet<Integer>();
  static {
    ENTERED_STATUS_SAMPLE_LIST
            .add(Integer.parseInt(StatusService.getInstance().getStatusID(SampleStatus.Entered)));
  }

  public BarcodeLabelMaker() {
    labels = new ArrayList<Label>();
  }

  public BarcodeLabelMaker(Label label) {
    labels = new ArrayList<Label>();
    labels.add(label);
  }

  public BarcodeLabelMaker(ArrayList<Label> labels) {
    this.labels = labels;
  }

  /**
   * Create all labels that were requested and place them into the provided list of labels
   * @param labels    A list that the generated labels should be placed into
   * @param labNo     The labNo that should appear on the bar codes
   * @param patientId The id belonging to the patient that should appear on the label
   * @param type      The type of bar code that should be created
   * @param quantity  The number of labels that should be printed
   * @param override  Whether the print limit should be ignored in label generation
   * @param request   This is used to attribute this action to a user
   */
  public void generateLabels(String labNo, String patientId, String type,
          String quantity, String override) {
    
    /*System.out.println(
            "labNo: " + labNo + "\n" +
            "patientId: " + patientId + "\n" +
            "type: " + type + "\n" +
            "quantity: " + quantity + "\n" +
            "override: " + override); */

    if ("default".equals(type)) {
      // add 2 order label per default
      SampleDAO sampleDAO = new SampleDAOImpl();
      Sample sample = sampleDAO.getSampleByAccessionNumber(labNo);
      OrderLabel orderLabel = new OrderLabel(getPatientForID(patientId), sample, labNo);
      orderLabel.setNumLabels(2);
      orderLabel.linkBarcodeLabelInfo();
      // get sysUserId from login module
      orderLabel.setSysUserId(sysUserId);
      if (orderLabel.checkIfPrintable() || "true".equals(override))
        labels.add(orderLabel);
      
      // 1 specimen label per sampleitem
      SampleItemDAO sampleItemDAO = new SampleItemDAOImpl();
      List<SampleItem> sampleItemList = sampleItemDAO
              .getSampleItemsBySampleIdAndStatus(sample.getId(), ENTERED_STATUS_SAMPLE_LIST);
      for (SampleItem sampleItem : sampleItemList) {
        SpecimenLabel specLabel = new SpecimenLabel(getPatientForID(patientId), sample, sampleItem,
                labNo);
        specLabel.setNumLabels(1);
        specLabel.linkBarcodeLabelInfo();
        // get sysUserId from login module
        specLabel.setSysUserId(sysUserId);
        if (specLabel.checkIfPrintable() || "true".equals(override))
          labels.add(specLabel);
      }
    // order case
    } else if ("order".equals(type)) {
      SampleDAO sampleDAO = new SampleDAOImpl();
      Sample sample = sampleDAO.getSampleByAccessionNumber(labNo);
      OrderLabel orderLabel = new OrderLabel(getPatientForID(patientId), sample, labNo);
      orderLabel.setNumLabels(Integer.parseInt(quantity));
      orderLabel.linkBarcodeLabelInfo();
      // get sysUserId from login module
      orderLabel.setSysUserId(sysUserId);
      if (orderLabel.checkIfPrintable() || "true".equals(override))
        labels.add(orderLabel);

    // specimen case
    } else if ("specimen".equals(type)) {
      String specimenNumber = labNo.substring(labNo.lastIndexOf(".") + 1);
      labNo = labNo.substring(0, labNo.lastIndexOf("."));
      SampleDAO sampleDAO = new SampleDAOImpl();
      SampleItemDAO sampleItemDAO = new SampleItemDAOImpl();
      Sample sample = sampleDAO.getSampleByAccessionNumber(labNo);
      List<SampleItem> sampleItemList = sampleItemDAO
              .getSampleItemsBySampleIdAndStatus(sample.getId(), ENTERED_STATUS_SAMPLE_LIST);
      for (SampleItem sampleItem : sampleItemList) {
        // get only the sample item matching the specimen number
        if (sampleItem.getSortOrder().equals(specimenNumber)) {
          SpecimenLabel specLabel = new SpecimenLabel(getPatientForID(patientId), sample,
                  sampleItem, labNo);
          specLabel.setNumLabels(Integer.parseInt(quantity));
          specLabel.linkBarcodeLabelInfo();
          // get sysUserId from login module
          specLabel.setSysUserId(sysUserId);
          if (specLabel.checkIfPrintable() || "true".equals(override))
            labels.add(specLabel);
        }
      }
      
    // blank case
    } else if ("blank".equals(type)) {
      BlankLabel blankLabel = new BlankLabel(labNo);
      blankLabel.linkBarcodeLabelInfo();
      // get sysUserId from login module
      blankLabel.setSysUserId(sysUserId);
      if (blankLabel.checkIfPrintable() || "true".equals(override))
        labels.add(blankLabel);
    }
  }

  /**
   * Creates a PDF as a stream of all the stored labels
   * @return    Stream of all labels that have been generated
   */
  public ByteArrayOutputStream createLabelsAsStream() {
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    if (labels.isEmpty())
      return stream;
    try {
      Document document = new Document();
      PdfWriter writer = PdfWriter.getInstance(document, stream);
      document.open();
      for (Label label : labels) {
        for (int i = 0; i < label.getNumLabels(); ++i) {
          if (label.checkIfPrintable() || "true".equals(override)) {
            //a ratio is used with set width so that font size 
            //does not need to be adjusted            
            float ratio = label.getHeight() / label.getWidth();
            label.pdfWidth = 350;
            label.pdfHeight = label.pdfWidth * ratio;
            drawLabel(label, writer, document);
            label.incrementNumPrinted();
          }
        }
      }
      document.close();
      writer.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return stream;
  }

  // parse label info to draw label and add to document
  /**
   * Draws a label as a PDF page and adds the page to the document
   * @param label         The label to draw as a pdf "page"
   * @param writer        The writer for the pdf stream
   * @param document      The document to add the label "page" to
   * @throws DocumentException
   * @throws IOException
   */
  private void drawLabel(Label label, PdfWriter writer, Document document)
          throws DocumentException, IOException {
    // set up document and grid
    Rectangle rec = new Rectangle(label.pdfWidth, label.pdfHeight);
    document.setPageSize(rec);
    document.newPage();
    PdfPTable table = new PdfPTable(NUM_COLUMNS);
    table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
    table.setTotalWidth(label.pdfWidth - (2 * label.getMargin()));
    table.setLockedWidth(true);
    
    // add above fields into table
    Iterable<LabelField> fields = label.getAboveFields();
    for (LabelField field : fields) {
      if (field.isStartNewline())
        table.completeRow();
      table.addCell(createFieldAsPDFField(label, field));
    }
    table.completeRow();
    
    // add bar code
    if (label.getScaledBarcodeSpace() != NUM_COLUMNS) {
      table.addCell(createSpacerCell((NUM_COLUMNS - label.getScaledBarcodeSpace()) / 2));
      table.addCell(create128Barcode(label, writer, label.getScaledBarcodeSpace()));
      table.addCell(createSpacerCell((NUM_COLUMNS - label.getScaledBarcodeSpace()) / 2));
    } else {
      table.addCell(create128Barcode(label, writer, label.getScaledBarcodeSpace()));
    }
    
    // add below fields into table
    Iterable<LabelField> belowFields = label.getBelowFields();
    if (belowFields != null) {
      for (LabelField field : belowFields) {
        if (field.isStartNewline())
          table.completeRow();
        table.addCell(createFieldAsPDFField(label, field));
      }
      table.completeRow();
    }
    
    // convert table to image, scale image, and center it on document
    document.add(scaleCentreTableAsImage(label, writer, table));
  }

  /**
   * Converts table to a scaled, centered image
   * @param label     The label to specify the dimensions
   * @param writer    For the stream
   * @param table     The table to convert and scale
   * @return          An image representation of the table
   * @throws BadElementException
   */
  private Image scaleCentreTableAsImage(Label label, PdfWriter writer, PdfPTable table)
          throws BadElementException {
    PdfContentByte cb = writer.getDirectContent();
    PdfTemplate template = cb.createTemplate(table.getTotalWidth(), table.getTotalHeight());
    table.writeSelectedRows(0, -1, 0, table.getTotalHeight(), template);
    Image labelAsImage = Image.getInstance(template);
    labelAsImage.scaleAbsoluteHeight(label.pdfHeight - (2 * label.getMargin()));
    labelAsImage.setAbsolutePosition(((label.pdfWidth) - labelAsImage.getScaledWidth()) / 2,
            ((label.pdfHeight) - labelAsImage.getScaledHeight()) / 2);
    return labelAsImage;
  }

  /**
   * Create code 128 bar code with bar code as text below bar code
   * @param label     The label containing the bar code
   * @param writer    For the stream
   * @param colspan   The number of columns the bar code will span
   * @return          Cell containing bar code with bar code text
   * @throws DocumentException
   * @throws IOException
   */
  private PdfPCell create128Barcode(Label label, PdfWriter writer, int colspan)
          throws DocumentException, IOException {
    
    Barcode128 barcode = new Barcode128();
    barcode.setCodeType(Barcode.CODE128);
    barcode.setCode(label.getCode());
    //shrink bar code height inversely with number of text rows
    barcode.setBarHeight(
            (10 - (label.getNumTextRowsBefore() + label.getNumTextRowsAfter())) * 30 / 10);
    PdfPCell cell = new PdfPCell(
            barcode.createImageWithBarcode(writer.getDirectContent(), null, null), true);
    cell.setBorder(Rectangle.NO_BORDER);
    cell.setColspan(colspan);
    cell.setPadding(1);
    return cell;
  }

  /**
   * Create code 128 bar code without bar code as text below bar code
   * Recommended for large fonts
   * @param label     The label containing the bar code
   * @param writer    For the stream
   * @param colspan   The number of columns the bar code will span
   * @return          Cell containing bar code 
   * @throws DocumentException
   * @throws IOException
   */
  @SuppressWarnings("unused")
  private PdfPCell create128BarcodeNoText(Label label, PdfWriter writer, int colspan)
          throws DocumentException, IOException {
    Barcode128 barcode = new Barcode128();
    barcode.setCodeType(Barcode.CODE128);
    barcode.setCode(label.getCode());
    barcode.setFont(null);
    //shrink bar code height inversely with number of text rows
    barcode.setBarHeight(
            (10 - (label.getNumTextRowsBefore() + label.getNumTextRowsAfter())) * 30 / 10);
    PdfPCell cell = new PdfPCell(
            barcode.createImageWithBarcode(writer.getDirectContent(), null, null), true);
    cell.setBorder(Rectangle.NO_BORDER);
    cell.setColspan(colspan);
    cell.setPadding(1);
    return cell;
  }

  /**
   * Create a cell from a Field object
   * @param label   Contains font to use
   * @param field   To base values, and formats on
   * @return        Cell containing field
   */
  private PdfPCell createFieldAsPDFField(Label label, LabelField field) {
    Paragraph fieldPDF = new Paragraph();
    // add field name if applicable
    if (field.isDisplayFieldName()) {
      Chunk name = new Chunk(field.getName() + ": ");
      name.setFont(label.getValueFont());
      fieldPDF.add(name);
    }
    // add value 
    Chunk value = new Chunk(field.getValue());
    value.setFont(label.getValueFont());
    // add underline to value if applicable
    if (field.isUnderline()) {
      Chunk underline = new Chunk(new LineSeparator(0.5f, 100, null, 0, -1));
      value.setUnderline(0.5f, -1);
      fieldPDF.add(value);
      fieldPDF.add(underline);
    } else {
      fieldPDF.add(value);
    }
    // construct and configure cell
    PdfPCell cell = new PdfPCell(fieldPDF);
    cell.setBorder(Rectangle.NO_BORDER);
    cell.setColspan(field.getColspan());
    cell.setPadding(1);
    
    return cell;
  }

  /**
   * Creates a blank cell to fill space
   * @param colspan   The number of columns cell should take up
   * @return          Cell containing nothing
   */
  private PdfPCell createSpacerCell(int colspan) {
    PdfPCell cell = new PdfPCell();
    cell.setBorder(Rectangle.NO_BORDER);
    cell.setColspan(colspan);
    return cell;
  }

  /**
   * Get patient by id
   * @param personKey The PK for the patient 
   * @return          The corresponding patient
   */
  private Patient getPatientForID(String personKey) {
    Patient patient = new Patient();
    patient.setId(personKey);
    PatientDAO dao = new PatientDAOImpl();
    dao.getData(patient);
    if (patient.getId() == null)
      return null;
    else
      return patient;
  }

  public String getOverride() {
    return override;
  }

  public void setOverride(String override) {
    this.override = override;
  }

  public String getSysUserId() {
    return sysUserId;
  }

  public void setSysUserId(String sysUserId) {
    this.sysUserId = sysUserId;
  }

}
