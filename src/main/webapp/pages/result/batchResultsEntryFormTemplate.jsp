
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

<bean:define id="formName" value='<%= (String)request.getAttribute(IActionConstants.FORM_NAME) %>' />
<%!
String path = "";
String basePath = "";
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

%>

<head>

<style type="text/css">
BODY, TD, P
{
	font-family: Verdana;
	font-size: 10pt;
}
H1
{
	font-family: Verdana;
	font-size: 14pt;
}
TABLE
{
	border-collapse: collapse;
}
TD.bre, TH.bre
{
	border-top: solid 1px;
	border-bottom: solid 1px;
	white-space: nowrap;
	padding-left: 10px;
	padding-right: 10px;
	padding-top: 2px;
	padding-bottom: 2px;
	text-align: left;
}
#outerDiv
{
	position: relative;
}
#innerDiv
{
	overflow: auto;
}
#innerDiv TD
{
	white-space: nowrap;
}
</style>

<link rel="stylesheet" type="text/css" href="<%=basePath%>css/openElisCore.css?ver=<%= Versioning.getBuildNumber() %>" />

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
  
    //THIS IS SPECIAL LOGIC TO WORK AROUND PROBLEM WITH 2 DIVS FOR SCROLLBARS -horizontal/vertical datagrid - WHERE SELECT DROPDOWN CHANGES
    //CAN NOT BE SAVED BECAUSE OF cloneNode() duplication of form elements into divs 
    //THE SOLUTION = DISABLE duplicate (cloned for divs) form elements that do not have the correct selected values
    //GO THROUGH LIST OF FORM ELEMENTS IN REVERSE ORDER - the correct elements to be saved/not disabled
    //                                                    are at the end of the form.elements array in this case
    var saved = '';
    var firstTime = true;
    var startDisabling = false;
    for (var i = form.elements.length -1; i >= 0; i--) {
    
      //alert("This is the form " + form.name + " a form element " + form.elements[i].name + " " + form.elements[i].value);
      //found the repeated form elements from 2 divs - now start disabling these elements that are causing problems with saving the form with updates in select dropdowns
      if (saved == form.elements[i].name && !firstTime) {
         startDisabling = true;
       
      }
      //this is to work around problem with submitting form using divs and cloneNode (only problem are select dropdowns within divs)
      if (startDisabling) {
          var aName = form.elements[i].name;
          if (aName.indexOf("sample_TestAnalytes[") >= 0) {
              form.elements[i].disabled = true;
          }
      } 
      if (firstTime) {
        var aName = form.elements[i].name;
        if (aName.indexOf("sample_TestAnalytes[") >= 0) {
          saved = aName;
          firstTime = false;
        }
      }
      

	} 
	//END SOLUTION

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
	//alert("Going to validatedAnDsubmitForm this is action " + form.name + " " + form.action);
	validateAndSubmitForm(form);
	
}

var divContent = null;
var divHeaderRow = null;
var divHeaderColumn = null;
var divHeaderRowColumn = null;
var headerRowFirstColumn = null;
var x;
var y;
var horizontal = false;
var vertical = false;


