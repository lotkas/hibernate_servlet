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

        session.save(sale);

        transaction.commit();

        return sale;
    }

    @Override
    public Sale update(Sale sale) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.get(Sale.class, sale.getId());
        sale.setProduct(sale.getProduct());
        sale.setUser(sale.getUser());
        session.update(sale);

        transaction.commit();
        session.close();

        return sale;
    }

    @Override
    public Sale getById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Sale sale = session.get(Sale.class, id);

        transaction.commit();
        session.close();

        return sale;
    }

    @Override
    public Sale deleteById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Sale sale = session.load(Sale.class, id);
        session.delete(sale);

        transaction.commit();
        session.close();
        return sale;
    }

    @Override
    public List<Sale> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String selectAll = "FROM Sale";

        List<Sale> sales = session.createQuery(selectAll, Sale.class).getResultList();

        transaction.commit();
        session.close();

        return sales;
    }
}
