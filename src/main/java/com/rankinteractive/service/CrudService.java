package com.rankinteractive.service;

import java.util.List;
import java.util.Optional;

/**
 * @author anelechila
 * @since 11/03/2020
 * @version 1.0
 */
public interface CrudService<T, ID> {

    List<T> findAll();

    T findById(ID id);

    T save(T object);

    void delete(T object);

    void deleteById(ID id);

}
