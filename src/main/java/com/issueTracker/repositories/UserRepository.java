package com.issueTracker.repositories;

import com.issueTracker.entities.users.User;

public interface UserRepository {

    void create(User user);

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);
}
