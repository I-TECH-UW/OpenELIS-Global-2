<%@ page language="java" contentType="text/html; charset=utf-8"
         import="us.mn.state.health.lims.common.action.IActionConstants,
				us.mn.state.health.lims.common.formfields.FormFields,
				us.mn.state.health.lims.common.util.*,
	            us.mn.state.health.lims.common.util.ConfigurationProperties.Property,
		        us.mn.state.health.lims.sample.util.AccessionNumberUtil,
		        us.mn.state.health.lims.sample.bean.SampleEditItem" %>

<%@ taglib uri="/tags/struts-bean"		prefix="bean" %>
<%@ taglib uri="/tags/struts-html"		prefix="html" %>
<%@ taglib uri="/tags/struts-logic"		prefix="logic" %>
<%@ taglib uri="/tags/labdev-view"		prefix="app" %>
<%@ taglib uri="/tags/struts-tiles"     prefix="tiles" %>
<%@ taglib uri="/tags/sourceforge-ajax" prefix="ajax"%>

<bean:define id="formName"		value='<%=(String) request.getAttribute(IActionConstants.FORM_NAME)%>' />
<bean:define id="idSeparator"	value='<%=SystemConfiguration.getInstance().getDefaultIdSeparator()%>' />
<bean:define id="accessionFormat" value='<%=ConfigurationProperties.getInstance().getPropertyValue(Property.AccessionFormat)%>' />
<bean:define id="genericDomain" value='' />
<bean:define id="accessionNumber" name="<%=formName %>" property="accessionNumber"/>
<bean:define id="newAccessionNumber" name="<%=formName %>" property="newAccessionNumber"/>
<bean:define id="cancelableResults"   name="<%=formName%>" property="ableToCancelResults" type="java.lang.Boolean" />
<bean:define id="isEditable" name="<%=formName%>" property="isEditable" type="java.lang.Boolean" />

<%!
	String basePath = "";
	int editableAccession = 0;
	int nonEditableAccession = 0;
	int maxAccessionLength = 0;
    boolean useCollectionDate = true;
%>
<%
	String path = request.getContextPath();
	basePath = request.getScheme() + "://" + request.getServerName() + ":"	+ request.getServerPort() + path + "/";
	editableAccession = AccessionNumberUtil.getChangeableLength();
	nonEditableAccession = AccessionNumberUtil.getInvarientLength();
	maxAccessionLength = editableAccession + nonEditableAccession;
    useCollectionDate = FormFields.getInstance().useField( FormFields.Field.CollectionDate);
%>

<script src="scripts/ui/jquery.ui.core.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script src="scripts/ui/jquery.ui.widget.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script src="scripts/ui/jquery.ui.button.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script src="scripts/ui/jquery.ui.menu.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script src="scripts/ui/jquery.ui.position.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script src="scripts/ui/jquery.ui.autocomplete.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script src="scripts/customAutocomplete.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script type="text/javascript" src="<%=basePath%>scripts/utilities.js?ver=<%= Versioning.getBuildNumber() %>" ></script>
<script type="text/javascript" src="<%=basePath%>scripts/ajaxCalls.js?ver=<%= Versioning.getBuildNumber() %>" ></script>
<link rel="stylesheet" href="css/jquery_ui/jquery.ui.all.css?ver=<%= Versioning.getBuildNumber() %>">
<link rel="stylesheet" href="css/customAutocomplete.css?ver=<%= Versioning.getBuildNumber() %>">

<script type="text/javascript" >

var checkedCount = 0;
var currentSampleType;
var sampleIdStart = 0;
var orderChanged = false;

//This handles the case where sampleAdd.jsp tile is not used.  Will be overridden in sampleAdd.jsp
function samplesHaveBeenAdded(){ return false;}

$jq(document).ready( function() {
    if( !<%=isEditable%>) {
        $jq(":input").prop("readOnly", true);
        $jq(".patientSearch").prop("readOnly", false);
    }
});

$jq(function() {
   	var maxAccessionNumber = $("maxAccessionNumber").value;
	var lastDash = maxAccessionNumber.lastIndexOf('-');
   	sampleIdStart = maxAccessionNumber.substring(lastDash + 1);
});

