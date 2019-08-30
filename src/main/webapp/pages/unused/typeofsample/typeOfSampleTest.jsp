<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="org.openelisglobal.common.action.IActionConstants" %>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

 

<%!

String allowEdits = "true";

%>

<%
if (request.getAttribute(IActionConstants.ALLOW_EDITS_KEY) != null) {
 allowEdits = (String)request.getAttribute(IActionConstants.ALLOW_EDITS_KEY);
}

%>

<script>
function validateForm(form) {
	return !$("sampleId").value.blank() && !$("testId").value.blank();
}



</script>

<table>
		<tr>
						<td class="label">
							<spring:message code="typeofsample.test.sample"/>:<span class="requiredlabel">*</span>
						</td>	
						<td>
						<html:select name="${form.formName}" property="sample" id="sampleId">
					   	  <app:optionsCollection 
										name="${form.formName}" 
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
							<spring:message code="typeofsample.test.test"/>:<span class="requiredlabel">*</span>
						</td>	
						<td> 
					   	<html:select name="${form.formName}" property="test" id="testId" >
					   	  <app:optionsCollection 
										name="${form.formName}" 
							    		property="tests" 
										label="testName" 
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


