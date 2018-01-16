package application.controller.mapper;


public interface Mapper<T, P> {
    T map(P p);
}
