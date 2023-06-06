package repository;

import model.Manager;
import model.modelDTO.EntranceDTO;

public interface ManagerRepository extends GenericRepository<Manager, Long> {
    Manager findManager (EntranceDTO type);
}
