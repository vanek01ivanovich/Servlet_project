package ua.training.controller.commands;


import org.apache.log4j.Logger;
import ua.training.controller.security.UserSessionSecurity;
import ua.training.model.dao.entity.DestinationProperty;
import ua.training.model.service.ApplicationService;
import ua.training.model.service.DestinationPropertyService;
import static ua.training.controller.constants.PageConstants.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import static ua.training.controller.constants.RequestConstants.*;


public class FindRouteCommand implements Command {

    private HttpSession session;
    private DestinationPropertyService destinationPropertyService;
    private ApplicationService applicationService;
    private static final Logger log = Logger.getLogger(UserSessionSecurity.class);

    public FindRouteCommand(DestinationPropertyService destinationPropertyService,ApplicationService applicationService){
        this.destinationPropertyService = destinationPropertyService;
        this.applicationService = applicationService;
    }

    /**
     * Method that was implemented from interface Command
     * @param request needed to understand what method
     * @param response implemented from interface Command
     * @return string page depends on post or get method
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        session = request.getSession();

        return request.getMethod().equalsIgnoreCase(POST_METHOD) ? findRoutePostMethod(request):FIND_ROUTE_PAGE;
    }


    /**
     * Post method for findRouteCommand
     * Returns string page with list of destinations
     * Also check for errors
     * @param request in order to set attributes for jsp
     * @return string page jsp
     */
    private String findRoutePostMethod(HttpServletRequest request){
        List<DestinationProperty> destinationProperties = session.getAttribute(LANG_ATTRIBUTE).equals(ENGLISH_ATTRIBUTE) ?
                destinationPropertyService.getDestinations( applicationService.addApplication(request)) :
                destinationPropertyService.getDestinationsByUkrainianApplication(applicationService.addApplication(request));

        if (!destinationProperties.isEmpty()){
            request.setAttribute(PAGE_ATTRIBUTE,request.getParameter(PAGE_ATTRIBUTE));
            request.setAttribute(EMPTY_MESSAGE_ATTRIBUTE,false);
            request.setAttribute(REDIRECT_ATTRIBUTE,"/routes");
            session.setAttribute(LIST_ROUTES_ATTRIBUTE,destinationProperties);
            return null;
        }else{
            log.info("ERROR LIST ROUTES ON " + request.getParameter("date") +
                    " FROM " + request.getParameter("stationFrom") +
                    " TO " + request.getParameter("stationTo") + " IS EMPTY");
            request.setAttribute(EMPTY_MESSAGE_ATTRIBUTE,true);
            return FIND_ROUTE_PAGE;
        }

    }

}
