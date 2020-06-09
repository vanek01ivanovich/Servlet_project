package ua.training.model.service;

import ua.training.controller.security.BcryptEncoder;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserService {

    private DaoFactory factory = DaoFactory.getInstance();
    private UserDao userDao = factory.createUserDao();
    private BcryptEncoder encoder = new BcryptEncoder();

    /**
     * Method that checks if user exists or not
     * @param userName needed for checking if user exists
     * @return boolean value if user exist
     */
    public boolean isExistUser(String userName){
        return userDao.isExistUser(userName);
    }

    /**
     * Method saves new user in db
     * @param userRequest needed for saving new user in db
     */
    public void saveNewUser(HttpServletRequest userRequest){
        userDao.saveNewUser(userRequest);
    }

    /**
     * Method finds all users in db
     * @return list of users
     */
    public List<User> findAllUsers(){
        return userDao.findAll();
    }

    /**
     * Method that can update user in db
     * @param newUser needed for updating user in db
     */
    public void updateUser(User newUser){
        userDao.update(newUser);
    }

    /**
     * Method that deletes user from db
     * @param user needed for deleting user from db
     */
    public void deleteUser(User user){userDao.delete(user);}

    /**
     * Method that tops up user`s money
     * @param user needed in order to top up money
     * @param cardNumber needed in order to top up money
     * @param money needed in order to top up money
     * @return boolean value if top up card number was successfully
     */
    public boolean topUpUserCardNumber(User user, int cardNumber,int money){
        return userDao.topUpCardNumber(user,cardNumber,money);
    }

    /**
     * Method that checks if cardNumber exists or not
     * @param cardNumber needed for checking if cardNumber exists
     * @return boolean value if cardNumber exist
     */
    public boolean isCardExist(String cardNumber) {
        return userDao.isCardExist(cardNumber);
    }



}
