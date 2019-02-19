<%@ page language="java"
	contentType="text/html; charset=utf-8"
%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" uri="/tags/labdev-view" %>
<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>
<table width="95%">
<tr><td colspan="4">&nbsp;</td>
<tr>
    <td width="20%">&nbsp;</td>	
    <td width="110" noWrap>&nbsp;</td>
    <td colspan="2" align="left">
        <%--bugzilla 2376--%>
        <spring:message code="label.button.submit" var="submitText"/>
        <input type="button" id="submitButton" onclick="submitOnClick(this);return false;" value="${submitText}"/>
  			
		<spring:message code="label.button.changePassword" var="changePasswordText"/>
        <input type="button" id="changePasswordButton" onclick="setAction(document.getElementById("mainForm"), 'ChangePassword', 'no', '');" value="${changePasswordText}"/>
  		   
    </td>        
</tr>         
</table>