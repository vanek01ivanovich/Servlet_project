package ua.training.controller.commands;

import org.apache.log4j.Logger;
import ua.training.controller.constants.PageConstants;
import ua.training.controller.security.UserSessionSecurity;
import ua.training.model.dao.entity.DestinationProperty;
import ua.training.model.service.PageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


import static ua.training.controller.constants.RequestConstants.*;
import static ua.training.controller.constants.PageConstants.*;
import static ua.training.controller.constants.CommandsUrlConstants.*;

public class RoutesCommand implements Command {


    /**
     * Method that was implemented from interface Command
     * Also has pagination realization
     * @param request needed to understand what method
     * @param response implemented from interface Command
     * @return string page depends on post or get method
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)  {

        HttpSession session = request.getSession();
        List<?> routes = (List<?>) session.getAttribute(LIST_ROUTES_ATTRIBUTE);

        PageService pageService = new PageService(3, 0, Math.min(routes.size(), 3));
        pageService.pagination(routes,request,LIST_ROUTES_ATTRIBUTE);

        return ROUTES_PAGE;

    }
}
