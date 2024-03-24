package org.library.service;

import org.library.db.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractService<T>{

    private final ConnectionFactory connectionFactory;

    /**
     * Конструктор, protected изменён,
     * для использования в сервлетах.
     */
    public AbstractService(){
        this.connectionFactory = ConnectionFactory.getInstance();
    }

    /**
     * Метод для упрощения работы с ресурсами Connection
     * Осуществляет создание и освобождение при каждой иницализации
     * @param command Команда интерфейса
     * @return Возвращает сущность дженерика
     * @param <T> Сущность дженерика
     */
    protected <T> T execute(AbstractService.DaoCommand<T> command) {
        try (Connection connection = connectionFactory.getConnection()) {
            return command.execute(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Интерфейс для создания команды на выполнение
     * @param <T>
     */
    protected interface DaoCommand<T> {
        T execute(Connection connection) throws SQLException;
    }

    /**
     * Выполнение получения сущности по Id.
     * Проверяется сразу два вида получения - связанных сущностей, и сущностей только в таблице.
     * @param id Идентификатор сущности
     * @return Entity - сущность
     */
    public abstract T getById(long id);

    /**
     * Сохранение сущности в БД.
     * @param t Entity - сущность
     * @return Boolean значение о выполнении
     */
    public abstract boolean save(T t);

    /**
     * Удаление сущности из БД.
     * @param id Идентификатор сущности
     * @return Boolean значение о выполнении
     */
    public abstract boolean deleteById(long id);

    /**
     * Обновление имени сущности в БД
     * @param id Идентификатор сущности
     * @param name Новое имя сущности
     * @return Boolean значение о выполнении
     */
    public abstract boolean updateById(long id, String name);
}
