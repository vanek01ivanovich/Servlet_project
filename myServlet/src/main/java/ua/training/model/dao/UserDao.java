package ua.training.model.dao;

import ua.training.model.dao.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface UserDao extends GenericDao<User>{
    Optional<User> checkLogin(String userName);
    boolean isExistUser(String userName);
    void saveNewUser(HttpServletRequest request);
    boolean topUpCardNumber(User user, int cardNumber,int money);

    boolean isCardExist(String cardNumber);
}

