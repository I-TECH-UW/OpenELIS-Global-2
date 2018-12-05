<%@ page language="java" contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	import="java.util.List,
                 us.mn.state.health.lims.testconfiguration.beans.TestCatalogBean"%>
<%@ page import="us.mn.state.health.lims.common.util.IdValuePair"%>
<%@ page import="us.mn.state.health.lims.common.util.StringUtil"%>
<%@ page import="us.mn.state.health.lims.common.util.Versioning"%>
<%@ page
	import="us.mn.state.health.lims.common.util.SystemConfiguration"%>
<%@ page
	import="us.mn.state.health.lims.common.services.TypeOfTestResultService"%>
<%@ page
	import="us.mn.state.health.lims.common.provider.query.EntityNamesProvider"%>
<%@ page
	import="us.mn.state.health.lims.testconfiguration.beans.ResultLimitBean"%>

<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/labdev-view" prefix="app"%>
<%--
  ~ The contents of this file are subject to the Mozilla Public License
  ~ Version 1.1 (the "License"); you may not use this file except in
  ~ compliance with the License. You may obtain a copy of the License at
  ~ http://www.mozilla.org/MPL/
  ~
  ~ Software distributed under the License is distributed on an "AS IS"
  ~ basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
  ~ License for the specific language governing rights and limitations under
  ~ the License.
  ~
  ~ The Original Code is OpenELIS code.
  ~
  ~ Copyright (C) ITECH, University of Washington, Seattle WA.  All Rights Reserved.
  --%>

<%!String basePath = "";
	String locale = "en_US";%>
<%
	String path = request.getContextPath();
	basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
			+ "/";
	locale = SystemConfiguration.getInstance().getDefaultLocale().toString();
%>
<!--Do not add jquery.ui.js, it will break the sorting -->
<script type="text/javascript"
	src="scripts/jquery.asmselect.js?ver=<%=Versioning.getBuildNumber()%>"></script>
<script type="text/javascript"
	src="<%=basePath%>scripts/ajaxCalls.js?ver=<%=Versioning.getBuildNumber()%>"></script>
<script type="text/javascript"
	src="<%=basePath%>scripts/multiselectUtils.js?ver=<%=Versioning.getBuildNumber()%>"></script>
<script type="text/javascript"
	src="scripts/jquery-ui.js?ver=<%=Versioning.getBuildNumber()%>"></script>
<link rel="stylesheet" type="text/css"
	href="css/jquery.asmselect.css?ver=<%=Versioning.getBuildNumber()%>" />
<link rel="stylesheet" media="screen" type="text/css"
	href="<%=basePath%>css/jquery_ui/jquery.ui.theme.css?ver=<%=Versioning.getBuildNumber()%>" />
<link rel="stylesheet" type="text/css"
	href="css/openElisCore.css?ver=<%=Versioning.getBuildNumber()%>" />


<script type="text/javascript"
	src="scripts/ajaxCalls.js?ver=<%=Versioning.getBuildNumber()%>"></script>

<bean:define id="formName"
	value='<%=(String) request.getAttribute(IActionConstants.FORM_NAME)%>' />
<bean:define id="testList" name='<%=formName%>' property="testList"
	type="java.util.List<IdValuePair>" />

<bean:define id="sampleTypeList" name='<%=formName%>'
	property="sampleTypeList" type="java.util.List<IdValuePair>" />
<bean:define id="panelList" name='<%=formName%>' property="panelList"
	type="java.util.List<IdValuePair>" />
<bean:define id="uomList" name='<%=formName%>' property="uomList"
	type="java.util.List<IdValuePair>" />
<bean:define id="resultTypeList" name='<%=formName%>'
	property="resultTypeList" type="java.util.List<IdValuePair>" />
<bean:define id="testUnitList" name='<%=formName%>'
	property="labUnitList" type="java.util.List<IdValuePair>" />
<bean:define id="ageRangeList" name='<%=formName%>'
	property="ageRangeList" type="java.util.List<IdValuePair>" />
<bean:define id="dictionaryList" name='<%=formName%>'
	property="dictionaryList" type="java.util.List<IdValuePair>" />
<bean:define id="groupedDictionaryList" name='<%=formName%>'
	property="groupedDictionaryList"
	type="java.util.List<java.util.List<IdValuePair>>" />
<bean:define id="testCatBeanList" name='<%=formName%>'
	property="testCatBeanList" type="List<TestCatalogBean>" />


<%!int testCount = 0;
	int columnCount = 0;
	int columns = 3;
%>

<%
	columnCount = 0;
	testCount = 0;
