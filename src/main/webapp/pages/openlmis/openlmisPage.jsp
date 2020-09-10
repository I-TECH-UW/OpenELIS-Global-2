<%@ page import="org.openelisglobal.common.util.ConfigurationProperties,
				org.openelisglobal.common.util.ConfigurationProperties.Property,
				org.openelisglobal.common.util.Versioning" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<br/>
<iframe src="${inventoryUrl}" onload='javascript:(function(o){o.style.height=o.contentWindow.document.body.scrollHeight+"px";}(this));' style="height:800px;width:100%;border:none;overflow:hidden;"></iframe>