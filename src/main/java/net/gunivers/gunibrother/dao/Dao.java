package net.gunivers.gunibrother.dao;

import java.util.List;
import java.util.UUID;

public interface Dao<T> {
    T get(UUID uuid);
    List<T> getAll();
    void save(T t);
    void update(T t, String[] params);
    void delete(UUID uuid);
}
