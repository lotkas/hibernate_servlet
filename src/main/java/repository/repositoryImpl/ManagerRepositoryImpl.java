package repository.repositoryImpl;

import model.Manager;
import model.modelDTO.EntranceDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repository.ManagerRepository;
import utils.Utils;

import java.util.List;

public class ManagerRepositoryImpl implements ManagerRepository {
    private static final SessionFactory sessionFactory = Utils.getSessionFactory();

    @Override
    public Manager save(Manager manager) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            session.save(manager);
            transaction.commit();

            return manager;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Manager update(Manager manager) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            session.get(Manager.class, manager.getId());
            manager.setFirstName(manager.getFirstName());
            manager.setLastName(manager.getLastName());
            manager.setPassword(manager.getPassword());
            session.update(manager);

            transaction.commit();

            return manager;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Manager getById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            Manager manager = session.load(Manager.class, id);
            transaction.commit();

            return manager;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Manager deleteById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            Manager manager = session.load(Manager.class, id);
            session.delete(manager);
            transaction.commit();

            return manager;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Manager> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            List<Manager> managers = session.createQuery("FROM Manager", Manager.class).getResultList();

            transaction.commit();

            return managers;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Manager findManager(EntranceDTO dto) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            String entranceManager = "FROM Manager WHERE firstName = :firstName AND lastName = :lastName AND password = :password";
            Manager manager = session.createQuery(entranceManager, Manager.class)
                    .setParameter("firstName", dto.getFirstName())
                    .setParameter("lastName", dto.getLastName())
                    .setParameter("password", dto.getPassword())
                    .uniqueResult();

            transaction.commit();

            return manager;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
