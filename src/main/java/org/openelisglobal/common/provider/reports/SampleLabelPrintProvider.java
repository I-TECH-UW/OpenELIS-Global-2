/**
 * The contents of this file are subject to the Mozilla Public License Version 1.1 (the "License");
 * you may not use this file except in compliance with the License. You may obtain a copy of the
 * License at http://www.mozilla.org/MPL/
 *
 * <p>Software distributed under the License is distributed on an "AS IS" basis, WITHOUT WARRANTY OF
 * ANY KIND, either express or implied. See the License for the specific language governing rights
 * and limitations under the License.
 *
 * <p>The Original Code is OpenELIS code.
 *
 * <p>Copyright (C) The Minnesota Department of Health. All Rights Reserved.
 */
package org.openelisglobal.common.provider.reports;

import java.io.IOException;
import java.util.Map;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.PrinterName;
import javax.print.event.PrintJobEvent;
import javax.print.event.PrintJobListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.openelisglobal.common.exception.LIMSInvalidPrinterException;
import org.openelisglobal.common.exception.LIMSPrintException;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.util.SystemConfiguration;

/**
 * @author benzd1 modified for bugzilla 2380
 */
public class SampleLabelPrintProvider extends BasePrintProvider {

  /*
   * (non-Javadoc)
   *
   * @see
   * org.openelisglobal.common.provider.reports.BaseReportsProvider#processRequest
   * (java.util.Map, javax.servlet.http.HttpServletRequest,
   * javax.servlet.http.HttpServletResponse) bugzilla 2380: this method now throws
   * exceptions instead of returning boolean successful
   */
  @Override
  public void processRequest(
      Map parameters, HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException, PrintException, LIMSPrintException,
          LIMSInvalidPrinterException {

    try {

      PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
      String printer = null;
      PrinterName printerName = null;

      PrintService ps = null;

      // bugzilla 2380
      String validPrintersMessage = "";
      for (int i = 0; i < services.length; i++) {
        printer = services[i].getName();
        // LogEvent.logInfo(this.getClass().getSimpleName(), "method unkown", "This is one of
        // the printers " + printer);
        // bugzilla 2380 this is for error message to list valid printers in ActionError
        if (i == 0) {
          validPrintersMessage = "\\n";
        }
        validPrintersMessage += "\\n     " + printer;

        // bugzilla 2380: name must match - not start with
        if (printer.equalsIgnoreCase(SystemConfiguration.getInstance().getLabelPrinterName())) {
          printerName = new PrinterName(printer, null);
          // LogEvent.logInfo(this.getClass().getSimpleName(), "method unkown", "This is the
          // printer I will use "
          // + printerName);
          ps = services[i];
          // bugzilla 2380: load all valid printer names for error message
          // break;
        }
      }

      // LogEvent.logInfo(this.getClass().getSimpleName(), "method unkown", "Printer is
      // found " + printer);
      if (printerName == null) {
        throw new LIMSInvalidPrinterException(validPrintersMessage);
      }

      String numberOfLabelCopiesString = null;
      int numberOfLabelCopies = 1;
      try {
        numberOfLabelCopiesString =
            SystemConfiguration.getInstance().getLabelNumberOfCopies("print.label.numberofcopies");
        numberOfLabelCopies = Integer.parseInt(numberOfLabelCopiesString);
      } catch (NumberFormatException e) {
        // bugzilla 2154
        LogEvent.logError(e);
      }

      String accessionNumber = (String) parameters.get("Accession_Number");

      if (ps == null) {
        throw new LIMSPrintException("no print service");
      }

      DocPrintJob job = ps.createPrintJob();

      PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
      aset.add(new Copies(numberOfLabelCopies));

      String label = SystemConfiguration.getInstance().getDefaultSampleLabel(accessionNumber);

      byte[] byt = label.getBytes();
      DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;

      Doc doc = new SimpleDoc(byt, flavor, null);

      job.addPrintJobListener(new MyPrintJobListener());

      job.print(doc, aset);

    } catch (RuntimeException e) {
      LogEvent.logError(e);
      throw new LIMSRuntimeException("Error in SampleLabelPrintProvider processRequest()", e);
    }
  }

  class MyPrintJobListener implements PrintJobListener {
    @Override
    public void printDataTransferCompleted(PrintJobEvent pje) {
      // System.out
      // .println("The print data has been transferred to the print service");
    }

    @Override
    public void printJobCanceled(PrintJobEvent pje) {
      // LogEvent.logInfo(this.getClass().getSimpleName(), "method unkown", "The print job
      // was cancelled");
    }

    @Override
    public void printJobCompleted(PrintJobEvent pje) {
      // LogEvent.logInfo(this.getClass().getSimpleName(), "method unkown", "The print job
      // was completed");
    }

    @Override
    public void printJobFailed(PrintJobEvent pje) {
      // LogEvent.logInfo(this.getClass().getSimpleName(), "method unkown", "The print job
      // has failed");
    }

    @Override
    public void printJobNoMoreEvents(PrintJobEvent pje) {
      // System.out
      // .println("No more events will be delivered from this print service for this
      // print job.");
      // No more events will be delivered from this
      // print service for this print job.
      // This event is fired in cases where the print service
      // is not able to determine when the job completes.
    }

    @Override
    public void printJobRequiresAttention(PrintJobEvent pje) {
      // System.out
      // .println("The print service requires some attention to repair some problem.
      // E.g. running out of paper would");
      // The print service requires some attention to repair
      // some problem. E.g. running out of paper would
      // cause this event to be fired.
    }
  }
}
