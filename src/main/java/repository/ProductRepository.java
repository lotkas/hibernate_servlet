package repository;

import model.Product;


public interface ProductRepository extends GenericRepository<Product, Long>{
    void updateAvailable(Product product);
}
