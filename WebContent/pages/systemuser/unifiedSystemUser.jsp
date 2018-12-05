<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants,
			us.mn.state.health.lims.common.provider.validation.PasswordValidationFactory,
		    us.mn.state.health.lims.common.util.Versioning,
			us.mn.state.health.lims.role.action.bean.DisplayRole,
			us.mn.state.health.lims.common.util.StringUtil" %>

<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/labdev-view" prefix="app" %>

<bean:define id="formName" value='<%= (String)request.getAttribute(IActionConstants.FORM_NAME) %>' />

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


<script language="JavaScript1.2">

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
    return "<bean:message key="banner.menu.dataLossWarning"/>";
	}
	window.onbeforeunload = formWarning;
}

function /*void*/ requiredFieldUpdated( field){
	field.style.borderColor = field.value.blank() ? "red" : "";
}

</script>
<html:hidden name="<%=formName %>"  property="systemUserId"/>
<html:hidden name="<%=formName %>"  property="loginUserId"/>
<html:hidden name="<%=formName %>"  property="systemUserLastupdated"/>
<table >
		<tr>
						<td class="label" >
							<bean:message key="login.login.name"/> <span class="requiredlabel">*</span>
						</td>
						<td >
							<app:text name="<%=formName%>" property="userLoginName" styleClass='required' onchange="requiredFieldUpdated( this); makeDirty(); "/>
						</td>
		</tr>
		<tr>
			<td colspan="2">
				<%=PasswordValidationFactory.getPasswordValidator().getInstructions() %>
			</td>
		</tr>
		<tr>
						<td class="label">
							<bean:message key="login.password"/> <span class="requiredlabel">*</span>
						</td>
						<td>
							<html:password name="<%=formName%>" 
										   property="userPassword1" 
										   styleId="password1" 
										   styleClass='required'
										   onchange="handlePassword1(this);  makeDirty();"/>
						</td>
		</tr>
		<tr>
						<td class="label">
							<bean:message key="login.repeat.password" /> <span class="requiredlabel">*</span>
						</td>
						<td>
							<html:password name="<%=formName%>" 
							               property="userPassword2"  
							               styleId="password2" 
							               styleClass='required'
							               onchange="handlePassword2(this); makeDirty();" />
						</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
						<td class="label">
							<bean:message key="person.firstName" /> <span class="requiredlabel">*</span>
						</td>
						<td>
							<app:text name="<%=formName%>" 
							          property="userFirstName" 
							          styleClass='required' 
							          onchange="requiredFieldUpdated( this); makeDirty();"/>
						</td>
		</tr>
		<tr>
						<td class="label">
							<bean:message key="person.lastName" /> <span class="requiredlabel">*</span>
						</td>
						<td>
							<app:text name="<%=formName%>" 
							          property="userLastName" 
							          styleClass='required' onchange="requiredFieldUpdated( this); makeDirty();"/>
						</td>
		</tr>
		<tr>
						<td class="label">
							<bean:message key="login.password.expired.date" />
						</td>
						<td>
							<app:text name="<%=formName%>" 
							          property="expirationDate" 
							          onchange="makeDirty();" />
						</td>
		</tr>
		<tr>
						<td class="label">
							<bean:message key="login.timeout" />
						</td>
						<td>
							<html:text name="<%=formName%>" property="timeout" onchange="makeDirty();" />
						</td>
						<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
						<td class="label">
							<bean:message key="login.account.locked" />
						</td>
						<td>
							<html:radio name="<%=formName %>" property="accountLocked" value="Y" onchange="makeDirty();">Y</html:radio>
							<html:radio name="<%=formName %>" property="accountLocked" value="N" onchange="makeDirty();">N</html:radio>
						</td>
		</tr>
		<tr>
						<td class="label">
							<bean:message key="login.account.disabled" />
						</td>
						<td>
							<html:radio name="<%=formName %>" property="accountDisabled" value="Y" onchange="makeDirty();">Y</html:radio>
							<html:radio name="<%=formName %>" property="accountDisabled" value="N" onchange="makeDirty();">N</html:radio>
						</td>
		</tr>
		<tr>
						<td class="label">
							<bean:message key="systemuser.isActive" />
						</td>
						<td>
							<html:radio name="<%=formName %>" property="accountActive" value="Y" onchange="makeDirty();">Y</html:radio>
							<html:radio name="<%=formName %>" property="accountActive" value="N" onchange="makeDirty();">N</html:radio>
						</td>
		</tr><tr>
			<td>&nbsp;</td>
		</tr>
</table>
<hr/>
<table>
		<tr>
		<td class="label" width="50%">
			<bean:message key="systemuserrole.roles" />
		</td>
		</tr>
	<logic:iterate  name="<%=formName%>" property="roles" id="role" type="DisplayRole" >
	<tr>
	<td>
		<% currentTab = "";
		   while(role.getNestingLevel() > 0){ currentTab += tab; role.setNestingLevel( role.getNestingLevel() - 1);} %>
       <%=currentTab%>
		<html:multibox name="<%=formName %>"
					   property="selectedRoles"
					   styleId='<%="role_" + role.getRoleId() %>'
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


