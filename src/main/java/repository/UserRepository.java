package repository;

import model.Product;
import model.User;
import model.modelDTO.EntranceDTO;
import model.modelDTO.GeneralDTO;
import model.modelDTO.userDTO.UserSaveRequestDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.Utils;

import java.math.BigDecimal;
import java.util.List;

public class UserRepository {
    private static final SessionFactory sessionFactory = Utils.getSessionFactory();

    public GeneralDTO<User> save(UserSaveRequestDTO userDTO) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            User user = new User();
            user.setBalance(userDTO.getBalance());
            user.setPassword(userDTO.getPassword());
            user.setLastName(userDTO.getLastName());
            user.setFirstName(userDTO.getFirstName());

            session.save(user);
            transaction.commit();

            return new GeneralDTO<>(user, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public GeneralDTO<User> update(User userUpdateDTO) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            session.update(userUpdateDTO);
            transaction.commit();

            return new GeneralDTO<>(userUpdateDTO, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

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

    public GeneralDTO<User> getById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            User user = session.get(User.class, id);
            transaction.commit();

            if (user == null) {
                return new GeneralDTO<>(null, "User not founded");
            }
            return new GeneralDTO<>(user, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public GeneralDTO<User> deleteById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            User user = session.load(User.class, id);
            session.delete(user);
            transaction.commit();

            return new GeneralDTO<>(user, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public GeneralDTO<User> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            List<User> users = session.createQuery("FROM User", User.class).getResultList();
            transaction.commit();

            return new GeneralDTO<>(users);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

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
