package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.UserDaoFactory;

import java.sql.SQLException;
import java.util.List;

public class UserHibernateDAO implements UserDAO{
    private Session session;

    public UserHibernateDAO(Session session) {
        this.session = session;
    }



    @Override
    public List<User> findAllUsers() {
        List<User> users = UserDaoFactory.getSessionFactory().openSession().createQuery("From User").list();
        return users;
    }

    @Override
    public void saveUser(User user) throws SQLException {
        Session session = UserDaoFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    @Override
    public User findUser(long id) throws SQLException {
        return UserDaoFactory.getSessionFactory().openSession().get(User.class, id);
    }

    @Override
    public void updateUser(User user) throws SQLException {
        Session session = UserDaoFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    @Override
    public void deleteUser(User user) throws SQLException {
        Session session = UserDaoFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }
}
