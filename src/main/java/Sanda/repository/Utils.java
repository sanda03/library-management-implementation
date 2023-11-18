package Sanda.repository;

import java.sql.Array;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {
    public static String insertQuery(String tableName, List<String> columns){
        String insertColumns = columns.stream()
            .map(el -> "\"" + el + "\"")
            .collect(Collectors.joining(","));
        return (
            "INSERT INTO " + tableName + "(" + insertColumns + ")" +
            " VALUES ( ?" + ",?".repeat(columns.size() - 1) +");"
        );
    }
}
