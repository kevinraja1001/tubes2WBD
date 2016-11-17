<%-- 
    Document   : do-edit
    Created on : Nov 13, 2016, 11:44:48 PM
    Author     : user
--%>

<%@page import="client.SOAPClientSAAJ" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><%= SOAPClientSAAJ.getStringFromSOAPRespond("113","edit_product", "id", request.getParameter("id"), "name", request.getParameter("name"), "desc", request.getParameter("desc"), "price", request.getParameter("price"), null, null, null, null, null, null, null, null) %></h1>
    </body>
</html>
