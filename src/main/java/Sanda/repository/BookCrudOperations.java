package Sanda.repository;

import Sanda.model.Author;
import Sanda.model.Book;
import Sanda.model.Topic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookCrudOperations implements CrudOperations<Book> {
    private final AuthorCrudOperations authorCrudOperations = new AuthorCrudOperations();
    private final DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

    private Book createBook(ResultSet resultSet) throws SQLException {
        Author author = authorCrudOperations.findOne((UUID) resultSet.getObject("id_author"));
        String[] topicsArray = (String[]) resultSet.getArray("topics").getArray();
        List<Topic> topics = new ArrayList<>();
        for (String topic: topicsArray) {
            topics.add(Topic.valueOf(topic));
        }
        return new Book(
            UUID.fromString(resultSet.getString("id")),
            resultSet.getString("book_name"),
            resultSet.getInt("page_numbers"),
            resultSet.getDate("release_date"),
            topics,
            author
        );
    }
    @Override
    public List<Book> findAll(){
        final String query = Utils.selectAllQuery("book");
        List<Book> authors = new ArrayList<>();
        Connection connection = databaseConnection.getConnection();

        try{
            ResultSet resultSet =  connection.prepareStatement(query).executeQuery();
            while(resultSet.next()){
                authors.add(createBook(resultSet));
            }
        }
        catch (SQLException error){
            System.out.println(error.getMessage());
        }

        return authors;
    }

    @Override
    public List<Book> saveAll(List<Book> toSave) {
        return null;
    }

    @Override
    public Book save(Book toSave) throws SQLException {
        String query = Utils.insertQuery(
    "book",
            List.of(
                "book_name",
                "page_numbers",
                "release_date",
                "topics",
                "id_author"
            )
        );

        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, toSave.getBookName());
        preparedStatement.setInt(2, toSave.getPageNumbers());
        preparedStatement.setDate(3, toSave.getReleaseDate());
/*
        preparedStatement.setArray(2, toSave.getTopics().toArray());
*/

        preparedStatement.executeUpdate();
/*
        List<Author> authors = findAll();
        return authors.get(authors.size() - 1);
*/
        return null;
    }

    @Override
    public Book delete(Book toDelete) throws SQLException {
        return null;
    }
}
