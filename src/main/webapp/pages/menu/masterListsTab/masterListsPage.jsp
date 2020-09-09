<%@ page import="org.openelisglobal.common.util.ConfigurationProperties,
				org.openelisglobal.common.util.ConfigurationProperties.Property,
				org.openelisglobal.common.util.Versioning" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<%-- &nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="ellis.configuration" text="ellis.configuration"/>:&nbsp; --%>
<%-- <%=ConfigurationProperties.getInstance().getPropertyValue( Property.configurationName )%><br/> --%>
<table width="100%">
<tr>
<td width="15%" valign="top"><tiles:insertAttribute name="left"/></td>
<td width="85%"><tiles:insertAttribute name="right"/></td>
</tr>
</table>

