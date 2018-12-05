<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants,
	us.mn.state.health.lims.common.util.SystemConfiguration" %>

<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/labdev-view" prefix="app" %>
<%@ taglib uri="/tags/sourceforge-ajax" prefix="ajax"%>


<div id="sound"></div>

<bean:define id="formName" value='<%= (String)request.getAttribute(IActionConstants.FORM_NAME) %>' />
<bean:define id="idSeparator" value='<%= SystemConfiguration.getInstance().getDefaultIdSeparator() %>' />

<%!

String allowEdits = "true";

%>

<%
if (request.getAttribute(IActionConstants.ALLOW_EDITS_KEY) != null) {
 allowEdits = (String)request.getAttribute(IActionConstants.ALLOW_EDITS_KEY);
}
%>
<style>
H1.codeElementXrefH1 {
  font-family: Arial, Helvetica, sans-serif;
  font-size:170%;
  color:#336699;
  background-color : #f7f7e7;
  border : none;
  width : 100%;
  float: right;
  padding-right: 5px;
  valign:top;
}
</style>
<script language="JavaScript1.2">
function validateForm(form) {
 return validateCodeElementXrefForm(form);
}

//check this before showing alert if no receiver or local codes - use prePageOnLoad instead of pageOnLoad
function prePageOnLoad() {
  showHideSections();
}

function showHideSections() {
 var linkedSection = document.getElementById("linked");
 var notLinkedSection = document.getElementById("notlinked");

 var rcvCode = document.getElementById("selectedReceiverCodeElementId");
 var locCode = document.getElementById("selectedLocalCodeElementId");
 
 var selectedRows = window.document.forms[0].elements['selectedRows'];
  
 if (rcvCode && rcvCode.options.length > 1 && locCode && locCode.options.length > 1) {
         //alert("unhiding notLinkedSection");
         notLinkedSection.style.display = 'block';
         notLinkedSection.style.visibility = 'visible';
  } else {
         //alert("hiding notLinkedSection");
         notLinkedSection.style.display = 'none';
         notLinkedSection.style.visibility = 'hidden';
  }
  
 if ((selectedRows != null && selectedRows[0] != null) ||
     (selectedRows != null && selectedRows.value != null)) {
         //alert("unhiding linkedSection");
         linkedSection.style.display = 'block';
         linkedSection.style.visibility = 'visible';
 
  } else {
         //alert("hiding linkedSection");
         linkedSection.style.display = 'none';
         linkedSection.style.visibility = 'hidden';
  }

}

function link() {
    var selectedLocalElementId = document.getElementById("selectedLocalCodeElementId");
    var selectedReceiverElementId = document.getElementById("selectedReceiverCodeElementId");
    if (selectedLocalElementId.value != null && selectedLocalElementId.value != '' 
        && selectedReceiverElementId.value != null && selectedReceiverElementId.value != '') {
            var href = '?selectedLocalElementId=' + selectedLocalElementId + '&selectedReceiverElementId=' + selectedReceiverElementId;
            setAction(window.document.forms[0], 'Link', 'yes', href);
    } 
}


