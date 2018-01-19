package application.controller.service.abstraction;

import application.model.entity.Product;

public interface ProductService extends Service<Product> {
    Integer add(Product product);
}
