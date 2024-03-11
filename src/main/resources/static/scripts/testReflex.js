if(window.Prototype) {
    delete Object.prototype.toJSON;
    delete Array.prototype.toJSON;
    delete Hash.prototype.toJSON;
    delete String.prototype.toJSON;
}

function showUserReflexChoices( index, resultId, sibIndex )
{
	var analysisElement = $("analysisId_" + index );
	var analysisId =  analysisElement ? analysisElement.value : "";
	var accessionElement = $("accessionNumberId_" + index);
	var accessionNumber = accessionElement ? accessionElement.value : "";
	var testId = $("testId_" + index ).value;

	var sibResultId = sibIndex ? $("resultId_" + sibIndex).value : null;
	var sibAnalysisId = sibIndex ? $("analysisId_" + sibIndex ).value : null;
	var sibTestId = sibIndex ? $("testId_" + sibIndex ).value : null;

	getReflexUserChoice( resultId, analysisId, testId, accessionNumber, index, processTestReflexSuccess);
}

function processTestReflexSuccess(xhr)
{
	//alert( xhr.responseText );
	var formField = xhr.responseXML.getElementsByTagName("formfield").item(0);
	var message = xhr.responseXML.getElementsByTagName("message").item(0);

	if (message.firstChild.nodeValue == "valid"){
        buildPopUp(formField.firstChild.textContent, true);
	}
}

function buildPopUp(rawResponse, showPopup){

    var response = JSON.parse( rawResponse);
    var rowIndex = response["rowIndex"];
    var selections = response["selections"];
    var i, selected;

    jQuery(".modal-body #testRow").val(rowIndex);
    jQuery(".modal-body #targetIds").val(response["triggerIds"]);
    jQuery(".modal-body #serverResponse").val( encodeJSONStringToHTML( rawResponse) );
    jQuery(".selection_element").remove();
    jQuery("#modal_ok").attr('disabled','disabled');
    for( i = 0; i < selections.length; i++){
        selected = jQuery.inArray(selections[i]["value"], response["selected"]) != -1;
        jQuery(".modal-body").append(getSelectionRow(selections[i]["name"], selections[i]["value"], i, selected));
    }
    jQuery(".modal-body #selectAll").prop('checked', false);
    jQuery(".selection_element").change( function(){ checkForCheckedReflexes(); });
    jQuery("#headerLabel").text(response["triggers"]);

    if( showPopup){
        showReflexSelection();
    }
}
function getSelectionRow(name, value, index, selected ){
    var tempWrapper = document.createElement('div');
    var p = document.createElement('p');
    p.setAttribute('class', 'selection_element')
    var input = document.createElement('input');
    input.setAttribute('style', 'vertical-align:text-bottom');
    input.setAttribute('id','selection_' + index);
    input.setAttribute('class', 'selectionCheckbox');
    input.setAttribute('value', value);
    input.setAttribute('type','checkbox');
    if (selected) {
        input.setAttribute('checked', 'checked');
    }
    input.innerText = '&nbsp;&nbsp;&nbsp;' + name;
    p.appendChild(input);
    tempWrapper.appendChild(p);
    return tempWrapper.innerHTML;

    // return "<p class='selection_element'><input style='vertical-align:text-bottom' id='selection_" +
    //     index + "' class='selectionCheckbox' value='" +
    //     value +  "' type='checkbox' " +
    //     check + ">&nbsp;&nbsp;&nbsp;" +
    //     name + "</p>";
}

function modalSelectAll(selectBox){
    if( jQuery(selectBox).prop('checked')){
        jQuery('.selectionCheckbox').prop('checked', true);
        jQuery("#modal_ok").removeAttr('disabled');
    } else{
        jQuery('.selectionCheckbox').prop('checked', false);
        jQuery("#modal_ok").attr('disabled','disabled');
    }
}

function checkForCheckedReflexes(){
    if( jQuery(".selectionCheckbox:checked").length == 0 ){
        jQuery("#modal_ok").attr('disabled','disabled');
    }else{
        jQuery("#modal_ok").removeAttr('disabled');
    }
}

