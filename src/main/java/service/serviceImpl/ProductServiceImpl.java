package service.serviceImpl;

import model.Product;
import repository.repositoryImpl.ProductRepositoryImpl;
import service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductRepositoryImpl productRepository;

    public ProductServiceImpl() {
        productRepository = new ProductRepositoryImpl();
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        return productRepository.update(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.getAll();
    }
}
