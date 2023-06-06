package repository.repositoryImpl;

import model.Product;
import model.User;
import model.modelDTO.EntranceDTO;
import model.modelDTO.UserDonateDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repository.UserRepository;
import utils.Utils;

import java.math.BigDecimal;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private static final SessionFactory sessionFactory = Utils.getSessionFactory();

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
    public void updateUserBalance(Product product, User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            BigDecimal productPrice = product.getPrice();
            BigDecimal userBalance = user.getBalance();

            BigDecimal updatedUserBalance = userBalance.subtract(productPrice);
            user.setBalance(updatedUserBalance);

            session.update(user);

            transaction.commit();

        } catch (Exception e) {
            throw new RuntimeException(e);
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
    public User deleteById(Long aLong) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        User user = session.load(User.class, aLong);
        session.delete(user);

        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public List<User> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String selectAll = "FROM User";
        //createQuery - создать query запрос
        List<User> users = session.createQuery(selectAll, User.class).getResultList();

        transaction.commit();
        session.close();

        return users;
    }

    @Override
    public User findUser(EntranceDTO dto) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            String entranceUser = "FROM User WHERE firstName = :firstName AND lastName = :lastName AND password = :password";
            User user = session.createQuery(entranceUser, User.class)
                    .setParameter("firstName", dto.getFirstName())
                    .setParameter("lastName", dto.getLastName())
                    .setParameter("password", dto.getPassword())
                    .uniqueResult();

            transaction.commit();

            return user;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
