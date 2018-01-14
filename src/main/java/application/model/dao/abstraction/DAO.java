package application.model.dao.abstraction;

import application.util.constants.ErrorMessages;

public interface DAO<R, E> extends ErrorMessages {
    R create(E e);
}
