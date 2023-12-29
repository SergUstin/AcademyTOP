package com.compony.springexample.service;

import java.util.Collection;

public interface CRUDService<T> {
    T getById(Long id);
    Collection<T> getAll();
    void create(T item);
    void updateById(Long id, T item);
    void deleteById(Long id);
}
