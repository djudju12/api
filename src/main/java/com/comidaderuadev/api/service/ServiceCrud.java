package com.comidaderuadev.api.service;

import java.util.List;

public interface ServiceCrud<T, ID> {
    public List<T> findAll();
    public T findById(ID id);
    public T add(T object);
    public T update(T object);
    public void delete(ID id);
}
