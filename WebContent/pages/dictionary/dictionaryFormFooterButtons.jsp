<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants,
	        org.apache.struts.Globals,
            us.mn.state.health.lims.common.util.Versioning,
	        us.mn.state.health.lims.common.util.SystemConfiguration"
%>

<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/labdev-view" prefix="app" %>

<bean:define id="yes" value='<%= IActionConstants.YES %>' />
<bean:define id="no" value='<%= IActionConstants.NO %>' />
<bean:define id="idSeparator" value='<%= SystemConfiguration.getInstance().getDefaultIdSeparator() %>' />

<%!
String path = "";
String basePath = "";
String recordFrozenDisableEdits = "false";
%>
<%--bugzilla 2061-2063--%>
<%
         if (request.getAttribute(IActionConstants.RECORD_FROZEN_EDIT_DISABLED_KEY) != null) {
            recordFrozenDisableEdits = (String)request.getAttribute(IActionConstants.RECORD_FROZEN_EDIT_DISABLED_KEY);
         }




	       path = request.getContextPath();
           basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	
		    String previousDisabled = "false";
            String nextDisabled = "false"; 
            if (request.getAttribute(IActionConstants.PREVIOUS_DISABLED) != null) {
               previousDisabled = (String)request.getAttribute(IActionConstants.PREVIOUS_DISABLED);
            }
            if (request.getAttribute(IActionConstants.NEXT_DISABLED) != null) {
               nextDisabled = (String)request.getAttribute(IActionConstants.NEXT_DISABLED);
            }
            String saveDisabled = (String)request.getAttribute(IActionConstants.SAVE_DISABLED);
            if (saveDisabled == "false") {
               //if security check enables modification - now also check if button is disabled for other reason
               if (recordFrozenDisableEdits == "true") {
                saveDisabled = "true";
               }
            }
    
        %>
        
<script language="JavaScript1.2">
function confirmSaveForwardPopup(direction)
{
  var myWin = createSmallConfirmPopup( "", null , null );
  
   <% 
      
      out.println("var message = null;");
   
      java.util.Locale locale = (java.util.Locale)request.getSession().getAttribute(Globals.LOCALE_KEY);
      String message =
					us.mn.state.health.lims.common.util.resources.ResourceLocator.getInstance().getMessageResources().getMessage(
					locale,
                    "message.popup.confirm.saveandforward");
        
     out.println("message = '" + message +"';");
     
     String button1 = 	us.mn.state.health.lims.common.util.resources.ResourceLocator.getInstance().getMessageResources().getMessage(
					    locale,
                       "label.button.yes");
     String button2 = 	us.mn.state.health.lims.common.util.resources.ResourceLocator.getInstance().getMessageResources().getMessage(
					    locale,
                       "label.button.no");
     String button3 = 	us.mn.state.health.lims.common.util.resources.ResourceLocator.getInstance().getMessageResources().getMessage(
					    locale,
                       "label.button.cancel");
                       
     String title = 	us.mn.state.health.lims.common.util.resources.ResourceLocator.getInstance().getMessageResources().getMessage(
					    locale,
                       "title.popup.confirm.saveandforward");
                       
     String space = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
       		
     
 %> 

    var href = "<%=basePath%>css/openElisCore.css?ver=<%= Versioning.getBuildNumber() %>";
 
    var strHTML = ""; 
 
    strHTML = '<html><link rel="stylesheet" type="text/css" href="' + href + '" /><head><'; 
    strHTML += 'SCRIPT LANGUAGE="JavaScript">var tmr = 0;function fnHandleFocus(){if(window.opener.document.hasFocus()){window.focus();clearInterval(tmr);tmr = 0;}else{if(tmr == 0)tmr = setInterval("fnHandleFocus()", 500);}}</SCRIPT'; 
    strHTML += '><'; 
    strHTML += 'SCRIPT LANGUAGE="javascript" >'
    strHTML += 'var imp= null; function impor(){imp="norefresh";} ';
    strHTML += ' function fcl(){  if(imp!="norefresh") {  window.opener.reFreshCurWindow(); }}';
    
    strHTML += '  function goToNextActionSave(){ ';
    strHTML += ' var reqParms = "?direction=next&ID="; ';
    strHTML += ' window.opener.setAction(window.opener.document.forms[0], "UpdateNextPrevious", "yes", reqParms);self.close();} ';
    strHTML += '  function goToPreviousActionSave(){ ';
    strHTML += ' var reqParms = "?direction=previous&ID="; ';
    strHTML += ' window.opener.setAction(window.opener.document.forms[0], "UpdateNextPrevious", "yes", reqParms);self.close();} ';
    
    strHTML += '  function goToNextActionNoSave(){ ';
    strHTML += ' var reqParms = "?direction=next&ID="; ';
    strHTML += ' window.opener.setAction(window.opener.document.forms[0], "NextPrevious", "no", reqParms);self.close();} ';
    strHTML += '  function goToPreviousActionNoSave(){ ';
    strHTML += ' var reqParms = "?direction=previous&ID="; ';
    strHTML += ' window.opener.setAction(window.opener.document.forms[0], "NextPrevious", "no", reqParms);self.close();} ';
    
    strHTML += ' setTimeout("impor()",359999);</SCRIPT'; 
    strHTML += '><title>' + "<%=title%>" + '</title></head>'; 
    strHTML += '<body onBlur="fnHandleFocus();" onLoad="fnHandleFocus();" ><form name="confirmSaveIt" method="get" action=""><div id="popupBody"><table><tr><td class="popuplistdata">';
    strHTML += message;
    if (direction == 'next') {
     strHTML += '<br><center><input type="button"  name="save" value="' + "<%=button1%>" + '" onClick="goToNextActionSave();" />';
     strHTML += "<%=space%>";
     strHTML += '<input type="button"  name="save" value="' + "<%=button2%>" + '" onClick="goToNextActionNoSave();"/>';
    } else if (direction == 'previous') {
     strHTML += '<br><center><input type="button"  name="save" value="' + "<%=button1%>" + '" onClick="goToPreviousActionSave();" />';
     strHTML += "<%=space%>";
     strHTML += '<input type="button"  name="save" value="' + "<%=button2%>" + '" onClick="goToPreviousActionNoSave();"/>';
    }
    strHTML += "<%=space%>";
    strHTML += '<input type="button"  name="cancel" value="' + "<%=button3%>" + '" onClick="self.close();" /></center></div>';
    strHTML += '</td></tr></table></form></body></html>'; 

     myWin.document.write(strHTML); 

     myWin.window.document.close(); 

      setTimeout ('myWin.close()', 360000); 
}

