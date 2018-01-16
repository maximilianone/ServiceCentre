package application.controller.mapper.request;

import application.controller.mapper.Mapper;
import application.model.entity.Product;
import application.util.PropertyReader;
import application.util.constants.RequestParameters;

import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

public class ProductRequestMapper implements Mapper<Product, HttpServletRequest>, RequestParameters {
    @Override
    public Product map(HttpServletRequest request){

        String productName = request.getParameter(PRODUCT_NAME);
        String productType = request.getParameter(PRODUCT_TYPE);

        return new Product.Builder()
                .setProductName(productName)
                .setProductType(productType)
                .build();
    }
}