function  /*void*/ setMyCancelAction(form, action, validate, parameters)
{
	//first turn off any further validation
	setAction(window.document.forms[0], 'Cancel', 'no', '');
}

function /*void*/ addRemoveRequest( checkbox ){
	checkedCount = Math.max(checkedCount + (checkbox.checked ? 1 : -1), 0 );

	if( typeof(showSuccessMessage) != 'undefinded' ){
		showSuccessMessage(false); //refers to last save
	}

	setSaveButton();

}


// Adds warning when leaving page if tests are checked
function formWarning(){
	var newAccession = $("newAccessionNumber").value;
	var accessionChanged = newAccession.length > 1 && newAccession != "<%=accessionNumber%>"; 

  	if ( checkedCount > 0 || accessionChanged || samplesHaveBeenAdded()) {
    	return "<bean:message key="banner.menu.dataLossWarning"/>";
	}
}
window.onbeforeunload = formWarning;

function /*void*/ savePage(){
	if( samplesHaveBeenAdded() && !sampleAddValid( false )){
		alert("<%= StringUtil.getMessageForKey("warning.sample.missing.test")%>");
		return;
	}


    if( $jq(".testWithResult:checked").size() > 0 &&
        !confirm("<%= StringUtil.getMessageForKey("test.modify.save.warning")%>") ) {
            return;
    }
	window.onbeforeunload = null; // Added to flag that formWarning alert isn't needed.
	loadSamples();
	
	var form = document.forms[0];
	form.action = "SampleEditUpdate.do";
	form.submit();
}

function checkEditedAccessionNumber(changeElement){
	var accessionNumber;
	clearFieldErrorDisplay( changeElement );

	$("newAccessionNumber").value = "";
	
	if( changeElement.value.length == 0){
		updateSampleItemNumbers( "<%=accessionNumber%>" );
		setSaveButton();
		return;
	}
	
	if( changeElement.value.length != <%= editableAccession%>){
		setFieldErrorDisplay( changeElement );
		setSaveButton();
		alert("<%=StringUtil.getMessageForKey("sample.entry.invalid.accession.number.length")%>");
		return;
	}
	
	accessionNumber = "<%=((String)accessionNumber).substring(0, nonEditableAccession)%>" + changeElement.value;
	
	if( accessionNumber == "<%=accessionNumber%>"){
		updateSampleItemNumbers( accessionNumber );
		setSaveButton();
		return;
	}
	
	validateAccessionNumberOnServer(true, false, changeElement.id, accessionNumber, processEditAccessionSuccess, null);
}

function processEditAccessionSuccess(xhr)
{
	//alert( xhr.responseText );
	var accessionNumberUpdate;
	var formField = xhr.responseXML.getElementsByTagName("formfield").item(0).firstChild.nodeValue;
	var message = xhr.responseXML.getElementsByTagName("message").item(0).firstChild.nodeValue;

	if (message == "SAMPLE_FOUND"){
		setFieldErrorDisplay( $(formField) );
		setSaveButton();
		alert("<%=StringUtil.getMessageForKey("errors.may_not_reuse_accession_number")%>");
		return;
	}
	
	if( message == "SAMPLE_NOT_FOUND"){
		accessionNumberUpdate = "<%=((String)accessionNumber).substring(0, nonEditableAccession)%>" + $(formField).value;
		updateSampleItemNumbers( accessionNumberUpdate );
		$("newAccessionNumber").value = accessionNumberUpdate;
		setSaveButton();
		return;
	}
	
	setFieldErrorDisplay( $(formField) );
	setSaveButton();
	alert(message);
}

function updateSampleItemNumbers(newAccessionNumber){
		var i, itemNumbers, currentValue, lastDash = 0;
		itemNumbers = $$('span.itemNumber');
		
		for( i = 0; i < itemNumbers.length; i++){
			if(itemNumbers[i].firstChild != undefined){
				currentValue = itemNumbers[i].firstChild.nodeValue;
				lastDash = currentValue.lastIndexOf('-');
				itemNumbers[i].firstChild.nodeValue = newAccessionNumber + currentValue.substring(lastDash);
			}
		}
}

