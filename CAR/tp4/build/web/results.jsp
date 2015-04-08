<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%response.setHeader("Refresh", "5; URL=index.jsp");%>
        <jsp:forward page="test.jsp">
            <jsp:param name="title" value="<%=request.getParameter("title")%>"/>
            <jsp:param name="author" value="<%=request.getParameter("author")%>"/>
            <jsp:param name="release" value="<%=request.getParameter("release")%>"/>
        </jsp:forward>
    </body>
</html>
 