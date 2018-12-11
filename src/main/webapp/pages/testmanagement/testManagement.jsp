<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants, 
			us.mn.state.health.lims.common.util.SystemConfiguration" %>  
	
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/labdev-view" prefix="app"%>
<%@ taglib uri="/tags/sourceforge-ajax" prefix="ajax"%>

<%--bugzilla 1510 add styleId for compatibility in firefox and for use of firebug debugger--%>

<bean:define id="formName" value='<%= (String)request.getAttribute(IActionConstants.FORM_NAME) %>' />
<bean:define id="idSeparator" value='<%= SystemConfiguration.getInstance().getDefaultIdSeparator() %>' />
<bean:define id="humanDomain" value='<%= SystemConfiguration.getInstance().getHumanDomain() %>' />
<%--bugzilla 2227 amend tests--%>
<bean:define id="accessionNumberParm" value='<%= IActionConstants.ACCESSION_NUMBER%>' />

<%!String allowEdits = "true";

	String accnNumb = "";
	String errorMessageAccessionNumber = "";	
	
	String quickEntry = "";
	String errorMessageLabelPrinted = "";

	%>

<%if (request.getAttribute(IActionConstants.ALLOW_EDITS_KEY) != null) {
				allowEdits = (String) request
						.getAttribute(IActionConstants.ALLOW_EDITS_KEY);
			}

			request.setAttribute(IActionConstants.ALLOW_EDITS_KEY, allowEdits);

			java.util.Locale locale = (java.util.Locale) request.getSession()
					.getAttribute(org.apache.struts.Globals.LOCALE_KEY);
			accnNumb = us.mn.state.health.lims.common.util.resources.ResourceLocator
					.getInstance().getMessageResources().getMessage(locale,
							"quick.entry.accession.number");
			quickEntry = us.mn.state.health.lims.common.util.resources.ResourceLocator
					.getInstance().getMessageResources().getMessage(locale,
							"quick.entry.title");				
							
			errorMessageAccessionNumber = us.mn.state.health.lims.common.util.resources.ResourceLocator
					.getInstance().getMessageResources().getMessage(locale,
							"errors.invalid", accnNumb);
							
			errorMessageLabelPrinted = us.mn.state.health.lims.common.util.resources.ResourceLocator
					.getInstance().getMessageResources().getMessage(locale,
							"error.testmanagement.entrystatus", quickEntry);

			%>


<script language="JavaScript1.2">


function pageOnLoad() {
    var accnNumb = $("accessionNumber");
}

function setAccessionNumberValidationMessage(message, field) {
      idField = $(field);
      if (message == "invalid") { 
        //bugzilla 2041 based on results of accession validation we might need to disable buttons
        setButtons(true); 
       	alert('<%=errorMessageAccessionNumber%>'); 
      } else if (message == "invalidStatus") {
        //bugzilla 2041 based on results of accession validation we might need to disable buttons
        setButtons(true);
     	alert('<%=errorMessageLabelPrinted%>');
      } else {
        //bugzilla 2041 based on results of accession validation we might need to disable buttons
        setButtons(false); 
        //bugzilla 1774 after selecting accn# we go to a View Action     
        setAction(window.document.forms[0], 'ViewSampleDemographicsAnd', 'yes', '');
      }

}

function processAccessionNumberValidationSuccess(xhr) {

  var message = xhr.responseXML.getElementsByTagName("message")[0];
  var formfield = xhr.responseXML.getElementsByTagName("formfield")[0];
  //alert("I am in parseMessage and this is message, formfield " + message + " " + formfield);
  setAccessionNumberValidationMessage(message.childNodes[0].nodeValue, formfield.childNodes[0].nodeValue);
}

function processFailure(xhr) {
  //ajax call failed
}

//bugzilla 1942: fixed bug passing wrong parm to validator
function validateAccessionNumber() {
     new Ajax.Request (
       'ajaxXML',  //url
       {//options
        method: 'get', //http method
        parameters: 'provider=AccessionNumberValidationProvider&form=' + document.forms[0].name + '&field=accessionNumber&id=' + escape($F("accessionNumber")),      //request parameters
        //indicator: 'throbbing'
        onSuccess:  processAccessionNumberValidationSuccess,
        onFailure:  processFailure
       }
     );
}


function validateForm(form) {    
    return true;
}

//bugzilla 2227
function resultsEntryHistoryBySamplePopup () {

  var form = document.forms[0];
  var accessionNumber = $F("accessionNumber");
  //if there is an error on the page we cannot go to add test
   //clear button clicked flag to allow add test again
   //if (isSaveEnabled() != true) {
       //clearAddTestClicked();
   //}
   
    //if  no errors otherwise on page -> go to add test popup
	var context = '<%= request.getContextPath() %>';
	var server = '<%= request.getServerName() %>';
	var port = '<%= request.getServerPort() %>';
	var scheme = '<%= request.getScheme() %>';
	
	
	var hostStr = scheme + "://" + server;
	if ( port != 80 && port != 443 )
	{
		hostStr = hostStr + ":" + port;
	}
	hostStr = hostStr + context;

	// Get the sessionID
	 var sessionid = '';

	 var sessionIndex = form.action.indexOf(';');
	 if(sessionIndex >= 0){
		 var queryIndex = form.action.indexOf('?');
		 var length = form.action.length;
		 if (queryIndex > sessionIndex) {
		 	length = queryIndex;
		 }
		 sessionid = form.action.substring(sessionIndex,length);
	 }
 
    var param = '?' + '<%=accessionNumberParm%>' + '=' + accessionNumber;
 	var href = context + "/ResultsEntryHistoryBySamplePopup.do" + param + sessionid;
    //alert("href "+ href);
	
	createPopup( href, 1250, 500 );
}

</script>


<table>
	<tr>
		<td valign="top">
			<bean:message key="sample.accessionNumber" />
			: <span class="requiredlabel">*</span>
		</td>
		<td valign="top">
			<app:text name="<%=formName%>" property="accessionNumber" allowEdits="true" maxlength="10" onkeypress="return noenter()" styleId="accessionNumber"/>

		</td>
		<td valign="top">

			<html:button onclick="validateAccessionNumber();" property="cancel">
				<bean:message key="label.button.display" />
			</html:button>
			&nbsp;
            <%--bugzilla 2227 amend tests--%>
			<logic:equal name="<%=formName%>" property="sampleHasTestRevisions" value="true">
				<html:button onclick="resultsEntryHistoryBySamplePopup();return false;" property="cancel">
				  <bean:message key="testmanagement.label.button.history" />
			    </html:button>
			</logic:equal>
		</td>
	</tr>
</table>




