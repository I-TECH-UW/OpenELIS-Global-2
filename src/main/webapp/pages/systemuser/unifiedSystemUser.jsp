<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants,
			us.mn.state.health.lims.common.provider.validation.PasswordValidationFactory,
		    us.mn.state.health.lims.common.util.Versioning,
			us.mn.state.health.lims.role.action.bean.DisplayRole,
			us.mn.state.health.lims.common.util.StringUtil" %>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" uri="/tags/labdev-view" %>
<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

 

<%!

String allowEdits = "true";
String basePath = "";
String tab = "&nbsp;&nbsp;&nbsp;&nbsp";
String currentTab = "";
%>

<%
if (request.getAttribute(IActionConstants.ALLOW_EDITS_KEY) != null) {
 allowEdits = (String)request.getAttribute(IActionConstants.ALLOW_EDITS_KEY);

String path = request.getContextPath();
basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";

}

%>

<script type="text/javascript" src="<%=basePath%>scripts/utilities.js?ver=<%= Versioning.getBuildNumber() %>" ></script>


<script>

$jq(document).ready( function() {
	var input = $jq( ":input" );
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
 		alert( '<%= StringUtil.getMessageForKey("errors.password.match")%>');
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
	if( typeof(showSuccessMessage) != 'undefinded' ){
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

</script>
<form:hidden path="systemUserId"/>
<form:hidden path="loginUserId"/>
<form:hidden path="systemUserLastupdated"/>
<table >
		<tr>
						<td class="label" >
							<spring:message code="login.login.name"/> <span class="requiredlabel">*</span>
						</td>
						<td >
							<app:text name="${form.formName}" property="userLoginName" styleClass='required' onchange="requiredFieldUpdated( this); makeDirty(); "/>
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
							<html:password name="${form.formName}" 
										   property="userPassword1" 
										   id="password1" 
										   styleClass='required'
										   onchange="handlePassword1(this);  makeDirty();"/>
						</td>
		</tr>
		<tr>
						<td class="label">
							<spring:message code="login.repeat.password" /> <span class="requiredlabel">*</span>
						</td>
						<td>
							<html:password name="${form.formName}" 
							               property="userPassword2"  
							               id="password2" 
							               styleClass='required'
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
							<app:text name="${form.formName}" 
							          property="userFirstName" 
							          styleClass='required' 
							          onchange="requiredFieldUpdated( this); makeDirty();"/>
						</td>
		</tr>
		<tr>
						<td class="label">
							<spring:message code="person.lastName" /> <span class="requiredlabel">*</span>
						</td>
						<td>
							<app:text name="${form.formName}" 
							          property="userLastName" 
							          styleClass='required' onchange="requiredFieldUpdated( this); makeDirty();"/>
						</td>
		</tr>
		<tr>
						<td class="label">
							<spring:message code="login.password.expired.date" />
						</td>
						<td>
							<app:text name="${form.formName}" 
							          property="expirationDate" 
							          onchange="makeDirty();" />
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
							<html:radio name="${form.formName}" property="accountLocked" value="Y" onchange="makeDirty();">Y</html:radio>
							<html:radio name="${form.formName}" property="accountLocked" value="N" onchange="makeDirty();">N</html:radio>
						</td>
		</tr>
		<tr>
						<td class="label">
							<spring:message code="login.account.disabled" />
						</td>
						<td>
							<html:radio name="${form.formName}" property="accountDisabled" value="Y" onchange="makeDirty();">Y</html:radio>
							<html:radio name="${form.formName}" property="accountDisabled" value="N" onchange="makeDirty();">N</html:radio>
						</td>
		</tr>
		<tr>
						<td class="label">
							<spring:message code="systemuser.isActive" />
						</td>
						<td>
							<html:radio name="${form.formName}" property="accountActive" value="Y" onchange="makeDirty();">Y</html:radio>
							<html:radio name="${form.formName}" property="accountActive" value="N" onchange="makeDirty();">N</html:radio>
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
	<logic:iterate  name="${form.formName}" property="roles" id="role" type="DisplayRole" >
	<tr>
	<td>
		<% currentTab = "";
		   while(role.getNestingLevel() > 0){ currentTab += tab; role.setNestingLevel( role.getNestingLevel() - 1);} %>
       <%=currentTab%>
		<html:multibox name="${form.formName}"
					   property="selectedRoles"
					   id='<%="role_" + role.getRoleId() %>'
					   onclick='<%="selectChildren(this, " + role.getChildrenID() + ");makeDirty();" %>' >
			<bean:write name="role" property="roleId" />
		</html:multibox>
		<bean:write name="role" property="roleName" />
	</td>
	</tr>
	</logic:iterate>
	<tr>
		<td>&nbsp;
			
		</td>
	</tr>

</table>


