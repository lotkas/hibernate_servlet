package repository;

import model.Product;
import model.User;
import model.modelDTO.EntranceDTO;
import model.modelDTO.GeneralDTO;
import model.modelDTO.userDTO.UserDonateRequestDTO;

public interface UserRepository extends GenericRepository<GeneralDTO<User>, Long> {

    void updateUserBalance(Product product, User user);

    GeneralDTO<User> update(UserDonateRequestDTO dto);

    GeneralDTO<User> findUser(EntranceDTO dto);
}
