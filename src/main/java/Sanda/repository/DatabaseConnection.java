package Sanda.repository;

import java.sql.Connection;
import java.sql.SQLException;

import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseConnection {
    private Connection connection;
    private Statement statement;
    private static DatabaseConnection instance;
    private DatabaseConnection(){
        try {
            this.connection = DriverManager.getConnection(
                PostgresqlConf.DB_URL,
                PostgresqlConf.DB_USERNAME,
                PostgresqlConf.DB_PASSWORD
            );
            this.statement = this.connection.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Statement getStatement() {
        return this.statement;
    }
    public Connection getConnection() {
        return this.connection;
    }

    public static DatabaseConnection getInstance() {
        if(DatabaseConnection.instance == null){
            DatabaseConnection.instance = new DatabaseConnection();
        }
        return DatabaseConnection.instance;
    }

    public void closeConnection(){
        try {
            this.connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}