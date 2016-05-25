<%-- 
    Document   : response
    Created on : 23-May-2016, 12:31:43
    Author     : telugbadebo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <jsp:useBean id="userBean" scope="session" class="org.helloWeb.Users.UserHandler" />
        <jsp:setProperty name="userBean" property="userName"/>
        <h1>Hello <jsp:getProperty name="userBean" property="userName" />!</h1>

    </body>
</html>
