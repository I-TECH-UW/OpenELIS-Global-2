package org.openelisglobal.workplan.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.test.service.TestServiceImpl;
import org.openelisglobal.workplan.form.WorkplanForm;
import org.openelisglobal.workplan.form.WorkplanForm.PrintWorkplan;
import org.openelisglobal.workplan.reports.IWorkplanReport;
import org.openelisglobal.workplan.reports.TestSectionWorkplanReport;
import org.openelisglobal.workplan.reports.TestWorkplanReport;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PrintWorkplanReportController extends BaseController {

    private static final String[] ALLOWED_FIELDS = new String[] { "selectedSearchID", "type", "testTypeID",
            "testSectionId", "testName", "workplanTests*.accessionNumber", "workplanTests*.patientInfo",
            "workplanTests*.receivedDate", "workplanTests*.testName", "workplanTests*.notIncludedInWorkplan",
            "resultList" };

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(ALLOWED_FIELDS);
    }

    @RequestMapping(value = "/PrintWorkplanReport", method = RequestMethod.POST)
    public void showPrintWorkplanReport(HttpServletRequest request, HttpServletResponse response,
            @ModelAttribute("form") @Validated(PrintWorkplan.class) WorkplanForm form, BindingResult result) {
        if (result.hasErrors()) {
            writeErrorsToResponse(result, response);
            return;
        }

        request.getSession().setAttribute(SAVE_DISABLED, "true");

        String workplanType = form.getType();
        String workplanName;

        if (workplanType.equals("test")) {
            String testID = form.getTestTypeID();
            workplanName = getTestTypeName(testID);
        } else {
            workplanType = Character.toUpperCase(workplanType.charAt(0)) + workplanType.substring(1);
            workplanName = form.getTestName();
        }

        // get workplan report based on testName
        IWorkplanReport workplanReport = getWorkplanReport(workplanType, workplanName);

        workplanReport.setReportPath(getReportPath());

        // set jasper report parameters
        HashMap<String, Object> parameterMap = workplanReport.getParameters();

        // prepare report
        List<?> workplanRows = workplanReport.prepareRows(form);

        // set Jasper report file name
        String reportFileName = workplanReport.getFileName();
        ClassLoader classLoader = getClass().getClassLoader();
        File reportFile = new File(classLoader.getResource("reports/" + reportFileName + ".jasper").getFile());

        try {

            byte[] bytes = null;

            JRDataSource dataSource = createReportDataSource(workplanRows);
            bytes = JasperRunManager.runReportToPdf(reportFile.getAbsolutePath(), parameterMap, dataSource);

            ServletOutputStream servletOutputStream = response.getOutputStream();
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            String downloadFilename = "WorkplanReport";
            response.setHeader("Content-Disposition", "filename=\"" + downloadFilename + ".pdf\"");

            servletOutputStream.write(bytes, 0, bytes.length);
            servletOutputStream.flush();
            servletOutputStream.close();

        } catch (JRException | IOException e) {
            LogEvent.logError(e);
            result.reject("error.jasper", "error.jasper");
        }
        if (result.hasErrors()) {
            saveErrors(result);
        }
    }

    private JRDataSource createReportDataSource(List<?> includedTests) {
        JRBeanCollectionDataSource dataSource;
        dataSource = new JRBeanCollectionDataSource(includedTests);

        return dataSource;
    }

    private String getTestTypeName(String id) {
        return TestServiceImpl.getUserLocalizedTestName(id);
    }

    public IWorkplanReport getWorkplanReport(String testType, String name) {

        IWorkplanReport workplan;

        if ("test".equals(testType)) {
            workplan = new TestWorkplanReport(name);
            // } else if ("Serology".equals(testType)) {
            // workplan = new ElisaWorkplanReport(name);
        } else {
            workplan = new TestSectionWorkplanReport(name);
        }

        return workplan;
    }

    public String getReportPath() {
        ClassLoader classLoader = getClass().getClassLoader();
        File reportFile = new File(classLoader.getResource("reports/").getFile());
        return reportFile.getAbsolutePath();
    }

    @Override
    protected String findLocalForward(String forward) {
        if (FWD_SUCCESS.equals(forward)) {
            return "homePageDefinition";
        } else {
            return "PageNotFound";
        }
    }

    @Override
    protected String getPageSubtitleKey() {
        return "workplan.title";
    }

    @Override
    protected String getPageTitleKey() {
        return "workplan.title";
    }
}
