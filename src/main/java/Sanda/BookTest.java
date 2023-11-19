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
        List<Book> books = bookCrudOperations.findAll();
        books.forEach(el -> System.out.println("[FIND]: "  + el));

        //delete test
        books.forEach(el -> {
            System.out.println("[DELETE]: " + bookCrudOperations.delete(el));
        });

        //insert book test
        System.out.println("[CREATE]: " + bookCrudOperations.save(
            new Book("", "Nos coeurs en désaccord", 227, Date.valueOf("2021-01-01"), List.of(Topic.ROMANCE), null)
        ));

        //Save all book test
        List<Book> result = bookCrudOperations.saveAll(List.of(
            new Book("", "book8", 255, Date.valueOf("2021-01-01"), List.of(Topic.ROMANCE, Topic.OTHER), null),
            new Book("", "book5", 255, Date.valueOf("2021-08-01"), List.of(Topic.OTHER), null)
        ));
        result.forEach(el -> System.out.println("[CREATE]: " + el));

        //last find
        List<Book> booksTwo = bookCrudOperations.findAll();
        booksTwo.forEach(el -> System.out.println("[FIND]: "  + el));
    }
}
