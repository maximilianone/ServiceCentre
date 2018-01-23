package application.model.dao.abstraction;

import application.util.constants.DBParameters;
import application.util.constants.ErrorMessages;


public interface DAO<E> extends ErrorMessages, DBParameters {
    Integer create(E e);

    /**
     * update entity
     * @param id id of entity to update
     * @param newValue new value of field
     * @param fieldName field name
     * @return status of update
     */

    Boolean update(int id, Object newValue, String fieldName);

}
