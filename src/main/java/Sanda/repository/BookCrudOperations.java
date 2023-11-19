package Sanda.repository;

import Sanda.model.Author;
import Sanda.model.Book;
import Sanda.model.Topic;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookCrudOperations implements CrudOperations<Book> {
    private final DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
    private String toTopicType(List<Topic> topics){
        return topics.toString().replace("[","{").replace("]","}");
    }
    private Book createBook(ResultSet resultSet){
        try {
            Author author = AuthorCrudOperations.findOne(resultSet.getString("id_author"));
            List<Topic> topics = Arrays.stream((String[]) resultSet.getArray("topics")
                    .getArray()).map(Topic::valueOf).toList();
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
        List<Book> results = new ArrayList<>();
        toSave.forEach(el -> results.add(save(el)));
        return results;
    }

    @Override
    public Book save(Book toSave){
        String insertValues = Stream.of("book_name", "page_numbers", "release_date","topics")
                .map(el -> "\"" + el + "\"").collect(Collectors.joining(","));
        String insertQuery = "INSERT INTO \"book\"(" + insertValues + ") VALUES (?,?,?,'" + toTopicType(toSave.getTopics()) + "');";
        try{
            PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(insertQuery);
            preparedStatement.setString(1,toSave.getBookName());
            preparedStatement.setLong(2,toSave.getPageNumbers());
            preparedStatement.setDate(3,toSave.getReleaseDate());
            preparedStatement.executeUpdate();
            List<Book> books = findAll();
            return books.get(books.size() - 1);
        }catch (SQLException error){
            System.out.println(error.getMessage());
        }
        return null;
    }

    @Override
    public Book delete(Book toDelete){
        String query = "DELETE FROM \"book\" WHERE \"id\"=?;";
        try {
            PreparedStatement statement = databaseConnection.getConnection().prepareStatement(query);
            statement.setString(1, toDelete.getId());
            statement.executeUpdate();
            return toDelete;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
