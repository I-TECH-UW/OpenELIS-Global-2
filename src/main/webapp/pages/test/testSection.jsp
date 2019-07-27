<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="org.openelisglobal.common.action.IActionConstants" %>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" uri="/tags/labdev-view" %>
<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>


<div id="sound"></div>

 


<%!

String allowEdits = "true";
String path = "";
String basePath = "";
%>
<%
path = request.getContextPath();
basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

if (request.getAttribute(IActionConstants.ALLOW_EDITS_KEY) != null) {
 allowEdits = (String)request.getAttribute(IActionConstants.ALLOW_EDITS_KEY);
}

%>

<script>
function validateForm(form) {
 return validateTestSectionForm(form);
}
</script>

<table>
		<tr>
						<td class="label">
							<spring:message code="testsection.id"/>:
						</td>	
						<td> 
							<app:text name="${form.formName}" property="id" allowEdits="false"/>
						</td>
		</tr>
		
	   <%-- bugzilla 2025 --%>
		<tr>
		                <td class="label">
		                   <spring:message code="testsection.parent"/>
		                </td>
		              <td>
		                    <html:text id="parentTestSectionName" size="20" name="${form.formName}" property="parentTestSectionName" />
		                    <span id="indicator2" style="display:none;"><img src="<%=basePath%>images/indicator.gif"/></span> 
		                    <input id="selectedParentTestSectionName" name="selectParentTestSectionName" type="hidden" size="20" />
		               </td>  
		</tr>                   
		 
		<tr>
						<td class="label">
							<spring:message code="testsection.testSectionName"/>:<span class="requiredlabel">*</span>
						</td>	
						<td> 
							<form:input path="testSectionName" />
						</td>
		</tr>

		 <tr>
						<td class="label">
							<spring:message code="testsection.organization"/>:<span class="requiredlabel">*</span>
						</td>	
						<td>
						
					   	  <html:text id="organizationName" size="30" name="${form.formName}" property="organizationName" /> 
					   	  <span id="indicator1" style="display:none;"><img src="<%=basePath%>images/indicator.gif"/></span>
	   			          <input id="selectedOrganizationId" name="selectedOrganizationId" type="hidden" size="30" />
						<%--html:select name="${form.formName}" property="selectedOrganizationId">
					   	  <app:optionsCollection 
										name="${form.formName}" 
							    		property="organizations" 
										label="organizationName" 
										value="id"  
							        	allowEdits="true"
							/>
                       </html:select--%>
                      
						</td>
		</tr>
        <tr>
						<td class="label">
							<spring:message code="testsection.isExternal"/>:<span class="requiredlabel">*</span>
						</td>	
						<td> 
							<form:input path="isExternal" size="1" onblur="this.value=this.value.toUpperCase()"/>
						</td>
		</tr>
		<tr>
						<td class="label">
							<spring:message code="testsection.description"/>:<span class="requiredlabel">*</span>
						</td>	
						<td> 
							<form:input path="description" />
						</td>
		</tr>
	
 		<tr>
		<td>&nbsp;</td>
		</tr>
</table>

 
  <ajax:autocomplete
  source="organizationName"
  target="selectedOrganizationId"
  baseUrl="ajaxAutocompleteXML"
  className="autocomplete"
  parameters="organizationName={organizationName},provider=OrganizationAutocompleteProvider,fieldName=organizationName,idName=id"
  indicator="indicator1"
  minimumCharacters="1" />
  
  <%-- bugzilla 2025 --%>
   <ajax:autocomplete
  source="parentTestSectionName"
  target="selectedParentTestSectionName"
  baseUrl="ajaxAutocompleteXML"
  className="autocomplete"
  parameters="testSectionName={parentTestSectionName},provider=TestSectionAutocompleteProvider,fieldName=testSectionName,idName=id"
  indicator="indicator2"
  minimumCharacters="1" />

<html:javascript formName="testSectionForm"/>


