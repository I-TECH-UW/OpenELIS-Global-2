<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.util.ConfigurationProperties.Property,
			us.mn.state.health.lims.common.action.IActionConstants,
			us.mn.state.health.lims.login.valueholder.UserSessionData,
			us.mn.state.health.lims.common.util.StringUtil"%>

<%!
      String path = "";
      String basePath = "";
      UserSessionData usd = null;
%>
<%
      path = request.getContextPath();
      basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	  
	  if ( request.getSession().getAttribute(IActionConstants.USER_SESSION_DATA) != null ){
          usd = ( UserSessionData ) request.getSession().getAttribute( IActionConstants.USER_SESSION_DATA );
      }

    if ( usd != null ) {
        int timeOut = usd.getUserTimeOut();
        
        String key1 = "login.session.timeout.message";  
        String key2 = "login.error.session.message";
        
        String message1 = StringUtil.getMessageForKey("login.session.timeout.message");
        String message2 = StringUtil.getMessageForKey("login.error.session.message");
%>    

<SCRIPT LANGUAGE="JavaScript1.2">
    var targetURL="<%=request.getContextPath()%>" + "/LoginPage.do";
    var milliseconds="<%=timeOut%>";
    
    var sec = 00;
    var min = milliseconds/60;

    function countDown() {
        sec--;
        if (sec == -01) {
            sec = 59;
            min = min - 1;
        }

        if (sec<=9) { 
            sec = "0" + sec; 
        }
        time = (min<=9 ? "0" + min : min) + ":" + sec;
        window.status = '<%=message1%> ' + time;
        SD=window.setTimeout("countDown();", 1000);
        if (min == '00' && sec == '00') { 
            sec = "00"; 
            window.clearTimeout(SD);
            window.status = '<%=message2%>';
            alert('<%=message2%>');
            window.location=targetURL; 
        }
    }
    
    countDown();

</SCRIPT> 

<%      
    }    
%>