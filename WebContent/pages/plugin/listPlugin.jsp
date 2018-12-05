<%@ page language="java" contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants"%>
<%@ page isELIgnored="false"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- <%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%> --%>
<%@ taglib uri="/tags/labdev-view" prefix="app"%>

<%-- <bean:define id="formName" value='<%=(String) request.getAttribute(IActionConstants.FORM_NAME)%>' /> --%>
<c:set var="formName" value="${form.formName}" />

<h3><spring:message code="plugin.installed.plugins" text="plugin.installed.plugins"/></h3>

<ul>
	<c:forEach items="${form.pluginList}" var="pluginNames">
		<li>
			${pluginNames}
		</li>
	</c:forEach>
<%--     <logic:iterate id="pluginNames" name="<%=formName%>" property="pluginList">
		<li>
		<bean:write name="pluginNames" />
		</li>
	</logic:iterate> --%>
</ul>
