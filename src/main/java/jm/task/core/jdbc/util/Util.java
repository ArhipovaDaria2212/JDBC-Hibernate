package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    // реализуйте настройку соединения с БД

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1234";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");

        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}