var buttonClicked = false;

function checkClicked(){
		if (buttonClicked == false){
			buttonClicked = true;
			return false;
		}else{
			return true;
		}
}

function clearClicked(){
	buttonClicked = false;
}
	
function getSessionFromURL(url) {
	 var sessionIndex = url.indexOf(';');
	 var sessionid = '';
	 if(sessionIndex >= 0){
		 var queryIndex = url.indexOf('?');
		 var length = url.length;
		 if (queryIndex > sessionIndex) {
		 	length = queryIndex;
		 }
		 sessionid = url.substring(sessionIndex,length);
	 }
	 return sessionid;
}

function validate(form) {
/*validateForm is defined in each individual form (see project.jsp, organization.jsp)*/
   /*alert("I am in validate(form)");*/
   var validated = validateForm(form);
   return validated;
}


function submitForm(form) {
//alert("I am in submitForm " + form);
	var validated = true;
	if ( form.action.indexOf('TestService')> 0)
		return false;
	else{
		if(!form.validateDocument){
		//alert("I am in submitForm2");
			return true;
		}
		else if(form.validateDocument.value == 'no') {
		//alert("I am in submitForm3");
			return true;
		}
		
		if(window.validate)
		{//alert("I am in submitForm4");
			validated = validate(form);
	
		if ( validated == false )
		{
		    //alert("I am in submitForm()5");
			clearClicked();
		}
		}
		return validated;
	}
}

function validateAndSubmitForm(form)
{//alert("I am in validateAndSubmitForm");
	if(submitForm(form))
	{//alert("submitting form " + form.action);
		form.submit();
	}
}


