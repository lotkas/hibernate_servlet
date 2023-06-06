package repository;

import model.Product;
import model.User;
import model.modelDTO.EntranceDTO;
import model.modelDTO.UserDonateDTO;

public interface UserRepository extends GenericRepository<User, Long> {

    void updateUserBalance(Product product, User user);

    UserDonateDTO update(UserDonateDTO dto);
    User findUser(EntranceDTO dto);
}