// Copy table to top and to left
function CreateScrollHeader(content, scrollHorizontal, scrollVertical)
{

    horizontal = scrollHorizontal;
	vertical = scrollVertical;
	
	if (content != null)
	{
		
	
        divContent = content;
     
		var headerRow = divContent.childNodes[0].childNodes[0].childNodes[0];
		
		x = divContent.childNodes[0].offsetWidth;
		y = divContent.childNodes[0].offsetHeight;	
		
       	divHeaderRow = divContent.cloneNode(true);
  		
		if (horizontal)
		{
			divHeaderRow.style.height = headerRow.offsetHeight;
			divHeaderRow.style.overflow = "hidden";
			
			divContent.parentNode.insertBefore(divHeaderRow, divContent);
			divContent.childNodes[0].style.position = "absolute";
			divContent.childNodes[0].style.top = "-" + headerRow.offsetHeight;
			
			y = y - headerRow.offsetHeight;
		}
		
		divHeaderRowColumn = divHeaderRow.cloneNode(true);
			
		headerRowFirstColumn = headerRow.childNodes[0];
 		divHeaderColumn = divContent.cloneNode(true);
 
		divContent.style.position = "relative";

		if (vertical)
		{
			divContent.parentNode.insertBefore(divHeaderColumn, divContent);
			divContent.style.left = headerRowFirstColumn.offsetWidth;
			
			divContent.childNodes[0].style.position = "absolute";
			divContent.childNodes[0].style.left = "-" + headerRowFirstColumn.offsetWidth;
		}
		else
		{
			divContent.style.left = 0;
		}

						
		if (vertical)
		{
			divHeaderColumn.style.width = headerRowFirstColumn.offsetWidth;
			divHeaderColumn.style.overflow = "hidden";
			divHeaderColumn.style.zIndex = "99";
			
			divHeaderColumn.style.position = "absolute";
			divHeaderColumn.style.left = "0";
			addScrollSynchronization(divHeaderColumn, divContent, "vertical");
			x = x - headerRowFirstColumn.offsetWidth;
		}
		
		if (horizontal)
		{
			if (vertical)
			{
				divContent.parentNode.insertBefore(divHeaderRowColumn, divContent);
			}
			divHeaderRowColumn.style.position = "absolute";
			divHeaderRowColumn.style.left = "0";
			divHeaderRowColumn.style.top = "0";
			divHeaderRowColumn.style.width = headerRowFirstColumn.offsetWidth;
			divHeaderRowColumn.overflow = "hidden";
			divHeaderRowColumn.style.zIndex = "100";
			divHeaderRowColumn.style.backgroundColor = "#ffffff";
			
		}
		
		if (horizontal)
		{
			addScrollSynchronization(divHeaderRow, divContent, "horizontal");
		}
		
		if (horizontal || vertical)
		{
			window.onresize = ResizeScrollArea;
			ResizeScrollArea();
		}
		
	}
}


// Resize scroll area to window size.
function ResizeScrollArea()
{
//commented this out to avoid vertical scrollbar on main IE window 
	//var height = document.documentElement.clientHeight - 120;
	var height = document.documentElement.clientHeight - 430;

	if (!vertical)
	{
		height -= divHeaderRow.offsetHeight;
		
	}
	

//commented this out to avoid horizontal scrollbar on main IE window
	//var width = document.documentElement.clientWidth - 50;
	var width = document.documentElement.clientWidth - 100;

	
	if (!horizontal)
	{
		width -= divHeaderColumn.offsetWidth;
	}

	
	var headerRowsWidth = 0;

	divContent.childNodes[0].style.width = x;
	divContent.childNodes[0].style.height = y;
		
	if (divHeaderRowColumn != null)
	{
		headerRowsWidth = divHeaderRowColumn.offsetWidth;
	}

    
	// width
	if (divContent.childNodes[0].offsetWidth > width)
	{
		divContent.style.width = Math.max(width - headerRowsWidth, 0);
		divContent.style.overflowX = "scroll";
		divContent.style.overflowY = "auto";
	}
	else
	{
		divContent.style.width = x;
		divContent.style.overflowX = "auto";
		divContent.style.overflowY = "auto";
	}

	if (divHeaderRow != null)
	{
		divHeaderRow.style.width = divContent.offsetWidth + headerRowsWidth;
	}

	// height
	if (divContent.childNodes[0].offsetHeight > height)
	{
		divContent.style.height = Math.max(height, 80);
		divContent.style.overflowY = "scroll";
	}
	else
	{
		divContent.style.height = y;
		divContent.style.overflowY = "hidden";
	}
	if (divHeaderColumn != null)
	{
		divHeaderColumn.style.height = divContent.offsetHeight;
	}

	// check scrollbars
	if (divContent.style.overflowY == "scroll")
	{
		divContent.style.width = divContent.offsetWidth + 17;
	}
	if (divContent.style.overflowX == "scroll")
	{
		divContent.style.height = divContent.offsetHeight + 17;
	}

    //alert("setting parentElement width to " + (divContent.offsetWidth + headerRowsWidth));
	divContent.parentElement.style.width = divContent.offsetWidth + headerRowsWidth;
	
}

