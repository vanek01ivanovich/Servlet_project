package ua.training.controller.commands;

import ua.training.controller.constants.PageConstants;
import ua.training.model.dao.entity.User;
import ua.training.model.service.PageService;
import ua.training.model.service.TicketService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static ua.training.controller.constants.RequestConstants.*;
import static ua.training.controller.constants.PageConstants.*;
import static ua.training.controller.constants.CommandsUrlConstants.*;

public class LookAllTicketsCommand implements Command {

    private TicketService ticketService;
   /* private static int numberOfTickets = 15;
    private static int start;
    private static int end=15;*/

    public LookAllTicketsCommand(TicketService ticketService){
        this.ticketService = ticketService;
    }

    /**
     * Method that was implemented from interface Command
     * Also method has pagination realization
     * @param request needed to understand what method
     * @param response implemented from interface Command
     * @return string page depends on post or get method
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<User> userAndTickets = ticketService.getAllUsersTickets();

        if (userAndTickets.size() >= 15){
            PageService pageService = new PageService(15,0,15);
            pageService.pagination(userAndTickets,request,ALL_TICKET_ATTRIBUTE);
        }else{
            request.setAttribute("pagination",false);
            request.setAttribute(ALL_TICKET_ATTRIBUTE,userAndTickets);
        }

        return ALL_TICKETS_PAGE;
    }




}
