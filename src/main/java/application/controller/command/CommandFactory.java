package application.controller.command;

import application.controller.command.impl.InvalidCommand;
import application.controller.command.impl.commentCommand.AddCommentCommand;
import application.controller.command.impl.commentCommand.BanCommentCommand;
import application.controller.command.impl.commentCommand.GetAllCommentsCommand;
import application.controller.command.impl.orderCommand.*;
import application.controller.command.impl.userCommand.*;
import application.controller.mapper.request.CommentRequestMapper;
import application.controller.mapper.request.OrderRequestMapper;
import application.controller.mapper.request.ProductRequestMapper;
import application.controller.mapper.request.UserRequestMapper;
import application.controller.mapper.result.CommentResultMapper;
import application.controller.mapper.result.FullOrderResultMapper;
import application.controller.mapper.result.UserResultMapper;
import application.controller.service.abstraction.*;
import application.controller.service.impl.CommentServiceImpl;
import application.controller.service.impl.OrderServiceImpl;
import application.controller.service.impl.UserServiceImpl;
import application.model.dao.impl.CommentDAOImpl;
import application.model.dao.impl.OrderDAOImpl;
import application.model.dao.impl.ProductDAOImpl;
import application.model.dao.impl.UserDAOImpl;
import application.model.entity.Order;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory implements CommadList{
    private static CommandFactory ourInstance = new CommandFactory();

    public static CommandFactory getInstance() {
        return ourInstance;
    }

    private Map<String, Command> commandMap;
    private Command invalidCommand;

    private CommandFactory() {
        OrderService orderServiceWithoutResult = new OrderServiceImpl(new OrderDAOImpl(), new ProductDAOImpl());
        OrderService orderService = new OrderServiceImpl(new OrderDAOImpl(new FullOrderResultMapper()));
        UserService userServiceWithoutResult = new UserServiceImpl(new UserDAOImpl());
        UserService userService = new UserServiceImpl(new UserDAOImpl(new UserResultMapper()));
        CommentService commentService = new CommentServiceImpl(new CommentDAOImpl(new CommentResultMapper()));

        invalidCommand = new InvalidCommand();

        commandMap = new HashMap<>();

        commandMap.put(addOrderCommand,
                new AddOrderCommand(orderServiceWithoutResult,
                        new ProductRequestMapper(),
                        new OrderRequestMapper()
                ));

        commandMap.put(changeOrderStatus, new ChangeOrderStatusCommand(orderService));

        commandMap.put(changeOrderStatusAsAdmin, new ChangeOrderStatusAsAdminCommand(orderService));

        commandMap.put(showAllOrders, new ShowAllOrdersCommand(orderService));

        commandMap.put(searchOrders, new SearchOrdersCommand(orderService));

        commandMap.put(showOrder, new ShowOrderCommand(orderService));

        commandMap.put(processNewOrder, new ProcessNewOrderCommand(orderService));

        commandMap.put(showMasterOrders, new ShowAllMasterOrdersCommand(orderService));

        commandMap.put(searchMasterOrders, new SearchMasterOrdersCommand(orderService));

        commandMap.put(changeOrderStatusAsMaster, new ChangeOrderStatusAsMasterCommand(orderService));

        commandMap.put(addUserCommand,
                new AddUserCommand(userServiceWithoutResult,
                        new UserRequestMapper()
                ));

        commandMap.put(searchUsers, new SearchUsersCommand(userService));

        commandMap.put(showAllUsers, new ShowAllUsersCommand(userService));

        commandMap.put(changeLocale, new ChangeLocaleCommand());

        commandMap.put(authorizationCommand, new AuthorizationCommand(userService));

        commandMap.put(logOutCommand, new LogOutCommand());

        commandMap.put(showPersonalPage, new ShowPersonalPageCommand(userService, orderService));

        commandMap.put(changeInfo, new ChangeUserInfoCommand(userServiceWithoutResult));

        commandMap.put(changePassword, new ChangePasswordCommand(userService));

        commandMap.put(changeUserRole, new ChangeUserRoleCommand(userService));

        commandMap.put(addCommentCommand,
                new AddCommentCommand(commentService,
                        new CommentRequestMapper()
                ));

        commandMap.put(showComments, new GetAllCommentsCommand(commentService));

        commandMap.put(banComment, new BanCommentCommand(commentService));
    }

    public Command getCommand(String name) {
        return commandMap.getOrDefault(name, invalidCommand);
    }
}
