package repository;

import model.Product;
import model.modelDTO.GeneralDTO;


public interface ProductRepository extends GenericRepository<GeneralDTO<Product>, Long>{
    void updateAvailable(Product product);
}
