<%@ page language="java"
         contentType="text/html; charset=utf-8"
         import="us.mn.state.health.lims.common.action.IActionConstants,
		         us.mn.state.health.lims.common.util.IdValuePair,
		         us.mn.state.health.lims.common.util.StringUtil,
		         us.mn.state.health.lims.common.util.Versioning,
		         java.util.List,
		         us.mn.state.health.lims.panel.valueholder.Panel,
		         us.mn.state.health.lims.testconfiguration.action.PanelTests,
		         us.mn.state.health.lims.test.valueholder.Test" %>

<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/labdev-view" prefix="app" %>
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


<bean:define id="formName" value='<%= (String)request.getAttribute(IActionConstants.FORM_NAME) %>'/>
<bean:define id="panelList" name='<%=formName%>' property="panelList" type="java.util.List"/>


<%!
    String basePath = "";
    int testCount = 0;
    int columnCount = 0;
    int columns = 3;
    int columnSize = (int) (100 / columns);
    Boolean success = false;
%>

<%
    basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
    columnCount = 0;
    testCount = 0;
    success = (Boolean)request.getAttribute("success");
    

%>

<link rel="stylesheet" media="screen" type="text/css"
      href="<%=basePath%>css/jquery_ui/jquery.ui.theme.css?ver=<%= Versioning.getBuildNumber() %>"/>

<form>
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
            return "<bean:message key="banner.menu.dataLossWarning"/>";
        }
        window.onbeforeunload = formWarning;
    }

    function submitAction(target) {
        var form = window.document.forms[0];
        form.action = target;
        form.submit();
    }

    function testSelected(input, id, currentPanel, onlyOneTestInPanel, panelId) {
        makeDirty();
        $jq(".test").each(function () {
            $jq(this).attr("disabled", "disabled");
            $jq(this).addClass("disabled-text-button");
        });

        $jq(".selectedTestName").text(input.value);
        $jq("#action").text('<bean:message key="label.button.edit"/>');
        $jq("#fromPanel").text(currentPanel);
        $jq("#testId").val(id);
        $jq("#deactivatePanelId").val(onlyOneTestInPanel ? panelId : "");
        $jq("#warnDeactivtePanel").text(currentPanel);
        $jq(".edit-step").show();
        $jq(".select-step").hide();
        $jq(window).scrollTop(0);

    }

    function panelSelected( selection){
   	    window.location.href = "PanelTestAssign.do?panelId=" + selection.value ;

    }

    function confirmValues() {
        $jq("#editButtons").hide();
        $jq(".confirmation-step").show();
        $jq("#action").text('<%=StringUtil.getMessageForKey("label.confirmation")%>');
        if( $jq("#deactivatePanelId").val().length > 0){
            $jq("#deatcitvateWarning").show();
        }else{
            $jq("#deatcitvateWarning").hide();
        }

        $jq("#panelSelection").attr("disabled", true);

    }

    function rejectConfirmation() {
        $jq("#editButtons").show();
        $jq(".confirmation-step").hide();
        $jq("#action").text('<%=StringUtil.getMessageForKey("label.button.edit")%>');

        $jq("#panelSelection").attr("disabled", false);
    }


    function savePage() {
        //window.onbeforeunload = null; // Added to flag that formWarning alert isn't needed.
        $jq('#list1 option').prop('selected', true);
        var form = window.document.forms[0];
        form.action = "PanelTestAssignUpdate.do";
        form.submit();
    }
    
    $jq(function(){
        $jq("#button1").click(function(){
            $jq("#list1 > option:selected").each(function(){
                $jq(this).remove().appendTo("#list2");
                $jq('#list2 option').prop('selected', false);
            });
        });
        
        $jq("#button2").click(function(){
            $jq("#list2 > option:selected").each(function(){
                $jq(this).remove().appendTo("#list1");
                $jq('#list1 option').prop('selected', false);
            });
        });
    });
    

</script>

<div id="successMsg" style="text-align:center; color:seagreen;  width : 100%;font-size:170%; visibility : <%=((success != null && success) ? "visible" : "hidden") %>" >
                <bean:message key="save.success"/>
