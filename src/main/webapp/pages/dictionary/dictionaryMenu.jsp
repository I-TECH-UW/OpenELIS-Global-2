<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.dictionary.valueholder.Dictionary,
		us.mn.state.health.lims.common.action.IActionConstants" %>

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
	   <th>
	   	  <bean:message key="dictionary.dictionarycategory"/>
	   </th>
	   <th>
	      <bean:message key="dictionary.dictEntry"/>
	   </th>
	   <th>
	      <bean:message key="dictionary.localAbbreviation"/>
	   </th>
	   <th>
	      <bean:message key="dictionary.isActive"/>
	   </th>
   
	</tr>
	<logic:iterate id="dict" indexId="ctr" name="<%=formName%>" property="menuList" type="us.mn.state.health.lims.dictionary.valueholder.Dictionary">
	<bean:define id="dictID" name="dict" property="id"/>
	  <tr>	
	   <td class="textcontent">
	      <html:multibox name="<%=formName%>" property="selectedIDs" onclick="output()">
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
