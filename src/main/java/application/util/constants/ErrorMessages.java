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
    String ADD_USER_ERROR = "Error! Failed to add new user";
    String ADD_PRODUCT_ERROR = "Error! Failed to add new product";
    String INVALID_COMMAND = "Error! Invalid Command";
    String LOGIN_EXIST_ERROR = "Error! User with this login already exist, try other";

    String ADD_COMMENT_ERROR = "Error! Failed to add new comment";
    String ADD_COMMENT_WRONG_ORDER = "Error! You don't have such order";


    String USER_SELECT_ERROR = "Error! Failed to read user info from database";
    String ORDER_SELECT_ERROR = "Error! Failed to read order info from database";
    String COMMENT_SELECT_ERROR = "Error! Failed to read comment info from database";

    String ORDER_UPDATE_ERROR = "Error! Failed to update order";
    String PRODUCT_UPDATE_ERROR = "Error! Failed to update product";
    String USER_UPDATE_ERROR = "Error! Failed to update user";
    String COMMENT_UPDATE_ERROR = "Error! Failed to update comment";

    String FAILED_AUTHORIZATION = "Failed authorization attempt";
    String WRONG_AUTHORIZATION_INFO = "Wrong login or password";

    String WRONG_PASSWORD = "Wrong password!";
    String FAILED_ATTEMPT_CHANGE_PASSWORD = "Failed attempt to change password";

    String FAILED_ATTEMPT_CHANGE_STATUS = "Failed attempt to change status";
    String ALREADY_CHANGED = "This order's status has been already changed";
}
