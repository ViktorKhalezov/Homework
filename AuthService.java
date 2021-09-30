package ru.geekbrains.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AuthService {


    public Optional<User> findUserByLoginAndPassword(String login, String password) {
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE login = ?");
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            String databasePassword = rs.getString("password");
            if(password.equals(databasePassword)) {
                return Optional.of(new User(rs.getString("name"), login, databasePassword));
            }
        } catch (SQLException e) {
            return Optional.empty();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

       return Optional.empty();
    }

    static class User {
        String name;
        String login;
        String password;

        User(String name, String login, String password) {
            this.name = name;
            this.login = login;
            this.password = password;
        }

        String getName() {
            return name;
        }

        String getLogin() {
            return login;
        }

        String getPassword() {
            return password;
        }


    }


}
