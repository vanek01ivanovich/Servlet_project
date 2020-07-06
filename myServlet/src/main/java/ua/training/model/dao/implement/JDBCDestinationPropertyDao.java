package ua.training.model.dao.implement;

import ua.training.App;
import ua.training.model.dao.DestinationPropertyDao;
import ua.training.model.dao.entity.Application;
import ua.training.model.dao.entity.DestinationProperty;
import ua.training.model.dao.entity.Destinations;
import ua.training.model.dao.entity.Train;
import ua.training.model.dao.mapper.PropertyMapper;
import ua.training.model.dao.mapper.DestinationsMapper;
import ua.training.model.dao.mapper.TrainMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class JDBCDestinationPropertyDao implements DestinationPropertyDao {
    private Connection connection;
    private PropertyMapper propertyMapper;
    private DestinationsMapper destinationsMapper;
    private TrainMapper trainMapper;

    private ResourceBundle resourceBundle = ResourceBundle.getBundle("databaseRequest");
    private final String FIND_DESTINATION_PROPERTY_BY_STATION_AND_DATE= "find.destination.property.by.station.and.date";
    private final String FIND_DESTINATION_PROPERTY_BY_UKRAINIAN_STATION_AND_DATE= "find.destination.property.by.ukrainian.station.and.date";

    JDBCDestinationPropertyDao(Connection connection){
        this.connection = connection;
    }

    @Override
    public void create(DestinationProperty entity) {}

    @Override
    public List<DestinationProperty> findAll() {
        return null;
    }

    @Override
    public void update(DestinationProperty entity) {}

    @Override
    public void delete(DestinationProperty entity) {}


    /**
     * Find english destinations by object Application
     * @param application needed for finding values from db
     * @return List of object DestinationProperty
     */
    @Override
    public List<DestinationProperty> findAllByApplication(Application application) {

        Map<Integer, DestinationProperty> destinationMap = new HashMap<>();
        try (PreparedStatement preparedStatement =
                connection.prepareStatement(resourceBundle.getString(FIND_DESTINATION_PROPERTY_BY_STATION_AND_DATE))){
            preparedStatement.setString(1,application.getDeparture());
            preparedStatement.setString(2,application.getArrival());
            findStaff(preparedStatement,destinationMap,application);
            return new ArrayList<>(destinationMap.values());
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Find ukrainian destinations by object Application
     * @param application needed for finding values from db
     * @return List of object DestinationProperty
     */
    @Override
    public List<DestinationProperty> findAllByUkrainianApplication(Application application) {

        Map<Integer, DestinationProperty> destinationMap = new HashMap<>();
        try (PreparedStatement preparedStatement =
                connection.prepareStatement(resourceBundle.getString(FIND_DESTINATION_PROPERTY_BY_UKRAINIAN_STATION_AND_DATE))){
            preparedStatement.setString(1,application.getDepartureUA());
            preparedStatement.setString(2,application.getArrivalUA());
            findStaff(preparedStatement,destinationMap,application);
            return new ArrayList<>(destinationMap.values());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Method that has access to Mappers
     * Method uses preparedStatement
     * @param preparedStatement needed for access to db
     * @param destinationMap needed for putting values to mappers
     * @param application needed for setting value in preparedStatement
     */
    private void findStaff(PreparedStatement preparedStatement,
                          Map<Integer, DestinationProperty> destinationMap,
                          Application application){
        try {
            preparedStatement.setString(3,application.getDateDeparture());
            ResultSet resultSet = preparedStatement.executeQuery();

            propertyMapper = new PropertyMapper();
            destinationsMapper = new DestinationsMapper();
            trainMapper = new TrainMapper();

            while (resultSet.next()) {
                DestinationProperty destinationProperty = propertyMapper.extractFromResultSet(resultSet);
                Destinations destination = destinationsMapper.extractFromResultSet(resultSet);
                Train train = trainMapper.extractFromResultSet(resultSet);

                destinationProperty.getDestinations().add(destination);
                destinationProperty.getTrains().add(train);

                propertyMapper.putValuesToMap(destinationMap, destinationProperty);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
