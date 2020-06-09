package ua.training.controller.filters;

import org.apache.log4j.Logger;
import ua.training.controller.security.UserSessionSecurity;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.entity.User;
import ua.training.model.dao.entity.enums.RoleStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.stream.Stream;

import static ua.training.controller.constants.RequestConstants.*;
import static ua.training.controller.constants.PageConstants.*;
import static ua.training.controller.constants.CommandsUrlConstants.*;

public class AuthFilter implements Filter {

    private HttpSession session;
    private static final Logger log = Logger.getLogger(UserSessionSecurity.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;
        session = req.getSession();

        HttpSession session = req.getSession();
        String currentUrl = req.getRequestURI();
        boolean urlCheck = currentUrl.equals("/") || currentUrl.equals("/login") || currentUrl.equals("/registration");

        User user = (User) session.getAttribute(USER_ATTRIBUTE);

        if (!urlCheck && session.getAttribute(LOGIN_ATTRIBUTE) == null){
            log.error("USER ANONYMOUS TRIED TO REDIRECT TO "
                    + currentUrl + ", PERMISSION DENIED, LOGIN FIRST!");
            res.sendRedirect("/login");

        }else if (urlCheck && session.getAttribute(LOGIN_ATTRIBUTE) != null){
            log.error("USER " + user.getUserName() + " TRIED TO REDIRECT TO " + currentUrl + ", PERMISSION DENIED,LOGOUT FIRST!");
            res.sendRedirect(user.getRole().equals(RoleStatus.ROLE_USER.toString()) ? "/user":"/admin");

        }else if(currentUrl.equals("/admin") && user.getRole().equals(RoleStatus.ROLE_USER.toString())){
            log.error("USER " + user.getUserName() + " TRIED TO REDIRECT TO "
                    + currentUrl + " WITH ROLE " + user.getRole() +", PERMISSION DENIED!");
            res.sendRedirect("/user");

        }else if(currentUrl.equals("/user") && user.getRole().equals(RoleStatus.ROLE_ADMIN.toString())){
            log.error("USER " + user.getUserName() + " TRIED TO REDIRECT TO "
                    + currentUrl + " WITH ROLE " + user.getRole() +", PERMISSION DENIED!");
            res.sendRedirect("/admin");
        }
        else {
            filterChain.doFilter(request, response);
        }


    }
    @Override
    public void destroy() {

    }
}
