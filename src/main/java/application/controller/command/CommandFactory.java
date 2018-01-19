package application.controller.command;

import application.controller.command.impl.InvalidCommand;
import application.controller.command.impl.commentCommand.AddCommentCommand;
import application.controller.command.impl.commentCommand.BannCommentCommand;
import application.controller.command.impl.commentCommand.GetAllCommentsCommand;
import application.controller.command.impl.orderCommand.AddOrderCommand;
import application.controller.command.impl.userCommand.*;
import application.controller.mapper.request.CommentRequestMapper;
import application.controller.mapper.request.OrderRequestMapper;
import application.controller.mapper.request.ProductRequestMapper;
import application.controller.mapper.request.UserRequestMapper;
import application.controller.mapper.result.CommentResultMapper;
import application.controller.mapper.result.FullOrderResultMapper;
import application.controller.mapper.result.UserResultMapper;
import application.controller.service.impl.CommentServiceImpl;
import application.controller.service.impl.OrderServiceImpl;
import application.controller.service.impl.UserServiceImpl;
import application.model.dao.impl.CommentDAOImpl;
import application.model.dao.impl.OrderDAOImpl;
import application.model.dao.impl.ProductDAOImpl;
import application.model.dao.impl.UserDAOImpl;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private final static String addOrderCommand = "addOrder";
    private final static String addUserCommand = "registration";
    private final static String addCommentCommand = "addComment";
    private final static String authorizationCommand = "login";
    private final static String logOutCommand = "logout";
    private final static String showComments = "showComments";
    private final static String banComment = "banComment";
    private final static String showPersonalPage = "showPersonalPage";
    private final static String changeInfo = "changePersonalInfo";
    private final static String changePassword = "changePassword";

    private static CommandFactory ourInstance = new CommandFactory();

    public static CommandFactory getInstance() {
        return ourInstance;
    }

    private Map<String, Command> commandMap;
    private Command invalidCommand;

    private CommandFactory() {
        invalidCommand = new InvalidCommand();

        commandMap = new HashMap<>();

        commandMap.put(addOrderCommand,
                new AddOrderCommand(
                        new OrderServiceImpl(new OrderDAOImpl(), new ProductDAOImpl()),
                        new ProductRequestMapper(),
                        new OrderRequestMapper()
                ));
        commandMap.put(addUserCommand,
                new AddUserCommand(
                        new UserServiceImpl(new UserDAOImpl()),
                        new UserRequestMapper()
                ));

        commandMap.put(authorizationCommand,
                new AuthorizationCommand(
                        new UserServiceImpl(new UserDAOImpl(new UserResultMapper()))
                ));

        commandMap.put(logOutCommand, new LogOutCommand());

        commandMap.put(showPersonalPage,
                new ShowPersonalPageCommand(
                        new UserServiceImpl(new UserDAOImpl(new UserResultMapper())),
                        new OrderServiceImpl(new OrderDAOImpl(new FullOrderResultMapper()))
                ));

        commandMap.put(changeInfo,
                new ChangeUserInfoCommand(
                        new UserServiceImpl(new UserDAOImpl())));

        commandMap.put(changePassword,
                new ChangePasswordCommand(
                        new UserServiceImpl(
                        new UserDAOImpl(new UserResultMapper())
                )));

        commandMap.put(addCommentCommand,
                new AddCommentCommand(
                        new CommentServiceImpl(new CommentDAOImpl()),
                        new CommentRequestMapper()
                ));

        commandMap.put(showComments,
                new GetAllCommentsCommand(
                        new CommentServiceImpl(new CommentDAOImpl(new CommentResultMapper()))
                ));

        commandMap.put(banComment,
                new BannCommentCommand(
                        new CommentServiceImpl(new CommentDAOImpl(new CommentResultMapper()))
                ));
    }

    public Command getCommand(String name) {
        return commandMap.getOrDefault(name, invalidCommand);
    }
}
