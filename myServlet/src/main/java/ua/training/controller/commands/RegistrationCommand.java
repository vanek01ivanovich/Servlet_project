package ua.training.controller.commands;

import org.apache.log4j.Logger;
import ua.training.controller.constants.PageConstants;
import ua.training.controller.security.UserSessionSecurity;
import ua.training.model.dao.entity.User;
import ua.training.model.service.UserService;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ua.training.controller.constants.RequestConstants.*;
import static ua.training.controller.constants.PageConstants.*;
import static ua.training.controller.constants.CommandsUrlConstants.*;

public class RegistrationCommand implements Command {

    private UserService userService;
    private  User user;
    private static final Logger log = Logger.getLogger(UserSessionSecurity.class);

    public RegistrationCommand(UserService userService){
        this.userService = userService;
    }

    /**
     * Method that was implemented from interface Command
     * @param request needed to understand what method
     * @param response implemented from interface Command
     * @return string page depends on post or get method
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){

        return request.getMethod().equalsIgnoreCase(POST_METHOD) ?
                registrationPostMethod(request) : registrationGetMethod(request);

    }

    /**
     * Get method for registration
     * @param request needed for setting attributes
     * @return string page for jsp
     */
    private String registrationGetMethod(HttpServletRequest request){
        user = new User();
        request.setAttribute(USER_ATTRIBUTE, user);
        return REGISTRATION_PAGE;
    }


    /**
     * Post method for registration
     * Also has checking for error regex and exist user
     * @param request needed for setting attributes
     * @return string page for jsp
     */
    private String registrationPostMethod(HttpServletRequest request){
        String userName = request.getParameter(USER_NAME_PARAMETER);
        String cardNumber = request.getParameter("cardNumber");
        fillUserInfo(request);

        if ((userService.isExistUser(userName))) {
            request.setAttribute(ALERT_ATTRIBUTE, 0);
            log.error("ERROR USERNAME { " + userName + " } IS ALREADY EXISTED!");
        } else if (userService.isCardExist(cardNumber)) {
            request.setAttribute(ALERT_ATTRIBUTE, 2);
            log.error("ERROR CARD { " + cardNumber + " } IS ALREADY EXISTED!");
        } else if (request.getAttribute(REGEX_ATTRIBUTE).equals(FALSE_ATTRIBUTE)) {
            request.setAttribute(ALERT_ATTRIBUTE, 1);
            log.error("ERROR REGEX!");
        } else {
            userService.saveNewUser(request);
            request.setAttribute(REDIRECT_ATTRIBUTE, "/login");
            return null;
        }
        request.setAttribute(USER_ATTRIBUTE, user);
        return REGISTRATION_PAGE;
    }

    /**
     * Method set fields to user entity
     * @param request in order to get attributes from user request
     */
    private void fillUserInfo(HttpServletRequest request){
        user = new User.Builder()
                .setUserName(request.getParameter("userName"))
                .setFirstName(request.getParameter("firstName"))
                .setFirstNameUkr(request.getParameter("ukrFirstName"))
                .setLastName(request.getParameter("lastName"))
                .setLastNameUkr(request.getParameter("ukrLastName"))
                .setCardNumber(Integer.parseInt(request.getParameter("cardNumber")))
                .build();
    }


}
