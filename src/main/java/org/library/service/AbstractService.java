package org.library.service;

import org.library.db.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractService<T>{

    private final ConnectionFactory connectionFactory;

    public AbstractService(){
        this.connectionFactory = ConnectionFactory.getInstance();
    }

    protected <T> T execute(AbstractService.DaoCommand<T> command) {
        try (Connection connection = connectionFactory.getConnection()) {
            return command.execute(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected interface DaoCommand<T> {
        T execute(Connection connection) throws SQLException;
    }

    public abstract T getById(long id);

    public abstract boolean deleteById(long id);

    public abstract boolean updateById(long id, String name);
}