//bugzilla 1403 (dictionary entries with a plus sign are seen as invalid because not carried over properly)
function URLencode(sStr) {
    var retString = escape(sStr);
    //escape function misses +, ', ", / so we need to do these manually
    retString = retString.replace(/\+/g, '%2B');
    retString = retString.replace(/\-/g, '%2D');
    retString = retString.replace(/\"/g,'%22');
    retString = retString.replace(/\'/g, '%27');
    retString = retString.replace(/\//g,'%2F');
    return retString;
}

function replaceWhiteSpaceWithBlank(str) {
    var retString = str;
    if (retString != null && retString != '') {
     retString = retString.replace(/\s/g, ' ');
    }
    return retString;
}




//Check if a date is less than current date 
function lessThanCurrent(d) {
var dte = d.value;
var date = new Date(dte.substring(6,10),
                            dte.substring(0,2)-1,
                            dte.substring(3,5));
var today = new Date();
if (date > today) {
  return false;
} else {
  return true;
}
}

//bugzilla 2437
function fromDateLessThanToDate(fromDate, toDate) {
  var from = fromDate.value;
  var to = toDate.value;
  var dateFrom = new Date(from.substring(6,10),
                            from.substring(0,2)-1,
                            from.substring(3,5));
  var dateTo = new Date(to.substring(6,10),
                            to.substring(0,2)-1,
                            to.substring(3,5));
  if (dateFrom < dateTo) {
    return true;
  } else {
    return false;
  }                   
}


<!-- Begin
function checkTime(time)
  {
    var errorMsg = "";
    
   if (!time.value || time.value == '00:00') {
       time.value = '00:00';
       return true;
   }
   

   if (time.value.indexOf(':') < 0) {
   if (time.value.length == 2) {
       time.value = '0' + time.value;
   }
   
   if (time.value.length ==3) {
       time.value = '0' + time.value;
   }
   if (time.value.length == 4) {
   

     var newTime = '';
     //insert colon
     for (i = 0; i < time.value.length; i++) 
     { 
       newTime += time.value.charAt(i); 
       if (i == 1) { 
         newTime += ':';
       }
     }
    time.value = newTime;
    }
    } else {
      var newTime = time.value;
      if (time.value.length == 3) {
        newTime = '00' + time.value;
      }
      
      if (time.value.length == 4) {
        newTime = '0' + time.value;
      }
    time.value = newTime;
    }
    
    // regular expression to match required time format
    re = /^(\d{1,2}):(\d{2})(:00)?([ap]m)?$/;
    
    if(time.value != '') {
      if(regs = time.value.match(re)) {
        if(regs[3]) {
          // 12-hour time format with am/pm
          if(regs[1] < 1 || regs[1] > 12) {
            errorMsg = "Invalid value for hours: " + regs[1];
          }
        } else {
          // 24-hour time format
          if(regs[1] > 23) {
            errorMsg = "Invalid value for hours: " + regs[1];
          }
        }
        if(!errorMsg && regs[2] > 59) {
          errorMsg = "Invalid value for minutes: " + regs[2];
        }
      } else {
        errorMsg = "Invalid time format: " + time.value;
      }
    }

    if(errorMsg != "") {
      //alert(errorMsg);
      //time.focus();
      return false;
    }
   
    return true;
  }
//  End -->







// PickList script- By Sean Geraty (http://www.freewebs.com/sean_geraty/)
// Visit JavaScript Kit (http://www.javascriptkit.com) for this JavaScript and 100s more
// Please keep this notice intact

// Control flags for list selection and sort sequence
// Sequence is on option value (first 2 chars - can be stripped off in form processing)
// It is assumed that the select list is in sort sequence initially
var singleSelect = true;  // Allows an item to be selected once only
var sortSelect = true;  // Only effective if above flag set to true
var sortPick = true;  // Will order the picklist in sort sequence

// Initialize - invoked on load
function initIt() {
//alert("Here I am initIt");
  var selectList = document.getElementById("SelectList");
  var selectOptions = selectList.options;
  var selectIndex = selectList.selectedIndex;
  var pickList = document.getElementById("PickList");
  var pickOptions = pickList.options;
  pickOptions[0] = null;  // Remove initial entry from picklist (was only used to set default width)
  if (!(selectIndex > -1)) {
    selectOptions[0].selected = true;  // Set first selected on load
    selectOptions[0].defaultSelected = true;  // In case of reset/reload
  }
  selectList.focus();  // Set focus on the selectlist
}

// Adds a selected item into the picklist by selected value (not by index)
//bugzilla 1844: added includeSorting
function reselectOnRedisplay(id, includeSorting) {
//alert("Here I am reselectOnRedisplay");
  var selectList = document.getElementById("SelectList");
  var selectIndex = 0; //need to find out how to get index from id and select it
  var selectOptions = selectList.options;
  var pickList = document.getElementById("PickList");
  var pickOptions = pickList.options;
  var pickOLength = pickOptions.length;
  
  var textToSelect = '';
  var sortFieldAToSelect;
  var sortFieldBToSelect;
  for (var i =0; i < selectOptions.length; i++) {
     if (selectOptions[i].value == id) {
        textToSelect = selectOptions[i].text;
        if (includeSorting) {
         sortFieldAToSelect = selectOptions[i].sortFieldA;
         sortFieldBToSelect = selectOptions[i].sortFieldB;
        }
        if (singleSelect) {
           selectOptions[i] = null;
        }
     }
  }
  // An item must be selected
  pickOptions[pickOLength] = new Option(textToSelect);
  pickOptions[pickOLength].value = id;
  if (includeSorting) {
   pickOptions[pickOLength].sortFieldA = sortFieldAToSelect;
   pickOptions[pickOLength].sortFieldB = sortFieldBToSelect;
  }
  if (sortPick) {
      var tempText;
      var tempValue;
      var tempSortFieldA;
      var tempSortFieldB;
      
      // Sort the pick list
      while (pickOLength > 0 && pickOptions[pickOLength].value < pickOptions[pickOLength-1].value) {
        tempText = pickOptions[pickOLength-1].text;
        tempValue = pickOptions[pickOLength-1].value;
        if (includeSorting) {
         tempSortFieldA = pickOptions[pickOLength-1].sortFieldA;
         tempSortFieldB = pickOptions[pickOLength-1].sortFieldB;
        }
        pickOptions[pickOLength-1].text = pickOptions[pickOLength].text;
        pickOptions[pickOLength-1].value = pickOptions[pickOLength].value;
        if (includeSorting) {
         pickOptions[pickOLength-1].sortFieldA = pickOptions[pickOLength].sortFieldA;
         pickOptions[pickOLength-1].sortFieldB = pickOptions[pickOLength].sortFieldB;
        }
        pickOptions[pickOLength].text = tempText;
        pickOptions[pickOLength].value = tempValue;
        if (includeSorting) {
         pickOptions[pickOLength].sortFieldA = tempSortFieldA;
         pickOptions[pickOLength].sortFieldB = tempSortFieldB;
        }
        pickOLength = pickOLength - 1;
      }
    }
    pickOLength = pickOptions.length;
 
  selectOptions[0].selected = true;
}

//benzd1 bugzilla 2293, 1844 (replacing 1776)
//this is to be used with sortableOptions or sortableOptionCollections tag
function sort(id, sortType, toggleAlternateLabel){
  var beginIndex = 0;
  var theOptions;
  var listObject=document.getElementById(id);
  //the sorting needs to begin at first AssignableTest of type test (panels are sorted to the top)
  theOptions = listObject.options;
  var oLength = theOptions.length;
  if (oLength < 1) {  
    return false;
}
  for (var i = 0; i < oLength; i++) {
    if (listObject[i].type == sortType) {
      beginIndex = i;
      break;
			}	  	 
		}		
  listArray=new Array();
  
  for (var indx=beginIndex;	indx < listObject.options.length;	indx++){
   listArray[indx-beginIndex]=listObject.options[indx];
  }
  
  listArray=listArray.sort(sortOptions);
  for (indx=0; indx < listArray.length; indx++){
    listObject.options[indx+beginIndex]=new Option(listArray[indx].text,listArray[indx].value,true,true);
    listObject.options[indx+beginIndex].type = listArray[indx].type;
    listObject.options[indx+beginIndex].sortFieldA = listArray[indx].sortFieldA;
    listObject.options[indx+beginIndex].sortFieldB = listArray[indx].sortFieldB;
    if (toggleAlternateLabel) {
      var originalLabel = listArray[indx].label;
      listObject.options[indx+beginIndex].label = listArray[indx].alternateLabel;
      listObject.options[indx+beginIndex].alternateLabel = originalLabel;
    }
  }
  listObject.selectedIndex=0;
}

//bugzilla 1844, 2293
//toggle back and forth between sort by sortFieldA and sortFieldB
function sortOptions(optionA,optionB){
 if (sortOrder == 'sortFieldB') {
  optA=optionA.sortFieldB.toLowerCase();
  optB=optionB.sortFieldB.toLowerCase();
 } else {
  optA=optionA.sortFieldA.toLowerCase();
  optB=optionB.sortFieldA.toLowerCase();
	}	
  if (optA<optB){ return -1; }
  if (optA>optB){ return 1; }
  return 0;
}



// Adds a selected item into the picklist
//bugzilla 1844, 2293 modified to allow sorting and distinguishing between panels and indiv. tests
function addIt(includeSorting) {
//alert("Here I am addIt");
  var selectList = document.getElementById("SelectList");
  var selectIndex = selectList.selectedIndex;
  var selectOptions = selectList.options;
  var pickList = document.getElementById("PickList");
  var pickOptions = pickList.options;
  var pickOLength = pickOptions.length;
  // An item must be selected
  while (selectIndex > -1) {
    pickOptions[pickOLength] = new Option(selectList[selectIndex].text);
    pickOptions[pickOLength].value = selectList[selectIndex].value;
    if (includeSorting) {
     pickOptions[pickOLength].type = selectList[selectIndex].type;
     pickOptions[pickOLength].sortFieldA = selectList[selectIndex].sortFieldA;
     pickOptions[pickOLength].sortFieldB = selectList[selectIndex].sortFieldB;
    }
    // If single selection, remove the item from the select list
    if (singleSelect) {
      selectOptions[selectIndex] = null;
    }
    if (sortPick) {
      var tempText;
      var tempValue;
      var tempType;
      var tempSortFieldA;
      var tempSortFieldB;
      // Sort the pick list
      while (pickOLength > 0 && pickOptions[pickOLength].value < pickOptions[pickOLength-1].value) {
        tempText = pickOptions[pickOLength-1].text;
        tempValue = pickOptions[pickOLength-1].value;
        if (includeSorting) {
         tempType = pickOptions[pickOLength-1].type;
         tempSortFieldA = pickOptions[pickOLength-1].sortFieldA;
         tempSortFieldB = pickOptions[pickOLength-1].sortFieldB;
        }
        pickOptions[pickOLength-1].text = pickOptions[pickOLength].text;
        pickOptions[pickOLength-1].value = pickOptions[pickOLength].value;
        if (includeSorting) {
         pickOptions[pickOLength-1].type = pickOptions[pickOLength].type;
         pickOptions[pickOLength-1].sortFieldA = pickOptions[pickOLength].sortFieldA;
         pickOptions[pickOLength-1].sortFieldB = pickOptions[pickOLength].sortFieldB;
        }
                
        pickOptions[pickOLength].text = tempText;
        pickOptions[pickOLength].value = tempValue;
        if (includeSorting) {
         pickOptions[pickOLength].type = tempType;
         pickOptions[pickOLength].sortFieldA = tempSortFieldA;
         pickOptions[pickOLength].sortFieldB = tempSortFieldB;
        }
        pickOLength = pickOLength - 1;
      }
    }
    selectIndex = selectList.selectedIndex;
    pickOLength = pickOptions.length;
  }
  selectOptions[0].selected = true;
  if (includeSorting) {
    sort('PickList');
  }
}

// Deletes an item from the picklist
//bugzilla 1844, 2293 modified to allow sorting and distinguishing between panels and indiv. tests
function delIt(includeSorting) {
//alert("Here I am delIt");
  var selectList = document.getElementById("SelectList");
  var selectOptions = selectList.options;
  var selectOLength = selectOptions.length;
  var pickList = document.getElementById("PickList");
  var pickIndex = pickList.selectedIndex;
  var pickOptions = pickList.options;
  while (pickIndex > -1) {
    // If single selection, replace the item in the select list
    if (singleSelect) {
      selectOptions[selectOLength] = new Option(pickList[pickIndex].text);
      selectOptions[selectOLength].value = pickList[pickIndex].value;
      if (includeSorting) {
       selectOptions[selectOLength].type = pickList[pickIndex].type;
       selectOptions[selectOLength].sortFieldA = pickList[pickIndex].sortFieldA;
       selectOptions[selectOLength].sortFieldB = pickList[pickIndex].sortFieldB;
      }
    }
    pickOptions[pickIndex] = null;
    if (singleSelect && sortSelect) {
      var tempText;
      var tempValue;
      var tempType;
      var tempSortFieldA;
      var tempSortFieldB;
      
      // Re-sort the select list
      while (selectOLength > 0 && selectOptions[selectOLength].value < selectOptions[selectOLength-1].value) {
        tempText = selectOptions[selectOLength-1].text;
        tempValue = selectOptions[selectOLength-1].value;
        if (includeSorting) {
         tempType = selectOptions[selectOLength-1].type;
         tempSortFieldA = selectOptions[selectOLength-1].sortFieldA;
         tempSortFieldB = selectOptions[selectOLength-1].sortFieldB;
        }
        selectOptions[selectOLength-1].text = selectOptions[selectOLength].text;
        selectOptions[selectOLength-1].value = selectOptions[selectOLength].value;
        if (includeSorting) {
         selectOptions[selectOLength-1].type = selectOptions[selectOLength].type;
         selectOptions[selectOLength-1].sortFieldA = selectOptions[selectOLength].sortFieldA;
         selectOptions[selectOLength-1].sortFieldB = selectOptions[selectOLength].sortFieldB;
        }
        selectOptions[selectOLength].text = tempText;
        selectOptions[selectOLength].value = tempValue;
        if (includeSorting) {
         selectOptions[selectOLength].type = tempType;
         selectOptions[selectOLength].sortFieldA = tempSortFieldA;
         selectOptions[selectOLength].value = tempSortFieldB;
        }
        selectOLength = selectOLength - 1;
      }
    }
    pickIndex = pickList.selectedIndex;
    selectOLength = selectOptions.length;
  }
  if (includeSorting) {
    sort('SelectList');
  }
}

//POPUP STUFF

// Default window width for tree select popup
var popupWidth  = 750;

// Default window width for tree select popup
var popUpHeight = 550;

// Default window width for lookup popup
var lookupPopupWidth  = 580;

// Default window width for tlookup popup
var lookupPopUpHeight = 355;


// Select popup opener form reference
var parentForm = null;

// Select popup opener field reference
var parentField = null;

//Lookup popup opener field reference
var lookupDisplayField = null;

//Lookup popup opener field reference
var lookupHiddenField = null;

var popupWindow = null;

//var buttonClicked = false;

// Array used to store form section toggle state
//var formSectionState;



function windowFocus() {
	if (popupWindow != null && popupWindow.closed == false) {
		popupWindow.focus();
	}
}

function windowClose() {
	if (popupWindow != null && popupWindow.closed == false) {
		popupWindow.close();
	}
	return true;
}

function createPopup(pageAddress, width, height)
{
	//alert("^^^" + pageAddress);
	if (width == null) {
		width = popupWidth;
	}
	if (height == null) {
		height = popUpHeight;
	}
		var xOffset = (window.screen.width  - width)/2;
		
		var yOffset = 0;
		if(xOffset < 0)
		{
			xOffset = 0;
		}
		var windowParams = null;
		//IE
		if(window.navigator.appName =='Microsoft Internet Explorer')
		{
			windowParams = 'width=' + width + ',height=' + height +',status=yes,resizable=yes,scrollbars=yes,left='+ xOffset +',top=60';
		}
		else //Netscape
		{
			windowParams = 'width=' + width + ',height=' + height +',status=yes,resizable=yes,scrollbars=yes,pageXOffset='+ xOffset +',pageYOffset=60';
		}
		
		//get the two digit random number and append it to window name
		var randNumStr = new String(Math.random());			
		randNum = randNumStr.substr(randNumStr.indexOf(".")+1, 2);
		var myNewWindowName = "smallwin" + randNum; 
		//alert("About to popup window: " + pageAddress + "..." + myNewWindowName + " ... " + windowParams);
		popupWindow = window.open(pageAddress, myNewWindowName, windowParams);
		popupWindow.focus();

}

//bugzilla 1467
function createSmallConfirmPopup(pageAddress, width, height)
{   
	if (width == null) {
		width = "350";
	}
	if (height == null) {
		height = "150";
	}
		var xOffset = (window.screen.width  - width)/2;
		
		var yOffset = 0;
		if(xOffset < 0)
		{
			xOffset = 0;
		}
		var windowParams = null;
		//IE
		if(window.navigator.appName =='Microsoft Internet Explorer')
		{
			windowParams = 'width=' + width + ',height=' + height +',status=no,resizable=no,titlebar=no,menubar=no,scrollbars=no,left='+ xOffset +',top=60';
		}
		else //Netscape
		{
			windowParams = 'width=' + width + ',height=' + height +',status=no,resizable=no,titlebar=no,menubar=no,scrollbars=no,pageXOffset='+ xOffset +',pageYOffset=60';
		}
		
		//get the two digit random number and append it to window name
		var randNumStr = new String(Math.random());			
		randNum = randNumStr.substr(randNumStr.indexOf(".")+1, 2);
		var myNewWindowName = "smallwin" + randNum; 
		//alert("About to popup window: " + pageAddress + "..." + myNewWindowName + " ... " + windowParams);
		popupWindow = window.open(pageAddress, myNewWindowName, windowParams);
		//popupWindow.focus();
		return popupWindow;
}



function createSelectPopup(pageAddress, theForm, theField)
{//alert("About to createPopup " + pageAddress);
	createPopup(pageAddress, popupWidth, popUpHeight);
	
	parentForm = theForm;
	parentField = theField;

}

function IsNumeric(strString)
   //  check for valid numeric strings	
   {
   var strValidChars = "0123456789.-";
   var strChar;
   var blnResult = true;

   if (strString.length == 0) return false;

   //  test strString consists of valid characters listed above
   for (i = 0; i < strString.length && blnResult == true; i++)
      {
      strChar = strString.charAt(i);
      if (strValidChars.indexOf(strChar) == -1)
         {
         blnResult = false;
         }
      }
   return blnResult;
}


function containsNewLine(strString)
   //  check for newline character	
   {
   var strNewLine = '\n';
   var foundNewLine = false;

   if (strString.length == 0) return false;

   //  test strString 
   if (strString.indexOf(strNewLine) >= 0) {
       foundNewLine = true;
   }

   return foundNewLine;
}


function noenter() {
  return !(window.event && window.event.keyCode == 13); 
}

//bugzilla # 1400 
function focusOnFirstInputField() {

  var bFound = false;

  // for each form
  for (f=0; f < document.forms.length; f++)
  {
    // for each element in each form
    for(i=0; i < document.forms[f].length; i++)
    {
      // if it's not a hidden element
      if (document.forms[f][i].type != "hidden")
      {
        // and it's not disabled
        if (document.forms[f][i].disabled != true)
        {
            // set the focus to it
            document.forms[f][i].focus();
            //alert("setting focus on " + document.forms[f][i].name);
            var bFound = true;
        }
      }
      // if found in this element, stop looking
      if (bFound == true)
        break;
    }
    // if found in this form, stop looking
    if (bFound == true)
      break;
  }
}

//bugzilla 1883 (cloned and modified isDirty() to work for a single form field)
function isFormFieldDirty(eElem) {

		if (eElem.disabled == true || eElem.readOnly == true)
		{	// Field is disabled, so don't need to check if dirty
			return false;
		}

		var eName = eElem.name;
		if ( eName.length == 0 )
		{	// Name of field is 0 length, so don't do check
			return false;
		}
	
		var eType = eElem.type;
		
		if( "hidden" == eType ) {
				return false;
		}
 		
		if ("text" == eType || "TEXTAREA" == eElem.tagName) {
			if (eElem.value != eElem.defaultValue) {
				return true;
			}
		}


		if ("checkbox" == eElem.type || "radio" == eElem.type) {
			if (eElem.checked != eElem.defaultChecked) {
				return true;
			}
		}

		if ("SELECT" == eElem.tagName) {
			checkDftSelected(eElem);
			var numOpts = eElem.options.length;
			for (var j=0; j < numOpts; j++) {
				var eopt = eElem.options[j];
				if (eopt.selected != eopt.defaultSelected && eopt.value != null && eopt.value.length > 0) {
					return true;
				}
			}
		}
	return false;
}


//bugzilla 1467
// Has an input-capable field on the form changed?
//If ever there is a problem with this - make sure there are no duplicate values in drop downs!!
function isDirty(form, ignoreFields) {
	var elemLength = form.elements.length;
	for (var i=0; i < elemLength; i++) {
		var eElem = form.elements[i];
		
		if (eElem.disabled == true || eElem.readOnly == true)
		{	// Field is disabled, so don't need to check if dirty
			continue;
		}
		var eName = eElem.name;
		if ( eName.length == 0 )
		{	// Name of field is 0 length, so don't do check
			continue;
		}
		
		if (ignoreFields != null && ignoreFields.indexOf(eName) > -1 )
		{	// Found field name in list of fields to ignore
			continue;
		}
		var eType = eElem.type;
		
		if( "hidden" == eType ) {
				continue;
		}
		
		if ("text" == eType || "TEXTAREA" == eElem.tagName) {
			if (eElem.value != eElem.defaultValue) {
				return true;
			}
		}

	
		if ("checkbox" == eElem.type || "radio" == eElem.type) {
			if (eElem.checked != eElem.defaultChecked) {
				return true;
			}
		}
		if ("SELECT" == eElem.tagName) {
			checkDftSelected(eElem);
			var numOpts = eElem.options.length;
			for (var j=0; j < numOpts; j++) {
				var eopt = eElem.options[j];
				if (eopt.selected != eopt.defaultSelected && eopt.value != null && eopt.value.length > 0) {
					return true;
				}
			}
		}
	}
	// If a page requires special functionality for checking if it's dirty that isn't global, 
	// create a pageIsDirty method
	if(window.pageIsDirty)
	{
		return pageIsDirty(form);
	}
	return false;
}

//bugzilla 1467
function checkDftSelected(eElem)
{
	var numOpts = eElem.options.length;
	for (var j=0; j < numOpts; j++) {
		var eopt = eElem.options[j];
		if (eopt.defaultSelected) {
			return;
        //bugzilla 1518 freeform notes bug fixed
		} else {
          if (eopt.selected) {
            return;
          }
        }
	}
	
	if(numOpts > 0)
	{
		eElem.options[0].defaultSelected = true;
		eElem.options[0].selected = true;
	}
}

//bugzilla 1942 added externalNotesDisabled parameter
function popupNotes (form, tableId, id, externalNotesDisabled) {

   
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
	var refTableKey = '<%= IActionConstants.NOTES_REFTABLE %>';
	var refIdKey = '<%= IActionConstants.NOTES_REFID %>';
	var disableExternalKey = '<%= IActionConstants.NOTES_EXTERNAL_NOTES_DISABLED %>';
	var parmString = "?" + refTableKey + "=" + tableId + "&" + refIdKey + "=" +id + "&" + disableExternalKey +  "=" + externalNotesDisabled;
	var href = context + "/NotesPopup" + parmString + sessionid;
    //alert("href "+ href);
	
	createPopup( href, null, null );
}

/*bugzilla 1664*/
/*
This script changes "body.style.zoom" according to the screen resolution.
You can freely distribute this script.
Please don't remove any comment.
See more details on: http://digilander.libero.it/indipendent_res/
Do this during body onload
*/
function getCorrectWidth() {
    return screen.width; //1280;
}

function getCorrectHeight() {
    return 1024;
}

function check_width() {
   var correctwidth=getCorrectWidth();
   // You can personalize "correctwidth" according to your layout.

   if (screen.width!=correctwidth) {
     document.body.style.zoom = screen.width / correctwidth;				
   }
}

//bugzilla 1803
function setCookie(cookieName,cookieValue,nDays) {
 var today = new Date();
 var expire = new Date();
 if (nDays==null || nDays==0) nDays=1;
 expire.setTime(today.getTime() + (3600000*24*nDays));
 document.cookie = cookieName+"="+escape(cookieValue) + ";expires="+expire.toGMTString();
}

//bugzilla 1803
function getCookie(cookieName) {
 if (document.cookie.length>0) {
  var c_start=document.cookie.indexOf(cookieName + "=");
  if (c_start!=-1) { 
    c_start=c_start + cookieName.length + 1;
    var c_end=document.cookie.indexOf(";",c_start);
    if (c_end==-1) c_end=document.cookie.length;
    return unescape(document.cookie.substring(c_start,c_end));
   } 
 }
 return "";
}

//bugzilla 1810

var keysPressed = [];

function selectAsYouType(e)
{

    var keyChar; var keyNum;
    var comboBox;
    if (window.event) // IE
    {
        keyNum = e.keyCode;            
    }
    else
    {
        return true; // browser doesn't support DOM, do default processing.
    }
    
    keyChar = String.fromCharCode(keyNum);
    comboBox = e.srcElement;
    
    if (!comboBox)
    {
        return true; // if DOM didn't work, do default processing
    }
    if (keyNum == 27) // Esc key
    {
        clearKeysPressed(comboBox);
    }
    else
    {
        return selectElement(comboBox, keyChar);
    }
    return true;
}

function clearKeysPressed(e)
{
    if (e.srcElement)
        keysPressed[e.srcElement]="";
}

function selectElement(comboBox, keyChar)
{
    var i = 0;
    var keysPressedSoFar =     keysPressed[comboBox] || "";
    keysPressedSoFar += keyChar;
    keysPressed[comboBox] = keysPressedSoFar;
    var options = comboBox.options;
    for (i = 0; i < options.length; ++i)
    {
        if (options[i].text.toUpperCase().indexOf(keysPressedSoFar.toUpperCase(), 0) == 0)
        {
            comboBox.selectedIndex = i;
            return false; // don't do default processing
        }
    }
    keysPressed[comboBox] = String(keyChar); // didn't match start of any option, so reset keysPressed to just this key
    return true;        
}
//END bugzilla 1810

//bugzilla 1413
function enterKeyPressed(e){ //e is event object passed from function invocation
var characterCode;

if(e && e.which){ //if which property of event object is supported (NN4)
e = e
characterCode = e.which //character code is contained in NN4's which property
}
else{
e = event
characterCode = e.keyCode //character code is contained in IE's keyCode property
}

if(characterCode == 13){ //if generated character code is equal to ascii 13 (if enter key)
return true; 
}
else{
return false;
}

}