package application.controller.command;

import application.controller.command.impl.InvalidCommand;
import application.controller.command.impl.orderCommand.AddOrderCommand;
import application.controller.requestMapper.impl.OrderRequestMapper;
import application.controller.requestMapper.impl.ProductRequestMapper;
import application.controller.service.impl.OrderServiceImpl;
import application.controller.service.impl.ProductServiceImpl;
import application.model.dao.impl.OrderDAOImpl;
import application.model.dao.impl.ProductDAOImpl;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private final static String addCommand = "addOrder";
    private static CommandFactory ourInstance = new CommandFactory();

    public static CommandFactory getInstance() {
        return ourInstance;
    }
    private Map<String, Command> commandMap;
    private Command invalidCommand;

    private CommandFactory() {
        invalidCommand = new InvalidCommand();

        commandMap = new HashMap<>();

        commandMap.put(addCommand,
                new AddOrderCommand(
                        new ProductServiceImpl(new ProductDAOImpl()),
                        new OrderServiceImpl(new OrderDAOImpl()),
                        new ProductRequestMapper(),
                        new OrderRequestMapper()
                        ));
    }

    public Command getCommand(String name){
       return commandMap.getOrDefault(name, invalidCommand);
    }
}
