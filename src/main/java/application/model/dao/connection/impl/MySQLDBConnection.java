package application.model.dao.connection.impl;

import application.model.dao.connection.abstraction.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Connection wrapper implementation
 */

public class MySQLDBConnection implements DBConnection {
    private boolean isUsed;
    private Connection connection;

    public MySQLDBConnection(Connection connection){
        isUsed = false;
        this.connection = connection;
    }

    /**
     * @inheritDoc
     */

    @Override
    public boolean isUsed(){
        return isUsed;
    }

    /**
     * @inheritDoc
     */

    @Override
    public void setUsed(boolean isUsed){
        this.isUsed = isUsed;
    }

    /**
     * @inheritDoc
     */

    @Override
    public void setAutoCommit(boolean autoCommit) throws SQLException{
        connection.setAutoCommit(autoCommit);
    }

    /**
     * @inheritDoc
     */

    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException{
        return connection.prepareStatement(sql);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int returnGeneratedKeys) throws SQLException{
        return connection.prepareStatement(sql, returnGeneratedKeys);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Statement createStatement() throws  SQLException{
        return connection.createStatement();
    }

    /**
     * @inheritDoc
     */

    @Override
    public void setTransactionIsolation(int isolationLevel) throws SQLException{
        connection.setTransactionIsolation(isolationLevel);
    }

    /**
     * @inheritDoc
     */

    @Override
    public void commit() throws SQLException{
        connection.commit();
    }

    /**
     * @inheritDoc
     */

    @Override
    public void rollback() throws SQLException{
        connection.rollback();
    }

    /**
     * @inheritDoc
     */

    @Override
    public void close() throws SQLException{
        connection.close();
    }
}
