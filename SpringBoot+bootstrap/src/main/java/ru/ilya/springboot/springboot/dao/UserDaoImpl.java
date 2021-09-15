package ru.ilya.springboot.springboot.dao;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import ru.ilya.springboot.springboot.entities.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDAO {

    @PersistenceContext()
    EntityManager entityManager;
    

    @Override
    public List<User> allUser() {
        return entityManager.createQuery(
                "from User ", User.class).getResultList();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUserById(long id) {
       return entityManager.find(User.class, id);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void removeUserById(long id) {
        User user = getUserById(id);
        if (user != null) {
            entityManager.remove(user);
        } else {
            System.out.println("There is no such user");
        }
    }

    @Override
    public User getUserByNickname(String nickname) {

        return entityManager.createQuery("SELECT u FROM User u WHERE u.nickname = '"+ nickname +"'", User.class).getSingleResult();
    }

}
