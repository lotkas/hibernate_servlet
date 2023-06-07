package service.serviceImpl;

import model.Product;
import model.modelDTO.GeneralDTO;
import repository.repositoryImpl.ProductRepositoryImpl;
import service.ProductService;


public class ProductServiceImpl implements ProductService {
    private final ProductRepositoryImpl productRepository;

    public ProductServiceImpl() {
        productRepository = new ProductRepositoryImpl();
    }

    @Override
    public GeneralDTO<Product> save(GeneralDTO<Product> product) {
        return productRepository.save(product);
    }

    @Override
    public GeneralDTO<Product> update(GeneralDTO<Product> product) {
        return productRepository.update(product);
    }

    @Override
    public GeneralDTO<Product> getById(Long id) {
        return productRepository.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public GeneralDTO<Product> getAll() {
        return productRepository.getAll();
    }
}
