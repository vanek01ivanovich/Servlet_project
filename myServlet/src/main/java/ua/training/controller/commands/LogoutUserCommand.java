package ua.training.controller.commands;

import ua.training.controller.constants.PageConstants;
import ua.training.controller.security.UserSessionSecurity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ua.training.controller.constants.RequestConstants.*;
import static ua.training.controller.constants.PageConstants.*;
import static ua.training.controller.constants.CommandsUrlConstants.*;

public class LogoutUserCommand implements Command{

    private UserSessionSecurity userSessionSecurity;

    public LogoutUserCommand(UserSessionSecurity userSessionSecurity) {
        this.userSessionSecurity = userSessionSecurity;
    }

    /**
     * Method that was implemented from interface Command
     * @param request needed to understand what method
     * @param response implemented from interface Command
     * @return string page depends on post or get method
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        userSessionSecurity.removeUserSession(session);
        request.setAttribute(LOGOUT_ATTRIBUTE,true);
        return LOGIN_PAGE;

    }
}
