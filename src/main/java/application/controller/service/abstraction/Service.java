package application.controller.service.abstraction;

public interface Service{
    /**
     * update entity
     * @param id id of entity to update
     * @param newValue parameter value
     * @param fieldName parameter name
     * @return status of update
     */
    Boolean update(int id, Object newValue, String fieldName);
}
