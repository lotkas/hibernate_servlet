package repository;

import model.Product;
import model.modelDTO.GeneralDTO;
import model.modelDTO.productDTO.ProductSaveRequestDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.Utils;

import java.util.List;

public class ProductRepository {
    private static final SessionFactory sessionFactory = Utils.getSessionFactory();

    public GeneralDTO<Product> save(ProductSaveRequestDTO productSaveRequestDTO) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            Product product = new Product();
            product.setName(productSaveRequestDTO.getName());
            product.setAvailable(productSaveRequestDTO.getAvailable());
            product.setPrice(productSaveRequestDTO.getPrice());

            session.save(product);
            transaction.commit();

            return new GeneralDTO<>(product, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public GeneralDTO<Product> update(Product productUpdateRequestDTO) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            session.update(productUpdateRequestDTO);

            transaction.commit();

            return new GeneralDTO<>(productUpdateRequestDTO, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public GeneralDTO<Product> getById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            Product product = session.get(Product.class, id);
            transaction.commit();

            if (product == null) {
                return new GeneralDTO<>(null, "Product not founded");
            }
            return new GeneralDTO<>(product, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

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
}
