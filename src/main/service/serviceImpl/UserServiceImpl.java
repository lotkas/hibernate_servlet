package service.serviceImpl;

import model.Product;
import model.Sale;
import model.User;
import model.modelDTO.UserBuyDTO;
import model.modelDTO.UserDonateDTO;
import repository.repositoryImpl.ProductRepositoryImpl;
import repository.repositoryImpl.UserRepositoryImpl;
import service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserRepositoryImpl userRepository = new UserRepositoryImpl();
    private ProductRepositoryImpl productsRepository = new ProductRepositoryImpl();

    @Override
    public User save(User type) {
        return userRepository.save(type);
    }

    @Override
    public User update(User type) {
        return userRepository.update(type);
    }

    @Override
    public UserDonateDTO update(UserDonateDTO dto) {
        User user = userRepository.findUser(dto);
        if (user == null) {
            return null;
        }
        try {
            return userRepository.update(dto);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public User getById(Long aLong) {
        return userRepository.getById(aLong);
    }

    @Override
    public void deleteById(Long aLong) {
        userRepository.deleteById(aLong);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public Sale buyProduct(UserBuyDTO dto) {
        User user = userRepository.findUser(dto);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        Product product = new Product();
        product.setId(dto.getProductId());
        productsRepository.getById(product.getId());
        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }
        if (product.getAvailable() <= 0) {
            throw  new IllegalArgumentException("Product not available");
        }
        int balance = user.getBalance().compareTo(product.getPrice());
        if (balance < 0) {
            throw new IllegalArgumentException("You don't have money for this");
        }
        else {
            return userRepository.buyProduct(dto);
        }
    }

    @Override
    public User findUser(UserBuyDTO dto) {
        return userRepository.findUser(dto);
    }
}