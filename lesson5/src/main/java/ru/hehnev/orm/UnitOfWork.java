package ru.hehnev.orm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnitOfWork {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    private final List<User> newUser = new ArrayList<>();
    private final List<User> updateUser = new ArrayList<>();
    private final List<User> deleteUser = new ArrayList<>();

    public UnitOfWork(Connection connection) {
        this.connection = connection;
    }

    public void registerNew(User user) {
        this.newUser.add(user);
    }

    public void registerUpdate(User user) {
        this.updateUser.add(user);
    }

    public void registerDelete(User user) {
        this.deleteUser.add(user);
    }

    public void commit() {
        insert();
        update();
        delete();
        clear();
    }

    private void insert() {
        String sql = "INSERT INTO (login, password) users VALUES(?, ?)";
        initPreparedStatement(sql);
        newUser.forEach(user -> {
            try {
                preparedStatement.setString(1, user.getLogin());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void update() {
        String sql = "UPDATE users SET login = ?, password = ? WHERE id = ?";
        initPreparedStatement(sql);
        updateUser.forEach(user -> {
            try {
                preparedStatement.setString(1, user.getLogin());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setLong(3, user.getId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void delete() {
        String sql = "DELETE FROM users WHERE id = ?";
        initPreparedStatement(sql);
        deleteUser.forEach(user -> {
            try {
                preparedStatement.setLong(1, user.getId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void initPreparedStatement(String sql) {
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void clear() {
        newUser.clear();
        updateUser.clear();
        deleteUser.clear();
    }

}
