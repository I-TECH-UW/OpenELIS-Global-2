<%-- <%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %> --%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" uri="/tags/labdev-view" %>
<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<%!
String menuDef = "default";
%>
<%
menuDef = "default";
if (request.getAttribute("menuDefinition'}") != null) {
  menuDef = (String)request.getAttribute("menuDefinition'}");
}
//System.out.println("menuDef " + menuDef);
%>
<%-- <bean:define id="menuDef== '<%=menuDef%>" /> --%>
<c:set var="menuDef" value="<%=menuDef%>" />
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
<c:if test="${menuDef == 'UserRoleMenuDefinition'}">
  <tiles:insertAttribute name="rightUserRole" />
</c:if>
<c:if test="${menuDef == 'UnifiedSystemUserMenuDefinition'}">
  <tiles:insertAttribute name="rightSystemUserOnePage" />
</c:if>
<c:if test="${menuDef == 'default'}">
<tiles:insertAttribute name="right"/>
</c:if>
</td>
</tr>
</table>
</center>