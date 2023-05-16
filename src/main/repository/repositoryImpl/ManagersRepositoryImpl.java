package repository.repositoryImpl;

import model.Managers;
import repository.ManagersRepository;

import java.util.List;

public class ManagersRepositoryImpl implements ManagersRepository {

    private ManagersRepositoryImpl() {
    }


    @Override
    public Managers save(Managers type) {
        return type;
    }

    @Override
    public Managers update(Managers type) {
        return null;
    }

    @Override
    public Managers getById(Long aLong) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {
    }

    @Override
    public List<Managers> getAll() {
        return null;
    }
}
