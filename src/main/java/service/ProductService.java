package service;

import model.Manager;
import model.Product;
import model.modelDTO.GeneralDTO;
import model.modelDTO.productDTO.ProductDeleteByIdRequestDTO;
import model.modelDTO.productDTO.ProductSaveRequestDTO;
import model.modelDTO.productDTO.ProductUpdateRequestDTO;
import repository.ManagerRepository;
import repository.ProductRepository;


public class ProductService {
    private final ProductRepository productRepository;

    private final ManagerRepository managerRepository;

    public ProductService() {
        productRepository = new ProductRepository();
        managerRepository = new ManagerRepository();
    }

    public GeneralDTO<Product> save(ProductSaveRequestDTO productRequestDTO) {
        Manager manager = managerRepository.findManager(productRequestDTO).getEntity();
        if (manager == null) {
            return new GeneralDTO<>(null, "Manager not founded");
        } else {
            return productRepository.save(productRequestDTO);
        }
    }

    public GeneralDTO<Product> update(ProductUpdateRequestDTO productUpdateRequestDTO) {
        Manager manager = managerRepository.findManager(productUpdateRequestDTO).getEntity();
        if (manager == null) {
            return new GeneralDTO<>(null, "Manager not founded");
        }

        Product product = productRepository.getById(productUpdateRequestDTO.getProductId()).getEntity();
        if (product == null) {
            return new GeneralDTO<>(null, "Product not founded");
        } else {
            product.setAvailable(productUpdateRequestDTO.getUpdatedAvailable());
            product.setPrice(productUpdateRequestDTO.getUpdatedPrice());
            product.setName(productUpdateRequestDTO.getUpdatedName());

            return productRepository.update(product);
        }
    }

    public GeneralDTO<Product> getById(Long id) {
        return productRepository.getById(id);
    }

    public GeneralDTO<Product> deleteById(ProductDeleteByIdRequestDTO productRequestDTO) {
        Manager manager = managerRepository.findManager(productRequestDTO).getEntity();
        if (manager == null) {
            return new GeneralDTO<>(null, "Manager not founded");
        }

        Product product = productRepository.getById(productRequestDTO.getProductId()).getEntity();
        if (product == null) {
            return new GeneralDTO<>(null, "Product not founded");
        } else {
            return productRepository.deleteById(productRequestDTO.getProductId());
        }
    }

    public GeneralDTO<Product> getAll() {
        return productRepository.getAll();
    }
}
