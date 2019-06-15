package userdb.service.serviceImpl;

import org.springframework.stereotype.Service;
import userdb.dao.UserDAO;
import userdb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import userdb.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public List<User> getUsers() {
        return userDAO.getUsers();
    }

    @Override
    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    @Override
    public User getUser(int theId) {
        return userDAO.getUser(theId);
    }

    @Override
    public void deleteUser(int theId) {
        userDAO.deleteUser(theId);
    }

    @Override
    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }
}
