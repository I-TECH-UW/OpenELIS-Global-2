<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:redirect url="LoginPage.do"/>

<%--

Redirect default requests to homePage global ActionForward.
By using a redirect, the user-agent will change address to match the path of our homePage ActionForward. 

--%>
