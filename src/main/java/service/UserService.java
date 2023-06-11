package service;

import model.Product;
import model.Sale;
import model.User;
import model.modelDTO.GeneralDTO;
import model.modelDTO.userDTO.UserDonateRequestDTO;
import repository.ProductRepository;
import repository.SaleRepository;
import repository.UserRepository;

import java.time.LocalDateTime;

public class UserService {
    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    private final SaleRepository saleRepository;

    public UserService() {
        saleRepository = new SaleRepository();
        productRepository = new ProductRepository();
        userRepository = new UserRepository();
    }

    public GeneralDTO<User> save(GeneralDTO<User> user) {
        return userRepository.save(user);
    }

    public GeneralDTO<User> update(GeneralDTO<User> user) {
        return userRepository.update(user);
    }

    public GeneralDTO<User> update(UserDonateRequestDTO dto) {
        User user = userRepository.findUser(dto).getEntity();
        if (user == null) {
            return new GeneralDTO<>(null, "User not founded");
        }
        return userRepository.update(dto);
    }

    public GeneralDTO<User> getById(Long id) {
        return userRepository.getById(id);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

   /* public GeneralDTO<User> getAll() {
        return userRepository.getAll();
    }

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
    }*/
}