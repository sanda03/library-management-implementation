package Sanda;

import Sanda.repository.DatabaseConnection;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
    }
}