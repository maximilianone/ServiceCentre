package application.controller.mapper;


import java.sql.SQLException;

public interface Mapper<T, P> {
    T map(P p) throws SQLException;
}
