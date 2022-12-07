
<%@ page isELIgnored="false" 
	import="org.openelisglobal.common.action.IActionConstants"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

<c:set var="success" value="${success || param.forward == 'success'}" />
<c:if test="${success || (not empty requestScope[Constants.SUCCESS_MSG])}">

<script type="text/javascript">
function /*void*/ showSuccessMessage( show ){
	$("successMsg").style.visibility = show ? 'visible' : 'hidden';
}
</script>

<div id="successMsg" 
     style="text-align:center; 
     		color:seagreen;  
     		width : 100%;
     		font-size:170%;
     		display:hidden;" >
	 		
	<c:if test="${empty successMessage}">
		<spring:message code="save.success"/>
	</c:if>
	<c:if test="${not empty successMessage}">
		<c:out value="${successMessage}"/>
	</c:if>
	
</div>
</c:if>


<c:set var="menuDef" value="${menuDefinition}" />
<center>
<table cellpadding="0" cellspacing="0" width="100%" height="100%" border="0">
<tr>
<td>
 <jsp:include page="${rightMenuFragment}"/>
<%-- <c:if test="${menuDef == 'AnalyzerTestNameMenuDefinition'}"> --%>
<%--   <jsp:include page="${rightAnalyzerTestNameFragment}"/> --%>
<%-- </c:if> --%>
<%-- <c:if test="${menuDef == 'DictionaryMenuDefinition'}"> --%>
<%--   <jsp:include page="${rightDictionaryFragment}"/> --%>
<%-- </c:if> --%>
<%-- <c:if test="${menuDef == 'OrganizationMenuDefinition'}"> --%>
<%--   <jsp:include page="${rightOrganizationFragment}"/> --%>
<%-- </c:if> --%>
<%-- <c:if test="${menuDef == 'ProviderMenuDefinition'}"> --%>
<%--   <jsp:include page="${rightProviderFragment}"/> --%>
<%-- </c:if> --%>
<%-- <c:if test="${menuDef == 'PatientTypeMenuDefinition'}"> --%>
<%--   <jsp:include page="${rightPatientTypeFragment}"/> --%>
<%-- </c:if> --%>
<%-- <c:if test="${menuDef == 'ResultLimitsMenuDefinition'}"> --%>
<%--   <jsp:include page="${rightResultLimitsFragment}"/> --%>
<%-- </c:if> --%>
<%-- <c:if test="${menuDef == 'RoleMenuDefinition'}"> --%>
<%--   <jsp:include page="${rightRoleFragment}"/> --%>
<%-- </c:if> --%>
<%-- <c:if test="${menuDef == 'SiteInformationMenuDefinition'}"> --%>
<%--   <jsp:include page="${rightSiteInformationFragment}"/> --%>
<%-- </c:if> --%>
<%-- <c:if test="${menuDef == 'TestSectionMenuDefinition'}"> --%>
<%--   <jsp:include page="${rightTestSectionFragment}"/> --%>
<%-- </c:if> --%>
<%-- <c:if test="${menuDef == 'TypeOfSamplePanelMenuDefinition'}"> --%>
<%--   <jsp:include page="${rightTypeOfSamplePanelFragment}"/> --%>
<%-- </c:if> --%>
<%-- <c:if test="${menuDef == 'TypeOfSampleTestMenuDefinition'}"> --%>
<%--   <jsp:include page="${rightTypeOfSampleTestFragment}"/> --%>
<%-- </c:if> --%>
<%-- <c:if test="${menuDef == 'ExternalConnectionMenuDefinition'}"> --%>
<%--   <jsp:include page="${rightExternalConnectionFragment}"/> --%>
<%-- </c:if> --%>
<%-- <c:if test="${menuDef == 'UserRoleMenuDefinition'}"> --%>
<%--   <jsp:include page="${rightUserRoleFragment}"/> --%>
<%-- </c:if> --%>
<%-- <c:if test="${menuDef == 'UnifiedSystemUserMenuDefinition'}"> --%>
<%--   <jsp:include page="${rightSystemUserOnePageFragment}"/> --%>
<%-- </c:if> --%>
<%-- <c:if test="${menuDef == 'TestNotificationMenuDefinition'}"> --%>
<%--   <jsp:include page="${rightTestNotificationFragment}"/> --%>
<%-- </c:if> --%>
<%-- <c:if test="${menuDef == 'default'}"> --%>
<%--   <jsp:include page="${rightDefaultFragment}"/> --%>
<%-- </c:if> --%>
<%-- <c:if test="${menuDef != 'default'}"> --%>
<%--   <jsp:include page="${rightDefaultFragment}"/> --%>
<%-- </c:if> --%>
</td>
</tr>
</table>
</center>

