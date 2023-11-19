package Sanda;

import Sanda.model.Book;
import Sanda.model.Topic;
import Sanda.repository.BookCrudOperations;

import java.sql.Date;
import java.util.List;

public class BookTest {
    private static BookCrudOperations bookCrudOperations = new BookCrudOperations();
    public static void test(){
        //find all test
        bookCrudOperations.findAll().forEach(System.out::println);

        //insert book test
        System.out.println(bookCrudOperations.save(
            new Book("", "Nos coeurs en désaccord", 227, Date.valueOf("2021-01-01"), List.of(Topic.ROMANCE), null)
        ));
    }
}
