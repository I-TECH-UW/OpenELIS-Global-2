<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants" %>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" uri="/tags/labdev-view" %>
<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

 

<table width="100%" border="2">
	<tr>
	   <th>
	     <spring:message code="label.form.select"/>
	   </th>
	   <th>
	   	  <spring:message code="systemuser.lastName"/>
	   </th>
	   <th>
	      <spring:message code="systemuser.firstName"/>
	   </th>
	   <th>
	   	  <spring:message code="systemuser.loginName"/>
	   </th>
	   <th>
	   	  <spring:message code="login.password.expired.date"/>
	   </th>
	   <th>
	   	  <spring:message code="login.account.locked"/>
	   </th>
	   <th>
	   	  <spring:message code="login.account.disabled"/>
	   </th>
	   <th>
	   	  <spring:message code="systemuser.isActive" />
	   </th>
	   <th>
	   	  <spring:message code="login.timeout"/>
	   </th>
	</tr>
	<logic:iterate id="systemUser" indexId="ctr" name="${form.formName}" property="menuList" type="us.mn.state.health.lims.systemuser.valueholder.UnifiedSystemUser">
	  <tr>
	   <td class="textcontent">
	      <html:multibox name="${form.formName}" property="selectedIDs"  onclick="output()" >
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
