package ua.training.model.dao.implement;

import ua.training.controller.security.BcryptEncoder;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.entity.User;
import ua.training.model.dao.entity.enums.RoleStatus;
import ua.training.model.dao.mapper.UserMapper;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class JDBCUserDao implements UserDao {
    private Connection connection;
    private UserMapper userMapper;
    private BcryptEncoder encoder = new BcryptEncoder();

    private ResourceBundle resourceBundle = ResourceBundle.getBundle("databaseRequest");

    private final String DELETE_USER = "delete.user";
    private final String FIND_ALL_USERS = "find.all.users";
    private final String UPDATE_USERS = "update.users";
    private final String FIND_EXIST_USERS = "find.exist.users";
    private final String FIND_EXIST_CARD_NUMBER = "find.exist.card.number";
    private final String ADD_NEW_USER = "add.new.user";

    JDBCUserDao(Connection connection) {
        this.connection = connection;
    }


    /**
     * Method make transaction of user money
     * @param user needed in order to top up money
     * @param cardNumber users cardNumber
     * @param money value that user wants to top up
     * @return boolean value if transaction was successfully
     */
    @Override
    public boolean topUpCardNumber(User user, int cardNumber,int money){
        PreparedStatement preparedStatement;

        try{

            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(resourceBundle.getString(FIND_EXIST_USERS),
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            preparedStatement.setString(1,user.getUserName());
            ResultSet resultSet = preparedStatement.executeQuery();
            int number = 0;
            if (resultSet.next()){
                number = resultSet.getInt("card_number");

                if (cardNumber != number){
                    return false;
                }else{
                    resultSet.updateInt("money",user.getMoney()+money);
                    resultSet.updateRow();
                }
            }
            connection.commit();
            connection.setAutoCommit(true);

        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return true;
    }

    /**
     * Method checks whether user`s cardNumber is exist
     * @param cardNumber needed for checking user`s cardNumber
     * @return boolean value that depends on
     */
    @Override
    public boolean isCardExist(String cardNumber) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(resourceBundle.getString(FIND_EXIST_CARD_NUMBER))){
            preparedStatement.setString(1,cardNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }


    /**
     * Method check if login is right
     * @param userName needed for authentication on login page
     * @return Optional value of User object
     */
    @Override
    public Optional<User> checkLogin(String userName){
        Optional<User> userOptional;
        try (PreparedStatement preparedStatement =
                connection.prepareStatement(resourceBundle.getString(FIND_EXIST_USERS))){
            preparedStatement.setString(1,userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            userMapper = new UserMapper();

            userOptional = Optional.ofNullable(resultSet.next() ? userMapper.extractFromResultSet(resultSet): null);

        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return userOptional;
    }

    /**
     * Method checks if user exists
     * Return boolean value
     * @param userName needed for checking if user exists
     * @return boolean value
     */
    @Override
    public boolean isExistUser(String userName) {
        try (PreparedStatement preparedStatement =
                connection.prepareStatement(resourceBundle.getString(FIND_EXIST_USERS))){
            preparedStatement.setString(1,userName);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @param request for filling preparedStatement values
     */
    @Override
    public void saveNewUser(HttpServletRequest request) {
        try (PreparedStatement preparedStatement =
                connection.prepareStatement(resourceBundle.getString(ADD_NEW_USER))){
                preparedStatement.setString(1,request.getParameter("firstName"));
                preparedStatement.setString(2,request.getParameter("lastName"));
                preparedStatement.setString(3,request.getParameter("ukrFirstName"));
                preparedStatement.setString(4,request.getParameter("ukrLastName"));
                preparedStatement.setString(5, RoleStatus.ROLE_USER.toString());
                preparedStatement.setString(6, encoder.hashPassword(request.getParameter("password")));
                preparedStatement.setString(7,request.getParameter("userName"));
                preparedStatement.setString(8,"0");
                preparedStatement.setString(9,request.getParameter("cardNumber"));
                preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void create(User entity) {

    }

    /**
     * Method finds all users in db
     * @return List of Users
     */
    @Override
    public List<User> findAll() {
        List<User> listUser = new ArrayList<>();
        try(PreparedStatement preparedStatement =
                connection.prepareStatement(resourceBundle.getString(FIND_ALL_USERS))){
            ResultSet resultSet = preparedStatement.executeQuery();

            userMapper = new UserMapper();

            while(resultSet.next()){
                User user =  userMapper.extractFromResultSet(resultSet);
                listUser.add(user);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return listUser;
    }

    /**
     * Method update user in db using preparedStatement
     * @param newUser needed for updating user in db
     */
    @Override
    public void update(User newUser) {
        try(PreparedStatement preparedStatement =
                connection.prepareStatement(resourceBundle.getString(UPDATE_USERS))){
            preparedStatement.setString(1,newUser.getUserName());
            preparedStatement.setString(2,newUser.getFirstName());
            preparedStatement.setString(3,newUser.getLastName());
            preparedStatement.setString(4,newUser.getRole());
            preparedStatement.setInt(5,newUser.getId());

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Method delete user from db
     * @param user needed for deleting value in db
     */
    @Override
    public void delete(User user) {
        try(PreparedStatement preparedStatement =
                connection.prepareStatement(resourceBundle.getString(DELETE_USER))){
            preparedStatement.setInt(1,user.getId());
            preparedStatement.executeUpdate();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
