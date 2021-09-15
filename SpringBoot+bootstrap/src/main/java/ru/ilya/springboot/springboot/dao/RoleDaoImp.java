package ru.ilya.springboot.springboot.dao;

import org.springframework.stereotype.Repository;
import ru.ilya.springboot.springboot.entities.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class RoleDaoImp implements RoleDAO {

    @PersistenceContext()
    EntityManager entityManager;

    @Override
    public List allRole() {
        return entityManager.createQuery(
                "from Role").getResultList();
    }

    @Override
    public Role findRoleById(Long id) throws NoSuchElementException {
        return entityManager.find(Role.class,id);
    }

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }

}
