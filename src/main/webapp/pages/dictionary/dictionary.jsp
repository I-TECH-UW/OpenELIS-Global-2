<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants" %>

<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/labdev-view" prefix="app" %>
<%@ taglib uri="/tags/sourceforge-ajax" prefix="ajax"%>
<%--bugzilla 2108 added required asterisks--%>

<div id="sound"></div>

<bean:define id="formName" value='<%= (String)request.getAttribute(IActionConstants.FORM_NAME) %>' />



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
java.util.Locale locale = (java.util.Locale)request.getSession().getAttribute(org.apache.struts.Globals.LOCALE_KEY);
errorNewLine =
					us.mn.state.health.lims.common.util.resources.ResourceLocator.getInstance().getMessageResources().getMessage(
					locale,
                    "error.dictionary.newlinecharacter");
                    
%>

<script language="JavaScript1.2">
function validateForm(form) {
   var validated = validateDictionaryForm(form);
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
<html:hidden property="dirtyFormFields" name="<%=formName%>" styleId="dirtyFormFields"/>
<table>
		<tr>
						<td class="label">
							<bean:message key="dictionary.id"/>:
						</td>	
						<td> 
							<app:text name="<%=formName%>" property="id" allowEdits="false"/>
						</td>
		</tr>
		<tr>
						<td class="label">
							<bean:message key="dictionary.dictionarycategory"/>:<span class="requiredlabel">*</span>
						</td>	
						<td>
						  <html:select name="<%=formName%>" property="selectedDictionaryCategoryId">
					   	     <app:optionsCollection 
										name="<%=formName%>" 
							    		property="categories" 
										label="description" 
										value="id"  />
					        
                       	  </html:select>
                       	</td>
						</tr>
        <tr>
						<td class="label">
							<bean:message key="dictionary.isActive"/>:<span class="requiredlabel">*</span>
						</td>	
						<td width="1"> 
							<html:text name="<%=formName%>" property="isActive" size="1" onblur="this.value=this.value.toUpperCase()" />
						</td>
		</tr>
		<tr>
						<td class="label">
							<bean:message key="dictionary.dictEntry"/>:<span class="requiredlabel">*</span>
						</td>	
						<td> 
							<%--html:text name="<%=formName%>" property="dictEntry" /--%>
						    <html:textarea name="<%=formName%>" property="dictEntry" styleId="dictEntry" cols="50" rows="4"/>
						</td>
		</tr>
        <%--bugzilla 1847--%>
		<tr>
						<td class="label">
							<bean:message key="dictionary.localAbbreviation"/>:
						</td>	
						<td> 
						    <html:text name="<%=formName%>" property="localAbbreviation" size="10" onblur="this.value=this.value.toUpperCase()" />
						</td>
		</tr>
	
 		<tr>
		<td>&nbsp;</td>
		</tr>
</table>

 
<html:javascript formName="dictionaryForm"/>


