package org.library.dao;

import java.sql.Connection;

public abstract class AbstractDAO<T> {
    public final Connection connection;
    protected AbstractDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Поиск сущности в базе данных по её id.
     * Поиск осуществляется через карты класса ReceiveWholeQuery
     * @param id собственный id сущности
     * @return Entity
     */
    public abstract T findById(long id);

    /**
     * Поиск сущности в базе данных по её id.
     * Поиск осуществляется через sql запрос напрямую в таблице.
     * @param id собственный id сущности
     * @return Entity
     */
    public abstract T findByIdStrict(long id);

    /**
     * Сохранение сущности в базе данных.
     * @param t Класс сущности Entity
     * @return True или False от успешности операции
     */
    public abstract boolean save(T t);

    /**
     * Удаление сущности из базы данных по id.
     * @param id собственный id сущности
     * @return True или False от успешности операции
     */
    public abstract boolean deleteById(long id);

    /**
     * Изменение поля Name у сущности по её id.
     * @param id собственный id сущности
     * @param name Новое имя сущности
     * @return True или False от успешности операции
     */
    public abstract boolean updateById(long id, String name);
}
