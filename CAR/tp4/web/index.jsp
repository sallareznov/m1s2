<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>BOOK A BOOK !</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <form action="results.jsp">
            <fieldset>
                <legend>The book!</legend>
            <table>
                <tr>
                    <td>Title : </td>
                    <td><input type="text" name="title" value=<%=request.getParameter("title")%>/></td>
                </tr>
                <tr>
                    <td>Author : </td>
                    <td><input type="text" name="author" value=<%=request.getParameter("author")%>/></td>
                </tr>
                <tr>
                    <td>Release date : </td>
                    <td><input type="text" name="release" value="<%=request.getParameter("release")%>"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Order" name="order" /></td>
                </tr>
            </table>
            </fieldset>
        </form>
    </body>
</html>
