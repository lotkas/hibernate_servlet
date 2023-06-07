package service;

public interface GenericService <T, ID> {
    T save(T type);
    T update(T type);
    T getById(ID id);
    void deleteById(ID id);
    T getAll();
}
