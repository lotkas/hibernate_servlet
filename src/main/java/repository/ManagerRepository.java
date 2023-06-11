package repository;

import model.Manager;
import model.modelDTO.EntranceDTO;
import model.modelDTO.GeneralDTO;
import model.modelDTO.managerDTO.ManagerSaveRequestDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.Utils;

import java.util.List;

public class ManagerRepository {
    private static final SessionFactory sessionFactory = Utils.getSessionFactory();

    public GeneralDTO<Manager> save(ManagerSaveRequestDTO managerDTO) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            Manager manager = new Manager();
            manager.setPassword(managerDTO.getPassword());
            manager.setLastName(managerDTO.getLastName());
            manager.setFirstName(managerDTO.getFirstName());

            session.save(manager);
            transaction.commit();

            return new GeneralDTO<>(manager, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public GeneralDTO<Manager> update(Manager manager) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            session.update(manager);

            transaction.commit();

            return new GeneralDTO<>(manager, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public GeneralDTO<Manager> getById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            Manager manager = session.get(Manager.class, id);
            transaction.commit();

            if (manager == null) {
                return new GeneralDTO<>(null, "Manager not founded");
            }
            return new GeneralDTO<>(manager, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public GeneralDTO<Manager> deleteById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            Manager manager = session.load(Manager.class, id);
            session.delete(manager);
            transaction.commit();

            return new GeneralDTO<>(manager, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public GeneralDTO<Manager> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            List<Manager> managers = session.createQuery("FROM Manager", Manager.class).getResultList();
            transaction.commit();

            return new GeneralDTO<>(managers);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public GeneralDTO<Manager> findManager(EntranceDTO managerDTO) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            String entranceManager = "FROM Manager WHERE firstName = :firstName AND lastName = :lastName AND password = :password";
            Manager manager = session.createQuery(entranceManager, Manager.class)
                    .setParameter("firstName", managerDTO.getFirstName())
                    .setParameter("lastName", managerDTO.getLastName())
                    .setParameter("password", managerDTO.getPassword())
                    .uniqueResult();

            transaction.commit();

            return new GeneralDTO<>(manager, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
