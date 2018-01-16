package application.model.dao.transaction;

import application.model.dao.connection.abstraction.ConnectionPool;
import application.model.dao.connection.abstraction.DBConnection;
import application.model.dao.connection.impl.MySQLConnectionPool;
import application.model.exception.ModelException;
import application.util.constants.ErrorMessages;

import java.sql.SQLException;

public class TransactionManager implements ErrorMessages {
    private static TransactionManager ourInstance = new TransactionManager();

    public static TransactionManager getInstance() {
        return ourInstance;
    }
    private static ThreadLocal<DBConnection> connectionThreadLocal = new ThreadLocal<>();

    private TransactionManager() {
    }

    public static DBConnection getConnection(){
        return connectionThreadLocal.get();
    }

    public static void runTransaction(int isolationLevel){
        try {
            DBConnection connection = MySQLConnectionPool.getInstance().getConnection();
            connectionThreadLocal.set(connection);

            connection.setTransactionIsolation(isolationLevel);
            connection.setAutoCommit(false);
            connection.setUsed(true);

        } catch (SQLException e) {
            throw new ModelException(RUN_TRANSACTION_ERROR);
        }
    }

    public static void commit(){
        try {
            DBConnection connection = connectionThreadLocal.get();

            connection.commit();
            connection.setAutoCommit(true);
            connection.setUsed(false);
            connection.close();
        } catch (SQLException e) {
            throw new ModelException(COMMIT_ERROR);
        }
    }

    public static void rollback(){
        try {
            DBConnection connection = connectionThreadLocal.get();

            connection.rollback();
            connection.setAutoCommit(true);
            connection.setUsed(false);
            connection.close();
        } catch (SQLException e) {
            throw new ModelException(ROLLBACK_ERROR);
        }
    }
}
