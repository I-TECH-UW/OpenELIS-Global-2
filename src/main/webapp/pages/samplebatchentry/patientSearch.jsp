<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page import="us.mn.state.health.lims.common.action.IActionConstants,
			     us.mn.state.health.lims.common.formfields.FormFields,
			     us.mn.state.health.lims.common.formfields.FormFields.Field,
			     us.mn.state.health.lims.common.provider.validation.AccessionNumberValidatorFactory,
			     us.mn.state.health.lims.common.provider.validation.IAccessionNumberValidator,
			     us.mn.state.health.lims.common.util.ConfigurationProperties.Property,
			     us.mn.state.health.lims.common.util.*" %>


<%@ taglib uri="/tags/struts-bean"		prefix="bean" %>
<%@ taglib uri="/tags/struts-html"		prefix="html" %>
<%@ taglib uri="/tags/struts-logic"		prefix="logic" %>
<%@ taglib uri="/tags/labdev-view"		prefix="app" %>
<%@ taglib uri="/tags/sourceforge-ajax" prefix="ajax"%>
<%@ taglib prefix="nested" uri="http://jakarta.apache.org/struts/tags-nested" %>

<bean:define id="formName"	value='<%=(String) request.getAttribute(IActionConstants.FORM_NAME)%>' />
<bean:define id="localDBOnly" value='<%=Boolean.toString(ConfigurationProperties.getInstance().getPropertyValueLowerCase(Property.UseExternalPatientInfo).equals("false"))%>' />
<bean:define id="patientSearch" name='<%=formName%>' property='patientSearch' type="us.mn.state.health.lims.patient.action.bean.PatientSearch" />

<%!
	IAccessionNumberValidator accessionNumberValidator;
	boolean supportSTNumber = true;
	boolean supportMothersName = true;
	boolean supportSubjectNumber = true;
	boolean supportNationalID = true;
	boolean supportLabNumber = false;
	String basePath = "";
 %>

 <%
 	supportSTNumber = FormFields.getInstance().useField(Field.StNumber);
  	supportMothersName = FormFields.getInstance().useField(Field.MothersName);
  	supportSubjectNumber = FormFields.getInstance().useField(Field.SubjectNumber);
  	supportNationalID = FormFields.getInstance().useField(Field.NationalID);
  	supportLabNumber = FormFields.getInstance().useField(Field.SEARCH_PATIENT_WITH_LAB_NO);
 	accessionNumberValidator = new AccessionNumberValidatorFactory().getValidator();
 	String path = request.getContextPath();
 	basePath = request.getScheme() + "://" + request.getServerName() + ":"	+ request.getServerPort() + path + "/";
 %>

<script type="text/javascript" src="<%=basePath%>scripts/ajaxCalls.js?ver=<%= Versioning.getBuildNumber() %>" ></script>
<script type="text/javascript">

var supportSTNumber = <%= supportSTNumber %>;
var supportMothersName = <%= supportMothersName %>;
var supportSubjectNumber = <%= supportSubjectNumber %>;
var supportNationalID = <%= supportNationalID %>;
var supportLabNumber = <%= supportLabNumber %>;
var patientSelectID;
var patientInfoHash = [];
var patientChangeListeners = [];
var localDB = '<%=localDBOnly%>' == "true";
var newSearchInfo = false;

function searchPatients() {
    var criteria = $jq("#searchCriteria").val();
    var value = $jq("#searchValue").val();
    var splitName;
    var lastName = "";
    var firstName = "";
    var STNumber = "";
    var subjectNumber = "";
    var nationalID = "";
    var labNumber = "";

	newSearchInfo = false;
    $jq("#resultsDiv").hide();
    $jq("#searchLabNumber").val('');
    if (criteria == 1) {
        firstName =  value.trim();
    } else if (criteria == 2) {
        lastName = value.trim();
    } else if (criteria == 3) {
        splitName = value.split(",");
        lastName = splitName[0].trim();
        firstName = splitName.size() == 2 ? splitName[1].trim() : "";
    } else if (criteria == 4) {
        STNumber = value.trim();
        subjectNumber = value.trim();
        nationalID = value.trim();
    } else if (criteria == 5) {
        labNumber = value;
        $jq("#searchLabNumber").val(value);
    }

	patientSearch(lastName, firstName, STNumber, subjectNumber, nationalID, labNumber, "", false, processSearchSuccess);
}

