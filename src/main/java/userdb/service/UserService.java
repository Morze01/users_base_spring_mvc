package userdb.service;

import org.springframework.data.repository.CrudRepository;
import userdb.model.User;

import java.util.List;

public interface UserService {
    public List<User> getUsers();

    public void saveUser(User user);

    public User getUser(int theId);

    public void deleteUser(int theId);

    public User findByUsername(String username);
}
