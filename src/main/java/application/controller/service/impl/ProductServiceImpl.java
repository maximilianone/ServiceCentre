package application.controller.service.impl;

import application.controller.service.abstraction.ProductService;
import application.model.dao.abstraction.DAO;
import application.model.dao.abstraction.ProductDAO;
import application.model.dao.impl.ProductDAOImpl;
import application.model.dao.transaction.TransactionManager;
import application.model.entity.Product;
import application.model.exception.ModelException;

import java.sql.Connection;

public class ProductServiceImpl implements ProductService {
    private DAO<Integer, Product> productDao = new ProductDAOImpl();

    public ProductServiceImpl(ProductDAO productDao) {
        this.productDao = productDao;
    }

    @Override
    public int addProduct(Product product) throws ModelException{
        TransactionManager.runTransaction(Connection.TRANSACTION_READ_COMMITTED);
        int productId = productDao.create(product);
        TransactionManager.commit();
        return productId;
    }
}
