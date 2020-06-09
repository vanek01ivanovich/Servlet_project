package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.DestinationPropertyDao;
import ua.training.model.dao.entity.Application;
import ua.training.model.dao.entity.DestinationProperty;

import java.util.List;


public class DestinationPropertyService {
    private DaoFactory factory = DaoFactory.getInstance();
    private DestinationPropertyDao destinationPropertyDao = factory.createDestinationDao();

    /**
     *
     * @param application needed for finding english applications
     * @return List of destinationProperty
     */
    public List<DestinationProperty> getDestinations(Application application){
        return destinationPropertyDao.findAllByApplication(application);
    }

    /**
     * @param application  needed for finding ukrainian applications
     * @return List of destinationProperty
     */
    public List<DestinationProperty> getDestinationsByUkrainianApplication(Application application){
        return destinationPropertyDao.findAllByUkrainianApplication(application);
    }


}
