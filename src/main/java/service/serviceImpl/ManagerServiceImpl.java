package service.serviceImpl;

import model.Manager;
import model.Product;
import model.modelDTO.ManagerAddDTO;
import model.modelDTO.ManagerDeleteDTO;
import model.modelDTO.ManagerUpdateDTO;
import repository.repositoryImpl.ManagerRepositoryImpl;
import repository.repositoryImpl.ProductRepositoryImpl;
import service.ManagerService;

import java.util.List;

public class ManagerServiceImpl implements ManagerService {
    private final ProductRepositoryImpl productRepository;

    private final ManagerRepositoryImpl managerRepository;

    public ManagerServiceImpl() {
        managerRepository = new ManagerRepositoryImpl();
        productRepository = new ProductRepositoryImpl();
    }

    @Override
    public Manager save(Manager manager) {
        return managerRepository.save(manager);
    }

    @Override
    public Manager update(Manager manager) {
        return managerRepository.update(manager);
    }

    @Override
    public Manager getById(Long id) {
        return managerRepository.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        managerRepository.deleteById(id);
    }

    @Override
    public List<Manager> getAll() {
        return managerRepository.getAll();
    }

    @Override
    public Product deleteProductById(ManagerDeleteDTO dto) {
        Manager manager = managerRepository.findManager(dto);
        if (manager == null) {
            throw new IllegalArgumentException("Manger not found");
        }
        Product product = productRepository.getById(dto.getProductId());
        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        } else {
            return productRepository.deleteById(dto.getProductId());
        }
    }

    @Override
    public Product updateAvailableProduct(ManagerUpdateDTO dto) {
        Manager manager = managerRepository.findManager(dto);
        if (manager == null) {
            throw new IllegalArgumentException("Manger not found");
        }
        Product product = productRepository.getById(dto.getProductId());
        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        } else {
            product.setAvailable(dto.getAvailable());
            return productRepository.update(product);
        }
    }

    @Override
    public Product saveProduct(ManagerAddDTO dto) {
        Manager manager = managerRepository.findManager(dto);
        if (manager == null) {
            return null;
        } else {
            Product product = new Product();
            product.setName(dto.getProductName());
            product.setPrice(dto.getProductPrice());
            product.setAvailable(dto.getProductAvailable());
            return productRepository.save(product);
        }
    }
}
