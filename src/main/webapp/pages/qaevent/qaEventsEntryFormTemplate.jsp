<!DOCTYPE html>
<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants,
			us.mn.state.health.lims.common.services.LocalizationService,
			us.mn.state.health.lims.common.util.ConfigurationProperties,
			org.owasp.encoder.Encode,
			us.mn.state.health.lims.common.util.Versioning" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<html:html>
<%--bugzilla 2053, 2501, 2504, 2502--%>
<bean:define id="formName" value='<%= (String)request.getAttribute(IActionConstants.FORM_NAME) %>' />
<bean:define id="viewModeParam" value='<%=IActionConstants.QAEVENTS_ENTRY_PARAM_VIEW_MODE%>'/>
<bean:define id="fullScreen" value='<%=IActionConstants.QAEVENTS_ENTRY_FULL_SCREEN_VIEW%>'/>
<bean:define id="normal" value='<%=IActionConstants.QAEVENTS_ENTRY_NORMAL_VIEW%>'/>
<bean:define id="fullScreenSection" value='<%=IActionConstants.QAEVENTS_ENTRY_FULL_SCREEN_VIEW_SECTION%>'/>
<bean:define id="sampleSection" value='<%=IActionConstants.QAEVENTS_ENTRY_FULL_SCREEN_VIEW_SAMPLE_SECTION%>'/>
<bean:define id="testSection" value='<%=IActionConstants.QAEVENTS_ENTRY_FULL_SCREEN_VIEW_TEST_SECTION%>'/>

<%!
String path = "";
String basePath = "";
String viewMode = "";
String fullScreenSection = "";
%>
<%
path = request.getContextPath();
basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";


String form = (String)request.getAttribute(IActionConstants.FORM_NAME);

if (form == null) {
	form = "n/a";
}

  int startingRecNo = 1;
  
  if (request.getAttribute("startingRecNo") != null) {
       startingRecNo = Integer.parseInt((String)request.getAttribute("startingRecNo"));
  }
  
   request.setAttribute("ctx", request.getContextPath()); 
   
viewMode = normal;
if (request.getAttribute(viewModeParam) != null) {
   viewMode = (String)request.getAttribute(viewModeParam);
}

fullScreenSection = sampleSection;
if (request.getAttribute(fullScreenSection) != null) {
   fullScreenSection = (String)request.getAttribute(fullScreenSection);
}  

%>

<head>
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/openElisCore.css?ver=<%= Versioning.getBuildNumber() %>" />
<script type="text/javascript" src="<%=basePath%>scripts/jquery-1.8.0.min.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script type="text/javascript" src="<%=basePath%>scripts/jquery.dataTables.min.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script type="text/javascript" src="<%=basePath%>scripts/bootstrap.min.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script language="JavaScript1.2" src="<%=basePath%>scripts/utilities.jsp"></script>
<script type="text/javascript" src="<%=basePath%>scripts/prototype-1.5.1.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script type="text/javascript" src="<%=basePath%>scripts/scriptaculous.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script type="text/javascript" src="<%=basePath%>scripts/overlibmws.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script type="text/javascript" src="<%=basePath%>scripts/ajaxtags-1.2.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script type="text/javascript" src="<%=basePath%>scripts/Tooltip-0.6.0.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script type="text/javascript" src="<%=basePath%>scripts/lightbox.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script language="JavaScript1.2">


function setAction(form, action, validate, parameters) {
   
    //alert("Iam in setAction " + form.name + " " + form.action);
   //for (var i = 0; i < form.elements.length; i++) {
    
      //alert("This is a form element " + form.elements[i].name + " " + form.elements[i].value);
       
    //}
    
    var sessionid = getSessionFromURL(form.action);
	var context = '<%= request.getContextPath() %>';
	var formName = form.name; 
	//alert("form name " + formName);
	var parsedFormName = formName.substring(1, formName.length - 4);
	parsedFormName = formName.substring(0,1).toUpperCase() + parsedFormName;
    //alert("parsedFormName " + parsedFormName);
    
    var idParameter = '<%= Encode.forJavaScript((String)request.getParameter("ID")) %>';
    var startingRecNoParameter = '<%= Encode.forJavaScript((String)request.getParameter("startingRecNo")) %>';
    //alert("This is idParameter " + idParameter);   
    if (!idParameter) {
       idParameter = '0';
    }
    
    if (!startingRecNoParameter) {
       startingRecNoParameter = '1';
    }
       
    if (parameters != '') {
	   parameters = parameters + idParameter;
	} else {
	   parameters = parameters + "?ID=" + idParameter;
	}
    parameters = parameters + "&startingRecNo=" + startingRecNoParameter;
	
	
	form.action = context + '/' + action + parsedFormName + ".do"  + sessionid + parameters ;
	form.validateDocument = new Object();
	form.validateDocument.value = validate;
	//alert("Going to validatedAnDsubmitForm this is action " + form.action);
	validateAndSubmitForm(form);
	
}

