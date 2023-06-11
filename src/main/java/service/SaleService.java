package service;

import model.Manager;
import model.Product;
import model.Sale;
import model.User;
import model.modelDTO.GeneralDTO;
import model.modelDTO.saleDTO.SaleDeleteByIdRequestDTO;
import model.modelDTO.saleDTO.SaleSaveRequestDTO;
import model.modelDTO.saleDTO.SaleUpdateRequestDTO;
import repository.ManagerRepository;
import repository.ProductRepository;
import repository.SaleRepository;
import repository.UserRepository;

import java.time.LocalDateTime;

public class SaleService {
    private final SaleRepository saleRepository;

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    private final ManagerRepository managerRepository;

    public SaleService() {
        this.saleRepository = new SaleRepository();
        this.userRepository = new UserRepository();
        this.productRepository = new ProductRepository();
        this.managerRepository = new ManagerRepository();
    }

    public GeneralDTO<Sale> save(SaleSaveRequestDTO saleRequestDTO) {
        User user = userRepository.findUser(saleRequestDTO).getEntity();
        if (user == null) {
            return new GeneralDTO<>(null, "User not founded");
        }

        Product product = productRepository.getById(saleRequestDTO.getProductId()).getEntity();
        if (product == null) {
            return new GeneralDTO<>(null, "Product not founded");
        }
        if (product.getAvailable() <= 0) {
            return new GeneralDTO<>(null, "Product is not available");
        }
        productRepository.updateProductAvailable(product);

        int balance = user.getBalance().compareTo(product.getPrice());
        if (balance < 0) {
            return new GeneralDTO<>(null, "You don't have money for this");
        }
        userRepository.updateUserBalance(product, user);

        Sale sale = new Sale();
        sale.setUser(user);
        sale.setProduct(product);
        sale.setAddDate(LocalDateTime.now());


        return saleRepository.save(sale);
    }

    public GeneralDTO<Sale> update(SaleUpdateRequestDTO saleUpdateRequestDTO) {
        Manager manager = managerRepository.findManager(saleUpdateRequestDTO).getEntity();
        if (manager == null) {
            return new GeneralDTO<>(null, "Manager not founded");
        }

        Sale sale = saleRepository.getById(saleUpdateRequestDTO.getSaleId()).getEntity();
        if (sale == null) {
            return new GeneralDTO<>(null, "Sale not founded");
        }

        Product product = productRepository.getById(saleUpdateRequestDTO.getProductId()).getEntity();
        if (product == null) {
            return new GeneralDTO<>(null, "Product not founded");
        }

        User user = userRepository.getById(saleUpdateRequestDTO.getUserId()).getEntity();
        if (user == null) {
            return new GeneralDTO<>(null, "User not founded");
        }

        else {
            sale.setUser(user);
            sale.setProduct(product);
            sale.setAddDate(saleUpdateRequestDTO.getAddDate());

            return saleRepository.update(sale);
        }
    }

    public GeneralDTO<Sale> getById(Long id) {
        return saleRepository.getById(id);
    }

    public GeneralDTO<Sale> deleteById(SaleDeleteByIdRequestDTO saleDeleteByIdRequestDTO) {
        Manager manager = managerRepository.findManager(saleDeleteByIdRequestDTO).getEntity();
        if (manager == null) {
            return new GeneralDTO<>(null, "Manager not founded");
        }

        Sale sale = saleRepository.getById(saleDeleteByIdRequestDTO.getSaleId()).getEntity();
        if (sale == null) {
            return new GeneralDTO<>(null, "Sale not founded");
        } else {
            return saleRepository.deleteById(sale.getId());
        }
    }

    public GeneralDTO<Sale> getAll() {
        return saleRepository.getAll();
    }
}