function addReflexToTests( editLabel ){
    var index = jQuery(".modal-body #testRow").val();
    var tests = '';
    var parentRow = jQuery('#noteRow_' + index);
    var targetIds = jQuery(".modal-body #targetIds" ).val();
    var popupJSONString = encodeHTMLToJSONString(jQuery(".modal-body #serverResponse").val());
    var popupJSONResponse = JSON.parse(popupJSONString);
    var testJSONString = encodeHTMLToJSONString( jQuery("#reflexServerResultId_" + index ).val());
    var testJSONResponse = JSON.parse( testJSONString );
    var existingDisplay = jQuery("#reflexSelection_" + index + "_" + targetIds );
    var selectedReflexes = [];

    jQuery(".selectionCheckbox:checked").each(function(index, value){
            tests += jQuery.trim(jQuery(value).parent().text()) + ", ";
            selectedReflexes.push(value.value);
    });

    tests = tests.substr(0, tests.length - 2 );

    if( existingDisplay.length == 0 ){
        parentRow.after(getSelectedTestDisplay(parentRow.attr("class"), index, targetIds, jQuery("#headerLabel").text().split(":")[1], tests, editLabel ));
    }else{
        existingDisplay.children().children("#reflexedTests").text(tests);
    }

    popupJSONResponse["selected"] = selectedReflexes;

    testJSONResponse[index + "_" + targetIds ] = popupJSONResponse;
    jQuery("#reflexServerResultId_" + index ).val(encodeJSONStringToHTML(JSON.stringify(testJSONResponse)));

}


function getSelectedTestDisplay( classValue, index, targetIds, parent, tests, editLabel){
	var wrapper = document.createElement('div');
	
	var tableRow = document.createElement('tr');
	tableRow.setAttribute('id', 'reflexSelection_' + index + '_' + targetIds);
	tableRow.setAttribute('class', classValue + ' reflexSelection_' + index);
	wrapper.appendChild(tableRow);
	
	var rowCell = document.createElement('td');
	tableRow.setAttribute('colspan', '5');
	tableRow.setAttribute('style', 'text-align:right');
	tableRow.setTextContent('parent');
	tableRow.appendChild(rowCell);
	
	rowCell = document.createElement('td');
	tableRow.setAttribute('colspan', '3');
	var textArea = document.createElement('textArea');
	textArea.setAttribute('readOnly', 'true');
	textArea.setAttribute('id', 'reflexedTests');
	textArea.setAttribute('rows', '2');
	textArea.setAttribute('style', 'width:98%');
	textArea.value = tests;
	rowCell.appendChild(textArea);
	tableRow.appendChild(rowCell);
	
	rowCell = document.createElement('td');
	tableRow.setAttribute('colspan', '1');
	tableRow.setAttribute('style', 'text-align:left');
	var inputButton = document.createElement('input');
	inputButton.setAttribute('type', 'button');
	inputButton.setAttribute('value', editLabel);
	inputButton.setAttribute('onclick', 'editReflexes("' + index + '", "' + targetIds + '");');
	rowCell.appendChild(inputButton);
	tableRow.appendChild(rowCell);
	
	rowCell = document.createElement('td');
	tableRow.setAttribute('id', 'reflexSelection_' + index + '_' + targetIds);
	tableRow.appendChild(rowCell);
	
	return wrapper.innerHTML;
	
	
    // return "<tr id='reflexSelection_" + index + "_" + targetIds + "' class='" + classValue + " reflexSelection_" + index + "'  >" +
    //   "<td colspan='5' style='text-align:right'>" + parent + "</td>" +
    //    "<td colspan='3'><textarea  readonly='true' id='reflexedTests' rows='2' style='width:98%' >" + tests + "</textarea></td>" +
    //    "<td colspan='1' style='text-align: left'><input type='button' value='" + editLabel + "' onclick=\"editReflexes('" + index + "', '" + targetIds  + "');\"></td>"   +
    //"</tr>";
}

function removeReflexesFor( triggers, row){
    var JSONResponses = JSON.parse(encodeHTMLToJSONString( jQuery("#reflexServerResultId_" + row ).val()));
    JSONResponses[row + "_" + triggers] = null;
    jQuery("#reflexServerResultId_" + row ).val(encodeJSONStringToHTML(JSON.stringify(JSONResponses)));

    jQuery("#reflexSelection_" + row + "_" + triggers).remove();
}

function editReflexes(index, targetIds){
    var JSONResponses = JSON.parse(encodeHTMLToJSONString( jQuery("#reflexServerResultId_" + index ).val()));
    buildPopUp( JSON.stringify(JSONResponses[index + "_" + targetIds] ), true);
}

function loadPagedReflexSelections( editLabel){
    var JSONResponse;
    //get collection of fields and send to buildpopup with new flag to stop actual popup
    jQuery(".reflexJSONResult").each( function(){
        if(jQuery(this).val() ){
            JSONResponse = JSON.parse(encodeHTMLToJSONString( jQuery(this ).val()));
            for (var member in JSONResponse) {
                if (!JSONResponse.hasOwnProperty(member) || typeof(JSONResponse[member]) === "function"){
                    continue;
                }
                buildPopUp( JSON.stringify(JSONResponse[member] ), false);
                addReflexToTests( editLabel )
            }
        }
    });
}
function showReflexSelection( element ){
    jQuery('#reflexSelect').modal('show');
}

function encodeJSONStringToHTML( json){
    return json.replace( /\"/g, "'");
}

function encodeHTMLToJSONString( html ){
    return html && html.length > 0 ? html.replace( /'/g, "\"") : "{}" ;
}