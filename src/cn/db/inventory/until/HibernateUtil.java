package cn.db.inventory.until;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Created by jiyun on 2016/6/30.
 */
public class HibernateUtil {
    private static SessionFactory factory;
    private static ServiceRegistry serviceRegistry;

    static {
        try {
            Configuration configuration = new Configuration().configure();
            serviceRegistry = new ServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .buildServiceRegistry();
            factory = configuration.buildSessionFactory(serviceRegistry);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory(){
        return factory;
    }

    public static Session getSession() {
        Session session = null;
        if (factory != null) {
            session = factory.openSession();
        }
        return session;
    }

    public static void closeSession(Session session) {
        if (session != null) {
            session.close();
        }
    }

}
