<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.dictionary.valueholder.Dictionary,
		us.mn.state.health.lims.common.action.IActionConstants" %>

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
	   <th>
	   	  <spring:message code="dictionary.dictionarycategory"/>
	   </th>
	   <th>
	      <spring:message code="dictionary.dictEntry"/>
	   </th>
	   <th>
	      <spring:message code="dictionary.localAbbreviation"/>
	   </th>
	   <th>
	      <spring:message code="dictionary.isActive"/>
	   </th>
   
	</tr>
	<logic:iterate id="dict" indexId="ctr" name="${form.formName}" property="menuList" type="us.mn.state.health.lims.dictionary.valueholder.Dictionary">
	<bean:define id="dictID" name="dict" property="id"/>
	  <tr>	
	   <td class="textcontent">
	      <html:multibox name="${form.formName}" property="selectedIDs" onclick="output()">
	         <bean:write name="dictID" />
	      </html:multibox>
     
   	   </td>	 
	   <td class="textcontent">
	        <bean:write name="dict" property="dictionaryCategory.categoryName"/>
	      &nbsp;
       </td>
	   <td class="textcontent">
   	      <app:write name="dict" property="dictEntry" />
	      &nbsp;
       </td>
	   <td class="textcontent">
   	      <app:write name="dict" property="localAbbreviation" maxLength="10" />
	      &nbsp;
       </td>
	   <td class="textcontent">
	   	  <bean:write name="dict" property="isActive"/>
	   </td>
     </tr>
	</logic:iterate>
</table>