function processSearchSuccess(xhr) {
	//alert(xhr.responseText);
	var formField = xhr.responseXML.getElementsByTagName("formfield").item(0);
	var message = xhr.responseXML.getElementsByTagName("message").item(0);
	var table = $("searchResultTable");

	clearTable(table);
	clearPatientInfoCache();

	if (message.firstChild.nodeValue == "valid") {
		$("noPatientFound").hide();
		$("searchResultsDiv").show();
		var resultNodes = formField.getElementsByTagName("result");
		for(var i = 0; i < resultNodes.length; i++) {
			addPatientToSearch(table, resultNodes.item(i));
		}
		
		if (resultNodes.length == 1 && <%= String.valueOf(patientSearch.isLoadFromServerWithPatient()) %>) {
			handleSelectedPatient();
		}
	} else {
		$("searchResultsDiv").hide();
		$("noPatientFound").show();
		selectPatient(null);
	}
}

function clearSearchResultTable() {
	var table = $("searchResultTable");
	clearTable(table);
	clearPatientInfoCache(); 
}

function clearTable(table) {
	var rows = table.rows.length - 1;
	while (rows > 0) {
		table.deleteRow(rows--);
	}
}

function clearPatientInfoCache() {
	patientInfoHash = [];
}

function addPatientToSearch(table, result) {
	var patient = result.getElementsByTagName("patient")[0];

	var firstName = getValueFromXmlElement(patient, "first");
	var lastName = getValueFromXmlElement(patient, "last");
	var gender = getValueFromXmlElement(patient, "gender");
	var DOB = getValueFromXmlElement(patient, "dob");
	var stNumber = getValueFromXmlElement(patient, "ST");
	var subjectNumber = getValueFromXmlElement(patient, "subjectNumber");
	var nationalID = getValueFromXmlElement(patient, "nationalID");
	var mother = getValueFromXmlElement(patient, "mother");
	var pk = getValueFromXmlElement(result, "id");
	var dataSourceName = getValueFromXmlElement(result, "dataSourceName");

	var row = createRow(table, firstName, lastName, gender, DOB, stNumber, subjectNumber, nationalID, mother, pk, dataSourceName);
	addToPatientInfo(firstName, lastName, gender, DOB, stNumber, subjectNumber, nationalID, mother, pk);

	if (row == 1) {
		patientSelectID = pk;
		$("sel_1").checked = "true";
		selectPatient(pk);
	}
}

function getValueFromXmlElement(parent, tag) {
	var element = parent.getElementsByTagName(tag).item(0);
	return element ? element.firstChild.nodeValue : "";
}

function createRow(table, firstName, lastName, gender, DOB, stNumber, subjectNumber, nationalID, mother, pk,  dataSourceName) {
		var row = table.rows.length;
		var newRow = table.insertRow(row);
		newRow.id = "_" + row;
		var cellCounter = -1;
		var selectionCell = newRow.insertCell(++cellCounter);
		if (!localDB) {
			var dataSourceCell = newRow.insertCell(++cellCounter);
			dataSourceCell.innerHTML = nonNullString(dataSourceName);
		}
		var lastNameCell = newRow.insertCell(++cellCounter);
		var firstNameCell = newRow.insertCell(++cellCounter);
		var genderCell = newRow.insertCell(++cellCounter);
		var dobCell = newRow.insertCell(++cellCounter);
		var motherCell = supportMothersName ? newRow.insertCell(++cellCounter) : null;
		var stCell = supportSTNumber ? newRow.insertCell(++cellCounter) : null;
		var subjectNumberCell = supportSubjectNumber ? newRow.insertCell(++cellCounter) : null;
		var nationalCell = supportNationalID ? newRow.insertCell(++cellCounter) : null;
		selectionCell.innerHTML = getSelectionHtml(row, pk);
		lastNameCell.innerHTML = nonNullString(lastName);
		firstNameCell.innerHTML = nonNullString(firstName);
		genderCell.innerHTML = nonNullString(gender);
		if (supportSTNumber) {stCell.innerHTML = nonNullString(stNumber);}
		if (supportSubjectNumber) {subjectNumberCell.innerHTML = nonNullString(subjectNumber);}
		if (supportNationalID) {nationalCell.innerHTML = nonNullString(nationalID);}

		dobCell.innerHTML = nonNullString(DOB);
		if (supportMothersName) {motherCell.innerHTML = nonNullString(mother);}

		return row;
}

