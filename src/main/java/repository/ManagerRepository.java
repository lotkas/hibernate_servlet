package repository;

import model.Manager;
import model.modelDTO.EntranceDTO;
import model.modelDTO.GeneralDTO;

public interface ManagerRepository extends GenericRepository<GeneralDTO<Manager>, Long> {
    GeneralDTO<Manager> findManager (EntranceDTO type);
}
