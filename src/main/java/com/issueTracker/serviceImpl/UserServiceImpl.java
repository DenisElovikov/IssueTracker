package com.issueTracker.serviceImpl;

import com.issueTracker.entities.users.User;
import com.issueTracker.models.bindingModels.RegisterUserModel;
import com.issueTracker.repositories.UserRepository;
import com.issueTracker.services.UserService;
import com.issueTracker.utils.parser.interfaces.ModelParser;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserServiceImpl implements UserService{

    @Inject
    private UserRepository userRepository;

    @Inject
    private ModelParser modelParser;

    @Override
    public void create(RegisterUserModel registerUserModel) {
        User user = this.modelParser.convert(registerUserModel, User.class);
        this.userRepository.create(user);
    }

    @Override
    public RegisterUserModel findByUsername(String username) {
        User user = this.userRepository.findByUsername(username);
        RegisterUserModel registerUserModel = null;
        if (user != null) {
            registerUserModel = this.modelParser.convert(user, RegisterUserModel.class);
        }

        return registerUserModel;
    }

    @Override
    public RegisterUserModel findByUsernameAndPassword(String username, String password) {
        User user = this.userRepository.findByUsernameAndPassword(username, password);
        RegisterUserModel registerUserModel = null;
        if (user != null) {
            registerUserModel = this.modelParser.convert(user, RegisterUserModel.class);
        }

        return registerUserModel;
    }
}
