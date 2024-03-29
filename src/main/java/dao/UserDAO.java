package dao;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    List<User> findAllUsers();
    void saveUser(User user) throws SQLException;
    User findUser(long id) throws SQLException;
    void updateUser(User user) throws SQLException;
    void deleteUser(User user) throws SQLException;
}
