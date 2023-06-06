package service.serviceImpl;

import model.Product;
import model.Sale;
import model.User;
import model.modelDTO.UserBuyDTO;
import model.modelDTO.UserDonateDTO;
import repository.repositoryImpl.ProductRepositoryImpl;
import repository.repositoryImpl.SaleRepositoryImpl;
import repository.repositoryImpl.UserRepositoryImpl;
import service.UserService;

import java.time.LocalDateTime;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRepositoryImpl userRepository;
    private final ProductRepositoryImpl productRepository;
    private final SaleRepositoryImpl saleRepository;

    public UserServiceImpl() {
        saleRepository = new SaleRepositoryImpl();
        productRepository = new ProductRepositoryImpl();
        userRepository = new UserRepositoryImpl();
    }

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
            return null;
        }

        Product product = productRepository.getById(dto.getProductId());
        if (product == null) {
            return null;
        }
        if (product.getAvailable() <= 0) {
            return null;
        }
        productRepository.updateAvailable(product);

        int balance = user.getBalance().compareTo(product.getPrice());
        if (balance < 0) {
            return null;
        }
        Sale sale = new Sale();
        sale.setUser(user);
        sale.setProduct(product);
        sale.setAddDate(LocalDateTime.now());

        userRepository.updateUserBalance(product, user);

        return saleRepository.save(sale);
    }
}