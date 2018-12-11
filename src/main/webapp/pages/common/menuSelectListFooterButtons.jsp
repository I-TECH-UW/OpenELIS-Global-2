<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants"
%>

<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/labdev-view" prefix="app" %>
<%--bugzilla 1908 changed some disabled values for Vietnam tomcat/linux--%>


<center>
<table border="0" cellpadding="0" cellspacing="0">
	<tbody valign="middle">
		<tr>
		<% 	
		    String previousDisabled = "false";
            String nextDisabled = "false"; 
            if (request.getAttribute(IActionConstants.PREVIOUS_DISABLED) != null) {
               previousDisabled = (String)request.getAttribute(IActionConstants.PREVIOUS_DISABLED);
            }
            if (request.getAttribute(IActionConstants.NEXT_DISABLED) != null) {
               nextDisabled = (String)request.getAttribute(IActionConstants.NEXT_DISABLED);
            }
 
        %>
	   <td >
  			<html:button onclick="setMenuAction(this, window.document.forms[0], '', 'yes', '?paging=1');return false;" property="previous" disabled="<%=Boolean.valueOf(previousDisabled).booleanValue()%>">
  			   <bean:message key="label.button.previous"/>
  			</html:button>
	   </td>
	   <td>
	   &nbsp;
	   </td>
	   <td>
  			<html:button onclick="setMenuAction(this, window.document.forms[0], '', 'yes', '?paging=2');return false;" property="next"  disabled="<%=Boolean.valueOf(nextDisabled).booleanValue()%>">
  			   <bean:message key="label.button.next"/>
  			</html:button>
	   </td>
	   <%--td removed 02/27/2006 not needed>
	   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	   </td>
	  <td>
  			<html:button onclick="setMenuAction(this, window.document.forms[0], 'Cancel', 'no', '');" property="cancel" >
  			   <bean:message key="label.button.cancel"/>
  			</html:button>
	    </td>
	    </tr--%>
	 </tbody>
</table>