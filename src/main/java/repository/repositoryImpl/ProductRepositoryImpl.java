package repository.repositoryImpl;

import model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repository.ProductRepository;
import utils.Utils;

import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {
    private static final SessionFactory sessionFactory = Utils.getSessionFactory();

    @Override
    public Product save(Product product) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            session.save(product);
            transaction.commit();

            return product;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product update(Product product) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            session.get(Product.class, product.getId());
            product.setPrice(product.getPrice());
            product.setAvailable((product.getAvailable()));
            session.update(product);

            transaction.commit();

            return product;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product getById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            Product product = session.get(Product.class, id);

            transaction.commit();

            return product;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product deleteById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            Product product = session.get(Product.class, id);
            session.delete(product);

            transaction.commit();

            return product;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            List<Product> products = session.createQuery("FROM Product", Product.class).getResultList();
            transaction.commit();

            return products;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateAvailable(Product product) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            product.setAvailable(product.getAvailable() - 1);
            session.update(product);

            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
