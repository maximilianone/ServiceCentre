package application.controller.validation;

import application.model.entity.Order.Status;
import application.model.exception.ModelException;
import application.util.constants.ErrorMessages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * order status change validator
 */

public class OrderStatusChangeValidator implements ErrorMessages{
    private static OrderStatusChangeValidator ourInstance = new OrderStatusChangeValidator();

    public static OrderStatusChangeValidator getInstance() {
        return ourInstance;
    }

    private static Logger logger = LogManager.getLogger(OrderStatusChangeValidator.class.getName());

    private OrderStatusChangeValidator() {
    }

    /**
     * validate status change
     * @param oldStatus old status
     * @param newStatus new status
     */


    public static boolean validateStatusChange(String oldStatus, String newStatus){
        Status oStatus = validateStatus(oldStatus);
        Status nStatus = validateStatus(newStatus);

        switch (oStatus){
            case NEW:
                if(nStatus==Status.ACCEPTED || nStatus ==Status.REJECTED){
                    return true;
                }
            case ACCEPTED:
                if(nStatus==Status.AGREED || nStatus ==Status.CLOSED){
                    return true;
                }
            case AGREED:
                if(nStatus==Status.WAITING_FOR_MASTER || nStatus ==Status.CLOSED){
                    return true;
                }
            case WAITING_FOR_MASTER:
                if(nStatus==Status.RESERVED_BY_MASTER || nStatus ==Status.CLOSED){
                    return true;
                }
            case RESERVED_BY_MASTER:
                if(nStatus==Status.PERFORMED || nStatus ==Status.CLOSED || nStatus ==Status.WAITING_FOR_MASTER){
                    return true;
                }
            case PERFORMED:
                if(nStatus==Status.FULFILLED || nStatus ==Status.CLOSED){
                    return true;
                }
            case FULFILLED:
                if(nStatus ==Status.CLOSED){
                    return true;
                }
            case REJECTED:
            case CLOSED:
                logger.error(INVALID_STATUS_CHANGE);
                throw new ModelException(INVALID_STATUS_CHANGE);
        }
        return false;
    }

    /**
     * validate status
     * @param status to validate
     * @return status if valid
     */

    private static Status validateStatus(String status){
        try {
            return Status.valueOf(status.toUpperCase());
        } catch (java.lang.IllegalArgumentException e) {
            logger.error(INVALID_STATUS);
            throw new ModelException(INVALID_STATUS);
        }
    }
}
