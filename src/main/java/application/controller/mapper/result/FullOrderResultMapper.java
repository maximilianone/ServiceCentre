package application.controller.mapper.result;

import application.controller.mapper.Mapper;
import application.model.entity.FullOrder;
import application.model.entity.Order;
import application.model.entity.Product;
import application.model.exception.ModelException;
import application.util.constants.DBParameters;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FullOrderResultMapper implements Mapper<FullOrder, ResultSet>, DBParameters{
    @Override
    public FullOrder map(ResultSet resultSet){
        try {
            Order order = new Order.Builder()
                    .setOrderID(resultSet.getInt(ORDER_ID))
                    .setUserID(resultSet.getInt(USER_ID))
                    .setProductID(resultSet.getInt(PRODUCT_ID))
                    .setManagerID(resultSet.getInt(MANAGER_ID))
                    .setMasterID(resultSet.getInt(MANAGER_ID))
                    .setProblemDescription(resultSet.getString(PROBLEM_DESCRIPTION))
                    .setRejectionReason(resultSet.getString(REJECTION_REASON))
                    .setPrice(resultSet.getDouble(PRICE))
                    .setDateOfPlacement(resultSet.getDate(DATE_OF_PLACEMENT).toLocalDate())
                    .setStatus(resultSet.getString(ORDER_STATUS))
                    .build();

            Product product = new Product.Builder()
                    .setId(resultSet.getInt(PRODUCT_ID))
                    .setProductName(resultSet.getString(PRODUCT_NAME))
                    .setProductType(resultSet.getString(PRODUCT_TYPE))
                    .build();

            return new FullOrder(order, product);
        } catch (SQLException e) {
            throw new ModelException(e);
        }
    }
}
