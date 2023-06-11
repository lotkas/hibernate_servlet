package repository;

import model.Sale;
import model.modelDTO.GeneralDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.Utils;

import java.util.List;

public class SaleRepository {
    private static final SessionFactory sessionFactory = Utils.getSessionFactory();

    public GeneralDTO<Sale> save(Sale sale) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            session.save(sale);
            transaction.commit();

            return new GeneralDTO<>(sale, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public GeneralDTO<Sale> update(Sale sale) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            session.get(Sale.class, sale.getId());
            session.update(sale);

            transaction.commit();

            return new GeneralDTO<>(sale, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public GeneralDTO<Sale> getById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            Sale sale = session.get(Sale.class, id);
            transaction.commit();

            if (sale == null) {
                return new GeneralDTO<>(null, "Sale not founded");
            }
            return new GeneralDTO<>(sale, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public GeneralDTO<Sale> deleteById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            Sale sale = session.get(Sale.class, id);
            session.delete(sale);
            transaction.commit();

            return new GeneralDTO<>(sale, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public GeneralDTO<Sale> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            List<Sale> sales = session.createQuery("FROM Sale", Sale.class).getResultList();
            transaction.commit();

            return new GeneralDTO<>(sales);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