function checkValidEntryDate(date, dateRange, blankAllowed)
{
    $jq("#sampleItemChanged_" + date.id.split("_")[1]).val(true);
    if((!date.value || date.value == "") && !blankAllowed){
        setSaveButton();
        return;
    } else if ((!date.value || date.value == "") && blankAllowed) {
        setValidIndicaterOnField(true, date.id);
        setSaveButton();
        return;
    }


    if( !dateRange || dateRange == ""){
        dateRange = 'past';
    }

    //ajax call from utilites.js
    isValidDate( date.value, processValidateEntryDateSuccess, date.id, dateRange );
}

function  /*void*/ processValidateEntryDateSuccess(xhr){

    //alert(xhr.responseText);

    var message = xhr.responseXML.getElementsByTagName("message").item(0).firstChild.nodeValue;
    var formField = xhr.responseXML.getElementsByTagName("formfield").item(0).firstChild.nodeValue;

    var isValid = message == "<%=IActionConstants.VALID%>";

    //utilites.js
    selectFieldErrorDisplay( isValid, $(formField));
    setSaveButton();

    if( message == '<%=IActionConstants.INVALID_TO_LARGE%>' ){
        alert( "<bean:message key="error.date.inFuture"/>" );
    }else if( message == '<%=IActionConstants.INVALID_TO_SMALL%>' ){
        alert( "<bean:message key="error.date.inPast"/>" );
    }
}


function checkValidTime(time, blankAllowed)
{
    var lowRangeRegEx = new RegExp("^[0-1]{0,1}\\d:[0-5]\\d$");
    var highRangeRegEx = new RegExp("^2[0-3]:[0-5]\\d$");

    $jq("#sampleItemChanged_" + time.id.split("_")[1]).val(true);
    if (time.value.blank() && blankAllowed == true) {
        clearFieldErrorDisplay(time);
        setSaveButton();
        return;
    }

    if( lowRangeRegEx.test(time.value) ||
            highRangeRegEx.test(time.value) )
    {
        if( time.value.length == 4 )
        {
            time.value = "0" + time.value;
        }
        clearFieldErrorDisplay(time);
    }
    else
    {
        setFieldErrorDisplay(time);
 //       setSampleFieldInvalid(time.name);
    }

    setSaveButton();
}

//all methods here either overwrite methods in tiles or all called after they are loaded
var dirty=false;
function makeDirty(){
    dirty=true;
    if( typeof(showSuccessMessage) != 'undefinded' ){
        showSuccessMessage(false); //refers to last save
    }
    // Adds warning when leaving page if content has been entered into makeDirty form fields
    function formWarning(){
        return "<bean:message key="banner.menu.dataLossWarning"/>";
    }
    window.onbeforeunload = formWarning;
}

</script>

<hr/>

<logic:equal name="<%=formName%>" property="noSampleFound" value="false">
<DIV  id="patientInfo" class='textcontent'>
<bean:message key="sample.entry.patient"/>:&nbsp;
<bean:write name="<%=formName%>" property="patientName"/>&nbsp;
<bean:write name="<%=formName%>" property="dob"/>&nbsp;
<bean:write name="<%=formName%>" property="gender"/>&nbsp;
<bean:write name="<%=formName%>" property="nationalId"/>
</DIV>
<hr/>
<br/>
<html:hidden name="<%=formName%>" property="accessionNumber"/>
<html:hidden name="<%=formName%>" property="newAccessionNumber" styleId="newAccessionNumber"/>
<html:hidden name="<%=formName%>" property="isEditable"/>
<html:hidden name="<%=formName%>" property="maxAccessionNumber" styleId="maxAccessionNumber"/>
<logic:equal name='<%=formName%>' property="isEditable" value="true" >
	<h1><%=StringUtil.getContextualMessageForKey("sample.edit.accessionNumber") %></h1>  
	<div id="accessionEditDiv" class="TableMatch">
		<b><%=StringUtil.getContextualMessageForKey("sample.edit.change.from") %>:</b> <bean:write name="<%=formName%>" property="accessionNumber"/>  
		<b><%=StringUtil.getContextualMessageForKey("sample.edit.change.to") %>:</b> <%= ((String)accessionNumber).substring(0, nonEditableAccession) %>
		<input type="text"
		       value='<%= ((String)newAccessionNumber).length() == maxAccessionLength ? ((String)newAccessionNumber).substring(nonEditableAccession, maxAccessionLength) : "" %>'
		       maxlength="<%= editableAccession%>"
		       size="<%= editableAccession%>"
		       onchange="checkEditedAccessionNumber(this);"
		       id="accessionEdit">
		       
	<br/><br/><hr/>
	</div>