function setDirtyFormFields(form) {

  
  var dirtyFormFields = "";
  for (var i = 0; i < form.elements.length; i++) {
     if (isFormFieldDirty(form.elements[i])) {
       if (i != 0) {
         dirtyFormFields += '<%=idSeparator%>';
       }
 
       dirtyFormFields += form.elements[i].name;
     }
  }
  
  form.dirtyFormFields.value = dirtyFormFields;
}

function saveIt(form) {
  setDirtyFormFields(form);
  setAction(form, 'Update', 'yes', '?ID=');
}

function previousAction(form, ignoreFields) {
  if (isDirty(form, ignoreFields)) {
     confirmSaveForwardPopup('previous');
  } else {
     setDirtyFormFields(form);
     setAction(form, 'NextPrevious', 'no', '?direction=previous&ID=');
  }
}


function nextAction(form, ignoreFields) {
  if (isDirty(form, ignoreFields)) {
      //popup to give user option to save, don't save AND go to next, cancel
      confirmSaveForwardPopup('next');
  } else {
      setDirtyFormFields(form);
      setAction(form, 'NextPrevious', 'no', '?direction=next&ID=');
  }
}
</script>
<center>
<table border="0" cellpadding="0" cellspacing="0">
	<tbody valign="middle">
		<tr>
	      	<td>
  			<html:button onclick="if(checkClicked()) 
							 {
							 	return false;
							 }
							 else {
							  saveIt(window.document.forms[0]);
							 }" property="save" disabled="<%=Boolean.valueOf(saveDisabled).booleanValue()%>">
  			   <bean:message key="label.button.save"/>
  			</html:button>
  	    </td>
        
		<td>&nbsp;</td>
		<td>
  			<html:button onclick="setAction(window.document.forms[0], 'Cancel', 'no', '');"  property="cancel" >
  			   <bean:message key="label.button.exit"/>
  			</html:button>
	    </td>
   		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
   		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
  		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
  		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
   		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
  		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
 	    <td>
  			<html:button onclick="previousAction(window.document.forms[0], '');" property="previous" disabled="<%=Boolean.valueOf(previousDisabled).booleanValue()%>">
  			   <bean:message key="label.button.previous"/>
  			</html:button>
	    </td>
     	<td>&nbsp;</td>
 	    <td>
  			<html:button onclick="nextAction(window.document.forms[0], '');"  property="next" disabled="<%=Boolean.valueOf(nextDisabled).booleanValue()%>">
  			   <bean:message key="label.button.next"/>
  			</html:button>
	    </td>
	    </tr>
	 </tbody>
</table>
</center>