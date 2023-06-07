package repository.repositoryImpl;

import model.Manager;
import model.modelDTO.EntranceDTO;
import model.modelDTO.GeneralDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repository.ManagerRepository;
import utils.Utils;

import java.util.List;

public class ManagerRepositoryImpl implements ManagerRepository {
    private static final SessionFactory sessionFactory = Utils.getSessionFactory();

    @Override
    public GeneralDTO<Manager> save(GeneralDTO<Manager> manager) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            session.save(manager.getEntity());
            transaction.commit();

            return new GeneralDTO<>(manager.getEntity(), null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GeneralDTO<Manager> update(GeneralDTO<Manager> manager) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            Manager managerFromEntity = manager.getEntity();
            session.get(Manager.class, managerFromEntity.getId());
            managerFromEntity.setFirstName(managerFromEntity.getFirstName());
            managerFromEntity.setLastName(managerFromEntity.getLastName());
            managerFromEntity.setPassword(managerFromEntity.getPassword());
            session.update(manager);

            transaction.commit();

            return new GeneralDTO<>(managerFromEntity, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GeneralDTO<Manager> getById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            Manager manager = session.load(Manager.class, id);
            transaction.commit();

            return new GeneralDTO<>(manager, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
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

    @Override
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

    @Override
    public GeneralDTO<Manager> findManager(EntranceDTO dto) {
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

            return new GeneralDTO<>(manager, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
