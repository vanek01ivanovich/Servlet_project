package ua.training.controller.filters;

import org.apache.log4j.Logger;
import ua.training.controller.constants.RegexPatternConstants;
import ua.training.controller.security.UserSessionSecurity;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ua.training.controller.constants.RequestConstants.*;

public class RegistrationRegexFilter implements Filter, RegexPatternConstants {

    private static final Logger log = Logger.getLogger(UserSessionSecurity.class);

    private String firstName;
    private String userName;
    private String lastName;
    private String firstNameUkr;
    private String lastNameUkr;
    private String cardNumber;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (request.getMethod().equalsIgnoreCase(POST_METHOD)){
            getAllRequestParameters(request);
            if (isValid(request)){
                request.setAttribute(REGEX_ATTRIBUTE,TRUE_ATTRIBUTE);
                filterChain.doFilter(request,response);
            }else{
                request.setAttribute(REGEX_ATTRIBUTE,FALSE_ATTRIBUTE);
                log.error("REGEX ERROR AT URL " + request.getRequestURI());
                filterChain.doFilter(request,response);
            }

        }else{
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    private boolean isValid(HttpServletRequest request){


        boolean validFirstName = nameSurnamePattern.matcher(firstName).matches();
        boolean validLastName = nameSurnamePattern.matcher(lastName).matches();
        boolean validFirstNameUkr = nameSurnameUkrPattern.matcher(firstNameUkr).matches();
        boolean validLastNameUkr = nameSurnameUkrPattern.matcher(lastNameUkr).matches();
        boolean validUserName = userNamePattern.matcher(userName).matches();
        boolean validUserCardNumber = userCardNumberPattern.matcher(cardNumber).matches();


        request.setAttribute("validFirstName", validFirstName);
        request.setAttribute("validLastName", validLastName);
        request.setAttribute("validFirstNameUkr", validFirstNameUkr);
        request.setAttribute("validLastNameUkr", validLastNameUkr);
        request.setAttribute("validUserName", validUserName);
        request.setAttribute("validUserCardNumber", validUserCardNumber);

        if (request.getRequestURI().contains("/registration")){
            return (validFirstName && validFirstNameUkr && validLastName && validLastNameUkr && validUserName && validUserCardNumber);
        }else{
            return (validFirstName && validFirstNameUkr && validLastName && validLastNameUkr && validUserName);
        }


    }

    private void getAllRequestParameters(HttpServletRequest request){
        firstName = request.getParameter("firstName") != null ? request.getParameter("firstName"):"";
        userName = request.getParameter("userName") != null ? request.getParameter("userName"):"";
        lastName = request.getParameter("lastName") != null ? request.getParameter("lastName"):"";
        firstNameUkr = request.getParameter("ukrFirstName") != null ? request.getParameter("ukrFirstName"):"";
        lastNameUkr = request.getParameter("ukrLastName") != null ? request.getParameter("ukrLastName"):"";
        cardNumber = request.getParameter("cardNumber") != null ? request.getParameter("cardNumber"):"";
    }

    @Override
    public void destroy() {

    }


}
