<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants,
			us.mn.state.health.lims.localization.daoimpl.LocalizationDAOImpl,
			us.mn.state.health.lims.localization.valueholder.Localization,
			us.mn.state.health.lims.siteinformation.valueholder.SiteInformation,
			us.mn.state.health.lims.localization.dao.LocalizationDAO,
			org.owasp.encoder.Encode" %>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" uri="/tags/labdev-view" %>
<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

 

<%!
    LocalizationDAO localizationDAO;
%>

<%
    localizationDAO = new LocalizationDAOImpl();
%>

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
	   	    <c:choose>
	   	    <c:when test="${site.valueType == 'logoUpload'}">
	   		<td class="textcontent">
	   			<c:choose>
	   			<c:when test="${site.name == 'headerLeftImage'}">
                <img src="./images/leftLabLogo.jpg?ver=<%= Math.random() %>"  
                	 height="42" 
	   		         width="42"  />
                </c:when><c:otherwise>
                <img src="./images/rightLabLogo.jpg?ver=<%= Math.random() %>"  
                	 height="42" 
	   		         width="42"  />
                </c:otherwise>
                </c:choose>
	   		         
	   		</td>
	   		</c:when><c:when test="${site.tag == 'localization'}">
	   		<% 
	   		SiteInformation site = (SiteInformation) pageContext.getAttribute("site");
	   		Localization localization = localizationDAO.getLocalizationById( site.getValue() ); %>
	   			<td class='textcontent'> <c:out value="${site.englishValue}"/>/<c:out value="${site.frenchValue}"/> </td>
            </c:when><c:otherwise>
	   		<td class="textcontent">
	   	  		<c:out value="${site.value}"/>
	   		</td>
	   		</c:otherwise>
	   		</c:choose>
     	</tr>
	</c:forEach>
	</form:form>
</table>
