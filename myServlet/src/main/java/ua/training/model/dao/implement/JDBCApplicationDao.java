package ua.training.model.dao.implement;

import ua.training.App;
import ua.training.model.dao.ApplicationDao;
import ua.training.model.dao.entity.Application;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import java.util.ResourceBundle;

public class JDBCApplicationDao implements ApplicationDao {

    private ResourceBundle resourceBundle = ResourceBundle.getBundle("databaseRequest");

    private final String ADD_APPLICATION = "add.application";
    private final String ADD_UKRAINIAN_APPLICATION = "add.ukrainian.application";


    private Connection connection;

    JDBCApplicationDao(Connection connection){this.connection = connection;}

    @Override
    public void create(Application entity) {}

    @Override
    public List<Application> findAll() {
        return null;
    }

    @Override
    public void update(Application entity) {}

    @Override
    public void delete(Application entity) {}

    /**
     * Returns Application object
     * Method has connection to db and also uses preparedStatement for query
     * @param application needed for filling application in db
     * @return Application object
     */
    @Override
    public Application addApplication(Application application) {
        try(PreparedStatement preparedStatement =
                connection.prepareStatement(resourceBundle.getString(ADD_APPLICATION))){
                preparedStatement.setInt(1,application.getIdUser());
                preparedStatement.setString(2,application.getDeparture());
                preparedStatement.setString(3,application.getArrival());
                preparedStatement.setString(4,application.getDateDeparture());
                preparedStatement.executeUpdate();

        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return application;
    }

    /**
     * Returns ukrainian Application object
     * Method has connection to db and also uses preparedStatement for query
     * @param application needed for filling application in db
     * @return ukrainian Application object
     */
    @Override
    public Application addUkrainianApplication(Application application) {
        try (PreparedStatement preparedStatement =
                connection.prepareStatement(resourceBundle.getString(ADD_UKRAINIAN_APPLICATION))){
                preparedStatement.setInt(1,application.getIdUser());
                preparedStatement.setString(2,application.getDepartureUA());
                preparedStatement.setString(3,application.getArrivalUA());
                preparedStatement.setString(4,application.getDateDeparture());
                preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return application;
    }


}
