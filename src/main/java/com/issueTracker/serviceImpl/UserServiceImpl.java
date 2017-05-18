package com.issueTracker.serviceImpl;

import com.issueTracker.models.bindingModels.RegisterUserModel;
import com.issueTracker.repositories.UserRepository;
import com.issueTracker.services.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserServiceImpl implements UserService{

    @Inject
    private UserRepository userRepository;

    @Override
    public void create(RegisterUserModel registerUserModel) {

    }

    @Override
    public RegisterUserModel findByUsername(String username) {
        return null;
    }

    @Override
    public RegisterUserModel findByUsernameAndPassword(String username, String password) {
        return null;
    }
}
