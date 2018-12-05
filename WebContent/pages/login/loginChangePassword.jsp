<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants,
			us.mn.state.health.lims.common.provider.validation.PasswordValidationFactory" %>   

<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/labdev-view" prefix="app" %>
<%@ taglib uri="/tags/sourceforge-ajax" prefix="ajax"%>

<bean:define id="formName" value='<%= (String)request.getAttribute(IActionConstants.FORM_NAME) %>' />

<script language="JavaScript1.2">
    function validateForm(form) {
        return validateLoginChangePasswordForm(form);
    }

</script>

<table width="100%">
<tr>
    <td width="50%" valign="top">
    <table width="95%">    
    <tr>
        <td width="20%">&nbsp;</td>	
        <td colspan="2"><%=PasswordValidationFactory.getPasswordValidator().getInstructions() %></td>
        <td width="20%">&nbsp;</td>
    </tr>
    </table> 
    <br>
    <table width="95%">             
    <tr>
        <td width="20%">&nbsp;</td>	
        <td width="10%" align="right" noWrap><bean:message key="login.msg.userName"/>:</td>
        <td colspan="2" align="left">
            <html:text name="<%=formName%>" property="loginName"/>
        </td>
    </tr>   
     <tr>
        <td width="20%">&nbsp;</td>
        <td width="10%" noWrap><bean:message key="login.msg.password.current"/>:</td>
        <td colspan="2" align="left">
            <html:password name="<%=formName%>" property="password"/>
        </td>
     </tr>
     <tr>
        <td colspan="4">&nbsp;</td>
     </tr>
     <tr>
        <td width="20%">&nbsp;</td>
        <td width="10%" align="right" noWrap><bean:message key="login.msg.password.new"/>:</td>
        <td colspan="2" align="left">
            <html:password name="<%=formName%>" property="newPassword"/>
        </td> 
     </tr>
     <tr>
        <td width="20%">&nbsp;</td>
        <td width="10%" align="right" noWrap><bean:message key="login.msg.password.new.again"/>:</td>
        <td colspan="2" align="left">
            <html:password name="<%=formName%>" property="confirmPassword"/>
        </td>  
     </tr>                    
    </table>    
    </td>
</tr>
</table>       

<app:javascript formName="loginChangePasswordForm"/>