</logic:equal>
    <bean:define id="confirmationSample" name='<%=formName%>' property="isConfirmationSample" type="java.lang.Boolean" />
    <div id="sampleOrder" class="colorFill" >
        <% if( confirmationSample ){ %>
        <tiles:insert attribute="sampleConfirmationOrder" />
        <% }else{ %>
        <tiles:insert attribute="sampleOrder" />
        <% } %>
    </div>

<logic:equal name='<%=formName%>' property="isEditable" value="true" >
	<h1><%=StringUtil.getContextualMessageForKey("sample.edit.tests") %></h1>
</logic:equal>
<table style="width:60%">
<caption><div><bean:message key="sample.edit.existing.tests"/></div>
    <span style="color: red"><small><small><%= cancelableResults ? StringUtil.getMessageForKey( "test.modify.static.warning" ) : "" %></small></small></span></caption>
<tr>
<th><%= StringUtil.getContextualMessageForKey("quick.entry.accession.number") %></th>
<th><bean:message key="sample.entry.sample.type"/></th>
<% if( useCollectionDate ){ %>
<th >
    <bean:message key="sample.collectionDate"/>&nbsp;<%=DateUtil.getDateUserPrompt()%>
</th>
<th >
    <bean:message key="sample.collectionTime"/>
</th>
<% } %>
<logic:equal name='<%=formName%>' property="isEditable" value="true" >
	<th style="width:16px"><bean:message key="sample.edit.remove.sample" /></th>
</logic:equal>
<th><bean:message key="test.testName"/></th>
<th><bean:message key="test.has.result"/></th>
<logic:equal name='<%=formName%>' property="isEditable" value="true" >
	<th style="width:16px"><bean:message key="sample.edit.remove.tests" /></th>
</logic:equal>
<logic:equal name='<%=formName%>' property="isEditable" value="false" >
	<th><bean:message key="analysis.status" /></th>
</logic:equal>

