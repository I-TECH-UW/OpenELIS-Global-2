<%@ page language="java"
	contentType="text/html; charset=utf-8"
%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" uri="/tags/labdev-view" %>
<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<c:set var="success" value="${success}" />
<script type="text/javascript">

function /*void*/ showSuccessMessage( show ){
	$("successMsg").style.visibility = show ? 'visible' : 'hidden';
}
</script>



<div id="successMsg" style="text-align:center; color:seagreen;  width : 100%;font-size:170%; visibility : hidden"  onload="showSuccessMessage(${success})">
				<spring:message code="save.success"/>
</div>



