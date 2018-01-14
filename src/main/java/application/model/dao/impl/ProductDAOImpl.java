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

public class ProductDAOImpl implements ProductDAO {
    private static Logger logger = LogManager.getLogger(ProductDAOImpl.class.getName());

    private static final String CREATE = "INSERT INTO PRODUCT(product_name, product_type) VALUES(?, ?)";

    @Override
    public Integer create(Product product) {
        try {
            PreparedStatement preparedStatement = TransactionManager.getInstance()
                    .getConnection()
                    .prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            int productId = insert(preparedStatement, product);
            if (productId > 0) {
                logger.info("New product is added: " + product);
            }else throw new ModelException(ADD_PRODUCT_ERROR);
            return productId;
        } catch (SQLException | ModelException e) {
            logger.error(ADD_PRODUCT_ERROR);
            throw new ModelException(ADD_PRODUCT_ERROR);
        }
    }

    private int insert(PreparedStatement preparedStatement, Product product) {
        try {
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getProductType());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new ModelException(ADD_PRODUCT_ERROR);
        }
    }
}
