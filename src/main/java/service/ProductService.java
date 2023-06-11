package service;

import model.Product;
import model.modelDTO.GeneralDTO;
import repository.ProductRepository;


public class ProductService {
    private final ProductRepository productRepository;

    public ProductService() {
        productRepository = new ProductRepository();
    }

    public GeneralDTO<Product> save(GeneralDTO<Product> product) {
        return productRepository.save(product);
    }

    public GeneralDTO<Product> update(GeneralDTO<Product> product) {
        return productRepository.update(product);
    }

    public GeneralDTO<Product> getById(Long id) {
        return productRepository.getById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public GeneralDTO<Product> getAll() {
        return productRepository.getAll();
    }
}
