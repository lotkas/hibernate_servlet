package repository.repositoryImpl;

import model.Product;
import model.Sale;
import model.User;
import model.modelDTO.EntranceDTO;
import model.modelDTO.UserBuyDTO;
import model.modelDTO.UserDonateDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repository.UserRepository;
import utils.HibernateUtil;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private SaleRepositoryImpl saleRepository = new SaleRepositoryImpl();
    private ProductRepositoryImpl productRepository = new ProductRepositoryImpl();
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public User save(User type) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(type);

        transaction.commit();
        session.close();

        return type;
    }

    @Override
    public User update(User type) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();

        User user = session.get(User.class, type.getId());
        user.setFirstName(type.getFirstName());
        user.setLastName(type.getLastName());
        user.setPassword(type.getPassword());
        user.setBalance(type.getBalance());
        session.update(user);

        transaction.commit();
        session.close();

        return user;
    }

    @Override
    public Sale buyProduct(UserBuyDTO dto) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            Product product = productRepository.getById(dto.getProductId());
            User user = findUser(dto);

            BigDecimal productPrice = product.getPrice();
            BigDecimal userBalance = user.getBalance();

            Sale sale = new Sale();
            sale.setUser(user);
            sale.setProduct(product);
            sale.setAddDate(LocalDateTime.now());
            saleRepository.save(sale);

            BigDecimal updatedUserBalance = userBalance.subtract(productPrice);
            user.setBalance(updatedUserBalance);

            product.setAvailable(product.getAvailable() - 1);

            productRepository.update(product);
            session.update(user);

            transaction.commit();

            return sale;
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public UserDonateDTO update(UserDonateDTO dto) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String query = "FROM User WHERE firstName = :firstName AND lastName = :lastName AND password = :password";
        User user = session.createQuery(query, User.class)
                .setParameter("firstName", dto.getFirstName())
                .setParameter("lastName", dto.getLastName())
                .setParameter("password", dto.getPassword())
                .uniqueResult();

        user.setBalance(user.getBalance().add(dto.getBalance()));

        transaction.commit();
        session.close();

        return dto;
    }

    @Override
    public User getById(Long aLong) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        //load - извлечение обьекта из бд по индентификатору
        User user = session.get(User.class, aLong);

        transaction.commit();
        session.close();

        return user;
    }

    @Override
    public Product deleteById(Long aLong) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        User user = session.load(User.class, aLong);
        session.delete(user);

        transaction.commit();
        session.close();
        return null;
    }

    @Override
    public List<User> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String s = "FROM User";
        //createQuery - создать query запрос
        List<User> users = session.createQuery(s, User.class).getResultList();

        transaction.commit();
        session.close();

        return users;
    }

    @Override
    public User findUser(EntranceDTO dto) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            String user = "FROM User WHERE firstName = :firstName AND lastName = :lastName AND password = :password";
            User users = session.createQuery(user, User.class)
                    .setParameter("firstName", dto.getFirstName())
                    .setParameter("lastName", dto.getLastName())
                    .setParameter("password", dto.getPassword())
                    .uniqueResult();

            if (users == null) {
                return null;
            }
            return users;

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            transaction.commit();
            session.close();
        }
    }
}
