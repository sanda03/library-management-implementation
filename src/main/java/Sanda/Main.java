package Sanda;

import Sanda.repository.BookCrudOperations;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        BookCrudOperations bookCrudOperations = new BookCrudOperations();
        System.out.println(bookCrudOperations.findAll());
    }
}