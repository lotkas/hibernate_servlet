package service;

import model.Sale;
import model.User;
import model.modelDTO.GeneralDTO;
import model.modelDTO.UserBuyDTO;
import model.modelDTO.UserDonateDTO;

public interface UserService extends GenericService<GeneralDTO<User>, Long>{

    GeneralDTO<Sale> buyProduct(UserBuyDTO dto);
    GeneralDTO<User> update(UserDonateDTO dto);
}
