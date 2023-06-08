package service;

import model.Manager;
import model.Product;
import model.modelDTO.GeneralDTO;
import model.modelDTO.managerDTO.ManagerUpdateRequestDTO;

public interface ManagerService extends GenericService<GeneralDTO<Manager>, Long> {
    GeneralDTO<Product> deleteProductById(ManagerDeleteDTO dto);
    GeneralDTO<Product> updateAvailableProduct(ManagerUpdateRequestDTO dto);
    GeneralDTO<Product> saveProduct(ManagerAddDTO dto);
}
