package utils;

import jakarta.servlet.http.HttpServletResponse;
import model.Manager;
import model.Product;
import model.Sale;
import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

public class Utils {
    private static SessionFactory sessionFactory;

    public Utils() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null)
            synchronized (Utils.class) {
                if (sessionFactory == null) {
                    try {
                        // application.properties
                        Properties properties = new Properties();
                        properties.load(Utils.class.getClassLoader().getResourceAsStream("application.properties"));

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
                }
            }
        return sessionFactory;
    }

    public static void returnNullResponse(HttpServletResponse resp, PrintWriter out, String message) throws IOException {
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, message);
        out.print(resp);
        out.flush();
    }
}
