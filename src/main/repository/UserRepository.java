package repository;

import model.Sale;
import model.User;
import model.modelDTO.EntranceDTO;
import model.modelDTO.UserBuyDTO;
import model.modelDTO.UserDonateDTO;

public interface UserRepository extends GenericRepository<User, Long> {

    Sale buyProduct(UserBuyDTO dto);

    UserDonateDTO update(UserDonateDTO dto);
    User findUser(EntranceDTO dto);
}
