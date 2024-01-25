package org.example.springexample.service;

import org.example.springexample.models.News;

import java.util.Collection;
import java.util.List;

public interface CRUDService<T> {
    T getById(Long id);
    Collection<T> getAll();
    T create(T item);
    T update(T item);
    void deleteById(Long id);
}
