/*
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations under
 * the License.
 *
 * The Original Code is OpenELIS code.
 *
 * Copyright (C) ITECH, University of Washington, Seattle WA.  All Rights Reserved.
 */

function handleMultiSelectChange(e, data) {
    var splitSource = e.target.id.split("_");
    var major = splitSource[0] + "_" + splitSource[1];
    var minorKey = splitSource[2];
    var accumulator = jQuery("#multi" + major);

    if (data.type == "add") {
        appendValueForMultiSelect(accumulator, minorKey, data.value);
        if (jQuery("#" + e.target.id).hasClass("userSelection")) {
            showUserReflexChoices(splitSource[1], data.value);
        }
    } else { //drop
        removeReflexesFor(data.value, splitSource[1]);
        removeValueForMultiSelect(accumulator, minorKey, data.value);
    }

    //The extra split is to handle the additional tests on referral page
    jQuery("#modified_" + splitSource[1].split("-")[0]).val("true");

    makeDirty();

    jQuery("#saveButtonId").removeAttr("disabled");
}

function appendValueForMultiSelect( element, key, addString ){
    var currentValues = element.val();
    var currentHash = {};
    if (!currentValues ) {
        currentHash[key] = addString;
    } else {
        currentHash = JSON.parse(currentValues);
        currentHash[key] = concatenateToCSV(currentHash[key], addString);
    }

    element.val(encodeHTMLToJSONString(JSON.stringify(currentHash)));
}

function removeValueForMultiSelect(element, key, removeString){
    var currentValues = element.val();
    var currentHash = {};
    var newString;
    if( !currentValues ){
        return;
    }

    currentHash = JSON.parse(currentValues);
    newString = removeFromCSV(currentHash[key], removeString);
    if( !newString){
        delete currentHash[key];
    }else{
        currentHash[key] = newString;
    }

    element.val(encodeHTMLToJSONString(JSON.stringify(currentHash)));

}
function concatenateToCSV( oldString, addString ){

    if( oldString && oldString.length > 1 ){
        return oldString + ',' + addString;
    }

    return addString;
}

function removeFromCSV( oldString, removeString){
    var splitValues =  oldString.split(",");
    var newString, i;

    for( i = 0; i < splitValues.length; i++ ){
        if( splitValues[i] != removeString ){
            newString = concatenateToCSV(newString, splitValues[i]);
        }
    }

    return newString;
}

function  removeAllMultiSelectionsFor( majorMinorTarget ){
    var splitSource = majorMinorTarget.split("_");
    var accumulator = jQuery("#multiresultId_" + splitSource[0]);
    var currentValue = accumulator.val();
    var currentHash = {};

    if( !currentValue){
        return;
    }

    currentHash = JSON.parse(currentValue);
    delete currentHash[splitSource[1]];
    accumulator.val(encodeHTMLToJSONString(JSON.stringify(currentHash)));
}

function loadMultiSelects(){
    jQuery(".multiSelectValues").each(function(i,element){loadMultSelect(element);});
}

function loadMultSelect( element ){
    var selections = JSON.parse(element.value);
    var index = element.id.split("_")[1];
    var key, selector, options, i;
    var keys = [];

    for( key in selections){
        if( selections.hasOwnProperty(key)){
            keys.push(key);
        }
    }

    keys.sort(function(a,b){return parseInt(a) - parseInt(b);});
    for( i = 0; i < keys.length; i++){
        options = selections[keys[i]].split(",");
        if( keys[i] != 0) {
            createNewMultiSelect( index, keys[i] );
        }

        jQuery("#resultId_" + index + "_" + keys[i] + " option").each(function (i, element) {
            if (options.indexOf(element.value) != -1) {
                element.selected = "selected";
            }
        });
    }
}

function setSelected( i, element, options){
    if (options.indexOf(element.value) != -1) {
        element.selected = "selected";
    }
}
function createNewMultiSelect( index, minorIndex){
    var divCount = jQuery("#divCount_" + index);
    var nextDivCount = minorIndex;
    jQuery('<div></div>',{id: 'cascadingMulti_' + index + "_" + nextDivCount, class: 'cascadingMulti_' + index}).insertAfter(".cascadingMulti_" + index + ":last");

    var select = jQuery("#resultId_" + index + "_0").clone();
    select.find("option:selected").prop("selected", false);
    select.find("option").attr("id", "");
    select.attr("id", "resultId_" + index + "_" + nextDivCount);
    var add = jQuery(".addMultiSelect" + index).last().clone();
    var remove = jQuery(".removeMultiSelect" + index).first().clone();
       remove.attr("onclick", remove.attr("onclick").replace("target", index + "_" + nextDivCount));
    var newDiv = jQuery('#cascadingMulti_' + index + "_" + nextDivCount);
    jQuery(".addMultiSelect" + index).hide();

    select.appendTo(newDiv);
    add.appendTo(newDiv);
    remove.appendTo(newDiv);

    add.show();
    remove.show();
    select.show();

    divCount.val(nextDivCount);
    return select;
}

function addNewMultiSelect( index ){
    var nextDivCount = parseInt(jQuery("#divCount_" + index).val()) + 1;
    var newSelect = createNewMultiSelect(index, nextDivCount);

    newSelect.asmSelect({
        removeLabel: "X"
    });
    newSelect.change(function(e, data) {
        handleMultiSelectChange( e, data );
    });

    jQuery(".asmContainer").css("display","inline-block");
}

function removeMultiSelect(target){
    //ie chokes if we just remove the div so we need to empty it first
    jQuery("#cascadingMulti_" + target).empty();
    jQuery("#cascadingMulti_" + target).remove();
    jQuery(".addMultiSelect" + target.split("_")[0]).last().show();
    removeAllMultiSelectionsFor( target );
}
function resetCascadingMultiSelect(index) {
	// remove all divs except for the first multiselect block				
	jQuery('#cell_' + index).find('.removeMultiSelect' + index).each(function(i) { 
		jQuery(this).click();
	});					
	// remove list items 
	jQuery('#cascadingMulti_' + index + '_0').find('.asmListItemRemove').each(function(i) { 
		jQuery(this).click();
	});		
}
function disableCascadingMultiSelect(index){
	jQuery('#cascadingMulti_' + index + '_0').find('.asmSelect').each(function(i) { 
		jQuery(this).attr('disabled', 'true');
	});		        
	jQuery('#cascadingMulti_' + index + '_0').find('.addMultiSelect' + index).each(function(i) { 
		jQuery(this).attr('disabled', 'true');
	});		        
}
function enableCascadingMultiSelect(index) {
	jQuery('#cascadingMulti_' + index + '_0').find('.asmSelect').each(function(i) { 
		jQuery(this).removeAttr('disabled');
	});		        
	jQuery('#cascadingMulti_' + index + '_0').find('.addMultiSelect' + index).each(function(i) { 
		jQuery(this).removeAttr('disabled');
	});	
}
function resetMultiSelect(index) {
	// remove list items 
	jQuery('#cell_' + index).find('.asmListItemRemove').each(function(i) { 
		jQuery(this).click();
	});		
}
function disableMultiSelect(index){
	jQuery('#cell_' + index).find('.asmSelect').each(function(i) { 
		jQuery(this).attr('disabled', 'true');
	});	
}
function enableMultiSelect(index) {
	jQuery('#cell_' + index).find('.asmSelect').each(function(i) { 
		jQuery(this).removeAttr('disabled');
	});		        
}
