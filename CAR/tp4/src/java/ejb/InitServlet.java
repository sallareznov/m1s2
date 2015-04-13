/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InitServlet extends HttpServlet {
    
    private BooksManager booksManager;

    public InitServlet(BooksManager booksManager) {
        this.booksManager = booksManager;
    }

    public BooksManager getBooksManager() {
        return booksManager;
    }
    
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        booksManager.init();
        final PrintWriter out = response.getWriter();
        out.println("Initlization done");
    }

}
