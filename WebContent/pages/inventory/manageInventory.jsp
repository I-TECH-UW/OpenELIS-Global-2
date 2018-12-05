<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page import="us.mn.state.health.lims.common.action.IActionConstants,
				us.mn.state.health.lims.inventory.form.InventoryKitItem,
				us.mn.state.health.lims.common.util.DateUtil,
				us.mn.state.health.lims.common.util.IdValuePair,
				us.mn.state.health.lims.common.util.StringUtil,
				java.util.List" %>


<%@ taglib uri="/tags/struts-bean"		prefix="bean" %>
<%@ taglib uri="/tags/struts-html"		prefix="html" %>
<%@ taglib uri="/tags/struts-logic"		prefix="logic" %>
<%@ taglib uri="/tags/labdev-view"		prefix="app" %>
<%@ taglib uri="/tags/sourceforge-ajax" prefix="ajax" %>

<bean:define id="formName"	value='<%=(String) request.getAttribute(IActionConstants.FORM_NAME)%>' />
<bean:define id="kitSources" name="<%=formName %>" property="sources" type="List<IdValuePair>" />
<bean:define id="kitTypeList" name="<%=formName %>" property="kitTypes" type="List<String>" />
<bean:size id="currentKitCount" name="<%=formName %>" property="inventoryItems"/>
<script type="text/javascript" language="JavaScript1.2">

