<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="org.openelisglobal.common.action.IActionConstants, 
			org.openelisglobal.internationalization.MessageUtil" %>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" uri="/tags/labdev-view" %>
<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>
<%--bugzilla 2108 added required asterisks--%>

<div id="sound"></div>

 



<%!

String allowEdits = "true";
//bugzilla 1494
String errorNewLine = "";
%>

<%
if (request.getAttribute(IActionConstants.ALLOW_EDITS_KEY) != null) {
 allowEdits = (String)request.getAttribute(IActionConstants.ALLOW_EDITS_KEY);
}
//bugzilla 1494
errorNewLine = MessageUtil.getMessage("error.dictionary.newlinecharacter");
                    
%>

<script>
function validateForm(form) {
	//TO DO reintroduce validation
   var validated = true;//validateDictionaryForm(form);
    //validation for no new line characters
    if (validated) {
      var dictEntry = document.getElementById("dictEntry").value;
      if (containsNewLine(dictEntry)) {
         alert('<%=errorNewLine%>'); 
         validated = false;
      }
    }
   return validated;
}
</script>
<%--bugzilla 2061-2063--%>
<form:hidden path="dirtyFormFields" id="dirtyFormFields"/>
<table>
		<tr>
						<td class="label">
							<spring:message code="dictionary.id"/>:
						</td>	
						<td> 
							<form:input path="id" readonly="true"/> 
						</td>
		</tr>
		<tr>
						<td class="label">
							<spring:message code="dictionary.dictionarycategory"/>:<span class="requiredlabel">*</span>
						</td>	
						<td>
						  <form:select path="selectedDictionaryCategoryId">
						  <option></option>
					   	     <form:options items="${form.categories}" 
										itemLabel="description" 
										itemValue="id"  />
					        
                       	  </form:select>
                       	</td>
						</tr>
        <tr>
						<td class="label">
							<spring:message code="dictionary.isActive"/>:<span class="requiredlabel">*</span>
						</td>	
						<td width="1"> 
							<form:input path="isActive" size="1" onblur="this.value=this.value.toUpperCase()" />
						</td>
		</tr>
		<tr>
						<td class="label">
							<spring:message code="dictionary.dictEntry"/>:<span class="requiredlabel">*</span>
						</td>	
						<td> 
							<%--html:text name="${form.formName}" property="dictEntry" /--%>
						    <form:textarea path="dictEntry" id="dictEntry" cols="50" rows="4"/>
						</td>
		</tr>
        <%--bugzilla 1847--%>
		<tr>
						<td class="label">
							<spring:message code="dictionary.localAbbreviation"/>:
						</td>	
						<td> 
						    <form:input path="localAbbreviation" size="10" onblur="this.value=this.value.toUpperCase()" />
						</td>
		</tr>
	
 		<tr>
		<td>&nbsp;</td>
		</tr>
</table>

 
<!-- <html:javascript formName="dictionaryForm"/> -->


