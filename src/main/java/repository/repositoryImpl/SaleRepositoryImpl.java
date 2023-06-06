package repository.repositoryImpl;

import model.Sale;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repository.SaleRepository;
import utils.Utils;

import java.util.List;

public class SaleRepositoryImpl implements SaleRepository {
    private static final SessionFactory sessionFactory = Utils.getSessionFactory();

    @Override
    public Sale save(Sale sale) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            session.save(sale);
            transaction.commit();

            return sale;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Sale update(Sale sale) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            session.get(Sale.class, sale.getId());
            sale.setProduct(sale.getProduct());
            sale.setUser(sale.getUser());
            session.update(sale);

            transaction.commit();

            return sale;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Sale getById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            Sale sale = session.get(Sale.class, id);
            transaction.commit();

            return sale;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Sale deleteById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            Sale sale = session.load(Sale.class, id);
            session.delete(sale);
            transaction.commit();

            return sale;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Sale> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            List<Sale> sales = session.createQuery("FROM Sale", Sale.class).getResultList();
            transaction.commit();

            return sales;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
