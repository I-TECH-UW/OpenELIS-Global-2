<%@ page language="java"
         contentType="text/html; charset=utf-8"
         import="java.util.List,
         		us.mn.state.health.lims.common.action.IActionConstants,
         		us.mn.state.health.lims.common.util.IdValuePair,
         		us.mn.state.health.lims.common.util.StringUtil,
         		us.mn.state.health.lims.test.beanItems.TestActivationBean,
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
 
 <c:set var="jsonChangeList" value="${form.jsonChangeList}" />
 <c:set var="activeTestList" value="${form.activeTestList}" />
 <c:set var="inactiveTestList" value="${form.inactiveTestList}" />

<script type="text/javascript" src="scripts/jquery-ui.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<link rel="stylesheet" media="screen" type="text/css"
      href="<%=basePath%>css/jquery_ui/jquery.ui.theme.css?ver=<%= Versioning.getBuildNumber() %>"/>

<script type="text/javascript">
    var backFunction = selectBack;

    if (!$jq) {
        var $jq = jQuery.noConflict();
    }

    $jq(document).ready( function() {
        configureForSelect();
    });

    function makeDirty() {
        function formWarning() {
            return "<spring:message code="banner.menu.dataLossWarning"/>";
        }

        window.onbeforeunload = formWarning;
    }

    function makeClean() {
        window.onbeforeunload = null;
    }

    function submitAction(target) {
        var form = window.document.forms[0];
        form.action = target;
        form.submit();
    }
    function checkedChanged(isActive, element) {
        var jqueriedElement = $jq(element);
        var activate, deactivate;

        //   alert( element.checked + ":"+ isActive + " -> " + (element.checked == isActive));

        if ((element.checked == isActive)) {
            jqueriedElement.parent().find("span").removeClass("altered");
            jqueriedElement.removeClass("activationChanged");
        } else {
            jqueriedElement.parent().find("span").addClass("altered");
            jqueriedElement.addClass("activationChanged");
        }

        deactivate = $jq(".active.activationChanged").length;
        activate = $jq(".inactive.activationChanged").length;

        $jq("#nextButtonSelect").attr('disabled', ( deactivate == 0 && activate == 0));

        if(  deactivate == 0 && activate == 0){
            makeClean();
        }else{
            makeDirty();
        }
    }

    function nextStepFromSelect() {
        var deactivate = $jq(".active.activationChanged").length;
        var activate = $jq(".inactive.activationChanged").length;

        $jq("#activateSection input").prop("disabled", true);
        //next button is disabled if activate and deactivate = 0
        if (activate == 0) {
            configureForConfirmation();
            confirmationStep(activate, deactivate);
        } else {
            sortStep();
        }

        window.scrollTo(0,0);
    }

    function nextStepToConfirmation(){
        var deactivate = $jq(".active.activationChanged").length;
        var activate = $jq(".inactive.activationChanged").length;
        configureForConfirmation();
        confirmationStep(activate, deactivate);

        window.scrollTo(0,0);
    }

    function confirmationStep(activateNumber, deactivateNumber) {
        var jsonObj = {};
        jsonObj.activateSample = [];
        jsonObj.deactivateSample = [];
        jsonObj.activateTest = [];
        jsonObj.deactivateTest = [];

        if (activateNumber > 0) {
            listActivatedItems(jsonObj);
        }

        if (deactivateNumber > 0) {
            listDeactivatedItems(jsonObj);
        }

        $jq("#jsonChangeList").val(JSON.stringify(jsonObj));
    }

    function listActivatedItems( jsonObj){
        var activateSampleType = true;
        var activateTestList = $jq("#testActivateList");
        var activateSampleList = $jq("#sampleTypeActivateList");
        var jsonBlob, sortOrder;

        $jq("#testActivate").show();
        $jq("#sortOrderList ul.sortable").each(function(){
            activateTestList.append("<br />");
            activateTestList.append( $jq(this).val());
            activateTestList.append("<br />");
            sortOrder = 0;
            $jq(this).find("li").each(function(){
                jsonBlob = {};
                jsonBlob.id = $jq(this).val();
                jsonBlob.activated = $jq(this).hasClass("altered");
                if( jsonBlob.activated){
                    activateTestList.append("&nbsp;&nbsp;&nbsp;&nbsp;" + $jq(this).text() );
                    activateTestList.append("<br />");
                }
                jsonBlob.sortOrder = sortOrder++;
                jsonObj.activateTest[jsonObj.activateTest.length] = jsonBlob;
            });

        });

        $jq("#sampleTypeSortOrder ul.sortable").each(function(){
            sortOrder = 0;
            $jq(this).find("li").each(function(){
              //  if( activateSampleType){
                console.log("activating sample type");
                    $jq("#sampleTypeActivate").show();
                    activateSampleType = false;
              //  }
                jsonBlob = {};
                jsonBlob.id = $jq(this).val();
                jsonBlob.activated = $jq(this).hasClass("altered");
                if( jsonBlob.activated){
                    activateSampleList.append( $jq(this).text() );
                    activateSampleList.append("<br />");
                }
                jsonBlob.sortOrder = sortOrder++;
                jsonObj.activateSample[jsonObj.activateSample.length] = jsonBlob;
            });

        });
    }

    function listDeactivatedItems(jsonObj) {
        var sampleType, deactivatedTests, jsonBlob;
        var deactivateTestList = $jq("#testDeactivateList");
        var deactivateSampleList = $jq("#sampleTypeDeactivateList");


        $jq("#testDeactivate").show();
        $jq(".activeSampleType").each(function () {
            sampleType = $jq(this);
            deactivatedTests = sampleType.parent().find(".active.activationChanged");

            if (deactivatedTests.length > 0) {
                deactivateTestList.append("<br />");
                deactivateTestList.append(sampleType.text());

                deactivateTestList.append("<br />");
                deactivatedTests.each(function () {
                    jsonBlob = {};
                    jsonBlob.id = $jq(this).val();
                    jsonObj.deactivateTest[jsonObj.deactivateTest.length] = jsonBlob;
                    deactivateTestList.append("&nbsp;&nbsp;&nbsp;&nbsp;" + $jq(this).siblings("span").text());
                    deactivateTestList.append("<br />");
                });

                if (deactivatedTests.length == sampleType.parent().find(".active").length) {
                    jsonBlob = {};
                    jsonBlob.id = sampleType.find("input").val();
                    jsonObj.deactivateSample[jsonObj.deactivateSample.length] = jsonBlob;
                    $jq("#sampleTypeDeactivate").show();
                    deactivateSampleList.append(sampleType.text());
                    deactivateSampleList.append("<br/>");
                }
            }

        });
    }

    function sortStep() {
        configureForSort();
        createSortingUI();
        $jq(".sortable").sortable();
        $jq(".sortable").disableSelection();
    }

    function createSortingUI() {
        var sortColumnCount = 0;
        var sortDiv = $jq("#sortOrderList");
        var table = $jq(document.createElement("table"));
        var headerRow = $jq(document.createElement("tr"));
        var bodyRow = $jq(document.createElement("tr"));
        var addActiveSampleTypesToSort = true;
        var sampleType, activatedTests;
        var headerCell, bodyCell;

        table.append(headerRow);
        table.append(bodyRow);
        table.attr("width", "100%");

        $jq(".activeSampleType,.inactiveSampleType").each(function () {
            sampleType = $jq(this);
            activatedTests = sampleType.parent().find(".inactive.activationChanged");
            if (activatedTests.length > 0) {
                if (sortColumnCount >= <%=columns %>) {
                    sortDiv.append(table);
                    table = $jq(document.createElement("table"));
                    headerRow = $jq(document.createElement("tr"));
                    bodyRow = $jq(document.createElement("tr"));
                    table.append(headerRow);
                    table.append(bodyRow);
                    table.attr("width", "100%");
                    sortColumnCount = 0;
                }


                headerCell = $jq(document.createElement("th"));
                headerCell.attr("width", '25%');
                headerCell.text(sampleType.text());
                headerRow.append(headerCell);

                bodyCell = $jq(document.createElement("td"));
                insertSortableTestList(bodyCell, sampleType, activatedTests);
                bodyRow.append(bodyCell);
                sortColumnCount++;
            }
        });

        if (sortColumnCount < <%=columns %>) {
            while (sortColumnCount < <%=columns %>) {
                headerCell = $jq(document.createElement("td"));
                headerRow.append(headerCell);
                sortColumnCount++;
            }
        }
        sortDiv.append(table);

        $jq(".inactiveSampleType").each(function () {
            sampleType = $jq(this);
            activatedTests = sampleType.parent().find(".inactive.activationChanged");
            if (activatedTests.length > 0) {
                var UL = $jq("#sampleTypeSortList");
                if( addActiveSampleTypesToSort){
                    $jq("#sampleTypeSortOrder").show();
                    addSampleTypeListElements($jq(".activeSampleType"), UL, false);
                    addActiveSampleTypesToSort = false;
                }

                addSampleTypeListElement(sampleType, UL, true);
            }
        });

    }

    function insertSortableTestList(cell, sampleType, activatedTests) {
        var UL = $jq(document.createElement("ul"));
        UL.val(sampleType.text());
        var tests = sampleType.parent().find(".sortable-test");
        UL.addClass("sortable");
        cell.append(UL);
        addTestListElements(tests, UL, false);
        addTestListElements(activatedTests, UL, true);

    }

    function addTestListElements(tests, UL, activated) {
        var LI, test;
        tests.each(function () {
            test = $jq(this);
            //make sure it test has not been deactivated
            if (activated || !test.hasClass("activationChanged")) {
                LI = createLI($jq(this).parent().find("span").text(), $jq(this).val(), activated);
                UL.append(LI);
            }
        });
    }

    function addSampleTypeListElements(sampleTypes, UL, highlight) {

        sampleTypes.each(function () {
            addSampleTypeListElement($jq(this), UL, highlight);
        });
    }

    function addSampleTypeListElement( sampleType, UL, highlight){
        UL.append(createLI(sampleType.text(), sampleType.find("input").val(), highlight ));
    }

    function createLI( name, value, highlight){
        var LI = $jq(document.createElement("li"));
        var span;

        LI.val(value);
        LI.addClass("ui-state-default_oe");
        if (highlight) {
            LI.addClass("altered");
        }

        span = $jq(document.createElement("span"));
        span.addClass("ui-icon ui-icon-arrowthick-2-n-s");
        LI.append(span);
        LI.append(name);

        return LI;
    }
    function navigateBack() {
        backFunction();
        window.scrollTo(0,0);
    }

    function selectBack(){
        submitAction('TestManagementConfigMenu.do');
    }

    function sortBack(){
        configureForSelect();
    }

    function confirmBack(){

       if($jq("#testActivationSortButton").is(":visible")){
           configureForSort();
       } else{
           configureForSelect();
       }
    }
    function configureForSelect() {
        $jq("#step").text("<%=StringUtil.getContextualMessageForKey("label.testActivate")%>");
        $jq("#instructions").text("<spring:message code="instructions.test.activation" htmlEscape="false"/>");
        $jq("#activateSection input").prop("disabled", false);
        $jq(".selectHide").hide();
        $jq(".selectShow").show();
        $jq(".selectClear").empty();
        backFunction = selectBack;
    }

    function configureForSort() {
        $jq("#step").text("Sort");
        $jq("#instructions").text("<%=StringUtil.getContextualMessageForKey("instructions.test.activation.sort")%>");
        $jq(".sortHide").hide();
        $jq(".sortShow").show();

        //The reason for the li is that the sample sortable UL is hardcoded as sortable, even if it has no contents
        if( $jq(".sortable li").length > 0) {
            $jq(".sortable").sortable("enable");
        }

        backFunction = sortBack;
    }

    function configureForConfirmation() {
        $jq("#step").text("<%=StringUtil.getContextualMessageForKey("label.testActivate.confirm")%>");
        $jq("#instructions").text("<%=StringUtil.getContextualMessageForKey("instructions.test.activation.confirm")%>");
        $jq(".confirmHide").hide();
        $jq(".confirmShow").show();

        //The reason for the li is that the sample sortable UL is hardcoded as sortable, even if it has no contents
        if( $jq(".sortable li").length > 0) {
            $jq(".sortable").sortable("disable");
        }

        if($jq("#testActivationSort").is(":visible")){
            $jq("#testActivationSortButton").show();
            $jq("#testActivationSort").hide();
        }

        backFunction = confirmBack;
    }
    function savePage() {
        window.onbeforeunload = null; // Added to flag that formWarning alert isn't needed.
        var form = document.getElementById("mainForm");
        form.method = "POST";
        form.action = "TestActivation.do";
        form.submit();
    }
