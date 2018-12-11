<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants,
			us.mn.state.health.lims.analyzerimport.action.beans.NamedAnalyzerTestMapping" %>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" uri="/tags/labdev-view" %>
<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

 

<table width="80%" border="2">
	<tr>
		<th>&nbsp;</th>
	   	<th><spring:message code="analyzer.label"/>&nbsp;-&nbsp;<spring:message code="analyzer.test.name"/> </th>
	   	<th> <spring:message code="analyzer.test.actual.name"/> </th>
	</tr>
	<logic:iterate id="analyzerTest" name="${form.formName}" indexId="ctr" property="menuList" type="NamedAnalyzerTestMapping">
	  	<tr>
	   		<td class="textcontent">
	      		<html:multibox name="${form.formName}" property="selectedIDs" onclick="output()">
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
