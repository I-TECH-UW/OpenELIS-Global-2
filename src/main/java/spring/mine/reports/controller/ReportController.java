package spring.mine.reports.controller;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import spring.mine.reports.form.ReportForm;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.services.ReportTrackingService;
import us.mn.state.health.lims.common.services.ReportTrackingService.ReportType;
import us.mn.state.health.lims.reports.action.implementation.IReportCreator;
import us.mn.state.health.lims.reports.action.implementation.IReportParameterSetter;
import us.mn.state.health.lims.reports.action.implementation.ReportImplementationFactory;

@Controller
@SessionAttributes("form")
public class ReportController extends BaseController {

	@Autowired
	ServletContext context;

	private static String reportPath = null;
	private static String imagesPath = null;

	@ModelAttribute("form")
	public ReportForm form() {
		return new ReportForm();
	}

	@RequestMapping(value = "/Report", method = RequestMethod.GET)
	public ModelAndView showReport(HttpServletRequest request, @ModelAttribute("form") ReportForm form)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new ReportForm();
		}
		form.setFormAction("");
		BaseErrors errors = new BaseErrors();
		if (form.getErrors() != null) {
			errors = (BaseErrors) form.getErrors();
		}
		ModelAndView mv = checkUserAndSetup(form, errors, request);

		if (errors.hasErrors()) {
			return mv;
		}

		PropertyUtils.setProperty(form, "type", request.getParameter("type"));
		PropertyUtils.setProperty(form, "report", request.getParameter("report"));
		IReportParameterSetter setter = ReportImplementationFactory.getParameterSetter(request.getParameter("report"));

		if (setter != null) {
			setter.setRequestParameters(form);
		}

		return findForward(forward, form);
	}

	@RequestMapping(value = "/ReportPrint", method = RequestMethod.GET)
	public void showReportPrint(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("form") ReportForm form, SessionStatus status)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new ReportForm();
		}
		form.setFormAction("");
		form.setFormMethod(RequestMethod.GET);
		BaseErrors errors = new BaseErrors();
		if (form.getErrors() != null) {
			errors = (BaseErrors) form.getErrors();
		}
		ModelAndView mv = checkUserAndSetup(form, errors, request);

		if (errors.hasErrors()) {
			return;
		}

		PropertyUtils.setProperty(form, "type", request.getParameter("type"));

		IReportCreator reportCreator = ReportImplementationFactory.getReportCreator(request.getParameter("report"));

		forward = FWD_FAIL;

		if (reportCreator != null) {
			reportCreator.setRequestedReport(request.getParameter("report"));
			reportCreator.initializeReport(form);
			reportCreator.setReportPath(getReportPath());

			HashMap<String, String> parameterMap = (HashMap<String, String>) reportCreator.getReportParameters();
			parameterMap.put("SUBREPORT_DIR", getReportPath());
			parameterMap.put("imagesPath", getImagesPath());

			try {
				response.setContentType(reportCreator.getContentType());
				String responseHeaderName = reportCreator.getResponseHeaderName();
				String responseHeaderContent = reportCreator.getResponseHeaderContent();
				if (!GenericValidator.isBlankOrNull(responseHeaderName)
						&& !GenericValidator.isBlankOrNull(responseHeaderContent)) {
					response.setHeader(responseHeaderName, responseHeaderContent);
				}

				byte[] bytes = reportCreator.runReport();

				response.setContentLength(bytes.length);

				ServletOutputStream servletOutputStream = response.getOutputStream();

				servletOutputStream.write(bytes, 0, bytes.length);
				servletOutputStream.flush();
				servletOutputStream.close();
			} catch (Exception e) {
				LogEvent.logErrorStack("CommonReportPrintAction", "performAction", e);
				e.printStackTrace();
			}
		}

		if ("patient".equals(request.getParameter("type"))) {
			trackReports(reportCreator, request.getParameter("report"), ReportType.PATIENT);
		}

		// signal to remove from from session
		status.setComplete();
	}

	private void trackReports(IReportCreator reportCreator, String reportName, ReportType type) {
		new ReportTrackingService().addReports(reportCreator.getReportedOrders(), type, reportName, currentUserId);
	}

	public String getReportPath() {
		if (reportPath == null) {
			ClassLoader classLoader = getClass().getClassLoader();
			reportPath = classLoader.getResource("reports").getPath();
		}
		return reportPath;
	}

	public String getImagesPath() {
		if (imagesPath == null) {
			imagesPath = context.getRealPath("") + "images" + File.separator;
		}
		return imagesPath;
	}

	@Override
	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if ("success".equals(forward)) {
			return new ModelAndView("commonReportDefiniton", "form", form);
		} else {
			return new ModelAndView("PageNotFound");
		}
	}

	@Override
	protected String getPageSubtitleKey() {
		return "reports.add.params";
	}

	@Override
	protected String getPageTitleKey() {
		return "reports.add.params";
	}

}
