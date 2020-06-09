package ua.training.model.dao;

import ua.training.model.dao.entity.DestinationProperty;
import ua.training.model.dao.entity.Ticket;
import ua.training.model.dao.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface TicketDao extends GenericDao<Ticket> {
    List<User> findAllUsersAndTickets();
    void addNewTicketTransaction(Ticket ticket, User user, DestinationProperty property);
}