</tr>
	<logic:iterate id="existingTests" name="<%=formName%>"  property="existingTests" indexId="index" type="us.mn.state.health.lims.sample.bean.SampleEditItem">
        <html:hidden property="sampleItemChanged" name="existingTests" styleId='<%= "sampleItemChanged_" + index %>' styleClass="sampleItemChanged" indexed="true"/>
	<tr>
		<td>
			<html:hidden name="existingTests" property="analysisId" indexed="true"/>
			<span class="itemNumber" ><bean:write name="existingTests" property="accessionNumber"/></span>
		</td>
		<td>
			<bean:write name="existingTests" property="sampleType"/>
		</td>
        <% if( useCollectionDate ){ %>
        <td >
			<% if( existingTests.getCollectionDate() != null ){%>
			<html:text name='existingTests'
					   property='collectionDate'
					   maxlength='10'
					   size ='12'
					   onchange="checkValidEntryDate(this, 'past', true);"
					   styleId='<%= "collectionDate_" + index %>'
					   styleClass='<%= "text" + (isEditable? "" : " readOnly") %>'
					   indexed="true" />
			<% } %>
        </td>
        <td >
            <% if( existingTests.getCollectionDate() != null ){%>
            <html:text name='existingTests'
                       property='collectionTime'
                       maxlength='10'
                       size ='12'
                       onkeyup='filterTimeKeys(this, event);'
                       onblur='checkValidTime(this, true);'
                       styleId='<%= "collectionTime_" + index %>'
                       styleClass='text'
                       indexed="true"/>
            <% } %>
        </td>
        <% } %>
		<logic:equal name='<%=formName%>' property="isEditable" value="true" >
            <td>
                <% if( existingTests.getAccessionNumber() != null ){
                    if( existingTests.isCanRemoveSample() ){ %>
                <html:checkbox name='existingTests' property='removeSample' indexed='true' onchange="addRemoveRequest(this);"/>
                <% }else{ %>
                <html:checkbox name='existingTests' property='removeSample' indexed='true' disabled="true"/>
                <% }
                } %>
            </td>
		</logic:equal>
		<td>
			<bean:write name="existingTests" property="testName"/>
		</td>
            <td style="text-align: center">
                <%= existingTests.isHasResults() ? "X" : ""%>
            </td>
		<logic:equal name='<%=formName%>' property="isEditable" value="true" >
			<td>
				<% if( existingTests.isCanCancel()){ %>
                <input type="checkbox" name='<%="existingTests[" + index +"].canceled"%>' value="on" onchange="addRemoveRequest(this);" <%=existingTests.isHasResults() ? "class='testWithResult'" : ""%>>
				<% }else{ %>
				<html:checkbox name='existingTests' property='canceled' indexed='true' disabled="true" />
				<% } %>
			</td>
		</logic:equal>
		<logic:equal name='<%=formName%>' property="isEditable" value="false" >
			<td>
				<bean:write name='existingTests' property="status" />
			</td>
		</logic:equal>
	</tr>
	</logic:iterate>
</table>
<hr/>
<br/>
<logic:equal name='<%=formName%>' property="isEditable" value="true" >
<table id="availableTestTable" style="width:80%">
<caption><bean:message key="sample.edit.available.tests"/></caption>
<tr>
<th><%= StringUtil.getContextualMessageForKey("quick.entry.accession.number") %></th>
<th><bean:message key="sample.entry.sample.type"/></th>
<th><bean:message key="sample.entry.assignTests"/></th>
<th><bean:message key="test.testName"/></th>
</tr>
	<logic:iterate id="possibleTests" name="<%=formName%>"  property="possibleTests" indexId="index" type="SampleEditItem">
	<tr>
		<td>
		    <html:hidden name="possibleTests" property="testId" indexed="true"/>
		    <html:hidden name="possibleTests" property="sampleItemId" indexed="true"/>
			<span class="itemNumber" ><bean:write name="possibleTests" property="accessionNumber"/></span>
		</td>
		<td>
			<bean:write name="possibleTests" property="sampleType"/>
		</td>
		<td>
			<html:checkbox name="possibleTests" property="add" indexed="true" onchange="addRemoveRequest(this);" />
		</td>
		<td>&nbsp;
			<bean:write name="possibleTests" property="testName"/>
		</td>
	</tr>
	</logic:iterate>
</table>

<hr>
<h1><bean:message key="sample.entry.addSample" /></h1>

<div id="samplesDisplay" class="colorFill" >
	<tiles:insert attribute="addSample"/>
</div>
</logic:equal>
</logic:equal>
<logic:equal name="<%=formName%>" property="noSampleFound" value="true">
	<bean:message key="sample.edit.sample.notFound"/>
</logic:equal>

<script type="text/javascript" >

    function setSaveButton(){
        var newAccession = $("newAccessionNumber").value;
        var accessionChanged = newAccession.length > 1 && newAccession != "<%=accessionNumber%>";
        var sampleItemChanged = $jq(".sampleItemChanged[value='true']").length > 0;
        var sampleAddIsValid = typeof(sampleAddValid) != 'undefined' ?  sampleAddValid(false) : true;
        var sampleConfirmationIsValid = typeof(sampleConfirmationValid) != 'undefined' ?  sampleConfirmationValid() : true;

        $("saveButtonId").disabled = errorsOnForm() || !sampleAddIsValid || !sampleConfirmationIsValid || (checkedCount == 0  && !accessionChanged && !samplesHaveBeenAdded() && !orderChanged && !sampleItemChanged );
    }

</script>