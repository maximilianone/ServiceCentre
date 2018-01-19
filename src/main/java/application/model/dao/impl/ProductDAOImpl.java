package application.model.dao.impl;

import application.model.dao.abstraction.ProductDAO;
import application.model.dao.transaction.TransactionManager;
import application.model.entity.Product;
import application.model.exception.ModelException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class ProductDAOImpl implements ProductDAO {
    private static Logger logger = LogManager.getLogger(ProductDAOImpl.class.getName());

    private static final String CREATE = "INSERT INTO PRODUCT(product_name, product_type) VALUES(?, ?)";

    @Override
    public Integer create(Product product) {
        try {
            Map<Integer, Object> parameterMap = new HashMap<>();
            parameterMap.put(1, product.getProductName());
            parameterMap.put(2, product.getProductType());
            int newID = DAOTemplate.executeInsert(CREATE, parameterMap);
            logger.info("New product is added: " + product);
            return newID;
        } catch (SQLException | ModelException e) {
            logger.error(ADD_PRODUCT_ERROR);
            throw new ModelException(ADD_PRODUCT_ERROR);
        }
    }

    @Override
    public Boolean update(int productID, Object newValue, String fieldName) {
        try {
            boolean updateStatus = DAOTemplate.executeUpdate(createUpdateQuery(fieldName), productID, newValue);
            logger.info("Product with id " + productID + " updated");
            return updateStatus;
        } catch (ModelException e) {
            logger.error(PRODUCT_UPDATE_ERROR);
            throw new ModelException(PRODUCT_UPDATE_ERROR);
        }
    }

    private String createUpdateQuery(String fieldName) {
        return "Update PRODUCT SET " + fieldName + " = ? WHERE product_id = ?";
    }
}