function getSelectionHtml(row, key) {
	return "<input name='selPatient' id='sel_" + row + "' value='" + key + "' onclick='selectPatient(this.value)' type='radio'>";
}

function /*String*/ nonNullString(target) {
	return target == "null" ? "" : target;
}

function addToPatientInfo(firstName, lastName, gender, DOB, stNumber, subjectNumber, nationalID, mother, pk) {
	var info = [];
	info["first"] = nonNullString(firstName);
	info["last"] = nonNullString(lastName);
	info["gender"] = nonNullString(gender);
	info["DOB"] = nonNullString(DOB);
	info["ST"] = nonNullString(stNumber);
	info["subjectNumber"] = nonNullString(subjectNumber);
	info["national"] = nonNullString(nationalID);
	info["mother"] = nonNullString(mother);

	patientInfoHash[pk] = info;
}


function selectPatient(patientID) {
    var i;
	if (patientID) {
		patientSelectID = patientID;

		var info = patientInfoHash[patientID];

		for(i = 0; i < patientChangeListeners.length; i++) {
			patientChangeListeners[i](info["first"],info["last"],info["gender"],info["DOB"],info["ST"],info["subjectNumber"],info["national"],info["mother"], patientID);
		}

	}else{
		for(i = 0; i < patientChangeListeners.length; i++) {
			patientChangeListeners[i]("","","","","","","","", null);
		}
	}
}

function /*void*/ addPatientChangedListener(listener) {
	patientChangeListeners.push(listener);
}


function /*void*/ handleEnterEvent() {
		if (newSearchInfo) {
			searchPatients();
		}
		return false;
}

function /*void*/ dirtySearchInfo(e) { 
	var code = e ? e.which : window.event.keyCode;
	if (code != 13) {
		newSearchInfo = true; 
	}
}
function /*void*/ doNothing() { 
	
}

function checkIndex(select) {
	var indexVal = select.options[select.selectedIndex].value;
	if (indexVal == "5") {
		$jq("#scanInstruction").show();
	} else {
		$jq("#scanInstruction").hide();
	}
}

function enableSearchButton(eventCode) {
    var valueElem = $jq("#searchValue");
    var criteriaElem  = $jq('#searchCriteria');
    var searchButton = $jq("#searchButton");
    if (valueElem.val() && criteriaElem.val() != "0" && valueElem.val() != '<%=StringUtil.getMessageForKey("label.select.search.here")%>') {
        searchButton.removeAttr("disabled");
        if (eventCode == 13) {
            searchButton.click();
        }
    } else {
        searchButton.attr("disabled", "disabled");
    }

    if (criteriaElem.val() == "5") {
        valueElem.attr("maxlength","<%= Integer.toString(accessionNumberValidator.getMaxAccessionLength()) %>");
    } else {
        valueElem.attr("maxlength","120");
    }
}

function handleSelectedPatient() {
    var accessionNumber = "";
    if ($jq("#searchCriteria").val() == 5) {//lab number
        accessionNumber = $jq("#searchValue").val();
    }

    $("searchResultsDiv").style.display = "none";
    var form = document.forms[0];
    form.action = '<%=formName%>'.sub('Form','') + ".do?accessionNumber=" + accessionNumber + "&patientID=" + patientSelectID;
    if (!(typeof requestType === 'undefined')) {
        form.action += "&type=" + requestType;
    }
    
    form.submit();
}

function firstClick() {
    var searchValue = $jq("#searchValue");
    searchValue.val("");
    searchValue.removeAttr("onkeydown");
}