</script>
<%
	if (request.getAttribute("cache") != null && request.getAttribute("cache").toString().equals("false")) 
	{
%>	
		<meta http-equiv="Cache-Control" content="no-cache, no-store, proxy-revalidate, must-revalidate"/> <%-- HTTP 1.1 --%>
		<meta http-equiv="Pragma" content="no-cache"/> <%-- HTTP 1.0 --%>
		<meta http-equiv="Expires" content="0"/> 
<%
	}
%>
	
	<title>
		<logic:notEmpty name="<%=IActionConstants.PAGE_TITLE_KEY%>">
			<bean:write name="<%=IActionConstants.PAGE_TITLE_KEY%>" scope='request' />
		</logic:notEmpty>
		<logic:empty name="<%=IActionConstants.PAGE_TITLE_KEY%>">
			<%= LocalizationService.getLocalizedValueById( ConfigurationProperties.getInstance().getPropertyValue( ConfigurationProperties.Property.BANNER_TEXT ) )%>
		</logic:empty>	
	</title>
	<tiles:insert attribute="banner"/>
    <tiles:insert attribute="login"/>
</head>


<%--html:errors/--%>

<%--bugzilla 1400--%><%--bugzilla 1664 check_width()--%>
<body onLoad="focusOnFirstInputField();check_width();onLoad()" >

<%--action is set in BaseAction--%>

<!-- for optimistic locking-->
<html:hidden property="lastupdated" name="<%=formName%>" />
   <div id="parent">
	<table cellpadding="0" cellspacing="1" width="100%">
			<tr>
				<td>
					<tiles:insert attribute="error"/>
				</td>
			</tr>
			 <% if (request.getAttribute(IActionConstants.ACTION_KEY) != null) { %>
                <form name='<%=(String)request.getAttribute(IActionConstants.FORM_NAME) %>' action='<%=(String)request.getAttribute(IActionConstants.ACTION_KEY) %>' onSubmit="return submitForm(this);" method="POST">
             <% } %>
			<tr>
				<td>
					<tiles:insert attribute="header"/>
				</td>
			</tr>
            <tr>
				<td>
					<tiles:insert attribute="preSelectionHeader"/>
				</td>
			</tr>
			<tr>
				<td>
		        	<tiles:insert attribute="samplebody"/>
				</td>
			</tr>
			<tr>
				<td>
					<tiles:insert attribute="samplefooter"/>
				</td>
			</tr>
			<tr>
				<td>
		        	<tiles:insert attribute="testbody"/>
				</td>
			</tr>
			<tr>
				<td>
					<tiles:insert attribute="testfooter"/>
				</td>
			</tr>
			<% if (request.getAttribute(IActionConstants.ACTION_KEY) != null) { %>
                 </form>
            <% } %>

	</table>
	</div>

</body>
<%--bugzilla 2502 full screen mode toggle between sample and tests--%>
<script>
  var fullScreenSection = document.getElementById("fullScreenSection").value;
  var sampleSection = "<%=sampleSection%>";
  var testSection = "<%=testSection%>";
  var viewMode = "<%=viewMode%>";
  var sampleDiv = document.getElementById("sampleDiv");
  var testDiv = document.getElementById("testDiv");
  var fullScreen = "<%=fullScreen%>";
  
  if (viewMode == fullScreen) {
    if (fullScreenSection == sampleSection) {
       sampleDiv.style.height = "680px";
    } else {
       testDiv.style.height = "680px";
    }
  } 
</script>


</html:html>

