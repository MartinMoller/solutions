<%-- 
    Document   : showRecipe
    Created on : Sep 22, 2018, 5:14:41 PM
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
        <%= request.getAttribute("recipe")%>
    </body>
</html>
