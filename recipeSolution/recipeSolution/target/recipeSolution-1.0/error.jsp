<%-- 
    Document   : errro
    Created on : Sep 22, 2018, 12:27:32 PM
    Author     : thomas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello there!</h1>
        <h3><%=request.getAttribute("message")%></h3>
    </body>
</html>
