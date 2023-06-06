package utils;

import model.Manager;
import model.Product;
import model.Sale;
import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        try {
            // application.properties
            Properties properties = new Properties();
            properties.load(HibernateUtil.class.getClassLoader().getResourceAsStream("application.properties"));

            Configuration configuration = new Configuration();
            configuration.setProperties(properties);

            configuration.addAnnotatedClass(Product.class);
            configuration.addAnnotatedClass(Sale.class);
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Manager.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }
}
