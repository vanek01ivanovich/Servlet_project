package ua.training.model.dao;

import ua.training.model.dao.entity.DestinationProperty;
import ua.training.model.dao.entity.User;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> {
    void create(T entity);
    List<T> findAll();
    void update(T entity);
    void delete(T entity);
}
