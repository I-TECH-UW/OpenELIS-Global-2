<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants" %>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" uri="/tags/labdev-view" %>
<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>


 

<%!

String allowEdits = "true";
String errorMakeSelection = "";

%>

<%
if (request.getAttribute(IActionConstants.ALLOW_EDITS_KEY) != null) {
 allowEdits = (String)request.getAttribute(IActionConstants.ALLOW_EDITS_KEY);
}

java.util.Locale locale = (java.util.Locale)request.getSession().getAttribute(org.apache.struts.Globals.LOCALE_KEY);
errorMakeSelection =
					us.mn.state.health.lims.common.util.resources.ResourceLocator.getInstance().getMessageResources().getMessage(
					locale,
                    "codeelementxref.validation.makeselection");
                    

%>

<script>
function validateForm(form) {
 return validateCodeElementXrefForm(form);
}

function clearSearchResults() {
     var objSelect = document.getElementById("selectedLocalCodeElementId");
     if (objSelect.options != null && objSelect.options.length > 0) {
        objSelect.options.length = 0;
     }
     
     objSelect = document.getElementById("selectedReceiverCodeElementId");
     if (objSelect.options != null && objSelect.options.length > 0) {
        objSelect.options.length = 0;
     }

}

function getCodes() {
   var moId = document.getElementById("selectedMessageOrganizationId");
   var cetId = document.getElementById("selectedCodeElementTypeId");
        if (moId.value != '' && cetId.value != '') {
            setAction(window.document.forms[0], 'View', 'no', '');
        } else {
            alert('<%=errorMakeSelection%>');
        }
}

</script>
<table width="100%">
  <tr> 
    <td width="13%">&nbsp;</td>
    <td width="33%"><h2><spring:message code="codeelementxref.messageOrganization"/></h2></td>
    <td width="33%"><h2><spring:message code="codeelementxref.codeElementType"/></h2></td>
    <td width="5%">&nbsp;</td>
    <td width="16%">&nbsp;</td>
  </tr>
  <tr>
  	<td width="13%"> 
      &nbsp;
	</td>
	<td width="33%"> 
	 	<html:select name="${form.formName}" property="selectedMessageOrganizationId" onchange="clearSearchResults();">
		   	  <app:optionsCollection 
					name="${form.formName}"
		    		property="messageOrganizations" 
					label="organization.organizationName" 
    				value="id"  
		        	filterProperty="isActive" 
	            	filterValue="N"
		   			allowEdits="true"
		    	/>
         </html:select>
	</td>
	<td width="33%"> 
		<html:select name="${form.formName}" property="selectedCodeElementTypeId" onchange="clearSearchResults();">
		  	  <app:optionsCollection 
		    		name="${form.formName}"
			   		property="codeElementTypes" 
					label="text" 
					value="id"  
					allowEdits="true"
			  />
        </html:select>
   </td>
   <td width="5%"> 
      &nbsp;
   </td>
   <td width="16%">
    <html:button  onclick="getCodes();" property="view" disabled="<%=Boolean.valueOf(allowEdits).booleanValue()%>">
	    <spring:message code="codeelementxref.button.getcodes"/>
    </html:button>    
	</td>
  </tr>
  <tr height="22">
    <td>
      &nbsp;
    </td>
  </tr>
</table>


<app:javascript formName="codeElementXrefForm"/>

