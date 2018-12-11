<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.organization.valueholder.Organization,
			us.mn.state.health.lims.common.action.IActionConstants,
			us.mn.state.health.lims.common.formfields.FormFields,
			us.mn.state.health.lims.common.util.StringUtil" %>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" uri="/tags/labdev-view" %>
<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

<%!
	boolean useOrgLocalAbbrev = true;
	boolean useOrgState = true;
	boolean useZipCode = true;
	boolean useMLS = true;
 %>

 <%
 	useOrgLocalAbbrev = FormFields.getInstance().useField(FormFields.Field.OrgLocalAbrev);
 	useOrgState = FormFields.getInstance().useField(FormFields.Field.OrgState);
 	useZipCode = FormFields.getInstance().useField(FormFields.Field.ZipCode);
 	useMLS = FormFields.getInstance().useField(FormFields.Field.MLS);
  %>

 


<table width="100%" border=2">
	<tr>
	   <th>
	     <spring:message code="label.form.select"/>
	   </th>
		<% if( useOrgLocalAbbrev) { %>
	   <th><%--bugzilla 2069 added--%>
	   	  <spring:message code="organization.localAbbreviation"/>
	   </th>
	   <% } %>
	   <th><%--bugzilla 1412 rearrange--%>
	   	  <spring:message code="organization.organizationName"/>
	   </th>
	   <th>
	   	  <spring:message code="organization.parent"/>
	   </th>
	   <th>
	   	  <%= StringUtil.getContextualMessageForKey("organization.short") %>
	   </th>
	   <th>
	      <spring:message code="organization.isActive"/>
	   </th>
	   <th>
	   	  <spring:message code="organization.streetAddress"/>
	   </th>
	   <th>
	   	  <spring:message code="organization.city"/>
	   </th>
	   <% if( useOrgState){ %>
	   <th>
	   	  <spring:message code="organization.state"/>
	   </th>
	   <% } %>
	   <% if( useZipCode ){ %>
	    <th>
	   	  <spring:message code="organization.zipCode"/>
	   </th>
	   <% } %>
	   <th>
	   	  <spring:message code="organization.clia.number"/>
	   </th>
	   <% if( useMLS ){ %>
	   <th>
	   	  <spring:message code="organization.mls.lab"/>
	   </th>
	   <% } %>

	</tr>
	<logic:iterate id="org1" indexId="ctr" name="${form.formName}" property="menuList" type="us.mn.state.health.lims.organization.valueholder.Organization">
	<bean:define id="orgID" name="org1" property="id"/>
	<logic:notEmpty name="org1" property="organization">
	 <bean:define id="parentOrgID" name="org1" property="organization.id"/>
	</logic:notEmpty>

	  <tr>
	   <td class="textcontent">
	      <html:multibox name="${form.formName}" property="selectedIDs" onclick="output()" >
	         <bean:write name="orgID" />
	      </html:multibox>

   	   </td>
		<% if( useOrgLocalAbbrev ){ %>
	   <td class="textcontent">
	   	  <bean:write name="org1" property="organizationLocalAbbreviation"/>
	   </td>
	   <% } %>
	   <td class="textcontent">
	   	  <bean:write name="org1" property="organizationName"/>
	   </td>
	   <td class="textcontent">
	    <logic:notEmpty name="org1" property="organization">
	        <bean:write name="org1" property="organization.organizationName"/>
	    </logic:notEmpty>
	      &nbsp;
	   </td>
	   <td class="textcontent">
	      <logic:notEmpty name="org1" property="shortName">
	   	  <bean:write name="org1" property="shortName"/>
	   	  </logic:notEmpty>
	   	  <logic:empty name="org1" property="shortName">
	   	   &nbsp;
	   	  </logic:empty>
	   </td>
	   <td class="textcontent">
	   	  <bean:write name="org1" property="isActive"/>
	   </td>
	   <td class="textcontent">
	   	  <bean:write name="org1" property="streetAddress"/>
	   	  &nbsp;
	   </td>
	   <td class="textcontent">
	   	  <bean:write name="org1" property="city"/>
	   	  &nbsp;
	   </td>
	   <% if( useOrgState ){ %>
	  <td class="textcontent">
	   	  <bean:write name="org1" property="state"/>
	   	  &nbsp;
	   </td>
	   <% } %>
	   <% if( useZipCode ){ %>
	   <td class="textcontent">
	   	  <bean:write name="org1" property="zipCode"/>
	   	  &nbsp;
	   </td>
	   <% } %>
	   <td class="textcontent">
	   	  <bean:write name="org1" property="cliaNum"/>
	   	  &nbsp;
	   </td>
	   <% if( useMLS ){ %>
	   <td class="textcontent">
	     <bean:write name="org1" property="mlsLabFlag"/>
	   	  &nbsp;
	    </td>
	   <% } %>
     </tr>
	</logic:iterate>
</table>
