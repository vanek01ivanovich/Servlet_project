package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.TicketDao;
import ua.training.model.dao.entity.DestinationProperty;
import ua.training.model.dao.entity.Ticket;
import ua.training.model.dao.entity.User;
import ua.training.model.dao.implement.JDBCTicketDao;
import ua.training.model.dao.mapper.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

public class TicketService {
    private DaoFactory factory = DaoFactory.getInstance();
    private TicketDao ticketDao = factory.createTicketDao();
    private Ticket ticket = new Ticket();

    /**
     * Method that find needed ticket in list of destinationProperty object and return it
     * @param destinationProperties list object
     * @param idProperty number of needed ticket
     * @return DestinationProperty object
     */
    public DestinationProperty getCurrentTicket(List<DestinationProperty> destinationProperties,int idProperty){
            for (DestinationProperty dest : destinationProperties) {
                if (dest.getIdProperty() == idProperty) {
                    return dest;
                }
            }
       return null;
    }

    /**
     * Method that add ticket to db
     * @param session needed for setting attribute user in session
     * @param request needed for getting parameter
     * @param destinationProperty object that will be added to the ticket
     * @return
     */
    public boolean addTicket(HttpSession session, HttpServletRequest request,DestinationProperty destinationProperty) {
        User user = (User) session.getAttribute("user");
        int ticketPrice = destinationProperty.getPrice();
        if (ticketPrice > user.getMoney()){
            return false;
        }
        int idDestinationProperty = Integer.parseInt(request.getParameter("idProperty"));
        ticket.setIdUser(user.getId());
        ticket.setIdDestinationProperty(idDestinationProperty);
        ticketDao.addNewTicketTransaction(ticket,user,destinationProperty);
        session.setAttribute("user",user);
        return true;
    }

    /**
     * Method find and return all users
     * @return list of users
     */
    public List<User> getAllUsersTickets(){
        return ticketDao.findAllUsersAndTickets();

    }

}
