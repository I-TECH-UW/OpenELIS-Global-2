package spring.mine.workplan.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import spring.mine.workplan.form.WorkplanForm;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.services.TestService;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.workplan.reports.ElisaWorkplanReport;
import us.mn.state.health.lims.workplan.reports.IWorkplanReport;
import us.mn.state.health.lims.workplan.reports.TestSectionWorkplanReport;
import us.mn.state.health.lims.workplan.reports.TestWorkplanReport;

@Controller
public class PrintWorkplanReportController extends BaseController {

	private static us.mn.state.health.lims.workplan.reports.IWorkplanReport workplanReport;
	private String reportPath;

	@RequestMapping(value = "/PrintWorkplanReport", method = RequestMethod.POST)
	public void showPrintWorkplanReport(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("form") WorkplanForm form) {
		if (form == null) {
			form = new WorkplanForm();
		}
		form.setFormAction("");
		BaseErrors errors = new BaseErrors();
		if (form.getErrors() != null) {
			errors = (BaseErrors) form.getErrors();
		}
		checkUserAndSetup(form, errors, request);

		if (errors.hasErrors()) {
			return;
		}

		request.getSession().setAttribute(SAVE_DISABLED, "true");

		String workplanType = form.getString("workplanType");
		String workplanName;

		if (workplanType.equals("test")) {
			String testID = (String) form.get("testTypeID");
			workplanName = getTestTypeName(testID);
		} else {
			workplanType = Character.toUpperCase(workplanType.charAt(0)) + workplanType.substring(1);
			workplanName = form.getString("testName");
		}

		// get workplan report based on testName
		workplanReport = getWorkplanReport(workplanType, workplanName);

		workplanReport.setReportPath(getReportPath());

		// set jasper report parameters
		HashMap<String, ?> parameterMap = workplanReport.getParameters();

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

			servletOutputStream.write(bytes, 0, bytes.length);
			servletOutputStream.flush();
			servletOutputStream.close();

		} catch (JRException jre) {
			LogEvent.logError("PringWorkplanReportAction", "processRequest()", jre.toString());
			errors.reject("error.jasper", "error.jasper");
		} catch (Exception e) {
			LogEvent.logError("PrintWorkplanReportAction", "processRequest()", e.toString());
			errors.reject("error.jasper", "error.jasper");
		}

		if (errors.hasErrors()) {
			saveErrors(errors);
		}

		// findForward(forward, form);
	}

	private String getName(String workplanType) {
		String name = StringUtil.getContextualMessageForKey("test.section." + workplanType);

		if (name == null) {
			name = workplanType;
		}
		return name;
	}

	private JRDataSource createReportDataSource(List<?> includedTests) {
		JRBeanCollectionDataSource dataSource;
		dataSource = new JRBeanCollectionDataSource(includedTests);

		return dataSource;
	}

	private String getTestTypeName(String id) {
		return TestService.getUserLocalizedTestName(id);
	}

	public IWorkplanReport getWorkplanReport(String testType, String name) {

		IWorkplanReport workplan;

		if ("test".equals(testType)) {
			workplan = new TestWorkplanReport(name);
		} else if ("Serology".equals(testType)) {
			workplan = new ElisaWorkplanReport(name);
		} else {
			workplan = new TestSectionWorkplanReport(name);
		}

		return workplan;
	}

	public String getReportPath() {
		if (reportPath == null) {
			ClassLoader classLoader = getClass().getClassLoader();
			File reportFile = new File(classLoader.getResource("reports/").getFile());
			reportPath = reportFile.getAbsolutePath();
		}
		return reportPath;
	}

	@Override
	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if ("success".equals(forward)) {
			return new ModelAndView("homePageDefinition", "form", form);
		} else {
			return new ModelAndView("PageNotFound");
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
