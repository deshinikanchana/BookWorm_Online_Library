package BookWorm.Config;

import BookWorm.Entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class SessionFactoryConfig {

    private static SessionFactoryConfig factoryConfig;

    private final SessionFactory sessionFactory;
    private SessionFactoryConfig() throws IOException {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Admin.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Book.class);
        configuration.addAnnotatedClass(Branch.class);
        configuration.addAnnotatedClass(BookTransaction.class);

        Properties properties = new Properties();
        properties.load(ClassLoader.getSystemResourceAsStream("hibernate.properties"));
        configuration.mergeProperties(properties);

        sessionFactory = configuration.buildSessionFactory();

    }

    public static SessionFactoryConfig getInstance() throws IOException {
        return (null == factoryConfig)
                ? factoryConfig = new SessionFactoryConfig()
                : factoryConfig;
    }
    public Session getSession() throws IOException {
        return sessionFactory.openSession();
    }
}
