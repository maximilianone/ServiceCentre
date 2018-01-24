package application.controller.validation;

import application.controller.command.CommadList;
import application.model.entity.User;
import application.model.entity.User.Role;
import application.util.constants.RequestParameters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * access validator
 */

public class AccessValidator implements CommadList, RequestParameters {
    private static AccessValidator ourInstance = new AccessValidator();

    public static AccessValidator getInstance() {
        return ourInstance;
    }

    private Map<String, String> commandRolePair;

    private String guest;

    private AccessValidator() {
        guest = Role.GUEST.name() + "_";
        String client = Role.CLIENT.name() + "_";
        String master = Role.MASTER.name() + "_";
        String admin = Role.ADMIN.name() + "_";
        String main = Role.MAIN.name() + "_";

        commandRolePair = new HashMap<>();

        commandRolePair.put(ADD_ORDER_COMMAND, client + master + admin + main);

        commandRolePair.put(ADD_USER_COMMAND, guest);

        commandRolePair.put(ADD_COMMENT_COMMAND, client + master + admin + main);

        commandRolePair.put(AUTHORIZATION_COMMAND, guest);

        commandRolePair.put(CHANGE_LOCALE, guest + client + master + admin + main);

        commandRolePair.put(LOG_OUT_COMMAND, client + master + admin + main);

        commandRolePair.put(SHOW_COMMENTS, guest + client + master + admin + main);

        commandRolePair.put(BAN_COMMENT, admin + main);

        commandRolePair.put(SHOW_PERSONAL_PAGE, client + master + admin + main);

        commandRolePair.put(CHANGE_INFO, client + master + admin + main);

        commandRolePair.put(CHANGE_PASSWORD, client + master + admin + main);

        commandRolePair.put(CHANGE_ORDER_STATUS, client + master + admin + main);

        commandRolePair.put(CHANGE_ORDER_STATUS_AS_ADMIN, admin + main);

        commandRolePair.put(SEARCH_ORDERS, admin + main);

        commandRolePair.put(SHOW_ORDER, admin + main);

        commandRolePair.put(PROCESS_NEW_ORDER, admin + main);

        commandRolePair.put(SHOW_ALL_ORDERS, admin + main);

        commandRolePair.put(SEARCH_USERS, admin + main);

        commandRolePair.put(SHOW_ALL_USERS, admin + main);

        commandRolePair.put(CHANGE_USER_ROLE, main);

        commandRolePair.put(SHOW_MASTER_ORDERS, master);

        commandRolePair.put(SEARCH_MASTER_ORDERS, master);

        commandRolePair.put(CHANGE_ORDER_STATUS_AS_MASTER, master);
    }

    /**
     * check permission of user to call command
     * @param request request to server
     * @param command response to server
     * @return permission status
     */

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

