package ua.training.model.service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static ua.training.controller.constants.RequestConstants.*;

public class PageService {

    private int numberOfTickets;
    private int start;
    private int end;

    public PageService(int numberOfTickets,int start,int end){
        this.numberOfTickets = numberOfTickets;
        this.start = start;
        this.end = end;
    }

    /**
     * Method for realization pagination in jsp forms
     * @param items needed for pagination like list of objects
     * @param request needed for setting attributes in jsp forms
     * @param pageJSP needed for setting string page in jsp forms
     */
    public void pagination(List<?> items, HttpServletRequest request,String pageJSP){
        int pages = items.size() / numberOfTickets;
        if (items.size() % numberOfTickets != 0){
            pages++;
        }

        if (request.getParameter(PAGE_ATTRIBUTE) != null){
            start = numberOfTickets*(Integer.parseInt(request.getParameter(PAGE_ATTRIBUTE))-1);
            end = pages == Integer.parseInt(request.getParameter(PAGE_ATTRIBUTE)) ? items.size() : start+numberOfTickets;
        }

        request.setAttribute("pagination",true);
        request.setAttribute(PAGINATION_LENGTH_ATTRIBUTE, pages);
        request.setAttribute(pageJSP, items.subList(start,end));
    }

}
