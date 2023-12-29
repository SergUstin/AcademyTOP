package com.compony.springexample.services;

import java.util.Collection;

public interface CRUDService<T> {
    T getById(Integer id);
    Collection<T> getAll();
    void creat(T item);
    void update(Integer id, T item);
    void delete(Integer id);
}
