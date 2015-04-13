/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="AuthorServlet", urlPatterns={"/AuthorServlet"})
public class AuthorServlet extends HttpServlet {
    
    private BooksManager booksManager;

    public AuthorServlet(BooksManager booksManager) {
        this.booksManager = booksManager;
    }
    
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final PrintWriter out = response.getWriter();
        out.println("<h3>Authors :</h3>");
        out.println("<ul>");
        for (final String author : booksManager.getAuthors()) {
            out.println("<li>" + author + "</li>");
        }
        out.println("</ul>");
    }
}
