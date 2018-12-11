<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants,
			us.mn.state.health.lims.localization.daoimpl.LocalizationDAOImpl,
			us.mn.state.health.lims.localization.valueholder.Localization,
			us.mn.state.health.lims.siteinformation.valueholder.SiteInformation,
			us.mn.state.health.lims.localization.dao.LocalizationDAO" %>

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
	<logic:iterate id="site" name="${form.formName}" indexId="ctr" property="menuList" type="SiteInformation">
		<bean:define id="siteId" name="site" property="id"/>
	  	<tr>
	   		<td class="textcontent">
	      		<html:multibox name="${form.formName}" property="selectedIDs" onclick="output()">
	         		<bean:write name="siteId" />
	      		</html:multibox>
   	   		</td>
   	   		<td class="textcontent">
	    		<bean:write name="site" property="name"/>
	   		</td>
   	  	 	<td class="textcontent">
	   	  	 <bean:message name="site" property="descriptionKey"/>
	   	    </td>
	   		<% if( site.getValueType().equals("logoUpload")){ %>
	   		<td class="textcontent">
                <% if( site.getName().equals("headerLeftImage")){ %>
                <img src="./images/leftLabLogo.jpg?ver=<%= Math.random() %>"  
                <%}else{%>
                <img src="./images/rightLabLogo.jpg?ver=<%= Math.random() %>"  
                <%}%>
	   		         height="42" 
	   		         width="42"  />
	   		</td>
	   		<% }else if("localization".equals(site.getTag())){
                Localization localization = localizationDAO.getLocalizationById( site.getValue() );

                out.write("<td class='textcontent'>" + localization.getEnglish() + "/" + localization.getFrench() + "</td>");
            } else { %>
	   		<td class="textcontent">
	   	  		<bean:write name="site" property="value"/>
	   		</td>
	   		<% } %>
     	</tr>
	</logic:iterate>
</table>
