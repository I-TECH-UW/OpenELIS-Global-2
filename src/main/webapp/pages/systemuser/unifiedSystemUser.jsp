<%@ page language="java"
	contentType="text/html; charset=UTF-8"
	import="org.openelisglobal.common.action.IActionConstants,
			org.openelisglobal.common.provider.validation.PasswordValidationFactory,
		    org.openelisglobal.common.util.Versioning,
			org.openelisglobal.role.action.bean.DisplayRole,
			org.openelisglobal.internationalization.MessageUtil" %>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

<script type="text/javascript" src="scripts/utilities.js?" ></script>


<script>

jQuery(document).ready( function() {
	var input = jQuery( ":input" );
	document.getElementsByName("save")[0].disabled = true;
	input.change( function( objEvent ){
		document.getElementsByName("save")[0].disabled = false;
	} );
});

function validateForm(form) {
 	 return checkRequiredElements( true );
 }
 
function /*boolean*/ checkRequiredElements( ){
 	var elements = $$(".required");
 	var ok = true;
 	
 	for( var i = 0; i < elements.length; ++i){
 		if( elements[i].value.blank() ){
 			elements[i].style.borderColor = "red";
 			ok = false;
 		}
 	}
 
 	if($("password1").value != $("password2").value ){
 		$("password1").style.borderColor = "red";
 		$("password2").style.borderColor = "red";

 		ok = false;
  	}
 	
 	return ok; 
}
 
function handlePassword1( password1 ){
	var password2 = $("password2");
	password2.value = "";
	password2.style.borderColor = "";
	password1.style.borderColor = "";
} 

function handlePassword2( password2 ){
 	var password1 = $("password1");
 	
 	if( !password1.value.blank() && !password2.value.blank() && password1.value != password2.value ){
 		password2.style.borderColor = "red";
		password1.style.borderColor = "red";
 		alert( '<%= MessageUtil.getMessage("errors.password.match")%>');
 	}else{
 		password2.style.borderColor = "";
		password1.style.borderColor = "";
 	}
}

function /*void*/ selectChildren(selection, childList ){
	if( childList ){
		isChecked = selection.checked;

		children = childList.split('_');

		for( i = 0; i < children.length; i++ ){
			$( "role_" + children[i] ).checked = isChecked;
		}

	}
}

function /*void*/ makeDirty(){
	dirty=true;
	if( typeof(showSuccessMessage) === 'function' ){
		showSuccessMessage(false); //refers to last save
	}

	// Adds warning when leaving page if content has been entered into makeDirty form fields
	function formWarning(){ 
    return "<spring:message code="banner.menu.dataLossWarning"/>";
	}
	window.onbeforeunload = formWarning;
}

function /*void*/ requiredFieldUpdated( field){
	field.style.borderColor = field.value.blank() ? "red" : "";
}

function mySaveAction() {
	window.onbeforeunload = null;
	document.getElementById("mainForm").submit();
}

</script>
<form:hidden path="systemUserId"/>
<form:hidden path="loginUserId"/>
<%-- <form:hidden path="systemUserLastupdated"/> --%>
<table >
		<tr>
						<td class="label" >
							<spring:message code="login.login.name"/> <span class="requiredlabel">*</span>
						</td>
						<td >
							<form:input path="userLoginName" cssClass='required' onchange="requiredFieldUpdated( this); makeDirty(); "/>
						</td>
		</tr>
		<tr>
			<td colspan="2">
				<%=PasswordValidationFactory.getPasswordValidator().getInstructions() %>
			</td>
		</tr>
		<tr>
						<td class="label">
							<spring:message code="login.password"/> <span class="requiredlabel">*</span>
						</td>
						<td>
							<form:password path="userPassword" 
										   id="password1" 
										   cssClass='required'
										   showPassword="true"
										   onchange="handlePassword1(this);  makeDirty();"/>
						</td>
		</tr>
		<tr>
						<td class="label">
							<spring:message code="login.repeat.password" /> <span class="requiredlabel">*</span>
						</td>
						<td>
							<form:password path="confirmPassword"  
							               id="password2" 
							               cssClass='required'
							               showPassword="true"
							               onchange="handlePassword2(this); makeDirty();" />
						</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
						<td class="label">
							<spring:message code="person.firstName" /> <span class="requiredlabel">*</span>
						</td>
						<td>
							<form:input path="userFirstName" 
							          cssClass='required' 
							          onchange="requiredFieldUpdated( this); makeDirty();"/>
						</td>
		</tr>
		<tr>
						<td class="label">
							<spring:message code="person.lastName" /> <span class="requiredlabel">*</span>
						</td>
						<td>
							<form:input path="userLastName" 
							          cssClass='required' onchange="requiredFieldUpdated( this); makeDirty();"/>
						</td>
		</tr>
		<tr>
						<td class="label">
							<spring:message code="login.password.expired.date" /> <span class="requiredlabel">*</span>
						</td>
						<td>

							<form:input path="expirationDate" 
							          cssClass='required' onchange="makeDirty();" />

						</td>
		</tr>
		<tr>
						<td class="label">
							<spring:message code="login.timeout" />
						</td>
						<td>
							<form:input path="timeout" onchange="makeDirty();" />
						</td>
						<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
						<td class="label">
							<spring:message code="login.account.locked" />
						</td>
						<td>
							<form:radiobutton path="accountLocked" value="Y" onchange="makeDirty();" label="Y" />
							<form:radiobutton path="accountLocked" value="N" onchange="makeDirty();" label="N" />
						</td>
		</tr>
		<tr>
						<td class="label">
							<spring:message code="login.account.disabled" />
						</td>
						<td>
							<form:radiobutton path="accountDisabled" value="Y" onchange="makeDirty();" label="Y" />
							<form:radiobutton path="accountDisabled" value="N" onchange="makeDirty();" label="N" />
						</td>
		</tr>
		<tr>
						<td class="label">
							<spring:message code="systemuser.isActive" />
						</td>
						<td>
							<form:radiobutton path="accountActive" value="Y" onchange="makeDirty();" label="Y" />
							<form:radiobutton path="accountActive" value="N" onchange="makeDirty();" label="N" />
						</td>
		</tr><tr>
			<td>&nbsp;</td>
		</tr>
</table>
<hr/>
<table>
		<tr>
		<td class="label" width="50%">
			<spring:message code="systemuserrole.roles" />
		</td>
		</tr>
	<c:forEach items="${form.roles}" var="role">
	<tr>
	<td>
       <c:forEach begin="0" end="${role.nestingLevel}">
      	&nbsp;&nbsp;&nbsp;&nbsp;
       </c:forEach>
       <form:checkbox path="selectedRoles" 
       				  id="role_${role.roleId}" 
       				  onclick="selectChildren(this, ${role.childrenID});makeDirty();"
       				  value="${role.roleId}"
       				  />
		<c:out value="${role.roleName}" />
	</td>
	</tr>
	</c:forEach>
	<tr>
		<td>&nbsp;
			
		</td>
	</tr>

</table>


