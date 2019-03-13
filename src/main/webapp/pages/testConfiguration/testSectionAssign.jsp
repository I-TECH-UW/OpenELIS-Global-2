<%@ page language="java"
         contentType="text/html; charset=utf-8"
         import="java.util.List,
         		java.util.LinkedHashMap,
         		java.util.Map,
         		us.mn.state.health.lims.common.action.IActionConstants,
         		us.mn.state.health.lims.common.util.IdValuePair,
         		us.mn.state.health.lims.common.util.StringUtil,
         		us.mn.state.health.lims.common.util.Versioning" %>

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
<script type="text/javascript" src="scripts/jquery-ui.js?ver=<%= Versioning.getBuildNumber() %>"></script>

 <c:set var="testSectionList" value="${form.testSectionList}" />
 <c:set var="sectionTestList" value="${form.sectionTestList}" />

<%!
    String basePath = "";
    int testCount = 0;
    int columnCount = 0;
    int columns = 3;
    int columnSize = (int) (100 / columns);
%>

<%
    basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
    columnCount = 0;
    testCount = 0;
%>

<link rel="stylesheet" media="screen" type="text/css"
      href="<%=basePath%>css/jquery_ui/jquery.ui.theme.css?ver=<%= Versioning.getBuildNumber() %>"/>

<script type="text/javascript">
    if (!$jq) {
        var $jq = jQuery.noConflict();
    }

    $jq(document).ready( function(){
        $jq(".sortable").sortable({
            stop: function( ) {makeDirty();}
        });
    });

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

    function testSelected(input, id, currentTestSection, onlyOneTestInSection, testSectionId) {
        makeDirty();
        $jq(".test").each(function () {
            $jq(this).attr("disabled", "disabled");
            $jq(this).addClass("disabled-text-button");
        });

        $jq(".selectedTestName").text(input.value);
        $jq("#action").text('<spring:message code="label.button.edit"/>');
        $jq("#fromTestSection").text(currentTestSection);
        $jq("#testId").val(id);
        $jq("#deactivateTestSectionId").val(onlyOneTestInSection ? testSectionId : "");
        $jq("#warnDeactivteTestSection").text(currentTestSection);
        $jq(".edit-step").show();
        $jq(".select-step").hide();
        $jq(window).scrollTop(0);

    }

    function testSectionSelected( selection){
        var optionId = $jq(selection).val();
        $jq("#saveButton").attr("disabled", (0 == optionId));
        $jq("#toTestSection").text($jq("#option_" + optionId).text());
        $jq("#testSectionId").val(optionId);
    }

    function confirmValues() {
        $jq("#editButtons").hide();
        $jq(".confirmation-step").show();
        $jq("#action").text('<%=StringUtil.getContextualMessageForKey("label.confirmation")%>');
        if( $jq("#deactivateTestSectionId").val().length > 0){
            $jq("#deatcitvateWarning").show();
        }else{
            $jq("#deatcitvateWarning").hide();
        }

        $jq("#testSectionSelection").attr("disabled", true);

    }

    function rejectConfirmation() {
        $jq("#editButtons").show();
        $jq(".confirmation-step").hide();
        $jq("#action").text('<%=StringUtil.getContextualMessageForKey("label.button.edit")%>');

        $jq("#testSectionSelection").attr("disabled", false);
    }


    function savePage() {
        window.onbeforeunload = null; // Added to flag that formWarning alert isn't needed.
        var form = document.getElementById("mainForm");
        form.action = "TestSectionTestAssign.do";
        form.submit();
    }
</script>

<style>
table{
  width: 80%;
}
td {
  width: 25%;
}
</style>

<form:form name="${form.formName}" 
				   action="${form.formAction}" 
				   modelAttribute="form" 
				   onSubmit="return submitForm(this);" 
				   method="${form.formMethod}"
				   id="mainForm">

    <form:hidden path="testId" id="testId"/>
    <form:hidden path="testSectionId" id="testSectionId"/>
    <form:hidden path="deactivateTestSectionId" id="deactivateTestSectionId"/>

    <input type="button" value='<%= StringUtil.getContextualMessageForKey("banner.menu.administration") %>'
           onclick="submitAction('MasterListsPage.do');"
           class="textButton"/>&rarr;
    <input type="button" value='<%= StringUtil.getContextualMessageForKey("configuration.test.management") %>'
           onclick="submitAction('TestManagementConfigMenu.do');"
           class="textButton"/>&rarr;
    <input type="button" value='<%= StringUtil.getContextualMessageForKey("configuration.testUnit.manage") %>'
           onclick="submitAction('TestSectionManagement.do');"
           class="textButton"/>&rarr;

