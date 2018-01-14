package application.controller.command;

import application.controller.command.impl.InvalidCommand;
import application.controller.command.impl.commentCommand.AddCommentCommand;
import application.controller.command.impl.orderCommand.AddOrderCommand;
import application.controller.command.impl.userCommand.AddUserCommand;
import application.controller.requestMapper.impl.CommentRequestMapper;
import application.controller.requestMapper.impl.OrderRequestMapper;
import application.controller.requestMapper.impl.ProductRequestMapper;
import application.controller.requestMapper.impl.UserRequestMapper;
import application.controller.service.impl.CommentServiceImpl;
import application.controller.service.impl.OrderServiceImpl;
import application.controller.service.impl.ProductServiceImpl;
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
                        new ProductServiceImpl(new ProductDAOImpl()),
                        new OrderServiceImpl(new OrderDAOImpl()),
                        new ProductRequestMapper(),
                        new OrderRequestMapper()
                        ));
        commandMap.put(addUserCommand,
                new AddUserCommand(
                        new UserServiceImpl(new UserDAOImpl()),
                        new UserRequestMapper()
        ));

        commandMap.put(addCommentCommand,
                new AddCommentCommand(
                        new CommentServiceImpl(new CommentDAOImpl()),
                        new CommentRequestMapper()
                ));
    }

    public Command getCommand(String name){
       return commandMap.getOrDefault(name, invalidCommand);
    }
}
