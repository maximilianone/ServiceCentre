package application.controller.mapper.result;

import application.controller.mapper.Mapper;
import application.model.dto.FullOrder;
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
                    .setOrderID(resultSet.getInt(DB_ORDER_ID))
                    .setUserID(resultSet.getInt(DB_USER_ID))
                    .setProductID(resultSet.getInt(PRODUCT_ID))
                    .setManagerID(resultSet.getInt(DB_MANAGER_ID))
                    .setMasterID(resultSet.getInt(DB_MANAGER_ID))
                    .setProblemDescription(resultSet.getString(PROBLEM_DESCRIPTION))
                    .setRejectionReason(resultSet.getString(DB_REJECTION_REASON))
                    .setPrice(resultSet.getDouble(DB_PRICE))
                    .setDateOfPlacement(resultSet.getDate(DATE_OF_PLACEMENT).toLocalDate())
                    .setStatus(resultSet.getString(DB_ORDER_STATUS))
                    .build();

            Product product = new Product.Builder()
                    .setId(resultSet.getInt(PRODUCT_ID))
                    .setProductName(resultSet.getString(PRODUCT_NAME))
                    .setProductType(resultSet.getString(PRODUCT_TYPE))
                    .build();

            String login = resultSet.getString(DB_LOGIN);
            String managerLogin = "";
            String masterLogin = "";

            if(resultSet.getInt(DB_MANAGER_ID)>0){
                managerLogin = resultSet.getString(DB_ADMIN_LOGIN);
            }

            if(resultSet.getInt(DB_MASTER_ID)>0){
                masterLogin = resultSet.getString(DB_MASTER_LOGIN);
            }


            return new FullOrder(order, product, login, masterLogin, managerLogin);
        } catch (SQLException e) {
            throw new ModelException(e);
        }
    }
}
