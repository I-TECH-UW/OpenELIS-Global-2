<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants,
			us.mn.state.health.lims.analyzerimport.action.beans.NamedAnalyzerTestMapping" %>

<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/labdev-view" prefix="app" %>

<bean:define id="formName" value='<%= (String)request.getAttribute(IActionConstants.FORM_NAME) %>' />

<table width="80%" border="2">
	<tr>
		<th>&nbsp;</th>
	   	<th><bean:message key="analyzer.label"/>&nbsp;-&nbsp;<bean:message key="analyzer.test.name"/> </th>
	   	<th> <bean:message key="analyzer.test.actual.name"/> </th>
	</tr>
	<logic:iterate id="analyzerTest" name="<%=formName%>" indexId="ctr" property="menuList" type="NamedAnalyzerTestMapping">
	  	<tr>
	   		<td class="textcontent">
	      		<html:multibox name="<%=formName%>" property="selectedIDs" onclick="output()">
	         		<bean:write name="analyzerTest" property="uniqueId" />
	      		</html:multibox>
			</td>
   	   		<td class="textcontent">
	    		<bean:write name="analyzerTest" property="analyzerName"/>&nbsp;-&nbsp;
	   	  		<bean:write name="analyzerTest" property="analyzerTestName"/>
	   		</td>
   	  	 	<td class="textcontent">
	   	  		<bean:write name="analyzerTest" property="actualTestName"/>
	   		</td>
     	</tr>
	</logic:iterate>
</table>
