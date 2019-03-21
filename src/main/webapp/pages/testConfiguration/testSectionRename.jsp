<%@ page language="java"
         contentType="text/html; charset=utf-8"
         import="us.mn.state.health.lims.common.action.IActionConstants,
         		us.mn.state.health.lims.common.util.IdValuePair,
         		us.mn.state.health.lims.common.util.*, spring.mine.internationalization.MessageUtil,
         		us.mn.state.health.lims.common.util.Versioning,
         		java.util.List,
         		java.util.ArrayList,
         		us.mn.state.health.lims.common.provider.query.EntityNamesProvider,
         		spring.generated.forms.TestSectionRenameEntryForm" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" uri="/tags/labdev-view" %>
<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>
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

<script type="text/javascript" src="scripts/ajaxCalls.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<c:set var="formName" value="${form.formName}" />
<c:set var="TestSectionList" value="${form.testSectionList}" />


<script type="text/javascript">
    if (!$jq) {
        var $jq = jQuery.noConflict();
    }

    function makeDirty(){
        function formWarning(){
            return "<spring:message code="banner.menu.dataLossWarning"/>";
        }
        window.onbeforeunload = formWarning;
    }

    function submitAction(target) {
        var form = document.getElementById("mainForm");
        form.action = target;
        form.submit();
    }

    function setForEditing(testSectionId, name) {
        $jq("#editDiv").show();
        $jq("#TestSectionName").text(name);
        $jq(".error").each(function (index, value) {
            value.value = "";
            $jq(value).removeClass("error");
            $jq(value).removeClass("confirmation");
        });
        $jq("#testSectionId").val(testSectionId);
        $jq(".TestSection").each(function () {
            var element = $jq(this);
            element.prop("disabled", "disabled");
            element.addClass("disabled-text-button");
        });
        getEntityNames(testSectionId, "<%=EntityNamesProvider.TEST_SECTION%>", TestSectionNameSuccess );
    }

    function TestSectionNameSuccess(xhr) {
        //alert(xhr.responseText);
        var formField = xhr.responseXML.getElementsByTagName("formfield").item(0);
        var message = xhr.responseXML.getElementsByTagName("message").item(0);
        var response;


        if (message.firstChild.nodeValue == "valid") {
            response = JSON.parse(formField.firstChild.nodeValue);
            $jq("#nameEnglish").text(response["name"]["english"]);
            $jq("#nameFrench").text(response["name"]["french"]);
            $jq(".required").each(function () {
                $jq(this).val("");
            });
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
            alert('<%=MessageUtil.getContextualMessage("error.all.required")%>');
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
            $jq("#action").text('<%=MessageUtil.getContextualMessage("label.confirmation")%>');
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
        $jq("#action").text('<%=MessageUtil.getContextualMessage("label.button.edit")%>');
    }

    function cancel() {
        $jq("#editDiv").hide();
        $jq("#testSectionId").val("");
        $jq(".TestSection").each(function () {
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
        var form = document.getElementById("mainForm");
        form.action = "TestSectionRenameEntry.do";
        form.submit();
    }
</script>


<%!
    int itemCount = 0;
    int columnCount = 0;
    int columns = 3;
%>

<%
    columnCount = 0;
	itemCount = 0;
    List TestSectionList;
    TestSectionList =  ((TestSectionRenameEntryForm) request.getAttribute("form")).getTestSectionList();
%>


	<form:form name="${form.formName}" 
				   action="${form.formAction}" 
				   modelAttribute="form" 
				   onSubmit="return submitForm(this);" 
				   method="${form.formMethod}"
				   id="mainForm">

<form:hidden path="testSectionId" id="testSectionId"/>


<input 	type="button"
		class="textButton" 
		value="<%= MessageUtil.getContextualMessage("banner.menu.administration")%>"
		onclick="submitAction('MasterListsPage.do');" >&rarr;

<input  type="button" 
		class="textButton"
		value="<%= MessageUtil.getContextualMessage("configuration.test.management") %>"
       	onclick="submitAction('TestManagementConfigMenu.do');" >&rarr;
        

<%=MessageUtil.getContextualMessage( "configuration.type.rename" ) %>
<br><br>


<div id="editDiv" style="display:none;">
    <h1 id="action"><spring:message code="label.button.edit"/></h1>
    <h2><%=MessageUtil.getContextualMessage( "testsection.browse.title" )%>:<span id="testSectionName"></span></h2>
    <br>
    <table>
        <tr>
            <td></td>
            <th colspan="2" style="text-align: center"><spring:message code="testSection.testSectionName"/></th>
        </tr>
        <tr>
            <td></td>
            <td style="text-align: center"><spring:message code="label.english"/></td>
            <td style="text-align: center"><spring:message code="label.french"/></td>
        </tr>
    
     	<tr>
            <td style="padding-right: 20px"><spring:message code="label.current"/>:</td>
            <td id="nameEnglish" style="padding-left: 10px"></td>
            <td id="nameFrench" style="padding-left: 10px"></td>
        </tr>
        
         <tr>
            <td style="padding-right: 20px"><spring:message code="label.new"/>:</td>
            <td><span class="requiredlabel">*</span>
            	<form:input
            			path="nameEnglish"
                        cssClass="required"
                        size="35"
                        onchange="handleInput(this);"/>
            </td>
            <td><span class="requiredlabel">*</span><form:input
            			path="nameFrench"
            			cssClass="required"
                        size="35"
                        onchange="handleInput(this);"/>
            </td>
        </tr>
     </table>

     <div style="text-align: center" id="editButtons">
        <input type="button" value='<%=MessageUtil.getContextualMessage("label.button.save")%>'
               onclick="confirmValues();"/>
        <input type="button" value='<%=MessageUtil.getContextualMessage("label.button.cancel")%>'
               onclick='cancel()'/>
    </div>

    <div style="text-align: center; display: none;" id="confirmationButtons">
        <input type="button" value='<%=MessageUtil.getContextualMessage("label.button.accept")%>'
               onclick="savePage();"/>
        <input type="button" value='<%=MessageUtil.getContextualMessage("label.button.reject")%>'
               onclick='rejectConfirmation();'/>
    </div>
    <br><br>
   
     </div>
   </form:form>

<table>
    <% while(itemCount < TestSectionList.size()){%>
    <tr>
        <td><input type="button" value='<%= ((IdValuePair)TestSectionList.get(itemCount)).getValue() %>'
                   onclick="setForEditing( '<%= ((IdValuePair)TestSectionList.get(itemCount)).getId() + "', '" + ((IdValuePair)TestSectionList.get(itemCount)).getValue() %>');"
                   class="textButton TestSection"/>
            <%
            	itemCount++;
                columnCount = 1;
            %></td>
        <% while(itemCount < TestSectionList.size() && ( columnCount < columns )){%>
        <td><input type="button" value='<%= ((IdValuePair)TestSectionList.get(itemCount)).getValue() %>'
                   onclick="setForEditing( '<%= ((IdValuePair)TestSectionList.get(itemCount)).getId() + "', '" + ((IdValuePair)TestSectionList.get(itemCount)).getValue() %>' );"
                   class="textButton TestSection"/>
            <%
            	itemCount++;
                columnCount++;
            %></td>
        <% } %>

    </tr>
    <% } %>
</table>

<br>
<input type="button" value='<%= MessageUtil.getContextualMessage("label.button.finished") %>'
       onclick="submitAction('TestManagementConfigMenu.do');"/>
