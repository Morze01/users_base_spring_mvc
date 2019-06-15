package userdb.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import userdb.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional (readOnly = true)
    public List<Role> getRoles() {
        return entityManager
                .createQuery("select u from Role u", Role.class)
                .getResultList();
    }

    @Override
    public void saveRole(Role role) {
        Role persistUser = entityManager.find(Role.class,role.getId());
        if (persistUser == null) {
            entityManager.persist(role);
        }
    }

    @Override
    public Role getRole(int theId) {
        return entityManager.find(Role.class, theId);
    }

    @Override
    public void deleteRole(int theId) {
        Role role = entityManager.find(Role.class,theId);
        entityManager.remove(role);
    }

    @Override
    public Role findeRoleByName(String role) {
        return entityManager
                .createQuery("select u from Role u where u.name=:Name", Role.class)
                .setParameter("Name",role)
                .getResultList()
                .stream()
                .findAny()
                .orElse(null);
    }
}
