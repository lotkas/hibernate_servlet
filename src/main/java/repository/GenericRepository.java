package repository;

public interface GenericRepository <T, ID> {
    T save (T type);
    T update (T type);
    T getById (ID id);
    T deleteById (ID id);
    T getAll();
}
