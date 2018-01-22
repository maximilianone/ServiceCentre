package application.controller.command;

public interface CommadList {
    String addOrderCommand = "addOrder";
    String addUserCommand = "registration";
    String addCommentCommand = "addComment";

    String changeLocale = "changeLocale";
    String authorizationCommand = "login";
    String logOutCommand = "logout";

    String showComments = "showComments";
    String banComment = "banComment";

    String showPersonalPage = "showPersonalPage";
    String changeInfo = "changePersonalInfo";
    String changePassword = "changePassword";
    String changeOrderStatus = "changeOrderStatus";

    String changeOrderStatusAsAdmin = "changeOrderStatusAsAdmin";
    String searchOrders = "searchOrders";
    String showOrder = "showOrder";
    String processNewOrder = "processNewOrder";
    String showAllOrders = "showAllOrders";

    String searchUsers = "searchUsers";
    String showAllUsers = "showAllUsers";
    String changeUserRole = "changeUserRole";

    String showMasterOrders = "showMasterOrders";
    String searchMasterOrders = "searchMasterOrders";
    String changeOrderStatusAsMaster = "changeOrderStatusAsMaster";
}
