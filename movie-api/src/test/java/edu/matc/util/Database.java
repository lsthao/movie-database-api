package edu.matc.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Database {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final static Database instance = new Database();
    private Properties properties;
    private Connection connection;

    private Database() {
        loadProperties();
    }

    private void loadProperties() {
        properties = new Properties();

        try {
            properties.load( this.getClass().getResourceAsStream("/database.properties"));
        } catch (IOException ioException) {
            logger.error(ioException.getMessage());
        } catch (Exception exception) {
            logger.error(exception.getMessage());
        }

    }

    public static Database getInstance() {
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void connect() throws Exception {
        if (connection != null) {
            return;
        }
        try {
            Class.forName(properties.getProperty("driver"));
        } catch (ClassNotFoundException classNotFoundException) {
            throw new Exception("Database.connect()...Error: MySQL Driver not found");
        }

        String url = properties.getProperty("url");
        connection = DriverManager.getConnection(url, properties.getProperty("username"), properties.getProperty("password"));

    }

    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException sqlException) {
                logger.error(sqlException.getMessage());
            }
        }

        connection = null;
    }

    public void runSQL(String sqlFile) {
        Statement statement = null;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(sqlFile);

        try(BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            Class.forName("com.mysql.jdbc.Driver");
            connect();
            statement = connection.createStatement();

            while (true) {
                String sql = br.readLine();
                if (sql == null) {
                    break;
                }

                statement.executeUpdate(sql);
            }
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } catch (Exception exception) {
            logger.error(exception.getMessage());
        } finally {
            disconnect();
        }

    }

}