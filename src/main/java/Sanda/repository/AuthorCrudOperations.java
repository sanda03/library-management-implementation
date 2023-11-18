package Sanda.repository;

import Sanda.model.Author;
import Sanda.model.Sex;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorCrudOperations implements CrudOperations<Author>{
    final private DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
    public Author createAuthor(ResultSet resultSet) throws SQLException {
        return new Author(
            resultSet.getString("id"),
            resultSet.getString("name"),
            Sex.valueOf(resultSet.getString("sex"))
        );
    }

    @Override
    public List<Author> findAll(){
        final String query = "SELECT * from \"author\";";
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
    public List<Author> saveAll(List<Author> toSave) {
        return null;
    }

    @Override
    public Author save(Author toSave) {
        return null;
    }

    @Override
    public Author delete(Author toDelete) {
        return null;
    }
}
