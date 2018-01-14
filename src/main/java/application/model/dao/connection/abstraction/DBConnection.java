package application.model.dao.connection.abstraction;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * connection wrapper
 */

public interface DBConnection extends AutoCloseable{
    /**
     * check if connection is used
     *
     * @return true if connection is used
     */

    boolean isUsed();

    /**
     * set that connection is used or not
     *
     * @param isUsed connection is used or not
     */

    void setUsed(boolean isUsed);

    PreparedStatement prepareStatement(String sql) throws SQLException;

    /**
     * Prepare sql statement
     *
     * @param sql statement
     * @param returnGeneratedKeys
     * @return prepared statement
     * @throws SQLException if cannot prepare statement
     */

    PreparedStatement prepareStatement(String sql, int returnGeneratedKeys) throws SQLException;

    /**
     * create new sql statement
     * @return sql statement
     * @throws SQLException if failed to create statement
     */
    Statement createStatement() throws SQLException;
    /**
     * Sets this connection's auto-commit mode to the given state
     *
     * @param autoCommit state of auto-commit
     * @throws SQLException if failed to set new mode of auto-commit
     */

    void setAutoCommit(boolean autoCommit) throws SQLException;

    /**
     * sets transaction isolation level
     *
     * @param isolationLevel transaction isolation
     * @throws SQLException if failed to set transaction isolation level
     */

    void setTransactionIsolation(int isolationLevel) throws SQLException;

    /**
     * commit transaction
     *
     * @throws SQLException if failed to commit transaction
     */

    void commit() throws SQLException;

    /**
     * rollback transaction
     *
     * @throws SQLException if failed to rollback transaction
     */

    void rollback() throws SQLException;

    /**
     * close connection
     *
     * @throws SQLException if failed to close connection
     */

    @Override
    void close() throws SQLException;

}
