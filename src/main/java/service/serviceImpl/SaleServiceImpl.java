package service.serviceImpl;

import model.Sale;
import repository.repositoryImpl.SaleRepositoryImpl;
import service.SaleService;

import java.util.List;

public class SaleServiceImpl implements SaleService {
    private final SaleRepositoryImpl saleRepository;

    public SaleServiceImpl() {
        saleRepository = new SaleRepositoryImpl();
    }

    @Override
    public Sale save(Sale type) {
        return saleRepository.save(type);
    }

    @Override
    public Sale update(Sale type) {
        return saleRepository.update(type);
    }

    @Override
    public Sale getById(Long aLong) {
        return saleRepository.getById(aLong);
    }

    @Override
    public void deleteById(Long aLong) {
        saleRepository.deleteById(aLong);
    }

    @Override
    public List<Sale> getAll() {
        return saleRepository.getAll();
    }
}
