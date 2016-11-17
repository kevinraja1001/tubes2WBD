<%-- 
    Document   : logout
    Created on : Nov 14, 2016, 12:36:38 AM
    Author     : kevinraja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="refresh" content="4; url=login.jsp" />
        <title>Log out</title>
    </head>
    <body>
        <%session.invalidate();%>
    </body>
</html>
