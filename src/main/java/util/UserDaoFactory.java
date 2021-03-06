package util;
import dao.UserDAO;
import dao.UserHibernateDAO;
import dao.UserJdbcDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.*;
import java.io.FileNotFoundException;
import java.util.Properties;

public class UserDaoFactory {
    private static String daotype;

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = DBHelper
                    .getInstance()
                    .getConfiguration()
                    .buildSessionFactory();
        }
        return sessionFactory;
    }

    public UserDaoFactory(String daotype) {
        this.daotype = daotype;
    }

    public static String getDaotype() throws IOException {
        Properties prop = new Properties();
        try(InputStream in = UserDaoFactory.class.getResourceAsStream("/config.properties"))
        {
            prop.load(in);
        }
        return prop.getProperty("daotype");
    }

    public static UserDAO getUserDAO(String daotype) {
        if (daotype.equals("jdbc")) {
            return new UserJdbcDAO(DBHelper.getInstance().getConnection());
        } else {
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                    .applySettings(DBHelper.getInstance().getConfiguration().getProperties());
            return new UserHibernateDAO(DBHelper.getInstance().getConfiguration()
                    .buildSessionFactory(builder.build())
                    .openSession());
        }
    }
}
