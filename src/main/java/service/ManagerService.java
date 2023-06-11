package service;

import model.Manager;
import model.modelDTO.GeneralDTO;
import model.modelDTO.managerDTO.ManagerSaveRequestDTO;
import model.modelDTO.managerDTO.ManagerUpdateRequestDTO;
import repository.ManagerRepository;

public class ManagerService {

    private final ManagerRepository managerRepository;

    public ManagerService() {
        managerRepository = new ManagerRepository();
    }

    public GeneralDTO<Manager> save(ManagerSaveRequestDTO manager) {
        return managerRepository.save(manager);
    }

    public GeneralDTO<Manager> update(ManagerUpdateRequestDTO managerDTO) {
        Manager manager = managerRepository.findManager(managerDTO).getEntity();
        if (manager == null) {
            return new GeneralDTO<>(null, "Manager not founded");
        } else {
            manager.setPassword(managerDTO.getUpdatedPassword());
            manager.setFirstName(managerDTO.getUpdatedFirstName());
            manager.setLastName(managerDTO.getUpdatedLastName());
            return managerRepository.update(manager);
        }
    }

   public GeneralDTO<Manager> getById(Long id) {
        return managerRepository.getById(id);
    }

    public GeneralDTO<Manager> deleteById(Long id) {
        Manager manager = managerRepository.getById(id).getEntity();
        if (manager == null) {
            return new GeneralDTO<>(null, "Manager not founded");
        }
        return managerRepository.deleteById(id);
    }

    public GeneralDTO<Manager> getAll() {
        return managerRepository.getAll();
    }

  /*  public GeneralDTO<Product> deleteProductById(ManagerDeleteDTO dto) {
        Manager manager = managerRepository.findManager(dto).getEntity();
        if (manager == null) {
            return new GeneralDTO<>(null, "Manager not found");
        }
        Product product = productRepository.getById(dto.getProductId()).getEntity();
        if (product == null) {
            return new GeneralDTO<>(null, "Product not found");
        } else {
            return productRepository.deleteById(product.getId());
        }
    }

    public GeneralDTO<Product> updateAvailableProduct(ManagerUpdateRequestDTO dto) {
        Manager manager = managerRepository.findManager(dto).getEntity();
        if (manager == null) {
            throw new IllegalArgumentException("Manger not found");
        }
        Product product = productRepository.getById(dto.getProductId()).getEntity();
        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        } else {
            product.setAvailable(dto.getAvailable());
            GeneralDTO<Product> productDTO = new GeneralDTO<>(product, null);

            return productRepository.update(productDTO);
        }
    }

    public GeneralDTO<Product> saveProduct(ManagerAddDTO dto) {
        Manager manager = managerRepository.findManager(dto).getEntity();
        if (manager == null) {
            return null;
        } else {
            Product product = new Product();
            product.setName(dto.getProductName());
            product.setPrice(dto.getProductPrice());
            product.setAvailable(dto.getProductAvailable());

            GeneralDTO<Product> productDTO = new GeneralDTO<>(product, null);

            return productRepository.save(productDTO);
        }
    }*/
}
