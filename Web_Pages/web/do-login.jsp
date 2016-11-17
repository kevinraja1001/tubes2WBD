<%-- 
    Document   : do-login
    Created on : Nov 11, 2016, 11:34:24 PM
    Author     : kevinraja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="refresh" content="5; url=catalog.jsp" />
        <title>Login</title>
    </head>
    <body>
        <%  String un = request.getParameter("un");
            String t = request.getParameter("t");
            String et = request.getParameter("et");
            String fn = request.getParameter("fn");
            session.setAttribute("un", un);
            session.setAttribute("t", t);
            session.setAttribute("expiry_time", et);
            session.setAttribute("fn", fn);
        %>
    </body>
</html>
