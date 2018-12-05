<%@ page language="java"
	contentType="text/html; charset=utf-8"
%>

<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/labdev-view" prefix="app" %>

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
                <html:button onclick="setAction(window.document.forms[0], 'Update', 'yes', '');"  property="save" >
  		            <bean:message key="label.button.submit"/>
                </html:button>
                <html:button onclick="setAction(window.document.forms[0], 'Exit', 'no', '');"  property="cancel" >
  		            <bean:message key="label.button.exit"/>
                </html:button>
            </td> 
        </tr>
        </table>
    </td>
</tr>
</table>  