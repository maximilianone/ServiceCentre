package application.controller.command;

import application.controller.command.impl.InvalidCommand;
import application.controller.command.impl.orderCommand.ChangeOrderStatusCommand;
import application.controller.mapper.result.FullOrderResultMapper;
import application.controller.service.abstraction.OrderService;
import application.controller.service.impl.OrderServiceImpl;
import application.model.dao.impl.OrderDAOImpl;
import application.model.dao.impl.ProductDAOImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandFactoryTest {
    private CommandFactory commandFactory = CommandFactory.getInstance();

    @Test
    public void shouldGetCommand() throws Exception {
        assertEquals(commandFactory.getCommand(CommadList.CHANGE_ORDER_STATUS).getClass(), ChangeOrderStatusCommand.class);
    }

    @Test
    public void shouldGetInvalidCommand() throws Exception{
        assertEquals(commandFactory.getCommand("fsdf").getClass(), InvalidCommand.class);
    }

}