<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants,
			us.mn.state.health.lims.userrole.valueholder.UserRole" %>

<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/labdev-view" prefix="app" %>

<bean:define id="formName" value='<%= (String)request.getAttribute(IActionConstants.FORM_NAME) %>' />

<table width="80%" border="2">
	<tr>
		<th>&nbsp;</th>
	   	<th><bean:message key="systemuserrole.user"/></th>
	   	<th> <bean:message key="systemuserrole.role"/> </th>
	</tr>
	<logic:iterate id="userRoles" name="<%=formName%>" indexId="ctr" property="menuList" type="UserRole">
		<!--<bean:define id="limitId" name="userRoles" property="id"/> -->
	  	<tr>
	   		<td class="textcontent">
	      		<html:multibox name="<%=formName%>" property="selectedIDs">
	         		<bean:write name="userRoles" property="uniqueIdentifyer" />
	      		</html:multibox>
   	   		</td>
   	   		<td class="textcontent">
	    		<bean:write name="userRoles" property="userName"/>
	   		</td>
   	  	 	<td class="textcontent">
	   	  		<bean:write name="userRoles" property="roleName"/>
	   		</td>
     	</tr>
	</logic:iterate>
</table>