%>
<form>
	<script type="text/javascript">
    if (!$jq) {
        var $jq = jQuery.noConflict();
    }
    
    function makeDirty(){
        function formWarning(){
            return "<bean:message key="banner.menu.dataLossWarning"/>";
        }
        window.onbeforeunload = formWarning;
    }

    function submitAction(target) {
        var form = window.document.forms[0];
        form.action = target;
        form.submit();
    }

    function setForEditing(testId, name) {
        $jq("#catDiv").show();
        $jq("#testName").text(name);
        $jq(".error").each(function (index, value) {
            value.value = "";
            $jq(value).removeClass("error");
            $jq(value).removeClass("confirmation");
        });
        
        $jq(".test").each(function () {
            var element = $jq(this);
            element.prop("disabled", "disabled");
            element.addClass("disabled-text-button");
        });
        
        var resultType = "";
        $jq(".resultClass").each(function (i,elem) {
        	if(testId !== $jq(elem).attr("fTestId")){    		
        		$jq(elem).remove();
        	} else {
				// from type_of_test_result table, translate to DisplayListService
        		switch($jq(elem).attr('fresulttype')) {
        	    case "R": 
        	    	$jq("#resultTypeSelection").val(1) 
        	    	break;
        	    case "D": 
        	    	$jq("#resultTypeSelection").val(2) 
        	    	break;
        	    case "T": 
        	    	$jq("#resultTypeSelection").val(3) 
        	    	break;
        	    case "N": 
        	    	$jq("#resultTypeSelection").val(4) 
        	    	break;
        	    case "A": 
        	    	$jq("#resultTypeSelection").val(5) 
        	    	break;
        	    case "M": 
        	    	$jq("#resultTypeSelection").val(6) 
        	    	break;
        	    case "C": 
        	    	$jq("#resultTypeSelection").val(7) 
        	    	break;
        	    default:
        		}
				
				var panel = $jq(elem).attr('fPanel');
				var optionsArray = Array.from($jq("#panelSelection")[0]);
				var panelOption = optionsArray.filter(function(elem) { return elem.label === panel });
				panelOption.forEach(function(elem) {
					$jq(elem).attr("selected", true)
				});
				
				$jq("#panelSelection").change();
							
        	}
        });
        
        getTestNames(testId, testNameSuccess);
		getTestEntities(testId, testEntitiesSuccess);
		$jq("#nextButton").prop("disabled", false);	
		$jq("#step1Div").show();
    }
   
    function testEntitiesSuccess(xhr) {
        //alert(xhr.responseText);
        var formField = xhr.responseXML.getElementsByTagName("formfield").item(0);
        var message = xhr.responseXML.getElementsByTagName("message").item(0);
        var response;
        var testSectionId = "";
        var uomId = "";

        if (message.firstChild.nodeValue == "valid") {
            response = JSON.parse(formField.firstChild.nodeValue);
            testSectionId = response["entities"]["testSectionId"];
            uomId = response["entities"]["uomId"];
            
         	//getEntityNames(testSectionId, "<%=EntityNamesProvider.TEST_SECTION%>", testSectionNameSuccess );
         	//getEntityNames(uomId, "<%=EntityNamesProvider.UNIT_OF_MEASURE%>", uomNameSuccess );
            
            $jq("#loinc").val(response["entities"]["loinc"]);
            $jq("#testUnitSelection").val(response["entities"]["testSectionId"]);
            $jq("#uomSelection").val(response["entities"]["uomId"]);
        }
        
        window.onbeforeunload = null;
        $jq("#step1Div").show();
    }
    
    function uomNameSuccess(xhr) {
        //alert(xhr.responseText);
        var formField = xhr.responseXML.getElementsByTagName("formfield").item(0);
        var message = xhr.responseXML.getElementsByTagName("message").item(0);
        var response;

        if (message.firstChild.nodeValue == "valid") {
            response = JSON.parse(formField.firstChild.nodeValue);
            $jq("#uomEnglish").text(response["name"]["english"]);
            $jq(".required").each(function () {
                $jq(this).val("");
            });
        }

        window.onbeforeunload = null;
    }
    
    function testSectionNameSuccess(xhr) {
        //alert(xhr.responseText);
        var formField = xhr.responseXML.getElementsByTagName("formfield").item(0);
        var message = xhr.responseXML.getElementsByTagName("message").item(0);
        var response;

        if (message.firstChild.nodeValue == "valid") {
            response = JSON.parse(formField.firstChild.nodeValue);
            $jq("#testSectionEnglish").text(response["name"]["english"]);
            $jq("#testSectionFrench").text(response["name"]["french"]);
            $jq(".required").each(function () {
                $jq(this).val("");
            });
        }

        window.onbeforeunload = null;
    }

    function testNameSuccess(xhr) {
        //alert(xhr.responseText);
        var formField = xhr.responseXML.getElementsByTagName("formfield").item(0);
        var message = xhr.responseXML.getElementsByTagName("message").item(0);
        var response;

        if (message.firstChild.nodeValue == "valid") {
            response = JSON.parse(formField.firstChild.nodeValue);
            
            $jq("#testNameEnglish").val(response["name"]["english"]);
            
            $jq("#testNameFrench").val(response["name"]["french"]);
            
            $jq("#testReportNameEnglish").val(response["reportingName"]["english"]);
            
            $jq("#testReportNameFrench").val(response["reportingName"]["french"]);
            
            //alert(response["reportingName"]["french"]);
            //$jq(".required").each(function () {
              //  $jq(this).val("");
            //});
        }

        window.onbeforeunload = null;
    }

    function confirmValues() {
        var hasError = false;
        $jq(".required").each(function () {
            var input = $jq(this);
            if (!input.val() || input.val().strip().length == 0) {
                input.addClass("error");
                hasError = true;
            }
        });

        if (hasError) {
            alert('<%=StringUtil.getMessageForKey("error.all.required")%>');
        } else {
            $jq(".required").each(function () {
                var element = $jq(this);
                element.prop("readonly", true);
                element.addClass("confirmation");
            });
            $jq(".requiredlabel").each(function () {
                $jq(this).hide();
            });
            $jq("#editButtons").hide();
            $jq("#confirmationButtons").show();
            $jq("#action").text('<%=StringUtil.getMessageForKey("label.confirmation")%>');
        }
    }

    function rejectConfirmation() {
        $jq(".required").each(function () {
            var element = $jq(this);
            element.removeProp("readonly");
            element.removeClass("confirmation");
        });
        $jq(".requiredlabel").each(function () {
            $jq(this).show();
        });

        $jq("#editButtons").show();
        $jq("#confirmationButtons").hide();
        $jq("#action").text('<%=StringUtil.getMessageForKey("label.button.edit")%>');
    }

    function cancel() {
        $jq("#editDiv").hide();
        $jq("#testId").val("");
        $jq(".test").each(function () {
            var element = $jq(this);
            element.removeProp("disabled");
            element.removeClass("disabled-text-button");
        });
        window.onbeforeunload = null;
    }

    function handleInput(element) {
        $jq(element).removeClass("error");
        makeDirty();
    }

    function savePage() {
        window.onbeforeunload = null; // Added to flag that formWarning alert isn't needed.
        var form = window.document.forms[0];
        form.action = "TestModifyUpdate.do";
        form.submit();
    }
    
    function genderMatersForRange(checked, index) {
        if (checked) {
            $jq(".sexRange_" + index).show();
        } else {
            $jq(".sexRange_" + index).hide();
            $jq("#lowNormal_G_" + index).val("-Infinity");
            $jq("#highNormal_G_" + index).val("Infinity");
            $jq("#lowNormal_G_" + index).removeClass("error");
            $jq("#highNormal_G_" + index).removeClass("error");
        }
    }
    
    var step = "step1";
    var currentNormalRangeIndex = 1;
    var maxAgeInMonths = 0;
    var useResultLimitDefault = true;
    var defaultResultLimits = null;

    if (!$jq) {
        var $jq = jQuery.noConflict();
    }

    $jq(document).ready(function () {
        $jq("select[multiple]").asmSelect({
            removeLabel: "X"
        });

        $jq("select[multiple]").change(function (e, data) {
            handleMultiSelectChange(e, data);
        });

        $jq("#dictionarySelectId .asmSelect").css("max-width", "275px");
    });


    function augmentMultiselects(parent) {
        $jq(parent + " select[multiple]").asmSelect({
            removeLabel: "X"
        });

        $jq(parent + " select[multiple]").change(function (e, data) {
            handleMultiSelectChange(e, data);
        });
    }
    function handleMultiSelectChange(e, data) {
        //e is the event
        //e.currentTarget is the id of the select element
        //data.type will be add or drop
        //data.value is the value from the select list

        if (step == 'step2') {
            createOrderBoxForSampleType(data);
        } else if (step == 'step3Dictionary') {
            appendOrderBoxForDictionary(e, data);
        }
        checkReadyForNextStep(e, data);
    }

    function createOrderBoxForSampleType(data) {
    	if(typeof data === 'undefined') return;
        var sampleTypeName = $jq("#sampleTypeSelection option[value=" + data.value + "]").text();
        var divId = data.value;
        if (data.type == 'add') {
            createNewSortingDiv(sampleTypeName, divId);
            getTestsForSampleType(data.value, testForSampleTypeSuccess);
        } else {
            $jq("#" + divId).remove();
        }
    }

    function appendOrderBoxForDictionary(e, data) {
        var dictionaryName = $jq(e.currentTarget).find("option[value=" + data.value + "]").text();
        var qualifiyList = $jq("#qualifierSelection");
        var ul = $jq("#dictionaryNameSortUI");
        var li, option;
        ul.addClass("sortable");
        if (e.currentTarget.id == "dictionarySelection") {
            if (data.type == 'add') {
                li = createLI(data.value, dictionaryName, false);
                li.addClass("ui-sortable-handle");
                ul.append(li);
                $jq("#dictionaryNameSortUI").sortable();
                $jq("#dictionaryNameSortUI").disableSelection();
                $jq("#dictionaryQualify .asmContainer").remove();
                $jq("#dictionaryQualify").append(qualifiyList);

                option = createOption(data.value, dictionaryName, false);
                qualifiyList.append(option);
                augmentMultiselects("#dictionaryQualify");

                option = createOption(data.value, dictionaryName, false);
                $jq("#referenceSelection").append(option);
            } else {
                $jq("#dictionaryNameSortUI li[value=" + data.value + "]").remove();

                $jq("#dictionaryQualify .asmContainer").remove();
                qualifiyList.find("option[value=" + data.value + "]").remove();
                $jq("#dictionaryQualify").append(qualifiyList);
                augmentMultiselects("#dictionaryQualify");
                $jq("#referenceSelection option[value=" + data.value + "]").remove();
            }
        }
    }

  	/*
    function dictionarySetDefault(valuesArray) {
        var dictionaryOption;
        clearDictionaryLists();
        var optionArray = Array.from($jq("#dictionarySelection")[0]);
        
        for(var i = 0; i < valuesArray.length; i++) {
        	dictionaryOption = optionArray.filter(function(elem) { return elem.label === valuesArray[i] });
        	
        	for(var j=dictionaryOption.length; j>0; j--) {
        		dictionaryOption.splice(j,1);
        	}
        	
        	dictionaryOption.forEach(function(inner) {
            	$jq(inner).attr("selected", "selected")
        	});
        }
        $jq("#dictionarySelection").change();
    }
  	*/
  	
  	
    function dictionarySetSelected(index) {
        var dictionarySelect;
        //clear existing selections
        clearDictionaryLists();
        //add new selections
        $jq("#dictionaryGroup_" + index + " li").each(function () {
            dictionarySelect = $jq("#dictionarySelectId .asmSelect option[value=" + $jq(this).val() + "]");
            dictionarySelect.attr("selected", "selected");
            dictionarySelect.trigger('change');
        });
    }

    function clearDictionaryLists(){
        $jq("#qualifierSelection option").remove();
        clearMultiSelectContainer($jq(".dictionaryMultiSelect"));
        $jq("#dictionaryNameSortUI li").remove();
        $jq("#referenceSelection option").remove();
        $jq("#referenceSelection").append(createOption("0", "", false));
    }
    function createOption(id, name, isActive) {
        var option = $jq('<option/>');
        option.attr({'value': id}).text(name);
        if (isActive == 'N') {
            option.addClass("inactiveTest");
        }
        return option;
    }
    function guideSelection(checkbox) {
        if (checkbox.checked) {
            $jq("#guide").show();
        } else {
            $jq("#guide").hide();
        }
    }

    function copyFromTestName() {
        $jq("#testReportNameEnglish").val($jq("#testNameEnglish").val());
        $jq("#testReportNameFrench").val($jq("#testNameFrench").val());
    }

    function testForSampleTypeSuccess(xhr) {
        //alert(xhr.responseText);
        var response = xhr.responseXML.getElementsByTagName("formfield").item(0);
        var tests = response.getElementsByTagName("test");
        var sampleTypeId = getValueFromXmlElement(response, "sampleTypeId");
        var test, name, modName, id, modId;
        var ul = $jq(document.createElement("ul"));
        var length = tests.length;
        ul.addClass("sortable sortable-tag");

        for (var i = 0; i < length; ++i) {
            test = tests[i];
            name = getValueFromXmlElement(test, "name");
            
            <%if (locale.equals("en_US")) {%>
            modName = $jq("#testNameEnglish").val();
            <%} else {%>
            modName = $jq("#testNameFrench").val();
            <%}%>
            
            id = getValueFromXmlElement(test, "id");
            if (name != modName ) {
            	ul.append(createLI(id, name, false));
            } else {
            	ul.append(createLI(id, name, true));
            }
        }

        <%if (locale.equals("en_US")) {%>
        //ul.append( createLI(modId, $jq("#testNameEnglish").val(), true) );
        <%} else {%>
        //ul.append( createLI(modId, $jq("#testNameFrench").val(), true) );
        <%}%>

        $jq("#sort" + sampleTypeId).append(ul);

        $jq(".sortable").sortable();
        $jq(".sortable").disableSelection();
    }

    function createLI(id, name, highlight) {
        var li = $jq(document.createElement("li"));
        var span = $jq(document.createElement("span"));

        li.val(id);
        li.addClass("ui-state-default_oe ui-state-default_oe-tag");
        span.addClass("ui-icon ui-icon-arrowthick-2-n-s");
        li.append(span);
        li.append(name);
        if (highlight) {
            li.addClass("altered");
        }
        return li
    }
    function getValueFromXmlElement(parent, tag) {
        var element = parent.getElementsByTagName(tag);
        return element ? element[0].childNodes[0].nodeValue : "";
    }
    function createNewSortingDiv(sampleTypeName, divId) {
        var mainDiv = $jq(document.createElement("div"));
        var nameSpan = createNameSpan(sampleTypeName);
        var sortSpan = createSortSpan(divId);

        mainDiv.attr("id", divId);
        mainDiv.addClass("sortingMainDiv");
        mainDiv.css("padding", "20px");
        mainDiv.append(nameSpan);
        mainDiv.append(sortSpan);
        $jq("#endOrderMarker").before(mainDiv);

    }

    function createNameSpan(sampleTypeName) {
        var nameSpan = $jq(document.createElement("span"));
        nameSpan.addClass("half-tab");
        nameSpan.append(sampleTypeName);
        return nameSpan;
    }

    function createSortSpan(divId) {
        var sortSpan = $jq(document.createElement("span"));
        sortSpan.attr("id", "sort" + divId);

        return sortSpan;
    }

    function makeSortListsReadOnly() {
        if ($jq(".sortable li").length > 0) {
            $jq(".sortable").removeClass("sortable");
            $jq(".ui-state-default_oe").removeClass("ui-state-default_oe");
        }
    }

    function upperAgeRangeChanged(index) {
        var copy, htmlCopy, monthYear, lowAge, lowAgeValue, highAgeValue, lowAgeModifier, newMonthValue;
        var element = $jq("#upperAgeSetter_" + index);

        element.removeClass("error");
        if (element.val() != "Infinity") {
            monthYear = $jq(".yearMonthSelect_" + index + ":checked").val();

            if (index != 0) {
                lowAge = $jq("#lowerAge_" + index).text();
                lowAgeModifier = lowAge.charAt(lowAge.length - 1);
                lowAgeValue = lowAge.substring(0, lowAge.length - 1);
                lowAgeValue = lowAgeModifier == "<%=StringUtil.getMessageForKey("abbreviation.year.single")%>" ? lowAgeValue *= 12 : +lowAgeValue;
                highAgeValue = +element.val();
                if (highAgeValue != element.val()) {
                    alert("<%=StringUtil.getMessageForKey("error.age.value")%>");
                    element.addClass("error");
                    return;
                }

                newMonthValue = monthYear == '<%=StringUtil.getMessageForKey("abbreviation.month.single")%>' ? highAgeValue : 12 * highAgeValue;

                if (newMonthValue <= lowAgeValue) {
                    element.addClass("error");
                    alert("<%=StringUtil.getMessageForKey("error.age.begining.ending.order")%>");
                    return;
                }
            }


            $jq(element).hide();
            $jq("#upperAge_" + index).text(element.val() + monthYear);
            $jq(".yearMonthSelect_" + index).attr("disabled", "disabled");
            $jq("#ageRangeSelect_" + index).attr("disabled", "disabled");
            copy = $jq("#normalRangeTemplate table tbody").clone();
            htmlCopy = copy.html().replace(/index/g, currentNormalRangeIndex);
            $jq("#endRow").before(htmlCopy);
            $jq(".sexRange_" + currentNormalRangeIndex).hide();
            $jq("#lowerAge_" + currentNormalRangeIndex).text(element.val() + monthYear);
            if (index != 0) {
                $jq("#removeButton_" + index).hide();
            }
            currentNormalRangeIndex++;
        }

    }

    function removeLimitRow(index) {
        $jq(".row_" + index).remove();

        for (var i = index - 1; index >= 0; i--) {
            if ($jq(".row_" + i)) {
                $jq(".yearMonthSelect_" + i).removeAttr("disabled");
                $jq("#ageRangeSelect_" + i).removeAttr("disabled");
                $jq("#ageRangeSelect_" + i).val(0);
                $jq("#upperAge_" + i).text("");
                $jq("#upperAgeSetter_" + i).show();
                if (i != 0) {
                    $jq("#removeButton_" + i).show();
                }
                break;
            }
        }
    }

    function ageRangeSelected(element, index) {
        var ageInMonths = $jq(element).find("option:selected").val();
        var selectFound = false;
        var optionValue;

        if (ageInMonths != 0) {
            if (ageInMonths == "Infinity") {
                $jq("#upperAgeSetter_" + index).val(ageInMonths);
            } else if (ageInMonths % 12 == 0) {
                $jq("input:radio[name=time_" + index + "]").val(["<%=StringUtil.getMessageForKey("abbreviation.year.single")%>"]);
                $jq("#upperAgeSetter_" + index).val(ageInMonths / 12);
            } else {
                $jq("input:radio[name=time_" + index + "]").val(["<%=StringUtil.getMessageForKey("abbreviation.month.single")%>"]);
                $jq("#upperAgeSetter_" + index).val(ageInMonths);
            }
            upperAgeRangeChanged(index);

            $jq("#ageRangeSelect_" + (currentNormalRangeIndex - 1) + " option").each(function () {
                optionValue = $jq(this).val();
                if (!selectFound) {
                    if (optionValue == ageInMonths) {
                        selectFound = true;
                    }
                    if (optionValue != 0) {
                        $jq(this).hide();
                    }
                }
            });
        }
    }

    function normalRangeCheck(index) {
        var lowNormalValue, highNormalValue, lowValidValue, highValidValue;
        var lowGenderNormalValue, highGenderNormalValue;
        var lowGenderNormal, highGenderNormal;
        var lowNormal = $jq("#lowNormal_" + index);
        var highNormal = $jq("#highNormal_" + index);
        var lowValid = $jq("#lowValid");
        var highValid = $jq("#highValid");
        var checkGenderValues = $jq("#genderCheck_" + index).is(':checked');

        //check to see if the normal ranges are numeric (Except for infinity) and then compare them to make sure they
        //are ordered correctly.
        lowNormal.removeClass("error");
        lowNormalValue = +lowNormal.val();
        if (lowNormalValue != "-Infinity" &&
                lowNormalValue != lowNormal.val()) {
            lowNormal.addClass("error");
            alert("<%=StringUtil.getMessageForKey("error.low.normal.value")%>");
            return;
        }

        highNormal.removeClass("error");
        highNormalValue = +highNormal.val();
        if (highNormalValue != "Infinity" &&
                highNormalValue != highNormal.val()) {
            highNormal.addClass("error");
            alert("<%=StringUtil.getMessageForKey("error.high.normal.value")%>");
            return;
        }

        if (highNormalValue != "Infinity" && lowNormalValue != "-Infinity") {
            if (highNormalValue <= lowNormalValue) {
                highNormal.addClass("error");
                lowNormal.addClass("error");
                alert("<%=StringUtil.getMessageForKey("error.low.normal.high.normal.order")%>");
                return;
            }
        }

        if (checkGenderValues) {
            lowGenderNormal = $jq("#lowNormal_G_" + index);
            highGenderNormal = $jq("#highNormal_G_" + index);
            lowGenderNormal.removeClass("error");
            lowGenderNormalValue = +lowGenderNormal.val();
            if (lowGenderNormalValue != "-Infinity" &&
                    lowGenderNormalValue != lowGenderNormal.val()) {
                lowGenderNormal.addClass("error");
                alert("<%=StringUtil.getMessageForKey("error.low.normal.value")%>");
                return;
            }

            highGenderNormal.removeClass("error");
            highGenderNormalValue = +highGenderNormal.val();
            if (highGenderNormalValue != "Infinity" &&
                    highGenderNormalValue != highGenderNormal.val()) {
                highGenderNormal.addClass("error");
                alert("<%=StringUtil.getMessageForKey("error.high.gender.value")%>");
                return;
            }

            if (highGenderNormalValue != "Infinity" && lowGenderNormalValue != "-Infinity") {
                if (highGenderNormalValue <= lowGenderNormalValue) {
                    highGenderNormal.addClass("error");
                    lowGenderNormal.addClass("error");
                    alert("<%=StringUtil.getMessageForKey("error.low.normal.high.normal.order")%>");
                    return;
                }
            }
        }

        //below we are testing against the valid values
        lowValidValue = +lowValid.val();
        if (lowValidValue != "-Infinity" &&
                lowValidValue != lowValid.val()) {
            return;
        }

        highValidValue = +highValid.val();
        if (highValidValue != "Infinity" &&
                highValidValue != highValid.val()) {
            return;
        }


        if (lowValidValue == "-Infinity" && highValidValue == "Infinity") {
            return;
        }

        if (lowValidValue != "-Infinity" && lowNormalValue < lowValidValue) {
            lowNormal.addClass("error");
            alert("<%=StringUtil.getMessageForKey("error.low.normal.low.valid.order")%>");
            return;
        }

        if (highValidValue != "Infinity" && highNormalValue > highValidValue) {
            highNormal.addClass("error");
            alert("<%=StringUtil.getMessageForKey("error.high.normal.high.valid.order")%>");
            return;
        }

        if (checkGenderValues) {
            if (lowValidValue != "-Infinity" && lowGenderNormalValue < lowValidValue) {
                lowGenderNormal.addClass("error");
                alert("<%=StringUtil.getMessageForKey("error.low.normal.low.valid.order")%>");
                return;
            }

            if (highValidValue != "Infinity" && highGenderNormalValue > highValidValue) {
                highGenderNormal.addClass("error");
                alert("<%=StringUtil.getMessageForKey("error.high.normal.high.valid.order")%>");
            }
        }
    }

    function validRangeCheck() {
        var highValidValue, lowValidValue;
        var lowValid = $jq("#lowValid");
        var highValid = $jq("#highValid");

        lowValid.removeClass("error");
        lowValidValue = +lowValid.val();
        if (lowValidValue != "-Infinity" &&
                lowValidValue != lowValid.val()) {
            lowValid.addClass("error");
            alert("<%=StringUtil.getMessageForKey("error.low.valid.value")%>");
            return;
        }

        highValid.removeClass("error");
        highValidValue = +highValid.val();
        if (highValidValue != "Infinity" &&
                highValidValue != highValid.val()) {
            highValid.addClass("error");
            alert("<%=StringUtil.getMessageForKey("error.high.valid.value")%>");
            return;
        }

        if (lowValidValue != "-Infinity" && highValidValue != "Infinity" &&
                lowValidValue >= highValidValue) {
            highValid.addClass("error");
            lowValid.addClass("error");
            alert("<%=StringUtil.getMessageForKey("error.low.high.valid.order")%>");
            return;
        }

        $jq(".rowKey").each(function () {
            //index is in the template
            if ($jq(this).val() != "index") {
                normalRangeCheck($jq(this).val());
            }
        });
    }
    function checkReadyForNextStep() {
        var ready = true;
        if (step == "step1") {
            $jq("#step1Div .required").each(function () {
                if (!$jq(this).val() || $jq(this).val() == 0 || $jq(this).val().length == 0) {
                    ready = false;
                }
            });
        } else if (step == "step2") {
            $jq("#sampleTypeSelectionDiv .required").each(function () {
                if (!$jq(this).val() || $jq(this).val() == 0 || $jq(this).val().length == 0) {
                    ready = false;
                }
            });
        } else if (step == "step3Dictionary") {
            $jq("#testDisplayOrderDiv .required").each(function () {
                if (!$jq(this).val() || $jq(this).val() == 0 || $jq(this).val().length == 0) {
                    ready = false;
                }
            });
        }

        $jq("#nextButton").prop("disabled", !ready);
    }
    
    function editRangeAsk(){
    	//alert(step);
    	step = 'step3NumericAsk';
    	useResultLimitDefault = false;
    	nextStep();
    }
    
    function editDictionaryAsk(){
    	//alert(step);
    	step = 'step3DictionaryAsk';
    	nextStep();
    }

    function nextStep() {
        var resultTypeId;
		//alert(step);
        if (step == 'step1') {
            step = 'step2';
            setStep1ReadOnlyFields();
            $jq(".step1").hide();
            $jq(".step2").show();
            $jq("#nextButton").attr("disabled", "disabled");
            $jq("#sampleTypeContainer").show();
            
            var sampleType = null;
            var sampleTypeId = null;
            
            $jq(".resultClass").each(function (i,elem) {
            	sampleType = $jq(elem).attr("fSampleType")
            });
            
			var optionsArray = Array.from($jq("#sampleTypeSelection")[0]);
			var sampleTypeOption = optionsArray.filter(function(elem) { return elem.label === sampleType });
			sampleTypeOption.forEach(function(elem) {
				$jq(elem).attr("selected", true)
				sampleTypeId = $jq(elem).attr("value");
			});
			
			var sampleTypeData = {value:sampleTypeId, type:"add"};
			//Triggered manualy, non-event. Added test for undefined event object in createOrderBoxForSampleType
			//because "change" triggers extra call
            createOrderBoxForSampleType(sampleTypeData);
			$jq("#sampleTypeSelection").change();
			$jq("#nextButton").prop("disabled", false);	

        } else if (step == 'step2') {
        	var significantDigits = null;
			var dictionaryValues = null;
			var dictionaryIds = null;
			var referenceValue = null;
			var referenceId = null;

            $jq(".resultClass").each(function (i,elem) {
            	significantDigits = $jq(elem).attr("fSignificantDigits")
            	dictionaryValues = $jq(elem).attr("fDictionaryIds")
            	dictionaryIds = $jq(elem).attr("fDictionaryValues")
            	referenceValue = $jq(elem).attr("fReferenceValue")
            	referenceId = $jq(elem).attr("fReferenceId")
            });
            
            // format dictionary values
            if(!typeof dictionaryValues === 'undefined') {
            	var tmpArray = dictionaryValues.split("[");
            	var tmpArray = tmpArray[1].split("]");
            	var tmpArray = tmpArray[0].split(", ");
            	var valuesArray = jQuery.makeArray(tmpArray);
            }
            resultTypeId = $jq("#resultTypeSelection").val();
            $jq("#sortTitleDiv").attr("align", "left");
            $jq("#step2BreadCrumb").hide();
            $jq("#step2Guide").hide();
            makeSortListsReadOnly();
            if (resultTypeId == '<%=TypeOfTestResultService.ResultType.ALPHA.getId()%>' ||
                    resultTypeId == '<%=TypeOfTestResultService.ResultType.REMARK.getId()%>') {
                $jq("#sampleTypeSelectionDiv").hide();
                $jq("#sortTitleDiv").text("Sample type and test sort order");
                $jq(".confirmShow").show();
                $jq(".selectShow").hide();
                $jq("#step2Confirm").show();
                createJSON();
            } else if (resultTypeId == '<%=TypeOfTestResultService.ResultType.NUMERIC.getId()%>') {
            		step = "step3Numeric";
            		$jq("#significantDigits").val(significantDigits);
                    $jq("#normalRangeAskDiv").show();
                    $jq("#editResultLimitsButton").show();
                    $jq("#sampleTypeSelectionDiv").hide();
            } else if (resultTypeId == '<%=TypeOfTestResultService.ResultType.DICTIONARY.getId()%>' ||
                    resultTypeId == '<%=TypeOfTestResultService.ResultType.MULTISELECT.getId()%>' ||
                    resultTypeId == '<%=TypeOfTestResultService.ResultType.CASCADING_MULTISELECT.getId()%>') {
                step = 'step3Dictionary';
                $jq("#sampleTypeSelectionDiv").hide();
                $jq("#dictionaryAskDiv").show();
            }
        } else if (step == "step3NumericAsk") {
        	step = "step3Numeric";
        	$jq("#normalRangeDiv").show();
            $jq("#sampleTypeSelectionDiv").hide();
            $jq(".resultLimits").show();
            resetResultLimits();
        } else if (step == "step3Numeric") {
        	
        	var defaultLimitsString = null;
        	defaultLimitsString = $jq("#fLimit").val();
        	//console.log("defaultLimitsString:" + defaultLimitsString);
        	defaultResultLimits = buildResultLimitesFromDefault(defaultLimitsString);
        	
            $jq("#normalRangeDiv input,select").attr("disabled", "disabled");
            $jq(".confirmShow").show();
            $jq(".selectShow").hide();
            $jq(".resultLimits").hide();
            $jq(".resultLimitsConfirm").show();
            
            createJSON();
            
        } else if (step == "step3DictionaryAsk") {
        	step = 'step3Dictionary';
        	$jq("#dictionaryAskDiv").hide();
        	$jq("#dictionaryVerifyId").hide();
        	$jq(".dictionarySelect").show();
        	
        	var dictionaryValues = null;
        	var dictionaryIds = null;
        	var referenceValue = null;
        	var referenceId = null;
            $jq(".resultClass").each(function (i,elem) {
            	dictionaryValues = $jq(elem).attr("fDictionaryValues")
            	dictionaryValues = $jq(elem).attr("fDictionaryIds")
            	referenceValue = $jq(elem).attr("fReferenceValue")
            	referenceId = $jq(elem).attr("fReferenceId")
            });
            
            $jq("#referenceValue").text($jq(referenceValue).text());
            $jq("#referenceId").text($jq(referenceId).text());
            
        } else if (step == "step3Dictionary") {
        	$jq("#dictionaryAskDiv").hide();
            $jq(".dictionarySelect").hide();
            $jq("#sortDictionaryDiv").hide();
           
            
            var dictionaryValues = null;
        	var dictionaryIds = null;
        	var referenceValue = null;
        	var referenceId = null;
            $jq(".resultClass").each(function (i,elem) {
            	dictionaryValues = $jq(elem).attr("fDictionaryValues")
            	dictionaryIds = $jq(elem).attr("fDictionaryIds")
            	referenceValue = $jq(elem).attr("fReferenceValue")
            	referenceId = $jq(elem).attr("fReferenceId")
            });
            
            if($jq("#referenceSelection option").length == 1) {
            	//console.log("dict default:" + dictionaryValues);
            	$jq("#referenceValue").text(referenceValue);
            	buildVerifyDictionaryListFromDefault(dictionaryValues);
            } else {
            	console.log("dict group:");
            	$jq("#referenceValue").text($jq("#referenceSelection option:selected").text());
            	buildVerifyDictionaryList();
            }

            $jq("#dictionaryVerifyId").show();
            $jq(".selectListConfirm").show();
            $jq(".confirmShow").show();
            $jq(".selectShow").hide();
            createJSON();
        }
    }
    
    $jq(".dictionarySelect").hide();
    $jq("#sortDictionaryDiv").hide();
    
    function buildResultLimitesFromDefault(defaultLimitsString) {
    	var resultLimits = [];
    	
    	var gender = null;
    	var age = [];
    	var ageLow = null; var ageHigh = null;
    	var normal = [];
    	var normalLow = null; var normalHigh = null;
    	var valid = [];
    	var validLow = null; var validHigh = null;
		
    	var tmpArray = defaultLimitsString.split("|");
    	
    	for (var i = 0; i < tmpArray.length-1; i++) {
    		var tmpRangeArray = tmpArray[i].split(",");
    			gender = tmpRangeArray[0];
    			
    			var lowHigh = tmpRangeArray[1].split("-");
    			ageLow = lowHigh[0]; 
    			ageHigh = (lowHigh.length == 2) ? lowHigh[1] : "Infinity";
    			age = [ ageLow, ageHigh];
    			
    			normalLow = "-Infinity"; 
    			normalHigh = "Infinity"; 
    			normal = [normalLow, normalHigh];
    			
    			validLow = "-Infinity"; 
    			validHigh = "Infinity"; 
    			valid = [validLow, validHigh];
    			
    			resultLimits.push([gender, age, normal, valid]);
    	}

        return resultLimits;
    }
    
    function buildVerifyDictionaryListFromDefault(dictionaryValues) {
        var verifyList = $jq("#dictionaryVerifyListId");
        var qualifyList = $jq("#dictionaryQualify");
        var li, qualified;
        var tmpArray = dictionaryValues.split("[");
    	var tmpArray = tmpArray[1].split("]");
    	var tmpArray = tmpArray[0].split(", ");
    	dictionaryValues = jQuery.makeArray(tmpArray);
        verifyList.empty();
        for(var i=0; i < dictionaryValues.length; i++ ) {
            li = $jq(document.createElement("li"));
            li.val(dictionaryValues[i]);
            //qualified = qualifyList.find("option[value=" + this.value + "]:selected").length == 1;
            //li.append($jq(this).text() + (qualified ? "-- qualified" : ""));
            li.append(dictionaryValues[i]);
            verifyList.append(li);
        };
    }
    
    function buildVerifyDictionaryList() {
        var verifyList = $jq("#dictionaryVerifyListId");
        var qualifyList = $jq("#dictionaryQualify");
        var li, qualified;
        verifyList.empty();
        $jq("#dictionaryNameSortUI li").each(function () {
            li = $jq(document.createElement("li"));
            li.val(this.value);
            qualified = qualifyList.find("option[value=" + this.value + "]:selected").length == 1;
            li.append($jq(this).text() + (qualified ? "-- qualified" : ""));
            verifyList.append(li);
        });
    }
    function navigateBack() {
        $jq(".confirmShow").hide();
        $jq(".selectShow").show();
        if (step == 'step1') {
            submitAction('TestManagementConfigMenu.do');
        } else if (step == 'step2') {
            goBackToStep1();
        } else if ( step == 'step3Dictionary' || step == 'step3Numeric' || step == 'step3NumericAsk' || step == 'step3DictionaryAsk'){
            goBackToStep2();
        }
    }

    function navigateBackFromConfirm() {
        $jq(".confirmationBreadCrumb").hide();
        $jq(".confirmShow").hide();
        $jq(".selectShow").show();
        if (step == 'step2') {
            goBackToStep2();
        } else if (step == "step3Numeric") {
          goBackToResultLimits();
        } else if ( step == "step3Dictionary"){
          goBackToStep3Dictionary();
        }
    }

    function goBackToStep1() {
        step = 'step1';
        $jq('.step2').hide();
        $jq(".step1").show();
        $jq(".confirmShow").hide();
        $jq(".selectShow").show();
        $jq(".dictionarySelect").hide();
        $jq(".confirmationBreadCrumb").hide();
        $jq("#nextButton").removeAttr("disabled");
        $jq(".sortingMainDiv").remove();
        $jq("#normalRangeDiv").hide();
        $jq("#normalRangeDiv input,select").removeAttr("disabled");
        $jq(".resultLimits").hide();
        $jq("#sortTitleDiv").attr("align", "center");
        //The reason for the li is that the sample sortable UL is hardcoded as sortable, even if it has no contents
        if ($jq(".sortable-tag li").length > 0) {
            $jq(".sortable-tag").addClass("sortable");
            $jq(".ui-state-default_oe-tag").addClass("ui-state-default_oe");
            $jq(".sortable").sortable("enable");
        }
        $jq("#sortTitleDiv").text('<bean:message key="label.test.display.order"/>');
        $jq("#dictionaryVerifyId").hide();
        $jq(".notStep1BreadCrumb").hide();

        clearMultiSelectContainer($jq("#sampleTypeSelectionDiv"));
        clearDictionaryLists();
    }

    function goBackToStep2() {
        step = "step2";
        $jq(".step2").show();
        $jq(".resultLimits").hide();
        $jq("#editResultLimitsButton").hide();
        $jq("#sortTitleDiv").attr("align", "center");
        //The reason for the li is that the sample sortable UL is hardcoded as sortable, even if it has no contents
        if ($jq(".sortable-tag li").length > 0) {
            $jq(".sortable-tag").addClass("sortable");
            $jq(".ui-state-default_oe-tag").addClass("ui-state-default_oe");
            $jq(".sortable").sortable("enable");
        }
        $jq("#sortTitleDiv").text('<bean:message key="label.test.display.order"/>');
        clearDictionaryLists();

        $jq("#sampleTypeSelectionDiv").show();
        $jq(".dictionarySelect").hide();
        $jq("#dictioanryVerifyListId").hide();
        $jq(".confirmationBreadCrumb").hide();
        $jq("#nextButton").removeAttr("disabled");
        $jq(".confirmShow").hide();
        $jq(".selectShow").show();
        $jq("#normalRangeDiv").hide();
        $jq("#normalRangeDiv input,select").removeAttr("disabled");
        $jq("#dictionaryVerifyId").hide();
        $jq("#dictionaryAskDiv").hide();
        $jq("#dictionaryVerifyId").hide();
    }

    function goBackToStep3Dictionary(){
        $jq(".dictionarySelect").show();
        $jq("#sortDictionaryDiv").show();
        $jq("#dictionaryVerifyId").hide();
        $jq(".confirmationBreadCrumb").hide();
        $jq(".selectListConfirm").hide();
        $jq(".confirmShow").hide();
        $jq(".selectShow").show();
    }

    function goBackToResultLimits(){
        $jq(".confirmShow").hide();
        $jq(".selectShow").show();
        $jq(".confirmationBreadCrumb").hide();
        $jq("#dictionaryVerifyId").hide();
        $jq("#normalRangeDiv").show();
        $jq(".resultLimits").show();
        $jq("#normalRangeDiv input,select").removeAttr("disabled");
        resetResultLimits();
    }

    function doLims(item, index){
    	//alert(item,index);
    	var items = item.split(",");
    	alert(items);
    	var gender = items[0];
    	
    	var age = items[1];
    	var ages = age.split("-");
    	var ageLow = ages[0];
    	var ageHigh = ages[1];
    	
    	var normal = items[2];
    	var normals = normal.split("-");
    	var normalLow = normals[0];
    	var normalHigh = normals[1];
    	
    	var valid = items[3];
    	var valids = valid.split("-");
    	var validLow = valids[0];
    	var validHigh = valids[1];
    	
    	//alert(gender, age, normalLow, normalHigh, )
    	//ageRangeSelected();
    }
    
    function resetResultLimits(){
        genderMatersForRange(false,'0');
        $jq("#normalRangeDiv .createdFromTemplate").remove();

        $jq("#lowNormal_0").val("-Infinity");
        $jq("#highNormal_0").val("Infinity");
        $jq("#lowValid").val("-Infinity");
        $jq("#highValid").val("Infinity");
        $jq("#lowNormal_0").removeClass("error");
        $jq("#highNormal_0").removeClass("error");
        $jq("#reportingRange_0").val('');
        //$jq("#significantDigits").val('');
        $jq("#lowerAge_0").val('0');
        $jq("#upperAge_0").text('');
        $jq("#upperAgeSetter_0").val('Infinity');
        $jq("#upperAgeSetter_0").show();
        $jq("#ageRangeSelect_0").val(0);
        $jq("#genderCheck_0").prop('checked', false);
        
    }
    function clearMultiSelectContainer(root) {
        root.find(".asmList li").remove();
        root.find(".asmSelect option:disabled").removeAttr("disabled");
        root.find(".asmSelect .asmOptionDisabled").removeClass("asmOptionDisabled");
        root.find(" option:selected").removeAttr("selected");
    }
    function setStep1ReadOnlyFields() {
        var panelNames = "";

        $jq("#testNameEnglishRO").text($jq("#testNameEnglish").val());
        $jq("#testNameFrenchRO").text($jq("#testNameFrench").val());
        $jq("#testReportNameEnglishRO").text($jq("#testReportNameEnglish").val());
        $jq("#testReportNameFrenchRO").text($jq("#testReportNameFrench").val());
        $jq("#testSectionRO").text($jq("#testUnitSelection  option:selected").text());
        $jq("#panelSelection option:selected").each(function () {
            panelNames += ($jq(this).text()) + "\<br\>";
        });
        //we use append for optional
        $jq("#panelRO").empty();
        $jq("#panelRO").append(panelNames);
        $jq("#uomRO").empty();
        $jq("#uomRO").append($jq("#uomSelection  option:selected").text());
        $jq("#loincRO").text($jq("#loinc").val());
        $jq("#resultTypeRO").text($jq("#resultTypeSelection  option:selected").text());
        $jq("#activeRO").text($jq("#active").attr("checked") ? "Y" : "N");
        $jq("#orderableRO").text($jq("#orderable").attr("checked") ? "Y" : "N");
    }

    function createJSON() {
        var jsonObj = {};
        jsonObj.testNameEnglish = $jq("#testNameEnglish").val();
        jsonObj.testNameFrench = $jq("#testNameFrench").val();
        jsonObj.testReportNameEnglish = $jq("#testReportNameEnglish").val();
        jsonObj.testReportNameFrench = $jq("#testReportNameFrench").val();
        jsonObj.testSection = $jq("#testUnitSelection").val();
        jsonObj.panels = [];
        addJsonPanels(jsonObj);
        $jq("#panelSelection").val();
        jsonObj.uom = $jq("#uomSelection").val();
        jsonObj.loinc = $jq("#loinc").val();
        jsonObj.resultType = $jq("#resultTypeSelection").val();
        jsonObj.orderable = $jq("#orderable").attr("checked") ? 'Y' : 'N';
        jsonObj.active = $jq("#active").attr("checked") ? 'Y' : 'N';
        
        $jq(".resultClass").each(function (i,elem) {
        	jsonObj.testId = $jq(elem).attr('fTestId');
            //console.log("createJSON: " + $jq(elem).attr('fTestId') + ":" + $jq(elem).attr('fResultType'));
        });
        
        jsonObj.sampleTypes = [];
        addJsonSortingOrder(jsonObj);
        
        if (step == "step3Numeric") {
        	if(useResultLimitDefault) {
            	addJsonResultLimitsFromDefault(jsonObj);
            	useResultLimitDefault = false;
        	} else {
        		addJsonResultLimits(jsonObj);
        	}
        } else if (step == "step3Dictionary") {
        	
            addJsonDictionary(jsonObj);
        
        	//console.log(JSON.stringify(jsonObj.dictionary));
        	//dictionary from defaults if empty
        	if(JSON.stringify(jsonObj.dictionary == "[]")) {
        		console.log(JSON.stringify(jsonObj.dictionaryReference));
        		var significantDigits = null;
				var dictionaryValues = null;
				var referenceValue = null;
				var dictionaryIds = null;
				var referenceId = null;

           	 	$jq(".resultClass").each(function (i,elem) {
            		significantDigits = $jq(elem).attr("fSignificantDigits")
            		dictionaryValues = $jq(elem).attr("fDictionaryValues")
            		referenceValue = $jq(elem).attr("fReferenceValue")
            		dictionaryIds = $jq(elem).attr("fDictionaryIds")
            		referenceId = $jq(elem).attr("fReferenceId")
            	});
            	//console.log("Values:" + dictionaryValues + "::::" + referenceValue);
            	//console.log("Ids:" + dictionaryIds + "::::" + referenceId);
            	//dictionary by id and reference by id required
            	var valuesArray = dictionaryValues.split("[");
        		var valuesArray = valuesArray[1].split("]");
        		var valuesArray = valuesArray[0].split(", ");
        		
        		var idsArray = dictionaryIds.split("[");
        		var idsArray = idsArray[1].split("]");
        		var idsArray = idsArray[0].split(", ");
            	
            	for(var i=0; i < idsArray.length; i++) {
            		dictionary = {};
                	dictionary.value = idsArray[i];
                	dictionary.qualified = "N";
                	jsonObj.dictionary[i] = dictionary;
        		}
            	if (referenceId == "null") {
            		referenceId = null;
            	}
            	jsonObj.dictionaryReference = referenceId;
        	}
        }
        console.log(JSON.stringify(jsonObj));
        
		$jq("#jsonWad").val(JSON.stringify(jsonObj));
    }
    
    function addJsonPanels(jsonObj) {
        var panelSelections = $jq("#panelSelection").val();
        var jsonPanel;
        jsonObj.panels = [];
        if (panelSelections) {
            panelSelections.each(function (value, index) {
                jsonPanel = {};
                jsonPanel.id = value;
                jsonObj.panels[index] = jsonPanel;
            });
        }
    }

    function addJsonSortingOrder(jsonObj) {
        var sampleTypes = $jq("#sampleTypeSelection").val();
        var i, jsonSampleType, index, test;


        for (i = 0; i < sampleTypes.length; i++) {
            jsonSampleType = {};
            jsonSampleType.typeId = sampleTypes[i];
            jsonSampleType.tests = [];
            index = 0;
            $jq("#" + sampleTypes[i] + " li").each(function () {
            	//console.log("addJsonSortingOrder: " + jsonObj.testId + ":" + $jq(this).val());
            	//if (jsonObj.testId != $jq(this).val()){
            	if (true){
                	test = {};
                	test.id = $jq(this).val();
                	jsonSampleType.tests[index++] = test;
            	}
            });
            jsonObj.sampleTypes[i] = jsonSampleType;
        }
    }
    
    function addJsonResultLimitsFromDefault(jsonObj) {
    	//gnr, global defaultResultLimits
    	
    	for (var i = 0; i < defaultResultLimits.length; i++) {
        		console.log("addJsonResultLimitsFromDefault:defaultResultLimits:" + i + ":" + defaultResultLimits[i]);
        }
        var rowIndex, limit, gender, yearMonth, upperAge;
        var countIndex = 0;

        jsonObj.lowValid = defaultResultLimits[0][3][0];
        jsonObj.highValid = defaultResultLimits[0][3][1];
        jsonObj.resultLimits = [];

        for (var rowIndex = 0; rowIndex < defaultResultLimits.length; rowIndex++) {
            
            //yearMonth = monthYear = $jq(".yearMonthSelect_" + rowIndex + ":checked").val();
            yearMonth = 'M'; // always month regardless
            limit = {};

            upperAge = defaultResultLimits[rowIndex][1][1];
            if (upperAge != "Infinity") {
                //limit.highAgeRange = yearMonth == "<%=StringUtil.getMessageForKey("abbreviation.year.single")%>" ? (upperAge * 12).toString() : upperAge;
            	limit.highAgeRange = upperAge;
            } else {
                limit.highAgeRange = "Infinity";
            }
            
            if( defaultResultLimits[rowIndex][0] == "n/a" ){
            	limit.gender = false;
            } else {
            	limit.gender = true;
            }
            
            limit.lowNormal = defaultResultLimits[rowIndex][2][0];
            limit.highNormal = defaultResultLimits[rowIndex][2][1];
            //limit.reportingRange = not used

            if (limit.gender) {
                limit.lowNormalFemale = defaultResultLimits[rowIndex][2][0];
                limit.highNormalFemale = defaultResultLimits[rowIndex][2][1];
                //limit.reportingRangeFemale = not used
                rowIndex++; // m/f in same json object so skip row
            }

            jsonObj.resultLimits[countIndex++] = limit;
        }

    }
    
    function addJsonResultLimits(jsonObj) {
        var rowIndex, limit, gender, yearMonth, upperAge;
        var countIndex = 0;

        jsonObj.lowValid = $jq("#lowValid").val();
        jsonObj.highValid = $jq("#highValid").val();
        jsonObj.significantDigits = $jq("#significantDigits").val();
        jsonObj.resultLimits = [];

        $jq("#normalRangeDiv .rowKey").each(function () {
            rowIndex = $jq(this).val();
            gender = $jq("#genderCheck_" + rowIndex).is(":checked");
            yearMonth = monthYear = $jq(".yearMonthSelect_" + rowIndex + ":checked").val();
            limit = {};

            upperAge = $jq("#upperAgeSetter_" + rowIndex).val();
            if (upperAge != "Infinity") {
                limit.highAgeRange = yearMonth == "<%=StringUtil.getMessageForKey("abbreviation.year.single")%>" ? (upperAge * 12).toString() : upperAge;
            } else {
                limit.highAgeRange = upperAge;
            }

            limit.gender = gender;
            limit.lowNormal = $jq("#lowNormal_" + rowIndex).val();
            limit.highNormal = $jq("#highNormal_" + rowIndex).val();
            limit.reportingRange = $jq("#reportingRange_" + rowIndex).val();

            if (gender) {
                limit.lowNormalFemale = $jq("#lowNormal_G_" + rowIndex).val();
                limit.highNormalFemale = $jq("#highNormal_G_" + rowIndex).val();
                limit.reportingRangeFemale = $jq("#reportingRange_G_" + rowIndex).val();
            }

            jsonObj.resultLimits[countIndex++] = limit;
        });

    }

    function addJsonDictionary(jsonObj) {
        var dictionary;
        jsonObj.dictionary = [];

        $jq("#dictionarySelection option:selected").each(function (index, value) {
            if ($jq("#referenceSelection option:selected[value=" + value.value + "]").length == 1) {
                jsonObj.dictionaryReference = value.value;
            }
            dictionary = {};
            dictionary.value = value.value;
            dictionary.qualified = $jq("#qualifierSelection option:selected[value=" + value.value + "]").length == 1 ? "Y" : "N";
            jsonObj.dictionary[index] = dictionary;
        });
    }
    function submitAction(target) {
        var form = window.document.forms[0];
        form.action = target;
        form.submit();
    }