function messageRestore(element) {
    if (!element.value) {
        element.maxlength = 120;
        element.value = '<%=StringUtil.getMessageForKey("label.select.search.here")%>';
        element.onkeydown = firstClick;
        setCaretPosition(element, 0);
    }
}

function cursorAtFront(element) {
    if (element.onkeydown) {
        setCaretPosition(element, 0);
    }
}

function setCaretPosition(ctrl, pos) {
    if (ctrl.setSelectionRange) {
        ctrl.focus();
        ctrl.setSelectionRange(pos,pos);
    } else if (ctrl.createTextRange) {
        var range = ctrl.createTextRange();
        range.collapse(true);
        range.moveEnd('character', pos);
        range.moveStart('character', pos);
        range.select();
    }
}
</script>

<input type="hidden" id="searchLabNumber">

<div id="PatientPage" class="colorFill patientSearch" style="display:inline;" >

	<h3><bean:message key="sample.entry.search"/></h3>
    <logic:present property="warning" name="<%=formName%>" >
        <h3 class="important-text"><bean:message key="order.modify.search.warning" /></h3>
    </logic:present>
    <select id="searchCriteria"  style="float:left" onchange="checkIndex(this)" tabindex="1" class="patientSearch">
        <%
            for(IdValuePair pair : patientSearch.getSearchCriteria()) {
                out.print("<option value=\"" + pair.getId() +"\">" + pair.getValue() + "</option>");
            }
        %>
    </select>

    <input size="35"
           maxlength="120"
           id="searchValue"
           class="text patientSearch"
           value='<%=StringUtil.getMessageForKey("label.select.search.here")%>'
           type="text"
           onclick="cursorAtFront(this)"
           onkeydown='firstClick();'
           onkeyup="messageRestore(this);enableSearchButton(event.which);"
            tabindex="2"/>

    <input type="button"
           name="searchButton"
           class="patientSearch"
           value="<%= StringUtil.getMessageForKey("label.patient.search")%>"
           id="searchButton"
           onclick="searchPatients()"
           disabled="disabled" >
           
  	<span id="scanInstruction" style="display: none;"><bean:message key="sample.search.scanner.instructions"/> </span>

	<div id="noPatientFound" align="center" style="display: none" >
		<h1><bean:message key="patient.search.not.found"/></h1>
	</div>
	<div id="searchResultsDiv" class="colorFill" style="display: none;" >
		<% if (localDBOnly.equals("false")) { %>
		<table id="searchResultTable" style="width:90%">
			<tr>
				<th width="2%"></th>
				<th width="10%" >
					<bean:message key="patient.data.source" />
				</th>
		<% } else { %>
		<table id="searchResultTable" width="70%">
			<tr>
				<th width="2%"></th>
		<% } %>
				<th width="18%">
					<bean:message key="patient.epiLastName"/>
				</th>
				<th width="15%">
					<bean:message key="patient.epiFirstName"/>
				</th>
				<th width="5%">
					<bean:message key="patient.gender"/>
				</th>
				<th width="11%">
					<bean:message key="patient.birthDate"/>
				</th>
				<% if (supportMothersName) { %>
				<th width="20%">
					<bean:message key="patient.mother.name"/>
				</th>
				<% } if (supportSTNumber) { %>
				<th width="12%">
					<bean:message key="patient.ST.number"/>
				</th>
				<% } %>
				<% if (supportSubjectNumber) { %>
				<th width="12%">
					<bean:message key="patient.subject.number"/>
				</th>
				<% } %>
				<% if (supportNationalID) { %>
				<th width="12%">
                    <%=StringUtil.getContextualMessageForKey("patient.NationalID") %>
                </th>
                <% } %>
			</tr>
		</table>
		<br/>
        <% if (patientSearch.getSelectedPatientActionButtonText() != null) { %>
            <input type="button"
                   value="<%= patientSearch.getSelectedPatientActionButtonText()%>"
                   id="selectPatientButtonID"
                   onclick="handleSelectedPatient()">
        <% }%>
		</div>
	</div>

