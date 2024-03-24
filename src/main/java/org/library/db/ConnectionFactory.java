package org.library.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory{
    private static ConnectionFactory cf = new ConnectionFactory();

    private ConnectionFactory() {
        super();
    }

    public static synchronized ConnectionFactory getInstance() {
        if(cf==null) {
            cf = new ConnectionFactory();
        }
        return cf;
    }

    /**
     *
     * Метод получения связи с базой данных.
     * @return Connection
     *
     */
    public Connection getConnection() {
        Connection conn = null;
        Properties prop= new Properties();
        try {
            prop.load(this.getClass().getClassLoader().getResourceAsStream("database.properties"));
            Class.forName("org.postgresql.Driver");
            conn= DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password"));
        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }
}