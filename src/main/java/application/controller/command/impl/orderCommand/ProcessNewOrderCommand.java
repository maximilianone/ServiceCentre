package application.controller.command.impl.orderCommand;

import application.controller.command.Command;
import application.controller.service.abstraction.OrderService;
import application.controller.validation.OrderStatusChangeValidator;
import application.model.dto.FullOrder;
import application.model.exception.ModelException;
import application.util.constants.DBParameters;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * command to process new order
 */

public class ProcessNewOrderCommand implements Command, DBParameters {
    private OrderService orderService;

    public ProcessNewOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     *@inheritDoc
     */

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            String status = request.getParameter(ORDER_STATUS);

            OrderStatusChangeValidator.validateStatusChange(ORDER_STATUS_NEW, status);

            int orderID = Integer.parseInt(request.getParameter(ORDER_ID));
            int userID = Integer.parseInt(request.getParameter(USER_ID));
            Object value;
            String param;
            if (status.equals(ACCEPTED)) {
                param = DB_PRICE;
                value = Double.parseDouble(request.getParameter(ORDER_PRICE));
            } else {
                param = DB_REJECTION_REASON;
                value = request.getParameter(ORDER_REJECTION_REASON);
            }

            processNewOrder(orderID, userID, param, value, status);

            getOrder(orderID, request, response);
    }

    /**
     * change information of order with status new
     *
     * @param orderID order id of order to change
     * @param userID admin id of admin who is processing new order
     * @param param parameter to add
     * @param value value of parameter to add
     * @param status new status of order
     */

    private void processNewOrder(int orderID, int userID, String param, Object value, String status){
        orderService.processNewOrder(orderID, userID, param, value, status);
    }

    /**
     * get updated information about changed order
     *
     * @param orderID order id of order to get information
     * @param request request to server
     * @param response response to server
     * @throws IOException when failed to send redirect
     * @throws ServletException when failed to get dispatcher
     */

    private void getOrder(int orderID, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        FullOrder order = orderService.getBy(orderID, DB_ORDER_ID).get(0);

        request.setAttribute("order", order);
        request.getRequestDispatcher("/jsp/orderInfo.jsp").forward(request, response);
    }
}