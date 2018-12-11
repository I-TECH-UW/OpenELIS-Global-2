<%@ page language="java"
         contentType="text/html; charset=utf-8"
         import="us.mn.state.health.lims.common.action.IActionConstants,
		         us.mn.state.health.lims.common.util.IdValuePair,
		         us.mn.state.health.lims.common.util.StringUtil,
		         us.mn.state.health.lims.common.util.Versioning,
		         java.util.List,
		         us.mn.state.health.lims.panel.valueholder.Panel,
		         us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample,
		         us.mn.state.health.lims.testconfiguration.action.SampleTypePanel" %>


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
<bean:define id="testList" name='<%=formName%>' property="existingPanelList" type="java.util.List"/>
<bean:define id="inactiveTestList" name='<%=formName%>' property="inactivePanelList" type="java.util.List"/>

<bean:define id="existingPanels" name='<%=formName%>' property="existingPanelList" type="java.util.List"/>
<bean:define id="inactivePanels" name='<%=formName%>' property="inactivePanelList" type="java.util.List"/>

<bean:define id="existingSampleTypes" name='<%=formName%>' property="existingSampleTypeList" type="java.util.List"/>


<%!
    String basePath = "";
    int testCount = 0;
    int columnCount = 0;
    int columns = 4;
    int sampleTypeCount = 0;
%>

<%
    basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
    columnCount = 0;
    testCount = 0;
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


    function confirmValues() {
        $jq("#editButtons").hide();
        $jq("#confirmationButtons").show();
        $jq("#editMessage").hide();
        $jq("#action").text('<%=StringUtil.getMessageForKey("label.confirmation")%>');

        $jq(".sortable").sortable("disable");
    }

    function rejectConfirmation() {
        $jq("#editButtons").show();
        $jq("#confirmationButtons").hide();
        $jq("#editMessage").show();
        $jq("#action").text('<%=StringUtil.getMessageForKey("label.button.edit")%>');

        $jq(".sortable").sortable("enable");
    }

    function buildJSONList(){
        var sortOrder = 0;
        var jsonObj = {};
        jsonObj.panels = [];

        $jq("li.sortItem").each(function(){
            jsonBlob = {};
            jsonBlob.id = $jq(this).val();
            jsonBlob.sortOrder = sortOrder++;
            jsonObj.panels[sortOrder - 1] = jsonBlob;
        });

        $jq("#jsonChangeList").val(JSON.stringify(jsonObj));
    }
    function savePage() {
        buildJSONList();
        window.onbeforeunload = null; // Added to flag that formWarning alert isn't needed.
        var form = window.document.forms[0];
        form.action = "PanelOrderUpdate.do";
        form.submit();
    }
</script>
    <html:hidden name="<%=formName%>" property="jsonChangeList" styleId="jsonChangeList"/>

    <input type="button" value='<%= StringUtil.getMessageForKey("banner.menu.administration") %>'
           onclick="submitAction('MasterListsPage.do');"
           class="textButton"/>&rarr;
    <input type="button" value='<%= StringUtil.getMessageForKey("configuration.test.management") %>'
           onclick="submitAction('TestManagementConfigMenu.do');"
           class="textButton"/>&rarr;
    <input type="button" value='<%= StringUtil.getMessageForKey("configuration.panel.manage") %>'
           onclick="submitAction('PanelManagement.do');"
           class="textButton"/>&rarr;

<%=StringUtil.getMessageForKey( "configuration.panel.order" )%>
<br><br>

<div id="editDiv" >
    <h1 id="action"><bean:message key="label.button.edit"/></h1>

    <div id="editMessage" >
        <h3><bean:message key="configuration.panel.order.explain"/> </h3>
        <bean:message key="configuration.panel.order.explain.limits" /><br/><br/>
    </div>

    <UL class="sortable" style="width:250px">
        <% for(int i = 0; i < panelList.size(); i++){
            IdValuePair panel = (IdValuePair)panelList.get(i);
        %>
        <LI class="ui-state-default_oe sortItem" value='<%=panel.getId() %>' ><span class="ui-icon ui-icon-arrowthick-2-n-s" ></span><%=panel.getValue() %></LI>
        <% } %>

    </UL>

    <div style="text-align: center" id="editButtons">
        <input type="button" value='<%=StringUtil.getMessageForKey("label.button.next")%>'
               onclick="confirmValues();"/>
        <input type="button" value='<%=StringUtil.getMessageForKey("label.button.previous")%>'
               onclick='submitAction("PanelManagement.do")'/>
    </div>
    <div style="text-align: center; display: none;" id="confirmationButtons">
        <input type="button" value='<%=StringUtil.getMessageForKey("label.button.accept")%>'
               onclick="savePage();"/>
        <input type="button" value='<%=StringUtil.getMessageForKey("label.button.reject")%>'
               onclick='rejectConfirmation();'/>
    </div>
</div>
<% sampleTypeCount=0; %>
<h3><bean:message key="panel.existing" /></h3>

<% while(sampleTypeCount < existingPanels.size()){%>
<b><%=((SampleTypePanel)existingPanels.get(sampleTypeCount)).getTypeOfSampleName() %></b>

<table width="80%">
    <tr>
        <td width='100%>'>
        <% if( ((SampleTypePanel)existingPanels.get(sampleTypeCount)).getPanels() != null){ 
        
            testCount=0;
        %>
        &nbsp;&nbsp;          
        <% while(testCount < ((SampleTypePanel)existingPanels.get(sampleTypeCount)).getPanels().size()){ %>
            <% if (testCount != 0) { %>
                <%=", " %>
            <% } %>
            <%=((SampleTypePanel)existingPanels.get(sampleTypeCount)).getPanels().get(testCount).getLocalizedName()%>
            <% testCount++; %>
        <% } %>  
        <% } %>
         </td>
    </tr>
</table>
<% sampleTypeCount++; %>
<br>
<% } %>
<h3><bean:message key="panel.existing.inactive" /></h3>

<% if( !inactivePanels.isEmpty()){ %>
<% sampleTypeCount = 0; %>
<% while(sampleTypeCount < inactivePanels.size()){%>
<b><%=((SampleTypePanel)inactivePanels.get(sampleTypeCount)).getTypeOfSampleName() %></b>
 <table width="80%"> 
     <tr> 
        <td width="100%">
        <% if( ((SampleTypePanel)inactivePanels.get(sampleTypeCount)).getPanels() != null){ 
        
            testCount = 0;
        %>    
        &nbsp;&nbsp;
        <% while(testCount < ((SampleTypePanel)inactivePanels.get(sampleTypeCount)).getPanels().size()){%>
            <% if (testCount != 0) { %>
                <%=", " %>
            <% } %>            
            <%= ((SampleTypePanel)inactivePanels.get(sampleTypeCount)).getPanels().get(testCount).getLocalizedName() %>
            <% testCount++; %>
        <% } %>
        <% } %>
        </td>
     </tr> 
 </table> 
<% sampleTypeCount++; %>
<br>
<% } %>
<% } %>

