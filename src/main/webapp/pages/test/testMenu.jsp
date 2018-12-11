<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants" %>

<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/labdev-view" prefix="app" %>

<bean:define id="formName" value='<%= (String)request.getAttribute(IActionConstants.FORM_NAME) %>' />

<table width="100%" border=2">
	<tr>
	   <th>
	     <bean:message key="label.form.select"/>
	   </th>
	   <%--remove the following 09/12/2006 bugzilla 1399--%>
	   <%--
	   <th>
	      <bean:message key="test.id"/>
	   </th>
	   --%>
	   <th><%--bugzilla 1412--%>
	   	  <bean:message key="test.testSectionName"/>
	   </th>
	   <th>
	   	  <bean:message key="test.testName"/>
	   </th>
	   <%--bugzilla 1399 sorting by testSection so show it/ remove test method--%>
	   <th>
	   	  <bean:message key="test.description"/>
	   </th>
	   <th>
	   	  <bean:message key="test.isActive"/>
	   </th>
       <%--bugzilla 1784 added isReportable--%>
	   <th>
	   	  <bean:message key="test.isReportable"/>
	   </th>
	   <%--bugzilla 1856--%>
	   <th>
	   	  <bean:message key="test.sortOrder"/>
	   </th> 
    </tr>
	<logic:iterate id="tst" indexId="ctr" name="<%=formName%>" property="menuList" type="us.mn.state.health.lims.test.valueholder.Test">
	<bean:define id="tstID" name="tst" property="id"/>
	  <tr>
	   <td class="textcontent">
	      <html:multibox name='<%=formName%>' property="selectedIDs">
	         <bean:write name="tstID" />
	      </html:multibox>
   	   </td>
   	   <%--remove the following 09/12/2006 bugzilla 1399--%>
	   <%--
	   <td class="textcontent">
	      <bean:write name="tst" property="id"/>
	   </td>
	   --%>
	   <td class="textcontent">
	   	 <logic:notEmpty name="tst" property="testSection">
	        <bean:write name="tst" property="testSection.testSectionName"/>
	     </logic:notEmpty>
	   </td>
	   <td class="textcontent">
	      <bean:write name="tst" property="testName"/>
	   </td>
	   <td class="textcontent">
	   	  <bean:write name="tst" property="description"/>
	   </td>
	   <td class="textcontent">
	   	  <bean:write name="tst" property="isActive"/>
	   </td>
       <%--bugzilla 1784added isReportable--%>
	   <td class="textcontent">
	   	  <bean:write name="tst" property="isReportable"/>
	   </td>
	   <%--bugzilla 1856--%>
	   <td class="textcontent">
	   	  <bean:write name="tst" property="sortOrder"/>
	   </td>
     </tr>
	</logic:iterate>
</table>
