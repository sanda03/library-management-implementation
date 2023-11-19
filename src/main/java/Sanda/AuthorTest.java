package Sanda;

import Sanda.model.Author;
import Sanda.model.Sex;
import Sanda.repository.AuthorCrudOperations;

import java.sql.SQLException;
import java.util.List;

public class AuthorTest {
    public static void test(){
        AuthorCrudOperations authorCrudOperations = new AuthorCrudOperations();

        //Find all test
        List<Author> authors = authorCrudOperations.findAll();
        System.out.println("-".repeat(5)  +  " findAll test for authors " + "-".repeat(5));
        authors.forEach(el -> System.out.println("[FIND]: "  + el));

        //delete test
        System.out.println("\n" + "-".repeat(5)  +  " delete test for authors " + "-".repeat(5));
        authors.forEach(el -> {
            System.out.println("[DELETED]: " + authorCrudOperations.delete(el));
        });
        System.out.println(authorCrudOperations.findAll());

        //save test
        Author author1 = new Author(null, "Author1", Sex.M);
        System.out.println("\n" + "-".repeat(5)  +  " save test for authors " + "-".repeat(5));
        System.out.println("[CREATE]: " + authorCrudOperations.save(author1));

        // save all test
        Author author2 = new Author(null, "Author2", Sex.F);
        Author author3 = new Author(null, "Author3", Sex.M);
        System.out.println("\n" + "-".repeat(5)  +  " saveAll test for authors " + "-".repeat(5));
        authorCrudOperations.saveAll(List.of(author2, author3)).forEach(el -> System.out.println("[CREATE]: " + el));

        //Last find
        List<Author> authorsTwo = authorCrudOperations.findAll();
        System.out.println("\n" + "-".repeat(5)  +  " findAll test for authors " + "-".repeat(5));
        authorsTwo.forEach(el -> System.out.println("[FIND]: "  + el));
    }
}
