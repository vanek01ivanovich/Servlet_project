package ua.training.controller.commands;

import org.apache.log4j.Logger;
import ua.training.controller.constants.PageConstants;
import ua.training.controller.constants.RequestConstants;
import ua.training.controller.security.UserSessionSecurity;
import ua.training.model.dao.entity.User;
import ua.training.model.service.UserService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import static ua.training.controller.constants.RequestConstants.*;
import static ua.training.controller.constants.PageConstants.*;

public class EditUserCommand implements Command {

    private static final Logger log = Logger.getLogger(UserSessionSecurity.class);

    private UserService userService;
    private static User user;

    public EditUserCommand(UserService userService) {
        this.userService = userService;
    }


    /**
     * Method that was implemented from interface Command
     * @param request needed to understand what method
     * @param response implemented from interface Command
     * @return string page depends on post or get method
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        return  request.getMethod().equalsIgnoreCase(POST_METHOD) ?
                editUserPostMethod(request):editUserGetMethod(request,session);

    }

    /**
     * Returns string page
     * Method sets attribute list of users except admin
     * @param request in order to set attributes to jsp
     * @param session in order to get attributes
     * @return admin page in order to edit user info
     */
    private String editUserGetMethod(HttpServletRequest request,HttpSession session){
        List<User> userList = (List<User>) session.getAttribute(USERS_LIST_ATTRIBUTES);
        user = userList.stream()
                .filter(u -> u.getId() == Integer.parseInt(request.getParameter(RequestConstants.USER_ID_PARAMETER)))
                .findAny()
                .orElse(null);
        request.setAttribute(USER_ATTRIBUTE, user);
        return EDIT_USER_PAGE;
    }


    /**
     * Post method of EditUser method
     * If there`re no errors in user request than returns null and redirect to
     * /admin/allUsers page, if not return edit user page
     * @param request in order to set attributes to jsp
     * @return string page
     */
    private String editUserPostMethod(HttpServletRequest request){
        String newUserName = request.getParameter(USER_NAME_PARAMETER);
        if ((userService.isExistUser(newUserName) && !newUserName.equals(user.getUserName()))
                || request.getAttribute(REGEX_ATTRIBUTE).equals(FALSE_ATTRIBUTE)){
            //user.setUserName(newUserName);
            request.setAttribute(ALERT_ATTRIBUTE, 0);
            log.error("USER " + user.getUserName() + " CAN`T BE EDITED, PROBLEMS WITH INFO!");
            request.setAttribute(USER_ATTRIBUTE, user);
            return EDIT_USER_PAGE;
        }else{
            getNewUser(request);
            userService.updateUser(user);
            request.setAttribute(REDIRECT_ATTRIBUTE,"/admin/allUsers");
            return null;
        }
    }


    /**
     * Method set fields to user entity
     * @param request in order to get attributes from user request
     */
    private void getNewUser(HttpServletRequest request){
        user.setUserName(request.getParameter(USER_NAME_PARAMETER));
        user.setFirstName(request.getParameter(FIRST_NAME_PARAMETER));
        user.setLastName(request.getParameter(LAST_NAME_PARAMETER));
        user.setFirstNameUkr(request.getParameter(UKR_FIRST_NAME_PARAMETER));
        user.setLastNameUkr(request.getParameter(UKR_LAST_NAME_PARAMETER));
        user.setRole(request.getParameter(ROLE_PARAMETER));
    }
}
