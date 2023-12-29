package com.compony.springexample.services;

import com.compony.springexample.dto.CommentDto;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.TreeMap;

@Service
public class CommentsCrudServiceImpl implements CRUDService<CommentDto> {

    private TreeMap<Integer, CommentDto> storage = new TreeMap<>();

    @Override
    public CommentDto getById(Integer id) {
        System.out.println("Get by ID: " + id);
        return storage.get(id);
    }

    @Override
    public Collection<CommentDto> getAll() {
        System.out.println("Get all");
        return storage.values();
    }

    @Override
    public void creat(CommentDto item) {
        System.out.println("Create");
        int nextId = storage.isEmpty() ? 1 : storage.lastKey() + 1;
        item.setId(nextId);
        storage.put(nextId, item);
    }

    @Override
    public void update(Integer id, CommentDto item) {
        if (!storage.containsKey(id)) {
            return;
        }
        System.out.println("Update by ID: " + id);
        item.setId(id);
        storage.put(id, item);
    }

    @Override
    public void delete(Integer id) {
        System.out.println("Delete by ID: " + id);
        storage.remove(id);
    }
}
