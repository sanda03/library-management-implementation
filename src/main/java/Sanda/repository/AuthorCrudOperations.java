package Sanda.repository;

import Sanda.model.Author;
import Sanda.model.Sex;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorCrudOperations implements CrudOperations<Author>{
    final static private DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
    public static Author createAuthor(ResultSet resultSet) throws SQLException {
        return new Author(
            resultSet.getString("id"),
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
        for (Author el : toSave) {
            authors.add(this.save(el));
        }
        return authors;
    }

    @Override
    public Author save(Author toSave){
        String query = Utils.insertQuery("author", List.of("name", "sex"));
        try {
            PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, toSave.getName());
            preparedStatement.setString(2, toSave.getSex().toString());
            preparedStatement.executeUpdate();
            List<Author> authors = findAll();
            return authors.get(authors.size() - 1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Author delete(Author toDelete){
        String query = "DELETE FROM \"author\" WHERE \"id\"=?;";
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

    public static Author findOne(String id){
        String query = "SELECT * FROM \"author\" WHERE \"id\"=?;";
        try {
            PreparedStatement statement = databaseConnection.getConnection().prepareStatement(query);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return createAuthor(resultSet);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
