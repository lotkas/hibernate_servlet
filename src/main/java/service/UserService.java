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
}