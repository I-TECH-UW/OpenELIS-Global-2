<%@ page language="java" contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.util.SystemConfiguration,
			us.mn.state.health.lims.common.util.ConfigurationProperties,
			us.mn.state.health.lims.common.formfields.AdminFormFields,
			us.mn.state.health.lims.common.formfields.AdminFormFields.Field"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" uri="/tags/labdev-view" %>
<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>



<%!String permissionBase = SystemConfiguration.getInstance()
			.getPermissionAgent();
	AdminFormFields adminFields = AdminFormFields.getInstance();%>
<table cellpadding="0" cellspacing="1" width="100%" align="left">
	<%--id is important for activating the menu tabs: see tabs.jsp from struts-menu for how masterListsSubMenu is used--%>
	<%-- similar code will need to be added in the left panel and in tabs.jsp for any menu tab that has the submenu on the left hand side--%>

	<ul id="masterListsSubMenu.do" class="leftnavigation">
		<%
			if ("true"
					.equals(ConfigurationProperties
							.getInstance()
							.getPropertyValueLowerCase(
									ConfigurationProperties.Property.TrainingInstallation))) {
		%>
		<li><a href=<c:url value="/DatabaseCleaning"/> >
				<spring:message code="database.clean" />
			</a></li>
		<%
			}
		%>
		<%
			if (adminFields.useField(Field.AnalyzerTestNameMenu)) {
		%>
		<li><a href=<c:url value="/AnalyzerTestNameMenu.do"/> >
				<spring:message code="analyzerTestName.browse.title" />
			</a></li>
		<%
			}
		%>
		<%
			if (adminFields.useField(Field.CodeElementXref)) {
		%>
		<li><a href=<c:url value="/CodeElementXref.do"/> >
				<spring:message code="codeelementxref.browse.title" />
			</a></li>
		<%
			}
		%>
		<%
			if (adminFields.useField(Field.CodeElementTypeMenu)) {
		%>
		<li><a href=<c:url value="/CodeElementTypeMenu.do"/> >
				<spring:message code="codeelementtype.browse.title" />
			</a></li>
		<%
			}
		%>
		<%
			if (adminFields.useField(Field.CountyMenu)) {
		%>
		<li><a href=<c:url value="/CountyMenu.do"/> >
				<spring:message code="county.browse.title" />
			</a></li>
		<%
			}
		%>
		<%
			if (adminFields.useField(Field.DictionaryMenu)) {
		%>
		<li><a href=<c:url value="/DictionaryMenu.do"/> >
				<spring:message code="dictionary.browse.title" />
			</a></li>
		<%
			}
		%>
		<%
			if (adminFields.useField(Field.DictionaryCategoryMenu)) {
		%>
		<li><a href=<c:url value="/DictionaryCategoryMenu.do"/> >
				<spring:message code="dictionarycategory.browse.title" />
			</a></li>
		<%
			}
		%>
		<%
			if (adminFields.useField(Field.LabelMenu)) {
		%>
		<li><a href=<c:url value="/LabelMenu.do"/> >
				<spring:message code="label.browse.title" />
			</a></li>
		<%
			}
		%>
		<%
			if (adminFields.useField(Field.MethodMenu)) {
		%>
		<li><a href=<c:url value="/MethodMenu.do"/> >
				<spring:message code="method.browse.title" />
			</a></li>
		<%
			}
		%>
		<%
			if (adminFields.useField(Field.OrganizationMenu)) {
		%>
		<li><a href=<c:url value="/OrganizationMenu.do"/> >
				<spring:message code="organization.browse.title" />
			</a></li>
		<%
			}
		%>
		<%
			if (adminFields.useField(Field.PanelMenu)) {
		%>
		<li><a href=<c:url value="/PanelMenu.do"/> >
				<spring:message code="panel.browse.title" />
			</a></li>
		<%
			}
		%>
		<%
			if (adminFields.useField(Field.PanelItemMenu)) {
		%>
		<li><a href=<c:url value="/PanelItemMenu.do"/> >
				<spring:message code="panelitem.browse.title" />
			</a></li>
		<%
			}
		%>
		<%
			if (adminFields.useField(Field.PatientTypeMenu)) {
		%>
		<li><a href=<c:url value="/PatientTypeMenu.do"/> >
				<spring:message code="patienttype.browse.title" />
			</a></li>
		<%
			}
		%>
		<%
			if (adminFields.useField(Field.ProgramMenu)) {
		%>
		<li><a href=<c:url value="/ProgramMenu.do"/> >
				<spring:message code="program.browse.title" />
			</a></li>
		<%
			}
		%>
		<%
			if (adminFields.useField(Field.ProjectMenu)) {
		%>
		<li><a href=<c:url value="/ProjectMenu.do"/> >
				<spring:message code="project.browse.title" />
			</a></li>
		<%
			}
		%>
		<%
			if (adminFields.useField(Field.ProviderMenu)) {
		%>
		<li><a href=<c:url value="/ProviderMenu.do"/> >
				<spring:message code="provider.browse.title" />
			</a></li>
		<%
			}
		%>
		<%
			if (adminFields.useField(Field.QaEventMenu)) {
		%>
		<li><a href=<c:url value="/QaEventMenu.do"/> >
				<spring:message code="qaevent.browse.title" />
			</a></li>
		<%
			}
		%>
		<%
			if (adminFields.useField(Field.ReceiverCodeElementMenu)) {
		%>
		<li><a href=<c:url value="/ReceiverCodeElementMenu.do"/> >
				<spring:message code="receivercodeelement.browse.title" />
			</a></li>
		<%
			}
		%>
		<%
			if (adminFields.useField(Field.RegionMenu)) {
		%>
		<li><a href=<c:url value="/RegionMenu.do"/> >
				<spring:message code="region.browse.title" />
			</a></li>
		<%
			}
		%>
		<%
			if (adminFields.useField(Field.ResultLimitsMenu)) {
		%>
		<li><a href=<c:url value="/ResultLimitsMenu.do"/> >
				<spring:message code="resultlimits.browse.title" />
			</a></li>
		<%
			}
		%>
		<%
			if (adminFields.useField(Field.RoleMenu)) {
		%>
		<li><a href=<c:url value="/RoleMenu.do"/> >
				<spring:message code="role.browse.title" />
			</a></li>
		<%
			}
		%>
		<%
			if (adminFields.useField(Field.SiteInformationMenu)) {
		%>
		<li><a href=<c:url value="/SiteInformationMenu.do"/> >
				<spring:message code="siteInformation.browse.title" />
			</a></li>
		<%
			}
		%>
		<%
			if (adminFields.useField(Field.SampleEntryMenu)) {
		%>
		<li><a href=<c:url value="/SampleEntryConfigMenu.do"/> >
				<spring:message code="sample.entry.browse.title" />
			</a></li>
		<%
			}
		%>
		<li><a href=<c:url value="/TestManagementConfigMenu.do"/> >
				<spring:message code="configuration.test.management" />
			</a></li>

		<li><a href=<c:url value="/BatchTestReassignment"/> >
				<spring:message code="configuration.batch.test.reassignment" />
			</a></li>

		<li><a href=<c:url value="/MenuStatementConfigMenu.do"/> >
				<spring:message code="MenuStatementConfig.browse.title" />
			</a></li>

		<%
			if (adminFields.useField(Field.PATIENT_ENTRY_CONFIGURATION)) {
		%>
		<li><a href=<c:url value="/PatientConfigurationMenu.do"/> >
				<spring:message code="patientEntryConfiguration.browse.title" />
			</a></li>
		<%
			}
		%>

		<%
			if (adminFields.useField(Field.ResultInformationMenu)) {
		%>
		<li><a href=<c:url value="/ResultConfigurationMenu.do"/> >
				<spring:message code="resultConfiguration.browse.title" />
			</a></li>
		<%
			}
		%>
		<%
			if (adminFields.useField(Field.PRINTED_REPORTS_CONFIGURATION)) {
		%>
		<li><a href=<c:url value="/PrintedReportsConfigurationMenu.do"/> >
				<spring:message code="printedReportsConfiguration.browse.title" />
			</a></li>
		<%
			}
		%>
		<%
			if (adminFields.useField(Field.WORKPLAN_CONFIGURATION)) {
		%>
		<li><a href=<c:url value="/WorkplanConfigurationMenu.do"/> >
				<spring:message code="workplanConfiguration.browse.title" />
			</a></li>
		<%
			}
		%>
		<%
			if (adminFields.useField(Field.NON_CONFORMITY_CONFIGURATION)) {
		%>
		<li><a href=<c:url value="/NonConformityConfigurationMenu.do"/> >
				<spring:message code="nonConformityConfiguration.browse.title" />
			</a></li>
		<%
			}
		%>
		<%
			if (adminFields.useField(Field.SampleDomainMenu)) {
		%>
		<li><a href=<c:url value="/SampleDomainMenu.do"/> >
				<spring:message code="sampledomain.browse.title" />
			</a></li>
		<%
			}
		%>
		<%
			if (adminFields.useField(Field.ScriptletMenu)) {
		%>
		<li><a href=<c:url value="/ScriptletMenu.do"/> >
				<spring:message code="scriptlet.browse.title" />
			</a></li>
		<%
			}
		%>
		<%
			if (adminFields.useField(Field.SourceOfSampleMenu)) {
		%>
		<li><a href=<c:url value="/SourceOfSampleMenu.do"/> >
				<spring:message code="sourceofsample.browse.title" />
			</a></li>
		<%
			}
		%>
		<%
			if (adminFields.useField(Field.StatusOfSampleMenu)) {
		%>
		<li><a href=<c:url value="/StatusOfSampleMenu.do"/> >
				<spring:message code="statusofsample.browse.title" />
			</a></li>
		<%
			}
		%>
		<%
			if (adminFields.useField(Field.TestMenu)) {
		%>
		<li><a href=<c:url value="/TestMenu.do"/> >
				<spring:message code="test.browse.title" />
			</a></li>
		<%
			}
		%>
		<%
			if (adminFields.useField(Field.TestAnalyteMenu)) {
		%>
		<li><a href=<c:url value="/TestAnalyteMenu.do"/> >
				<spring:message code="testanalyte.browse.title" />
			</a></li>
		<%
			}
		%>
		<%
			if (adminFields.useField(Field.TestReflexMenu)) {
		%>
		<li><a href=<c:url value="/TestReflexMenu.do"/> >
				<spring:message code="testreflex.browse.title" />
			</a></li>
		<%
			}
		%>
		<%
			if (adminFields.useField(Field.TestResultMenu)) {
		%>
		<li><a href=<c:url value="/TestResultMenu.do"/> >
				<spring:message code="testresult.browse.title" />
			</a></li>
		<%
			}
		%>
		<%
			if (adminFields.useField(Field.TestSectionMenu)) {
		%>
		<li><a href=<c:url value="/TestSectionMenu.do"/> >
				<spring:message code="testsection.browse.title" />
			</a></li>
		<%
			}
		%>
		<%
			if (adminFields.useField(Field.TestTrailerMenu)) {
		%>
		<li><a href=<c:url value="/TestTrailerMenu.do"/> >
				<spring:message code="testtrailer.browse.title" />
			</a></li>
		<%
			}
		%>
		<%
			if (adminFields.useField(Field.TypeOfSampleMenu)) {
		%>
		<li><a href=<c:url value="/TypeOfSampleMenu.do"/> >
				<spring:message code="typeofsample.browse.title" />
			</a></li>
		<%
			}
		%>
		<%
			if (adminFields.useField(Field.TypeOfSamplePanelMenu)) {
		%>
		<li><a href=<c:url value="/TypeOfSamplePanelMenu.do"/> >
				<spring:message code="typeofsample.panel" />
			</a></li>
		<%
			}
		%>
		<%
			if (adminFields.useField(Field.TypeOfSampleTestMenu)) {
		%>
		<li><a href=<c:url value="/TypeOfSampleTestMenu.do"/> >
				<spring:message code="typeofsample.test" />
			</a></li>
		<%
			}
		%>
		<%
			if (adminFields.useField(Field.TypeOfTestResultMenu)) {
		%>
		<li><a href=<c:url value="/TypeOfTestResultMenu.do"/> >
				<spring:message code="typeoftestresult.browse.title" />
			</a></li>
		<%
			}
		%>
		<%
			if (adminFields.useField(Field.UnitOfMeasureMenu)) {
		%>
		<li><a href=<c:url value="/UnitOfMeasureMenu.do"/> >
				<spring:message code="unitofmeasure.browse.title" />
			</a></li>
		<%
			}
		%>
		<%
			if (adminFields.useField(Field.TestAnalyteTestResult)) {
		%>
		<li><a href=<c:url value="/TestAnalyteTestResult.do"/> >
				<spring:message code="testanalytetestresult.browse.title" />
			</a></li>
		<%
			}
		%>
		<%
			if (adminFields.useField(Field.TestUsageAggregatation)) {
		%>
		<li><a href=<c:url value="/TestUsageConfiguration.do"/> >
				<spring:message code="testusageconfiguration.browse.title" />
			</a></li>
		<%
			}
		%>
		<%
			if (adminFields.useField(Field.RESULT_REPORTING_CONFIGURATION)) {
		%>
		<li><a href=<c:url value="/ResultReportingConfiguration.do"/> >
				<spring:message code="resultreporting.browse.title" />
			</a></li>
		<%
			}
		%>
		
		<li><a href=<c:url value="/BarcodeConfiguration.do"/> >
				<spring:message code="barcodeconfiguration.browse.title" />
		</a></li>
		
		<li><a href=<c:url value="/ListPlugins.do"/> >
				<spring:message code="plugin.menu.list.plugins" />
			</a></li>
		<hr>
		<%
			if (adminFields.useField(Field.SystemUserModuleMenu)) {
		%>
		<li><a href=<c:url value="/SystemUserModuleMenu.do"/> >
				<spring:message code="systemusermodule.browse.title" />
			</a></li>
		<%
			}
		%>
		<%
			if (permissionBase.equals("USER")) {
		%>
		<li><a href=<c:url value="/SystemUserSectionMenu.do"/> >
				<spring:message code="systemusersection.browse.title" />
			</a></li>
		<%
			} else if (permissionBase.equals("ROLE")) {
		%>
		<li><a href=<c:url value="/UnifiedSystemUserMenu.do"/> >
				<spring:message code="unifiedSystemUser.browser.title" />
			</a></li>
		<%
			}
		%>
	</ul>
</table>
