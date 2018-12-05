<%@ page language="java" contentType="text/html; charset=utf-8"
		import="us.mn.state.health.lims.common.action.IActionConstants,
				us.mn.state.health.lims.common.formfields.FormFields,
                us.mn.state.health.lims.common.formfields.FormFields.Field,
                us.mn.state.health.lims.common.provider.validation.AccessionNumberValidatorFactory,
                us.mn.state.health.lims.common.provider.validation.IAccessionNumberValidator,
                us.mn.state.health.lims.common.provider.validation.NonConformityRecordNumberValidationProvider,
                us.mn.state.health.lims.common.services.PhoneNumberService,
                us.mn.state.health.lims.common.util.DateUtil,
                us.mn.state.health.lims.common.util.StringUtil, 
                us.mn.state.health.lims.common.util.Versioning,
                us.mn.state.health.lims.qaevent.valueholder.retroCI.QaEventItem,
                us.mn.state.health.lims.common.util.ConfigurationProperties" %>


<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/labdev-view" prefix="app"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/sourceforge-ajax" prefix="ajax"%>

<bean:define id="formName"
	value='<%=(String) request.getAttribute(IActionConstants.FORM_NAME)%>' />


<%! String basePath = "";
    IAccessionNumberValidator accessionNumberValidator;
    boolean useProject = FormFields.getInstance().useField(Field.Project);
    boolean useSiteList = FormFields.getInstance().useField(Field.NON_CONFORMITY_SITE_LIST);
    boolean useSubjectNo = FormFields.getInstance().useField(Field.QASubjectNumber);
    boolean useNationalID = FormFields.getInstance().useField(Field.NationalID);
%>
<%
    String path = request.getContextPath();
    basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
                    + "/";
    accessionNumberValidator = new AccessionNumberValidatorFactory().getValidator();
    
%>

<link rel="stylesheet" href="css/jquery_ui/jquery.ui.all.css?ver=<%= Versioning.getBuildNumber() %>">
<link rel="stylesheet" href="css/customAutocomplete.css?ver=<%= Versioning.getBuildNumber() %>">

<script src="scripts/ui/jquery.ui.core.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script src="scripts/ui/jquery.ui.widget.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script src="scripts/ui/jquery.ui.button.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script src="scripts/ui/jquery.ui.menu.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script src="scripts/ui/jquery.ui.position.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script src="scripts/ui/jquery.ui.autocomplete.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script src="scripts/customAutocomplete.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script src="scripts/utilities.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script src="scripts/ajaxCalls.js?ver=<%= Versioning.getBuildNumber() %>"></script>

<script type="text/javascript" >

var dirty = false;

var confirmNewTypeMessage = "<bean:message key='nonConformant.confirm.newType.message'/>";

/* $jq(function() {
	var eventsTable = $('qaEventsTable');
	if (eventsTable === null) {
		return;
	}
	var fields = eventsTable.getElementsBySelector(".requiredField");
	fields.each(function(field) {
				if( !field.disabled){
					fieldValidator.addRequiredField(field.id);
				}  
			});
}); */
function siteListChanged(textValue){
	var siteList = $("site");
	//if the index is 0 it is a new entry, if it is not then the textValue may include the index value
	if( siteList.selectedIndex == 0 || siteList.options[siteList.selectedIndex].label != textValue){
		  $("newServiceName").value = textValue;
		  siteList.selectedIndex = 0;
	}
	
	$("serviceNew").value = !textValue.blank();
}

function /*void*/loadForm() {
	if (!$("searchId").value.empty()) {
		var form = document.forms[0];

		form.action = "NonConformity.do?labNo=" + $("searchId").value;

		form.submit();
	}
}

function setMyCancelAction() {
	setAction(window.document.forms[0], 'Cancel', 'no', '');
}
function doNothing(){
	
}
function onChangeSearchNumber(searchField) {
	var searchButton = $("searchButtonId");
	if (searchField.value === "") {
		searchButton.disable();
	} else {
	    validateAccessionNumberOnServer( true, true, searchField.id, searchField.value, processAccessionSuccess);
	}
}

