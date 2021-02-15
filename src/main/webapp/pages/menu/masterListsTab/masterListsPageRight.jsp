
<%@ page isELIgnored="false" 
	import="org.openelisglobal.common.action.IActionConstants"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


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
<c:if test="${menuDef == 'AnalyzerTestNameMenuDefinition'}">
  <tiles:insertAttribute name="rightAnalyzerTestName" />
</c:if>
<c:if test="${menuDef == 'DictionaryMenuDefinition'}">
  <tiles:insertAttribute name="rightDictionary" />
</c:if>
<c:if test="${menuDef == 'OrganizationMenuDefinition'}">
  <tiles:insertAttribute name="rightOrganization" />
</c:if>
<c:if test="${menuDef == 'PatientTypeMenuDefinition'}">
  <tiles:insertAttribute name="rightPatientType" />
</c:if>
<c:if test="${menuDef == 'ResultLimitsMenuDefinition'}">
  <tiles:insertAttribute name="rightResultLimits" />
</c:if>
<c:if test="${menuDef == 'RoleMenuDefinition'}">
  <tiles:insertAttribute name="rightRole" />
</c:if>
<c:if test="${menuDef == 'SiteInformationMenuDefinition'}">
  <tiles:insertAttribute name="rightSiteInformation" />
</c:if>
<c:if test="${menuDef == 'TestSectionMenuDefinition'}">
  <tiles:insertAttribute name="rightTestSection" />
</c:if>
<c:if test="${menuDef == 'TypeOfSamplePanelMenuDefinition'}">
  <tiles:insertAttribute name="rightTypeOfSamplePanel" />
</c:if>
<c:if test="${menuDef == 'TypeOfSampleTestMenuDefinition'}">
  <tiles:insertAttribute name="rightTypeOfSampleTest" />
</c:if>
<c:if test="${menuDef == 'ExternalConnectionMenuDefinition'}">
  <tiles:insertAttribute name="rightExternalConnection" />
</c:if>
<c:if test="${menuDef == 'UserRoleMenuDefinition'}">
  <tiles:insertAttribute name="rightUserRole" />
</c:if>
<c:if test="${menuDef == 'UnifiedSystemUserMenuDefinition'}">
  <tiles:insertAttribute name="rightSystemUserOnePage" />
</c:if>
<c:if test="${menuDef == 'TestNotificationMenuDefinition'}">
  <tiles:insertAttribute name="rightTestNotification" />
</c:if>
<c:if test="${menuDef == 'default'}">
<tiles:insertAttribute name="right"/>
</c:if>
</td>
</tr>
</table>
</center>

