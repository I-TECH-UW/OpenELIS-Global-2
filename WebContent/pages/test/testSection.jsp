<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants" %>

<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/labdev-view" prefix="app" %>
<%@ taglib uri="/tags/sourceforge-ajax" prefix="ajax"%>


<div id="sound"></div>

<bean:define id="formName" value='<%= (String)request.getAttribute(IActionConstants.FORM_NAME) %>' />


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

<script language="JavaScript1.2">
function validateForm(form) {
 return validateTestSectionForm(form);
}
</script>

<table>
		<tr>
						<td class="label">
							<bean:message key="testsection.id"/>:
						</td>	
						<td> 
							<app:text name="<%=formName%>" property="id" allowEdits="false"/>
						</td>
		</tr>
		
	   <%-- bugzilla 2025 --%>
		<tr>
		                <td class="label">
		                   <bean:message key="testsection.parent"/>
		                </td>
		              <td>
		                    <html:text styleId="parentTestSectionName" size="20" name="<%=formName%>" property="parentTestSectionName" />
		                    <span id="indicator2" style="display:none;"><img src="<%=basePath%>images/indicator.gif"/></span> 
		                    <input id="selectedParentTestSectionName" name="selectParentTestSectionName" type="hidden" size="20" />
		               </td>  
		</tr>                   
		 
		<tr>
						<td class="label">
							<bean:message key="testsection.testSectionName"/>:<span class="requiredlabel">*</span>
						</td>	
						<td> 
							<html:text name="<%=formName%>" property="testSectionName" />
						</td>
		</tr>

		 <tr>
						<td class="label">
							<bean:message key="testsection.organization"/>:<span class="requiredlabel">*</span>
						</td>	
						<td>
						
					   	  <html:text styleId="organizationName" size="30" name="<%=formName%>" property="organizationName" /> 
					   	  <span id="indicator1" style="display:none;"><img src="<%=basePath%>images/indicator.gif"/></span>
	   			          <input id="selectedOrganizationId" name="selectedOrganizationId" type="hidden" size="30" />
						<%--html:select name="<%=formName%>" property="selectedOrganizationId">
					   	  <app:optionsCollection 
										name="<%=formName%>" 
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
							<bean:message key="testsection.isExternal"/>:<span class="requiredlabel">*</span>
						</td>	
						<td> 
							<html:text name="<%=formName%>" property="isExternal" size="1" onblur="this.value=this.value.toUpperCase()"/>
						</td>
		</tr>
		<tr>
						<td class="label">
							<bean:message key="testsection.description"/>:<span class="requiredlabel">*</span>
						</td>	
						<td> 
							<html:text name="<%=formName%>" property="description" />
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


