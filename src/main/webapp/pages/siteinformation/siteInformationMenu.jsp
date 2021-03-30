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

 


<table width="80%" border="2">
	<tr>
		<th>&nbsp;</th>
	   	<th><spring:message code="generic.name" /></th>
	   	<th><spring:message code="generic.description"/></th>
	   	<th><spring:message code="generic.value"/></th>
	</tr>
	<form:form name="${form.formName}" 
		   action="${form.formAction}" 
		   modelAttribute="form" 
		   method="${form.formMethod}"
		   id="menuForm">
	<c:forEach items="${form.menuList}" var="site" varStatus="iter">
		<c:set var="siteId" value="${site.id}"/>
	  	<tr>
	   		<td class="textcontent">
	      		<form:checkbox path="selectedIDs" value="${siteId}" onclick="output()"/>
   	   		</td>
   	   		<td class="textcontent">
	    		<c:out value="${site.name}"/>
	   		</td>
   	  	 	<td class="textcontent">
	   	  	 <spring:message code="${site.descriptionKey}"/>
	   	    </td>
	   	    <td class='textcontent'>
	   	    <c:choose>
	   	    <c:when test="${site.valueType == 'logoUpload'}">
	   			<c:choose>
	   			<c:when test="${site.name == 'headerLeftImage'}">
                <img src="./dbImage/siteInformation/headerLeftImage.jpg"  
                	 height="42" 
	   		         width="42"  />
                </c:when>
	   			<c:when test="${site.name == 'labDirectorSignature'}">
                <img src="./dbImage/siteInformation/labDirectorSignature.jpg"  
                	 height="42" 
	   		         width="42"  />
                </c:when><c:otherwise>
                <img src="./dbImage/siteInformation/headerRightImage.jpg"  
                	 height="42" 
	   		         width="42"  />
                </c:otherwise>
                </c:choose>
	   		</c:when><c:when test="${site.tag == 'localization'}">
	   			<c:forEach items="${site.localization.localesAndValuesOfLocalesWithValues}" var="localizationValue">
	   				<c:out value="${localizationValue}"/>
	   				<br>
	   			</c:forEach>
            </c:when><c:otherwise>
	   	  		<c:out value="${site.value}"/>
	   		</c:otherwise>
	   		</c:choose>
	   		</td>
     	</tr>
	</c:forEach>
	</form:form>
</table>
