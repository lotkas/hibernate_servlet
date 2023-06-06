package service;

import model.Sale;
import model.User;
import model.modelDTO.UserBuyDTO;
import model.modelDTO.UserDonateDTO;

public interface UserService extends GenericService<User, Long>{

    Sale buyProduct(UserBuyDTO dto);
    User findUser(UserBuyDTO dto);
    UserDonateDTO update(UserDonateDTO dto);

}
