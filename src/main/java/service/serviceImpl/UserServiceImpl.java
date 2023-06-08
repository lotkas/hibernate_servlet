package service.serviceImpl;

import model.Product;
import model.Sale;
import model.User;
import model.modelDTO.GeneralDTO;
import model.modelDTO.userDTO.UserDonateRequestDTO;
import repository.repositoryImpl.ProductRepositoryImpl;
import repository.repositoryImpl.SaleRepositoryImpl;
import repository.repositoryImpl.UserRepositoryImpl;
import service.UserService;

import java.time.LocalDateTime;

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
    public GeneralDTO<User> save(GeneralDTO<User> user) {
        return userRepository.save(user);
    }

    @Override
    public GeneralDTO<User> update(GeneralDTO<User> user) {
        return userRepository.update(user);
    }

    @Override
    public GeneralDTO<User> update(UserDonateRequestDTO dto) {
        User user = userRepository.findUser(dto).getEntity();
        if (user == null) {
            return new GeneralDTO<>(null, "User not founded");
        }
        return userRepository.update(dto);
    }

    @Override
    public GeneralDTO<User> getById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public GeneralDTO<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public GeneralDTO<Sale> buyProduct(UserBuyDTO dto) {
        User user = userRepository.findUser(dto).getEntity();
        if (user == null) {
            return null;
        }

        Product product = productRepository.getById(dto.getProductId()).getEntity();
        if (product == null || product.getAvailable() <= 0) {
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
        GeneralDTO<Sale> saleDTO = new GeneralDTO<>(sale, null);

        userRepository.updateUserBalance(product, user);

        return saleRepository.save(saleDTO);
    }
}