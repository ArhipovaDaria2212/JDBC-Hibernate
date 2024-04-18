package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS users " +
                "(id SERIAL PRIMARY KEY, " +
                "name VARCHAR(50), " +
                "last_name VARCHAR(50), " +
                "age SMALLINT);";

        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(createTableSQL);
        } catch (SQLException e) {
            System.out.println("Database did`t created ");
        } catch (ClassNotFoundException e) {
            System.out.println("Error creating database driver");
        }
    }

    public void dropUsersTable() {
        String dropTableSQL = "DROP TABLE IF EXISTS users";
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(dropTableSQL);
        } catch (SQLException e) {
            System.out.println("Database did`t dropped " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Error creating database driver");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveUserSQL = "INSERT INTO users (name, last_name, age) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(saveUserSQL)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.printf("User с именем – %s добавлен в базу данных\n", name);
        } catch (SQLException e) {
            System.out.println("Database did`t saved " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Error creating database driver");
        }
    }

    public void removeUserById(long id) {
        String removeUserSQL = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(removeUserSQL)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("User did`t removed " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Error creating database driver");
        }
    }

    public List<User> getAllUsers() {
        String getAllUsersSQL = "SELECT * FROM users";
        List<User> userList = new ArrayList<>();
        try (Statement statement = Util.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(getAllUsersSQL)) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Database did`t get all users " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Error creating database driver");
        }

        return userList;
    }

    public void cleanUsersTable() {
        String truncateTableSQL = "TRUNCATE TABLE users";
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(truncateTableSQL);
        } catch (SQLException e) {
            System.out.println("Database did`t clean " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Error creating database driver");
        }
    }
}
