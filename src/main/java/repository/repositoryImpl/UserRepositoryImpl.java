package repository.repositoryImpl;

import model.Product;
import model.User;
import model.modelDTO.EntranceDTO;
import model.modelDTO.GeneralDTO;
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
    public GeneralDTO<User> save(GeneralDTO<User> user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            session.save(user.getEntity());
            transaction.commit();

            return new GeneralDTO<>(user.getEntity(), null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GeneralDTO<User> update(GeneralDTO<User> user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();

        try (session) {
            User userFromEntity = user.getEntity();
            session.get(User.class, userFromEntity.getId());

            userFromEntity.setFirstName(userFromEntity.getFirstName());
            userFromEntity.setLastName(userFromEntity.getLastName());
            userFromEntity.setPassword(userFromEntity.getPassword());
            userFromEntity.setBalance(userFromEntity.getBalance());

            session.update(userFromEntity);
            transaction.commit();

            return new GeneralDTO<>(userFromEntity, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
    public GeneralDTO<User> update(UserDonateDTO dto) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            String query = "FROM User WHERE firstName = :firstName AND lastName = :lastName AND password = :password";
            User user = session.createQuery(query, User.class)
                    .setParameter("firstName", dto.getFirstName())
                    .setParameter("lastName", dto.getLastName())
                    .setParameter("password", dto.getPassword())
                    .uniqueResult();

            user.setBalance(user.getBalance().add(dto.getBalance()));
            transaction.commit();

            return new GeneralDTO<>(user, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GeneralDTO<User> getById(Long aLong) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            User user = session.get(User.class, aLong);
            transaction.commit();

            return new GeneralDTO<>(user, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GeneralDTO<User> deleteById(Long aLong) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            User user = session.load(User.class, aLong);
            session.delete(user);
            transaction.commit();

            return new GeneralDTO<>(user, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GeneralDTO<User> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String selectAll = "FROM User";

        try (session) {
            List<User> users = session.createQuery(selectAll, User.class).getResultList();
            transaction.commit();

            return new GeneralDTO<>(users);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GeneralDTO<User> findUser(EntranceDTO dto) {
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

            return new GeneralDTO<>(user, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