</div>

    <bean:define id="selectedPanel" name='<%=formName%>' property="selectedPanel" type="us.mn.state.health.lims.testconfiguration.action.PanelTests"/>
    
    <html:hidden name="<%=formName%>" property="panelId" styleId="panelId" value="<%=(selectedPanel.getPanelIdValuePair() != null ? selectedPanel.getPanelIdValuePair().getId() : new String()) %>"/>
    <html:hidden name="<%=formName%>" property="deactivatePanelId" styleId="deactivatePanelId"/>

    <input type="button" value='<%= StringUtil.getMessageForKey("banner.menu.administration") %>'
           onclick="submitAction('MasterListsPage.do');"
           class="textButton"/>&rarr;
    <input type="button" value='<%= StringUtil.getMessageForKey("configuration.test.management") %>'
           onclick="submitAction('TestManagementConfigMenu.do');"
           class="textButton"/>&rarr;
    <input type="button" value='<%= StringUtil.getMessageForKey("configuration.panel.manage") %>'
           onclick="submitAction('PanelManagement.do');"
           class="textButton"/>&rarr;

<%=StringUtil.getMessageForKey( "configuration.panel.assign" )%>
<br><br>

    <h1 id="action" ><bean:message key="label.form.select"/></h1>
    <h1 id="action" class="edit-step" style="display: none"></h1>
    <h2><bean:message key="configuration.panel.assign"/> </h2>

    <div class="select-step" >
    Panel:<html:select name='<%= formName %>' property="panelId" 
         onchange="panelSelected(this);" >
        <app:optionsCollection name="<%=formName%>" property="panelList" label="value" value="id" />
    </html:select>
<br>

    
                
    </div>
    <div class="edit-step" style="display:none">

        Test: <span class="selectedTestName" ></span><br><br>
        &nbsp;&nbsp;<bean:message key="configuration.panel.assign.new.type" />:&nbsp;

    <div class="confirmation-step" style="display:none">
        <br><span class="selectedTestName" ></span>&nbsp;<bean:message key="configuration.testUnit.confirmation.move.phrase" />&nbsp;<span id="fromPanel" ></span> <bean:message key="word.to" /> <span id="toPanel" ></span>.
        <div id="deatcitvateWarning" >
            <br/><span id="warnDeactivtePanel"></span>&nbsp;<bean:message key="configuration.panel.assign.deactivate" />
        </div>
    </div>

    <div style="text-align: center" id="editButtons">
        <input id="saveButton" type="button" value='<%=StringUtil.getMessageForKey("label.button.next")%>'
               onclick="confirmValues();" disabled="disabled"/>configuration.testUnit.confirmation.move.phrase
        <input type="button" value='<%=StringUtil.getMessageForKey("label.button.previous")%>'
               onclick='window.onbeforeunload = null; submitAction("PanelTestAssign.do")'/>
    </div>
    <div style="text-align: center; display: none;" class="confirmation-step">
        <input type="button" value='<%=StringUtil.getMessageForKey("label.button.accept")%>'
               onclick="savePage();"/>
        <input type="button" value='<%=StringUtil.getMessageForKey("label.button.reject")%>'
               onclick='rejectConfirmation();'/>
    </div>
</div>


<% if (selectedPanel.getPanelIdValuePair()!= null )  {%>

<table style="width:80%">
<tr>
<td >
<div>
    <h3><%=selectedPanel.getPanelIdValuePair().getValue() %> Tests</h3>

    <select name='currentTests' style="height:200px;line-height:200px;width:100%;" id="list1" multiple="multiple" property="currentTests">
        <% for (IdValuePair panelTest : selectedPanel.getTests()) {%>
            <%="<option value='" + panelTest.getId() + "'>" + panelTest.getValue() + "</option>" %>
        <%} %>
    </select>
    
    
</div>
</td>
<td align="center" valign="middle">
<input id="button2" type="button" value="&lt" /><br>
<input id="button1" type="button" value="&gt" />
</td>
<td>
<div>
     <h3>Available Tests (<%=selectedPanel.getSampleTypeIdValuePair().getValue() %>)</h3>
    <select name='availableTests' style="height:200px;line-height:200px;width:100%;" id="list2" multiple="multiple" style="width:100%;" property="availableTests">
        <% for (IdValuePair availableTest : selectedPanel.getAvailableTests()) {%>
            <%="<option value='" + availableTest.getId() + "'>" + availableTest.getValue() + "</option>" %>
        <%} %>     
    </select>


</div>
</td>
</tr>
<tr>
<td colspan="3" align="right">

        <input type="button" value='Save'
           onclick="savePage();"
           />
</td>
</tr>

</table>
<%} %>
</form>
