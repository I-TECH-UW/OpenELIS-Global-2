<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants,
			us.mn.state.health.lims.typeofsample.formbean.TypeOfSampleLink" %>

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
	   <th><%--bugzilla 1412--%>
	   	  <bean:message key="typeofsample.test.sample"/>
	   </th>
	   <th>
	   	  <bean:message key="typeofsample.test.name"/>
	   </th>
	   <th>
	   	  <bean:message key="typeofsample.test.description"/>
	   </th>
	</tr>
	<logic:iterate id="tos" name="<%=formName%>"  indexId="ctr"  property="menuList" type="TypeOfSampleLink">
	<bean:define id="tosID" name="tos" property="id"/>
	  <tr>
	   <td class="textcontent">
	      <html:multibox name="<%=formName%>" property="selectedIDs">
	         <bean:write name="tosID" />
	      </html:multibox>
   	   </td>
	   <td class="textcontent">
	      <logic:notEmpty name="tos" property="sampleName">
	        <bean:write name="tos" property="sampleName"/>
	      </logic:notEmpty>
	      &nbsp;
	   </td>
	   <td class="textcontent">
	   	  <bean:write name="tos" property="linkName"/>
	   </td>
	   <td class="textcontent">
	   	  <bean:write name="tos" property="linkDescription"/>
	   </td>
     </tr>
	</logic:iterate>
</table>