function processAccessionSuccess(xhr)
{
    //alert(xhr.responseText);
	var message = xhr.responseXML.getElementsByTagName("message").item(0);
	var success = message.firstChild.nodeValue == "valid";

    var searchButton = $("searchButtonId");

	if( !success ){
		alert( message.firstChild.nodeValue );
        searchButton.disable();
	}else {
        searchButton.enable();
        searchButton.focus();
    }
}


/**
 * make the text of the blank option for sample type say "all types"
 */
function tweekSampleTypeOptions() {
	var eventsTable = $('qaEventsTable');
	if (eventsTable === null) {
		return;
	}
	var fields = eventsTable.getElementsBySelector(".typeOfSample");
	fields.each(function(field) {
				field.options[1].text = "<bean:message key='nonConformant.allSampleTypesText'/>";
			});
}

function addRow( ) {
	var eventsTable = $('qaEventsTable');
	if (eventsTable == null)
		return;
	var rows = eventsTable.getElementsBySelector("TR.qaEventRow");
	// display all rows up to the first unused one
	var row = rows.detect(function(row) {
		row.show(); // show each row as we go everyone
			var idFieldValue = row.getElementsBySelector(".id")[0].getValue();
			if (idFieldValue == "NEW") {
				enableRow(row, idFieldValue);
			} else {
				disableRow(row, true);
			}
			return idFieldValue == "";
		});
	if (row == null) {
		row = rows[rows.length - 1];
	}
	// we have an empty row
	row.show();
	var idFields = row.getElementsBySelector(".id");
	$(idFields[0]).value = "NEW";
	enableRow(row, "NEW");
	setSave();
}

function enableRow(row, newIndicator) {
	var isNew = newIndicator == "NEW";
	var elements = row.getElementsBySelector(".qaEventElement");

	elements.each(function(field) {
		field = $(field);
		if( isNew || field.id.startsWith("note")){
			field.enable();
			field.removeClassName("readOnly");
		}
	});
	
/* 	elements = row.getElementsBySelector(".requiredField");
	elements.each(function(field) {
		fieldValidator.addRequiredField(field.id);
	});
 */
}
function disableRow(row, userAdded) {
	var elements = row.getElementsBySelector(".qaEventElement");
	elements.each(function(field) {
		field = $(field);
		if( !(userAdded && field.id.startsWith("note"))){
			field.disable();
			field.addClassName("readOnly");
		}
	});
	
	/* elements = row.getElementsBySelector(".requiredField");
	elements.each(function(field) {
		fieldValidator.removeRequiredField(field.id);
	}); */
}

function areNewTypesOfSamples() {
	var eventsTable = $('qaEventsTable');
	var fields = eventsTable.getElementsBySelector(".typeOfSample");
	if ($("sampleItemsTypeOfSampleIds").value == "") {
		return false; // we don't worry about sample types when there aren't any at all
	}

    return fields.detect(function (field) {
        var ids = $("sampleItemsTypeOfSampleIds").value;
        var val = field.value;
        return (val !== null && val !== "0" && ids.indexOf("," + val + ",") == -1);
    }) != null;
}

function /*boolean*/ handleEnterEvent(){
	loadForm();
	return false;
}

function validateRecordNumber( recordElement){
	if( recordElement.value != ""){
		validateNonConformityRecordNumberOnServer( recordElement, recordNumberSuccess);
	}
	
	clearFieldErrorDisplay( recordElement);
}

function recordNumberSuccess( xhr){
    //alert(xhr.responseText);
	var message = xhr.responseXML.getElementsByTagName("message").item(0);
	var formField = xhr.responseXML.getElementsByTagName("formfield").item(0).firstChild.nodeValue;
	var success = message.firstChild.nodeValue == "Record not Found";
	var fieldElement = $(formField);

	selectFieldErrorDisplay( success, fieldElement);
    fieldValidator.setFieldValidity(success, fieldElement.id);

	if( !success ){
		alert( message.firstChild.nodeValue );
	}
	
	setSave();
}

function checkValidTime( timeElement ){
	var valid = !timeElement.value || checkTime(timeElement.value);
	selectFieldErrorDisplay( valid, timeElement);
	fieldValidator.setFieldValidity(valid, timeElement.id);
	setSave();
}

