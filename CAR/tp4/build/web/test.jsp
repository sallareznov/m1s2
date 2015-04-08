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
    </body>
</html>
