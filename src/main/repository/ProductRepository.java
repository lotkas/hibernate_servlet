package repository;

import model.Product;
import model.modelDTO.UserBuyDTO;

public interface ProductRepository extends GenericRepository<Product, Long>{
    Product getByIdFromUser(UserBuyDTO dto);
}
