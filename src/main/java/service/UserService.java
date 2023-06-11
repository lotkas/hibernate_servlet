package service;

import model.User;
import model.modelDTO.GeneralDTO;
import model.modelDTO.userDTO.UserSaveRequestDTO;
import model.modelDTO.userDTO.UserUpdateDTO;
import repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService() {
        userRepository = new UserRepository();
    }

    public GeneralDTO<User> save(UserSaveRequestDTO user) {
        return userRepository.save(user);
    }

    public GeneralDTO<User> update(UserUpdateDTO userUpdateDTO) {
        User user = userRepository.findUser(userUpdateDTO).getEntity();
        if (user == null) {
            return new GeneralDTO<>(null, "User not founded");
        } else {
            user.setBalance(userUpdateDTO.getUpdatedBalance());
            user.setFirstName(userUpdateDTO.getUpdatedFirstName());
            user.setPassword(userUpdateDTO.getUpdatedPassword());
            user.setLastName(userUpdateDTO.getUpdatedLastName());
            return userRepository.update(user);
        }
    }

    public GeneralDTO<User> getById(Long id) {
        return userRepository.getById(id);
    }

    public GeneralDTO<User> deleteById(Long id) {
        User user = userRepository.getById(id).getEntity();
        if (user == null) {
            return new GeneralDTO<>(null, "User not founded");
        }
        return userRepository.deleteById(id);
    }

   public GeneralDTO<User> getAll() {
        return userRepository.getAll();
    }

    /*public GeneralDTO<Sale> buyProduct(UserBuyDTO dto) {
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