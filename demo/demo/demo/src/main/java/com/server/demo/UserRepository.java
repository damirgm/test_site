package com.server.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            String query = "SELECT * FROM users";
            Statement statement = DBConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String email = resultSet.getString("email");

                users.add(new User(id, firstName, lastName, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public User findById(Long id) {
        User user = null;
        try {
            String query = "SELECT * FROM users WHERE id = ?";
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String email = resultSet.getString("email");

                user = new User(id, firstName, lastName, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public User save(User user) {
        try {
            String query = "INSERT INTO users(firstName, lastName, email) VALUES (?, ?, ?)";
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public void delete(Long id) {
        try {
            String query = "DELETE FROM users WHERE id = ?";
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
            statement.setLong(1, id);

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting user failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
