package ru.hehnev.orm;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class UnitOfWork {
    private final Connection connection;

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
    }

    private void insert() {

    }

    private void update() {

    }

    private void delete() {

    }

}
