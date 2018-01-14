package application.controller.requestMapper.impl;

import application.controller.requestMapper.RequestMapper;
import application.model.entity.Order;
import application.util.PropertyReader;
import application.util.constants.RequestParameters;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Properties;

public class OrderRequestMapper implements RequestMapper<Order>, RequestParameters {
    @Override
    public Order map(HttpServletRequest request){
        Properties properties = PropertyReader.readProperties(PARAMETERS);

        String problemDescription = request.getParameter(properties.getProperty(PROBLEM_DESCRIPTION));
        int user_id = 1;

        return new Order(user_id, problemDescription, LocalDate.now());
    }


}
