package application.controller.validation;

import application.controller.command.CommadList;
import application.model.entity.User.Role;
import application.util.constants.RequestParameters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class AccessValidator implements CommadList, RequestParameters {
    private static AccessValidator ourInstance = new AccessValidator();

    public static AccessValidator getInstance() {
        return ourInstance;
    }

    private Map<String, String> commandRolePair;

    private String guest;
    private String client;
    private String master;
    private String admin;
    private String main;

    private AccessValidator() {
        guest = Role.GUEST.name() + "_";
        client = Role.CLIENT.name() + "_";
        master = Role.MASTER.name() + "_";
        admin = Role.ADMIN.name() + "_";
        main = Role.MAIN.name()+ "_";

        commandRolePair = new HashMap<>();

        commandRolePair.put(addOrderCommand, client + master + admin + main);

        commandRolePair.put(addUserCommand, guest);

        commandRolePair.put(addCommentCommand, client + master + admin + main);

        commandRolePair.put(authorizationCommand, guest);

        commandRolePair.put(changeLocale, guest + client + master + admin + main);

        commandRolePair.put(logOutCommand, client + master + admin + main);

        commandRolePair.put(showComments, guest + client + master + admin + main);

        commandRolePair.put(banComment, admin + main);

        commandRolePair.put(showPersonalPage, client + master + admin + main);

        commandRolePair.put(changeInfo, client + master + admin + main);

        commandRolePair.put(changePassword, client + master + admin + main);

        commandRolePair.put(changeOrderStatus, client + master + admin + main);

        commandRolePair.put(changeOrderStatusAsAdmin, admin + main);

        commandRolePair.put(searchOrders, admin + main);

        commandRolePair.put(showOrder, admin + main);

        commandRolePair.put(processNewOrder, admin + main);

        commandRolePair.put(showAllOrders, admin + main);

        commandRolePair.put(searchUsers, admin + main);

        commandRolePair.put(showAllUsers, admin + main);

        commandRolePair.put(changeUserRole, main);

        commandRolePair.put(showMasterOrders, master);

        commandRolePair.put(searchMasterOrders, master);

        commandRolePair.put(changeOrderStatusAsMaster, master);
    }

    public boolean checkAccess(HttpServletRequest request, String command) {
        HttpSession session = request.getSession();

        if (session == null || session.getAttribute(USER_ROLE) == null) {
            return commandRolePair.get(command).contains(guest);

        } else {
            String role = (String) session.getAttribute(USER_ROLE);

            return commandRolePair.get(command).contains(role);
        }
    }
}

