package application.model.dao.connection.abstraction;

public interface ConnectionPool {
    /**
     * Returns connection from pool
     *
     * @return connection from pool
     */

    DBConnection getConnection();
}
