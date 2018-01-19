package application.model.dao.abstraction;

import application.util.constants.DBParameters;
import application.util.constants.ErrorMessages;


public interface DAO<E> extends ErrorMessages, DBParameters {
    Integer create(E e);

    Boolean update(int id, Object newValue, String fieldName);

}
