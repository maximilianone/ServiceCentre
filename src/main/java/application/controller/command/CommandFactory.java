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
import application.model.dao.abstraction.ProductDAO;
import application.model.dao.impl.CommentDAOImpl;
import application.model.dao.impl.OrderDAOImpl;
import application.model.dao.impl.ProductDAOImpl;
import application.model.dao.impl.UserDAOImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * command factory to determine commands from request
 */

public class CommandFactory implements CommadList{
    private static CommandFactory ourInstance = new CommandFactory();

    public static CommandFactory getInstance() {
        return ourInstance;
    }

    private Map<String, Command> commandMap;
    private Command invalidCommand;

    private CommandFactory() {
        OrderService orderService = new OrderServiceImpl(new OrderDAOImpl(new FullOrderResultMapper()), new ProductDAOImpl());

        UserService userService = new UserServiceImpl(new UserDAOImpl(new UserResultMapper()));
        CommentService commentService = new CommentServiceImpl(new CommentDAOImpl(new CommentResultMapper()));

        invalidCommand = new InvalidCommand();

        commandMap = new HashMap<>();

        commandMap.put(ADD_ORDER_COMMAND,
                new AddOrderCommand(orderService,
                        new ProductRequestMapper(),
                        new OrderRequestMapper()
                ));

        commandMap.put(CHANGE_ORDER_STATUS, new ChangeOrderStatusCommand(orderService));

        commandMap.put(CHANGE_ORDER_STATUS_AS_ADMIN, new ChangeOrderStatusAsAdminCommand(orderService));

        commandMap.put(SHOW_ALL_ORDERS, new ShowAllOrdersCommand(orderService));

        commandMap.put(SEARCH_ORDERS, new SearchOrdersCommand(orderService));

        commandMap.put(SHOW_ORDER, new ShowOrderCommand(orderService));

        commandMap.put(PROCESS_NEW_ORDER, new ProcessNewOrderCommand(orderService));

        commandMap.put(SHOW_MASTER_ORDERS, new ShowAllMasterOrdersCommand(orderService));

        commandMap.put(SEARCH_MASTER_ORDERS, new SearchMasterOrdersCommand(orderService));

        commandMap.put(CHANGE_ORDER_STATUS_AS_MASTER, new ChangeOrderStatusAsMasterCommand(orderService));

        commandMap.put(ADD_USER_COMMAND,
                new AddUserCommand(userService,
                        new UserRequestMapper()
                ));

        commandMap.put(SEARCH_USERS, new SearchUsersCommand(userService));

        commandMap.put(SHOW_ALL_USERS, new ShowAllUsersCommand(userService));

        commandMap.put(CHANGE_LOCALE, new ChangeLocaleCommand());

        commandMap.put(AUTHORIZATION_COMMAND, new AuthorizationCommand(userService));

        commandMap.put(LOG_OUT_COMMAND, new LogOutCommand());

        commandMap.put(SHOW_PERSONAL_PAGE, new ShowPersonalPageCommand(userService, orderService));

        commandMap.put(CHANGE_INFO, new ChangeUserInfoCommand(userService));

        commandMap.put(CHANGE_PASSWORD, new ChangePasswordCommand(userService));

        commandMap.put(CHANGE_USER_ROLE, new ChangeUserRoleCommand(userService));

        commandMap.put(ADD_COMMENT_COMMAND,
                new AddCommentCommand(commentService,
                        new CommentRequestMapper()
                ));

        commandMap.put(SHOW_COMMENTS, new GetAllCommentsCommand(commentService));

        commandMap.put(BAN_COMMENT, new BanCommentCommand(commentService));
    }

    public Command getCommand(String name) {
        return commandMap.getOrDefault(name, invalidCommand);
    }
}
