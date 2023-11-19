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
        return topics.toString().replace("[","{").replace("]","}");
    }
    private Book createBook(ResultSet resultSet){
        try {
            Author author = authorCrudOperations.findOne(resultSet.getString("id_author"));
            List<Topic> topics = new ArrayList<>();
            String[] topicsArray = (String[]) resultSet.getArray("topics").getArray();
            return new Book(
                resultSet.getString("id"),
                resultSet.getString("book_name"),
                resultSet.getInt("page_numbers"),
                resultSet.getDate("release_date"),
                topics,
                author
            );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
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
    public Book save(Book toSave){
        return null;
    }

    @Override
    public Book delete(Book toDelete){
        return null;
    }
}
