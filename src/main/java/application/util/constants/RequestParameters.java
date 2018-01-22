package application.util.constants;

public interface RequestParameters {
    String PARAMETERS = "parameters.properties";

    String USER_ID = "userID";
    String USER_FIRST_NAME = "firstName";
    String USER_LAST_NAME ="lastName";
    String USER_LOGIN ="login";
    String USER_OLD_PASSWORD = "oldPassword";
    String USER_PASSWORD ="password";
    String USER_EMAIL ="email";
    String USER_PHONE ="phone";
    String USER_ROLE = "role";

    String PRODUCT_ID = "productID";
    String PRODUCT_NAME = "productName";
    String PRODUCT_TYPE = "productType";

    String PROBLEM_DESCRIPTION = "problemDescription";
    String ORDER_ID = "orderID";
    String ORDER_STATUS = "status";
    String ORDER_PRICE = "price";
    String ORDER_REJECTION_REASON = "rejectionReason";

    String COMMENT_CONTENT = "commentContent";
    String COMMENT_ID = "commentID";

    String SEARCH_PARAMETER = "searchParam";
    String SEARCH_VALUE = "searchVar";

    String ACCEPTED = "accepted";
    String REJECTED = "rejected";
    String WAITING_FOR_MASTER = "waiting_for_master";
    String RESERVED_BY_MASTER = "reserved_by_master";
    String PERFORMED = "performed";

    String AS_ADMIN = "admin";
    String AS_CUSTOMER = "customer";

    String USER_FOR_STATUS_CHANGE = "user";

    String OLD_ORDER_STATUS = "oldStatus";
    String ORDER_STATUS_NEW = "new";

    String OLD_USER_ROLE = "oldRole";
    String NEW_USER_ROLE = "newRole";
    String UPDATED_USER_ID = "updatedUserID";

    String MASTER = "master";

    String LOCALE = "session_locale";

}
