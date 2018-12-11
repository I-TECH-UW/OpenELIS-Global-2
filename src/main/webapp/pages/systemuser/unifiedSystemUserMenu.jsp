<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants" %>

<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/labdev-view" prefix="app" %>

<bean:define id="formName" value='<%= (String)request.getAttribute(IActionConstants.FORM_NAME) %>' />

<table width="100%" border="2">
	<tr>
	   <th>
	     <bean:message key="label.form.select"/>
	   </th>
	   <th>
	   	  <bean:message key="systemuser.lastName"/>
	   </th>
	   <th>
	      <bean:message key="systemuser.firstName"/>
	   </th>
	   <th>
	   	  <bean:message key="systemuser.loginName"/>
	   </th>
	   <th>
	   	  <bean:message key="login.password.expired.date"/>
	   </th>
	   <th>
	   	  <bean:message key="login.account.locked"/>
	   </th>
	   <th>
	   	  <bean:message key="login.account.disabled"/>
	   </th>
	   <th>
	   	  <bean:message key="systemuser.isActive" />
	   </th>
	   <th>
	   	  <bean:message key="login.timeout"/>
	   </th>
	</tr>
	<logic:iterate id="systemUser" indexId="ctr" name="<%=formName%>" property="menuList" type="us.mn.state.health.lims.systemuser.valueholder.UnifiedSystemUser">
	  <tr>
	   <td class="textcontent">
	      <html:multibox name="<%=formName%>" property="selectedIDs"  onclick="output()" >
              <bean:write name="systemUser" property="combinedUserID"/>
	      </html:multibox>
     
   	   </td>
   	   
	   <td class="textcontent">
	   	  <bean:write name="systemUser" property="lastName"/>
	   </td>
	   <td class="textcontent">
	   	  <bean:write name="systemUser" property="firstName"/>
	   </td>
	   <td class="textcontent">
	   	  <bean:write name="systemUser" property="loginName"/>
	   </td>
	   <td class="textcontent">
	   	  <bean:write name="systemUser" property="expDate"/>
	   </td>
	   <td class="textcontent">
	   	  <bean:write name="systemUser" property="locked"/>
	   </td>
	   <td class="textcontent">
	   	  <bean:write name="systemUser" property="disabled"/>
	   </td>
	   <td class="textcontent">
	   	  <bean:write name="systemUser" property="active"/>
	   </td>
	   <td class="textcontent">
	   	  <bean:write name="systemUser" property="timeout"/>
	   </td>
	   
       </tr>
	</logic:iterate>
</table>
