<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants,
			us.mn.state.health.lims.resultlimits.form.ResultLimitsLink" %>

<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/labdev-view" prefix="app" %>

<bean:define id="formName" value='<%= (String)request.getAttribute(IActionConstants.FORM_NAME) %>' />

<table width="100%" border=2">
	<tr>
	   <th>&nbsp;</th>
	   <th>&nbsp;</th>
	   <th>&nbsp;</th>
	   <th colspan="2" align="center">
	      <bean:message key="resultlimits.age"/>
	   </th>
	   <th>&nbsp;</th>
	   <th colspan="3" align="center">
	      <bean:message key="resultlimits.normal"/>
	   </th>
	   <th colspan="2" align="center">
	      <bean:message key="resultlimits.valid"/>
	   </th>
	</tr>
	<tr>
	   <th>
	     <bean:message key="label.form.select"/>
	   </th>
	   <th>
	   	  <bean:message key="resultlimits.test"/>
	   </th>
	   <th>
	   	  <bean:message key="resultlimits.resulttype"/>
	   </th>
	   <th>
	      <bean:message key="resultlimits.min"/>
	   </th>
	   <th>
	      <bean:message key="resultlimits.max"/>
	   </th>
	   <th>
	      <bean:message key="resultlimits.gender"/>
	   </th>
	   <th>
	      <bean:message key="resultlimits.low"/>
	   </th>
	   <th>
	      <bean:message key="resultlimits.high"/>
	   </th>
	   <th>
	   	  <bean:message key="resultLimits.dictionaryNormal" />
	   </th>
	   <th>
	      <bean:message key="resultlimits.low"/>
	   </th>
	   <th>
	      <bean:message key="resultlimits.high"/>
	   </th>
	</tr>
	<logic:iterate id="limit" name="<%=formName%>" indexId="ctr" property="menuList" type="ResultLimitsLink">
	<bean:define id="limitId" name="limit" property="id"/>
	  <tr>
	   <td class="textcontent">
	      <html:multibox name="<%=formName%>" property="selectedIDs">
	         <bean:write name="limitId" />
	      </html:multibox>
   	   </td>
   	   <td class="textcontent">
	   	  <bean:write name="limit" property="testName"/>
	   </td>
   	   <td class="textcontent">
	   	  <bean:write name="limit" property="resultType"/>
	   </td>
   	   <td class="textcontent" align="right" >
	   	  <bean:write name="limit" property="minDayAgeDisplay"/> / <bean:write name="limit" property="minAgeDisplay"/>
	   </td>
   	   <td class="textcontent" align="right" >
	   	  <bean:write name="limit" property="maxDayAgeDisplay"/> / <bean:write name="limit" property="maxAgeDisplay"/>
	   </td>
   	   <td class="textcontent"  align="center" >
	   	  <bean:write name="limit" property="gender"/>
	   </td>
   	   <td class="textcontent" align="right" >
	   	  <bean:write name="limit" property="lowNormalDisplay"/>
	   </td>
   	   <td class="textcontent" align="right" >
	   	  <bean:write name="limit" property="highNormalDisplay"/>
	   </td>
   	   <td class="textcontent" align="right" >
	   	  <bean:write name="limit" property="dictionaryNormal"/>
	   </td>
   	   <td class="textcontent" align="right" >
	   	  <bean:write name="limit" property="lowValidDisplay"/>
	   </td>
   	   <td class="textcontent" align="right" >
	   	  <bean:write name="limit" property="highValidDisplay"/>
	   </td>
     </tr>
	</logic:iterate>
</table>
