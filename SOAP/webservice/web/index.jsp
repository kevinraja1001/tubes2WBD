<%-- 
    Document   : index
    Created on : Nov 12, 2016, 2:16:08 AM
    Author     : user
--%>

<%@ page import="javax.xml.ws.WebServiceRef" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
       <h1>Hello World!</h1>
        <%="coba"%>
        <% 
            marketplace.produk p = new marketplace.produk();
            out.println(p.hello("coba"));
        %>
                
    
</html>
