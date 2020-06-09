package ua.training.model.dao.mapper;

import ua.training.model.dao.entity.DestinationProperty;
import ua.training.model.dao.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements ObjectMapper<User> {
    @Override
    public User extractFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("idusers"));
        user.setUserName(resultSet.getString("user_name"));
        user.setPassword(resultSet.getString("password"));
        user.setRole(resultSet.getString("role"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setLastNameUkr(resultSet.getString("last_name_ukr"));
        user.setFirstNameUkr(resultSet.getString("first_name_ukr"));
        user.setMoney(resultSet.getInt("money"));
        user.setCardNumber(resultSet.getInt("card_number"));
        return user;
    }

    @Override
    public void putValuesToMap(Map<Integer, User> emptyEntity, User entity) {

    }

    public void adminTransaction(ResultSet resultSet, DestinationProperty property) throws SQLException {
        int adminMoney = resultSet.getInt("money") + property.getPrice();
        resultSet.updateInt("money",adminMoney);
        resultSet.updateRow();

    }

    public void userTransaction(ResultSet resultSet, DestinationProperty property,User user) throws SQLException {
        int userMoney = resultSet.getInt("money") - property.getPrice();
        resultSet.updateInt("money",userMoney);
        resultSet.updateRow();
        user.setMoney(userMoney);
    }

    public void userAdminTransaction(ResultSet resultSet,int money) throws SQLException {

        resultSet.updateInt("money",money);
        resultSet.updateRow();

    }


}
