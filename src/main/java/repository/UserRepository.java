package repository;

import model.Product;
import model.User;
import model.modelDTO.EntranceDTO;
import model.modelDTO.GeneralDTO;
import model.modelDTO.UserDonateDTO;

public interface UserRepository extends GenericRepository<GeneralDTO<User>, Long> {

    void updateUserBalance(Product product, User user);

    GeneralDTO<User> update(UserDonateDTO dto);

    GeneralDTO<User> findUser(EntranceDTO dto);
}
