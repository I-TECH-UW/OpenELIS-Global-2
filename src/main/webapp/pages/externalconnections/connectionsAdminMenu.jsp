<%@ page language="java" 
		contentType="text/html; charset=utf-8" %>
<%@ page import="us.mn.state.health.lims.common.action.IActionConstants,
				us.mn.state.health.lims.common.formfields.AdminFormFields,
				us.mn.state.health.lims.common.formfields.AdminFormFields.Field" %>

<%@ taglib uri="/tags/struts-bean"		prefix="bean" %>
<%@ taglib uri="/tags/struts-html"		prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<bean:define id="formName" value='<%= (String)request.getAttribute(IActionConstants.FORM_NAME) %>' />

<table width="80%" border="2">
	<tr>
	   	<th><bean:message key="externalconnect.menu.title" /></th>
	</tr>
	<logic:iterate id="urlForDisplay" name="<%=formName%>" property="menuList">
	  	<tr>
   	   		<td class="textcontent">
	    		<a href="<bean:write name="urlForDisplay" property="urlAddress"/>"><bean:message name="urlForDisplay" property="displayKey"/></a>
	   		</td>
     	</tr>
	</logic:iterate>
</table>