function enableDisableIfChecked(removeCheckBox){
	var row = removeCheckBox.parentNode.parentNode;
	var idFieldValue = row.getElementsBySelector(".id")[0].getValue();

	if( removeCheckBox.checked){
		disableRow(row, false );
	}else{
		enableRow(row, idFieldValue);
	}
	
	setSave();
}

function setSave(){
	var saveButton = $("saveButtonId"); 
	var validToSave = fieldValidator.isAllValid();

		//all this crap is to make sure there is not an enabled new row that has no value for the required field then none of the 
		//other fields have values		
	if( validToSave){
		$jq("#qaEventsTable tbody tr").each( function(rowIndex, rowElement){
			var jqRow = $jq(rowElement);
			if(validToSave && jqRow.is(":visible")){
				//if row is visible and the required field is blank make sure no other field has a value
				if( !(jqRow.find(".qaEventEnable").is(":checked") ) && requiredSelectionsNotDone( jqRow ) ){
					jqRow.find(".qaEventElement").each( function(index, element){
						var cellValue = $jq(element).val(); 
						if( !(cellValue.length == 0 || cellValue == "0" )){
							validToSave = false;
							return;
						}
					});
				}
			}
		});
	}

	if( saveButton){
		saveButton.disabled = !validToSave;
	}	
}

function requiredSelectionsNotDone( jqRow ){
    var done = true;

    jqRow.find(".requiredField").each( function(index, element){
        if( element.value == 0 ){
            done = false;
            return;
        }
    });

    return !done;
}

function validatePhoneNumber( phoneElement){
    validatePhoneNumberOnServer( phoneElement, processPhoneSuccess);
}

function  processPhoneSuccess(xhr){
    //alert(xhr.responseText);

    var formField = xhr.responseXML.getElementsByTagName("formfield").item(0);
    var message = xhr.responseXML.getElementsByTagName("message").item(0);
    var success = false;

    if (message.firstChild.nodeValue == "valid"){
        success = true;
    }
    var labElement = formField.firstChild.nodeValue;
    selectFieldErrorDisplay( success, $(labElement));
    fieldValidator.setFieldValidity(success, labElement);

    if( !success ){
        alert( message.firstChild.nodeValue );
    }

    setSave();
}

</script>


<div align="center">
	<%= StringUtil.getContextualMessageForKey("quick.entry.accession.number") %>
	:
	<input type="text" name="searchNumber"
		maxlength='<%=Integer.toString(accessionNumberValidator.getMaxAccessionLength())%>'
		value="" onchange="doNothing()" id="searchId">
	&nbsp;
	<input type="button" id="searchButtonId"
		value='<%=StringUtil.getMessageForKey("label.button.search")%>'
		onclick="loadForm();" disabled="disabled">
