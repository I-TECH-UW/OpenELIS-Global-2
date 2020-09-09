<%@ page language="java"
	contentType="text/html; charset=UTF-8"
	import="org.openelisglobal.common.action.IActionConstants,
			org.openelisglobal.localization.valueholder.Localization,
			org.openelisglobal.siteinformation.valueholder.SiteInformation,
			org.owasp.encoder.Encode" %>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

<script type="text/javascript" src="scripts/externalConnections.js"></script>
<script>

function editConnection(id) {
	location.href = "ExternalConnection?ID=" + id;
}

</script>

<table width="80%" border="2">
	<tr>
		<th>&nbsp;</th>
	   	<th><spring:message code="externalconnections.name" /></th>
	   	<th><spring:message code="externalconnections.description"/></th>
	   	<th><spring:message code="externalconnections.lastupdated"/></th>
	</tr>
	<form:form name="${form.formName}" 
		   action="${form.formAction}" 
		   modelAttribute="form" 
		   method="${form.formMethod}"
		   id="menuForm">
	<c:forEach items="${form.menuList}" var="externalConnection" varStatus="iter">
	  	<tr>
	   		<td class="textcontent">
	      		<form:checkbox path="selectedIDs" value="${externalConnection.id}" onclick="output()"/>
   	   		</td>
   	   		<td class="textcontent">
	    		<c:out value="${externalConnection.nameLocalization.localizedValue}"/>
	   		</td>
   	   		<td class="textcontent">
	    		<c:out value="${externalConnection.descriptionLocalization.localizedValue}"/>
	   		</td>
   	  	 	<td class="textcontent">
	    		<c:out value="${externalConnection.lastupdated}"/>
	   	    </td>
   	  	 	<td class="textcontent">
   	  	 		<button type="button" onClick="editConnection(${externalConnection.id})">Edit</button>
	   	    </td>
   	  	 	<td class="textcontent">
				<button type="button" onClick="testConnectionById(${externalConnection.id})"><spring:message code="externalConnections.test"/></button>
				<span id="connect-wait-${externalConnection.id}" hidden="hidden"><i class="fas fa-spinner" style="color:Blue;" ></i></span>
				<span id="connect-success-${externalConnection.id}" hidden="hidden"><i class="fas fa-check-double" style="color:Green;" ></i></span>
				<span id="connect-partial-${externalConnection.id}" hidden="hidden"><i class="fas fa-check" style="color:Goldenrod;"></i></span>
				<span id="connect-fail-${externalConnection.id}" hidden="hidden"><i class="fas fa-times" style="color:DarkRed;"></i></span>
	   	    </td>
     	</tr>
	</c:forEach>
	</form:form>
</table>
