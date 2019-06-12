<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" uri="/tags/labdev-view" %>
<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>


<%@ page import="us.mn.state.health.lims.common.action.IActionConstants,
				spring.service.localization.LocalizationServiceImpl,
				 us.mn.state.health.lims.common.util.ConfigurationProperties,
				 us.mn.state.health.lims.common.util.ConfigurationProperties.Property,
				 spring.mine.internationalization.MessageUtil,
				 us.mn.state.health.lims.common.util.Versioning,
                 us.mn.state.health.lims.login.valueholder.UserSessionData,
				 us.mn.state.health.lims.menu.util.MenuUtil,
				 spring.mine.common.form.BaseForm,
				 org.owasp.encoder.Encode"%>

<%!String path = "";
	String basePath = "";
	String menuItems[];
	boolean languageSwitch = false;
%>
<%
	path = request.getContextPath();
	basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
	+ "/";

	languageSwitch = "true".equals(ConfigurationProperties.getInstance().getPropertyValue(Property.languageSwitch));
%>

<c:set var="formName" value="${form.formName}"/>


<script>
function /*void*/ setLanguage( language ){

	//this weirdness is because we want the language to which we are changing, not the one we are in
	if( language == 'en_US'){
	    update = confirm("Changing the language will affect all logged in users ");
	} else if( language == 'fr_FR' ){
		update = confirm( "Modification de la langue affectera tous les utilisateurs enregistrés");
	}
	
	if( update ){
		window.location.href = "LoginPage.do?lang=" + language;
	}
}


//Note this is hardcoded for haiti clinical.  Message resources would be a good way to get both language and context
function displayHelp(){

    var url = '<%=basePath%>' + 'documentation/' + '<%= MessageUtil.getContextualMessage("documentation") %>';

	var	newwindow=window.open( url,'name','height=1000,width=850, menuBar=yes');

	if (window.focus) {newwindow.focus()}
}

</script>

<!-- New additions below by mark47 -->
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/menu.css?ver=<%= Versioning.getBuildNumber() %>" />

<!-- Begin new menu -->

<script type="text/javascript" src="<%=basePath%>scripts/menu/hoverIntent.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script type="text/javascript" src="<%=basePath%>scripts/menu/superfish.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script type="text/javascript" src="<%=basePath%>scripts/menu/supersubs.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script type="text/javascript" src="<%=basePath%>scripts/menu/supposition.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script type="text/javascript">
	// initialize superfish menu plugin. supposition added to allow sub-menus on left when window size is too small.
	jQuery(function(){
		jQuery('ul.nav-menu').supersubs({
			minWidth: 9,
			maxWidth: 100,
			extraWidth: 1
		}).superfish({
			delay: 400,
			speed: 0
		}).supposition();
	});
</script>

<div id="header">
  	<div id="oe-logo" style="width: 89px" onclick="navigateToHomePage();"><img src="images/openelis_logo.png" title="OpenELIS" alt="OpenELIS" /></div>
	<div style="margin-left: 94px">
 		<div style="display: block">
			<%
				UserSessionData usd = null;
				if (request.getSession().getAttribute(IActionConstants.USER_SESSION_DATA) != null) {
					usd = (UserSessionData) request.getSession().getAttribute(IActionConstants.USER_SESSION_DATA);
			%>
			<spring:url value="/Logout.do" var="loginurl"/>
			<form id="logout-form" method="post" action="${loginurl}">
			<div id="user-info"><div><%=usd.getElisUserName()%> - 
			<input type="submit" value="<spring:message code="homePage.menu.logOut.toolTip"/>" class="btn-link"/>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
			</div></div></form>
			<%
				}
			%>
  	  		<div id="oe-title" onclick="navigateToHomePage();"><%= Encode.forHtmlContent(LocalizationServiceImpl.getLocalizedValueById( ConfigurationProperties.getInstance().getPropertyValue( Property.BANNER_TEXT ) )) %></div>
  		</div>  
  		<div id="oe-version" style="display: block">
    		<div id="appVersion">
    		<spring:message code="ellis.version" />:&nbsp;
		    <%= ConfigurationProperties.getInstance().getPropertyValue(Property.releaseNumber)%>&nbsp;&nbsp;&nbsp;
		    </div>
    
		    <% if("true".equals(ConfigurationProperties.getInstance().getPropertyValueLowerCase(Property.TrainingInstallation))){ %>
		      <div id="training-alert"><span title="training.note">training.note</span></div>
		    <% } %>
  		</div>
<%	
	if (usd != null) {
%>

<%= MenuUtil.getMenuAsHTML(path) %>

<%
		}
%>

	</div>
</div> <!-- Closes id=header -->


<% if( languageSwitch && "loginForm".equals((String) pageContext.getAttribute("formName")) ){ %>
  <div id="language-chooser"><a href="#" onclick="setLanguage('fr_FR')">Français</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="setLanguage('en_US')">English</a></div>
<% } %>

