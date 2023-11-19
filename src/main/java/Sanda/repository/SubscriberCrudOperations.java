package Sanda.repository;

import Sanda.model.Subscriber;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubscriberCrudOperations implements CrudOperations<Subscriber>{
    final static private DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
    public static Subscriber createSubscriber(ResultSet resultSet) throws SQLException {
        return new Subscriber(
            resultSet.getString("id"),
            resultSet.getString("name"),
            resultSet.getString("ref")
        );
    }

    @Override
    public List<Subscriber> findAll(){
        final String query = Utils.selectAllQuery("subscriber");
        List<Subscriber> subscribers = new ArrayList<>();
        Connection connection = databaseConnection.getConnection();

        try{
            ResultSet resultSet =  connection.prepareStatement(query).executeQuery();
            while(resultSet.next()){
                subscribers.add(createSubscriber(resultSet));
            }
        }
        catch (SQLException error){
            System.out.println(error.getMessage());
        }

        return subscribers;
    }

    @Override
    public List<Subscriber> saveAll(List<Subscriber> toSave){
        List<Subscriber> subscribers = new ArrayList<>();
        for (Subscriber el : toSave) {
            subscribers.add(this.save(el));
        }
        return subscribers;
    }

    @Override
    public Subscriber save(Subscriber toSave){
        String query = Utils.insertQuery("subscriber", List.of("name", "ref"));
        try {
            PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, toSave.getName());
            preparedStatement.setString(2, toSave.getRef());
            preparedStatement.executeUpdate();
            List<Subscriber> subscribers = findAll();
            return subscribers.get(subscribers.size() - 1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Subscriber delete(Subscriber toDelete){
        String query = "DELETE FROM \"subscriber\" WHERE \"id\"=?;";
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

    public static Subscriber findOne(String id){
        String query = "SELECT * FROM \"subscriber\" WHERE \"id\"=?;";
        if(id != null){
            try {
                PreparedStatement statement = databaseConnection.getConnection().prepareStatement(query);
                statement.setString(1, id);
                ResultSet resultSet = statement.executeQuery();
                resultSet.next();
                return createSubscriber(resultSet);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }
}
