package application.util.constants;


public interface ErrorMessages {
    String ERROR_PAGE = "/jsp/error.jsp";

    String CONNECTION_ERROR = "Error! Cannot get connection";
    String FATAL_ERROR = "Fatal error";
    String DRIVER_ERROR = "Error! Cannot find driver";
    String PROPERTY_FILE_ERROR = "Error! Cannot read properties from file";
    String RUN_TRANSACTION_ERROR = "Error! Failed to start transaction";
    String COMMIT_ERROR = "Error! Failed to commit transaction";
    String ROLLBACK_ERROR = "Error! Failed to rollback transaction";
    String ADD_ORDER_ERROR = "Error! Failed to add new order";
    String ADD_PRODUCT_ERROR = "Error! Failed to add new product";
}
