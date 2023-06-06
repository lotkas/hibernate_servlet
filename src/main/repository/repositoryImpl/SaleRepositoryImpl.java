package repository.repositoryImpl;

import model.Product;
import model.Sale;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repository.SaleRepository;
import utils.HibernateUtil;

import java.util.List;

public class SaleRepositoryImpl implements SaleRepository {
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public Sale save(Sale type) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(type);

        transaction.commit();

        return type;
    }

    @Override
    public Sale update(Sale type) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Sale sale = session.get(Sale.class, type.getId());
        sale.setProduct(type.getProduct());
        sale.setUser(type.getUser());
        sale.setAddDate(type.getAddDate());
        session.update(sale);

        transaction.commit();
        session.close();

        return sale;
    }

    @Override
    public Sale getById(Long aLong) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Sale sale = session.get(Sale.class, aLong);

        transaction.commit();
        session.close();

        return sale;
    }

    @Override
    public Product deleteById(Long aLong) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Sale sale = session.load(Sale.class, aLong);
        session.delete(sale);

        transaction.commit();
        session.close();
        return null;
    }

    @Override
    public List<Sale> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String s = "FROM Sale";

        List<Sale> sales = session.createQuery(s, Sale.class).getResultList();

        transaction.commit();
        session.close();

        return sales;
    }
}
