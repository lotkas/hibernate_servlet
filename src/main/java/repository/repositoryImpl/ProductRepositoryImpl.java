package repository.repositoryImpl;

import model.Product;
import model.modelDTO.GeneralDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repository.ProductRepository;
import utils.Utils;

import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {
    private static final SessionFactory sessionFactory = Utils.getSessionFactory();

    @Override
    public GeneralDTO<Product> save(GeneralDTO<Product> product) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            session.save(product.getEntity());
            transaction.commit();

            return new GeneralDTO<>(product.getEntity(), null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GeneralDTO<Product> update(GeneralDTO<Product> product) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            Product productFromEntity = product.getEntity();
            session.get(Product.class, productFromEntity.getId());
            productFromEntity.setPrice(productFromEntity.getPrice());
            productFromEntity.setAvailable((productFromEntity.getAvailable()));
            session.update(product);

            transaction.commit();

            return new GeneralDTO<>(productFromEntity, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GeneralDTO<Product> getById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            Product product = session.get(Product.class, id);

            transaction.commit();

            return new GeneralDTO<>(product, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GeneralDTO<Product> deleteById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            Product product = session.get(Product.class, id);
            session.delete(product);

            transaction.commit();

            return new GeneralDTO<>(product, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GeneralDTO<Product> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            List<Product> products = session.createQuery("FROM Product", Product.class).getResultList();
            transaction.commit();

            return new GeneralDTO<>(products);
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
