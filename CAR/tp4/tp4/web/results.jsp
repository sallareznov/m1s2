<%-- 
    Document   : results
    Created on : 31 mars 2015, 18:12:23
    Author     : diagne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Book informations :</h1>
        <h3>Title = <%= request.getParameter("title")%> </h3>
        <h3>Author = <%= request.getParameter("author")%> </h3>
        <h3>Release Date = <%= request.getParameter("release")%> </h3>
        <% request.setAttribute("title", request.getParameter("title")); %>
        <form action="index.html">
            <input type="submit" value="Submit as admin">
        </form>
    </body>
</html>
