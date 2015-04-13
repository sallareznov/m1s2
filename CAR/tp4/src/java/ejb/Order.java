/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;

public class Order {
    
    private int number;
    private List<Book> orderedBooks;

    public Order(int number, List<Book> orderedBooks) {
        this.number = number;
        this.orderedBooks = orderedBooks;
    }

    public int getNumber() {
        return number;
    }

    public List<Book> getOrderedBooks() {
        return orderedBooks;
    }
    
}
