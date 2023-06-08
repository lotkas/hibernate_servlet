package service.serviceImpl;

import model.Manager;
import model.Product;
import model.modelDTO.GeneralDTO;
import model.modelDTO.managerDTO.ManagerUpdateRequestDTO;
import repository.repositoryImpl.ManagerRepositoryImpl;
import repository.repositoryImpl.ProductRepositoryImpl;
import service.ManagerService;


public class ManagerServiceImpl implements ManagerService {
    private final ProductRepositoryImpl productRepository;

    private final ManagerRepositoryImpl managerRepository;

    public ManagerServiceImpl() {
        managerRepository = new ManagerRepositoryImpl();
        productRepository = new ProductRepositoryImpl();
    }

    @Override
    public GeneralDTO<Manager> save(GeneralDTO<Manager> manager) {
        return managerRepository.save(manager);
    }

    @Override
    public GeneralDTO<Manager> update(GeneralDTO<Manager> manager) {
        return managerRepository.update(manager);
    }

    @Override
    public GeneralDTO<Manager> getById(Long id) {
        return managerRepository.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        managerRepository.deleteById(id);
    }

    @Override
    public GeneralDTO<Manager> getAll() {
        return managerRepository.getAll();
    }

    @Override
    public GeneralDTO<Product> deleteProductById(ManagerDeleteDTO dto) {
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

    @Override
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

    @Override
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
    }
}
