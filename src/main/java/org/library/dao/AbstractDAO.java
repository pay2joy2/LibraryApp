package org.library.dao;

import org.library.entities.Author;

import java.sql.Connection;

public abstract class AbstractDAO<T> {
    public final Connection connection;

    protected AbstractDAO(Connection connection) {
        this.connection = connection;
    }

    public abstract T findById(long id);
    public abstract T findByIdStrict(long id);
    public abstract boolean save(T t);
    public abstract boolean deleteById(long id);
    public abstract boolean updateById(long id, String name);
}
