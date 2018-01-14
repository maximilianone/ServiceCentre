package application.model.dao.connection.impl;

import javax.sql.DataSource;

import application.model.dao.connection.abstraction.ConnectionPool;
import application.model.dao.connection.abstraction.DBConnection;
import application.model.exception.ModelException;
import application.util.PropertyReader;
import application.util.constants.ErrorMessages;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.UncheckedIOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConnectionPool implements ConnectionPool, ErrorMessages {
    private static final String CONNECTION_PROPERTIES =  "db"+File.separator+"connection.properties";
    private static final String DB_MAX_CONNECTIONS = "db.max";
    private static final String DB_URL = "db.url";
    private static final String DB_USER = "db.user";
    private static final String DB_PASSWORD = "db.password";
    private static final String DB_DRIVER = "db.driver";

    private DataSource dataSource;

    private static MySQLConnectionPool ourInstance = new MySQLConnectionPool();
    private static Logger logger = LogManager.getLogger(MySQLConnectionPool.class.getName());


    private MySQLConnectionPool() {
        this.dataSource = createDataSource(CONNECTION_PROPERTIES);
    }

    private DataSource createDataSource(String filename) {
        try {
            Properties properties = PropertyReader.readProperties(filename);
            String driver = properties.getProperty(DB_DRIVER);
            Class.forName(driver);

            BasicDataSource basicDataSource = new BasicDataSource();
            basicDataSource.setUrl(properties.getProperty(DB_URL));
            basicDataSource.setUsername(properties.getProperty(DB_USER));
            basicDataSource.setPassword(properties.getProperty(DB_PASSWORD));
            int maxConnectionNumber = Integer.parseInt(properties.getProperty(DB_MAX_CONNECTIONS));
            basicDataSource.setMaxTotal(maxConnectionNumber);
            return basicDataSource;
        } catch (ClassNotFoundException e) {
            logger.fatal(DRIVER_ERROR);
            e.printStackTrace();
            System.exit(1);
        } catch (UncheckedIOException e) {
            logger.fatal(FATAL_ERROR);
            e.printStackTrace();
        }
        return null;
    }

    public static MySQLConnectionPool getInstance() {
        return ourInstance;
    }

    @Override
    public synchronized DBConnection getConnection() {
        try {
            Connection connection = dataSource.getConnection();
            return new MySQLDBConnection(connection);
        } catch (SQLException e) {
            logger.error(CONNECTION_ERROR);
            e.printStackTrace();
            throw new ModelException(CONNECTION_ERROR);
        }
    }
}