</div>
<hr />
<bean:define id="readOnly" name='<%=formName%>' property="readOnly"	type="java.lang.Boolean" />
<logic:notEmpty name='<%=formName%>' property="labNo">
	<html:hidden name='<%=formName%>' property="sampleId" />
	<html:hidden name='<%=formName%>' property="patientId" />
	<html:hidden name='<%=formName%>' styleId="sampleItemsTypeOfSampleIds" property="sampleItemsTypeOfSampleIds" />
	<table >
		<tr>
			<td >
				<%= StringUtil.getContextualMessageForKey("nonconformity.date") %>&nbsp;
                <span style="font-size: xx-small; "><%=DateUtil.getDateUserPrompt()%></span>
				:
			</td>
			<td>
				<html:text name='<%=formName %>' 
				           styleId="date1" 
				           property="date"
					       maxlength="10" 
					       onchange="checkValidDate(this);" />
			<% if( FormFields.getInstance().useField(Field.QATimeWithDate)){ %>
				<%= StringUtil.getContextualMessageForKey("nonconformity.time") %>
				:
				<html:text name='<%=formName %>' 
				           styleId="time" 
				           property="time"
					       maxlength="5" 
					       onchange="checkValidTime(this);" />
			<% } %>
			</td>
		</tr>
		<tr>
			<html:hidden name='<%=formName%>' property="project" />
			<% if ( useProject ) { %>
			<td >
				<bean:message key="label.study" />
				:
			</td>
			<logic:equal name='<%=formName%>' property="project" value="">
				<td>
					<html:select name="<%=formName%>" property="projectId"
						onchange='makeDirty();'>
						<option value="0"></option>
						<html:optionsCollection name="<%=formName%>" property="projects"
							label="localizedName" value="id" />
					</html:select>
				</td>
			</logic:equal>
			<logic:notEqual name='<%=formName%>' property="project" value="">
				<td>
					<bean:write name="<%=formName%>" property="project" />
				</td>
			</logic:notEqual>
			<% } %>
		</tr>
		<% if ( useSubjectNo ) { %>
		<tr>
			<td >
				<bean:message key="patient.subject.number" />
				:
			</td>
			<html:hidden name='<%=formName%>' styleId="subjectNew"
				property="subjectNew" />
			<logic:equal name='<%=formName%>' property="subjectNo" value="">
				<td>
					<html:text name='<%=formName %>' property="subjectNo"
						onchange="makeDirty();$('subjectNew').value = true;" />
				</td>
			</logic:equal>
			<logic:notEqual name='<%=formName%>' property="subjectNo" value="">
				<td>
					<bean:write name="<%=formName%>" property="subjectNo" />
				</td>
			</logic:notEqual>
		</tr>
		<% } %>
		<% if ( FormFields.getInstance().useField(Field.StNumber) ) { %>
		<tr>
			<td >
				<bean:message key="patient.ST.number" />
				:
			</td>
			<html:hidden name='<%=formName%>' property="newSTNumber" styleId="newSTNumber"/>
			<logic:equal name='<%=formName%>' property="STNumber" value="">
				<td>
					<html:text name='<%=formName %>' property="STNumber"
						onchange="makeDirty();$('newSTNumber').value = true;" />
				</td>
			</logic:equal>
			<logic:notEqual name='<%=formName%>' property="STNumber" value="">
				<td>
					<bean:write name="<%=formName%>" property="STNumber" />
				</td>
			</logic:notEqual>
		</tr>
		<% } %>
		<% if ( useNationalID ) { %>
		<tr>
			<td >
			    <%=StringUtil.getContextualMessageForKey("patient.NationalID") %>
				:
			</td>
			<html:hidden name='<%=formName%>'	property="nationalIdNew" styleId="nationalIdNew"/>
			<logic:equal name='<%=formName%>' property="nationalId" value="">
				<td>
					<html:text name='<%=formName %>' property="nationalId"
						onchange="makeDirty();$('nationalIdNew').value = true;" />
				</td>
			</logic:equal>
			<logic:notEqual name='<%=formName%>' property="nationalId" value="">
				<td>
					<bean:write name="<%=formName%>" property="nationalId" />
				</td>
			</logic:notEqual>
		</tr>
		<% } %>
		<tr>
			<td >
				<%=StringUtil.getContextualMessageForKey("sample.id") %>
				:
			</td>
			<td>
				<bean:write name="<%=formName%>" property="labNo" />
			</td>
		</tr>
		<html:hidden name='<%=formName%>' styleId="serviceNew" property="serviceNew" />
			<html:hidden name='<%=formName%>' styleId="newServiceName" property="newServiceName" />
			<logic:equal name='<%=formName%>' property="service" value="">
				<% if( useSiteList ){ %>
					<tr>
						<td><%= StringUtil.getContextualMessageForKey("sample.entry.project.siteName") %>
						<td>
							<html:select styleId="site"
									     name="<%=formName%>"
									     property="service"
									     onchange="makeDirty();$('serviceNew').value = true;">
								<option value=""></option>
								<html:optionsCollection name="<%=formName%>" property="siteList" label="value" value="id" />
						   	</html:select>
						</td>
					</tr>
				<% } else { %>
					<tr>
						<td ><bean:message key="patient.project.service" />:</td>
				  		<td><html:text name='<%=formName %>' property="service" onchange="makeDirty();$('serviceNew').value = true;" /></td>
					</tr>
				<% }%>
			</logic:equal>
			<logic:notEqual name='<%=formName%>' property="service" value="">
					<tr>
						<td ><%= StringUtil.getContextualMessageForKey("sample.entry.project.siteName") %>:</td>
						<td>
							<bean:write name='<%=formName%>' property="service" />
						</td>
					</tr>
			</logic:notEqual>
		<html:hidden name='<%=formName%>' styleId="doctorNew" property="doctorNew" />
		<%  if (FormFields.getInstance().useField(Field.QA_FULL_PROVIDER_INFO )) { %>
        <% if(ConfigurationProperties.getInstance().isPropertyValueEqual(ConfigurationProperties.Property.QA_SAMPLE_ID_REQUIRED, "true")) { %>
					<tr>
						<td><bean:message key="sample.clientReference" />:</td>
						<td >
						<logic:equal name='<%=formName%>' property="requesterSampleID" value="">
							<app:text name="<%=formName%>"
									  property="requesterSampleID"
									  size="30"
								  	onchange="makeDirty();"/>
						</logic:equal>		  	
						<logic:notEqual name='<%=formName%>' property="requesterSampleID" value="">					
							<bean:write name="<%=formName%>" property="requesterSampleID" />
						</logic:notEqual>
						</td>
					<td colspan="2">&nbsp;</td>
				</tr>
        <% } %>
				<tr>
					<td><%= StringUtil.getContextualMessageForKey("nonconformity.provider.label") %></td>
				</tr>
				<tr>
					<logic:equal name='<%=formName%>' property="providerLastName" value="">
						<td align="right"><bean:message key="person.lastName" />:</td>
						<td >
						<html:text name="<%=formName%>"
								  property="providerLastName"
							      styleId="providerLastNameID"
							      onchange="makeDirty();$('doctorNew').value = true;"
							      size="30" />
						<bean:message key="humansampleone.provider.firstName.short"/>:
						<html:text name="<%=formName%>"
								  property="providerFirstName"
							      styleId="providerFirstNameID"
							      onchange="makeDirty();$('doctorNew').value = true;"
							      size="30" />
						</td>		      
					</logic:equal>
					<logic:notEqual name='<%=formName%>' property="providerLastName" value="">
						<td align="right"><bean:message key="person.name" />:</td>
						<td><bean:write name="<%=formName%>" property="providerLastName" />,&nbsp;<bean:write name="<%=formName%>" property="providerFirstName" /></td>
					</logic:notEqual>		      
				</tr>
				<tr>
					<td align="right">
						<bean:message  key="person.streetAddress.street" />:
					</td>
					<td>
					<logic:equal name='<%=formName%>' property="providerStreetAddress" value="">
						<app:text name='<%=formName%>'
					  			  property="providerStreetAddress"
					  			  styleClass="text"
					  			  size="70" />
					</logic:equal>
					<logic:notEqual name='<%=formName%>' property="providerStreetAddress" value="">
						<bean:write name="<%=formName%>" property="providerStreetAddress" />
					</logic:notEqual>
					</td>
				</tr>
			<% if( FormFields.getInstance().useField(Field.ADDRESS_VILLAGE )) { %>
				<tr>
					<td align="right">
					    <%= StringUtil.getContextualMessageForKey("person.town") %>:
					</td>
					<td>
					<logic:equal name='<%=formName%>' property="providerCity" value="">
						<app:text name='<%=formName%>'
								  property="providerCity"
								  styleClass="text"
								  size="30" />
					</logic:equal>
					<logic:notEqual name='<%=formName%>' property="providerCity" value="">
						<bean:write name="<%=formName%>" property="providerCity" />
					</logic:notEqual>			  
					</td>
				</tr>
			<% } %>
			<% if( FormFields.getInstance().useField(Field.ADDRESS_COMMUNE)){ %>
			<tr>
				<td align="right">
					<bean:message  key="person.commune" />:
				</td>
				<td>
				<logic:equal name='<%=formName%>' property="providerCommune" value="">
					<app:text name='<%=formName%>'
							  property="providerCommune"
							  styleClass="text"
							  size="30" />
				</logic:equal>
				<logic:notEqual name='<%=formName%>' property="providerCommune" value="">
					<bean:write name="<%=formName%>" property="providerCommune" />
				</logic:notEqual>
				</td>
			</tr>
			<% } %>
			<% if( FormFields.getInstance().useField(Field.ADDRESS_DEPARTMENT )){ %>
			<tr>
				<td align="right">
					<bean:message  key="person.department" />:
				</td>
				<td>
				<logic:equal name='<%=formName%>' property="providerDepartment" value="">
					<html:select name='<%=formName%>'
								 property="providerDepartment"
							     styleId="departmentID" >
					<option value="0" ></option>
					<html:optionsCollection name="<%=formName %>" property="departments" label="value" value="id" />
					</html:select>
				</logic:equal>	
				<logic:notEqual name='<%=formName%>' property="providerDepartment" value="">
					<bean:write name="<%=formName%>" property="providerDepartment" />
				</logic:notEqual>
				</td>
			</tr>
			<% } %>

			<tr>
				<td align="right">
					<bean:message key="person.phone" />&nbsp;
					<%= PhoneNumberService.getPhoneFormat() %>
				</td>
				<td>
				<logic:equal name='<%=formName%>' property="providerWorkPhone" value="">
					<app:text name="<%=formName%>"
					          property="providerWorkPhone"
						      styleId="providerWorkPhoneID"
						      size="30"
						      maxlength="35"
						      styleClass="text"
						      onchange="validatePhoneNumber(this);makeDirty();$('doctorNew').value = true;" />
				    <div id="providerWorkPhoneMessage" class="blank" ></div>
				</logic:equal>    
				<logic:notEqual name='<%=formName%>' property="providerWorkPhone" value="">
					<bean:write name="<%=formName%>" property="providerWorkPhone" />
				</logic:notEqual>
				</td>
			</tr>
		<% } else { %>
			<tr>
				<td ><bean:message key="sample.entry.project.doctor" />:</td>
				<logic:equal name='<%=formName%>' property="doctor" value="">
					<td>
						<html:text name='<%=formName %>' property="doctor"
							onchange="makeDirty();$('doctorNew').value = true;" />
					</td>
				</logic:equal>
				<logic:notEqual name='<%=formName%>' property="doctor" value="">
					<td >
						<bean:write name="<%=formName%>" property="doctor" />
					</td>
				</logic:notEqual>
			</tr>
		<% } %>
	</table>
	
	<hr />
	<table style="width:95%" id="qaEventsTable">
		<thead>
		<tr>
			<th style="display: none"></th>
			<% if( FormFields.getInstance().useField(Field.QA_DOCUMENT_NUMBER)){ %>
				<th style="width:100px">
					<bean:message key="nonConformity.document.number" /><br> <%= NonConformityRecordNumberValidationProvider.getDocumentNumberFormat() %>  :
				</th>
			<% } %>
			<th style="width:22%">
				<bean:message key="label.refusal.reason" /><span class="requiredlabel">*</span>
			</th>
			<th style="width:16%">
				<bean:message key="label.sampleType" /><span class="requiredlabel">*</span>
			</th>
			<th style="width:11%">
				<%=StringUtil.getContextualMessageForKey("nonconformity.section") %>
			</th>
			<th style="width:13%">
				<%=StringUtil.getContextualMessageForKey("label.biologist") %>
			</th>
			<th >
				<%= StringUtil.getContextualMessageForKey("nonconformity.note") %>
			</th>
			<th style="width:5%">
				<bean:message key="label.remove" />
			</th>
		</tr>
		</thead>
		<tbody>
		<logic:iterate id="qaEvents" name="<%=formName%>" property="qaEvents"
			indexId="index" type="QaEventItem">
			<tr id="qaEvent_<%=index%>" style="display: none" class="qaEventRow">
			<% if( FormFields.getInstance().useField(Field.QA_DOCUMENT_NUMBER)){ %>
				<td>
					<html:text 
						styleId='<%="record" + index%>'
						styleClass="readOnly qaEventElement" 
						name='qaEvents'
						property = 'recordNumber'
						indexed = 'true'
						size = '12'
						onchange="makeDirty();validateRecordNumber(this)" />		 
				</td>
			<% } %>
				<td style="display: none">
					<html:hidden styleId='<%="id" + index%>' styleClass="id" name="qaEvents"
						property="id" indexed="true" onchange='makeDirty();' />
				</td>
				<td>
					<html:select styleId='<%="qaEvent" + index%>' 
								 name="qaEvents"
						         styleClass="readOnly qaEventElement requiredField" 
						         property="qaEvent"
						         indexed="true" 
						         disabled = "true"						    
						         style="width: 99%" 
						         onchange='makeDirty();'>
						<option value="0"></option>
						<html:optionsCollection name="<%=formName%>"
							property="qaEventTypes" label="value" value="id" />
					</html:select>
				</td>
				<td>
                    <html:select styleId='<%="sampleType" + index%>' name="qaEvents"
						styleClass="readOnly qaEventElement typeOfSample requiredField" disabled="false"
						property="sampleType" onchange='makeDirty();' indexed="true"
						style="width: 99%">
                        <option value="0"></option>
                        <option value="-1"  <%= (qaEvents.getSampleType() != null && qaEvents.getSampleType().equals("-1")) ? "selected='selected'" : ""%> ></option>
						<html:optionsCollection name="<%=formName%>"
							property="typeOfSamples" label="value" value="id" />
					</html:select>
				</td>
				<td>
					<html:select name="qaEvents" styleId='<%="section" + index%>'
						styleClass="readOnly qaEventElement" disabled="false"
						property="section" indexed="true" style="width: 99%"
						onchange='makeDirty();'>
						<option ></option>
						<html:optionsCollection name="<%=formName%>" property="sections"
							label="localizedName" value="nameKey" />
					</html:select>
				</td>
				<td>
					<html:text styleId='<%="author" + index%>' name="qaEvents"
						styleClass="readOnly qaEventElement" disabled="false"
						property="authorizer" indexed="true" onchange='makeDirty();'
						style="width: 99%" />
				</td>
				<td>
					<html:text styleId='<%="note" + index%>'
					           name="qaEvents"
							   styleClass="qaEventElement" 
							   disabled="false"
							   property="note" 
							   indexed="true" 
							   onchange='makeDirty();'
						style="width: 99%" />
				</td>
				<td>
					<html:checkbox styleId='<%="remove" + index%>' name="qaEvents"
						styleClass="qaEventEnable" property="remove" indexed="true"
						onclick='makeDirty(); enableDisableIfChecked(this)' />
				</td>
			</tr>
		</logic:iterate>
		</tbody>
	</table>
	<input type="button" id="addButtonId"
		value='<%=StringUtil.getMessageForKey("label.button.add")%>'
		onclick="addRow()" />
	<hr />
	<html:hidden name='<%=formName%>' styleId="commentNew"
		property="commentNew" value="" />
	<bean:message key="label.comments" />:
	<div style="width: 50%">
		<html:textarea name="<%=formName %>" property="comment"
			onchange="makeDirty();$('commentNew').value = true;"
			disabled='<%=readOnly%>' />
	</div>
