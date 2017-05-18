package com.issueTracker.repositoryImpl;

import com.issueTracker.entities.users.User;
import com.issueTracker.repositories.UserRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class UserRepositoryImpl implements UserRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(User user) {
        this.entityManager.persist(user);
    }

    @Override
    public User findByUsername(String username) {
        Query query = this.entityManager.createQuery("SELECT u FROM User AS u " +
            "WHERE u.username = :username");
        query.setParameter("username", username);
        List<User> users = query.getResultList();
        return users.stream().findFirst().orElse(null);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        Query query = this.entityManager.createQuery("SELECT u FROM User AS u " +
                "WHERE u.username = :username " +
                "AND u.password = :password ");
        query.setParameter("username", username);
        query.setParameter("password", password);
        List<User> users = query.getResultList();
        return users.stream().findFirst().orElse(null);
    }
}
