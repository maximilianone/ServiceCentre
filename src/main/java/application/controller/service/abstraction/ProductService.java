package application.controller.service.abstraction;

import application.model.entity.Product;

public interface ProductService extends Service {
    Integer add(Product product);
}
