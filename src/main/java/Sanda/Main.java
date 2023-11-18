package Sanda;

import Sanda.model.Author;
import Sanda.repository.AuthorCrudOperations;
import Sanda.repository.DatabaseConnection;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AuthorCrudOperations authorCrudOperations = new AuthorCrudOperations();
        List<Author> authors = authorCrudOperations.findAll();
        authors.forEach(System.out::println);
    }
}