package service;

import model.Manager;
import model.Product;
import model.modelDTO.GeneralDTO;
import model.modelDTO.ManagerAddDTO;
import model.modelDTO.ManagerDeleteDTO;
import model.modelDTO.ManagerUpdateDTO;

public interface ManagerService extends GenericService<GeneralDTO<Manager>, Long> {
    GeneralDTO<Product> deleteProductById(ManagerDeleteDTO dto);
    GeneralDTO<Product> updateAvailableProduct(ManagerUpdateDTO dto);
    GeneralDTO<Product> saveProduct(ManagerAddDTO dto);
}