</logic:notEmpty>

<script type="text/javascript" >
//all methods here either overwrite methods in tiles or all called after they are loaded

function makeDirty() {
	dirty = true;

	if (typeof (showSuccessMessage) != 'undefinded') {
		showSuccessMessage(false);
	}
	// Adds warning when leaving page if content has been entered into makeDirty form fields
	function formWarning(){ 
    return "<bean:message key="banner.menu.dataLossWarning"/>";
	}
	window.onbeforeunload = formWarning;
	
	setSave();
}

function savePage() {
	if (areNewTypesOfSamples() && !confirm(confirmNewTypeMessage)) {
		return false;
	}
	
	var form = window.document.forms[0];
  
	window.onbeforeunload = null; // Added to flag that formWarning alert isn't needed.
	form.action = "NonConformityUpdate.do";
	form.submit();
}

addRow(); // call once at the beginning to make the table of Sample QA Events look right.
tweekSampleTypeOptions();

<% if( FormFields.getInstance().useField(Field.NON_CONFORMITY_SITE_LIST_USER_ADDABLE)){ %>
// Moving autocomplete to end - needs to be at bottom for IE to trigger properly
$jq(document).ready( function() {
     	var dropdown = $jq( "select#site" );
        autoCompleteWidth = dropdown.width() + 66 + 'px';
        clearNonMatching = false;
        capitialize = true;
        dropdown.combobox();
       // invalidLabID = '<bean:message key="error.site.invalid"/>'; // Alert if value is typed that's not on list. FIX - add badmessage icon
        maxRepMsg = '<bean:message key="sample.entry.project.siteMaxMsg"/>'; 
        
        resultCallBack = function( textValue) {
  				siteListChanged(textValue);
  				makeDirty();
  				setSave();
				};
});
<%  } %>

</script>