function unlink() {

  var fieldObj = window.document.forms[0].elements['selectedRows'];
  var listOfSelectedIds = '';
  
  if (fieldObj != null) {
    //If only one checkbox
    if (fieldObj[0] == null) {
       if (fieldObj.value != null && fieldObj.checked == true) {
         listOfSelectedIds += fieldObj.value;
       }
   } else {
      for (var i = 0; i < fieldObj.length; i++) {
         if (fieldObj[i].checked == true) {
            listOfSelectedIds += fieldObj[i].value;
            listOfSelectedIds  += '<%=idSeparator%>'
         }
       }
    }
  }

  if (listOfSelectedIds != '') {
          setAction(window.document.forms[0], 'UnLink', 'yes', '');
   }
}
</script>
<div id="notlinked">
<table width="100%">
  <tr> 
    <td width="13%">
     <h1 class="codeElementXrefH1">
      <bean:message key="codeelementxref.label.notlinked"/>:
     </h1>
    </td>
    <td width="33%"><h2><bean:message key="codeelementxref.receiverCodeElements"/></h2></td>
    <td width="33%"><h2><bean:message key="codeelementxref.localCodeElements"/></h2></td>
    <td width="5%">&nbsp;</td>
    <td width="16%">&nbsp;</td>
  </tr>
  <tr>
  	<td width="13%"> 
      &nbsp;
	</td>
	<td width="33%"> 
		 <html:select name="<%=formName%>" property="selectedReceiverCodeElementId">
		   	  <app:optionsCollection 
				name="<%=formName%>"
	     		property="receiverCodeElements" 
				label="text" 
				value="id"
		       	allowEdits="true"
		  />
          </html:select>
    </td>
	<td width="33%"> 
		 <html:select name="<%=formName%>" property="selectedLocalCodeElementId">
		   	  <app:optionsCollection 
				name="<%=formName%>"
	     		property="localCodeElements" 
				label="name" 
				value="id"  
				filterProperty="isActive"  
		      	filterValue="N"
				allowEdits="true"
		  	/>
          </html:select>
   </td>
   <td width="5%"> 
      &nbsp;
   </td>
   <td width="16%">
        <html:button  onclick="link();" property="save" disabled="<%=Boolean.valueOf(allowEdits).booleanValue()%>">
	        <bean:message key="codeelementxref.button.link"/>
        </html:button>
	</td>
  </tr>
  <tr height="22">
    <td>
      &nbsp;
    </td>
  </tr>
</table>
</div>
<div id="linked">
<table width="100%">
  <tr valign="middle"> 
    <td width="13%"><h1 class="codeElementXrefH1"><bean:message key="codeelementxref.label.linked"/>:</h1></td>
    <td width="33%"><h2><bean:message key="codeelementxref.receiverCodeElement"/></h2></td>
    <td width="33%"><h2><bean:message key="codeelementxref.localCodeElement"/></h2></td>
    <td width="5%">&nbsp;</td>
    <td width="16%">&nbsp;</td>
  </tr>
  <logic:iterate id="codeXref" name="<%=formName%>" indexId="codexref_ctr" property="codeElementXrefs" type="us.mn.state.health.lims.codeelementxref.valueholder.CodeElementXref">
  <%--for hover over information--%>
  <bean:define id="receiver" name="codeXref" property="receiverCodeElement"/>
  <bean:define id="identifier" name="receiver" property="identifier"/>
  <bean:define id="codeSystem" name="receiver" property="codeSystem"/>
  <bean:define id="local" name="codeXref" property="localCodeElement"/>
  <bean:define id="localCodeElementId" name="local" property="id"/>
  
  <tr valign="middle">
  	<td width="13%"> 
      &nbsp;
	</td>
	<td width="33%"> 
      <a class="hoverinformation" href="" title='<%=identifier + " " + codeSystem%>' onclick="return false;">
		<bean:write name="receiver" property="text"/>
	  </a>
	</td>
	<td width="33%"> 
	  <a class="hoverinformation" href="" title='<%=localCodeElementId%>' onclick="return false;">
		<bean:write name="local" property="name"/>
	  </a>
	</td>
	<td width="5%">
	  <html:multibox name='<%=formName%>' property="selectedRows" style="float:right; padding-right: 5px;">
	      <bean:write name="codeXref" property="id"/>
	  </html:multibox>
	</td>
	<td width="16%">
     <logic:equal name="codexref_ctr" value="0">
        <html:button  onclick="unlink();" property="save" disabled="<%=Boolean.valueOf(allowEdits).booleanValue()%>">
	        <bean:message key="codeelementxref.button.unlink"/>
        </html:button>        
	  </logic:equal>
	</td>

  </tr>
  </logic:iterate>
</table>
</div>

<app:javascript formName="codeElementXrefForm"/>