// ********************************************************************************
// Synchronize div elements when scrolling 
// from http://webfx.eae.net/dhtml/syncscroll/syncscroll.html
// ********************************************************************************
// This is a function that returns a function that is used
// in the event listener
function getOnScrollFunction(oElement) {
	return function () {
		if (oElement._scrollSyncDirection == "horizontal" || oElement._scrollSyncDirection == "both")
			oElement.scrollLeft = event.srcElement.scrollLeft;
		if (oElement._scrollSyncDirection == "vertical" || oElement._scrollSyncDirection == "both")
			oElement.scrollTop = event.srcElement.scrollTop;
	};

}

// This function adds scroll syncronization for the fromElement to the toElement
// this means that the fromElement will be updated when the toElement is scrolled
function addScrollSynchronization(fromElement, toElement, direction) {
	removeScrollSynchronization(fromElement);
	
	fromElement._syncScroll = getOnScrollFunction(fromElement);
	fromElement._scrollSyncDirection = direction;
	fromElement._syncTo = toElement;
	toElement.attachEvent("onscroll", fromElement._syncScroll);
}

// removes the scroll synchronization for an element
function removeScrollSynchronization(fromElement) {
	if (fromElement._syncTo != null)
		fromElement._syncTo.detachEvent("onscroll", fromElement._syncScroll);

	fromElement._syncTo = null;
	fromElement._syncScroll = null;
	fromElement._scrollSyncDirection = null;
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
			<%=LocalizationService.getLocalizedValueById( ConfigurationProperties.getInstance().getPropertyValue( ConfigurationProperties.Property.BANNER_TEXT ) )%>
		</logic:empty>	
	</title>
	<tiles:insert attribute="banner"/>
    <tiles:insert attribute="login"/>
</head>


<body onload="check_width();focusOnFirstInputField();onLoad()" >

<!-- for optimistic locking-->
<html:hidden property="lastupdated" name="<%=formName%>" />
	<table cellpadding="0" cellspacing="1" width="100%">
			<tr>
				<td class="bre">
					<tiles:insert attribute="error"/>
				</td>
			</tr>
			 <% if (request.getAttribute(IActionConstants.ACTION_KEY) != null) { %>
                <form name='<%=(String)request.getAttribute(IActionConstants.FORM_NAME) %>' action='<%=(String)request.getAttribute(IActionConstants.ACTION_KEY) %>' onsubmit="return submitForm(this);" method="post">
             <% } %>
			<tr>
				<td class="bre">
					<tiles:insert attribute="header"/>
				</td>
			</tr>
            <tr>
				<td class="bre">
					<tiles:insert attribute="preSelectionHeader"/>
				</td>
			</tr>
			<tr>
				<td class="bre">
		        	<tiles:insert attribute="body"/>
				</td>
			</tr>

			<tr>
				<td class="bre">
					<tiles:insert attribute="footer"/>
				</td>
			</tr>
			<% if (request.getAttribute(IActionConstants.ACTION_KEY) != null) { %>
                 </form>
            <% } %>

	</table>
<%--this is for datagrid--%>
<script language="JavaScript1.2">

               if (document.getElementById("innerDiv")) {
                  if (document.getElementById("innerDiv").childNodes[0]) {
                    if (document.getElementById("innerDiv").childNodes[0].childNodes[0]) {
                      if (document.getElementById("innerDiv").childNodes[0].childNodes[0].childNodes[0]) {
                         CreateScrollHeader(document.getElementById("innerDiv"), true, true); 
                       }
                 	 }
                 }
              }
</script>
</body>




</html:html>
