package org.library.db;

import java.io.FileNotFoundException;
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
     * Getting connection for DB - postgresql
     * Returning Connection
     *
     */

    public Connection getConnection() {
        Connection conn = null;
        Properties prop= new Properties();
        try {
            prop.load(this.getClass().getClassLoader().getResourceAsStream("database.properties"));
            Class.forName("org.postgresql.Driver");
            conn= DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password"));
        } catch (SQLException e) {
            System.out.println("failed to create connection");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
}