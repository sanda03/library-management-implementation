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
    private String toTopicType(List<Topic> topics){
        StringBuilder result = new StringBuilder("ARRAY['" + topics.get(0).toString() + "'::\"topic\"");
        for (int i = 1; i < topics.size() ; i++) {
            result.append(",'").append(topics.get(i)).append("'::\"topic\"");
        }
        return  result.append("]").toString();
    }
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
        return null;
    }

/*
    @Override
    public Book save(Book toSave) throws SQLException {
        String query = "INSERT INTO \"book\"(\"book_name\", \"page_numbers\", \"release_date\", \"topics\") VALUE (?,?,?)";
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, toSave.getBookName());
        preparedStatement.setInt(2, toSave.getPageNumbers());
        preparedStatement.setDate(3, toSave.getReleaseDate());
        preparedStatement.setString(4, toTopicType(toSave.getTopics()));
        preparedStatement.setObject(5, toSave.getAuthor().getId());
        preparedStatement.executeUpdate();

        return toSave;
    }
*/

    @Override
    public Book delete(Book toDelete) throws SQLException {
        return null;
    }
}
