/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import static ejb.Book_.author;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="AuthorServlet", urlPatterns={"/AuthorServlet"})
public class AuthorServlet extends HttpServlet {
    
    @EJB
    private BookFacadeLocal bean;
    
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final PrintWriter out = response.getWriter();
        out.println("<h3>Authors :</h3>");
        out.println("<ul>");
        final List<Book> books = bean.findAll();
        for (final Book book : books) {
            out.println("<li>" + book.getAuthor() + "</li>");
        }
        out.println("</ul>");
    }
}
