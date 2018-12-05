<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants,
			us.mn.state.health.lims.role.valueholder.Role" %>

<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/labdev-view" prefix="app" %>

<bean:define id="formName" value='<%= (String)request.getAttribute(IActionConstants.FORM_NAME) %>' />

<table width="80%" border="2">
	<tr>
		<th>&nbsp;</th>
	   	<th><bean:message key="role.name" /></th>
	   	<th><bean:message key="role.description"/></th>
	   	<th><bean:message key="role.isGroupingRole"/></th>
	   	<th><bean:message key="role.parent.role"/></th>
	   	<th><bean:message key="role.display.key"/></th>
	</tr>
	<logic:iterate id="limit" name="<%=formName%>" indexId="ctr" property="menuList" type="Role">
		<bean:define id="limitId" name="limit" property="id"/>
	  	<tr>
	   		<td class="textcontent">
	      		<html:multibox name="<%=formName%>" property="selectedIDs">
	         		<bean:write name="limitId" />
	      		</html:multibox>
   	   		</td>
   	   		<td class="textcontent">
	    		<bean:write name="limit" property="name"/>
	   		</td>
   	  	 	<td class="textcontent">
	   	  		<bean:write name="limit" property="description"/>
	   		</td>
   	  	 	<td class="textcontent">
	   	  		<bean:write name="limit" property="groupingRole"/>
	   		</td>
   	  	 	<td class="textcontent">
	   	  		<bean:write name="limit" property="groupingParent"/>
	   		</td>
   	  	 	<td class="textcontent">
	   	  		<bean:write name="limit" property="displayKey"/>
	   		</td>
     	</tr>
	</logic:iterate>
</table>
