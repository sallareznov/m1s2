/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateful;

@Stateful
public class BooksManager {
    
    private List<Book> books;
    private List<String> authors;

    public void init() {
        final Book book1 = new Book("Harry Potter and the Philosopher's Stone", "JK Rowling", 1997);
        final Book book2 = new Book("Harry Potter and the Chamber of Secrets", "JK Rowling", 1998);
        final Book book3 = new Book("Harry Potter and the Prisoner of Azkaban", "JK Rowling", 1999);
        final Book book4 = new Book("Harry Potter and the Goblet of Fire", "JK Rowling", 2000);
        final Book book5 = new Book("Harry Potter and the Order of the Phenix", "JK Rowling", 2003);
        final Book book6 = new Book("Harry Potter and the Half-Blood Prince", "JK Rowling", 2005);
        final Book book7 = new Book("Harry Potter and the Deathly Hallows", "JK Rowling", 2007);
        books = new LinkedList<Book>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);
        books.add(book6);
        books.add(book7);
        authors = new LinkedList<String>();
        authors.add("JK Rowling");
    }

    public List<String> getAuthors() {
        return authors;
    }

    public List<Book> getBooks() {
        return books;
    }
    
}
