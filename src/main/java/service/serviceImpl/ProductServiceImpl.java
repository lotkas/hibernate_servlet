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
    public Product save(Product type) {
        return productRepository.save(type);
    }

    @Override
    public Product update(Product type) {
        return productRepository.update(type);
    }

    @Override
    public Product getById(Long aLong) {
        return productRepository.getById(aLong);
    }

    @Override
    public void deleteById(Long aLong) {
        productRepository.deleteById(aLong);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.getAll();
    }
}
