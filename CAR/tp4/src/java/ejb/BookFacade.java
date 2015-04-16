/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

@Stateless
public class BookFacade extends AbstractFacade<Book> implements BookFacadeLocal {
    
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public void init() {
        final Book book1 = new Book("Harry Potter and the Philosopher's Stone", "JK Rowling", 1997);
        final Book book2 = new Book("Harry Potter and the Chamber of Secrets", "JK Rowling", 1998);
      /*  final Book book3 = new Book("Harry Potter and the Prisoner of Azkaban", "JK Rowling", 1999);
        final Book book4 = new Book("Harry Potter and the Goblet of Fire", "JK Rowling", 2000);
        final Book book5 = new Book("Harry Potter and the Order of the Phenix", "JK Rowling", 2003);
        final Book book6 = new Book("Harry Potter and the Half-Blood Prince", "JK Rowling", 2005);
        final Book book7 = new Book("Harry Potter and the Deathly Hallows", "JK Rowling", 2007);*/
        em.persist(book1);
        em.persist(book2);
        /*em.persist(book3);
        em.persist(book4);
        em.persist(book5);
        em.persist(book6);
        em.persist(book7);*/
    }

    public BookFacade() {
        super(Book.class);
    }
    
}
