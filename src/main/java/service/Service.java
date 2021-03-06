package service;
import model.User;
import util.UserDaoFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Service {

    private String daotype;

    public Service(String daotype) {
        this.daotype = daotype;
    }


    private static Service service;
    public static Service getInstance() throws IOException {
        if (service == null){
            service = new Service(UserDaoFactory.getDaotype());
        }
        return service;
    }

    public User findUser(int id) throws SQLException {
        return UserDaoFactory.getUserDAO(daotype).findUser(id);
    }

    public void saveUser(User user) throws SQLException {
        UserDaoFactory.getUserDAO(daotype).saveUser(user);
    }

    public void deleteUser(User user) throws SQLException {
        UserDaoFactory.getUserDAO(daotype).deleteUser(user);
    }

    public void updateUser(User user) throws SQLException {
        UserDaoFactory.getUserDAO(daotype).updateUser(user);
    }

    public List<User> findAllUsers() {
        return UserDaoFactory.getUserDAO(daotype).findAllUsers();
    }

}
