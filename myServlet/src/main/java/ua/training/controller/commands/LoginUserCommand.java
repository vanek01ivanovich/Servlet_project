package ua.training.controller.commands;


import org.apache.log4j.Logger;
import ua.training.controller.security.UserSessionSecurity;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.entity.User;
import ua.training.model.dao.entity.enums.RoleStatus;
import static ua.training.controller.constants.PageConstants.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;
import static ua.training.controller.constants.RequestConstants.*;

public class LoginUserCommand implements Command {
    private DaoFactory factory = DaoFactory.getInstance();
    private UserDao userDao = factory.createUserDao();
    private  UserSessionSecurity userSessionSecurity;
    private static final Logger log = Logger.getLogger(UserSessionSecurity.class);

    public LoginUserCommand(UserSessionSecurity userSessionSecurity) {
        this.userSessionSecurity = userSessionSecurity;
    }

    /**
     * Method that was implemented from interface Command
     * @param request needed to understand what method
     * @param response implemented from interface Command
     * @return string page depends on post or get method
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter(USER_NAME_PARAMETER);
        String password = request.getParameter(PASSWORD_ATTRIBUTE);
        HttpSession session = request.getSession();

        return request.getMethod().equalsIgnoreCase(POST_METHOD)?
                loginUserPostMethod(userName,password,session,request):LOGIN_PAGE;

    }

    /**
     * Login method for authentication users
     * Returns string page
     * @param userName needed for check login
     * @param password needed for check user password with password in db
     * @param session needed for adding user to loggedUsers
     * @param request needed for setting attributes
     * @return string page for jsp
     */
    private String loginUserPostMethod(String userName,String password,HttpSession session,HttpServletRequest request){
        Optional<User> userOptional = userDao.checkLogin(userName);

        if (userOptional.filter(user -> userSessionSecurity.checkPassword(userOptional.get(),password)).isPresent()){
            UserSessionSecurity.addLoggedUser(session,userOptional.get());
            request.setAttribute(REDIRECT_ATTRIBUTE,
                    userOptional.get().getRole().equals(RoleStatus.ROLE_USER.toString()) ? "/user":"/admin");
            return null;
        } else {
            request.setAttribute(ERROR_LOGIN_ATTRIBUTE,true);
            log.error("ERROR LOGIN WITH [ userName: " + userName + " ]");
            return LOGIN_PAGE;
        }
    }
}


