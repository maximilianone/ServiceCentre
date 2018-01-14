package application.controller.requestMapper.impl;

import application.controller.requestMapper.RequestMapper;
import application.model.entity.Product;
import application.util.PropertyReader;
import application.util.constants.RequestParameters;

import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

public class ProductRequestMapper implements RequestMapper<Product>, RequestParameters {
    @Override
    public Product map(HttpServletRequest request){

        Properties properties = PropertyReader.readProperties(PARAMETERS);

        String productName = request.getParameter(properties.getProperty(PRODUCT_NAME));
        String productType = request.getParameter(properties.getProperty(PRODUCT_TYPE));

        return new Product(productName, productType);
    }
}
