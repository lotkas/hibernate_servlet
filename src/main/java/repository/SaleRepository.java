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

    public GeneralDTO<Sale> save(GeneralDTO<Sale> sale) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            session.save(sale.getEntity());
            transaction.commit();

            return new GeneralDTO<>(sale.getEntity(), null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public GeneralDTO<Sale> update(GeneralDTO<Sale> sale) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            Sale saleFromEntity = sale.getEntity();
            session.get(Sale.class, saleFromEntity.getId());
            saleFromEntity.setProduct(saleFromEntity.getProduct());
            saleFromEntity.setUser(saleFromEntity.getUser());
            session.update(sale);

            transaction.commit();

            return new GeneralDTO<>(saleFromEntity, null);
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

            return new GeneralDTO<>(sale, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public GeneralDTO<Sale> deleteById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            Sale sale = session.load(Sale.class, id);
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
