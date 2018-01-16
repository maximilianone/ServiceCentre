package application.controller.service.impl;

import application.controller.mapper.result.FullOrderResultMapper;
import application.controller.mapper.result.UserResultMapper;
import application.controller.service.abstraction.OrderService;
import application.controller.service.abstraction.UserService;
import application.model.dao.abstraction.UserDAO;
import application.model.dao.impl.OrderDAOImpl;
import application.model.dao.impl.UserDAOImpl;

public class Test {
    public static void main(String[] args) {
        OrderService userService = new OrderServiceImpl(new OrderDAOImpl(new FullOrderResultMapper()));
        System.out.println(userService.getAll());
    }
}
