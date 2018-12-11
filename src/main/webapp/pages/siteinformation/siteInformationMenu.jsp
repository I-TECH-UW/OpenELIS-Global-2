<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants,
			us.mn.state.health.lims.localization.daoimpl.LocalizationDAOImpl,
			us.mn.state.health.lims.localization.valueholder.Localization,
			us.mn.state.health.lims.siteinformation.valueholder.SiteInformation,
			us.mn.state.health.lims.localization.dao.LocalizationDAO" %>

<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/labdev-view" prefix="app" %>

<bean:define id="formName" value='<%= (String)request.getAttribute(IActionConstants.FORM_NAME) %>' />

<%!
    LocalizationDAO localizationDAO;
%>

<%
    localizationDAO = new LocalizationDAOImpl();
%>

<table width="80%" border="2">
	<tr>
		<th>&nbsp;</th>
	   	<th><bean:message key="generic.name" /></th>
	   	<th><bean:message key="generic.description"/></th>
	   	<th><bean:message key="generic.value"/></th>
	</tr>
	<logic:iterate id="site" name="<%=formName%>" indexId="ctr" property="menuList" type="SiteInformation">
		<bean:define id="siteId" name="site" property="id"/>
	  	<tr>
	   		<td class="textcontent">
	      		<html:multibox name="<%=formName%>" property="selectedIDs" onclick="output()">
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
