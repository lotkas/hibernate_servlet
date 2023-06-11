package service;

import model.Manager;
import model.modelDTO.GeneralDTO;
import model.modelDTO.managerDTO.ManagerSaveRequestDTO;
import model.modelDTO.managerDTO.ManagerUpdateRequestDTO;
import repository.ManagerRepository;

public class ManagerService {

    private final ManagerRepository managerRepository;

    public ManagerService() {
        managerRepository = new ManagerRepository();
    }

    public GeneralDTO<Manager> save(ManagerSaveRequestDTO manager) {
        return managerRepository.save(manager);
    }

    public GeneralDTO<Manager> update(ManagerUpdateRequestDTO managerDTO) {
        Manager manager = managerRepository.findManager(managerDTO).getEntity();
        if (manager == null) {
            return new GeneralDTO<>(null, "Manager not founded");
        } else {
            manager.setPassword(managerDTO.getUpdatedPassword());
            manager.setFirstName(managerDTO.getUpdatedFirstName());
            manager.setLastName(managerDTO.getUpdatedLastName());

            return managerRepository.update(manager);
        }
    }

   public GeneralDTO<Manager> getById(Long id) {
        return managerRepository.getById(id);
    }

    public GeneralDTO<Manager> deleteById(Long id) {
        Manager manager = managerRepository.getById(id).getEntity();
        if (manager == null) {
            return new GeneralDTO<>(null, "Manager not founded");
        } else {
            return managerRepository.deleteById(id);
        }
    }

    public GeneralDTO<Manager> getAll() {
        return managerRepository.getAll();
    }
}
