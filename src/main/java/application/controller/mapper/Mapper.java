package application.controller.mapper;



public interface Mapper<T, P> {
    /**
     * map P to get T
     * @param p object to map
     * @return mapped object
     */
    T map(P p);
}
