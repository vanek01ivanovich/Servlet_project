package ua.training.controller.filters;


import javafx.scene.control.Tab;
import org.apache.log4j.Logger;
import ua.training.controller.security.UserSessionSecurity;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import static ua.training.controller.constants.RequestConstants.*;
import static ua.training.controller.constants.PageConstants.*;
import static ua.training.controller.constants.CommandsUrlConstants.*;

public class LocaleFilter implements Filter {

    private static final Logger log = Logger.getLogger(UserSessionSecurity.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        servletResponse.setContentType("text/html");
        servletResponse.setCharacterEncoding("UTF-8");
        servletRequest.setCharacterEncoding("UTF-8");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (request.getSession().getAttribute(LANG_ATTRIBUTE) == null) {
            request.getSession().setAttribute(LANG_ATTRIBUTE,ENGLISH_ATTRIBUTE);
            log.info("LOCALE SWITCHED TO " + ENGLISH_ATTRIBUTE);
        }

        if (request.getParameter(LANG_ATTRIBUTE) != null){
            request.getSession().setAttribute(LANG_ATTRIBUTE,request.getParameter(LANG_ATTRIBUTE));
            log.info("LOCALE SWITCHED TO " + request.getParameter(LANG_ATTRIBUTE));
        }

        filterChain.doFilter(request, response);


    }

    @Override
    public void destroy() {

    }
}
