package com.issueTracker.serviceImpl;

import com.issueTracker.entities.roles.Role;
import com.issueTracker.entities.users.User;
import com.issueTracker.models.bindingModels.LoggedUserModel;
import com.issueTracker.models.bindingModels.LoginUserModel;
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
        long totalUsers = this.userRepository.getTotalUsers();
        if (totalUsers ==0) {
            user.setRole(Role.ADMIN);
        }else {
            user.setRole(Role.USER);
        }

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
    public LoggedUserModel findByUsernameAndPassword(String username, String password) {
        User user = this.userRepository.findByUsernameAndPassword(username, password);
        LoggedUserModel loggedUserModel = null;
        if (user != null) {
            loggedUserModel = this.modelParser.convert(user, LoggedUserModel.class);
        }

        return loggedUserModel;
    }
}
