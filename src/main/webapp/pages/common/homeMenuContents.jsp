<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<div  id="IEWarning" style="display:none;background-color:#b0c4de"  ><b>
<spring:message code="banner.menu.ie.warning" text="banner.menu.ie.warning"/><br/>
<spring:message code="banner.menu.ie.instructions" text="banner.menu.ie.instructions"/></b>
</div>

<table cellpadding="30" align="center">
<tr>
	<td align="center" width="139" valign="top">
		<img src="images/mainSamples.jpg" /><br/>
		<h1 class="txtHeader"><spring:message code="banner.menu.sample" text="banner.menu.sample"/></h1>
	</td>
	<td align="center" width="139" valign="top">
		<img src="images/mainPatient.jpg" /><br/>
		<h1 class="txtHeader"><spring:message code="banner.menu.patient" text="banner.menu.patient"/></h1>
	</td>
	<td align="center" width="139" valign="top">
		<img src="images/mainResults.jpg" /><br/>
		<h1 class="txtHeader"><spring:message code="banner.menu.results" text="banner.menu.results"/></h1>
	</td>
 <%--    <td align="center" width="139" valign="top">
		<img src="images/mainInventory.jpg" /><br/>
		<h1 class="txtHeader"><spring:message code="banner.menu.inventory"/></h1>
	</td> --%> 
	
	<td align="center" width="139" valign="top">
		<img src="images/mainReports.jpg" /><br/>
		<h1 class="txtHeader"><spring:message code="banner.menu.reports" text="banner.menu.reports"/></h1>
	</td>
</tr>
</table>

<script>

function initWarning(){
	var ua = navigator.userAgent;

	//all we care about is if it is IE in non-capatibility mode
	var regEx = new RegExp("MSIE 8");
	var messageNeeded = regEx.test(ua);

	if( messageNeeded ){
		$("IEWarning").show();
	}else{
		$("IEWarning").hide();
	}
}

initWarning();
</script>