<%=StringUtil.getContextualMessageForKey( "configuration.testUnit.assign" )%>

<%    List testSectionList = (List) pageContext.getAttribute("testSectionList"); %>

<br><br>

    <h1 id="action" ><spring:message code="label.form.select"/></h1>
    <h1 id="action" class="edit-step" style="display: none"></h1>
    <h2><spring:message code="configuration.testUnit.assign"/> </h2>

    <div class="select-step" >
        <spring:message code="configuration.testUnit.assign.explain" />
    </div>
    <div class="edit-step" style="display:none">

        Test: <span class="selectedTestName" ></span><br><br>
        &nbsp;&nbsp;<spring:message code="configuration.testUnit.assign.new.unit" />:&nbsp;
    <select id="testSectionSelection" onchange="testSectionSelected(this);">

        <% for(int i = 0; i < testSectionList.size(); i++){
            IdValuePair testSection = (IdValuePair)testSectionList.get(i);
        %>
        <option id='<%="option_" + testSection.getId()%>' value="<%=testSection.getId()%>"><%=testSection.getValue()%></option>
        <% } %>
    </select>

    <div class="confirmation-step" style="display:none">
        <br><span class="selectedTestName" ></span>&nbsp;<spring:message code="configuration.testUnit.confirmation.move.phrase" />&nbsp;<span id="fromTestSection" ></span> <spring:message code="word.to" /> <span id="toTestSection" ></span>.
        <div id="deatcitvateWarning" >
            <br/><span id="warnDeactivteTestSection"></span>&nbsp;<spring:message code="configuration.testUnit.assign.deactivate" />
        </div>
    </div>

    <div style="text-align: center" id="editButtons">
        <input id="saveButton" type="button" value='<%=StringUtil.getContextualMessageForKey("label.button.save")%>'
               onclick="confirmValues();" disabled="disabled"/>
        <input type="button" value='<%=StringUtil.getContextualMessageForKey("label.button.cancel")%>'
               onclick='window.onbeforeunload = null; submitAction("TestSectionTestAssign.do")'/>
    </div>
    <div style="text-align: center; display: none;" class="confirmation-step">
        <input type="button" value='<%=StringUtil.getContextualMessageForKey("label.button.accept")%>'
               onclick="savePage();"/>
        <input type="button" value='<%=StringUtil.getContextualMessageForKey("label.button.reject")%>'
               onclick='rejectConfirmation();'/>
    </div>
</div>
    
	<%
	//<bean:define id="testMap" name='${form.formName}' property="sectionTestList" type="java.util.LinkedHashMap<IdValuePair, java.util.List<IdValuePair>>"/>
                	
			LinkedHashMap<IdValuePair, List<IdValuePair>> testMap = new LinkedHashMap<IdValuePair, List<IdValuePair>>();
			testMap = (LinkedHashMap<IdValuePair, List<IdValuePair>>) pageContext.getAttribute("sectionTestList");
      %>


<% for( IdValuePair pair : testMap.keySet()){
 List<IdValuePair> testList = testMap.get(pair);
 %>
    <div>
        <h4><%=pair.getValue()%></h4>
        <% testCount = 0;%>
        <table width="95%" style="position:relative;left:5%">
            <% while (testCount < testList.size()) {%>
            <tr>
                <%
                    columnCount = 0;
                %>
                <% while (testCount < testList.size() && (columnCount < columns)) {%>
                <td width='<%=columnSize + "%"%>'>
                    <input type="button"
                           class="textButton test"
                           value='<%=testList.get(testCount).getValue()%>'
                           onclick="testSelected(this, '<%=testList.get(testCount).getId() %>', '<%=pair.getValue()%>', <%= testList.size() == 1 %>, '<%=pair.getId()%>')"
                           checked>
                    <%
                        testCount++;
                        columnCount++;
                    %></td>
                <% } %>
                <% while (columnCount < columns) {
                    columnCount++; %>
                <td width='<%=columnSize + "%"%>'></td>
                <% } %>
            </tr>
            <% } %>
        </table>

    </div>
<%}%>
</form:form>

