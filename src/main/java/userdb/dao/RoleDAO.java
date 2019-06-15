package userdb.dao;


import userdb.model.Role;

import java.util.List;

public interface RoleDAO {
    public List<Role> getRoles();

    public void saveRole(Role role);

    public Role getRole(int theId);

    public void deleteRole(int theId);

    public Role findeRoleByName(String role);
}