</script>

	<html:hidden styleId="jsonWad" name='<%=formName%>' property="jsonWad" />

	<input type="button"
		value="<%=StringUtil.getMessageForKey("banner.menu.administration")%>"
		onclick="submitAction('MasterListsPage.do');" class="textButton" />
	&rarr; <input type="button"
		value="<%=StringUtil.getMessageForKey("configuration.test.management")%>"
		onclick="submitAction('TestManagementConfigMenu.do');"
		class="textButton" />&rarr; <span class="step1"> <bean:message
			key="configuration.test.modify" />
	</span> <span class="step2 notStep1BreadCrumb" id="step2BreadCrumb"
		style="display: none"> <input type="button"
		value="<%=StringUtil.getMessageForKey("configuration.test.modify")%>"
		onclick="goBackToStep1();" class="textButton" />&rarr; <%=StringUtil.getMessageForKey("label.selectSampleType")%>
	</span> <span id="step2Confirm notStep1BreadCrumb"
		class="confirmationBreadCrumb" style="display: none"> <input
		type="button"
		value="<%=StringUtil.getMessageForKey("configuration.test.modify")%>"
		onclick="goBackToStep1();" class="textButton" />&rarr; <input
		type="button"
		value="<%=StringUtil.getMessageForKey("label.selectSampleType")%>"
		onclick="goBackToStep2();" class="textButton" />&rarr; <bean:message
			key="label.confirmation" />
	</span> <span class="dictionarySelect notStep1BreadCrumb"
		style="display: none"> <input type="button"
		value="<%=StringUtil.getMessageForKey("configuration.test.modify")%>"
		onclick="goBackToStep1();" class="textButton" />&rarr; <input
		type="button"
		value="<%=StringUtil.getMessageForKey("label.selectSampleType")%>"
		onclick="goBackToStep2();" class="textButton" />&rarr; <bean:message
			key="label.select.list.values" />
	</span> <span class="resultLimits notStep1BreadCrumb" style="display: none">
		<input type="button"
		value="<%=StringUtil.getMessageForKey("configuration.test.modify")%>"
		onclick="goBackToStep1();" class="textButton" />&rarr; <input
		type="button"
		value="<%=StringUtil.getMessageForKey("label.selectSampleType")%>"
		onclick="goBackToStep2();" class="textButton" />&rarr; <bean:message
			key="label.set.result.limits" />
	</span> <span
		class="selectListConfirm confirmationBreadCrumb notStep1BreadCrumb"
		style="display: none"> <input type="button"
		value="<%=StringUtil.getMessageForKey("configuration.test.modify")%>"
		onclick="goBackToStep1();" class="textButton" />&rarr; <input
		type="button"
		value="<%=StringUtil.getMessageForKey("label.selectSampleType")%>"
		onclick="goBackToStep2();" class="textButton" />&rarr; <input
		type="button"
		value="<%=StringUtil.getMessageForKey("label.select.list.values")%>"
		onclick="goBackToStep3Dictionary();" class="textButton" />&rarr; <bean:message
			key="label.confirmation" />
	</span> <span class="resultLimitsConfirm confirmationBreadCrumb"
		style="display: none"> <input type="button"
		value="<%=StringUtil.getMessageForKey("configuration.test.modify")%>"
		onclick="goBackToStep1();" class="textButton" />&rarr; <input
		type="button"
		value="<%=StringUtil.getMessageForKey("label.selectSampleType")%>"
		onclick="goBackToStep2();" class="textButton" />&rarr; <input
		type="button"
		value="<%=StringUtil.getMessageForKey("label.set.result.limits")%>"
		onclick="goBackToResultLimits();" class="textButton" />&rarr; <bean:message
			key="label.confirmation" />
	</span>

	<html:hidden property="testId" name="<%=formName%>" styleId="testId" />

	<div id="catDiv" style="display: none">
		<h1 id="action">
			<bean:message key="label.button.edit" />
		</h1>

		<h2><%=StringUtil.getMessageForKey("sample.entry.test")%>:<span
				id="testName"></span>
		</h2>


		<%
			// tr section test result from testCatalog.jsp
		%>
		<%
			for (TestCatalogBean bean : testCatBeanList) {
		%>
		<table>
			<tbody fTestId='<%=bean.getId()%>' 
					fResultType='<%=bean.getResultType()%>'
					fPanel='<%=bean.getPanel()%>' 
					fSampleType='<%=bean.getSampleType()%>'
					fSignificantDigits='<%=bean.getSignificantDigits()%>'
					fDictionaryValues='<%=bean.getDictionaryValues()%>'
					fDictionaryIds='<%=bean.getDictionaryIds()%>'
					fReferenceValue='<%=bean.getReferenceValue()%>'
					fReferenceId='<%=bean.getReferenceId()%>'
				class='resultClass'>
				
				<tr>
					<td colspan="2"><span class="catalog-label"><bean:message
								key="configuration.test.catalog.name" /></span></td>
					<td colspan="2"><span class="catalog-label"><bean:message
								key="configuration.test.catalog.report.name" /></span></td>
				</tr>
				<tr>
					<td width="25%"><span class="catalog-label">En.</span> <b><%=bean.getEnglishName()%></b>
					</td>
					<td width="25%"><span class="catalog-label">Fr.</span> <b><%=bean.getFrenchName()%></b>
					</td>
					<td width="25%"><span class="catalog-label">En.</span> <b><%=bean.getEnglishReportName()%></b>
					</td>
					<td width="25%"><span class="catalog-label">Fr.</span> <b><%=bean.getFrenchReportName()%></b>
					</td>
				</tr>
				<tr>
					<td><b><%=bean.getActive()%></b></td>
					<td><b><%=bean.getOrderable()%></b></td>
				</tr>
				<tr>
					<td><span class="catalog-label"><bean:message
								key="label.test.unit" /></span> <b><%=bean.getTestUnit()%></b></td>
					<td><span class="catalog-label"><bean:message
								key="label.sample.types" /></span> <b><%=bean.getSampleType()%></b></td>
					<td><span class="catalog-label"><bean:message
								key="label.panel" /></span> <b><%=bean.getPanel()%></b></td>
					<td><span class="catalog-label"><bean:message
								key="label.result.type" /></span> <b><%=bean.getResultType()%></b></td>
				</tr>
				<tr>
					<td><span class="catalog-label"><bean:message
								key="label.uom" /></span> <b><%=bean.getUom()%></b></td>
					<td><span class="catalog-label"><bean:message
								key="label.significant.digits" /></span> <b><%=bean.getSignificantDigits()%></b>
					</td>
					<td><span class="catalog-label"><bean:message
								key="label.loinc" /></span> <b><%=bean.getLoinc()%></b></td>
				</tr>

				<%
					if (bean.isHasDictionaryValues()) {
							boolean top = true;
							for (String value : bean.getDictionaryValues()) {
				%>
				<tr>
					<td>
						<%
							if (top) {
						%><span class="catalog-label"><bean:message
								key="configuration.test.catalog.select.values" /></span> <%
 	}
 %>
					</td>
					<td colspan="2"><b><%=value%></b></td>
					<td colspan="2">
						<%
							if (top) {
											top = false;
						%><span class="catalog-label"><bean:message
								key="configuration.test.catalog.reference.value" /></span> <b><%=bean.getReferenceValue()%></b>
					</td>
					<%
						}
								}
							}
					%>
				</tr>

				<%
					if (bean.isHasLimitValues()) {
						 String limitArray = null;
				%>
				<tr>
					<td colspan="5" align="center"><span class="catalog-label"><bean:message
								key="configuration.test.catalog.result.limits" /></span></td>
				</tr>
				<tr>
					<td><span class="catalog-label"><bean:message
								key="label.sex" /></span></td>
					<td><span class="catalog-label"><bean:message
								key="configuration.test.catalog.age.range.months" /></span></td>
					<td><span class="catalog-label"><bean:message
								key="configuration.test.catalog.normal.range" /></span></td>
					<td><span class="catalog-label"><bean:message
								key="configuration.test.catalog.valid.range" /></span></td>
				</tr>
				<%
					String fLimitString = "";
					for (ResultLimitBean limitBean : bean.getResultLimits()) {
						fLimitString = fLimitString + limitBean.getGender() + ",";
						fLimitString = fLimitString + limitBean.getAgeRange() + ",";
						fLimitString = fLimitString + limitBean.getNormalRange() + ",";
						fLimitString = fLimitString + limitBean.getValidRange() + "|";
				%>
				<tr>
					<td><b><%=limitBean.getGender()%></b></td>
					<td><b><%=limitBean.getAgeRange()%></b></td>
					<td><b><%=limitBean.getNormalRange()%></b></td>
					<td><b><%=limitBean.getValidRange()%></b></td>
				</tr>
				<%
					}
					%>
					<input id="fLimit" type="hidden" value='<%=fLimitString%>' />
					<%
						}
					}
				%>
			</tbody>
		</table>

	</div>

	<%
		// section testAdd
	%>


	<span class="step2 notStep1BreadCrumb" id="step2BreadCrumb"
		style="display: none"> <input type="button"
		value="<%=StringUtil.getMessageForKey("configuration.test.modify")%>"
		onclick="goBackToStep1();" class="textButton" />&rarr; <%=StringUtil.getMessageForKey("label.selectSampleType")%>
	</span> <span id="step2Confirm notStep1BreadCrumb"
		class="confirmationBreadCrumb" style="display: none"> <input
		type="button"
		value="<%=StringUtil.getMessageForKey("configuration.test.modify")%>"
		onclick="goBackToStep1();" class="textButton" />&rarr; <input
		type="button"
		value="<%=StringUtil.getMessageForKey("label.selectSampleType")%>"
		onclick="goBackToStep2();" class="textButton" />&rarr; <bean:message
			key="label.confirmation" />
	</span> <span class="dictionarySelect notStep1BreadCrumb"
		style="display: none"> <input type="button"
		value="<%=StringUtil.getMessageForKey("configuration.test.modify")%>"
		onclick="goBackToStep1();" class="textButton" />&rarr; <input
		type="button"
		value="<%=StringUtil.getMessageForKey("label.selectSampleType")%>"
		onclick="goBackToStep2();" class="textButton" />&rarr; <bean:message
			key="label.select.list.values" />
	</span> <span class="resultLimits notStep1BreadCrumb" style="display: none">
		<input type="button"
		value="<%=StringUtil.getMessageForKey("configuration.test.modify")%>"
		onclick="goBackToStep1();" class="textButton" />&rarr; <input
		type="button"
		value="<%=StringUtil.getMessageForKey("label.selectSampleType")%>"
		onclick="goBackToStep2();" class="textButton" />&rarr; <bean:message
			key="label.set.result.limits" />
	</span> <span
		class="selectListConfirm confirmationBreadCrumb notStep1BreadCrumb"
		style="display: none"> <input type="button"
		value="<%=StringUtil.getMessageForKey("configuration.test.modify")%>"
		onclick="goBackToStep1();" class="textButton" />&rarr; <input
		type="button"
		value="<%=StringUtil.getMessageForKey("label.selectSampleType")%>"
		onclick="goBackToStep2();" class="textButton" />&rarr; <input
		type="button"
		value="<%=StringUtil.getMessageForKey("label.select.list.values")%>"
		onclick="goBackToStep3Dictionary();" class="textButton" />&rarr; <bean:message
			key="label.confirmation" />
	</span> <span class="resultLimitsConfirm confirmationBreadCrumb"
		style="display: none"> <input type="button"
		value="<%=StringUtil.getMessageForKey("configuration.test.modify")%>"
		onclick="goBackToStep1();" class="textButton" />&rarr; <input
		type="button"
		value="<%=StringUtil.getMessageForKey("label.selectSampleType")%>"
		onclick="goBackToStep2();" class="textButton" />&rarr; <input
		type="button"
		value="<%=StringUtil.getMessageForKey("label.set.result.limits")%>"
		onclick="goBackToResultLimits();" class="textButton" />&rarr; <bean:message
			key="label.confirmation" />
	</span>


	<h3>
		<bean:message key="configuration.test.modify" />
	</h3>

	<input type="checkbox" onchange="guideSelection(this)">
	<bean:message key="label.show.guide" />
	<br />
	<br />

	<div id="guide" style="display: none">
		<span class="requiredlabel">*</span>
		<bean:message key="label.required.field" />
		<br />
		<br /> <span class="step1"> <bean:message
				key="configuration.test.modify.guide.test" />
		</span> <span class="step2" id="step2Guide" style="display: none"> <bean:message
				key="configuration.test.modify.guide.sample.type" />
		</span> <span class="dictionarySelect" style="display: none"> <bean:message
				key="configuration.test.modify.guide.select.list" />
		</span> <span class="resultLimits" style="display: none;"> <bean:message
				key="configuration.test.modify.guide.result.limits" />
		</span> <span class="confirmShow" style="display: none"> <bean:message
				key="configuration.test.modify.guide.verify" />
		</span> <br />
		<hr />
	</div>

	<div id="step1Div" class="step1" style="display: none">
		<table width="80%">
			<tr>
				<td width="25%">
					<table>
						<tr>
							<td colspan="2"><bean:message key="test.testName" /><span
								class="requiredlabel">*</span></td>
						</tr>
						<tr>
							<td width="25%" align="right"><bean:message
									key="label.english" /></td>
							<td width="75%"><input type="text" id="testNameEnglish"
								class="required" onchange="checkReadyForNextStep()" /></td>
						</tr>
						<tr>
							<td width="25%" align="right"><bean:message
									key="label.french" /></td>
							<td width="75%"><input type="text" id="testNameFrench"
								class="required" onchange="checkReadyForNextStep()" /></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td colspan="2"><bean:message key="test.testName.reporting" /><span
								class="requiredlabel">*</span></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="button"
								onclick="copyFromTestName(); checkReadyForNextStep()"
								value='<%=StringUtil.getMessageForKey("test.add.copy.name")%>'></td>
						</tr>
						<tr>
							<td width="25%" align="right"><bean:message
									key="label.english" /></td>
							<td width="75%"><input type="text"
								id="testReportNameEnglish" class="required"
								onchange="checkReadyForNextStep()" /></td>
						</tr>
						<tr>
							<td width="25%" align="right"><bean:message
									key="label.french" /></td>
							<td width="75%"><input type="text" id="testReportNameFrench"
								class="required" onchange="checkReadyForNextStep()" /></td>
						</tr>
					</table>
				</td>

				<td width="25%" style="vertical-align: top; padding: 4px"><bean:message
						key="test.testSectionName" /><span class="requiredlabel">*</span><br />
					<select id="testUnitSelection" class="required"
					onchange="checkReadyForNextStep()">
						<option value="0"></option>
						<%
							for (IdValuePair pair : testUnitList) {
						%>
						<option value='<%=pair.getId()%>'><%=pair.getValue()%>
						</option>
						<%
							}
						%>
				</select><br />
				<br />
				<br />
				<br />
				<br /> <bean:message key="label.loinc" /><br />
					<input type="text" id="loinc"
					onchange="checkReadyForNextStep()" /></td>

				<td width="25%" style="vertical-align: top; padding: 4px"
					id="panelSelectionCell"><bean:message
						key="typeofsample.panel.panel" /><br /> <select
					id="panelSelection" name="panelSelection" multiple="multiple" title="Multiple">
					
					 <%
							for (IdValuePair pair : panelList) {
					 %>
						<option value='<%=pair.getId()%>'><%=pair.getValue()%>
						</option>
					 <%
							}
					 %>
					   
				</select><br />
				<br />
				<br /> <bean:message key="label.unitofmeasure" /><br /> <select
					id="uomSelection">
						<option value='0'></option>
						<%
							for (IdValuePair pair : uomList) {
						%>
						<option value='<%=pair.getId()%>'><%=pair.getValue()%>
						</option>
						<%
							}
						%>
				</select></td>
				<td width="25%" style="vertical-align: top; padding: 4px"><bean:message
						key="result.resultType" /><span class="requiredlabel">*</span><br />
					<select id="resultTypeSelection" class="required"
					onchange="checkReadyForNextStep()">
						<option value="0"></option>
						<%
							for (IdValuePair pair : resultTypeList) {
						%>
						<option value='<%=pair.getId()%>'><%=pair.getValue()%>
						</option>
						<%
							}
						%>
				</select><br />
				<br />
				<br />
				<br />
				<br /> <label for="orderable"><bean:message
							key="test.isActive" /></label> <input type="checkbox" id="active"
					checked="checked" /><br /> <label for="orderable"><bean:message
							key="label.orderable" /></label> <input type="checkbox" id="orderable"
					checked="checked" /></td>
			</tr>
		</table>
	</div>
	<div id="sampleTypeContainer" class="step2"
		style="width: 100%; overflow: hidden; display: none">
		<div style="float: left; width: 20%;">
			<bean:message key="test.testName" />
			<br /> <span class="tab"><bean:message key="label.english" />:
				<span id="testNameEnglishRO"></span></span><br /> <span class="tab"><bean:message
					key="label.french" />: <span id="testNameFrenchRO"></span></span><br /> <br />
			<bean:message key="test.testName.reporting" />
			<br /> <span class="tab"><bean:message key="label.english" />:
				<span id="testReportNameEnglishRO"></span></span><br /> <span class="tab"><bean:message
					key="label.french" />: <span id="testReportNameFrenchRO"></span></span><br />
			<br />
			<bean:message key="test.testSectionName" />
			<div id="testSectionRO" class="tab"></div>
			<br />
			<bean:message key="typeofsample.panel.panel" />
			<div class="tab" id="panelRO">
				<bean:message key="label.none" />
			</div>
			<br />
			<bean:message key="label.unitofmeasure" />
			<div class="tab" id="uomRO">
				<bean:message key="label.none" />
			</div>
			<br />
			<bean:message key="label.loinc" />
			<div class="tab" id="loincRO">
				<bean:message key="label.none" />
			</div>
			<br />
			<bean:message key="result.resultType" />
			<div class="tab" id="resultTypeRO"></div>
			<br />
			<bean:message key="test.isActive" />
			<div class="tab" id="activeRO"></div>
			<br />
			<bean:message key="label.orderable" />
			<div class="tab" id="orderableRO"></div>
			<br />
		</div>
		<div class="step2" style="float: right; width: 80%; display: none">
			<div id="sampleTypeSelectionDiv" class="step2"
				style="float: left; width: 20%;">
				<bean:message key="label.sampleType" />
				<span class="requiredlabel">*</span> <select
					id="sampleTypeSelection" class="required" multiple="multiple"
					title="Multiple">
					<%
						for (IdValuePair pair : sampleTypeList) {
					%>
					<option value='<%=pair.getId()%>'><%=pair.getValue()%>
					</option>
					<%
						}
					%>
				</select><br />
			</div>
			<div id="testDisplayOrderDiv" style="float: left; width: 80%;">
				<div id="sortTitleDiv" align="center">
					<bean:message key="label.test.display.order" />
				</div>
				<div id="endOrderMarker"></div>
				
			
				
				<div class="dictionarySelect dictionaryMultiSelect"
					id="dictionarySelectId"
					style="padding: 10px; float: left; width: 280px; display: none; overflow: hidden">
					<bean:message key="label.select.list.options" />
					<span class="requiredlabel">*</span><br /> <select
						id="dictionarySelection" class="required" multiple="multiple"
						title="Multiple">
						<%
							for (IdValuePair pair : dictionaryList) {
						%>
						<option value='<%=pair.getId()%>'><%=pair.getValue()%>
						</option>
						<%
							}
						%>
					</select><br />
					<br />
					<br />
				</div>
				<div id="dictionaryVerifyId"
					style="padding: 10px; float: left; width: 280px; display: none; overflow: hidden;">
					<span><span class="half-tab"><bean:message
								key="label.select.list" /></span><br />
						<ul id="dictionaryVerifyListId">

						</ul> </span> <span><bean:message key="label.reference.value" /><br>
					<ul>
							<li id="referenceValue"></li>
						</ul></span>
				</div>
				<div id="sortDictionaryDiv" align="center" class="dictionarySelect"
					style="padding: 10px; float: left; width: 33%; display: none;">
					<bean:message key="label.result.order" />
					<span id="dictionarySortSpan" align="left">
						<UL id="dictionaryNameSortUI">

						</UL>
					</span>
				</div>
				<div class="dictionarySelect"
					style="padding: 10px; float: left; width: 280px; display: none">
					<div id="dictionaryReference">
						<bean:message key="label.reference.value" />
						<br /> <select id='referenceSelection'>
							<option value="0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
						</select>
					</div>
					<br>

					<div id="dictionaryQualify" class="dictionaryMultiSelect">
						<bean:message key="label.qualifiers" />
						<br /> <select id='qualifierSelection' multiple='multiple'
							title='Multiple'></select>
					</div>
				</div>
			</div>
		</div>
	</div>
	
		
				<div id="dictionaryAskDiv" style="display: none;">
					<input type="button"
					value="<%=StringUtil.getMessageForKey("label.button.editSelectValues")%>"
					onclick="editDictionaryAsk();" id="editDictionaryButton" /> 
				</div>

	<div id="dictionaryExistingGroups" class="dictionarySelect"
		style="display: none; width: 100%">
		<div style="width: 100%; text-align: center;">
			<bean:message key="label.existing.test.sets" />
		</div>
		<hr>
		<table>
			<%
				while (testCount < groupedDictionaryList.size()) {
			%>
			<tr>
				<td id='<%="dictionaryGroup_" + testCount%>'
					style="padding: 5px 10px; vertical-align: top"><input
					type="button"
					value="<%=StringUtil.getMessageForKey("label.form.select")%>"
					onclick="<%="dictionarySetSelected(" + testCount + ");"%>"
					class="textButton" />
					<ul style="padding-left: 0; list-style-type: none">
						<%
							for (IdValuePair pair : groupedDictionaryList.get(testCount)) {
						%>
						<li value="<%=pair.getId()%>"><%=pair.getValue()%></li>
						<%
							}
						%>
					</ul> <%
 	testCount++;
 		columnCount = 1;
 %></td>
				<%
					while (testCount < groupedDictionaryList.size() && (columnCount < columns)) {
				%>
				<td id='<%="dictionaryGroup_" + testCount%>'
					style="padding: 5px 10px; vertical-align: top"><input
					type="button"
					value="<%=StringUtil.getMessageForKey("label.form.select")%>"
					onclick="<%="dictionarySetSelected(" + testCount + ");"%>"
					class="textButton" />
					<ul style="padding-left: 0; list-style-type: none">
						<%
							for (IdValuePair pair : groupedDictionaryList.get(testCount)) {
						%>
						<li value="<%=pair.getId()%>"><%=pair.getValue()%></li>
						<%
							}
						%>
					</ul></td>
				<%
					testCount++;
							columnCount++;
				%>
				<%
					}
				%>

			</tr>
			<%
				}
			%>
		</table>
	</div>
	<div id="normalRangeTemplate" style="display: none;">
		<table>
			<tr class="row_index createdFromTemplate">
				<td><input type="hidden" class="rowKey" value="index" /><input
					id="genderCheck_index" type="checkbox"
					onchange="genderMatersForRange(this.checked, 'index')"></td>
				<td><span class="sexRange_index" style="display: none">
						<bean:message key="sex.male" />
				</span></td>
				<td><input class="yearMonthSelect_index" type="radio"
					name="time_index"
					value="<%=StringUtil.getMessageForKey("abbreviation.year.single")%>"
					onchange="upperAgeRangeChanged( 'index' )" checked>
				<bean:message key="abbreviation.year.single" /> <input
					class="yearMonthSelect_index" type="radio" name="time_index"
					value="<%=StringUtil.getMessageForKey("abbreviation.month.single")%>"
					onchange="upperAgeRangeChanged( 'index' )">
				<bean:message key="abbreviation.month.single" />&nbsp;</td>
				<td id="lowerAge_index">0</td>
				<td><input type="text" id="upperAgeSetter_index"
					value="Infinity" size="10"
					onchange="upperAgeRangeChanged( 'index' )"><span
					id="upperAge_index"></span></td>
				<td><select id="ageRangeSelect_index"
					onchange="ageRangeSelected( this, 'index');">
						<option value="0"></option>
						<%
							for (IdValuePair pair : ageRangeList) {
						%>
						<option value='<%=pair.getId()%>'><%=pair.getValue()%>
						</option>
						<%
							}
						%>
				</select></td>
				<td><input type="text" value="-Infinity" size="10"
					id="lowNormal_index" class="lowNormal"
					onchange="normalRangeCheck('index');"></td>
				<td><input type="text" value="Infinity" size="10"
					id="highNormal_index" class="highNormal"
					onchange="normalRangeCheck('index');"></td>
				<td><input type="text" value="" size="12"
					id="reportingRange_index"></td>
				<td></td>
				<td></td>
				<td><input id="removeButton_index" type="button"
					class="textButton" onclick='removeLimitRow( index );'
					value="<%=StringUtil.getMessageForKey("label.remove")%>" /></td>
			</tr>
			<tr class="sexRange_index row_index createdFromTemplate">
				<td></td>
				<td><bean:message key="sex.female" /></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><input type="text" value="-Infinity" size="10"
					id="lowNormal_G_index" class="lowNormal"
					onchange="normalRangeCheck('index');"></td>
				<td><input type="text" value="Infinity" size="10"
					id="highNormal_G_index" class="highNormal"
					onchange="normalRangeCheck('index');"></td>
				<td><input type="text" value="" size="12"
					id="reportingRange_G_index"></td>
				<td></td>
				<td></td>
			</tr>
		</table>
	</div>
	
	<div id="normalRangeAskDiv" style="display: none;">
	<input type="button"
			value="<%=StringUtil.getMessageForKey("label.button.editResultLimits")%>"
			onclick="editRangeAsk();" id="editResultLimitsButton" /> 
	</div>
	<div id="normalRangeDiv" style="display: none;">
		<h3>
			<bean:message key="configuration.test.catalog.normal.range" />
		</h3>
		<table style="display: inline-table">
			<tr>
				<th></th>
				<th colspan="8"><bean:message
						key="configuration.test.catalog.normal.range" /></th>
				<th colspan="2"><bean:message
						key="configuration.test.catalog.valid.range" /></th>
				<th></th>
			</tr>
			<tr>
				<td><bean:message key="label.sex.dependent" /></td>
				<td><span class="sexRange" style="display: none"><bean:message
							key="label.sex" /> </span></td>
				<td colspan="4" align="center"><bean:message
						key="label.age.range" /></td>
				<td colspan="2" align="center"><bean:message key="label.range" /></td>
				<td align="center"><bean:message key="label.reporting.range" /></td>
				<td colspan="2"></td>
			</tr>
			<tr class="row_0">
				<td><input type="hidden" class="rowKey" value="0" /><input
					id="genderCheck_0" type="checkbox"
					onchange="genderMatersForRange(this.checked, '0')"></td>
				<td><span class="sexRange_0" style="display: none"> <bean:message
							key="sex.male" />
				</span></td>
				<td><input class="yearMonthSelect_0" type="radio" name="time_0"
					value="<%=StringUtil.getMessageForKey("abbreviation.year.single")%>"
					onchange="upperAgeRangeChanged('0')" checked>
				<bean:message key="abbreviation.year.single" /> <input
					class="yearMonthSelect_0" type="radio" name="time_0"
					value="<%=StringUtil.getMessageForKey("abbreviation.month.single")%>"
					onchange="upperAgeRangeChanged('0')">
				<bean:message key="abbreviation.month.single" />&nbsp;</td>
				<td id="lowerAge_0">0&nbsp;</td>
				<td><input type="text" id="upperAgeSetter_0" value="Infinity"
					size="10" onchange="upperAgeRangeChanged('0')"><span
					id="upperAge_0"></span></td>
				<td><select id="ageRangeSelect_0"
					onchange="ageRangeSelected( this, '0');">
						<option value="0"></option>
						<%
							for (IdValuePair pair : ageRangeList) {
						%>
						<option value='<%=pair.getId()%>'><%=pair.getValue()%>
						</option>
						<%
							}
						%>
				</select></td>
				<td><input type="text" value="-Infinity" size="10"
					id="lowNormal_0" class="lowNormal"
					onchange="normalRangeCheck('0');"></td>
				<td><input type="text" value="Infinity" size="10"
					id="highNormal_0" class="highNormal"
					onchange="normalRangeCheck('0');"></td>
				<td><input type="text" value="" size="12" id="reportingRange_0"></td>
				<td><input type="text" value="-Infinity" size="10"
					id="lowValid" onchange="validRangeCheck();"></td>
				<td><input type="text" value="Infinity" size="10"
					id="highValid" onchange="validRangeCheck();"></td>
			</tr>
			<tr class="sexRange_0 row_0" style="display: none">
				<td></td>
				<td><bean:message key="sex.female" /></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><input type="text" value="-Infinity" size="10"
					id="lowNormal_G_0" class="lowNormal"
					onchange="normalRangeCheck('0');"></td>
				<td><input type="text" value="Infinity" size="10"
					id="highNormal_G_0" class="highNormal"
					onchange="normalRangeCheck('0');"></td>
				<td><input type="text" value="" size="12"
					id="reportingRange_G_0"></td>
				<td></td>
				<td></td>
			</tr>
			<tr id="endRow"></tr>
		</table>
		<label for="significantDigits"><bean:message
				key="label.significant.digits" /></label> <input type="number" min="0"
			max="10" id="significantDigits">
	</div>


	<div class="selectShow"
		style="margin-left: auto; margin-right: auto; width: 40%;">
		<input type="button"
			value="<%=StringUtil.getMessageForKey("label.button.next")%>"
			disabled="disabled" onclick="nextStep();" id="nextButton" /> <input
			type="button"
			value="<%=StringUtil.getMessageForKey("label.button.back")%>"
			onclick="navigateBack()" />
	</div>
	<div class="confirmShow"
		style="margin-left: auto; margin-right: auto; width: 40%; display: none">
		<input type="button"
			value="<%=StringUtil.getMessageForKey("label.button.accept")%>"
			onclick="submitAction('TestModifyUpdate.do');" /> <input
			type="button"
			value="<%=StringUtil.getMessageForKey("label.button.back")%>"
			onclick="navigateBackFromConfirm()" />
	</div>

	<table>
		<%
			while (testCount < testList.size()) {
		%>
		<tr>
			<td><input type="button"
				value='<%=((IdValuePair) testList.get(testCount)).getValue()%>'
				onclick="setForEditing( '<%=((IdValuePair) testList.get(testCount)).getId() + "', '"
						+ ((IdValuePair) testList.get(testCount)).getValue()%>');"
				class="textButton test" /> <%
 	testCount++;
 		columnCount = 1;
 %></td>
			<%
				while (testCount < testList.size() && (columnCount < columns)) {
			%>
			<td><input type="button"
				value='<%=((IdValuePair) testList.get(testCount)).getValue()%>'
				onclick="setForEditing( '<%=((IdValuePair) testList.get(testCount)).getId() + "', '"
							+ ((IdValuePair) testList.get(testCount)).getValue()%>' );"
				class="textButton test" /> <%
 	testCount++;
 			columnCount++;
 %></td>
			<%
				}
			%>

		</tr>
		<%
			}
		%>
	</table>

	<br> <input type="button"
		value='<%=StringUtil.getMessageForKey("label.button.finished")%>'
		onclick="submitAction('TestManagementConfigMenu.do');" />
</form>
