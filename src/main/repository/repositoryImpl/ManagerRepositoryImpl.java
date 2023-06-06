package repository.repositoryImpl;

import model.Manager;
import model.Product;
import model.modelDTO.EntranceDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repository.ManagerRepository;
import utils.HibernateUtil;

import java.util.List;

public class ManagerRepositoryImpl implements ManagerRepository {
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public Manager save(Manager type) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(type);

        transaction.commit();
        session.close();

        return type;
    }

    @Override
    public Manager update(Manager type) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Manager manager = session.get(Manager.class, type.getId());
        manager.setFirstName(type.getFirstName());
        manager.setLastName(type.getLastName());
        manager.setPassword(type.getPassword());
        session.update(manager);

        transaction.commit();
        session.close();

        return manager;
    }

    @Override
    public Manager getById(Long aLong) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Manager managers = session.load(Manager.class, aLong);

        transaction.commit();
        session.close();

        return managers;
    }

    @Override
    public Product deleteById(Long aLong) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Manager managers = session.load(Manager.class, aLong);
        session.delete(managers);

        transaction.commit();
        session.close();
        return null;
    }

    @Override
    public List<Manager> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String s = "FROM Manager";

        List<Manager> managers = session.createQuery(s, Manager.class).getResultList();

        transaction.commit();
        session.close();

        return managers;
    }

    @Override
    public Manager findManager(EntranceDTO dto) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            String managers = "FROM Manager WHERE firstName = :firstName AND lastName = :lastName AND password = :password";
            Manager manager = session.createQuery(managers, Manager.class)
                    .setParameter("firstName", dto.getFirstName())
                    .setParameter("lastName", dto.getLastName())
                    .setParameter("password", dto.getPassword())
                    .uniqueResult();

            if (manager == null) {
                return null;
            }
            return manager;

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            transaction.commit();
            session.close();
        }
    }
}
