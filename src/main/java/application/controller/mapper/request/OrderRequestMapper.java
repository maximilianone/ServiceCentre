package application.controller.mapper.request;

import application.controller.mapper.Mapper;
import application.model.entity.Order;
import application.util.PropertyReader;
import application.util.constants.RequestParameters;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Properties;

public class OrderRequestMapper implements Mapper<Order, HttpServletRequest>, RequestParameters {
    @Override
    public Order map(HttpServletRequest request){

        String problemDescription = request.getParameter(PROBLEM_DESCRIPTION);
        int user_id = (Integer) request.getSession().getAttribute(USER_ID);
        return new Order.Builder()
                .setProblemDescription(problemDescription)
                .setUserID(user_id)
                .setDateOfPlacement(LocalDate.now())
                .build();
    }


}
