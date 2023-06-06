package repository;

import model.Product;

import java.util.List;

public interface GenericRepository <T, ID> {
    T save (T type);
    T update (T type);
    T getById (ID id);
    T deleteById (ID id);
    List<T> getAll();
}
