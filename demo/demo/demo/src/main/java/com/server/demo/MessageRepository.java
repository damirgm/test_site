package com.server.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageRepository {

    public List<Message> findAll() {
        List<Message> messages = new ArrayList<>();
        try {
            String query = "SELECT * FROM messages";
            Statement statement = DBConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String text = resultSet.getString("text");
                Timestamp timestamp = resultSet.getTimestamp("timestamp");

                messages.add(new Message(id, text, timestamp));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messages;
    }

    public Message findById(Long id) {
        Message message = null;
        try {
            String query = "SELECT * FROM messages WHERE id = ?";
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String text = resultSet.getString("text");
                Timestamp timestamp = resultSet.getTimestamp("timestamp");

                message = new Message(id, text, timestamp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return message;
    }

    public Message save(Message message) {
        try {
            String query = "INSERT INTO messages(text, timestamp) VALUES (?, ?)";
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, message.getText());
            statement.setTimestamp(2, message.getTimestamp());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating message failed, no rows affected.");
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                message.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("Creating message failed, no ID obtained.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return message;
    }

    public void delete(Long id) {
        try {
            String query = "DELETE FROM messages WHERE id = ?";
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
            statement.setLong(1, id);

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting message failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

