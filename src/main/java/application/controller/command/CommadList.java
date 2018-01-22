package application.controller.command;

public interface CommadList {
    String ADD_ORDER_COMMAND = "addOrder";
    String ADD_USER_COMMAND = "registration";
    String ADD_COMMENT_COMMAND = "addComment";

    String CHANGE_LOCALE = "changeLocale";
    String AUTHORIZATION_COMMAND = "login";
    String LOG_OUT_COMMAND = "logout";

    String SHOW_COMMENTS = "showComments";
    String BAN_COMMENT = "banComment";

    String SHOW_PERSONAL_PAGE = "showPersonalPage";
    String CHANGE_INFO = "changePersonalInfo";
    String CHANGE_PASSWORD = "changePassword";
    String CHANGE_ORDER_STATUS = "changeOrderStatus";

    String CHANGE_ORDER_STATUS_AS_ADMIN = "changeOrderStatusAsAdmin";
    String SEARCH_ORDERS = "searchOrders";
    String SHOW_ORDER = "showOrder";
    String PROCESS_NEW_ORDER = "processNewOrder";
    String SHOW_ALL_ORDERS = "showAllOrders";

    String SEARCH_USERS = "searchUsers";
    String SHOW_ALL_USERS = "showAllUsers";
    String CHANGE_USER_ROLE = "changeUserRole";

    String SHOW_MASTER_ORDERS = "showMasterOrders";
    String SEARCH_MASTER_ORDERS = "searchMasterOrders";
    String CHANGE_ORDER_STATUS_AS_MASTER = "changeOrderStatusAsMaster";
}
