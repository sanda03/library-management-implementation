package Sanda.repository;

import Sanda.model.Author;
import Sanda.model.Sex;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AuthorCrudOperations implements CrudOperations<Author>{
    final private DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
    public static Author createAuthor(ResultSet resultSet) throws SQLException {
        return new Author(
            UUID.fromString(resultSet.getString("id")),
            resultSet.getString("name"),
            Sex.valueOf(resultSet.getString("sex"))
        );
    }

    @Override
    public List<Author> findAll(){
        final String query = Utils.selectAllQuery("author");
        List<Author> authors = new ArrayList<>();
        Connection connection = databaseConnection.getConnection();

        try{
            ResultSet resultSet =  connection.prepareStatement(query).executeQuery();
            while(resultSet.next()){
                authors.add(createAuthor(resultSet));
            }
        }
        catch (SQLException error){
            System.out.println(error.getMessage());
        }

        return authors;
    }

    @Override
    public List<Author> saveAll(List<Author> toSave){
        List<Author> authors = new ArrayList<>();
        toSave.forEach(el -> {
            try {
                authors.add(this.save(el));
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        });
        return authors;
    }

    @Override
    public Author save(Author toSave) throws SQLException {
        String query = Utils.insertQuery("author", List.of("name", "sex"));
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, toSave.getName());
        preparedStatement.setString(2, toSave.getSex().toString());
        preparedStatement.executeUpdate();
        List<Author> authors = findAll();
        return authors.get(authors.size() - 1);
    }

    @Override
    public Author delete(Author toDelete) throws SQLException {
        String query = "DELETE FROM \"author\" WHERE \"id\"=?;";
        PreparedStatement statement = databaseConnection.getConnection().prepareStatement(query);
        statement.setObject(1, toDelete.getId());
        statement.executeUpdate();
        return toDelete;
    }

    public Author findOne(UUID id) throws SQLException {
        String query = "SELECT * FROM \"author\" WHERE \"id\"=?;";
        PreparedStatement statement = databaseConnection.getConnection().prepareStatement(query);
        statement.setObject(1, id);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return createAuthor(resultSet);
    }
}
