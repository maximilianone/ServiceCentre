package application.controller.service.abstraction;

public interface Service<P> {
    Boolean update(int id, Object newValue, String fieldName);
}
