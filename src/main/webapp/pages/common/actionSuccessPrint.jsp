<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.util.StringUtil"
%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" uri="/tags/labdev-view" %>
<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<script type="text/javascript">

function /*void*/ showSuccessMessage( show ){
	$("successMsg").style.visibility = show ? 'visible' : 'hidden';
}

function printBarcode(success, failure) {
	var labNo = document.getElementById('searchValue').value;
	document.getElementById('getBarcodePDF').href = "LabelMakerServlet";
	document.getElementById('getBarcodePDF').click();
}
</script>



<div id="successMsg" style="text-align:center; color:seagreen;  width : 100%;font-size:170%; <c:if test="${not success}">visibility : hidden</c:if>">
	<spring:message code="save.success"/>
	<div>
		<input type="button"
        	value="<%= StringUtil.getMessageForKey("barcode.common.button.print")%>"
        	id="printBarcodeButton"
        	onclick="printBarcode();">
        <a href="" id="getBarcodePDF" target="_blank"></a>
	</div>
</div>




