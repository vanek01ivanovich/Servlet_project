package ua.training.model.service;

import ua.training.model.dao.ApplicationDao;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.entity.Application;
import ua.training.model.dao.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ApplicationService {
    private DaoFactory factory = DaoFactory.getInstance();
    private ApplicationDao applicationDao = factory.createApplicationDao();
    private Application application = new Application();


    /**
     * Method for managing application request
     * @param request needed for extracting parameters from user request
     * @return Application object after applicationService
     */
    public Application addApplication(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String departure = request.getParameter("stationFrom");
        String arrival = request.getParameter("stationTo");
        String date = request.getParameter("date");

        application.setDateDeparture(date);
        application.setIdUser(user.getId());

        if (session.getAttribute("lang").equals("en")){
            application.setArrival(arrival);
            application.setDeparture(departure);
            return applicationDao.addApplication(application);
        }else{
            application.setArrivalUA(arrival);
            application.setDepartureUA(departure);
            return applicationDao.addUkrainianApplication(application);
        }



    }
}