var dirty = false;
var nextKitValue = 	<%= currentKitCount%> + 1;
var errorColor = "#ff0000";
var DatePattern = /^((((0?[1-9]|[12]\d|3[01])[\.\-\/](0?[13578]|1[02])[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|[12]\d|30)[\.\-\/](0?[13456789]|1[012])[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|1\d|2[0-8])[\.\-\/]0?2[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|(29[\.\-\/]0?2[\.\-\/]((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00)))|(((0[1-9]|[12]\d|3[01])(0[13578]|1[02])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|[12]\d|30)(0[13456789]|1[012])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|1\d|2[0-8])02((1[6-9]|[2-9]\d)?\d{2}))|(2902((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00))))$/;
var TEST_KIT_PROTOTYPE_ID = '<input name="inventoryLocationId" size="3" value="" disabled="disabled" type="text">';
var TEST_KIT_PROTOTYPE_NAME = '<input name="kitName" value="" type="text" class="kitName" onblur="checkIfEmpty(this);">';
var TEST_KIT_PROTOTYPE_TYPE = '<select name="type" class="kitType" > \
								<option value="" ></option> ' +
								'<%
									for( String kitTypeValue : kitTypeList ){
									out.print("<option value=\"" + kitTypeValue + "\">" + kitTypeValue +"</option>" );
									}
								%>'
								+ '</select>';
var TEST_KIT_PROTOTYPE_RECEIVE = '<input name="receiveDate" class="receiveDate" size="12" value="" type="text" onblur="validateDate(this);">';
var TEST_KIT_PROTOTYPE_EXPIRE = '<input name="expirationDate" class="expirationDate" size="12" value=""  type="text" onblur="validateDate(this);">';
var TEST_KIT_PROTOTYPE_LOT = '<input name="lotNumber" class="lotNumber" size="6" value="" type="text">';
var TEST_KIT_PROTOTYPE_SOURCE = '<select name="organizationId" class="organizationId" > \
								' +

								'<%
									for( IdValuePair pair : kitSources ){
									out.print("<option value=\"" + pair.getId() + "\">" + pair.getValue() +"</option>" );
									}
								%>'
								+ '</select>';
var TEST_KIT_PROTOTYPE_REMOVE = '<input name="removeButton" value="' + '<bean:message key="label.button.remove"/>' + '" onclick="removeTestKit(#);" class="textButton" type="button">';

function validateDate( id ) {
	id.value = id.value.strip();

    if (((id.value.match(DatePattern)) && (id.value!=='')) || (id.value=='')) {
    	id.style.borderColor = "";
    	return true;
    } else {
    	id.style.borderColor  = errorColor;
    	return false;
    }
}

function checkIfEmpty( field ) {
	field.style.borderColor = field.value ? "" :errorColor;
}

function /*void*/ showInactiveKits( sendingButton ){
	sendingButton.hide();
	$('hideInactiveButton').show();
	$('inactiveInventoryItems').show();
}

function /*void*/ hideInactiveKits( sendingButton ){
    sendingButton.hide();
	$('showInactiveButton').show();
	$('inactiveInventoryItems').hide();
}

function /*void*/ deactivateTestKit( index ){
	$("activeRow_" + index).hide();
	$("inactiveRow_" + index).show();
	$("isActive_" + index ).value = false;
	setModifiedFlag(index);
}

function /*void*/ reactivateTestKit( index ){
	$("activeRow_" + index).show();
	$("inactiveRow_" + index).hide();
	$("isActive_" + index ).value = true;
	setModifiedFlag(index);
}

function /*void*/ setModifiedFlag( index ){
	$("isModified_" + index ).value = true;
	makeDirty();
}

function /*void*/ addNewTestKit(){

	var testKitTable = $("testKitTable");
	var rows = testKitTable.rows;
	var origionalRowCount = rows.length;
	var newRow = testKitTable.insertRow( origionalRowCount );
	//N.B. assigning innerHTML to a row does not work for IE
	newRow.insertCell(0).innerHTML = TEST_KIT_PROTOTYPE_ID;
	newRow.insertCell(1).innerHTML = TEST_KIT_PROTOTYPE_NAME;
	newRow.insertCell(2).innerHTML = TEST_KIT_PROTOTYPE_TYPE;
	newRow.insertCell(3).innerHTML = TEST_KIT_PROTOTYPE_RECEIVE;
	newRow.insertCell(4).innerHTML = TEST_KIT_PROTOTYPE_EXPIRE;
	newRow.insertCell(5).innerHTML = TEST_KIT_PROTOTYPE_LOT;
	newRow.insertCell(6).innerHTML = TEST_KIT_PROTOTYPE_SOURCE;
	newRow.insertCell(7).innerHTML = TEST_KIT_PROTOTYPE_REMOVE.replace(/\#/g, nextKitValue);

	newRow.id = "addedKit_" + nextKitValue;
	newRow.className = "addedKit";
	nextKitValue++;

	makeDirty();
	//alert( newRow.innerHTML );
}

function /*void*/ removeTestKit( index ){
	var testKitTable = $("testKitTable");
	var row = $("addedKit_" + index);
	testKitTable.deleteRow( row.rowIndex );
}


function /*void*/ createNewKitXML(){
	var xml = "<?xml version='1.0' encoding='utf-8'?><newKits>";
	var newKitRows = $$('tr.addedKit');
	var rowsLength = newKitRows.length;
    var i;

	for( var rowIndex = 0; rowIndex < rowsLength; ++rowIndex ){

		xml += "<kit ";

		var row = newKitRows[rowIndex];
		var cells = row.getElementsByTagName("input");
		var cellsLength = cells.length;

		for( i = 1; i < cellsLength; ++i ){ //the first cell is the kit id #

			xml += cells[i].className + "=\"";
			xml += cells[i].value;
			xml += "\" ";
		}

		cells = row.getElementsByTagName("select");
		cellsLength = cells.length;

		for( i = 0; i < cellsLength; ++i ){

			xml += cells[i].className + "=\"";
			xml += cells[i].value;
			xml += "\" ";
		}

		xml += " />";
	}

	xml += "</newKits>";

	$('newKits').value = xml;
}

function  /*void*/ setMyCancelAction(form, action, validate, parameters)
{
	//first turn off any further validation
	setAction(window.document.forms[0], 'Cancel', 'no', '');
}

function  /*void*/ savePage()
{
	var receiveDates, expirationDates,i;
	var kits = $$("input.kitName");
		
	for(i = 0; i < kits.length; ++i) {
		if( kits[i].value == null || kits[i].value == "" ) {
			alert("Le nom est obligatoire");
			return;
		}
	}

	receiveDates = $$("input.receiveDate");

	for(i = 0; i < receiveDates.length; ++i) {
		if( !validateDate(receiveDates[i]) ){
			alert("Mal formaté la date");
			return;
		}
	}
	
	expirationDates = $$("input.expirationDate");
	
	for(i = 0; i < expirationDates.length; ++i) {
		if( !validateDate(expirationDates[i]) ){
			alert("Mal formaté la date");
			return;
		}
	}
	
	
	createNewKitXML();
  
	window.onbeforeunload = null; // Added to flag that formWarning alert isn't needed.
	var form = window.document.forms[0];
	form.action = "ManageInventoryUpdate.do";
	form.submit();

}

function /*void*/ makeDirty(){
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

<div id="PatientPage" class="colorFill" style="display:inline" >
	<logic:present name="<%=formName%>" property="inventoryItems" >
	<html:hidden name="<%=formName%>"  property="newKitsXML" styleId="newKits" />
	<table id="testKitTable"   style="width:100%" >
	<tr >
		<th style="width:5%">
			<bean:message key="inventory.testKit.id"/>
		</th>
		<th style="width:15%">
			<bean:message key="inventory.testKit.name"/>
			<span class="requiredlabel">*</span>
		</th>
		<th style="width:10%">
  			<bean:message key="inventory.testKit.type"/>
		</th>
		<th style="width:10%">
			<bean:message key="inventory.testKit.receiveDate"/><br><span style="font-size: xx-small; "><%=DateUtil.getDateUserPrompt()%></span>
		</th>
		<th style="width:10%">
			<bean:message key="inventory.testKit.expiration"/><br><span style="font-size: xx-small; "><%=DateUtil.getDateUserPrompt()%></span>
		</th>
		<th style="width:10%">
			<bean:message key="inventory.testKit.lot"/>
		</th>
		<th style="width:20%">
			<bean:message key="inventory.testKit.source"/>
			<span class="requiredlabel">*</span>
		</th>
		<th style="width:10%">
		</th>
	</tr>
	<logic:iterate id="inventoryItems"  name="<%=formName%>" property="inventoryItems" indexId="index" type="InventoryKitItem" >
		<tr <% if(!inventoryItems.getIsActive()){out.print("style=\"display:none\"");} %> id='<%="activeRow_" + index %>' >
			<td >
				<html:hidden indexed="true" name="inventoryItems" property="isActive" styleId='<%= "isActive_" + index %>'/>
				<html:hidden indexed="true" name="inventoryItems" property="isModified" styleId='<%= "isModified_" + index %>'/>
				<html:text  indexed="true" name="inventoryItems" property="inventoryLocationId" disabled="true" size="3" />
			</td>
			<td>
			<html:text  indexed="true"
			            name="inventoryItems"
			            property="kitName"
			            styleClass="kitName"
			            onchange='<%="setModifiedFlag(" + index + ");" %>'
			            onblur="checkIfEmpty(this);"/>
			</td>
			<td >
				<html:select indexed="true"
							 name="inventoryItems"
							 property="type"  value='<%=inventoryItems.getType()%>'
							 onchange= '<%=" setModifiedFlag(" + index + ");" %>' >
				    <option value="" ></option>
					<html:options name="<%=formName%>" property="kitTypes" />
				</html:select>
			</td>
			<td >
				<html:text  indexed="true"
							name="inventoryItems"
							property="receiveDate"
							styleClass="receiveDate"
							size="12"
							onchange='<%="setModifiedFlag(" + index + "); " %>' onblur="validateDate(this);"/>
			</td>
			<td >
				<html:text  indexed="true"
				            name="inventoryItems"
				            property="expirationDate"
				            styleClass="expirationDate"
				            size="12"
				            onchange='<%="setModifiedFlag(" + index + ");" %>' onblur="validateDate(this);"/>
			</td>
			<td >
				<html:text  indexed="true"
				            name="inventoryItems"
				            property="lotNumber"
				            size="6"
				            onchange='<%="setModifiedFlag(" + index + ");" %>'/>
			</td>
			<td >
						<html:select indexed="true"
				             name="inventoryItems"
				             property="organizationId"
				             value='<%=inventoryItems.getOrganizationId()%>'
				             onchange='<%="setModifiedFlag(" + index + ");" %>'>

					<html:optionsCollection name="<%=formName%>" property="sources" value="id" label="value"/>
				</html:select>
			</td>
			<td >
                <input type="button"
                       value='<%= StringUtil.getMessageForKey("label.button.deactivate")%>'
                       onclick='<%= "deactivateTestKit(" + index + ");"%>'
                       class="textButton">
			</td>
		</tr>
	</logic:iterate>
	</table>
	<div id="inactiveInventoryItems" style="display:none" >
	<hr>

	<table style="width:100%" >
	<tr>
		<th colspan="8"  align="left"><bean:message key="invnetory.testKit.inactiveKits"/></th>
	</tr>
	<logic:iterate id="item"  name="<%=formName%>" property="inventoryItems" indexId="index" type="InventoryKitItem" >
		<tr <% if(item.getIsActive()){out.print("style=\"display:none\"");} %> id='<%="inactiveRow_" + index %>' >
			<td style="width:5%">
				<html:text name="item" property="inventoryLocationId" disabled="true" size="3" />
			</td>
			<td style="width:15%">
				<html:text name="item" property="kitName" disabled="true" />
			</td>
			<td style="width:10%" >
				<html:text name="item" property="type" disabled="true" size="12" />
			</td>
			<td style="width:10%">
				<html:text name="item" property="receiveDate" disabled="true" size="12" />
			</td>
			<td style="width:10%">
				<html:text name="item" property="expirationDate" disabled="true" size="12" />
			</td>
			<td style="width:10%">
				<html:text name="item" property="lotNumber" disabled="true" size="6" />
			</td>
			<td style="width:20%">
				<html:text name="item" property="source" disabled="true" />
			</td>
			<td style="width:10%">
                    <input type="button"
                           value='<%= StringUtil.getMessageForKey("label.button.reactivate")%>'
                           onclick='<%= "reactivateTestKit(" + index + ");"%>'
                           class="textButton">
			</td>
		</tr>
	</logic:iterate>
	</table>
	</div>
	<br/>
        <input type="button"
                   value='<%= StringUtil.getMessageForKey("inventory.testKit.showAll")%>'
                   onclick="showInactiveKits( this );"
                   class="textButton"
                   id="showInactiveButton">
        <input type="button"
               value='<%= StringUtil.getMessageForKey("inventory.testKit.hideInactive")%>'
               onclick="hideInactiveKits( this );"
               class="textButton"
               style="display:none"
               id="hideInactiveButton">
        <input type="button"
               value='<%= StringUtil.getMessageForKey("inventory.testKit.add")%>'
               onclick="addNewTestKit();"
               class="textButton">
</logic:present>
<logic:notPresent name="<%=formName%>" property="inventoryItems" >
	<bean:message key="inventory.testKit.none"/>
</logic:notPresent>


</div>
