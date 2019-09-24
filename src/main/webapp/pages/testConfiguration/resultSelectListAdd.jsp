<%--
  Created by IntelliJ IDEA.
  User: kenny
  Date: 2019-09-24
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java"
         contentType="text/html; charset=utf-8"
         import="org.openelisglobal.common.action.IActionConstants,
         		java.util.List,
         		org.openelisglobal.panel.valueholder.Panel,
         		org.openelisglobal.common.util.IdValuePair,
         		org.openelisglobal.internationalization.MessageUtil,
         		org.openelisglobal.common.util.Versioning,
         		org.openelisglobal.testconfiguration.action.SampleTypePanel" %>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

<input 	type="button"
          class="textButton"
          value="<%= MessageUtil.getContextualMessage("banner.menu.administration")%>"
          onclick="submitAction('MasterListsPage.do');" >&rarr;

<input  type="button"
        class="textButton"
        value="<%= MessageUtil.getContextualMessage("configuration.test.management") %>"
        onclick="submitAction('TestManagementConfigMenu.do');" >&rarr;


<%=MessageUtil.getContextualMessage( "label.resultSelectList" ) %>
<br><br>

<h1 id="action"><spring:message code="configuration.selectList.header"/></h1>
<p><spring:message code="configuration.selectList.description"/></p>
<div style="width: 100%; min-height: 50px;">
    <div style="width: 300px; float: left;">
        <label style="width: 100%;">English</label>
        <input name="english"/>
    </div>

    <div style="width: 300px; float: left;">
        <label style="width: 100%;">French</label>
        <input name="french"/>
    </div>
</div>

<div style="width: 100%; text-align: center">
    <button>
        <spring:message code="label.button.next"/>
    </button>
    <button>
        <spring:message code="label.button.cancel"/>
    </button>
</div>

<h1 id="action"><spring:message code="configuration.selectList.assign.header"/></h1>
<p><spring:message code="configuration.selectList.assign.description"/></p>
<p><spring:message code="configuration.selectList.assign.description_2"/></p>
<p><spring:message code="configuration.selectList.assign.description_3"/></p>
<p><spring:message code="configuration.selectList.assign.new"/></p>

<table style="margin: 0 auto; width: 20%;">
    <tr>
        <th>English</th><th>French</th>
    </tr>
    <tr>
        <td>xxx</td><td>xxx</td>
    </tr>
</table>

<div style="width: 100%; text-align: center">
    <button>
        <spring:message code="label.button.save"/>
    </button>
    <button>
        <spring:message code="label.button.back"/>
    </button>
</div>