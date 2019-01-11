<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="org.apache.struts.action.*,
			us.mn.state.health.lims.common.action.IActionConstants"
 %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>



<c:if test="${not empty form.formAction}">
<form:form name='${form.formName}' action='${form.formAction}' modelAttribute="form" onsubmit="return submitForm(this);" method="${form.formMethod}">

	<table cellpadding="0" cellspacing="1" width="100%" height="100%" border="0">
	
			<tr valign="top">
				<td>
					<%--tiles:insertAttribute name="error"/--%>
				</td>
			</tr>
			<tr valign="top">
				<td>
					<tiles:insertAttribute name="preSelectionHeader"/>
				</td>
			</tr>
			<tr valign="top">
				<td>
					<tiles:insertAttribute name="header"/>
				</td>
			</tr>
			<tr valign="top">
				<td>
					<tiles:insertAttribute name="body"/>
				</td>
			</tr>
			<tr valign="bottom">
				<td>
					<tiles:insertAttribute name="footer"/>
				</td>
			</tr>

	</table>
</form:form>
</c:if>
<c:if test="${empty form.formAction}">
	<table cellpadding="0" cellspacing="1" width="100%" height="100%" border="0">
	
			<tr valign="top">
				<td>
					<%--tiles:insertAttribute name="error"/--%>
				</td>
			</tr>
			<tr valign="top">
				<td>
					<tiles:insertAttribute name="preSelectionHeader"/>
				</td>
			</tr>
			<tr valign="top">
				<td>
					<tiles:insertAttribute name="header"/>
				</td>
			</tr>
			<tr valign="top">
				<td>
					<tiles:insertAttribute name="body"/>
				</td>
			</tr>
			<tr valign="bottom">
				<td>
					<tiles:insertAttribute name="footer"/>
				</td>
			</tr>

	</table>
</c:if>
