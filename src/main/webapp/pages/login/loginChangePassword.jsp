<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants,
			us.mn.state.health.lims.common.provider.validation.PasswordValidationFactory" %>   

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" uri="/tags/labdev-view" %>
<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

<script>
    function validateForm(form) {
    	return true;
      // return validateLoginChangePasswordForm(form);
    }

</script>
<form:form name='${form.formName}' action='${form.formAction}' modelAttribute="form" onSubmit="return submitForm(this);" method="POST">

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
        <td width="10%" align="right" noWrap><spring:message code="login.msg.userName"/>:</td>
        <td colspan="2" align="left">
            <form:input path="loginName"/>
        </td>
    </tr>   
     <tr>
        <td width="20%">&nbsp;</td>
        <td width="10%" noWrap><spring:message code="login.msg.password.current"/>:</td>
        <td colspan="2" align="left">
            <form:password path="password"/> 
        </td>
     </tr>
     <tr>
        <td colspan="4">&nbsp;</td>
     </tr>
     <tr>
        <td width="20%">&nbsp;</td>
        <td width="10%" align="right" noWrap><spring:message code="login.msg.password.new"/>:</td>
        <td colspan="2" align="left">
            <form:password path="newPassword"/>
        </td> 
     </tr>
     <tr>
        <td width="20%">&nbsp;</td>
        <td width="10%" align="right" noWrap><spring:message code="login.msg.password.new.again"/>:</td>
        <td colspan="2" align="left">
            <form:password path="confirmPassword"/>
        </td>  
     </tr>                    
    </table>    
    </td>
</tr>
</table>       
</form:form>

<%-- <app:javascript formName="loginChangePasswordForm"/> --%>


