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


<c:set var="pageSuccess" value="${success || param.forward == 'success'}"/>

<div id="successMsg" 
     style="text-align:center; color:seagreen;  width : 100%;font-size:170%; <c:if test="${not pageSuccess}">visibility : hidden</c:if>">
				<spring:message code="save.success"/>
</div>



