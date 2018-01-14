package application.model.dao.abstraction;

import application.model.entity.Product;

public interface ProductDAO extends DAO<Integer, Product> {
    @Override
    Integer create(Product product);
}
