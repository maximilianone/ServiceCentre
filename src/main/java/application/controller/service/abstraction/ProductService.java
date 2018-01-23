package application.controller.service.abstraction;

import application.model.entity.Product;

public interface ProductService extends Service {
    /**
     * add new product
     *
     * @param product product to add
     * @return id of added product
     */

    Integer add(Product product);
}
