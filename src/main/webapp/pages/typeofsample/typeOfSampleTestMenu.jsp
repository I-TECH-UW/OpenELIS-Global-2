<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="org.openelisglobal.common.action.IActionConstants,
			org.openelisglobal.typeofsample.formbean.TypeOfSampleLink" %>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" uri="/tags/labdev-view" %>
<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

 

<table width="100%" border=2">
	<tr>
	   <th>
	     <spring:message code="label.form.select"/>
	   </th>
	   <th><%--bugzilla 1412--%>
	   	  <spring:message code="typeofsample.test.sample"/>
	   </th>
	   <th>
	   	  <spring:message code="typeofsample.test.name"/>
	   </th>
	   <th>
	   	  <spring:message code="typeofsample.test.description"/>
	   </th>
	</tr>
	<logic:iterate id="tos" name="${form.formName}"  indexId="ctr"  property="menuList" type="TypeOfSampleLink">
	<bean:define id="tosID" name="tos" property="id"/>
	  <tr>
	   <td class="textcontent">
	      <html:multibox name="${form.formName}" property="selectedIDs">
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
