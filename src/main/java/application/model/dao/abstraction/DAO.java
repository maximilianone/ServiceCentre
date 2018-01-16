package application.model.dao.abstraction;

import application.util.constants.ErrorMessages;


public interface DAO<E> extends ErrorMessages {
    Integer create(E e);

}
