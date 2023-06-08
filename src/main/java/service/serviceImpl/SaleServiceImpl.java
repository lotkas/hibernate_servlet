package service.serviceImpl;

import repository.repositoryImpl.SaleRepositoryImpl;
import service.SaleService;

public class SaleServiceImpl implements SaleService {
    private final SaleRepositoryImpl saleRepository;

    public SaleServiceImpl() {
        this.saleRepository = new SaleRepositoryImpl();
    }

    @Override
    public Object save(Object type) {
        return null;
    }

    @Override
    public Object update(Object type) {
        return null;
    }

    @Override
    public Object getById(Object o) {
        return null;
    }

    @Override
    public void deleteById(Object o) {

    }

    @Override
    public Object getAll() {
        return null;
    }
}
