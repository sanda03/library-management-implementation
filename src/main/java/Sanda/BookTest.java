package Sanda;

import Sanda.repository.BookCrudOperations;

public class BookTest {
    private static BookCrudOperations bookCrudOperations = new BookCrudOperations();
    public static void test(){
        //find all test
        bookCrudOperations.findAll().forEach(System.out::println);
    }
}
