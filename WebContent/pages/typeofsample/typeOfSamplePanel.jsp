<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants" %>

<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/labdev-view" prefix="app" %>

<bean:define id="formName" value='<%= (String)request.getAttribute(IActionConstants.FORM_NAME) %>' />

<%!

String allowEdits = "true";

%>

<%
if (request.getAttribute(IActionConstants.ALLOW_EDITS_KEY) != null) {
 allowEdits = (String)request.getAttribute(IActionConstants.ALLOW_EDITS_KEY);
}

%>

<script language="JavaScript1.2">
function validateForm(form) {
	return !$("sampleId").value.blank() && !$("panelId").value.blank();
}



</script>

<table>
		<tr>
						<td class="label">
							<bean:message key="typeofsample.test.sample"/>:<span class="requiredlabel">*</span>
						</td>	
						<td>
						<html:select name="<%=formName%>" property="sample" styleId="sampleId">
					   	  <app:optionsCollection 
										name="<%=formName%>" 
							    		property="samples" 
										label="description" 
										value="id"  
							   			allowEdits="true"
							/> 
						</html:select>
						</td>
		 </tr>
 		<tr>
						<td class="label">
							<bean:message key="typeofsample.panel.panel"/>:<span class="requiredlabel">*</span>
						</td>	
						<td> 
					   	<html:select name="<%=formName%>" property="panel" styleId="panelId" >
					   	  <app:optionsCollection 
										name="<%=formName%>" 
							    		property="panels" 
										label="panelName" 
										value="id"  
							   			allowEdits="true"
							/>
                        
					   </html:select>

						</td>
		</tr>
     	<tr>
		<td>&nbsp;</td>
		</tr>
</table>


