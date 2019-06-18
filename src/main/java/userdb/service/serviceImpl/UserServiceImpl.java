package userdb.service.serviceImpl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import userdb.dao.RoleDAO;
import userdb.dao.UserDAO;
import userdb.model.Role;
import userdb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import userdb.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getUsers() {
        return userDAO.getUsers();
    }

    @Override
    public void saveUser(User user, boolean isNewUser) {
        if (isNewUser) {
            userDAO.saveUser(user);
        } else {
            User persistUser = userDAO.getUser(user.getId());
            persistUser.setUsername(user.getUsername());
            persistUser.setPassword(user.getPassword());
            persistUser.setEnabled(user.isEnabled());
            persistUser.setRoleSet(user.getRoleSet());
            userDAO.updateUser(persistUser);
        }
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

    @Override
    public void registerNewUser(String username, String password) {
        Role userRole = roleDAO.findeRoleByName("ROLE_USER");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(userRole);
        User newUser = new User(username,passwordEncoder.encode(password),roleSet);
        userDAO.saveUser(newUser);
    }
}
