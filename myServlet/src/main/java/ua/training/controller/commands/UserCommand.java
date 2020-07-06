package ua.training.controller.commands;

import org.apache.log4j.Logger;
import ua.training.controller.security.UserSessionSecurity;
import ua.training.model.dao.entity.User;
import ua.training.model.dao.entity.enums.RoleStatus;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static ua.training.controller.constants.RequestConstants.*;
import static ua.training.controller.constants.PageConstants.*;

public class UserCommand implements Command{

    private UserSessionSecurity userSessionSecurity;
    private UserService userService;
    private static final Logger log = Logger.getLogger(UserCommand.class);

    public UserCommand(UserSessionSecurity userSessionSecurity,UserService userService){
        this.userSessionSecurity = userSessionSecurity;
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
        User user = (User) session.getAttribute(USER_ATTRIBUTE);

        if (request.getMethod().equalsIgnoreCase(POST_METHOD)){
            return userPostMethodTopUpMoney(request,user);
        }if (user.getRole() == null){
            return LOGIN_PAGE;
        }else if (user.getRole().equals(RoleStatus.ROLE_USER.toString())){
            request.setAttribute(USER_ATTRIBUTE,user);
            return USER_PAGE;
        }else{
            return ADMIN_PAGE;
        }

    }

    /**
     * Post method for top up user`s money
     * @param request needed for setting attributes
     * @param user needed for attributes
     * @return string page for jsp
     */
    private String userPostMethodTopUpMoney(HttpServletRequest request,User user){
        String password = request.getParameter("password");
        int cardNumber = Integer.parseInt(request.getParameter("cardNumber"));
        int money = Integer.parseInt(request.getParameter("money"));

        if (userSessionSecurity.checkPassword(user,password) &&
                userService.topUpUserCardNumber(user,cardNumber,money)){
            user.setMoney(user.getMoney()+money);
            log.info("USER " + user.getUserName() + " has topped up " +  money + " money successfully!");
            request.setAttribute(ALERT_ATTRIBUTE, 1);
        }else {
            log.error("USER " + user.getUserName() + " can`t top up money,problem with user properties!");
            request.setAttribute(ALERT_ATTRIBUTE, 0);
        }
        request.setAttribute(USER_ATTRIBUTE, user);
        return USER_PAGE;
    }
}
