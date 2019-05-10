package spring.mine.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import us.mn.state.health.lims.analyzerimport.action.AnalyzerImportServlet;
import us.mn.state.health.lims.common.servlet.barcode.LabelMakerServlet;
import us.mn.state.health.lims.common.servlet.query.AjaxQueryXMLServlet;
import us.mn.state.health.lims.common.servlet.reports.LogoUploadServlet;
import us.mn.state.health.lims.common.servlet.reports.ReportsServlet;
import us.mn.state.health.lims.common.servlet.startup.StartStopListener;
import us.mn.state.health.lims.common.servlet.validation.AjaxTextServlet;
import us.mn.state.health.lims.common.servlet.validation.AjaxXMLServlet;
import us.mn.state.health.lims.common.util.UTF8Filter;
import us.mn.state.health.lims.dataexchange.aggregatereporting.IndicatorAggregationReportingServlet;
import us.mn.state.health.lims.dataexchange.order.action.OrderRawServlet;
import us.mn.state.health.lims.dataexchange.order.action.OrderServlet;
import us.mn.state.health.lims.metricservice.action.MetricServicesServlet;
import us.mn.state.health.lims.security.SecurityFilter;

public class AnnotationWebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(AppConfig.class);

		servletContext.addListener(new ContextLoaderListener(context));
		servletContext.addListener(new StartStopListener());

		setupServlets(servletContext);
		setupFilters(servletContext);
	}

	private void setupServlets(ServletContext servletContext) {
		int startupOrder = 0;
		AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();

		dispatcherContext.register(AppConfig.class);
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher",
				new DispatcherServlet(dispatcherContext));
		dispatcher.setLoadOnStartup(++startupOrder);
		dispatcher.addMapping("/");

		ServletRegistration.Dynamic logoUploadServlet = servletContext.addServlet("logoUpload",
				LogoUploadServlet.class);
		logoUploadServlet.setLoadOnStartup(++startupOrder);
		logoUploadServlet.addMapping("/logoUpload");

		ServletRegistration.Dynamic ajaxTextServlet = servletContext.addServlet("ajaxText", AjaxTextServlet.class);
		ajaxTextServlet.setLoadOnStartup(++startupOrder);
		ajaxTextServlet.addMapping("/ajaxText");

		ServletRegistration.Dynamic ajaxXMLServlet = servletContext.addServlet("ajaxXML", AjaxXMLServlet.class);
		ajaxXMLServlet.setLoadOnStartup(++startupOrder);
		ajaxXMLServlet.addMapping("/ajaxXML");

		ServletRegistration.Dynamic ajaxQueryXMLServlet = servletContext.addServlet("ajaxQueryXML",
				AjaxQueryXMLServlet.class);
		ajaxQueryXMLServlet.setLoadOnStartup(++startupOrder);
		ajaxQueryXMLServlet.addMapping("/ajaxQueryXML");

		ServletRegistration.Dynamic ajaxAutocompleteXMLServlet = servletContext.addServlet("ajaxAutocompleteXML",
				us.mn.state.health.lims.common.servlet.autocomplete.AjaxXMLServlet.class);
		ajaxAutocompleteXMLServlet.setLoadOnStartup(++startupOrder);
		ajaxAutocompleteXMLServlet.addMapping("/ajaxAutocompleteXML");

		ServletRegistration.Dynamic ajaxSelectDropDownXMLServlet = servletContext.addServlet("ajaxSelectDropDownXML",
				us.mn.state.health.lims.common.servlet.selectdropdown.AjaxXMLServlet.class);
		ajaxSelectDropDownXMLServlet.setLoadOnStartup(++startupOrder);
		ajaxSelectDropDownXMLServlet.addMapping("/ajaxSelectDropDownXML");

		ServletRegistration.Dynamic reportsServlet = servletContext.addServlet("reportsServlet", ReportsServlet.class);
		reportsServlet.setLoadOnStartup(++startupOrder);
		reportsServlet.addMapping("/reportsServlet");

		ServletRegistration.Dynamic ajaxDataXMLLServlet = servletContext.addServlet("ajaxDataXML",
				us.mn.state.health.lims.common.servlet.data.AjaxXMLServlet.class);
		ajaxDataXMLLServlet.setLoadOnStartup(++startupOrder);
		ajaxDataXMLLServlet.addMapping("/ajaxDataXML");

		ServletRegistration.Dynamic importAnalyzerServlet = servletContext.addServlet("importAnalyzer",
				AnalyzerImportServlet.class);
		importAnalyzerServlet.setLoadOnStartup(++startupOrder);
		importAnalyzerServlet.addMapping("/importAnalyzer");

		ServletRegistration.Dynamic metricServicesServlet = servletContext.addServlet("MetricServicesServlet",
				MetricServicesServlet.class);
		metricServicesServlet.setLoadOnStartup(++startupOrder);
		metricServicesServlet.addMapping("/MetricServices");

		ServletRegistration.Dynamic indicatorAggregationServlet = servletContext
				.addServlet("IndicatorAggregationServlet", IndicatorAggregationReportingServlet.class);
		indicatorAggregationServlet.setLoadOnStartup(++startupOrder);
		indicatorAggregationServlet.addMapping("/IndicatorAggregation");

		ServletRegistration.Dynamic orderServlet = servletContext.addServlet("OrderRequestServlet", OrderServlet.class);
		orderServlet.setLoadOnStartup(++startupOrder);
		orderServlet.addMapping("/OrderRequest");

		ServletRegistration.Dynamic orderRequestRawServlet = servletContext.addServlet("OrderRequestRawServlet",
				OrderRawServlet.class);
		orderRequestRawServlet.setLoadOnStartup(++startupOrder);
		orderRequestRawServlet.addMapping("/OrderRequest_Raw");

		ServletRegistration.Dynamic labelMakerServlet = servletContext.addServlet("LabelMakerServlet",
				LabelMakerServlet.class);
		labelMakerServlet.setLoadOnStartup(++startupOrder);
		labelMakerServlet.addMapping("/LabelMakerServlet");
	}

	private void setupFilters(ServletContext servletContext) {
		FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("UTF8Filter", UTF8Filter.class);
		encodingFilter.addMappingForUrlPatterns(null, true, "/*");

		FilterRegistration.Dynamic securityFilter = servletContext.addFilter("SecurityFilter", SecurityFilter.class);
		securityFilter.addMappingForUrlPatterns(null, true, "/*");
	}

}
