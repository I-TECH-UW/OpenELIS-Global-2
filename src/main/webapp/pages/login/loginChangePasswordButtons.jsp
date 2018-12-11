<%@ page language="java"
	contentType="text/html; charset=utf-8"
%>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" uri="/tags/labdev-view" %>
<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

<table width="100%">
<tr>
    <td width="50%" valign="top">
        <table width="95%">
        <tr><td colspan="4">&nbsp;</td>
        <tr>         
            <td width="20%">&nbsp;</td>
            <td width="10%" noWrap>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td colspan="2" align="left">
                <button type="submit" form='form' value="submit" >
  		            <spring:message code="label.button.submit"/>
                </button>
                <a href="LoginPage.do">
  		            <spring:message code="label.button.exit"/>
                </a>
            </td> 
        </tr>
        </table>
    </td>
</tr>
</table>  