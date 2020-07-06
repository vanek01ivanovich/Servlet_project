package ua.training.controller.commands;

import org.apache.log4j.Logger;
import ua.training.controller.constants.PageConstants;
import ua.training.controller.security.UserSessionSecurity;
import ua.training.model.dao.entity.DestinationProperty;
import ua.training.model.dao.entity.Ticket;
import ua.training.model.dao.entity.User;
import ua.training.model.service.TicketService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static ua.training.controller.constants.RequestConstants.*;
import static ua.training.controller.constants.PageConstants.*;

public class TicketCommand implements Command {
    private TicketService ticketService;
    private static String idProperty;
    private static DestinationProperty destinationProperty;
    private static final Logger log = Logger.getLogger(TicketCommand.class);

    public TicketCommand(TicketService ticketService){
        this.ticketService = ticketService;
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

        return request.getMethod().equalsIgnoreCase(POST_METHOD) ?
                ticketPostMethod(request,session,user) : ticketGetMethod(request,session,user);

    }

    /**
     * Post method for ticket service
     * @param request needed for setting attributes
     * @param session needed for realization in TicketService
     * @param user needed for attributes
     * @return string page for jsp
     */
    private String ticketPostMethod(HttpServletRequest request,HttpSession session,User user){
        if (!ticketService.addTicket(session,request,destinationProperty)){
            request.setAttribute(ALERT_ATTRIBUTE, 0);
            request.setAttribute(USER_ATTRIBUTE,user);
            request.setAttribute(TICKET_ATTRIBUTE, destinationProperty);
            log.error("USER " + user.getUserName() + "CAN`T BUY TICKET,NOT ENOUGH MONEY");
            return TICKET_PAGE;
        }
       /* request.setAttribute(REDIRECT_ATTRIBUTE,"/findroute");
        return null;*/
        request.setAttribute(ALERT_ATTRIBUTE, 1);
        request.setAttribute(USER_ATTRIBUTE,user);
        request.setAttribute(TICKET_ATTRIBUTE, destinationProperty);
        return TICKET_PAGE;

    }

    /**
     * Get method for ticket service
     * @param request needed for setting attributes
     * @param session needed for realization in TicketService
     * @param user needed for attributes
     * @return string page for jsp
     */
    private String ticketGetMethod(HttpServletRequest request,HttpSession session,User user){
        List<DestinationProperty> ticket = (List<DestinationProperty>) session.getAttribute(LIST_ROUTES_ATTRIBUTE);

        if (request.getParameter(ID_PROPERTY_PARAMETER) != null){
            idProperty = request.getParameter(ID_PROPERTY_PARAMETER);
        }

        destinationProperty = ticketService.getCurrentTicket(ticket, Integer.parseInt(idProperty));
        request.setAttribute(USER_ATTRIBUTE,user);
        request.setAttribute(TICKET_ATTRIBUTE, destinationProperty);
        return TICKET_PAGE;
    }

}

