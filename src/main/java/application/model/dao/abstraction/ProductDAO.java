package application.model.dao.abstraction;

import application.model.entity.Product;

public interface ProductDAO extends DAO<Product> {
    /**
     * create new product
     * @param product product to create
     * @return id pf created product
     */
    @Override
    Integer create(Product product);

}
