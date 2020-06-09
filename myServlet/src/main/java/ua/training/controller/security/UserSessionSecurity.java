package ua.training.controller.security;

import org.apache.log4j.Logger;
import ua.training.model.dao.entity.User;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;

public class UserSessionSecurity {
    private BcryptEncoder encoder = new BcryptEncoder();
    private static final Logger log = Logger.getLogger(UserSessionSecurity.class);

    public void removeUserSession(HttpSession session){
        User user = (User)session.getAttribute("user");
        log.info("USER " + user.getUserName() + " has logged out successfully");
        session.removeAttribute("login");
        session.removeAttribute("user");
        session.removeAttribute("listRoutes");
        session.invalidate();


    }

    public static void addLoggedUser(HttpSession session, User user){
        session.setAttribute("login", true);
        session.setAttribute("user", user);
        log.info("USER " + user.getUserName() + " has logged successfully!");
    }

    public boolean checkPassword(User user,String password){

        return encoder.checkPass(password, user.getPassword());
    }
}