</script>
 
<style>
table{
  width: 100%;
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
				   
<input type="button" value='<%= StringUtil.getContextualMessageForKey("banner.menu.administration") %>'
       onclick="submitAction('MasterListsPage.do');"
       class="textButton"/> &rarr;
<input type="button" value='<%= StringUtil.getContextualMessageForKey("configuration.test.management") %>'
       onclick="submitAction('TestManagementConfigMenu.do');"
       class="textButton"/>&rarr;
<span id="testActivationSelectionButton" class="selectHide sortShow confirmShow" style="display:none" >
<input type="button" value='<%=StringUtil.getContextualMessageForKey("label.testActivate")%>'
       onclick="configureForSelect();"
       class="textButton"/>&rarr;
</span>

<span id="testActivationSortButton" class="selectHide sortHide" style="display:none" >
<input type="button" value='<%= StringUtil.getContextualMessageForKey("label.button.sort") %>'
       onclick="configureForSort();"
       class="textButton"/>&rarr;
</span>

<span id="testActivationSort" class="selectHide sortShow" style="display:none" ><%= StringUtil.getContextualMessageForKey("label.button.sort") %></span>
<span id="testActivationConfirmation" class="selectHide sortHide confirmShow" style="display:none" ><%=StringUtil.getContextualMessageForKey("label.confirmation")%></span>
<span id="testActivationSelection" class="selectShow sortHide confirmHide"><%=StringUtil.getContextualMessageForKey("label.testActivate")%></span>

<h1 id="step"><spring:message code="label.testActivate"/></h1><br/>

<div class="indent"><span id="instructions"><spring:message code="instructions.test.activation" htmlEscape="false" /></span></div>

<br>

<div id="testActivate" class="selectHide sortHide" >
    <h4><%=StringUtil.getContextualMessageForKey("label.test.activate")%></h4>

    <div id="testActivateList" class="selectClear"></div>
    <br>
</div>
<div id="sampleTypeActivate" class="selectHide  sortHide" >
    <h4><%=StringUtil.getContextualMessageForKey("label.sample.types.activate")%></h4>
    <div id="sampleTypeActivateList" class="selectClear"></div>
    <br>
</div>
<div id="testDeactivate" class="selectHide sortHide">
    <h4><%=StringUtil.getContextualMessageForKey("label.test.deactivate")%></h4>

    <div id="testDeactivateList" class="selectClear"></div>
    <br>
</div>
<div id="sampleTypeDeactivate" class="selectHide sortHide" >
    <h4><%=StringUtil.getContextualMessageForKey("label.sample.types.deactivate")%></h4>

    <div id="sampleTypeDeactivateList" class="selectClear"></div>
    <br>
</div>


<div id="sortOrder" class="selectHide sortShow" style="display:none">
    <h4><%=StringUtil.getContextualMessageForKey("label.test.display.order")%></h4>
    <div id="sortOrderList" class="selectClear" ></div>
</div>
<div id="sampleTypeSortOrder" class="selectHide" style="display: none">
    <hr/>
    <h4><%=StringUtil.getContextualMessageForKey("label.sample.type.display.order")%></h4>
    <table>
        <tr><th><%=StringUtil.getContextualMessageForKey("label.sample.types")%></th></tr>
        <tr><td>
            <ul class="sortable ui-sortable selectClear" id="sampleTypeSortList"></ul>
        </td></tr>
    </table>
</div>

<div class="selectHide sortShow confirmShow" style="margin-left:auto; margin-right:auto;width: 40%;">
    <input type="button"
           class="sortShow confirmHide"
           value='<%= StringUtil.getContextualMessageForKey("label.button.next")%>'
           onclick="nextStepToConfirmation();"
           id="nextButtonSort"/>
    <input type="button"
           class="sortHide confirmShow"
           value='<%=StringUtil.getContextualMessageForKey("label.button.accept")%>'
           onclick="savePage();"
           id="acceptButton"
           style="display: none"/>

    <input type="button" value='<%=StringUtil.getContextualMessageForKey("label.button.previous")%>' onclick="navigateBack()" />
</div>

<hr/>

<%!
    String basePath = "";
    int testCount = 0;
    int columnCount = 0;
    int columns = 4;
%>

<%
    basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
    columnCount = 0;
    testCount = 0;
%>

<form:hidden path="jsonChangeList" id="jsonChangeList"/>

<div id="activateSection" class="indent">

    <c:forEach var="activeBean" varStatus="iter" items="${form.activeTestList}">
        <div>
            <span class="activeSampleType"><c:out value="${activeBean.sampleType.value}"/>
            </span>
            <div class="indent">
                <% 
                	TestActivationBean bean = (TestActivationBean) pageContext.getAttribute("activeBean");
                	List<IdValuePair> testList = bean.getActiveTests();
                	testCount = 0; 
                %>
                <table>
                    <% while (testCount < testList.size()) { %>
                    <tr>
                        <% columnCount = 0; %>
                        <% while (testCount < testList.size() && (columnCount < columns)) { %>
                        <td>
                            <input type="checkbox"
                                   class="active sortable-test"
                                   value='<%=testList.get(testCount).getId()%>'
                                   onchange="checkedChanged(true,this)"
                                   checked>
                            <span><%= testList.get(testCount).getValue()%></span>
                            <%
                                testCount++;
                                columnCount++;
                            %></td>
                        <% } %>
                        <% while (columnCount < columns) {
                            columnCount++; %>
                        <td></td>
                        <% } %>
                    </tr>
                <% } %>    
                </table>
                <% 
            		testList = bean.getInactiveTests();
                	testCount = 0; 
                %>
                <table>
                    <% while (testCount < testList.size()) {%>
                    <tr>
                        <% columnCount = 0;
                           while (testCount < testList.size() && (columnCount < columns)) {%>
                        <td>
                            <input type="checkbox"
                                   class="inactive"
                                   value='<%=testList.get(testCount).getId()%>'
                                   onchange="checkedChanged(false,this)">
                            <span><%= testList.get(testCount).getValue()%></span>
                            <%
                                testCount++;
                                columnCount++;
                            %></td>
                        <% } %>
                        <% while (columnCount < columns) {
                            columnCount++; %>
                        <td></td>
                        <% } %>
                    </tr>
                    <% } %>
                </table>
            </div>
        </div>
    </c:forEach>
    
    <c:if test="${!empty form.inactiveTestList}">
        <br/><br/>
        <div style="text-align: center"><%= StringUtil.getContextualMessageForKey("label.testActivate.inactiveSampleTypes") %></div>
        <hr/>
        
        <c:forEach var="inactiveBean" varStatus="iter" items="${form.inactiveTestList}">
            <div>
            <span class="inactiveSampleType"><c:out value="${inactiveBean.sampleType.value}"/>
            </span>
            <div class="indent">
                <% 
                	TestActivationBean bean = (TestActivationBean) pageContext.getAttribute("inactiveBean");
                	List<IdValuePair> testList = bean.getInactiveTests();
                	testCount = 0; 
                %>
                <table>
                    <% while (testCount < testList.size()) {%>
                    <tr>
                        <%
                            columnCount = 0;
                            while (testCount < testList.size() && (columnCount < columns)) {%>
                        <td>
                            <input type="checkbox"
                                   class="inactive"
                                   value='<%=testList.get(testCount).getId()%>'
                                   onchange="checkedChanged(false,this);">
                            <span><%= testList.get(testCount).getValue()%></span>
                            <%
                                testCount++;
                                columnCount++;
                            %></td>
                        <% } %>
                    </tr>
                    <% } %>
                </table>
            </div>
     	  </div>
 		</c:forEach>   
  	</c:if>
  	
</div>

<div class="selectShow sortHide confirmHide" style="margin-left:auto; margin-right:auto;width: 40%;">
    <input type="button"
           value='<%= StringUtil.getContextualMessageForKey("label.button.save") %>'
           disabled="disabled"
           onclick="nextStepFromSelect();"
           id="nextButtonSelect"/>

    <input type="button" value='<%=StringUtil.getContextualMessageForKey("label.button.cancel")%>' onclick="navigateBack()" />


</div>
</form:form>
