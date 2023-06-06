package repository.repositoryImpl;

import model.Product;
import model.modelDTO.UserBuyDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repository.ProductRepository;
import utils.HibernateUtil;

import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public Product save(Product type) {
        //add product by manager
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(type);

        transaction.commit();
        session.close();

        return type;
    }

    @Override
    public Product update(Product type) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Product product = session.get(Product.class, type.getId());
        product.setPrice(type.getPrice());
        product.setAvailable((type.getAvailable()));
        session.update(product);

        transaction.commit();
        session.close();

        return product;
    }

    @Override
    public Product getById(Long aLong) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            Product product = session.get(Product.class, aLong);
            if (product == null) {
                return null;
            }
            return product;

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public Product deleteById(Long aLong) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Product product = session.get(Product.class, aLong);
        session.delete(product);

        transaction.commit();
        session.close();

        return product;
    }

    @Override
    public List<Product> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String s = "FROM Product";

        List<Product> products = session.createQuery(s, Product.class).getResultList();

        transaction.commit();
        session.close();

        return products;
    }

    @Override
    public Product getByIdFromUser(UserBuyDTO dto) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Product product = session.get(Product.class, dto.getProductId());

        transaction.commit();
        session.close();

        return product;
    }
}
