package service;

import model.Manager;
import model.Product;
import model.modelDTO.ManagerAddDTO;
import model.modelDTO.ManagerDeleteDTO;
import model.modelDTO.ManagerUpdateDTO;

public interface ManagerService extends GenericService<Manager, Long> {
    Product deleteProductById(ManagerDeleteDTO dto);
    Product updateAvailableProduct(ManagerUpdateDTO dto);
    Product saveProduct(ManagerAddDTO dto);
}
