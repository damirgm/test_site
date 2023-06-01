package com.server.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChatRepository {
    
    public List<Chat> findAll() {
        List<Chat> chats = new ArrayList<>();
        try {
            String query = "SELECT * FROM chats";
            Statement statement = DBConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");

                chats.add(new Chat(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chats;
    }

    public Chat findById(Long id) {
        Chat chat = null;
        try {
            String query = "SELECT * FROM chats WHERE id = ?";
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");

                chat = new Chat(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chat;
    }

    public Chat save(Chat chat) {
        try {
            String query = "INSERT INTO chats(name) VALUES (?)";
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, chat.getName());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating chat failed, no rows affected.");
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                chat.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("Creating chat failed, no ID obtained.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chat;
    }

    public void delete(Long id) {
        try {
            String query = "DELETE FROM chats WHERE id = ?";
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
            statement.setLong(1, id);

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting chat failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
