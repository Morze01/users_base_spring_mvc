package userdb.service;

import userdb.model.Role;

import java.util.List;

public interface RoleService {
    public List<Role> getRoles();

    public void saveRole(Role role);

    public Role getRole(int theId);

    public void deleteRole(int theId);

    public Role findRoleByName(String role);
}
