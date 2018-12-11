<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.test.valueholder.TestSection,
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
	   <%--remove the following 09/12/2006 bugzilla 1399--%>
	   <%--
	   <th>
	      <bean:message key="testsection.id"/>
	   </th>
	   --%>
	   <th><%--bugzilla 1412--%>
	   	  <bean:message key="testsection.organization"/>
	   </th>
	   <th>
	   	  <bean:message key="testsection.testSectionName"/>
	   </th> 
	   <th> <%-- bugzilla 2025 --%>
	      <bean:message key="testsection.parent"/>
	    </th>
	    <th>
	      <bean:message key="testsection.isExternal"/>
	   </th>
	   <th>
	   	  <bean:message key="testsection.description"/>
	   </th>
	   
	</tr>
	<logic:iterate id="testSec" indexId="ctr" name="<%=formName%>" property="menuList" type="us.mn.state.health.lims.test.valueholder.TestSection">
	<bean:define id="testSecID" name="testSec" property="id"/>
	
	  <tr>	
	   <td class="textcontent">
	      <html:multibox name="<%=formName%>" property="selectedIDs">
	         <bean:write name="testSecID" />
	      </html:multibox>
     
   	   </td>
   	   <%--remove the following 09/12/2006 bugzilla 1399--%>
	   <%--
	   <td class="textcontent">
	      <bean:write name="testSec" property="id"/>
	   </td>
	   --%>

	   <td class="textcontent">
	   <logic:notEmpty name="testSec" property="organization">
	   	  <bean:write name="testSec" property="organization.organizationName"/>
	   	</logic:notEmpty>
	   </td>
	   <td class="textcontent">
          <bean:write name="testSec" property="testSectionName"/>
       </td>
       
       <%-- bugzilla 2025 --%>
       <td class="textcontent">
	    <logic:notEmpty name="testSec" property="parentTestSection">
	        <bean:write name="testSec" property="parentTestSection.testSectionName"/>
	    </logic:notEmpty>
	   </td>
       
	   <td class="textcontent">
	   	  <bean:write name="testSec" property="isExternal"/>
	   </td>
	   <td class="textcontent">
	     <bean:write name="testSec" property="description"/>
	   	  &nbsp;
	    </td>
     </tr>
	</logic:iterate>
</table>
